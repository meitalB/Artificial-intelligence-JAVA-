import java.util.List;

public class minimax {
    public minimax(){}

    /** Recursive minimax at level of depth for either maximizing or minimizing player.
     Return int[3] of {score, row, col}  */
    //boolean player- true=max, false=min
    public int[] minimax(board b, int depth, boolean player, char numOfMyPlayer) {
        // Generate possible next moves in a List of int[2] of {row, col}.
        stateOfBoard s = new stateOfBoard(b,numOfMyPlayer);
        List<board> nextMoves = s.getNextBoard();

        // mySeed is maximizing; while oppSeed is minimizing
        int bestScore = (player == true) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char numofOP = (numOfMyPlayer=='B') ? 'W' : 'B';
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            bestScore = s.getHuristicVal(numofOP);

        } else {
            for (board move : nextMoves) {
                // Try this move for the current "player"
//                cells[move[0]][move[1]].content = player;
                if (player == true) {  // mySeed (computer) is maximizing player
                    currentScore = minimax(move,depth - 1, false,numofOP)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = s.getmapOfNextBoard().get(move).getx();
                        bestCol = s.getmapOfNextBoard().get(move).gety();
                    }
                } else {  // oppSeed is minimizing player
                    currentScore = minimax(move,depth - 1, true,numofOP)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = s.getmapOfNextBoard().get(move).getx();
                        bestCol = s.getmapOfNextBoard().get(move).gety();
                    }
                }
                // Undo move
//                cells[move[0]][move[1]].content = Seed.EMPTY;
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }
}
