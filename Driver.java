import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Driver {
	static int c = 0;
	public static void main(String[] args) throws FileNotFoundException {		
		
		Record record = new Record(); //To save all record for algorithm (visited cities, the path and expanded cities) 
		double [][] h1, h2;    		//Heuristic(h1)--> Areal distances, Heuristic(h2)--> Walk distances.
		Graph DrivingMap = null;    //A graph that will be built after reading the data from the file.
		String[] cities = null;		//To save the names of cites
		File inFile = new File("C:\\Users\\tariq\\OneDrive\\Desktop\\University\\AI\\Projects\\Project1\\Project1-Ai\\Project1\\src\\Data.txt");
		Scanner inf = new Scanner(inFile);
		DrivingMap = new Graph(inf.nextInt());    	//read number of cites from first line in file
		Graph WalkingMap = new Graph(DrivingMap.numberOfVertex);
		cities = new String[DrivingMap.numberOfVertex];
		h1 = new double[DrivingMap.numberOfVertex][DrivingMap.numberOfVertex];
		h2 = new double[DrivingMap.numberOfVertex][DrivingMap.numberOfVertex];
		inf.close();
		readGraph(0, inFile, DrivingMap, cities, h1, h2);     		//read the data from file and take car distances as real cost
		readGraph(1, inFile, WalkingMap, cities, null, null);     	//read the data from file and take walk distances as real cost
		Scanner in = new Scanner ( System.in );	
		String str;
		
		while(true){
			display_main_menu();
			str = in.next();              			//reads input from user   
			Main:
		    switch (str) {
		      case "1":
		    	System.out.println ( "\t\t ______________________________________ " );
			    System.out.println ( "\t\t|                                      |" );
			    System.out.println ( "\t\t|        The Palestinian cities        |" );
			    System.out.println ( "\t\t|______________________________________|\n" );
			    display_cities(DrivingMap, cities);
		        break;
		      
		      case "2":
			    System.out.println ( "\t\t ______________________________________ " );
		        System.out.println ( "\t\t|                                      |" );
		        System.out.println ( "\t\t| The Car distances between the cities |" );
		        System.out.println ( "\t\t|______________________________________|\n" );
		        printGraph(DrivingMap, cities);
		        System.out.println ( "\n\t\t _______________________________________ " );
		        System.out.println ( "\t\t|                                       |" );
		        System.out.println ( "\t\t| The Walk distances between the cities |" );
		        System.out.println ( "\t\t|_______________________________________|\n\n" );
		        printGraph(WalkingMap, cities);
		        break;
		      
		      case "3":
		    	System.out.println ( "\t\t ______________________________________ " );
			    System.out.println ( "\t\t|                                      |" );
			    System.out.println ( "\t\t|  The Heuristic 1 (Aerial distances)  |" );
			    System.out.println ( "\t\t|______________________________________|\n" );
			    System.out.print("\t\t\t");
			    display_heuristic(DrivingMap, cities, h1);
		        break;
		        
		      case "4":
		        System.out.println ( "\t\t ______________________________________ " );
				System.out.println ( "\t\t|                                      |" );
				System.out.println ( "\t\t|   The Heuristic 2 (WAlk distances)   |" );
				System.out.println ( "\t\t|______________________________________|\n" );
				System.out.print("\t\t\t");
				display_heuristic(WalkingMap, cities, h2);
		        break;	        
		        
		      case "5":
		    	String s, d;
		    	int sur = 0;
		    	int des = 0;
		    	int a, b, c;
		    	System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		        while(true){
			        display_path_menu();
					s = in.next();               			//reads input for second menu from user   	
		        	if(s.equals("1")){
		        		System.out.println("Are you Driving or Walking?");
		        		System.out.println("->If Driving enter 1");
		        		System.out.println("->If Walking enter 2");
		        		d = in.next(); 
		        		if(d.equals("1")){
		        			System.out.print("[Cities and their number]: ");
		    			    display_cities(DrivingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			DrivingMap.BFS(sur, des, record);
				        			display_record(record, DrivingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(DrivingMap) + ")\n");
			        			}
			        			else{ 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else if(d.equals("2")){
		        			System.out.print("[Cities and their number]: ");
		        			display_cities(WalkingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			WalkingMap.BFS(sur, des, record);
				        			display_record(record, WalkingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(WalkingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}	
		        			}	 
		        		}
		        		else{
			        		System.out.println("Invalid input");
		        		}
		        	}		        	
		        	if(s.equals("2")){
		        		System.out.println("Are you Driving or Walking?");
		        		System.out.println("->If Driving enter 1");
		        		System.out.println("->If Walking enter 2");
		        		d = in.next(); 
		        		if(d.equals("1")){
		        			System.out.print("[Cities and their number]: ");
		    			    display_cities(DrivingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			DrivingMap.DFS(sur, des, record);
				        			display_record(record, DrivingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(DrivingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else if(d.equals("2")){
		        			System.out.print("[Cities and their number]: ");
		        			display_cities(WalkingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			WalkingMap.DFS(sur, des, record);
				        			display_record(record, WalkingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(WalkingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else{
			        		System.out.println("Invalid input");
		        		}
		        	}		        	
		        	if(s.equals("3")){
		        		System.out.println("Are you Driving or Walking?");
		        		System.out.println("->If Driving enter 1");
		        		System.out.println("->If Walking enter 2");
		        		d = in.next(); 
		        		if(d.equals("1")){
		        			System.out.print("[Cities and their number]: ");
		    			    display_cities(DrivingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			DrivingMap.UniformCostSearch(sur, des, record);
				        			display_record(record, DrivingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(DrivingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else if(d.equals("2")){
		        			System.out.print("[Cities and their number]: ");
		        			display_cities(WalkingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			WalkingMap.UniformCostSearch(sur, des, record);
				        			display_record(record, WalkingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(WalkingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else{
			        		System.out.println("Invalid input");
		        		}
		        	}
		        	 
		        	if(s.equals("4")) {
		        		System.out.print("[Cities and their number]: ");
	        			display_cities(DrivingMap, cities);
	        			System.out.println("Plaese enter the start node:");
	        			a = in.nextInt();   
	        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
	        				sur = a;
	        			}
	        			else { 
	        				System.out.println("Invalid input");
	        			}
	        			System.out.println("Plaese enter the number of goals:");
        				b = in.nextInt();
	        			for (int i = 1 ; i <= b ; i++){
		        			System.out.println("Plaese enter the goal " +i+ ":");	  
		        			c = in.nextInt();
		        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
		        				des = c;
		        				updateHeuristic(des, h1, DrivingMap ,cities);    //Use h(n) Aerial distance
		        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
			        			DrivingMap.greedy(sur, des, record);
			        			display_record(record, DrivingMap , cities);
			        			System.out.println("[Cost]: " + record.path_cost(DrivingMap) + "\n");
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
	        			}	 
		        	}
		        	
		        	if(s.equals("5")) {
		        		System.out.println("Are you Driving or Walking?");
		        		System.out.println("->If Driving enter 1");
		        		System.out.println("->If Walking enter 2");
		        		d = in.next(); 
		        		if(d.equals("1")){
		        			System.out.print("[Cities and their number]: ");
		    			    display_cities(DrivingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex )){
			        				des = c;
			        				updateHeuristic(des, h2, DrivingMap ,cities);    //Use h(n) Walk distance
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			DrivingMap.AStar(sur, des, record);
				        			display_record(record, DrivingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(DrivingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}
		        			}	 
		        		}
		        		else if(d.equals("2")){
		        			System.out.print("[Cities and their number]: ");
		        			display_cities(WalkingMap, cities);
		        			System.out.println("Plaese enter the start node:");
		        			a = in.nextInt();   
		        			if((a >= 0) && (a <= DrivingMap.numberOfVertex )){
		        				sur = a;
		        			}
		        			else { 
		        				System.out.println("Invalid input");
		        			}
		        			System.out.println("Plaese enter the number of goals:");
	        				b = in.nextInt();
		        			for (int i = 1 ; i <= b ; i++){
			        			System.out.println("Plaese enter the goal " +i+ ":");	  
			        			c = in.nextInt();   
			        			if((c >= 0) && (c <= DrivingMap.numberOfVertex)){
			        				des = c;
			        				updateHeuristic(des, h1, WalkingMap ,cities);    //Use h(n) Aerial distance
			        				record = new Record();				//Make sure to delete the record after printing the visited and path ...etc
				        			WalkingMap.AStar(sur, des, record);
				        			display_record(record, WalkingMap , cities);
				        			System.out.println("[Cost]: (" + record.path_cost(WalkingMap) + ")\n");
			        			}
			        			else { 
			        				System.out.println("Invalid input");
			        			}		        		
		        			}	 
		        		}
		        		else{
			        		System.out.println("Invalid input");
		        		}
		        	}
		        	if(s.equals("0")) {
		        		System.out.println ( "\t\t ______________________________________ " );
						System.out.println ( "\t\t|                                      |" );
						System.out.println ( "\t\t|        >>>Back to Main Menu<<<       |" );
						System.out.println ( "\t\t|______________________________________|\n" );
						break Main;
		        	}
		        	if (!((s.equals("1")) || (s.equals("2")) || (s.equals("3")) || (s.equals("4")) || (s.equals("5")) || (s.equals("0")))){
		        		System.out.println ( "\t\t ______________________________________ " );
				    	System.out.println ( "\t\t|                                      |" );
				    	System.out.println ( "\t\t|          Unrecognized option         |" );
						System.out.println ( "\t\t|______________________________________|\n" );
				        System.out.println ( "---> Please enter available option from menu" );
		        	}
		        }
		        		        
		      case "0":
		    	System.out.println ( "\t\t ______________________________________ " );
				System.out.println ( "\t\t|                                      |" );
				System.out.println ( "\t\t|              >>>Exit<<<              |" );
				System.out.println ( "\t\t|______________________________________|\n" );
		        System.exit(0);
		        break;
		        
		      default:
		    	System.out.println ( "\t\t ______________________________________ " );
			    System.out.println ( "\t\t|                                      |" );
			    System.out.println ( "\t\t|          Unrecognized option         |" );
				System.out.println ( "\t\t|______________________________________|\n" );
			    System.out.println ( "---> Please enter available option from menu" );
		        break;
	    	}
		}
	}
	
//--------------------------------------------------------------------------------
			//Method to print the main menu
	static void display_main_menu(){
		System.out.print("\t\t ______________________________________________________________________________\n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|                     [Welcome in Palestine Paths Program]                     |\n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|>>>>>> This programming is an implementation of search algorithms for a <<<<<<|\n");
		System.out.print("\t\t|>>>> specific goal of finding an optimal path between cities in Palestine <<<<|\n");
		System.out.print("\t\t|______________________________________________________________________________|\n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|                                 [Main Menu]                                  |\n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|              1- Print the Palestinian cities (Historical Palestine).         |\n");
		System.out.print("\t\t|              2- Print the distances between the cities.                      |\n");   
		System.out.print("\t\t|              3- Print Heuristic 1 (Aerial distances).                        |\n");
		System.out.print("\t\t|              4- Print Heuristic 2 (Walk  distances).                         |\n");
		System.out.print("\t\t|              5- Find the optimal path between cities.                        |\n");
		System.out.print("\t\t|              0- Exist.                                                       |\n");
		System.out.print("\t\t|______________________________________________________________________________|\n");    
	}

//--------------------------------------------------------------------------------
			//Method to print the path menu
	static void display_path_menu(){
		System.out.print("\t\t ______________________________________________________________________________ \n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|                                 [Path Menu]                                  |\n");
		System.out.print("\t\t|                                                                              |\n");
		System.out.print("\t\t|              1- Find the path using Breadth First Search (BFS).              |\n");
		System.out.print("\t\t|              2- Find the path using Depth First Search (DFS).                |\n");
		System.out.print("\t\t|              3- Find the path using Uniform Cost Search (UCS).               |\n");   
		System.out.print("\t\t|              4- Find the path using Greedy Algorithm.                        |\n");
		System.out.print("\t\t|              5- Find the path using A* Search Algorithm.                     |\n");
		System.out.print("\t\t|              0- Back to Main Menu.                                           |\n");
		System.out.print("\t\t|______________________________________________________________________________|\n");    
	}
	
//--------------------------------------------------------------------------------
		//Method to print the cities
	static void display_cities(Graph g , String[] cities){
		for(int i = 0 ; i < g.numberOfVertex ; i++){
        	if((i == g.numberOfVertex -1) || (i == (g.numberOfVertex/2)-1)){
        		System.out.print("["+i+": "+cities[i]+"] \n");
        	}
        	else {
	    		System.out.print("["+i+": "+cities[i]+"], ");
        	}
    	}
        System.out.println();
	}
	
//--------------------------------------------------------------------------------
			//Method to print the heuristic	
	
	static void display_heuristic(Graph g, String[] cities, double [][] h){
		 for(int i = 0 ; i < g.numberOfVertex ; i++){
     		System.out.print("["+i+": "+cities[i]+"]\t");
     }
	    System.out.println();
	    for(int i = 0 ; i < g.numberOfVertex ; i++){
	    	System.out.print("["+i+": "+cities[i]+"]\t\t");
			for(int j=0 ; j<g.numberOfVertex ; j++){
					System.out.print(h[i][j]+"\t\t");
			}
			System.out.println();
	    }
	    System.out.println();	
	}
//--------------------------------------------------------------------------------
			//Method to print the record
	static void display_record(Record record, Graph g, String[] cities){
		String s;
		
		s = "\n[Path]: (";
		for(int i = 0 ; i < record.path.size() ; i++){
			if((i == record.path.size() -1)){
				s+="["+record.path.get(i)+": "+cities[record.path.get(i)]+"]) \n";
	    	}
	    	else {
	    		s+="["+record.path.get(i)+": "+cities[record.path.get(i)]+"]--> ";
	    	}
		}
		
		s += "[Visited]: (";
		for(int i = 0 ; i < record.visited.size() ; i++){
			if((i == record.visited.size() -1)){
				s+="["+record.visited.get(i)+": "+cities[record.visited.get(i)]+"]) \n";
	    	}
	    	else {
	    		s+="["+record.visited.get(i)+": "+cities[record.visited.get(i)]+"], ";
	    	}
		}
	
		s += "[Expanded]: (";
		for(int i = 0 ; i < record.expanded.size() ; i++){
			if((i == record.expanded.size() -1)){
				s+="["+record.expanded.get(i)+": "+cities[record.expanded.get(i)]+"]) \n";
	    	}
	    	else {
	    		s+="["+record.expanded.get(i)+": "+cities[record.expanded.get(i)]+"], ";
	    	}
		}
		
		System.out.print(s);
	}

//--------------------------------------------------------------------------------
			//Method to read the data from file and build graph
	static void readGraph(int optionDrivingOrWalking, File f, Graph g, String[] cities, double[][] h1, double[][] h2) throws FileNotFoundException{
		Scanner inf = new Scanner(f);
		inf.nextInt();
		String line;
		String[] tokens;
		String[] eachCityToken;
		
		for(int i=0 ; i < g.numberOfVertex ; i++){   	//to read the cities name from file
			line = inf.next();
			cities[i] = new String(line);
		}
		
		for(int i=0 ; i < g.numberOfVertex ; i++){   	//to read the areal, walk and car distances from file
			line = inf.next();
			tokens = line.split(",");
			
			for(int j=0 ; j<tokens.length ; j++){
				eachCityToken = tokens[j].split("#");
				if(optionDrivingOrWalking == 0){		//if the cost is car distances
					h1[i][j] = Double.parseDouble(eachCityToken[0]);
					h2[i][j] = Double.parseDouble(eachCityToken[1]);
					if(eachCityToken.length == 3){
						int src = i;
						int dest = j;
						c++;
						if(src>=0 && dest>=0){
							g.addEdge(src, dest, Double.parseDouble(eachCityToken[2]));
						}
					}
				}
				
				else if(optionDrivingOrWalking == 1){		//if the cost is walk distances
					if(h1 != null){
						h1[i][j]=Double.parseDouble(eachCityToken[0]);
					}
					if(eachCityToken.length == 3){
						int src = i;
						int dest = j;
						c++;
						if(src>=0 && dest>=0){
							g.addEdge(src, dest,Double.parseDouble(eachCityToken[1]));
						}
					}	
				}
			}
		}
		inf.close();
	}

//--------------------------------------------------------------------------------
			//Method to find the number of specific city
	static int findCity(String[] CityToFind, String name){
		for(int i=0 ; i < CityToFind.length ; i++) {
			if(CityToFind[i].equals(name)){
				return i;
			}
		}
		return -1;
	}

//--------------------------------------------------------------------------------
			//Method to print the graph
	static void printGraph(Graph g, String[] cities){
		for(int i=0 ; i < g.numberOfVertex ; i++){
			System.out.print("["+cities[i]+"]--> ");
			for(int j=0 ; j < g.adjList[i].size() ; j++){
				System.out.print("{"+cities[g.adjList[i].get(j).getCityNum()]+",("+g.adjList[i].get(j).getDistance()+")} ");
			}
			System.out.println();
			
		}
	}
	
//--------------------------------------------------------------------------------
			//To update and save the heuristic from source to destination (use it in Greedy and A* algorithms)
	static void updateHeuristic(int destination, double[][] heuristic, Graph g, String[] cities){    
		for(int i = 0 ; i < g.numberOfVertex ; i++) {
			g.heuristic[i] = heuristic[i][destination];
		}
		System.out.print("[Heuristic from cities to " +cities[destination]+"]: (");
		for(int i = 0 ; i < g.numberOfVertex ; i++){		//Print the heuristic
			System.out.print(cities[i]+":{"+g.heuristic[i]+"}"+" ");
		}
		System.out.print(") \n\n");
		
	}
	
}
