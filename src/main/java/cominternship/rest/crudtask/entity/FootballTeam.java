package cominternship.rest.crudtask.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "t_team")
@Getter
@Setter
@NoArgsConstructor
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "TEAM_NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CHAMPIONS")
    private boolean areChampions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_NAME")
    private List<Player> playerList;

    public FootballTeam(String name, String country, boolean areChampions) {
        this.name = name;
        this.country = country;
        this.areChampions = areChampions;
    }

    public FootballTeam(TeamDTO team){
        this.id = team.getId();
        this.name = team.getName();
        this.country = team.getCountry();
        this.areChampions = team.isAreChampions();
        this.playerList = team.getPlayerList();
    }
}
