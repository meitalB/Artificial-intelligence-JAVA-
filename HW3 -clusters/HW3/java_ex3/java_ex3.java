import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class java_ex3 {
    //this class is the main function that get input
    // and return output file with who is going to win


    //main function
    public static void main(String[] args) {

        int counterLines = 0;
        String ans="";

        String clusterName="";
        int numOfCluster=0;
        List<point> inputPoints = new ArrayList<>();
        int currentNumOfCluster=0;
        List<cluster> listOfCluster=  new ArrayList<>();
        List<cluster> updateNumbers=new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        //read from file
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("input.txt");//TODO change to input regular
            br = new BufferedReader(fr);

            String sCurrentLine;
            //read data from file
            while ((sCurrentLine = br.readLine()) != null) {
               // System.out.println(sCurrentLine);
                if (counterLines == 0) {
                    clusterName = sCurrentLine;
                } else if (counterLines == 1) {
                    numOfCluster = Integer.parseInt(sCurrentLine);
                } else {
                    String[] elephantList = sCurrentLine.split(",");
                    point p = new point(Double.parseDouble(elephantList[0]), Double.parseDouble(elephantList[1]));
                    inputPoints.add(p);
                }
                counterLines++;
            }
        currentNumOfCluster=inputPoints.size();
        //init clusters
            for(int i=0; i<currentNumOfCluster; i++){
                point a=inputPoints.get(i);
                List<point> l= new ArrayList<>();
                l.add(a);
                cluster C= new cluster(l,i+1);
                listOfCluster.add(C);

            }
        currentNumOfCluster=listOfCluster.size();
            List<cluster> temp=new ArrayList<>();
            if(currentNumOfCluster==numOfCluster){
                updateNumbers=listOfCluster;
            }
            //reduce the cluster- each time one
        while (currentNumOfCluster>numOfCluster){
            temp =updateCluster(listOfCluster,clusterName);
            currentNumOfCluster=temp.size();

        }
        int counterTemp=1;
        int found=0;

        //update the cluster number as require in the assingment
            for(int i=0; i<inputPoints.size(); i++) {
                found = 0;
                point current = inputPoints.get(i);
                for (int j = 0; j < updateNumbers.size(); j++) {
                    cluster c = updateNumbers.get(j);
                    for (int y = 0; y < c.getMyPoints().size(); y++) {
                        if (current == c.getMyPoints().get(y)) {
                            found = 1;

                        }
                    }
                }
                    if (found == 0) {
                        for (int r = 0; r < temp.size(); r++) {
                            cluster n = temp.get(r);
                            for (int e = 0; e < n.getMyPoints().size(); e++) {
                                if (n.getMyPoints().get(e) == current) {
                                    n.setValCluster(counterTemp);
                                    updateNumbers.add(n);
                                    counterTemp++;
                                }
                            }

                        }

                    }
                }


        //print the numbers of clusters
        for(int i=0; i<inputPoints.size(); i++){
            point current= inputPoints.get(i);
            for(int j=0; j<updateNumbers.size(); j++){
                cluster c= updateNumbers.get(j);
                for(int k=0; k<c.getMyPoints().size(); k++){
                    if(c.getMyPoints().get(k)==current){
                        ans+=c.getValCluster()+"\n";
                    }
                }

            }

        }



        }catch (IOException e) {

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


        //write to file
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), "utf-8"));
            writer.write(ans);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }


    //update the list of cluster
    public static List<cluster> updateCluster(List<cluster>listCluster,String algoName) {
        double minDistance = 100000000;
        cluster minclusterLeft = new cluster(null, -9);
        cluster minclusterRight = new cluster(null, -9);
        if (algoName.equals("single link")) {
            for (int i = 0; i < listCluster.size() ; i++) {
                for (int j = 0; j < listCluster.size(); j++) {
                    cluster left = listCluster.get(i);
                    cluster right = listCluster.get(j);
                    if (left != right) {

                    double local = getMinDistance(left.getMyPoints(), right.getMyPoints());
                    if (local < minDistance) {
                        minDistance = local;
                        minclusterLeft = left;
                        minclusterRight = right;
                    }
                }
                }
            }
            int numToRemove = -9;
            for (int i = 0; i < listCluster.size(); i++) {
                if (listCluster.get(i) == minclusterLeft) {
                    List<point> rightPoints = minclusterRight.getMyPoints();
                    for (int j = 0; j < minclusterRight.getMyPoints().size(); j++) {
                        listCluster.get(i).addPoint(rightPoints.get(j));
                    }
                } else if (listCluster.get(i) == minclusterRight) {
                    numToRemove = i;
                }
            }
            listCluster.remove(numToRemove);
            return listCluster;
        }else if(algoName.equals("average link")){
            for (int i = 0; i < listCluster.size() ; i++) {
                for (int j = 0; j < listCluster.size(); j++) {
                    cluster left = listCluster.get(i);
                    cluster right = listCluster.get(j);
                    if (left != right) {

                        double local = getAvgDistance(left.getMyPoints(), right.getMyPoints());
                        if (local < minDistance) {
                            minDistance = local;
                            minclusterLeft = left;
                            minclusterRight = right;
                        }
                    }
                }
            }
            int numToRemove = -9;
            for (int i = 0; i < listCluster.size(); i++) {
                if (listCluster.get(i) == minclusterLeft) {
                    List<point> rightPoints = minclusterRight.getMyPoints();
                    for (int j = 0; j < minclusterRight.getMyPoints().size(); j++) {
                        listCluster.get(i).addPoint(rightPoints.get(j));
                    }
                } else if (listCluster.get(i) == minclusterRight) {
                    numToRemove = i;
                }
            }
            listCluster.remove(numToRemove);
        }
        return listCluster;
    }

    //calculate distance for single link
    public static double getMinDistance(List<point> ls1,List<point> ls2){
        double minDistance=100000000;
        for (int i=0; i<ls1.size(); i++){
            for (int j=0; j<ls2.size(); j++){

                double localDis =dis(ls1.get(i),ls2.get(j));
                if(localDis<minDistance){
                    minDistance=localDis;
                }
            }
        }
        return minDistance;
    }

    // calculate distance for average link
    public static double getAvgDistance(List<point> ls1,List<point> ls2){
        double avgDistance=0;
        for (int i=0; i<ls1.size(); i++){
            for (int j=0; j<ls2.size(); j++){

                double localDis =dis(ls1.get(i),ls2.get(j));
                avgDistance+=localDis;
            }
        }
        double temp = avgDistance/(ls1.size()+ls2.size());
        return temp;
    }

    //check distance of to points
    public static  double dis(point p1, point p2){
        return Math.sqrt(Math.pow((p2.x()-p1.x()),2)+Math.pow((p2.y()-p1.y()),2));
    }

}

