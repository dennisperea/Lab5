import java.util.*;
public class WeightedGraph{
    public ArrayList<Router> routers = new ArrayList<Router>();
    public int size = 0;
    public ArrayList<Integer> hops = new ArrayList<Integer>();

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

    public boolean hasAvailableConnections(Router current){
        //Break case 2, all of the connections have been visited already
        boolean destinationAvailable = false;
        for (Connection c : current.getConnections()){
            //if there is a router that has not been visited
            if (c.getDestination().getVisit() == false){
                destinationAvailable = true;
            }
        }
        return destinationAvailable;
    }

    public void routerList(){
        for (Router r:routers){
            System.out.println("Router " + r.getID());
            r.showConnections();
        }
    }

    /*
    *  Remember, array[pos] = id - 1
    *  returns <lasthop, pathcost>
    */
    public int[] djekstra(Router root, Router destination){
        //if router and destination same
        if (root.getID() == destination.getID()){
            int[] result = new int[2];
            result[0] = root.getID();
            result[1] = 0;
            hops.add(0);
            return result;
        }

        //Storing shortest path currently known
        int[] shortestPath = new int[size];
        //Storing the weights of the current router's connection to neighbors
        int[] currentNeighborConnectionWeight = new int[size];

        //First set all of the positions to 9999 (pseudo infinity)
        for (int i = 0; i < shortestPath.length;i++){
            shortestPath[i] = 9999;
        }

        // Set the distance to itself as 0, mark as visited
        shortestPath[root.getID()-1] = 0;
        root.visit();

        //For each connection the root has, set the shortestPath available to the weight to the neighbor
        for (Connection c : root.getConnections()){
            shortestPath[c.getDestination().getID()-1] = c.getWeight();
        }

        // Prints out content
        // System.out.println("-----------------------------------------------------------------------------------");
        // System.out.println("We are going from " + root.getID() + " to " + destination.getID());

        //Find the smallest router to start the algorithm
        //ID of smallest router, start the value with the first available option
        int smallestRouterID = 0;
        int smallestRouterWeight = 9999;
        //Router used for iterating
        Router current = null;
        //Storing path length
        int totalPathLength = 0;
        int oldPathLength =99999;
        ArrayList<Integer> oldHops = new ArrayList<Integer>();


        //Find the shortest available path link to start with
        for (int k = 0; k < shortestPath.length; k++){
            if ((shortestPath[k]  < smallestRouterWeight) && (shortestPath[k] != 0)){
                // id is pos+1
                smallestRouterID = k + 1;
                smallestRouterWeight = shortestPath[k];
            }
        }
        // add its weight
        totalPathLength += smallestRouterWeight;

        //This is where we start our first step
        current = getRouter(smallestRouterID);
        hops.add(current.getID());
        current.visit();
        // System.out.println("First step--> Router:" + smallestRouterID + " Weight to router from root:" + smallestRouterWeight);
        //Now we loop
        Router previousRouter = current;
        boolean done = false;
        while (done == false){
            //Break case 1, we found the router
            if (current.getID() == destination.getID()){
              // check if root has available connections
              /*if (hasAvailableConnections(root)){
                // set current to root and store totalPathLength to new variable && store hops in new array ArrayList
                oldPathLength = totalPathLength;
                totalPathLength=0;
                oldHops = hops;
                hops.clear();
                break;
              }
              if (oldPathLength <totalPathLength){
                totalPathLength = oldPathLength;
                hops = oldHops;
              }*/
              done = true;
              // System.out.println("Router found");
              break;
            }

            //Break case 2, all of the connections have been visited already
            boolean destinationAvailable = hasAvailableConnections(current);
            if (destinationAvailable == false){
                if(hasAvailableConnections(root)){
                  current = root;
                  hops.clear();
                  totalPathLength= 0;
                } else {
                  done = true;
                  // System.out.println("v-- No more routers available");
                  break;
                }
            }

            //set all to 9999 to assume we cant see
            for (int a = 0; a < currentNeighborConnectionWeight.length; a++){
                currentNeighborConnectionWeight[a] = 9999;
            }

            //Recalculate for new path lengths
            for (Connection c : current.getConnections()){
                //Only calculate path for routers that havent been visited
                if (c.getDestination().getVisit() == false){
                    currentNeighborConnectionWeight[c.getDestination().getID()-1] = c.getWeight();
                }
            }

            //Find the next step to take
            //Find the shortest available path link to start with
            smallestRouterID = 0;
            smallestRouterWeight = 9999;
            for (int b = 0; b < currentNeighborConnectionWeight.length; b++){
            if ((currentNeighborConnectionWeight[b]  < smallestRouterWeight) & (getRouter(b+1).getConnections() != null)){
                // id is pos+1
                smallestRouterID = b + 1;
                smallestRouterWeight = currentNeighborConnectionWeight[b];
            }
            }

            // add its weight
            totalPathLength += smallestRouterWeight;
            previousRouter = current;
            current = getRouter(smallestRouterID);
            hops.add(current.getID());
            current.visit();
            // System.out.println("Next shortest path--> Router: " + current.getID() + " Length from root:" + totalPathLength);


        } // <--------------------------------------------------------------------------------End of while loop

        // returns <lasthop, pathcost>
        int[] result = new int[2];
        //Check and see if the path length is smaller then the original
        if (totalPathLength < shortestPath[destination.getID()-1]){
            shortestPath[destination.getID()-1] = totalPathLength;
            result[0] = previousRouter.getID();
            result[1] = shortestPath[destination.getID()-1];
        } else {
            //root was shorter
            result[0] = root.getID();
            result[1] = shortestPath[destination.getID()-1];
        }
        // System.out.println("Last hop:" + current.getID() + " Total Distance:" + shortestPath[destination.getID()-1]);

        //reset the visited list
        for (Router r: routers){
            r.unvisit();
        }
        return result;
    }

    public void printHops(){
      for (int i : hops) {
        System.out.print(" "+i);
      }
    }

    public void printRoutingTable(){
        for (int j = 1; j < routers.size()+1;j++){
            System.out.println("Router " + getRouter(j).getID());
            for (int i = 1; i < size+1; i++){
                //getRouter(i) is destination
                int[] djekstraResult = djekstra(getRouter(j), getRouter(i));
                System.out.println(getRouter(i).getID() + " " + djekstraResult[0] + " " + djekstraResult[1]);
            }
            System.out.println();
        }
    }


}
