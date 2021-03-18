package com.example.FirstSpringBoot.api.Model;

public class Awards {

    private Integer wins;
    private Integer nominations;
    private String text;

    public Integer getWins() {
        return wins;
    }

    public Integer getNominations() {
        return nominations;
    }

    public String getText() {
        return text;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void setNominations(Integer nominations) {
        this.nominations = nominations;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Awards{" +
                "wins=" + wins +
                ", nominations=" + nominations +
                ", text='" + text + '\'' +
                '}';
    }
}
