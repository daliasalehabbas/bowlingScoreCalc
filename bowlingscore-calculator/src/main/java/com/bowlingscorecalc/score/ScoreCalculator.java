package com.bowlingscorecalc.score;

import java.util.*;

public class ScoreCalculator {
	int strikescore, sparescore=10;
	List<Integer> masterList = new ArrayList<>(Collections.nCopies(10, 0));
	List<Integer> lastFrameScores = new ArrayList<>(Collections.nCopies(3, 0));
	boolean[] hasStrike = new boolean[11];
	int totalScore=0;
	boolean prevSpare=false;
	int ballsThrowStrike=0;
	boolean prevStrike =false;
	
	public void setIndividualScore(Score score, int frame) {
	    score.setTotalScore(masterList.get(frame - 1));
	}
		
		public List < Integer > getMaterList(){
	    return masterList;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public void calcStrike(int frame, int first, int second) {
		if(first !=10) {
			hasStrike[frame-1]=false;
		}
		int sum=totalScore+10+first+second;
		masterList.set(frame-1,sum);
		totalScore+=10+first+second;
	}
	
	public void calcStrike2(int frame, int first, int second) {
		if(first !=10) {
			hasStrike[frame-2]=false;
		}
		//int sum=totalScore+first;
		masterList.set(frame-2,masterList.get(frame-2)+first);
		totalScore+=first;
	}
	


	public void addScore(int frame, int first, int second) {
		System.out.print("hejjjj");
		masterList.stream().forEach(x -> System.out.println(x));
		if(frame ==10) {
			if(prevStrike) {
				totalScore += first+second;
			}else {
			//System.out.print("here: " + totalScore);
			totalScore += first;
			
			}
			masterList.set(9, totalScore);
		}
		if(frame == 9) {
			if(first ==10) {
				prevStrike=true;
				masterList.add(9, totalScore+first);
				totalScore+=first;
			}else if(first+second <10) {
				masterList.add(9, totalScore+first+second);
				
				totalScore+=first+second;
			//	System.out.println("Round 9 score: " + totalScore+ " first: " + first + "Second: " + second+ " list: " + masterList.get(9));
				
			}else {
				masterList.add(9, totalScore+first+second);
				
				totalScore+=first+second;
			lastFrameScores.set(0, first);
			lastFrameScores.set(1, second);
			}
		}
		
		if(frame >=2 && hasStrike[frame-2]) {
			calcStrike2(frame, first, second);
		}
		
		if(frame >=1 &&hasStrike[frame-1]) {
			calcStrike(frame, first, second);
		}
		
		if(prevSpare) {
			int sum= totalScore+10+first;
			masterList.set(frame-1,sum);
			totalScore+=10+first;
			prevSpare=false;
		}

		if(first == 10 && frame != 9) {
			hasStrike[frame]=true;
		}else if((first+second) == 10 && frame != 9) {
			prevSpare=true;
		}else {
		int sum=first+second;
		
		if(frame < 9) {
		masterList.set(frame,totalScore+sum);
		totalScore+=sum;
		}
		}
	//	System.out.println("här");
	/*	for(int i=0; i<masterList.size(); i++) {
			System.out.println(masterList.get(i));
						}*/
		//System.out.println(frame+": " + totalScore);
		}
	
	public void displayScore() {
		for(int i=0; i<masterList.size(); i++) {
		System.out.println("Score round " + (i+1) + ": " + masterList.get(i));
		
		}
		System.out.println("totalScore: " + totalScore);
	}
}
