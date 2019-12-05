import java.io.*; 
import java.util.Scanner; 
public class dvrouter{
    /*
    * [ARGS] : {topoFile, changesFile, messageFile}
    */
    public static void main(String [] args){
    // first check for number of arguments
    if(args.length < 3) {
        System.out.println("Error, usage: java dvrouter topoFile changesFile messageFile");
	    System.exit(1);
    }

    //dvrouter object made to manipulate and store the routers
    WeightedGraph test = new WeightedGraph();
    
    // This creates all the routers from the topology files
    try {
        Scanner scan = new Scanner(new FileInputStream(args[0]));
        while (scan.hasNextLine()) {
            String inputLine = scan.nextLine();
            String[] values = inputLine.split(" ");
            test.routerExist(Integer.parseInt(values[0]));
            test.routerExist(Integer.parseInt(values[1]));
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
            
            Router router1 = test.getRouter(router1ID);
            Router router2 = test.getRouter(router2ID);
            router1.addConnection(router2, weight);
            router2.addConnection(router1, weight);
        }
        scan.close();
    } catch (Exception e){
        System.out.println(e + "2");
    }

    /*
    System.out.println("Changes File");
    //This will read changes file
    try {
        Scanner scan = new Scanner(new FileInputStream(args[1]));
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
    } catch (Exception e){
        System.out.println(e);
    }

    System.out.println("Message File");
    //This will read message file
    try {
        Scanner scan = new Scanner(new FileInputStream(args[2]));
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
    } catch (Exception e){
        System.out.println(e);
    }
    */

    //test.routerList();

    }
}