package cs320lab2web;

import java.io.FileNotFoundException;

public class DrivingTestMain {

    public static void main( String args[] ) throws FileNotFoundException
    {
        DrivingTest drivingTest = new DrivingTest();

        while( true )
        {
            // display the current question
            Question question = drivingTest.getCurrentQuestion();
            System.out.println( question.getDescription() );
            System.out.println( "\t" + question.getAnswerA() );
            System.out.println( "\t" + question.getAnswerB() );
            System.out.println( "\t" + question.getAnswerC() + "\n" );

            // set the answer to the current question to 1
            drivingTest.getCurrentQuestion().setAnswer( 1 );

            // if this is the last question, we are done.
            if( drivingTest.isLastQuestion() ) break;

            // it is not the last question, so increment CurrentQuestionIndex
            int currentQuestionIndex = drivingTest.getCurrentQuestionIndex();
            drivingTest.setCurrentQuestionIndex( currentQuestionIndex + 1 );
        }

        // display the test score
        System.out.println( "Your test score is: " + drivingTest.getScore() );
    }

}

