package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerDatastore {
	private Trick trickNo;
	private Player player;
	private Card[] card = new Card[4];
	
	public PlayerDatastore() {
	}

	public PlayerDatastore(Trick trickNo, Player player, Card[] card) {
		this.trickNo = trickNo;
		this.player = player;
		this.card = card;
	}

	public Trick getTrickNo() {
		return trickNo;
	}

	public void setTrickNo(Trick trickNo) {
		this.trickNo = trickNo;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Card[] getCard() {
		return card;
	}

	public void setCard(Card[] card) {
		this.card = card;
	}
	
	
  }
