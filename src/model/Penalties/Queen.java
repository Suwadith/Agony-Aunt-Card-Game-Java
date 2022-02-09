package model.Penalties;

import java.util.Stack;
import model.*;

public class Queen extends Penalty {
	
	private boolean penaltyPresent;
	
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
