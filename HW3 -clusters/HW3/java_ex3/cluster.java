import javax.swing.*;
import java.awt.*;
import java.util.List;

public class cluster {
    //the class hold the cluster- inside there are the value and points in this cluster
    private List<point> myPoints;
    private int valCluster;

    //constractor
    public cluster(List<point> p,int val){
        myPoints=p;
        valCluster=val;
    }
    //set
    public void setMyPoints(List<point> p){
        myPoints=p;
    }
    //set
    public void setValCluster(int val){
        valCluster=val;
    }
    //get
    public int getValCluster(){
        return valCluster;
    }
    //get
    public List<point> getMyPoints(){
        return myPoints;
    }
    //add new point
    public void addPoint(point p){
        myPoints.add(p);
    }

}
