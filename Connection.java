import java.io.*; 
import java.util.*;

public class Connection {
    /*
    * A connection is where the connection goes to and the weight of the connection
    */
    private Router destination;
    private int weight;

    public Connection(Router destinationRouter, int connectionWeight){
        destination = destinationRouter;
        weight = connectionWeight;
    }

    // This checks if the connection's router has the same id
    public boolean isConnection(int id) {
        return (id == destination.getID());
    }

    public int getWeight(){
        return weight;
    }

    public Router getDestination(){
        return destination;
    }

    public void showInfo(){
        System.out.println("Connection to " +  destination.getID() + ", weight: " + weight);
    }
}