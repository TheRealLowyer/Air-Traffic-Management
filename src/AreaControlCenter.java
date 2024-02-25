import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

public class AreaControlCenter {
	private String name;
	int opTime=0;
	private ArrayList<AirTrafficControl> atc;
	private ArrayList<String> airTChash;
	private ArrayList<String> list;
	private ArrayList<Flight> flights;
	private MyHeap readyQ;
	private Hashtable<Integer, String> ht;
	public AreaControlCenter(String name, ArrayList<AirTrafficControl> airport,ArrayList<String> list, MyHeap readyQ) {
		super();
		this.name = name;
		this.atc = airport;
		this.setList(list);
		this.readyQ = readyQ;
		
	}
	
	public int getOpTime() {
		return opTime;
	}

	public void setOpTime(int opTime) {
		this.opTime = opTime;
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	public Hashtable<Integer, String> getHt() {
		return ht;
	}
    
	public int getTime() {
		return opTime;
	}

	public void setTime(int opTime) {
		this.opTime = opTime;
	}

	public void setHt(Hashtable<Integer, String> ht) {
		this.ht = ht;
	}

	public ArrayList<String> getAirTChash() {
		return airTChash;
	}

	public void setAirTChash(ArrayList<String> airTChash) {
		this.airTChash = airTChash;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<AirTrafficControl> getAirport() {
		return atc;
	}
	public void setAirport(ArrayList<AirTrafficControl> airport) {
		this.atc = airport;
	}
	public MyHeap getReadyQ() {
		return readyQ;
	}
	public void setReadyQ(MyHeap readyQ) {
		this.readyQ = readyQ;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
}
