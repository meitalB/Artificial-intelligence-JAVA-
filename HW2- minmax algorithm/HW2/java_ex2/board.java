public class board {
    /************************************************************************
     name class: board
     the class hold an object call board and has some members
     *************************************************************************/
    private char[] myBoard;
    private int size;

    /************************************************************************
     constractor for board that getting grid
     *************************************************************************/
    public board(char grid[][]) {
        myBoard = new char[25];
        int k = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                k++;
                myBoard[k] = grid[i][j];
            }
        }
        size = 5;
    }
    /************************************************************************
     copy constractor for board that getting board
     *************************************************************************/
    public board(board b) {
        this.myBoard = new char[25];
        for (int i = 0; i < 25; i++) {

                this.myBoard[i] = b.myBoard[i];

        }
        this.size = b.size;
    }

    /************************************************************************
     get board
     *************************************************************************/
    public char[] getMyBoard() {
        return this.myBoard;
    }

    /************************************************************************
     name function: checkEmptySquare
     The Input:str from input
     The output : if the square empty1 otherwise0
     The function operation::check if the square empty
     *************************************************************************/

    int checkEmptySquare(int x, int y) {//1 empty 0 full

        if (myBoard[x + y * size] == 'E') {
            return 1;
        }
        return 0;
    }

    /************************************************************************
     name function: cheakCloseTosombodyNotEmpty
     The Input:int x int y - point
     The output : 1 is good, close to somebody , otherwise 0
     The function operation::check if the neighbors are empty
     *************************************************************************/

    int cheakCloseTosombodyNotEmpty(int x, int y) {//1 is good, o is only empty around
        //right
        if (x < 4) {
            if ((x + y * size + 1 >= 0) && (x + y * size + 1 <= (size * size - 1))) {
                if (myBoard[x + y * size + 1] != 'E') {
                    return 1;
                }
            }
        }
        //left
        if (x > 0) {
            if ((x + y * size - 1 >= 0) && (x + y * size - 1 <= (size * size - 1))) {
                if (myBoard[x + y * size - 1] != 'E') {
                    return 1;
                }
            }
        }
        //up
        if (y > 0) {
            if ((x + y * size - size >= 0) && (x + y * size - size <= (size * size - 1))) {
                if (myBoard[x + y * size - size] != 'E') {
                    return 1;
                }
            }
        }
        //down
        if (y < 4) {
            if ((x + y * size + size >= 0) && (x + y * size + size <= (size * size - 1))) {
                if (myBoard[x + y * size + size] != 'E') {
                    return 1;
                }
            }

        }
        //up-right
        if ((y > 0) && (x < 4)) {
            if (((x + y * size - (size - 1)) % size != 0) && (x + y * size - (size - 1) >= 0)
                    && (x + y * size - (size - 1) <= (size * size - 1))) {
                if (myBoard[x + y * size - (size - 1)] != 'E') {
                    return 1;
                }
            }
        }
        //down left
        if ((x > 0) && (y < 4)) {
            if (((x + y * size + (size - 1)) % size != size - 1) && (x + y * size + (size - 1) >= 0) && (x + y * size + (size - 1) <= (size * size - 1))) {
                if (myBoard[x + y * size + (size - 1)] != 'E') {
                    return 1;
                }
            }
        }
        //down right
        if ((x < 4) && (y < 4)) {
            if (((x + y * size + (size + 1)) % size != 0) && (x + y * size + (size + 1) >= 0) && (x + y * size + (size + 1) <= (size * size - 1))) {
                if (myBoard[x + y * size + (size + 1)] != 'E') {
                    return 1;
                }
            }
        }
        //up left
        if ((x > 0) && (y > 0)) {
            if (((x + y * size - (size + 1)) % size != 0) && (x + y * size - (size + 1) >= 0)
                    && (x + y * size - (size + 1) <= (size * size - 1))) {
                if (myBoard[x + y * size - (size + 1)] != 'E') {
                    return 1;
                }
            }

        }
        return 0;
    }


    /************************************************************************
     name function: check_right
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_right(int x, int y, char numOfcurrentPlayer) {
        int i;
        if ((x + y * size + 1 >= 0) && (x + y * size + 1 <= (size * size - 1))) {
            if ((myBoard[x + y * size + 1] != numOfcurrentPlayer)
                    && (myBoard[x + y * size + 1] != 'E')) {
                for (i = x + y * size + 1; i <= y * size + (size - 1); i++) {
                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_left
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_left(int x, int y, char numOfcurrentPlayer) {
        int i;
        if ((x + y * size - 1 >= 0) && (x + y * size - 1 <= (size * size - 1))) {
            if ((myBoard[x + y * size - 1] != numOfcurrentPlayer)
                    && (myBoard[x + y * size - 1] != 'E')) {
                for (i = x + y * size - 1; i >= y * size; i--) {

                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_down
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_down(int x, int y, char numOfcurrentPlayer) {
        int i;
        if ((x + y * size + size >= 0) && (x + y * size + size <= (size * size - 1))) {
            if ((myBoard[x + y * size + size] != numOfcurrentPlayer)
                    && (myBoard[x + y * size + size] != 'E')) {
                for (i = x + y * size + size; i <= (size * size - 1); i = i + size) {
                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_up
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_up(int x, int y, char numOfcurrentPlayer) {
        int i;
        if ((x + y * size - size >= 0) && (x + y * size - size <= (size * size - 1))) {
            if ((myBoard[x + y * size - size] != numOfcurrentPlayer)
                    && (myBoard[x + y * size - size] != 'E')) {
                for (i = x + y * size - size; i >= 0; i = i - size) {
                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_up_right
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_up_right(int x, int y, char numOfcurrentPlayer) {
        int i;
        if (((x + y * size - (size - 1)) % size != 0) && (x + y * size - (size - 1) >= 0)
                && (x + y * size - (size - 1) <= (size * size - 1))) {
            if ((myBoard[x + y * size - (size - 1)] != numOfcurrentPlayer)
                    && (myBoard[x + y * size - (size - 1)] != 'E')) {
                for (i = x + y * size - (size - 1); i >= 0; i = i -( size-1)) {

                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                    if ((i - (size - 1)) % size == 0) {
                        break;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_down_left
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_down_left(int x, int y, char numOfcurrentPlayer) {
        int i;
        if (((x + y * size + (size - 1)) % size != size - 1) && (x + y * size + (size - 1) >= 0) && (x + y * size + (size - 1) <= (size * size - 1))) {
            if ((myBoard[x + y * size + (size - 1)] != numOfcurrentPlayer)
                    && (myBoard[x + y * size + (size - 1)] != 'E')) {

                for (i = x + y * size + (size - 1); i <= (size * size - 1); i = i + (size - 1)) {

                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                    if ((i + (size - 1)) % size == size - 1) {
                        break;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_down_right
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_down_right(int x, int y, char numOfcurrentPlayer) {
        int i;
        if (((x + y * size + (size + 1)) % size != 0) && (x + y * size + (size + 1) >= 0) && (x + y * size + (size + 1) <= (size * size - 1))) {
            if ((myBoard[x + y * size + (size + 1)] != numOfcurrentPlayer)
                    && (myBoard[x + y * size + (size + 1)] != 'E')) {

                for (i = x + y * size + (size + 1); i <= (size * size - 1); i = i + (size + 1)) {

                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                    if ((i + (size + 1)) % size == 0) {
                        break;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: check_up_left
     The Input:point x,y, the num of the current player
     The output : return 1  If there are coins that can be turned otherwise 0
     The function operation:calculate if there is coins we can turned
     *************************************************************************/
    int check_up_left(int x, int y, char numOfcurrentPlayer) {
        int i;
        if(x==0){
            return 0;
        }
        if (((x + y * size - (size + 1)) % size != 0) && (x + y * size - (size + 1) >= 0)
                && (x + y * size - (size + 1) <= (size * size - 1))) {
            if ((myBoard[x + y * size - (size + 1)] != numOfcurrentPlayer)
                    && (myBoard[x + y * size - (size + 1)] != 'E')) {

                for (i = x + y * size - (size + 1); i >= 0; i = i - (size + 1)) {

                    if (myBoard[i] == 'E') {
                        break;
                    }
                    if (myBoard[i] == numOfcurrentPlayer) {
                        return 1;
                    }
                    if ((i - (size + 1)) % size == (size - 1)) {
                        break;
                    }
                }
            }
        }
        return 0;
    }

    /************************************************************************
     name function: checkSquareChangeSomething
     The Input:x,y, the num of the current player
     The output :1 if there is coins to turned
     The function operation:check if there reason to continue
     *************************************************************************/
    int checkSquareChangeSomething(int x, int y, char currentPlayer) {//1 good 0 not found
        if ((check_right(x, y, currentPlayer) == 1) ||
                (check_left(x, y, currentPlayer) == 1) ||
                (check_down(x, y, currentPlayer) == 1)
                || (check_up(x, y, currentPlayer) == 1) ||
                (check_up_right(x, y, currentPlayer) == 1)
                || (check_up_left(x, y, currentPlayer) == 1)
                || (check_down_left(x, y, currentPlayer) == 1) ||
                (check_down_right(x, y, currentPlayer) == 1)) {
            return 1;
        }
        return 0;
    }

    /************************************************************************
     name function: printBoard
     The Input:no input
     The output : no output
     The function operation:print the board to screen
     *************************************************************************/
    void printBoard() {
        System.out.println("The board is:\n");
        int i;
        for (i = 0; i < size * size; i++) {
            if (i % size == 0) {
                System.out.print("\n");
            }
            System.out.print(myBoard[i]);

        }
        System.out.println("\n");
        System.out.println("\n");
    }

    /************************************************************************
     name function: updateMyBoard
     The Input:point x,y, the num of the current player
     The output : no output
     The function operation:update board due to before checks coins
     *************************************************************************/
    void updateMyBoard(int x, int y, char numOfcurrentPlayer) {
        int i;
        myBoard[x + y * size] = numOfcurrentPlayer;
        //check right
        if (check_right(x, y, numOfcurrentPlayer) == 1) {
            if ((x + y * size + 1 >= 0) && (x + y * size + 1 <= (size * size - 1))) {
                if ((myBoard[x + y * size + 1] != numOfcurrentPlayer)
                        && (myBoard[x + y * size + 1] != 'E')) {
                    for (i = x + y * size + 1; i <= y * size + (size - 1); i++) {
                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                    }
                }
            }
        }
        //check left
        if (check_left(x, y, numOfcurrentPlayer) == 1) {
            //int i;
            if ((x + y * size - 1 >= 0) && (x + y * size - 1 <= size * size - 1)) {
                if ((myBoard[x + y * size - 1] != numOfcurrentPlayer)
                        && (myBoard[x + y * size - 1] != 'E')) {
                    for (i = x + y * size - 1; i >= y * size; i--) {
                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                    }
                }
            }
        }
        //check down
        if (check_down(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if ((x + y * size + size >= 0) && (x + y * size + size <= (size * size - 1))) {
                if ((myBoard[x + y * size + size] != numOfcurrentPlayer)
                        && (myBoard[x + y * size + size] != 'E')) {
                    for (i = x + y * size + size; i <= size * size - 1; i = i + size) {
                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                    }
                }
            }
        }
        //check up
        if (check_up(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if ((x + y * size - size >= 0) && (x + y * size - size <= size * size - 1)) {
                if ((myBoard[x + y * size - size] != numOfcurrentPlayer)
                        && (myBoard[x + y * size - size] != 'E')) {
                    for (i = x + y * size - size; i >= 0; i = i - size) {
                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                    }
                }
            }
        }
        //check up-right
        if (check_up_right(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if (((x + y * size - (size - 1) % size != 0) && (x + y * size - (size - 1) >= 0)
                    && (x + y * size - (size - 1) <= (size * size - 1)))) {
                if ((myBoard[x + y * size - (size - 1)] != numOfcurrentPlayer)
                        && (myBoard[x + y * size - (size - 1)] != 'E')) {
                    for (i = x + y * size - (size - 1); i >= 0; i = i - (size - 1)) {

                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                        if ((i - (size - 1)) % size == 0) {
                            break;
                        }
                    }
                }
            }
        }
        //check down-left
        if (check_down_left(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if (((x + y * size + (size - 1)) % size != size - 1) && (x + y * size + size - 1 >= 0)
                    && (x + y * size + size - 1 <= size * size - 1)) {
                if ((myBoard[x + y * size + size - 1] != numOfcurrentPlayer)
                        && (myBoard[x + y * size + size - 1] != 'E')) {

                    for (i = x + y * size + size - 1; i <= size * size - 1; i = i + size - 1) {

                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                        if ((i + size - 1) % size == size - 1) {
                            break;
                        }
                    }
                }
            }
        }
        //check down-right
        if (check_down_right(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if (((x + y * size + (size + 1)) % size != 0) && (x + y * size + size + 1 >= 0)
                    && (x + y * size + size + 1 <= size * size - 1)) {
                if ((myBoard[x + y * size + size + 1] != numOfcurrentPlayer)
                        && (myBoard[x + y * size + size + 1] != 'E')) {

                    for (i = x + y * size + size + 1; i <= size * size - 1; i = i + size + 1) {

                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                        if ((i + size + 1) % size == 0) {
                            break;
                        }
                    }
                }
            }
        }
        //check up-left
        if (check_up_left(x, y, numOfcurrentPlayer) == 1) {
            // int i;
            if (((x + y * size - (size + 1)) % size != 0) && (x + y * size - (size + 1) >= 0)
                    && (x + y * size - (size + 1) <= size * size - 1)) {
                if ((myBoard[x + y * size - (size + 1)] != numOfcurrentPlayer)
                        && (myBoard[x + y * size - (size + 1)] != 'E')) {

                    for (i = x + y * size - (size + 1); i >= 0; i = i - (size + 1)) {

                        if (myBoard[i] == 'E') {
                            break;
                        }
                        if (myBoard[i] == numOfcurrentPlayer) {
                            break;
                        }
                        if (myBoard[i] != numOfcurrentPlayer) {
                            myBoard[i] = numOfcurrentPlayer;
                        }
                        if ((i - (size + 1)) % size == size - 1) {
                            break;
                        }
                    }
                }
            }
        }

    }

    /************************************************************************
     name function: chooseSquare
     The Input:no input
     The output : no
     The function operation::check the choose Square
     *************************************************************************/
     board chooseSquare(board b, int x, int y, char numOfMyPlayer) {

        if ((checkEmptySquare(x, y) == 1) && (cheakCloseTosombodyNotEmpty(x, y) == 1)) {
            //(checkSquareChangeSomething(x, y, numOfMyPlayer) == 1) ) {
            board newBoard = new board(b);
            newBoard.updateMyBoard(x, y, numOfMyPlayer);
            return newBoard;
        }
        return null;

    }
    /************************************************************************
     name function: get huristical
     the calculate is as the targil say
     *************************************************************************/
    int getHuristicVal(board b,char numOfMyPlayer){
         int counterBlack=0;
         int counterWhite=0;
         int counterBlackOnSide=0;
         int counterWhiteOnSide=0;
         for (int i=0; i<25; i++){
             if(b.myBoard[i]=='B'){
                 counterBlack++;
                 if((i<6)||(i==9)||(i==10)||(i==14)||(i==15)||(i==19)||(i==20)||(i==24)){
                     counterBlackOnSide++;
                 }
             }else if(b.myBoard[i]=='W'){
                 counterWhite++;
                 if((i<6)||(i==9)||(i==10)||(i==14)||(i==15)||(i==19)||(i==20)||(i==24)){
                     counterWhiteOnSide++;
                 }
             }

         }
         if (numOfMyPlayer=='B'){
             if(checkGameOver()==1){
                 if((counterWhite>counterBlack)){//black lose
                     return -1000000000;
                 }
                 else if ((counterBlack>counterWhite)){//black win
                     return 1000000000;
                 }
             }

             else{
                // return (counterBlack-counterWhite)+(counterBlackOnSide-counterWhiteOnSide);
                 return (counterBlack-counterWhite)+(counterBlackOnSide-counterWhiteOnSide);

             }

         }else {//numOfMyPlayer=='W'
             if(checkGameOver()==1){

                 if((counterWhite>counterBlack)&&(counterWhite>=13)){//white lose
                     return 1000000000;
                 }
                 else if ((counterBlack>counterWhite)&&(counterBlack>=13)){//white win
                     return -1000000000;
                 }
             }

             else{
           //      return (counterWhite-counterBlack)+(counterWhiteOnSide-counterBlack);
                 return (counterWhite-counterBlack)+(counterWhiteOnSide-counterBlackOnSide);

             }
         }
         return -9;

    }
    /************************************************************************
     name function: checkGameOver
     The output : 0 not-game over, 1 yes game over
     The function operation::check if the game over
     *************************************************************************/
    public int checkGameOver(){//0 not-game over, 1 yes game over
        for (int i = 0; i < 25; i++) {
            if(this.myBoard[i] =='E'){
                return 0;
            }
        }
        return 1;

    }
    /************************************************************************
     name function: WhoIsWin
     The output : return b if b won , w if w won
     *************************************************************************/
    public char WhoIsWin()
    {   int counterW=0;
        int counterB=0;
        for (int i = 0; i < 25; i++) {
            if(this.myBoard[i] =='B'){
                counterB++;
            }else {counterW++;}
        }
        if(counterB>counterW){
            return 'B';
        }else
            return 'W';
    }

}

