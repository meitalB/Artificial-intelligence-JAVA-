import java.util.*;

public class IDS {
    // this class handle the algorithm of IDS
    //get a start node and end node - return the path of this algorithm

    //members
    private int total_cost;


    // Constructor init the ids.
    public IDS() {
        this.total_cost = 0;
    }

    //the function get start node and end node and depth - do IDS algorithm and return path
    public List<Node> funcIDS(Node start, Node end, int depth, char grid[][],int size) {
        List<Node> path = new ArrayList<>();
        List<Node> final_path = new ArrayList<>();
        int final_cost = 1000000000;
        int cost = 0;

        Node p;

        // Create a queue for IDS
        Stack<Node> stack = new Stack<>();
        List<Node> current_in_stack = new ArrayList<>();
        List<Node> neighbors = new ArrayList<>();

        // enqueue the start node.
        stack.add(start);
        current_in_stack.add(start);
        start.setDistance(0);
        start.setParent(null);
        while (!stack.isEmpty()) {
            // Dequeue a vertex from queue
            start = stack.pop();
            if (start.getDistance() <= depth) {
//            System.out.println("current kodkod"+ start.getContent() +
//                    " x "+start.point.getx() +  "  y "+ start.point.gety()+"my distance"+start.getDistance());
                if (start.getContent() != 'G') {
                    start.visited = true;
                }
                //remove from current_in_stack
                for (int k = 0; k < current_in_stack.size(); k++) {
                    if ((current_in_stack.get(k).point.getx() == start.point.getx())
                            && (current_in_stack.get(k).point.gety() == start.point.gety())) {
                        current_in_stack.remove(k);
                        break;
                    }
                }
                //start.visited=true;
                //System.out.println(start.getContent());

                //if we get to the destination
                if (start.getContent() == 'G') {

                    int checkCorrectPath = 1;//1 good. 0 not good
                    cost = 0;
                    path.add(start);
                    p = start.getParent();
                    while (p != null) {
                        if (p.getContent() == 'R') {
                            cost += 1;
                        } else if (p.getContent() == 'D') {
                            cost += 3;
                        } else if (p.getContent() == 'H') {
                            cost += 10;
                        }
                        path.add(p);
                        p = p.getParent();
                    }
                    if (cost < final_cost) {
                        final_cost = cost;
                        final_path = new ArrayList<Node>();
                        for (int d = 0; d < path.size(); d++) {
                            final_path.add(path.get(d));

                        }
                        path = new ArrayList<Node>();
                        break;
                    }
                }


                // Get all adjacent vertices of the dequeue vertex s
                // If a adjacent has not been visited, then mark it visited
                // and enqueue it

                neighbors = start.getNeighbors(size,grid);
                for (int i = neighbors.size() - 1; i > -1; i--) {
                    Node n = neighbors.get(i);
//                System.out.println("cheak n"+ n.getContent() +
//                        " x "+n.point.getx() +  "  y "+ n.point.gety() +"my distance"+n.getDistance());
                    int exist = 0;
                    //check if the node exist
                    for (int k = 0; k < current_in_stack.size(); k++) {
                        if ((current_in_stack.get(k).point.getx() == n.point.getx())
                                && (current_in_stack.get(k).point.gety() == n.point.gety())) {
                            exist = 1;
                            break;
                        }
                    }
                    //and to stack if the following condition true
                    if (n != null && exist == 0 && !n.visited) {
                        stack.add(n);
                        current_in_stack.add(n);
                        n.setDistance(start.getDistance() + 1);
                        n.setParent(start);

                    } else if (n != null && exist == 0 && n.getDistance() >= start.getDistance()) {
                        if (n.getDistance() > start.getDistance()) {
//                            System.out.println("current n" + n.getContent() +
//                                    " x " + n.point.getx() + "  y " + n.point.gety());
                            stack.add(n);
                            current_in_stack.add(n);
                            n.setDistance(start.getDistance() + 1);
                            n.setParent(start);

                        }
                        if (n.getDistance() == 0 && start.getDistance() == 0) {

//                        System.out.println("current n" + n.getContent() +
//                                " x " + n.point.getx() + "  y " + n.point.gety());
                            stack.add(n);
                            current_in_stack.add(n);
                            n.setDistance(start.getDistance() + 1);
                            n.setParent(start);
                        }
                    }

                }

            } else{
                //remove from current_in_stack
                for (int k = 0; k < current_in_stack.size(); k++) {
                    if ((current_in_stack.get(k).point.getx() == start.point.getx())
                            && (current_in_stack.get(k).point.gety() == start.point.gety())) {
                        current_in_stack.remove(k);
                        break;
                    }
                }
            }
        }//return the path of the IDS
        this.total_cost = final_cost;
        return final_path;
    }

    //return the final cost to this path in this algorithm
    public int getCost() {
        return this.total_cost;
    }
}