package cominternship.rest.crudtask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name;
    private String lastName;
    private int age;

    public PlayerDTO(Player player){
        this.id = player.getId();
        this.name = player.getName();
        this.lastName = player.getLastName();
        this.age = player.getAge();
    }
}
