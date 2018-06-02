import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class java_ex1 {
    //this class is the main function that get input
    // and return output file with the path of the algorithm (ids\A*)

    //main function
    public static void main(String[] args) {
        String nameOfAlgo = "";
        int counterLines = 1;
        int sizeOfGrid = 0;
        char[][] grid = new char[1][1];
        int lineInGrid;

        BufferedReader br = null;
        FileReader fr = null;
//read from file
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("input.txt");//TODO change to input regular
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                // System.out.println(sCurrentLine);
                if (counterLines == 1) { // save name of algorithm
                    nameOfAlgo = sCurrentLine;
                    // System.out.println("my algo    "+sCurrentLine);

                } else if (counterLines >= 2) {// save sizeof grid
                    if (counterLines == 2) {
                        sizeOfGrid = Integer.parseInt(sCurrentLine);
                        //System.out.println("my size    "+sCurrentLine);
                        grid = new char[sizeOfGrid][sizeOfGrid];
                    } else {
                        lineInGrid = counterLines - 3;
                        for (int i = 0; i < sizeOfGrid; i++) {
                            grid[lineInGrid][i] = sCurrentLine.charAt(i);
                            //System.out.println("current tav    "+grid[lineInGrid][i]);

                        }

                    }
                }
                counterLines++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        char[][] OPgrid = new char[sizeOfGrid][sizeOfGrid];
        //update grid
        for (int i = 0; i < sizeOfGrid; ++i) {
            for (int j = 0; j < sizeOfGrid; ++j) {
                OPgrid[i][j] = grid[j][i];
                // System.out.println("current tav    "+OPgrid[i][j]);

            }
            //System.out.println("  ");
        }

        int cost_total = 0;
        Matrix matrix1 = new Matrix(sizeOfGrid, OPgrid);
        IDS ids1 = new IDS();
        List<Node> path = new ArrayList<>();
        String myPath = "";

        //check if ids is the algorithm
        if (nameOfAlgo.equals("IDS")) {
            for (int e = 0; e < ((sizeOfGrid ) * (sizeOfGrid )); e++) {
                path = ids1.funcIDS(new Node((grid[0][0]),0,0),//TODO change depth(4) to e
                        new Node(grid[sizeOfGrid - 1][ sizeOfGrid - 1],sizeOfGrid - 1,sizeOfGrid - 1),
                        e,OPgrid,sizeOfGrid);
                if (path.size() != 0) {
                    break;
                }
            }
            for (int i = path.size() - 1; i > 0; i--) {
//                System.out.println("current tav"+ path.get(i).getContent() +
//                " x "+path.get(i).point.getx() +  "  y "+ path.get(i).point.gety());
                if ((path.get(i).point.getx() == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() + 1 == path.get(i - 1).point.gety())) {
                    myPath += "D";
                } else if ((path.get(i).point.getx() + 1 == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety())) {
                    myPath += "R";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() + 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety())) {
                    myPath += "L";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "U";
                } else if ((path.get(i).point.getx() + 1 == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() + 1 == path.get(i - 1).point.gety())) {
                    myPath += "RD";

                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() - 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "LU";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() + 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "LD";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() - 1)
                        && (path.get(i).point.gety() - 1 == path.get(i - 1).point.gety())) {
                    myPath += "RU";
                }
                if (i - 1 != 0) {
                    myPath += "-";
                }
            }
            myPath += " " + ids1.getCost();


            if (path.size() == 0) {
                //System.out.println("no path");
                myPath = "no path";
            }
            //System.out.println(myPath);
//write answer to file
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "utf-8"));
                writer.write(myPath);
            } catch (IOException ex) {
                // report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/}
            }


        } else if (nameOfAlgo.equals("A*")) {// if A* is the algoritm
            Astar astar = new Astar();
            path = astar.funcAstar(new Node((grid[0][0]),0,0),
                    new Node(grid[sizeOfGrid - 1][ sizeOfGrid - 1],sizeOfGrid - 1,sizeOfGrid - 1),
                    OPgrid,sizeOfGrid);
            for (int i = path.size() - 1; i > 0; i--) {
//                System.out.println("current tav"+ path.get(i).getContent() +
//                " x "+path.get(i).point.getx() +  "  y "+ path.get(i).point.gety());
                if ((path.get(i).point.getx() == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() + 1 == path.get(i - 1).point.gety())) {
                    myPath += "D";
                } else if ((path.get(i).point.getx() + 1 == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety())) {
                    myPath += "R";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() + 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety())) {
                    myPath += "L";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "U";
                } else if ((path.get(i).point.getx() + 1 == path.get(i - 1).point.getx())
                        && (path.get(i).point.gety() + 1 == path.get(i - 1).point.gety())) {
                    myPath += "RD";

                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() - 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "LU";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() + 1)
                        && (path.get(i).point.gety() == path.get(i - 1).point.gety() - 1)) {
                    myPath += "LD";
                } else if ((path.get(i).point.getx() == path.get(i - 1).point.getx() - 1)
                        && (path.get(i).point.gety() - 1 == path.get(i - 1).point.gety())) {
                    myPath += "RU";
                }
                if (i - 1 != 0) {
                    myPath += "-";
                }
            }
            myPath += " " + astar.getCost();


            if (path.size() == 0) {
                //  System.out.println("no path");
                myPath = "no path";
            }
            //System.out.println(myPath);

            //write to file
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "utf-8"));
                writer.write(myPath);
            } catch (IOException ex) {
                // report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/}
            }
        }

    }
}
