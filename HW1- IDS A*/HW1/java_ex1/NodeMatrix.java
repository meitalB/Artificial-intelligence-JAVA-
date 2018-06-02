public class NodeMatrix extends Node{
    // this class handle the NodeMatrix that include
    //Node and more parameters for the matrix

    //member
    private boolean isObstacle;

//constructor uses int x and y
    public NodeMatrix(int x, int y, char content)  {
        this.content=content;
        this.point = new Point(x,y);
        this.isObstacle = false;
    }



    // return the point of node.
    public Point getPoint() {
        return this.point;
    }

    //set if that obstacle
    public void setIsObstacle(boolean x) {
        this.isObstacle = x;
    }

    //return the distance from the start to this node
    public int getDistance() {
        if (isObstacle) {
            return -2;
        }
        return this.distance;
    }
}
