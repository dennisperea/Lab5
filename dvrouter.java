import java.io.*; 
import java.util.*;
import java.util.Scanner; 
public class dvrouter{
    /*
    * [ARGS] : {topologyFile, changesFile, messageFile}
    */

    public static void main(String [] args){
    // first check for number of arguments
    if(args.length < 3) {
        System.out.println("Error, usage: java ClassName topologyFile changesFile messageFile");
	    System.exit(1);
    }

    try {
        Scanner scan = new Scanner(new FileInputStream(args[0]));
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
    } catch (Exception e){
        System.out.println(e);
    }

    }
}