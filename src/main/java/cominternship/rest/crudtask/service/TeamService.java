package cominternship.rest.crudtask.service;

import cominternship.rest.crudtask.dao.TeamDAO;
import cominternship.rest.crudtask.entity.FootballTeam;
import cominternship.rest.crudtask.entity.TeamDTO;
import cominternship.rest.crudtask.exceptionshandler.CustomServiceException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@Data
@RequiredArgsConstructor
public class TeamService {

    private final TeamDAO teamDAO;


    public List<TeamDTO> getTeams() throws CustomServiceException {
        try{
            List<TeamDTO> teamDTOS = new ArrayList<>();
            for (FootballTeam team : teamDAO.findAll()){
                teamDTOS.add(new TeamDTO(team));
            }
            return teamDTOS;
        }catch (Exception e){
            throw new CustomServiceException("Issue when getting all team from dataSource", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addPlayer(TeamDTO teamDTO) throws CustomServiceException {
        try {
            validateTeam(teamDTO);
            teamDAO.save(new FootballTeam(teamDTO));
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, team could not be saved", INTERNAL_SERVER_ERROR);
        }
    }


    public void removePlayer(Long id) throws CustomServiceException {
        try {
            teamDAO.deleteById(id);
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, could not delete team by id", INTERNAL_SERVER_ERROR);
        }
    }

    public TeamDTO getOneTeam(Long id) throws CustomServiceException {
        Optional<FootballTeam> optionalFootballTeam = teamDAO.findById(id);
        if (optionalFootballTeam.isPresent()) {
            return new TeamDTO(optionalFootballTeam.get());
        }
        throw new CustomServiceException("The team could not be found", INTERNAL_SERVER_ERROR);
    }

    public List<TeamDTO> getTeamsByName(String name) throws CustomServiceException {
        try {
            List<TeamDTO> teamDTOS = new ArrayList<>();
            for (FootballTeam team : teamDAO.findByName(name)){
                teamDTOS.add(new TeamDTO(team));
            }
            if(teamDTOS.isEmpty())
                throw new NullPointerException();
            return teamDTOS;
        } catch (Exception e) {
            throw new CustomServiceException("This team is not in our database", NOT_FOUND);
        }
    }


    public void updatePlayer(TeamDTO teamDTO) throws CustomServiceException {
        Optional<FootballTeam> optionalFootballTeam = teamDAO.findById(teamDTO.getId());
        teamPresent(optionalFootballTeam);
        validateTeam(teamDTO);
        FootballTeam toBeUpdated = optionalFootballTeam.get();
        toBeUpdated.setName(teamDTO.getName());
        toBeUpdated.setCountry(teamDTO.getCountry());
        toBeUpdated.setAreChampions(teamDTO.isAreChampions());
        toBeUpdated.setPlayerList(teamDTO.getPlayerList());
        try {
            teamDAO.save(toBeUpdated);
        } catch (Exception e) {
            throw new CustomServiceException("Data Source issue, team could not be updated", INTERNAL_SERVER_ERROR);
        }
    }


    private void teamPresent(Optional<FootballTeam> footballTeam) throws CustomServiceException {
        if (footballTeam.isPresent()) {
            return;
        }
        throw new CustomServiceException("Team not found", INTERNAL_SERVER_ERROR);
    }

    private boolean validateTeam(TeamDTO teamDTO) throws CustomServiceException {
        try {
            if (validateDetails(teamDTO.getName())) {
                if (validateDetails(teamDTO.getCountry())) {
                    return true;
                } else {
                    throw new CustomServiceException("You are trying to set an invalid country", BAD_REQUEST);
                }
            }
            throw new CustomServiceException("You are trying to set an invalid team's name", BAD_REQUEST);
        } catch (NullPointerException npe) {
            throw new CustomServiceException("No null values here buddy", BAD_REQUEST);
        }
    }


    private boolean validateDetails(String name) {
        return !name.isEmpty() && !name.contains(" ") && name.length() >= 1 && !name.matches(".*\\d.*");
    }
}
