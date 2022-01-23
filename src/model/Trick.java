package model;

public class Trick {

    private Card leadCard;
    private Card[] followingCards;
    private Player winner;

    public Trick(Card leadCard, Card[] followingCards) {
        this.leadCard = leadCard;
        this.followingCards = followingCards;
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

    public void setFollowingCards(Card[] followingCards) {
        this.followingCards = followingCards;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
