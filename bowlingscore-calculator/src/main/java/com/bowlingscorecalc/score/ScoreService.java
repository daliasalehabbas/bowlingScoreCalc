package com.bowlingscorecalc.score;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
	List<Score> scores = new ArrayList<>();
	
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	
	public List<Score> getAllScores() {
		List<Score> scores = new ArrayList<>();
		//scores.add(new Score("1"));
		
		scoreRepository.findAll().forEach(x -> scores.add(x));
		scores.stream().forEach(x -> System.out.println("frame: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()));
		System.out.println("Size: " + scores.size());
		return scores;
	}
	
	public void addScore(Score score) {
		scoreRepository.save(score);
		scores.stream().forEach(x -> System.out.println("frame: "+ x.getFrame()+" firstscore: " + x.getFirstScore() +" secondScore: " + x.getSecondScore()));
	}
	
	public void updateScore(String oldFrame, Score score) {
		scoreRepository.save(score);
		
	}
	}
	
	

