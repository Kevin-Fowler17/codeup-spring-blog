package com.codeup.codeupspringblog.controllers;

public class Rolls {
    private int random;
    private int guess;
    private String result;

    @Override
    public String toString() {
        return "Rolls{" +
                "random=" + random +
                ", guess=" + guess +
                ", result='" + result + '\'' +
                '}';
    }

    public Rolls(int random, int guess, String result) {
        this.random = random;
        this.guess = guess;
        this.result = result;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
