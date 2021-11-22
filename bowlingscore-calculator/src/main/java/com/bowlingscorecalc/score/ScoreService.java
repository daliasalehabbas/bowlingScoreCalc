package com.bowlingscorecalc.score;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

	
	
	@Autowired
	private ScoreRepository scoreRepository;
	private Calc calc = new Calc();
	
	public List<Score> getAllScores() {
		List<Score> scores = new ArrayList<>();
		scoreRepository.findAll().forEach(x -> {
			scores.add(x);
			});
		//scores.stream().forEach(x -> System.out.println("frame: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()));
		//System.out.println("Size: " + scores.size());
		if(scores.size()>0) {
		//System.out.println("hhhhh"+scores.get(scores.size()-1).getFirstScore());
		//skickar med den senaste som lades in	
		calc.addToList(scores.get(scores.size()-1));
		}
		return scores;
	}
	
	public void addScore(Score score) {
		scoreRepository.save(score);
		//scores.stream().forEach(x -> System.out.println("frame: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()));
	}
	
	public void updateScore(String oldFrame, Score score) {
		scoreRepository.save(score);
		
	}

	public List<Integer> getAllTotalscores() {
		// TODO Auto-generated method stub
		return calc.getMaterList();
	}
	}
	
	

