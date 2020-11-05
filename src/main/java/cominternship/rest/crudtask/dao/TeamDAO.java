package cominternship.rest.crudtask.dao;


import cominternship.rest.crudtask.entity.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDAO extends JpaRepository<FootballTeam,Long> {

    @Query("select t from FootballTeam t where t.name= :name")
    List<FootballTeam> findByName(String name);
}
