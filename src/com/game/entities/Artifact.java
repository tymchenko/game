package com.game.entities;

import com.game.services.EntityService;

public class Artifact extends Entity implements Powerable{

	public Artifact() {
		generatePower();
	}

	@Override
	public void generatePower() {
		setPower(EntityService.getInstance().generatePower(this));
	}
}
