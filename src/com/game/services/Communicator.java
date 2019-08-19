package com.game.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Communicator {
	private static Communicator instance = new Communicator();
	private static final String INCORRECT_DATA_INPUT = "<<<\tINPUT DATA IS INCORRECT\t>>>";

	private Communicator() {

	}

	public static Communicator getInstance(){
		return instance;
	}

	public int askDoorNumber() throws IOException {
		boolean incorrectAnswer = true;
		String answer = new String();
		while (incorrectAnswer) {
			System.out.print("Please enter door number: ");
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(System.in));
			answer = reader.readLine();
			boolean isCorrect = validateLine(answer);
			if(!isCorrect){
				System.out.println(INCORRECT_DATA_INPUT);
				continue;
			}
			incorrectAnswer = false;
		}
		return Integer.parseInt(answer);
	}

	private boolean validateLine(String answer) {
		char [] answerArray = answer.toCharArray();
		if(answer.length() > 2){
			return false;
		}
		for(int i = 0; i < answerArray.length; i++){
			if(!Character.isDigit(answerArray[i])){
				return false;
			}
		}
		return true;
	}
}
