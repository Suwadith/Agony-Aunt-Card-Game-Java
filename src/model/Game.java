package model;

public class Game {

    private Player[] players;
    private DumpCard dumpCard;
    private Trick[] tricks = new Trick[13];
    private Player winner;
    private int roundNumber;
    public static boolean newGame=true;

    public Game(Player[] players, DumpCard dumpCard) {
        this.players = players;
        this.dumpCard = dumpCard;
        roundNumber += 1;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public DumpCard getDumpCard() {
        return dumpCard;
    }

    public void setDumpCard(DumpCard dumpCard) {
        this.dumpCard = dumpCard;
    }

    public Trick[] getTricks() {
        return tricks;
    }

    public void setTricks(Trick[] tricks) {
        this.tricks = tricks;
    }
    
    public void setTrick(int trickNumber, Trick currentTrick) {
    	this.tricks[trickNumber] = currentTrick;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public static boolean getNewGame() {
		return newGame;
	}

	public static void setNewGame(boolean newGame) {
		Game.newGame = newGame;
	}
	
}
