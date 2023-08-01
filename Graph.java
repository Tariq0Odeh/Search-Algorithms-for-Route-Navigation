import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph{
	int numberOfVertex;					//Number of cities
	ArrayList<MyPair>[] adjList;		//The pairs of cities (use it in Greedy algorithm and Uniform Cost Search)
	double[] heuristic;					//To save the heuristic from source to destination (use it in Greedy and A* algorithms)
	
	Graph(int numberOfVertex){
		this.numberOfVertex = numberOfVertex;
		adjList = new ArrayList[numberOfVertex];
		heuristic = new double[numberOfVertex];
		for(int i = 0 ; i < numberOfVertex ; i++){
			adjList[i] = new ArrayList<MyPair>();
		}
	}
	
	void addEdge(int src, int cityNum, double distance){  			//To add edge on graph between cities
        this.adjList[src].add(new MyPair(cityNum, distance));  
    }
	
	
//----------------------------------------------------------------------------------
			//Breadth First Search (BFS) method	
	boolean BFS(int start, int destination, Record r){     
		boolean[] visitedNode = new boolean[this.numberOfVertex];    //To know if the city is visited or not
		for(int i = 0 ; i < this.numberOfVertex ; i++){      	//Make all cities not visited
			visitedNode[i]=false ;
		}
		Queue<Integer> q = new LinkedList<Integer>();			//To store cities to visit in order
		int []parentsOfVisitedNodes = new int [this.numberOfVertex];  //To know parents of visited cities  (used to retrieve the path)
		if(start >=0 && start < this.numberOfVertex && destination >=0 && destination < this.numberOfVertex){		//Check if the cities available
			q.add(start);
			int curr;
			visitedNode[start] = true;	//Make the city visited
			r.expanded.add(start);      //Added the city to expanded list
			while(!q.isEmpty()){
				curr = q.poll();
				r.visited.add(curr);	//Added the city to visited list
				if(curr == destination){   //Check if the city is the destination
					r.path.add(0,destination);
					int node;
					while((node = r.path.get(0)) != start){	//If reach the destination add the path
						r.path.add(0, parentsOfVisitedNodes[node]);
					}
					return true;
				}
				System.out.print("["+curr+"]: | ");
				int adjNode;
				for(int i = 0 ; i < this.adjList[curr].size() ; i++){	//Check the cities and expanded his children to continue search
					adjNode = this.adjList[curr].get(i).getCityNum();
					if(!visitedNode[adjNode]){			//Check the nodes who did not visit and expanded his children 
						visitedNode[adjNode] = true;
						r.expanded.add(adjNode); 
						parentsOfVisitedNodes[adjNode] = curr;    //Make the current city the father
						q.add(adjNode);
						System.out.print(adjNode+" | ");
					}
				}
				System.out.print("\n");				
			}
			
		}
		return false;
	}
	
//----------------------------------------------------------------------------------
			//Depth First Search (DFS) method	
	boolean DFS(int start, int destination, Record r){     
		boolean[] visitedNode = new boolean[this.numberOfVertex];    //To know if the city is visited or not
		for(int i = 0 ; i < this.numberOfVertex ; i++){      	//Make all cities not visited
			visitedNode[i]=false ;
		}
		 
		Stack<Integer> q = new Stack<Integer>();			//To store cities to visit in order
		int []parentsOfVisitedNodes = new int [this.numberOfVertex];  //To know parents of visited cities  (used to retrieve the path)
		if(start >=0 && start < this.numberOfVertex && destination >=0 && destination < this.numberOfVertex){		//Check if the cities available
			q.add(start);
			int curr;
			visitedNode[start] = true;	//Make the city visited
			r.expanded.add(start);      //Added the city to expanded list
			while(!q.isEmpty()){
				curr = q.pop();
				r.visited.add(curr);	//Added the city to visited list
				if(curr == destination){   //Check if the city is the destination
					r.path.add(0,destination);
					int node;
					while((node = r.path.get(0)) != start){	//If reach the destination add the path
						r.path.add(0, parentsOfVisitedNodes[node]);
					}
					return true;
				}
				System.out.print("["+curr+"]: | ");
				int adjNode;
				for(int i = 0 ; i < this.adjList[curr].size() ; i++){	//Check the cities and expanded his children to continue search
					adjNode = this.adjList[curr].get(i).getCityNum();
					if(!visitedNode[adjNode]){			//Check the nodes who did not visit and expanded his children 
						visitedNode[adjNode] = true;
						r.expanded.add(adjNode); 
						parentsOfVisitedNodes[adjNode] = curr;    //Make the current city the father
						q.add(adjNode);
						System.out.print(adjNode+" | ");
					}
				}
				System.out.print("\n");				
			}
			
		}
		return false;
	}
	
//----------------------------------------------------------------------------------
			//Greedy Algorithm method
	boolean greedy(int start, int destination, Record r){
		PriorityQueue<MyPair> pq = new PriorityQueue<MyPair>();  //To save cities based on heuristic
		boolean[] visitedNode = new boolean[this.numberOfVertex];
		for(int i = 0 ; i < this.numberOfVertex ; i++){ 	//Make all cities not visited
			visitedNode[i] = false;
		}
		int []parentsOfVisitedNodes = new int [this.numberOfVertex];			//To know parents of visited cities  (used to retrieve the path)
		if(start >= 0 && start < this.numberOfVertex && destination >= 0 && destination < this.numberOfVertex){		//Check if the cities available
			pq.add(new MyPair(start,this.heuristic[start]));
			visitedNode[start] = true;				//Make the city visited
			r.expanded.add(start);					//Added the city to expanded list
			int curr;
			while(!pq.isEmpty()) {
				curr = pq.poll().getCityNum();
				r.visited.add(curr);				//Added the city to visited list
				if(curr == destination){ 			//Check if the city is the destination
					r.path.add(0,destination);
					int node;
					while((node = r.path.get(0)) != start){		//If reach the destination add the path
						r.path.add(0,parentsOfVisitedNodes[node]);
					}
						return true;
				}
				System.out.print("["+curr+"]: ");
				int adjNode;
				for(int i = 0 ; i < this.adjList[curr].size() ; i++){ 			//Check the cities and expanded his children to continue search
					adjNode = this.adjList[curr].get(i).getCityNum();
					if(!visitedNode[adjNode]){						//Check the nodes who did not visit and expanded his children 
						visitedNode[adjNode] = true;
						r.expanded.add(adjNode);
						parentsOfVisitedNodes[adjNode ] = curr;    	//Make the current city the father
						pq.add(new MyPair(adjNode,this.heuristic[adjNode]));
					}
				}
				System.out.println(pq);	
			}
		}
		return false;
	}
	
//----------------------------------------------------------------------------------
			//Uniform Cost Search method
	boolean UniformCostSearch(int start, int destination, Record r){
		PriorityQueue<MyPair> pq = new PriorityQueue<MyPair>(); 		//To save cities based on cost
		boolean[] visitedNode = new boolean[this.numberOfVertex];	
		MyPair[] pairs = new MyPair[this.numberOfVertex];			
		for(int i=0 ; i < this.numberOfVertex ; i++){				//Make all cities not visited
			visitedNode[i] = false;
		}
		int []parentsOfVisitedNodes = new int [this.numberOfVertex];				//To know parents of visited cities  (used to retrieve the path)
		
		if(start >= 0 && start < this.numberOfVertex && destination >= 0 && destination < this.numberOfVertex){			//Check if the cities available
			pq.add(new MyPair(start,0));
			visitedNode[start] = true;					//Make the city visited
			r.expanded.add(start);						//Added the city to expanded list
			MyPair curr;
			while(!pq.isEmpty()){ 						//Dealing with the city we took from priority queue and examined
				curr = pq.poll();
				pairs[curr.getCityNum()] = null;
				r.visited.add(curr.getCityNum());
				visitedNode[curr.getCityNum()] = true;
				if(curr.getCityNum() == destination){			//Check if the city is the destination
					r.path.add(0,destination);
					int node;
					while((node=r.path.get(0)) != start) {
						r.path.add(0,parentsOfVisitedNodes[node]);
					}
					return true;
				} 
				System.out.print("["+curr+"]: ");
				MyPair adjNode;
				for(int i = 0; i < this.adjList[curr.getCityNum()].size() ; i++){ 		 //Check the cities and expanded his children to continue search
					adjNode = this.adjList[curr.getCityNum()].get(i);			
					if(!visitedNode[adjNode.getCityNum()]){							//Check the nodes who did not visit and expanded his children			
						r.expanded.add(adjNode.getCityNum()); 
						if(pairs[adjNode.getCityNum()] == null) {					
							pairs[adjNode.getCityNum()] = new MyPair(adjNode.getCityNum(), curr.getDistance() + adjNode.getDistance());  //Adding the father to this node and the cost to access it from the source
							parentsOfVisitedNodes[adjNode.getCityNum()] = curr.getCityNum();  		//Make the current city the father
							pq.add(pairs[adjNode.getCityNum()]);				//Add the node to priority queue
						}
						else if(pairs[adjNode.getCityNum()].getDistance() > (adjNode.getDistance() + curr.getDistance())){   //If there is a less expensive way to get to the city
							pq.remove(pairs[adjNode.getCityNum()]);																		//Update the less expensive way
							MyPair temp = new MyPair(adjNode.getCityNum(),adjNode.getDistance() + curr.getDistance());
							pq.add(temp);
							pairs[adjNode.getCityNum()] = temp;
							parentsOfVisitedNodes[adjNode.getCityNum()] = curr.getCityNum();
						}
					}
				}
				System.out.println(pq);	
			}
		}
		return false;
	}
	
//----------------------------------------------------------------------------------
				//A* method	
	boolean AStar(int start, int destination, Record r){
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();			//To save cities based on cost and heuristic
		boolean[] visitedNode = new boolean[this.numberOfVertex];
		Tuple[] tuples = new Tuple[this.numberOfVertex];				//Tuple that contains the City Num, sum Of Distances and heuristic for the city
		for(int i = 0 ; i < this.numberOfVertex ; i++){					//Make all cities not visited
			visitedNode[i]=false;
		}
		int []parentsOfVisitedNodes = new int [this.numberOfVertex];			//To know parents of visited cities (used to retrieve the path)
		if(start >= 0 && start < this.numberOfVertex && destination >= 0 && destination < this.numberOfVertex){			//Check if the cities available
			pq.add(new Tuple(start,0,this.heuristic[start]));
			visitedNode[start] = true;					//Make the city visited
			r.expanded.add(start);						//Added the city to expanded list
			Tuple curr;
			while(!pq.isEmpty()){						//Dealing with the city we took from priority queue and examined
				curr = pq.poll();
				tuples[curr.getCityNum()] = null;
				r.visited.add(curr.getCityNum());
				visitedNode[curr.getCityNum()] = true;
				if(curr.getCityNum() == destination){				//Check if the city is the destination
					r.path.add(0,destination);
					int node;
					while((node=r.path.get(0)) != start){
						r.path.add(0,parentsOfVisitedNodes[node]);
					}
					return true;
				}
				System.out.print("["+curr+"]: ");
				Tuple adjNode;															
				for(int i = 0 ; i < this.adjList[curr.getCityNum()].size() ; i++){ 		//Check the cities and expands his children to continue search
					adjNode = new Tuple(this.adjList[curr.getCityNum()].get(i).getCityNum(), this.adjList[curr.getCityNum()].get(i).getDistance(), this.heuristic[this.adjList[curr.getCityNum()].get(i).getCityNum()]);
					if(!visitedNode[adjNode.getCityNum()]){				//Check the nodes who did not visit and expanded his children			
						r.expanded.add(adjNode.getCityNum());
						if(tuples[adjNode.getCityNum()] == null){				//This is the first time the node is expanded	
							tuples[adjNode.getCityNum()] = new Tuple(adjNode.getCityNum(), curr.getSumOfDistances()+adjNode.getSumOfDistances(), this.heuristic[adjNode.getCityNum()]);
							parentsOfVisitedNodes[adjNode.getCityNum()] = curr.getCityNum();
							pq.add(tuples[adjNode.getCityNum()]);
						}										//If there is a less expensive way to get to the city
						else if(tuples[adjNode.getCityNum()].getSumOfDistances() + tuples[adjNode.getCityNum()].getHeuristic() > adjNode.getSumOfDistances() + adjNode.getHeuristic() + curr.getSumOfDistances()){
							pq.remove(tuples[adjNode.getCityNum()]);								//Update the less expensive way
							Tuple temp = new Tuple(adjNode.getCityNum(), curr.getSumOfDistances() + adjNode.getSumOfDistances(), this.heuristic[adjNode.getCityNum()]);
							pq.add(temp);			//Add the node to priority queue
							tuples[adjNode.getCityNum()] = temp;
							parentsOfVisitedNodes[adjNode.getCityNum()] = curr.getCityNum();
						}
					}
				}	
				System.out.println(pq);	
			}
		}
		return false;
	}
}