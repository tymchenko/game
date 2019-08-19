package com.game;

import com.game.entities.Artifact;
import com.game.entities.Hero;
import com.game.entities.Monster;
import com.game.hall.Door;
import com.game.hall.Hall;
import com.game.services.Communicator;
import com.game.services.Printer;

import java.io.IOException;

public class Game {
	Printer printer = Printer.getInstance();
	Communicator communicator = Communicator.getInstance();

	public void start() throws IOException, InterruptedException {
		Hero hero = new Hero();
		Hall hall = new Hall();
		printer.printHallInformation(hall);
		printer.printNumberOfDoorsWithDeath(hall, hero);
		boolean isAlive = true;
		while (isAlive) {
			int doorNumber = communicator.askDoorNumber();
			Door door = hall.getDoors().get(doorNumber-1);
			if (door.getEntity() instanceof Artifact) {
				Artifact artifact = (Artifact) door.getEntity();
				hero.setPower(hero.getPower() + artifact.getPower());
				System.out.println(String.format("You got artifact. %s point were added to hero.", artifact.getPower()));
				continue;
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
				continue;
			}
		}
	}
}
