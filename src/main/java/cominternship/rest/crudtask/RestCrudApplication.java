package cominternship.rest.crudtask;

import cominternship.rest.crudtask.dao.PlayerDAO;
import cominternship.rest.crudtask.dao.TeamDAO;
import cominternship.rest.crudtask.entity.FootballTeam;
import cominternship.rest.crudtask.entity.Player;
import cominternship.rest.crudtask.service.PlayerService;
import cominternship.rest.crudtask.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCrudApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(PlayerDAO playerDAO, TeamDAO teamDAO){
		return  (args) -> {
			FootballTeam barcelona = new FootballTeam("Barcelona","Spain",true);
			FootballTeam realMadrid = new FootballTeam("Real Madrid","Spain",false);
			FootballTeam bayern = new FootballTeam("Bayern Munchen","Germany",true);
			FootballTeam borussia = new FootballTeam("Borussia Dortmund","Germany",false);
			FootballTeam city = new FootballTeam("Manchester City","England",true);
			FootballTeam liverpool = new FootballTeam("Liverpool","England",false);

			List<Player> barcaPlayers = Arrays.asList(
					new Player("Lionel", "Messi",33),
					new Player("Ansu", "Fati",18),
					new Player("Marc Andre", "ter Stegen",28),
					new Player("Gerard", "Pique",33),
					new Player("Pedri", "Gonzalez",17)
			);
			for(Player player : barcaPlayers){
				playerDAO.save(player);
			}

			barcelona.setPlayerList(barcaPlayers);
			List<Player> realPlayers = Arrays.asList(
					new Player("Karim", "Benzema",30),
					new Player("Sergio", "Ramos",33),
					new Player("Ferland", "Mendy",23),
					new Player("Vinicius", "Junior",20),
					new Player("Toni", "Kroos",31)
			);
			for(Player player : realPlayers){
				playerDAO.save(player);
			}

			realMadrid.setPlayerList(realPlayers);


			List<Player> bayernPlayers = Arrays.asList(
					new Player("Robert", "Lewandowski",30),
					new Player("Leroy", "Sane",33),
					new Player("Serge", "Gnabry",23),
					new Player("Leon", "Goretzka",20),
					new Player("Manuel", "Neuer",31)
			);
			for(Player player : bayernPlayers){
				playerDAO.save(player);
			}
			bayern.setPlayerList(bayernPlayers);

			List<Player> borussiaPla = Arrays.asList(
					new Player("Erling", "Haaland",30),
					new Player("Axel", "Witsel",33),
					new Player("Marco", "Reus",23),
					new Player("Abdou", "Diallo",20),
					new Player("Manuel", "Akanji",31)
			);
			for(Player player : borussiaPla){
				playerDAO.save(player);
			}
			borussia.setPlayerList(borussiaPla);


			List<Player> manCityPlayers = Arrays.asList(
					new Player("Sergio", "Aguero",30),
					new Player("Riyad", "Mahrez",33),
					new Player("Kevin", "De Bruyne",23),
					new Player("Ederson", "Moraes",20),
					new Player("Raheem", "Sterling",31)
			);
			for(Player player : manCityPlayers){
				playerDAO.save(player);
			}
			city.setPlayerList(manCityPlayers);


			List<Player> liverpoolPlayers = Arrays.asList(
					new Player("Roberto", "Firmino",30),
					new Player("Virgil", "Van Dijk",33),
					new Player("Mohamed", "Salah",23),
					new Player("Alisson", "Becker",20),
					new Player("Sadio", "Mane",31)
			);
			for(Player player : liverpoolPlayers){
				playerDAO.save(player);
			}
			liverpool.setPlayerList(liverpoolPlayers);


			teamDAO.save(liverpool);
			teamDAO.save(realMadrid);
			teamDAO.save(barcelona);
			teamDAO.save(bayern);
			teamDAO.save(borussia);
			teamDAO.save(city);
		};
	}

}
