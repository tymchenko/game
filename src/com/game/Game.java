package com.game;

import com.game.entities.Artifact;
import com.game.entities.Hero;
import com.game.entities.Monster;
import com.game.hall.Door;
import com.game.hall.Hall;
import com.game.hall.Status;
import com.game.services.Communicator;
import com.game.services.Printer;

import java.io.IOException;
import java.util.List;

public class Game {
	Printer printer = Printer.getInstance();
	Communicator communicator = Communicator.getInstance();

	public void start() throws IOException, InterruptedException {
		Hero hero = new Hero();
		Hall hall = new Hall();
		printer.printHallInformation(hall);
		printer.printNumberOfDoorsWithDeath(hall, hero);
		printer.printWinPath(hall, hero);
		boolean isAlive = true;
		boolean roomsAvailable = true;
		while (roomsAvailable && isAlive) {
			int doorNumber = communicator.askDoorNumber();
			Door door = hall.getDoors().get(doorNumber-1);
			if(Status.VISISTED.equals(door.getStatus())){
				System.out.println("This room is empty");
				continue;
			}
			else{
				door.setStatus(Status.VISISTED);
			}
			if (door.getEntity() instanceof Artifact) {
				Artifact artifact = (Artifact) door.getEntity();
				hero.setPower(hero.getPower() + artifact.getPower());
				System.out.println(String.format("You got artifact. %s point were added to hero. Hero has %s power",
									artifact.getPower(),
									hero.getPower()));
			}
			if (door.getEntity() instanceof Monster) {
				Monster monster = (Monster) door.getEntity();
				System.out.println(String.format("You met monster with %s power", monster.getPower()));
				Thread.sleep(1000);
				int fightResult = hero.getPower() - monster.getPower();
				if(fightResult < 0){
					System.out.println("<<<\tYOU DEAD\t>>>");
					isAlive = false;
					continue;
				}
				System.out.println("<<<\tYOU WIN\t>>>");
				System.out.println(String.format("You have ", hero.getPower()));
			}
			roomsAvailable = isAvailableRooms(hall.getDoors());

		}
	}

	private boolean isAvailableRooms(List<Door> doors) {
		for(Door door : doors){
			if(Status.NEW.equals(door.getStatus())){
				return true;
			}
		}
		System.out.println("<<<< YOU WIN >>>>>");
		System.out.println("Game finished");
		return false;
	}
}
