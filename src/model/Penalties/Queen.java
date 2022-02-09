package model.Penalties;

import java.util.concurrent.atomic.AtomicBoolean;
import model.*;

public class Queen extends Penalty {
	
	private boolean penaltyPresent;
	
    public Queen(Card cardPenalty, CounterColor counterColor) {
    	this.penaltyPresent = checkForPenalty(cardPenalty);
    	if(penaltyPresent) {
        	//super.placeCounter(null, null, null);
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
