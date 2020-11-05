package cominternship.rest.crudtask.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_player")
@Getter
@Setter
@RequiredArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private int age;

    public Player(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Player(PlayerDTO playerDTO){
        this.age = playerDTO.getAge();
        this.id = playerDTO.getId();
        this.lastName = playerDTO.getLastName();
        this.name = playerDTO.getName();
    }
}
