import java.util.*;

public class Astar {
    // this class handle the algorithm of A*
    //get a start node and end node - return the path of this algorithm

    //members
    private int total_cost;

    // Constructor init the Astar.
    public Astar() {
        // this.total_cost=0;
    }

    //the function get start node and end node and depth - do A* algorithm and return path
    public List<Node> funcAstar(Node start, Node end,char grid[][],int size) {
        List<Node> path = new ArrayList<>();
        List<Node> final_path = new ArrayList<>();
        int final_cost = 1000000000;
        int cost = 0;
        Comparator<Node> comparator = new CostValueComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comparator);//TODO change to something the num

        Node p;

        // Create a queue for Astar
        List<Node> current_in_stack = new ArrayList<>();
        List<Node> neighbors = new ArrayList<>();
        List<Node> helpNeighbors = new ArrayList<>();

        int bound = heuristicFunction(start, end);

        // enqueue the start node.
        //stack.add(start);
        queue.add(start);
        current_in_stack.add(start);
        //was_in_stack.add(start);
        start.setMyCost(0);
        start.setDistance(0);
        start.setParent(null);
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            start = queue.poll();
            start.heuristicVal = start.myCost + heuristicFunction(start, end);

            if (start.myCost <= (size*size*10)) {
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

                }
                path = new ArrayList<Node>();
                break;
            }


            // Get all adjacent vertices of the dequeue vertex s
            // If a adjacent has not been visited, then mark it visited
            // and enqueue it
            neighbors = start.getNeighbors(size,grid);

            for (int i = 0; i < neighbors.size() ; i++) {
                Node n = neighbors.get(i);
                n.setPlaceInCircle(i);
                int current_tav_cost = 0;
                int exist = 0;
                for (int k = 0; k < current_in_stack.size(); k++) {
                    if ((current_in_stack.get(k).point.getx() == n.point.getx())
                            && (current_in_stack.get(k).point.gety() == n.point.gety())) {
                        exist = 1;
                        break;
                    }
                }
                if (n.getContent() == 'R') {
                    current_tav_cost = 1;
                } else if (n.getContent() == 'D') {
                    current_tav_cost = 3;
                } else if (n.getContent() == 'H') {
                    current_tav_cost = 10;
                }
                if (n != null && exist == 0 && !n.visited) {

                    //=stack.add(n);
                    current_in_stack.add(n);
                    n.setDistance(start.getDistance() + 1);
                    n.setMyCost(start.getMyCost() + current_tav_cost);
                    n.setParent(start);
                    n.heuristicVal = n.getMyCost() + heuristicFunction(n, end);

                    queue.add(n);
                    //  n.visited = true;
                    // }
                    // }

                } else if (n != null && exist == 0 && n.getDistance() >= start.getDistance()) {
                    if (n.getDistance() > start.getDistance()) {
//                            System.out.println("current n" + n.getContent() +
//                                    " x " + n.point.getx() + "  y " + n.point.gety());
                        //= stack.add(n);


                        current_in_stack.add(n);
                        n.setDistance(start.getDistance() + 1);
                        n.setMyCost(start.getMyCost() + current_tav_cost);
                        n.setParent(start);
                        n.heuristicVal = n.getMyCost() + heuristicFunction(n, end);

                        queue.add(n);
                        //  n.visited = true;
                    }
                    if (n.getDistance() == 0 && start.getDistance() == 0) {

//                        System.out.println("current n" + n.getContent() +
//                                " x " + n.point.getx() + "  y " + n.point.gety());
                        //= stack.add(n);


                        current_in_stack.add(n);
                        n.setDistance(start.getDistance() + 1);
                        n.setMyCost(start.getMyCost() + current_tav_cost);
                        n.setParent(start);
                        n.heuristicVal = n.getMyCost() + heuristicFunction(n, end);
                        queue.add(n);
                    }
                }

            }

            // }
        }
            else {
                //remove from current_in_stack
                for (int k = 0; k < current_in_stack.size(); k++) {
                    if ((current_in_stack.get(k).point.getx() == start.point.getx())
                            && (current_in_stack.get(k).point.gety() == start.point.gety())) {
                        current_in_stack.remove(k);
                        break;
                    }
                }

            }
        }//return the path of the Astar
        this.total_cost = cost;
        return final_path;
    }

    //return compute the heuristic Function
    public int heuristicFunction(Node s, Node g) {
        double dx =Math.abs ((double) s.point.getx()-(double)g.point.getx());
        double dy = Math.abs((double) g.point.gety()-(double)g.point.gety());
        return (int) Math.max(dx,dy);
    }

    //the cost to this algorihtm
    public int getCost() {
        return this.total_cost;
    }
}