import java.io.*; 
import java.util.*;
import java.util.Scanner;
public class WeightedGraph{
    public ArrayList<Router> routers = new ArrayList<Router>();

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
}