package model.Penalties;

import java.util.Stack;
import model.*;

public class Queen extends Penalty {
	
	private boolean penaltyPresent;
	
	public Queen(Card cardPenalty, Stack<Counter> counters, PenaltyBoard penaltyboard) {
		this.penaltyPresent = checkForPenalty(cardPenalty);
		if(penaltyPresent) {
    		String counterColor = counters.pop().getCounterColor().toString();
    		char color = counterColor.charAt(0);
			
    		if(cardPenalty.getSuit() == Suit.SPADES) {
    			//Place counter on penalty board
	    		super.placeCounter(color, 0, 1,penaltyboard);
			}
    		if(cardPenalty.getSuit() == Suit.HEARTS) {
    			//Place counter on penalty board
	    		super.placeCounter(color, 1, 0,penaltyboard);
    		}
    		if(cardPenalty.getSuit() == Suit.DIAMONDS) {
    			//Place counter on penalty board
	    		super.placeCounter(color, 1, 2,penaltyboard);
    		}
    		if(cardPenalty.getSuit() == Suit.CLUBS) {
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
}
