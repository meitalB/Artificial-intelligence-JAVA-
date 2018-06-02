import java.util.*;

public class stateOfBoard {
    /************************************************************************
     a structe that hold board and neighbors and more
     *************************************************************************/
    private board b;
    private List<board> listOfNextBoard;
    private int huristicVal;
    private char numOfMyPlayer;
    private Map<board,point> mapOfNextBoard;
//constarctor
    public stateOfBoard(board b,char numOfMyPlayer){
        this.b=b;
        listOfNextBoard=new ArrayList<board>() ;
        this.numOfMyPlayer=numOfMyPlayer;
        mapOfNextBoard=new HashMap<>();
    }

    //get the next boards
    public List<board> getNextBoard() {
        for (int myX = 0; myX < 5; myX++) {
            for (int myY = 0; myY < 5; myY++) {
                board temp = b.chooseSquare(this.b, myX, myY, numOfMyPlayer);
                if (temp != null) {
                    listOfNextBoard.add(temp);
                    mapOfNextBoard.put(temp, new point(myX, myY));
                }
            }
        }

        int sizeOfList = listOfNextBoard.size();
        if (sizeOfList > 0) {
            for (int i = 0; i < sizeOfList; i++) {
                System.out.println("my board" + i);
                listOfNextBoard.get(i).printBoard();
            }
        }
        return listOfNextBoard;
    }
    //get map Of Next Board
    public Map<board,point> getmapOfNextBoard(){

        return mapOfNextBoard;
    }
    //get board
    public board getBoard (){
        return this.b;
    }
    //get huristical value

    public int getHuristicVal(char numOfMyPlayer){

        return this.b.getHuristicVal(this.b,numOfMyPlayer);
    }
}
