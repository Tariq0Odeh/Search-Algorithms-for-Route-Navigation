import java.util.ArrayList;

public class Record{								//To save all record for algorithm (visited cities, the path, expanded cities and cost)
	protected ArrayList<Integer> visited;			
	protected ArrayList<Integer> path;
	protected ArrayList<Integer> expanded;
	
	public Record(){
		this.visited = new ArrayList<Integer>();
		this.path = new ArrayList<Integer>();
		this.expanded = new ArrayList<Integer>();
	}	
	
	public double path_cost(Graph g){				//Method to find the total cost of path
		double tc = 0.0;
		for(int i = 0 ; i < this.path.size()-1 ; i++){
			for(int j = 0 ; j < g.adjList[this.path.get(i)].size() ; j++) {
				if(g.adjList[this.path.get(i)].get(j).getCityNum() == this.path.get(i+1)){
					tc += g.adjList[this.path.get(i)].get(j).getDistance();
					break;
				}
			}	
		}
		return tc;
	}
}
