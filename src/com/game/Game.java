package com.game;

import com.game.hall.Hall;
import com.game.services.Printer;

public class Game {

	public void start() {
		Hall hall = new Hall();
		Printer.getInstance().printHallInformation(hall);
	}

}
