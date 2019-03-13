package Gui;

import java.io.Serializable;

public class Player implements Serializable{
	private static final long serialVersionUID = -2018257411492339158L;
	private String name;
	private int score;
	
	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
