package com.game.services;

import com.game.entities.Monster;
import com.game.entities.Powerable;

import java.util.Random;


public class PowerGenerator {
	private static PowerGenerator generator = new PowerGenerator();

	private static final int MIN_ARTIFACT_POWER = 10;
	private static final int MAX_ARTIFACT_POWER = 80;
	private static final int MIN_MONSTER_POWER = 5;
	private static final int MAX_MONSTER_POWER = 100;

	private Random random = new Random();

	private PowerGenerator() {
	}

	public PowerGenerator getInstance(){
		return generator;
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
}
