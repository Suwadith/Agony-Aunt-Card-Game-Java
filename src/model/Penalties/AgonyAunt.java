package model.Penalties;

import java.util.Stack;
import model.*;

public class AgonyAunt extends Penalty {
	
	private boolean penaltyPresent;
	public String penaltyCode;
	public String counterColor;
	
//	  public AgonyAunt(Card cardPenalty, DumpCard dumpCard, Stack<Counter> counters, PenaltyBoard penaltyboard) {
//	    	this.penaltyPresent = checkForPenalty(cardPenalty, dumpCard);
//	    	if(penaltyPresent) {
//	    		String counterColor = counters.pop().getCounterColor().toString();
//	    		char color = counterColor.charAt(0);
//	        	//Place counter on penalty board
//	    		super.placeCounter(color, 1, 1,penaltyboard);
//	    	}
//	  }
	
	public AgonyAunt(Card cardPenalty, DumpCard dumpCard, Stack<Counter> counters) {
    	this.penaltyPresent = checkForPenalty(cardPenalty, dumpCard);
    	if(penaltyPresent) {
//    		counters.pop();
    		setPenalty();
    		counterColor = counters.pop().getCounterColor().toString();
//        	//Place counter on penalty board
//    		super.placeCounter(color, 1, 1,penaltyboard);
    	}
  }

    @Override
    public boolean checkForPenalty(Card card) {
		// TODO Auto-generated method stub
		return false;
    }

	@Override
	public boolean checkForPenalty(Card card, DumpCard dumpCard) {
		boolean check = false;
		//Penalty check for queen in the trick
    	if(card.getRank() == Rank.QUEEN && card.getSuit() == dumpCard.getSuit()) {
    		check = true;
    	}
    	return check;
	}
	
	@Override
	public void setPenalty() {
		this.penaltyCode = "AG";
	}

	public String getPenaltyCode() {
		return penaltyCode;
	}

	public String getCounterColor() {
		return counterColor;
	}
}
