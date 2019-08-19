package com.game.services;

import com.game.entities.Artifact;
import com.game.entities.Monster;
import com.game.entities.Powerable;
import com.game.hall.Door;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EntityService {
	private static EntityService generator = new EntityService();

	private static final int NUMBER_OF_DOORS = 10;
	private static final int MIN_ARTIFACT_POWER = 10;
	private static final int MAX_ARTIFACT_POWER = 80;
	private static final int MIN_MONSTER_POWER = 5;
	private static final int MAX_MONSTER_POWER = 100;

	private Random random = new Random();

	private EntityService() {
	}

	public static EntityService getInstance(){
		return generator;
	}

	public static Powerable createEntity() {
		if(isArtifact()){
			return new Artifact();
		}
		return new Monster();
	}

	private static boolean isArtifact() {
		Random random = new Random();
		return random.nextInt() % 2 > 0;
	}

	public int generatePower(Powerable entity){
		if(entity instanceof Monster){
			return generateMonsterPower();
		}
		return generateArtifactPower();
	}

	private int generateMonsterPower() {
		return MIN_MONSTER_POWER + (int) (Math.random() * MAX_MONSTER_POWER);
	}

	private int generateArtifactPower() {
		return MIN_ARTIFACT_POWER + (int) (Math.random() * MAX_ARTIFACT_POWER);

	}

	public List<Door> generateDoors() {
		List <Door> doors = new LinkedList<>();
		for(int i = 0; i < NUMBER_OF_DOORS; i++){
			doors.add(new Door());
		}
		return doors;
	}
}
