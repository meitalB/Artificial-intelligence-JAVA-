public class Matrix {
    // this class handle the matrix that save data and
    //check and get neighbors

    //members
    private int width, height;
    private char[][] arr;


    // Constructor init the matrix.
    public Matrix(int length, char[][] grid) {
        this.width = length;
        this.height = length;
        arr = new char[length][length];

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                arr[i][j] = grid[i][j];
//                arr[i][j].setParent(null);
            //    this.neighbors(arr[i][j], i, j);

            }
        }

//        for (int i = 0; i < length; ++i) {
//            for (int j = 0; j < length; ++j) {
//                this.neighbors(arr[i][j], i, j);
//
//            }
//        }
    }


    //update neighbors of the current node
    private void neighbors(Node n, int x, int y) {
        int right=0,down=0,left=0,up=0;

        if ((x < width - 1)) {//right
            if(arr[x + 1][y]=='W'){
                right=1;
            }else {
                n.addNeighbor(new Node(arr[x + 1][y]));
            }
        }
        if ((x < width-1) &&(y < height - 1)&&(right==0)) {//right down
            if (y < height - 1) {//down
                if (arr[x][y + 1]== 'W') {
                    down = 1;
                }
            }
            if (down == 0) {
                if(arr[x + 1][y + 1]!='W') {
                    n.addNeighbor(new Node(arr[x + 1][y + 1]));
                }
            }
        }
        if (y < height - 1) {//down
                if (arr[x][y + 1] == 'W') {
                    down = 1;
                }else {
                    if (arr[x][y + 1] != 'W') {
                        n.addNeighbor(new Node(arr[x][y + 1]));
                    }
                }
        }
        if((x>0)&&(y < height - 1)&&(down==0)){//down left
               if( arr[x - 1][y]=='W'){
                   left=1;
               }
            if(left==0){
                   if(arr[x-1][y + 1]!='W') {
                       n.addNeighbor(new Node(arr[x - 1][y + 1]));
                   }
               }
        }
        if (x > 0) { //left
            if (arr[x - 1][y] == 'W') {
                left = 1;
            }else{
                if(arr[x - 1][y]!='W') {
                    n.addNeighbor(new Node(arr[x - 1][y]));
                }
            }
        }
        if ((x > 0)&&(y>0)&&(left==0)) { //left up
            if (arr[x][y - 1]=='W'){
                up=1;
            }else {
                if(arr[x - 1][y-1]!='W') {
                    n.addNeighbor(new Node(arr[x - 1][y - 1]));
                }
            }
        }
        if (y > 0) { //up
            if(arr[x][y - 1]=='W'){
                up=1;
            }else {
                if(arr[x][y - 1]!='W') {
                    n.addNeighbor(new Node(arr[x][y - 1]));
                }
            }
        }
        if ((y > 0)&&(x < width - 1)&&(right==0)&&(up==0)) { //up right

           if(arr[x+1][y - 1]!='W') {
               n.addNeighbor(new Node(arr[x + 1][y - 1]));
           }
        }
    }

    //get a Node
    public char getNode(int x, int y) {
        return this.arr[x][y];
    }

//    //initialize the matrix
//    public void initDistanceForAll(int d) {
//        for (int i = 0; i < this.width; ++i) {
//            for (int j = 0; j < this.height; ++j) {
//                arr[i][j].setDistance(d);
//            }
//        }
//    }

//    //add a new obstacle
//    public void addObstacle(Node n) {
//        for (int i = 0; i < this.width; ++i) {
//            for (int j = 0; j < this.height; ++j) {
//                if (arr[i][j] == n) {
//                    arr[i][j].setIsObstacle(true);
//                    return;
//                }
//            }
//        }
//    }
//
//    //remove a new obstacle
//    public void removeObstacle(Node n) {
//        for (int i = 0; i < this.width; ++i) {
//            for (int j = 0; j < this.height; ++j) {
//                if (arr[i][j] == n) {
//                    arr[i][j].setIsObstacle(false);
//                    return;
//                }
//            }
//        }
//    }
}
