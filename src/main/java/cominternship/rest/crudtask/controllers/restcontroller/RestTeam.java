package cominternship.rest.crudtask.controllers.restcontroller;

import cominternship.rest.crudtask.entity.PlayerDTO;
import cominternship.rest.crudtask.entity.TeamDTO;
import cominternship.rest.crudtask.exceptionshandler.ErrorResponseObject;
import cominternship.rest.crudtask.exceptionshandler.ExceptionsHandler;
import cominternship.rest.crudtask.service.TeamService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/teams")
public class RestTeam {
    private final TeamService teamService;
    private final ExceptionsHandler exceptionsHandler;


    @GetMapping("/")
    public ResponseEntity<Object> players() throws Exception {
        return new ResponseEntity<>(teamService.getTeams(), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody TeamDTO teamDTO) throws Exception {
        teamService.addPlayer(teamDTO);
        return new ResponseEntity<>(new ErrorResponseObject("Team added to our secret database"), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) throws Exception {
        teamService.removePlayer(id);
        return new ResponseEntity<>(new ErrorResponseObject("Team deleted from our secret database"), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeamById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(teamService.getOneTeam(id), OK);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Object> getTeamByName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(teamService.getTeamsByName(name), OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTeamsDetails(@PathVariable Long id, @RequestBody TeamDTO teamDTO) throws Exception {
        teamDTO.setId(id);
        teamService.updatePlayer(teamDTO);
        return new ResponseEntity<>(new ErrorResponseObject("Team updated"), OK);
    }
}
