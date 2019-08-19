package com.game.hall;

import com.game.services.EntityService;

import java.util.List;

public class Hall {
	private static final int DOORS_IN_HALL = 10;
	List<Door> doors;

	public Hall() {
		this.doors = EntityService.getInstance().generateDoors();
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}

	public List<Door> getDoors() {
		return doors;
	}
}
