package com.game.services;

import com.game.entities.Artifact;
import com.game.entities.Hero;
import com.game.entities.Monster;
import com.game.entities.Powerable;
import com.game.hall.Door;
import com.game.hall.Hall;

import java.util.LinkedList;
import java.util.List;

public class Printer {
	private static final Printer instance = new Printer();

	private Printer() {
	}

	public static Printer getInstance(){
		return instance;
	}

	public void printHallInformation(Hall hall){
		List<Door> doors = hall.getDoors();
		printFirstLine(doors);
		System.out.print("|");
		for(Door door : doors){
			String message = readDoorInfo(door.getEntity());
			System.out.print(String.format("%s\t|", message));
		}
		System.out.println("");
	}

	private void printFirstLine(List<Door> doors) {
		System.out.print("|");
		for(int i = 0; i < doors.size(); i++){
			System.out.print(String.format("Door #%s\t\t\t|", i+1));
		}
		System.out.println("");
	}

	private String readDoorInfo(Powerable entity) {
		String message;
		if(entity instanceof Artifact){
			message = String.format("Artifact %s power", ((Artifact) entity).getPower());
		} else {
			message = String.format("Monster %s power", ((Monster) entity).getPower());
		}
		return message;
	}

	public void printNumberOfDoorsWithDeath(Hall hall, Hero hero) {
		List<Door> doors = hall.getDoors();
		int numberOfDeathDoors = 0;
		for(Door door : doors){
			if(isDeath(door, hero)){
				++numberOfDeathDoors;
			}
		}
		System.out.println(String.format("Number of doors with death: %s", numberOfDeathDoors));
	}

	private boolean isDeath(Door door, Hero hero) {
		Powerable entity = door.getEntity();
		if(entity instanceof Monster){
			if (((Monster) entity).getPower() > hero.getPower()) {
				return true;
			}
		}
		return false;
	}

	public void printWinPath(Hall hall, Hero hero) {
		List<Artifact> artifacts = fetchArtfacts(hall.getDoors());
		List<Monster> monsters = fetchMonsters(hall.getDoors());
		int winPoints = fetchWinPoints(artifacts);
		int deathPoints = fetchDeathPoints(monsters);
		if(winPoints < deathPoints){
			System.out.println("Victory is impossible.");
			System.exit(0);
		}

	}

	private int fetchDeathPoints(List<Monster> monsters) {
		int sum = 0;
		for(Monster monster : monsters){
			sum += monster.getPower();
		}
		return sum;
	}

	private int fetchWinPoints(List<Artifact> artifacts) {
		int sum = 0;
		for(Artifact artifact : artifacts){
			sum += artifact.getPower();
		}
		return sum;
	}



	private List<Artifact> fetchArtfacts(List<Door> doors) {
		List<Artifact> artifacts = new LinkedList<>();
		for(Door door : doors){
			if(door.getEntity() instanceof Artifact){
				Artifact artifact = (Artifact) door.getEntity();
				artifacts.add(artifact);
			}
		}
		return artifacts;
	}

	private List<Monster> fetchMonsters(List<Door> doors) {
		List<Monster> monsters = new LinkedList<>();
		for(Door door : doors){
			if(door.getEntity() instanceof Monster){
				Monster monster = (Monster) door.getEntity();
				monsters.add(monster);
			}
		}
		return monsters;
	}
}
