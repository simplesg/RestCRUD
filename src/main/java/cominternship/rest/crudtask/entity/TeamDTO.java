package cominternship.rest.crudtask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;
    private String name;
    private String country;
    private boolean areChampions;
    private List<Player> playerList;


    public TeamDTO(FootballTeam team) {
        this.id = team.getId();
        this.name = team.getName();
        this.country = team.getCountry();
        this.areChampions = team.isAreChampions();
        this.playerList = team.getPlayerList();
    }
}
