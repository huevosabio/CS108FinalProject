package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

public class QuizTest {
	//Ramon Iglesias: I am still uncertain on how the implementation
	//Will actually be, but I'll take my best guess.
	private Quiz bunnyQuiz;
	
	@Test
	public void testBunnyQuiz() throws SQLException {
		bunnyQuiz = new Quiz(1);
		assertEquals("Animals", (String) bunnyQuiz.getCategory());
		
		assertEquals("An homage to the departed, legendary Bunny World project.", (String) bunnyQuiz.getDescription());
		
		QuestionResponse bunnyQuestion1 = (QuestionResponse)bunnyQuiz.getNextQuestion();
		assertEquals("What is the Biological Family for Bunnies?", (String) bunnyQuestion1.getStatement());
		assertTrue(bunnyQuestion1.getAnswers().contains("Leporidae"));
		
		FillInTheBlank bunnyQuestion2 = (FillInTheBlank) bunnyQuiz.getNextQuestion();
		assertEquals("The book __________ Down, about a bunch of rabbits, won the 1972 Carnegie Medal", (String) (bunnyQuestion2.getStatement()));
		assertTrue(bunnyQuestion2.getAnswers().contains("Watership"));
		
		MultipleChoice bunnyQuestion3 = (MultipleChoice) bunnyQuiz.getNextQuestion();
		assertEquals("Which of the following statements is false",bunnyQuestion3.getStatement());
		assertTrue(bunnyQuestion3.getWrongAnswers().contains("Aztec mythology includes a rabbit pantheon.") && bunnyQuestion3.getWrongAnswers().contains("In Japanese folklore, rabbits live on the moon."));
		assertTrue(bunnyQuestion3.getAnswer().contains("In Stanford tradition, rubbing the nose of a rabbit guarantees an A on the CS108 class project"));
		
		PictureResponse bunnyQuestion4 = (PictureResponse) bunnyQuiz.getNextQuestion();
		assertEquals("http://upload.wikimedia.org/wikipedia/en/b/b9/Bugs_Bunny_Pose.PNG", bunnyQuestion4.getURL());
		assertTrue(bunnyQuestion4.getAnswers().contains("Bugs Bunny"));
		
	}

}
