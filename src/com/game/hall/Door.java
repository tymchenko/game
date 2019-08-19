package com.game.hall;

import com.game.entities.Powerable;
import com.game.services.EntityService;

public class Door {
	private Powerable entity;
	private Status status;

	public Door() {
		this.entity = createdEntity();
		status = Status.NEW;
	}

	private Powerable createdEntity() {
		return EntityService.createEntity();
	}

	public Powerable getEntity() {
		return entity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
