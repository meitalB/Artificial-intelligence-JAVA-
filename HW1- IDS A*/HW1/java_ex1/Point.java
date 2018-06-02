public class Point {
    // this class handle the point that include
    //two parameters - x for col, y for  row

    //members
    private   int xVal, yVal;
    // Constructor uses int x and y values
    Point(int x, int y) {
        xVal=x;
        yVal=y;
    }

    //get x
    public int getx() { return  this.xVal; }

    //get y
    public int gety() { return  this.yVal; }

    //set x
    public void setx(int x) {  this.xVal=x; }

    //set y
    public void sety(int y) {   this.yVal=y; }

////constructor Point uses Point p
//    Point::Point(const Point &p) {
//        this->xVal = p.x();
//        this->yVal = p.y();
//    }

//return true if the values are equals, otherwise- false
    public boolean isSame( Point p) {
        return ((xVal == p.getx()) && (yVal == p.yVal));
    }
}
