import java.io.*; 
import java.util.*;
public class lsrouter{
    /*
    * [ARGS] : {topoFile, changesFile, messageFile}
    */
    public static void main(String [] args){
        // first check for number of arguments
        if(args.length < 3) {
            System.out.println("Error, usage: java lsrouter topoFile changesFile messageFile");
            System.exit(1);
        }
    
        //dvrouter object made to manipulate and store the routers
        WeightedGraph graph = new WeightedGraph();
        
        // This creates all the routers from the topology files
        try {
            Scanner scan = new Scanner(new FileInputStream(args[0]));
            while (scan.hasNextLine()) {
                String inputLine = scan.nextLine();
                String[] values = inputLine.split(" ");
                graph.routerExist(Integer.parseInt(values[0]));
                graph.routerExist(Integer.parseInt(values[1]));
            }
            scan.close();
        } catch (Exception e){
            System.out.println(e + "1");
        }
    
       
        // This creates all the connections for all the routers
        try {
            Scanner scan = new Scanner(new FileInputStream(args[0]));
            while (scan.hasNextLine()) {
                String inputLine = scan.nextLine();
                String[] values = inputLine.split(" ");
                int router1ID = Integer.parseInt(values[0]);
                int router2ID = Integer.parseInt(values[1]);
                int weight = Integer.parseInt(values[2]);
                
                Router router1 = graph.getRouter(router1ID);
                Router router2 = graph.getRouter(router2ID);
                router1.addConnection(router2, weight);
                router2.addConnection(router1, weight);
            }
            scan.close();
        } catch (Exception e){
            System.out.println(e + "2");
        }
    
        graph.djekstra(graph.getRouter(1), graph.getRouter(3));
        //graph.routerList();
    
        }
}