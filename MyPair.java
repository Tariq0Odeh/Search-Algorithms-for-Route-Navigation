
public class MyPair implements Comparable<MyPair>{  //To use it in Greedy algorithm and Uniform Cost Search in implementation
	private int cityNum;
	private double distance;
	
	public MyPair(int cityNum, double distance){
		super();
		this.cityNum = cityNum;
		this.distance = distance;
	}
	
	public int getCityNum(){
		return cityNum;
	}

	public void setCityNum(int cityNum){
		this.cityNum = cityNum;
	}

	public double getDistance(){
		return distance;
	}

	public void setDistance(double distance){
		this.distance = distance;
	}

	@Override
	public int compareTo(MyPair o){
		if(this.distance > o.distance){
			return 1;
		}
		else if(this.distance == o.distance){
			return 0;
		}
		return -1;
	}
	
	public String toString(){
		String s = "("+cityNum+","+distance+")";
		return s;
	}
	
}
