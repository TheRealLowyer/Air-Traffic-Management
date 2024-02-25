import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.lang.Math;
import java.lang.reflect.Array;


public class Project3 {
	public static void main(String args[]) {
		 ArrayList<AreaControlCenter> aC =new ArrayList<AreaControlCenter>();
		 ArrayList<Flight> ff =new ArrayList<Flight>();
		 try {
		        File myObj = new File(args[0]);
		        Scanner myReader = new Scanner(myObj);
		        try {
			        FileWriter myWriter= new FileWriter(args[1]);
			        while (myReader.hasNextLine()) {
			          String data = myReader.nextLine();
			          String[]input= data.split(" ");
			          int numberAcc=0;
			          int numberF=0;
			          
			          if (input.length==2) {
			        	  numberAcc=Integer.parseInt(input[0]);
			        	  numberF=Integer.parseInt(input[1]);
			          }
			          else if (input[0].length()==4 && input.length>2 &&input[1].length()==3 ) {
			        	  ArrayList<AirTrafficControl> k =new ArrayList<AirTrafficControl>();
			        	  ArrayList<String> list =new ArrayList<String>();
			        	  for(int i=1; i<input.length;i++) {
			        		  MyHeap ilk= new MyHeap(2);	
			        		  AirTrafficControl m= new AirTrafficControl(input[i],ilk);
			        		  k.add(m);
			        		  list.add(input[i]);
			        		  
			        	  }
			        	  AreaControlCenter s=new AreaControlCenter(input[0], k,list, null);
			        	  aC.add(s);			        	  
			          }
			          else {
			        	  ArrayList<Integer> m =new ArrayList<Integer>();
			        	  for(int i=5; i<input.length;i++) {
			        		  m.add(Integer.parseInt(input[i]));
			        	  }
			        	  AirTrafficControl departure=null;
			        	  AirTrafficControl landing=null;
			        	  for(AreaControlCenter i:aC) {
			        		  ArrayList<AirTrafficControl> k =i.getAirport();
			        		  for(AirTrafficControl atc:k) {
			        			  if(atc.getName().equals(input[3])) {
			        				  departure=atc;
			        			  }
			        			  if(atc.getName().equals(input[4])) {
			        				  landing=atc;
			        			  }			        				  
			        		  }
			        	  }
			        	  Flight f= new Flight(Integer.parseInt(input[0]),input[1],input[2],departure,landing,m,0);
			        	  ff.add(f);
			          }   	  
			        }
			        for(AreaControlCenter i:aC) {
			        	ArrayList<Flight> fl= new ArrayList<Flight>();
			        	for(Flight f:ff) {
			        		if(i.getName().equals(f.getAccCode())) {
			        			fl.add(f);
			        		}
			        	}
			        	i.setFlights(fl);
			        }
			        for(AreaControlCenter i:aC) {
						 ArrayList<String> hList = i.getList();
						 Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
						 ArrayList<String> airTChash= new ArrayList<String>();
						 for(String s:hList) {
							 int k=hashing(s);							 
							 int code= addHash(k,s,ht);
							 i.setHt(ht);
							 if(code<10) {
								 String airportCode=s+"00"+Integer.toString(code);
								 airTChash.add(airportCode);
							 }
							 else if(code>=10 &&code<100) {
								 String airportCode=s+"0"+Integer.toString(code);
								 airTChash.add(airportCode);
							 }
							 else {
								 String airportCode=s+Integer.toString(code);
								 airTChash.add(airportCode);
							 }
						 }
						 i.setAirTChash(airTChash);
				   }			        
			        for(AreaControlCenter i:aC) {
			        	int t=0;
			        	int QueueSize=(i.getFlights()).size();			        
			        	MyHeap geek= new MyHeap(QueueSize);			        	
			        	for(Flight ucus:i.getFlights()) {
			        		geek.add(ucus);
			        	}
			        	while(geek.getSize()!=0) {
			    			
			        		Flight min= geek.findMin();
			        		ArrayList<Integer> ops= min.getOperations();
			        		double index=min.getOpIndex();
			        		
			        		if(index==0) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(2);
			            			min.setEnter(t+ops.get(1));
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(0.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}    			
			        		}
			        		else if(index==0.5) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(2);
			            			min.setDecimal(true);
			            			min.setEnter(t+ops.get(1));
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(0.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}    			
			        		}
			        		else if(index==2 ) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			        				min.setOpIndex(3);
			        				min.setDecimal(true);
			            			min.setEnter(t);
			        				geek.deleteMin();
			        				geek.add(min);
			        				continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;        			
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(2.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}    			
			        		}
			        		else if(index==2.5) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);     			
			            			min.setOpIndex(3);
			            			min.setDecimal(true);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(2.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}    			
			        		}
			        		else if(index==3) {
			        			AirTrafficControl ATC= min.getDepartureAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(3)+ops.get(4));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(3));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(3)+ops.get(4));
			        				ATC.setObjTime(atcTime+ops.get(3));
			        			}
			        			min.setOpIndex(5);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
		            			continue;
			        		}
			        		else if(index==5) {
			        			AirTrafficControl ATC= min.getDepartureAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(5)+ops.get(6));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(5));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(5)+ops.get(6));
			        				ATC.setObjTime(atcTime+ops.get(5));
			        			}
			        			min.setOpIndex(7);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==7) {
			        			AirTrafficControl ATC= min.getDepartureAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(7)+ops.get(8));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(7));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(7)+ops.get(8));
			        				ATC.setObjTime(atcTime+ops.get(7));
			        			}
			        			min.setOpIndex(9);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==9) {
			        			AirTrafficControl ATC= min.getDepartureAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(9));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(9));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(9));
			        				ATC.setObjTime(atcTime+ops.get(9));
			        			}
			        			min.setOpIndex(10);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==10) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(12);
			            			min.setDecimal(true);
			            			min.setEnter(t+ops.get(11));
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(10.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        		}
			        		else if (index==10.5) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(12);
			            			min.setDecimal(true);
			            			min.setEnter(t+ops.get(11));
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(10.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        		}
			        		else if(index==12) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			        				min.setOpIndex(13);
			        				min.setDecimal(true);
			            			min.setEnter(t);
			        				geek.deleteMin();
			        				geek.add(min);
			        				continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;        			
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(12.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			} 
			        		}
			        		else if (index==12.5) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);     			
			            			min.setOpIndex(13);
			            			min.setDecimal(true);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(12.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        		}
			        		else if(index==13) {
			        			AirTrafficControl ATC= min.getLandingAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(13)+ops.get(14));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(13));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(13)+ops.get(14));
			        				ATC.setObjTime(atcTime+ops.get(13));
			        			}
			        			min.setOpIndex(15);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
		            			continue;
			        		}
			        		else if(index==15) {
			        			AirTrafficControl ATC= min.getLandingAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(15)+ops.get(16));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(15));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(15)+ops.get(16));
			        				ATC.setObjTime(atcTime+ops.get(15));
			        			}
			        			min.setOpIndex(17);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==17) {
			        			AirTrafficControl ATC= min.getLandingAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(17)+ops.get(18));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(17));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(17)+ops.get(18));
			        				ATC.setObjTime(atcTime+ops.get(17));
			        			}
			        			min.setOpIndex(19);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==19) {
			        			AirTrafficControl ATC= min.getLandingAirport();
			        			MyHeap atcHeap=ATC.getReadyQ();
			        			atcHeap.add(min);
			        			int enterenceOfFlight= min.getEnter();
			        			int counter=ATC.getCounter();
			        			int atcTime=ATC.getObjTime();
			        			if(counter==0) {
			        				ATC.setObjTime(enterenceOfFlight);
			        				ATC.setCounter(1);
			        			}
			        			if(atcTime<=enterenceOfFlight) {
			        				min.setEnter(enterenceOfFlight+ops.get(19));
			        				ATC.setObjTime(enterenceOfFlight+ops.get(19));
			        			}
			        			if(atcTime>enterenceOfFlight) {
			        				min.setEnter(atcTime+ops.get(19));
			        				ATC.setObjTime(atcTime+ops.get(19));
			        			}
			        			min.setOpIndex(20);
			        			atcHeap.deleteMin();
			        			geek.deleteMin();
		            			geek.add(min);
			        			continue;
			        		}
			        		else if(index==20) {
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(21);
			            			min.setDecimal(true);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(20.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        		}
			        		else if(index==20.5){
			        			if(ops.get((int)index)<=30) {
			        				if(min.getEnter()<t)
			        					t+=ops.get((int) index);
			        				else
			        					t=min.getEnter()+ops.get((int) index);
			            			min.setOpIndex(21);
			            			min.setDecimal(true);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        			else {
			        				if(min.getEnter()<t)
			        					t+=30;
			        				else	
			        					t=min.getEnter()+30;
			            			ops.set((int) index, ops.get((int) index)-30);
			            			min.setOpIndex(20.5);
			            			min.setDecimal(false);
			            			min.setEnter(t);
			            			geek.deleteMin();
			            			geek.add(min);
			            			continue;
			        			}
			        		}
			        		else if(index==21) {
			        			geek.deleteMin();
			        		}			        					        		
			    		}
			        	i.setTime(t);			        	
			        }
			        for(AreaControlCenter i:aC) {
			        	ArrayList<String> AirportCodes= i.getAirTChash();
			        	ArrayList<String> hashval= new ArrayList<String>();
			        	ArrayList<String> lastone= new ArrayList<String>();
			        	for(String k:AirportCodes) {
			        		char m=k.charAt(3);
			        		char l=k.charAt(4);
			        		char p=k.charAt(5);
			        		String s=String.valueOf(m)+String.valueOf(l)+String.valueOf(p);
			        		hashval.add(s);
			        	}
			        	Collections.sort(hashval);
			        	for(int e=0;e<hashval.size();e++) {
			        		lastone.add("d");
			        	}
			        	for(String k:AirportCodes) {
			        		char m=k.charAt(3);
			        		char l=k.charAt(4);
			        		char p=k.charAt(5);
			        		String s=String.valueOf(m)+String.valueOf(l)+String.valueOf(p);
			        		int index= hashval.indexOf(s);
			        		lastone.set(index, k);
			        	}
			        	i.setAirTChash(lastone);
			        }
			        for(AreaControlCenter i:aC) {
			        	myWriter.write(i.getName()+" ");
			        	myWriter.write(i.getOpTime()+" ");			        	
			        	for(String k:i.getAirTChash()) {
			        		myWriter.write(k+" ");
			        	}
			        	myWriter.write("\n");
			        }
			       myWriter.close();   
				 } catch (IOException e) {
				    e.printStackTrace();
			     }		        
           myReader.close();		 
		 } catch (FileNotFoundException e) {
		        e.printStackTrace();
		 }
	}
	public static int hashing(String s) {
		int hashVal=0;
		for(int i =0; i<s.length();i++) {
			hashVal+=Math.pow(31, i)*(int)s.charAt(i);
		}
		return hashVal;
		
	}
	public static int addHash(int val,String c,Hashtable<Integer, String> ht) {
		if (ht.get(val%1000) == null) {
			ht.put(val%1000,c);
			return val%1000;
        }
		else {
			return addHash(val+1,c,ht);
		}
	}

}
