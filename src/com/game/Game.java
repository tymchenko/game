package com.game;

import com.game.hall.Hall;

public class Game {

	public void start() {
		createHall();
		System.out.println("Start");
	}

	private void createHall() {
		Hall hall = new Hall();
	}
}
