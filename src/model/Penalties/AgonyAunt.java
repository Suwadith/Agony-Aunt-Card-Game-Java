package model.Penalties;

import java.util.Stack;
import model.*;

public class AgonyAunt extends Penalty {
	
	private boolean penaltyPresent;

	  public AgonyAunt(Card cardPenalty, Stack<Counter> counters, PenaltyBoard penaltyboard) {
	    	this.penaltyPresent = checkForPenalty(cardPenalty);
	    	if(penaltyPresent) {
	    		String countercolor = counters.pop().getCounterColor().toString();
	    		char color = countercolor.charAt(0);
	        	//Call method to place counter
	    		super.placeCounter(color, 1, 1,penaltyboard);
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

}
