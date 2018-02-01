package com.example.android.scorekeeper;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int foulTeamA = 0;
    int foulTeamB = 0;

    TextView scoreViewA;
    TextView scoreViewB;
    TextView foulViewA;
    TextView foulViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreViewA = findViewById(R.id.team_a_score);
        scoreViewB = findViewById(R.id.team_b_score);
        foulViewA = findViewById(R.id.team_a_foul);
        foulViewB = findViewById(R.id.team_b_foul);

        //retrieve values that are saved
        if (savedInstanceState!=null) {
            scoreTeamA = savedInstanceState.getInt("scoreA",0);
            scoreTeamB = savedInstanceState.getInt("scoreB",0);
            foulTeamA = savedInstanceState.getInt("foulA",0);
            foulTeamB = savedInstanceState.getInt("foulB",0);
            displayScoreForTeamA();
            displayScoreForTeamB();
            if (foulTeamA > 0) {
                displayFoulForTeamA("# of fouls: " + foulTeamA );
            }
            if (foulTeamB > 0) {
                displayFoulForTeamB("# of fouls: " + foulTeamB);
            }
        }
    }

    //Saving the variables when the screen is rotated
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("scoreA",scoreTeamA);
        outState.putInt("scoreB",scoreTeamB);
        outState.putInt("foulA",foulTeamA);
        outState.putInt("foulB",foulTeamB);
        super.onSaveInstanceState(outState);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayScoreForTeamA() {
        scoreViewA.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Displays the given foul for Team A.
     */
    public void displayFoulForTeamA(String foulMessage) {
        foulViewA.setText(foulMessage);
    }

    /**
     * Adds points for Team A.
     */
    public void addPointForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayScoreForTeamA();
    }

    /**
     * Adds fouls for Team A.
     */
    public void addFoulForTeamA(View v) {
        foulTeamA = foulTeamA + 1;
        String foulMessage = "# of fouls: " + foulTeamA;
        displayFoulForTeamA(foulMessage);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayScoreForTeamB() {
        scoreViewB.setText(String.valueOf(scoreTeamB));
    }

    /**
     * Displays the given foul for Team B.
     */
    public void displayFoulForTeamB(String foulMessage) {
        foulViewB.setText(foulMessage);
    }

    /**
     * Adds points for Team B.
     */
    public void addPointForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayScoreForTeamB();
    }

    /**
     * Adds fouls for Team B.
     */
    public void addFoulForTeamB(View v) {
        foulTeamB = foulTeamB + 1;
        String foulMessage = "# of fouls: " + foulTeamB;
        displayFoulForTeamB(foulMessage);
    }

    /*
    ** It resets the score and fouls
     */

    public void reset(View v) {
        scoreTeamB = 0;
        scoreTeamA = 0;
        foulTeamB = 0;
        foulTeamA = 0;
        displayScoreForTeamA();
        displayScoreForTeamB();
        displayFoulForTeamA("");
        displayFoulForTeamB("");
    }

    /*
    ** Creates the Pop up message to show the winner
     */
    public void showPopUp(String popUpMessage) {
        /*
        ** builder is helper class for adding features like message, title, button functionalities - what happens when you press on Ok/Cancel
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setMessage(popUpMessage);
            /*
            ** when you call create method on builder it creates an alert dialog.
            ** when you call show method on builder, it shows the dialog on the screen.
             */
        builder.create().show();
    }

    /*
    ** Displays winner
     */
    public void showWinner(View v) {
        if (scoreTeamA > scoreTeamB) {
            showPopUp("Team A won");
        } else if (scoreTeamA < scoreTeamB) {
            showPopUp("Team B won");
        } else {
            showPopUp("It's a draw");
        }
    }

}
