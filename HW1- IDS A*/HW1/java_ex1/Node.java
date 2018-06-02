import java.util.*;

public class Node {
    // this class handle the Node that help for
    //NodeMatrix and more parameters for the list node

    //members
    private Node parent;
    protected int distance;
    private List<Node> neighbors;
    protected char content;
    protected Point point;
    protected boolean visited;
    protected int heuristicVal;
    protected int myCost;
    protected int placeInCircle;

    //constructor uses int x and y
    public Node() {
        this.neighbors = new ArrayList<Node>();
        this.visited = false;
        this.heuristicVal = 0;
        this.myCost = 0;
        this.placeInCircle = -1;

    }

    //constructor uses int x and y
    public Node(char content) {
        this.neighbors = new ArrayList<Node>();
        this.visited = false;
        this.heuristicVal = 0;
        this.myCost = 0;
        this.placeInCircle = -1;
        this.content = content;

    }
    //constructor uses int x and y
    public Node(char content, int x, int y) {
        this.neighbors = new ArrayList<Node>();
        this.visited = false;
        this.heuristicVal = 0;
        this.myCost = 0;
        this.placeInCircle = -1;
        this.content = content;
        this.point = new Point(x, y);

    }

    //get the content of the current node
    public char getContent() {
        return this.content;
    }

    //get the parent of the current node
    public Node getParent() {
        return this.parent;
    }

    //set the parent of the current node
    public void setParent(Node p) {
        this.parent = p;
    }

    //get the distance of the current node
    public int getDistance() {
        return this.distance;
    }

    //get the Heuristic Val of the current node
    public int getHeuristicVal() {
        return this.heuristicVal;
    }

    //get the cost of the current node
    public int getMyCost() {
        return this.myCost;
    }

    //set the cost of the current node
    public void setMyCost(int myCost) {
        this.myCost = myCost;
    }

    //set the distance of the current node
    public void setDistance(int d) {
        this.distance = d;
    }

    //set the Place In Circle (order Neighbors) of the current node
    public void setPlaceInCircle(int n) {
        this.placeInCircle = n;
    }

    //get the Place In Circle (order Neighbors) of the current node
    public int getPlaceInCircle() {
        return this.placeInCircle;
    }

    //add a neighbor to the current node
    public void addNeighbor(Node n) {
        this.neighbors.add(n);
    }

    //get the neighbors of the current node
    public List<Node> getNeighbors(int size, char arr[][]) {
        int width = size;
        int height = size;
        int x = this.point.getx();
        int y = this.point.gety();
        //return this.neighbors;
        int right = 0, down = 0, left = 0, up = 0;

        if ((x < width - 1)) {//right
            if (arr[x + 1][y] == 'W') {
                right = 1;
            } else {
                this.addNeighbor(new Node(arr[x + 1][y],x+1,y));
            }
        }
        if ((x < width - 1) && (y < height - 1) && (right == 0)) {//right down
            if (y < height - 1) {//down
                if (arr[x][y + 1] == 'W') {
                    down = 1;
                }
            }
            if (down == 0) {
                if (arr[x + 1][y + 1] != 'W') {
                    this.addNeighbor(new Node(arr[x + 1][y + 1],x+1,y+1));
                }
            }
        }
        if (y < height - 1) {//down
            if (arr[x][y + 1] == 'W') {
                down = 1;
            } else {
                if (arr[x][y + 1] != 'W') {
                    this.addNeighbor(new Node(arr[x][y + 1],x,y+1));
                }
            }
        }
        if ((x > 0) && (y < height - 1) && (down == 0)) {//down left
            if (arr[x - 1][y] == 'W') {
                left = 1;
            }
            if (left == 0) {
                if (arr[x - 1][y + 1] != 'W') {
                    this.addNeighbor(new Node(arr[x - 1][y + 1],x-1,y+1));
                }
            }
        }
        if (x > 0) { //left
            if (arr[x - 1][y] == 'W') {
                left = 1;
            } else {
                if (arr[x - 1][y] != 'W') {
                    this.addNeighbor(new Node(arr[x - 1][y],x-1,y));
                }
            }
        }
        if ((x > 0) && (y > 0) && (left == 0)) { //left up
            if (arr[x][y - 1] == 'W') {
                up = 1;
            } else {
                if (arr[x - 1][y - 1] != 'W') {
                    this.addNeighbor(new Node(arr[x - 1][y - 1],x-1,y-1));
                }
            }
        }
        if (y > 0) { //up
            if (arr[x][y - 1] == 'W') {
                up = 1;
            } else {
                if (arr[x][y - 1] != 'W') {
                    this.addNeighbor(new Node(arr[x][y - 1],x,y-1));
                }
            }
        }
        if ((y > 0) && (x < width - 1) && (right == 0) && (up == 0)) { //up right

            if (arr[x + 1][y - 1] != 'W') {
                this.addNeighbor(new Node(arr[x + 1][y - 1],x+1,y-1));
            }
        }
        return this.neighbors;
    }


}
