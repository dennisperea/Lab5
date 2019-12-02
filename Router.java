import java.io.*; 
import java.util.*;

public class Router{
    /*
    * Router has an id and a list of connections to other routers
    */
    private int id;
    private ArrayList<Connection> Connections;

    public Router(int idNum) {
        id = idNum;
    }

    public int getID() {
        return id;
    }

    //gets Connection based off of Router ID being passed
    public Connection getConnection(int ID){
        Connection response = null;
        for (Connection c : Connections){
            if (c.isConnection(ID)){
                response = c;
            }
       }

       return response;
    }
}