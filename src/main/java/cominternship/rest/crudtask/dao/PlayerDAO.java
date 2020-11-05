package cominternship.rest.crudtask.dao;


import cominternship.rest.crudtask.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDAO extends JpaRepository<Player,Long> {
    @Query("select p from Player p where p.name= :name")
    List<Player> findByName(String name);
}
