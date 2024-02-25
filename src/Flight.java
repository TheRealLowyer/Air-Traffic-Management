import java.util.ArrayList;

public class Flight {
	private boolean decimal=true;
	private int enter;
	private String flightCode;
	private String AccCode;
	private AirTrafficControl departureAirport;
	private AirTrafficControl  landingAirport;
	private ArrayList<Integer> operations;
	private double opIndex;

	public Flight(int enter, String flightCode, String accCode, AirTrafficControl  departureAirport, AirTrafficControl  landingAirport,
			ArrayList<Integer> operations,int opIndex) {
		this.enter = enter;
		this.flightCode = flightCode;
		this.AccCode = accCode;
		this.departureAirport = departureAirport;
		this.landingAirport = landingAirport;
		this.operations = operations;
		this.opIndex=opIndex;
	}
	
	public boolean isDecimal() {
		return decimal;
	}

	public void setDecimal(boolean decimal) {
		this.decimal = decimal;
	}

	public double getOpIndex() {
		return opIndex;
	}
	public void setOpIndex(double opIndex) {
		this.opIndex = opIndex;
	}
	public int getEnter() {
		return enter;
	}
	public void setEnter(int enter) {
		this.enter = enter;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getAccCode() {
		return AccCode;
	}
	public void setAccCode(String accCode) {
		AccCode = accCode;
	}
	public AirTrafficControl  getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(AirTrafficControl  departureAirport) {
		this.departureAirport = departureAirport;
	}
	public AirTrafficControl  getLandingAirport() {
		return landingAirport;
	}
	public void setLandingAirport(AirTrafficControl  landingAirport) {
		this.landingAirport = landingAirport;
	}
	public ArrayList<Integer> getOperations() {
		return operations;
	}
	public void setOperations(ArrayList<Integer> operations) {
		this.operations = operations;
	}
	
}
