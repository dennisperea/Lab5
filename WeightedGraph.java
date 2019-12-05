import java.io.*; 
import java.util.*;
import java.util.Scanner;
public class WeightedGraph{
    public ArrayList<Router> routers = new ArrayList<Router>();
    int size = 0;

    public boolean routerExist(int id){
        // If the router exist, don't create another router
        for (Router r : routers){
            if (r.getID() == id){
                return true;
            }
        }
        // Otherwise make the new Router and add it to the arrayList
        Router newRouter = new Router(id);
        routers.add(newRouter);
        if (id > size){
            size = id;
        }
        return false;
    }

    public Router getRouter(int id){
        for (Router r : routers){
            if (r.getID() == id){
                return r;
            }
        }

        return null;
    }
    public void routerList(){
        for (Router r:routers){
            System.out.println("Router " + r.getID());
            r.showConnections();
        }
    }

    /*
    *  Remember, array[pos] = id - 1
    */
    public void djekstra(Router root, Router destination){
        //Storing shortest path currently known
        int[] shortestPath = new int[size];
        //Storing the weights of the current router's connection to neighbors
        int[] currentNeighborConnectionWeight = new int[size];
        //Router used for iterating
        Router current = null;
        //Storing path length
        int totalPathLength = 0;

        //First set all of the positions to 9999 (pseudo infinity)
        for (int i : shortestPath){
            shortestPath[i] = 9999;
        }

        // Set the distance to itself as 0, mark as visited
        shortestPath[root.getID()-1] = 0;
        root.visit();

        //For each connection the root has, set the shortestPath available to the weight to the neighbor
        for (Connection c : root.getConnections()){
            shortestPath[c.getDestination().getID()-1] = c.getWeight();
        }
    }
}