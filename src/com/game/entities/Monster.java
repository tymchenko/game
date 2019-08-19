package com.game.entities;

import com.game.services.EntityService;

public class Monster extends Entity implements Powerable{

	public Monster() {
		generatePower();
	}

	@Override
	public void generatePower() {
		setPower(EntityService.getInstance().generatePower(this));
	}
}
