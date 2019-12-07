import java.io.*;
import java.util.*;
public class lsrouter{
  /*
  * [ARGS] : {topoFile, changesFile, messageFile}
  */

  public void createTopologyFile(String[] args, WeightedGraph graph){
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
  }

  public void createConnections(String[] args, WeightedGraph graph){
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
  }

  public void readMessageFiles(String[] args, WeightedGraph graph){
    System.out.println("Message File");
    //This will read message file
    try {
      Scanner scan = new Scanner(new FileInputStream(args[2]));
      BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
      while (scan.hasNextLine()) {
        String inputLine = scan.nextLine();
        String[] values = inputLine.split(" ", 3);
        int root = Integer.parseInt(values[0]);
        int destination = Integer.parseInt(values[1]);
        graph.hops.clear();
        graph.djekstra(graph.getRouter(root), graph.getRouter(destination));
        System.out.print("from " + values[0] + " to " +values[1]+": hops");
        graph.printHops();
        System.out.print("; message: "+ values[2]);
        System.out.println();

        String result = "from " + values[0] + " to " + values[1] +": hops" +  graph.getHops() + "; message: "+ values[2];
        writer.append(result);
        writer.newLine();
        //add case for if destination unreachable
        //
      }
      writer.newLine();
      scan.close();
      writer.close();
    } catch (Exception e){
      System.out.println(e);
    }
  }

  public void readChangesFile(String[] args, WeightedGraph graph){
    System.out.println();
    System.out.println("Changes File");
    //This will read changes file
    
    try {
      Scanner scan = new Scanner(new FileInputStream(args[1]));
      while (scan.hasNextLine()) {
        String inputLine = scan.nextLine();
        String[] values = inputLine.split(" ");
        int router1ID = Integer.parseInt(values[0]);
        int router2ID = Integer.parseInt(values[1]);
        int weight = Integer.parseInt(values[2]);

        if(weight == -999){
          weight = 999;
        }

        Router router1 = graph.getRouter(router1ID);
        Router router2 = graph.getRouter(router2ID);
        router1.addConnection(router2, weight);
        router2.addConnection(router1, weight);

        graph.printRoutingTable();
        System.out.println();
        readMessageFiles(args,graph);
        
      }
      scan.close();
    } catch (Exception e){
        System.out.println(e + "2");
    }
  }

  public static void main(String [] args){
    // first check for number of arguments
    if(args.length < 3) {
      System.out.println("Error, usage: java lsrouter topoFile changesFile messageFile");
      System.exit(1);
    }

    //graph object made to manipulate and store the routers, lsrouter object to call helper functions
    WeightedGraph graph = new WeightedGraph();
    lsrouter lsrout = new lsrouter();
    lsrout.createTopologyFile(args, graph);
    lsrout.createConnections(args, graph);

    graph.printRoutingTable();

    lsrout.readMessageFiles(args, graph);
    lsrout.readChangesFile(args, graph);
  }
}
