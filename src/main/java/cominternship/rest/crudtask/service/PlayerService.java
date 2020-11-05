package cominternship.rest.crudtask.service;

import cominternship.rest.crudtask.dao.PlayerDAO;
import cominternship.rest.crudtask.entity.Player;
import cominternship.rest.crudtask.entity.PlayerDTO;
import cominternship.rest.crudtask.exceptionshandler.CustomServiceException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@Service
@Data
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerDAO playerDAO;


    public List<PlayerDTO> getPlayers() throws CustomServiceException {
        try {
            List<PlayerDTO> playerDTOS = new ArrayList<>();
            for (Player player : playerDAO.findAll()) {
                playerDTOS.add(new PlayerDTO(player));
            }
            return playerDTOS;
        } catch (Exception e) {
            throw new CustomServiceException("Issue when getting all players from dataSource", INTERNAL_SERVER_ERROR);
        }
    }


    public void addPlayer(PlayerDTO playerDTO) throws CustomServiceException {
        try {
            validatePlayer(playerDTO);
            playerDAO.save(new Player(playerDTO));
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, player could not be saved", INTERNAL_SERVER_ERROR);
        }
    }


    public void removePlayer(Long id) throws CustomServiceException {
        try {
            playerDAO.deleteById(id);
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, could not delete player by id", INTERNAL_SERVER_ERROR);
        }
    }

    public PlayerDTO getOnePlayer(Long id) throws CustomServiceException {
        Optional<Player> optionalPlayer = playerDAO.findById(id);
        if (optionalPlayer.isPresent()) {
            return new PlayerDTO(optionalPlayer.get());
        }
        throw new CustomServiceException("The player could not be found", INTERNAL_SERVER_ERROR);
    }

    public List<PlayerDTO> getPlayerByName(String name) throws CustomServiceException {
        try {
            List<PlayerDTO> playerDTOS = new ArrayList<>();
            for (Player player : playerDAO.findByName(name)) {
                playerDTOS.add(new PlayerDTO(player));
            }
            if(playerDTOS.isEmpty())
                throw new Exception();
            return playerDTOS;
        } catch (Exception e) {
            throw new CustomServiceException("This player is not in our database", NOT_FOUND);
        }
    }


    public void updatePlayer(PlayerDTO playerDTO) throws CustomServiceException {
        Optional<Player> optionalPlayer = playerDAO.findById(playerDTO.getId());
        playerPresent(optionalPlayer);
        validatePlayer(playerDTO);
        Player toBeUpdated = optionalPlayer.get();
        toBeUpdated.setName(playerDTO.getName());
        toBeUpdated.setLastName(playerDTO.getLastName());
        toBeUpdated.setAge(playerDTO.getAge());
        try {
            playerDAO.save(toBeUpdated);
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, player could not be updated", INTERNAL_SERVER_ERROR);
        }
    }


    private void playerPresent(Optional<Player> optionalPlayer) throws CustomServiceException {
        if (optionalPlayer.isPresent()) {
            return;
        }
        throw new CustomServiceException("Player not found", INTERNAL_SERVER_ERROR);
    }

    private boolean validatePlayer(PlayerDTO playerDTO) throws CustomServiceException {
        try {
            if (validateName(playerDTO.getName())) {
                if (validateName(playerDTO.getLastName())) {
                    validateAge(playerDTO.getAge());
                    return true;
                } else {
                    throw new CustomServiceException("You are trying to set an invalid lastName", BAD_REQUEST);
                }
            }
            throw new CustomServiceException("You are trying to set an invalid name", BAD_REQUEST);
        } catch (NullPointerException npe) {
            throw new CustomServiceException("No null values here buddy", BAD_REQUEST);
        }
    }

    private void validateAge(Integer age) throws CustomServiceException {
        if (age >= 16 && age < 50) {
            return;
        }
        throw new CustomServiceException("This player can't play anymore", BAD_REQUEST);
    }


    private boolean validateName(String name) {
        return !name.isEmpty() && !name.contains(" ") && name.length() >= 1 && !name.matches(".*\\d.*");
    }
}
