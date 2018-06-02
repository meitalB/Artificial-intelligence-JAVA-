import java.util.Comparator;

public class CostValueComparator implements Comparator<Node> {
    //this class help us for the comparator node with the PriorityQueue
    //compare according to the instruction

    //the compare function 1 means return x, -1 y
    @Override
    public int compare(Node x, Node y) {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.getHeuristicVal() < y.getHeuristicVal()) {
            return -1;
        } else if (y.getHeuristicVal() < x.getHeuristicVal()) {
            return 1;
        } else if (y.getParent() != x.getParent()) {
            if (x.getParent().getDistance() < y.getParent().getDistance()) {
                return -1;
            } else if (x.getParent().getDistance() > y.getParent().getDistance()) {
                return 1;
            }
            if (x.getPlaceInCircle() < y.getPlaceInCircle()) {
                return -1;
            } else if (x.getPlaceInCircle() > y.getPlaceInCircle()) {
                return 1;
            } else return 0;//Todo what if parent difrent and distance same?
        } else {
            if (x.getPlaceInCircle() < y.getPlaceInCircle()) {
                return -1;
            } else if (x.getPlaceInCircle() > y.getPlaceInCircle()) {
                return 1;
            } else return 0;
        }
        //return 0;
    }
}
