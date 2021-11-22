package com.bowlingscorecalc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bowlingscorecalc.score.ScoreCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

@SpringBootTest
class BowlingscoreCalculatorApplicationTests {

private ScoreCalculator calc;
	
	@BeforeEach
	public void setUp() throws Exception {
        calc= new ScoreCalculator();
    }
	
	@Test                                               
    @DisplayName("Test all Strikes")
	public void testStrikes() {
		calc.addScore(0, 10, 0);
		calc.addScore(1, 10, 0);
		calc.addScore(2, 10, 0);
		calc.addScore(3, 10, 0);
		calc.addScore(4, 10, 0);
		calc.addScore(5, 10, 0);
		calc.addScore(6, 10, 0);
		calc.addScore(7, 10, 0);
		calc.addScore(8, 10, 0);
		calc.addScore(9, 10, 0);
		calc.addScore(10,10, 10);
		assertEquals(calc.getTotalScore(), 300);
	}

	@Test                                               
    @DisplayName("Test random scoreinput")
	public void testRandomInput() {
		calc.addScore(0, 2, 4);
		calc.addScore(1, 5, 1);
		calc.addScore(2, 10, 0); //Strike
		calc.addScore(3, 10, 0); //Strike
		calc.addScore(4, 2, 1);
		calc.addScore(5, 7, 3); //spare
		calc.addScore(6, 6, 2);
		calc.addScore(7, 10, 0);
		calc.addScore(8, 5, 4);
		calc.addScore(9, 10, 0);
		calc.addScore(10,10, 10);
		
		assertEquals(calc.getTotalScore(), 132);
	}
	
	
	@Test                                               
    @DisplayName("Test all spares")
	public void testSpares() {
		calc.addScore(0, 9, 1);
		calc.addScore(1, 9, 1);
		calc.addScore(2, 9, 1); 
		calc.addScore(3, 9, 1); 
		calc.addScore(4, 9, 1);
		calc.addScore(5, 9, 1); 
		calc.addScore(6, 9, 1);
		calc.addScore(7, 9, 1);
		calc.addScore(8, 9, 1);
		calc.addScore(9, 9, 1);
		calc.addScore(10,9, 1);
		
		assertEquals(calc.getTotalScore(), 190);
		
	}

}
