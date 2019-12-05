import java.io.*; 
import java.util.*;

public class Router{
    /*
    * Router has an id and a list of connections to other routers
    */
    private int id;
    private ArrayList<Connection> connections;
    private boolean visited;

    public Router(int idNum) {
        id = idNum;
        connections = new ArrayList<Connection>();
        visited = false;
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

    public ArrayList<Connection> getConnections(){
        return connections;
    }

    public void showConnections(){
        for (Connection c: connections){
            c.showInfo();
        }
    }

    public void visit(){
        visited = true;
    }

    public void unvisit(){
        visited = false;
    }

    public boolean getVisit(){
        return visited;
    }
}