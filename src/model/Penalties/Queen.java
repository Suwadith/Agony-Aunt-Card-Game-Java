package model.Penalties;

import java.util.Stack;
import model.*;

public class Queen extends Penalty {
	
	public boolean penaltyPresent=false;
	public String penaltyCode;
	public String counterColor;
	
	public Queen(Card cardPenalty, Stack<Counter> counters, PenaltyBoard penaltyboard) {
		this.penaltyPresent = checkForPenalty(cardPenalty);
		if(penaltyPresent) {
			
    		if(cardPenalty.getSuit() == Suit.SPADES) {
    			setPenalty();
        		counterColor = counters.pop().getCounterColor().toString();
				char color = counterColor.charAt(0);
    			penaltyCode = penaltyCode + "_SPADES";
    			//Place counter on penalty board
	    		super.placeCounter(color, 0, 1,penaltyboard);
			}
    		if(cardPenalty.getSuit() == Suit.HEARTS) {
    			setPenalty();
    			counterColor = counters.pop().getCounterColor().toString();
				char color = counterColor.charAt(0);
    			penaltyCode = penaltyCode + "_HEARTS";
    			//Place counter on penalty board
	    		super.placeCounter(color, 1, 0,penaltyboard);
    		}
    		if(cardPenalty.getSuit() == Suit.DIAMONDS) {
    			setPenalty();
    			counterColor = counters.pop().getCounterColor().toString();
				char color = counterColor.charAt(0);
    			penaltyCode = penaltyCode + "_DIAMONDS";
    			//Place counter on penalty board
	    		super.placeCounter(color, 1, 2,penaltyboard);
    		}
    		if(cardPenalty.getSuit() == Suit.CLUBS) {
    			setPenalty();
    			counterColor = counters.pop().getCounterColor().toString();
				char color = counterColor.charAt(0);
    			penaltyCode = penaltyCode + "_CLUBS";
    			//Place counter on penalty board
	    		super.placeCounter(color, 2, 1,penaltyboard);
    		}
		}
	}
	
	@Override
    public boolean checkForPenalty(Card card) {
		boolean check = false; 	
		//Penalty check for queen in the trick
    	if(card.getRank() == Rank.QUEEN) {
    		check = true;
    	}
    	return check;
    }

	@Override
	public boolean checkForPenalty(Card card, DumpCard dumpCard) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setPenalty() {
		this.penaltyCode = "QP";
	}

	public String getPenaltyCode() {
		return penaltyCode;
	}
	
	public String getCounterColor() {
		return counterColor;
	}
}
