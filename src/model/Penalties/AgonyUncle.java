package model.Penalties;

import java.util.Stack;
import model.*;

public class AgonyUncle extends Penalty {
	
	private boolean penaltyPresent;
	public String penaltyCode;
	public String counterColor;
	
	  public AgonyUncle(Card cardPenalty, DumpCard dumpCard, Stack<Counter> counters, PenaltyBoard penaltyboard) {
		  	this.penaltyPresent = checkForPenalty(cardPenalty, dumpCard);
	    	if(penaltyPresent) {
//	    		counters.pop();
	    		setPenalty();
	    		counterColor = counters.pop().getCounterColor().toString();
	    		System.out.println(counterColor);
	    		char color = counterColor.charAt(0);
//	        	//Place counter on penalty board
	    		super.placeCounter(color, 0, 0,penaltyboard);
	    	}
		}
	
	@Override
    public boolean checkForPenalty(Card card, DumpCard dumpCard) {
		boolean check = false; 	
		//Penalty check for queen in the trick
    	if(card.getSuit() == dumpCard.getSuit() && card.getRank() == dumpCard.getRank()) {
    		check = true;
    	}
    	return check;
    }

	@Override
	public boolean checkForPenalty(Card card) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setPenalty() {
		this.penaltyCode = "AU";
	}

	public String getPenaltyCode() {
		return penaltyCode;
	}
	
	public String getCounterColor() {
		return counterColor;
	}
}
