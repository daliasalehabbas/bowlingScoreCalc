package com.bowlingscorecalc.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/scores")
	public List<Score> getAllTopics() {
		return scoreService.getAllScores();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/scores")
	public void addTopic(@RequestBody Score score) {
		scoreService.addScore(score);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/scores/{oldFrame}")
	public void updateTopic(@PathVariable String oldFrame,@RequestBody Score score) {
		scoreService.updateScore(oldFrame, score);
	}
	

}
