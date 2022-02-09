package model;

public abstract class Penalty {

    abstract public boolean checkForPenalty(Card card);
    abstract public boolean checkForPenalty(Card card, DumpCard dumpCard);
    
    public void placeCounter(char color, Integer row, Integer column, PenaltyBoard penaltyboard) {
    	penaltyboard.setCounterOnBoard(row, column, color);
    }

}
