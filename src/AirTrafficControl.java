
public class AirTrafficControl {
	private String name;
	private String hashName;
	private MyHeap readyQ;
	private int counter=0;
	private int objTime=0;
	public AirTrafficControl(String name, MyHeap readyQ) {
		super();
		this.name = name;
		this.readyQ = readyQ;
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getObjTime() {
		return objTime;
	}

	public void setObjTime(int objTime) {
		this.objTime = objTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHashName() {
		return hashName;
	}
	public void setHashName(String hashName) {
		this.hashName = hashName;
	}
	public MyHeap getReadyQ() {
		return readyQ;
	}
	public void setReadyQ(MyHeap readyQ) {
		this.readyQ = readyQ;
	}
	
}
