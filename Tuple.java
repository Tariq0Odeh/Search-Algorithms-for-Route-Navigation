
public class Tuple implements Comparable<Tuple>{    //To use it in A* algorithm in implementation
	private int CityNum;
	private double sumOfDistances;			
	private double heuristic;
	
	public Tuple(int cityNum, double sumOfDistances, double heuristic){
		super();
		CityNum = cityNum;
		this.sumOfDistances = sumOfDistances;
		this.heuristic = heuristic;
	}
	
	public int getCityNum(){
		return CityNum;
	}
	
	public void setCityNum(int cityNum){
		CityNum = cityNum;
	}
	
	public double getSumOfDistances(){
		return sumOfDistances;
	}
	
	public void setSumOfDistances(double sumOfDistances){
		this.sumOfDistances = sumOfDistances;
	}
	public double getHeuristic(){
		return heuristic;
	}
	
	public void setHeuristic(double heuristic){
		this.heuristic = heuristic;
	}
	
	@Override
	public int compareTo(Tuple o){
		if((this.getHeuristic() + this.getSumOfDistances()) > (o.getHeuristic() + o.getSumOfDistances())){ 
			return 1;
		}
		else if((this.getHeuristic() + this.getSumOfDistances()) == (o.getHeuristic() + o.getSumOfDistances())){
			return 0;
		}
		return -1;
	}
	
	public String toString(){
		String s = "("+this.getCityNum()+","+this.getSumOfDistances()+","+this.getHeuristic()+")";
		return s;
	}
	
}
