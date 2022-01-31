package model;

public class Trick {

    private Player trickLeader;
    private Card leadCard;
    private Player[] followingPlayers;
    private Card[] followingCards = new Card[3];
    private Player winner;
    private Player previousTrickWinner;
    public static int trickNumber;


    public Trick() {
        trickNumber += 1;
    }

    public Card getLeadCard() {
        return leadCard;
    }

    public void setLeadCard(Card leadCard) {
        this.leadCard = leadCard;
    }

    public Card[] getFollowingCards() {
        return followingCards;
    }

    public void setFollowingCards(Card followingCards, int position) {
        this.followingCards[position] = followingCards;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getTrickLeader() {
        return trickLeader;
    }

    public void setTrickLeader(Player trickLeader) {
        this.trickLeader = trickLeader;
    }

    public Player[] getFollowingPlayers() {
        return followingPlayers;
    }

    public void setFollowingPlayers(Player[] followingPlayers) {
        this.followingPlayers = followingPlayers;
    }

    public Player getPreviousTrickWinner() {
        return previousTrickWinner;
    }

    public void setPreviousTrickWinner(Player previousTrickWinner) {
        this.previousTrickWinner = previousTrickWinner;
    }
}
