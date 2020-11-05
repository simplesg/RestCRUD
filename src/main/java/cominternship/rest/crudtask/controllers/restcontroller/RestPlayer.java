package cominternship.rest.crudtask.controllers.restcontroller;

import cominternship.rest.crudtask.entity.Player;
import cominternship.rest.crudtask.entity.PlayerDTO;
import cominternship.rest.crudtask.exceptionshandler.ErrorResponseObject;
import cominternship.rest.crudtask.exceptionshandler.ExceptionsHandler;
import cominternship.rest.crudtask.service.PlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/players")
public class RestPlayer {

    private final PlayerService playerService;
    private final ExceptionsHandler exceptionsHandler;


    @GetMapping("/")
    public ResponseEntity<Object> players() throws Exception {
        return new ResponseEntity<>(playerService.getPlayers(), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PlayerDTO playerDTO) throws Exception {
        playerService.addPlayer(playerDTO);
        return new ResponseEntity<>(new ErrorResponseObject("Player added to our secret database"), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) throws Exception {
        playerService.removePlayer(id);
        return new ResponseEntity<>(new ErrorResponseObject("Player deleted from our secret database"), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlayerDetails(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(playerService.getOnePlayer(id), OK);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Object> getPlayerByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerByName(name), OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePlayersDetails(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) throws Exception {
        playerDTO.setId(id);
        playerService.updatePlayer(playerDTO);
        return new ResponseEntity<>(new ErrorResponseObject("Player updated"), OK);
    }
}
