package com.bowlingscorecalc.score;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	private String frame;
	private int firstScore;
	private int secondScore;
	
	public Score() {
		
	}
	
	public Score(String frame) {
		super();
		this.setFrame(frame);
	}
	
	public Score(String frame, int firstScore, int secondScore) {
		super();
		this.setFrame(frame);
		this.setFirstScore(firstScore);
		this.setSecondScore(secondScore);
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public int getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(int firstScore) {
		this.firstScore = firstScore;
	}

	public int getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(int secondScore) {
		this.secondScore = secondScore;
	}
	
	

}
