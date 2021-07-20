package com.bowlingscorecalc.score;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	private String frame;
	private int firstScore;
	private int secondScore;
	private int totalScore;
	
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
		this.getTotalScore();
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
	
	public void setTotalScore(int totalScore) {
		this.totalScore=totalScore;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	 @Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		  if(obj == null || obj.getClass()!= this.getClass()) {
	            return false;
		  }
		  
		  Score score = (Score) obj;
		  return score.frame.equals(this.frame);
	}
	 
	 @Override
	 public int hashCode() {
		
		 return Integer.parseInt(this.frame);
	 }
	
	

}
