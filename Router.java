import java.io.*; 
import java.util.*;

public class Router{
    //Router's ID
    private int id;
    private ArrayList<Router> connectedRouters;
    private ArrayList<Integer> pathDistance;
    /*  ^
    *   |
    *   Schema for "Forwarding Table"
    *   {R1, R3, R4}       <--Routers it is connected too
    *   {3,  7,  9 }       <--Distance to each router.  Will be put in same position in array list
    *
    */ 
    public Router(int idNum) {
        id = idNum;
    }

    public int getID() {
        return id;
    }

    public int getDistance(Router router){
        for (int i = 0; i < connectedRouters.size();i++){
            if (connectedRouters.get(i).getID() == router.getID()){
                return pathDistance.get(i);
            }
        }

        return -1;
    }
}