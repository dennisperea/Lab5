import java.io.*; 
import java.util.*;

public class Router{
    /*
    * Router has an id and a list of connections to other routers
    */
    private int id;
    private ArrayList<Connection> connections;

    public Router(int idNum) {
        id = idNum;
        connections = new ArrayList<Connection>();
    }

    public int getID() {
        return id;
    }

    public void addConnection(Router destinationID, int weight){
        Connection newCon = new Connection(destinationID, weight);
        connections.add(newCon);
    }

    //gets Connection based off of Router ID being passed
    public Connection getConnection(int ID){
        Connection response = null;
        for (Connection c : connections){
            if (c.isConnection(ID)){
                response = c;
            }
       }

       return response;
    }

    public void showConnections(){
        for (Connection c: connections){
            c.showInfo();
        }
    }
}