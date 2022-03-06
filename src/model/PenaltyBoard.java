package model;

public class PenaltyBoard {

    private PenaltySquares[][] penaltySquares = new PenaltySquares[3][3];

    public PenaltyBoard() {
        this.penaltySquares = createBoard();
    }

    public PenaltySquares[][] getPenaltyBoard() {
        return penaltySquares;
    }

    public PenaltySquares[][] createBoard() {
        penaltySquares[0][0] = new PenaltySquares("JOKER");
        penaltySquares[0][1] = new PenaltySquares("SPADES");
        penaltySquares[0][2] = new PenaltySquares("OMEGA");
        penaltySquares[1][0] = new PenaltySquares("HEARTS");
        penaltySquares[1][1] = new PenaltySquares("QUEEN");
        penaltySquares[1][2] = new PenaltySquares("DIAMONDS");
        penaltySquares[2][0] = new PenaltySquares("PLUS");
        penaltySquares[2][1] = new PenaltySquares("CLUBS");
        penaltySquares[2][2] = new PenaltySquares("HASH");

        return penaltySquares;
    }


    public void setCounterOnBoard(Integer row, Integer column, char color) {
    	String penalty = penaltySquares[row][column].getPenaltySquareName() + "(" + color +')';
    	penaltySquares[row][column].setPenaltySquareName(penalty);
    }

    public void removeCounterOnBoard(Integer row, Integer column, Counter counter) {
        String penalty = penaltySquares[row][column].getPenaltySquareName();
        String resetPenalty = penalty.substring(0, penalty.length()-3);
    }
    
    public void displayBoard() {
    	System.out.println("******************************");
    	System.out.println("PENALTY BOARD");
    	System.out.println("******************************");
    	for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.format("%-10s", penaltySquares[i][j].getPenaltySquareName());
            }
            System.out.println();
        }
        System.out.println();
    }
}
