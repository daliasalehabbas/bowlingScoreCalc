package com.bowlingscorecalc.score;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calc {
	Set<Score> scores= new HashSet<>();
	ScoreCalculator sc; 
	
	public Calc() {
		sc= new ScoreCalculator();
	}
	
	public List<Integer> getMaterList(){
		return sc.getMaterList();
	}
	
	public void addToList(Score score) {
		
		if(!scores.add(score)) {
			scores.remove(score);
			scores.add(score);
			
			
			sc.addScore(Integer.parseInt(score.getFrame())-1, score.getFirstScore(), score.getSecondScore());
			System.out.println("Frame added:");
			System.out.println(Integer.parseInt(score.getFrame())-1);
			//sc.displayScore();
			sc.setIndividualScore(score,Integer.parseInt(score.getFrame()));
			//System.out.println("totalscore: "+ score.getFrame()+" :" + score.getTotalScore());
		//	scores.stream().forEach(x -> System.out.println("frameee: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()
	//		+ " totalScore: " + x.getTotalScore()));
			}
	}
	
	
	/*public void addToList(Score score) {
		
		
		if(!scores.add(score)) {
		scores.remove(score);
		scores.add(score);
		}
		
		System.out.println("size of addToList: "+ scores.size());
		scores.stream().forEach(x -> System.out.println("frameee: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()));
		for(Score s: scores) {
			sc.addScore(Integer.parseInt(s.getFrame()), s.getFirstScore(), s.getSecondScore());
			sc.setIndividualScore(s,Integer.parseInt(s.getFrame()));
			System.out.println("totaldscore "+ s.getFrame()+" " + s.getTotalScore());
			//masterList.stream().forEach(x -> System.out.println(x));
		
		}
	/*	Score a = new Score("1",2,3);
		Score b = new Score("2",4,4);
		Score c = new Score("3",1,1);
		
		addScore(Integer.parseInt(a.getFrame()), a.getFirstScore(), a.getSecondScore());
		a.setTotalScore(masterList.get(Integer.parseInt(a.getFrame())));
		addScore(Integer.parseInt(b.getFrame()), b.getFirstScore(), b.getSecondScore());
		b.setTotalScore(masterList.get(Integer.parseInt(b.getFrame())));
		addScore(Integer.parseInt(c.getFrame()), c.getFirstScore(), c.getSecondScore());
		c.setTotalScore(masterList.get(Integer.parseInt(c.getFrame())));
		
		masterList.stream().forEach(x -> System.out.println(x));
		System.out.println("Score A totalscore: "+ a.getTotalScore());
		System.out.println("Score B totalscore: "+ b.getTotalScore());
		System.out.println("Score C totalscore: "+ c.getTotalScore());
		*/

	
}
