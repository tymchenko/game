package com.game.hall;

import com.game.entities.Powerable;
import com.game.services.EntityService;

public class Door {
	private Powerable entity;

	public Door() {
		this.entity = createdEntity();
	}

	private Powerable createdEntity() {
		return EntityService.createEntity();
	}

	public Powerable getEntity() {
		return entity;
	}
}
