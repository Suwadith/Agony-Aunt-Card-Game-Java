package model.Penalties;

import java.util.Stack;
import model.*;

public class AgonyAunt extends Penalty {
	
	public boolean penaltyPresent=false;
	public String penaltyCode;
	public String counterColor;

	public AgonyAunt(Card cardPenalty, DumpCard dumpCard, Stack<Counter> counters, PenaltyBoard penaltyboard) {
    	this.penaltyPresent = checkForPenalty(cardPenalty, dumpCard);
    	if(penaltyPresent) {
    		setPenalty();
    		if(!counters.isEmpty()) {
    		counterColor = counters.pop().getCounterColor().toString();
			char color = counterColor.charAt(0);
        	//Place counter on penalty board
    		super.placeCounter(color, 1, 1,penaltyboard); }
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
