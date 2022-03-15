package model;

public abstract class Penalty {

    abstract public boolean checkForPenalty(Card card);
    abstract public boolean checkForPenalty(Card card, DumpCard dumpCard);
    
    abstract public void setPenalty();
    
    public void placeCounter(char color, Integer row, Integer column, PenaltyBoard penaltyboard) {
    	penaltyboard.setCounterOnBoard(row, column, color);
    }

}
