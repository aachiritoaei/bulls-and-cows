package com.ach.alex.bullsandcowsreloaded;

import java.util.Random;

/**
 * Created by achir_000 on 21-Nov-16.
 */

public class Game {

    // score
    private float score;
    private int numberOfMoves;
    private static final float bullsRatio = 0.5f;
    private static final float cowsRatio = 0.25f;

    // random number
    private String secretNumber;
    private static final int minRange = 1000;
    private static final int maxRange = 9999;

    private String playerName;

    public Game() {
        Random random = new Random();
        // initialize score
        score = 0;
        numberOfMoves = 0;
        // initialize random number
        secretNumber = Integer.toString(random.nextInt((maxRange - minRange) + 1) + minRange);
    }

    public String getHint(String guess) {

        int bulls = 0;
        int cows = 0;

        int[] numbers = new int[10];

        for (int i = 0; i < secretNumber.length(); ++i) {

            int secretFigure = Character.getNumericValue(secretNumber.charAt(i));
            int guessFigure = Character.getNumericValue(guess.charAt(i));

            if (secretFigure == guessFigure) {
                bulls++;
            } else {
                if (numbers[secretFigure] < 0) {
                    cows++;
                }

                if (numbers[guessFigure] > 0) {
                    cows++;
                }

                numbers[secretFigure]++;
                numbers[guessFigure]--;
            }
        }

        numberOfMoves++;
        score += bulls * bullsRatio  + cows * cowsRatio;

        return bulls + "B" + cows + "C";
    }

    public String getSecretNumber() {
        return this.secretNumber;
    }

    public float getFinalScore() {
        return this.score / numberOfMoves;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
