import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyHeap {
	private int capacity;
	private Flight[] heap;
	private int heapSize=0;
	public MyHeap(int capacity) {
		this.capacity=capacity;
		heap = new Flight[capacity];
	}
	public boolean isEmpty(){
        return heapSize==0;
    }
	private int getParentIndex(int childIndex) {
	    return (childIndex - 1) / 2;
	  }
	private boolean hasParent(int index) {
	    return getParentIndex(index) >= 0;
	  }
	public boolean isFull(){
        return heapSize == heap.length;
    }
	private int parent(int i){
        return (i-1)/2;
    }
	public int getSize() {
		return heapSize;
	}
	private int kthChild(int i,int k){
        return 2*i  +k;
    }
	public void add(Flight x){
        if(isFull()) {
        	Flight[] newArray= new Flight[2*heapSize];
        	for(int i=0;i<heap.length;i++) {
        		newArray[i]=heap[i];
        	}
        	heap=newArray;
        }
            
        heap[heapSize] = x;
        heapSize++;
        heapifyUp();
    }
	private void heapifyUp() {
		int i= heapSize-1;
        Flight temp = heap[i];
        while(i>0 && comparator(heap[parent(i)],temp)==-1){
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = temp;
	}
	public void deleteMin( )
    {
        

        Flight minItem = findMin( );
        heap[ 0 ] = heap[ heapSize-1 ];
        heapSize--;
        heapifyDown(0);

    }
	private void heapifyDown(int ind)
    {
        int child;
        Flight tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (comparator(heap[child], tmp)==1)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }
	private int minChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= 2) && (pos < heapSize)) 
        {
            if (comparator(heap[pos], heap[bestChild])==1) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
    public Flight findMin(){
       if(isEmpty())
//          throw new NoSuchElementException("Heap is empty.");
    	   return null;
       return heap[0];
    }
    public void printHeap()
    {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i].getFlightCode() +" ");
        System.out.println();
    }
	public static int comparator(Flight a,Flight b) {
		if(a.getEnter()<b.getEnter()) 
			return 1;		
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=0&& a.getOpIndex()<3&& b.getOpIndex()>=0 &&b.getOpIndex()<3) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=0&& a.getOpIndex()<3&& b.getOpIndex()>=10 &&b.getOpIndex()<13) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=0&& a.getOpIndex()<3&& b.getOpIndex()>=20 &&b.getOpIndex()<21) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=10&& a.getOpIndex()<13&& b.getOpIndex()>=0 &&b.getOpIndex()<3) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=10&& a.getOpIndex()<13&& b.getOpIndex()>=10 &&b.getOpIndex()<13) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=10&& a.getOpIndex()<13&& b.getOpIndex()>=20 &&b.getOpIndex()<21) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=20&& a.getOpIndex()<21&& b.getOpIndex()>=0 &&b.getOpIndex()<3) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=20 && a.getOpIndex()<21&& b.getOpIndex()>=10 &&b.getOpIndex()<13) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}			
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()>=20 && a.getOpIndex()<21&& b.getOpIndex()>=20 &&b.getOpIndex()<21) {
			if(a.isDecimal()==true && b.isDecimal()==false)
				return 1;
			else if (a.isDecimal()==false && b.isDecimal()==true)
				return -1;
			else {
				if(a.getFlightCode().compareTo(b.getFlightCode())<0)
					return 1;
				else 
					return-1;
			}
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()<=9 && a.getOpIndex()>=3 && b.getOpIndex()<=9 && b.getOpIndex()>=3) {
			if(a.getFlightCode().compareTo(b.getFlightCode())<0)
				return 1;
			else 
				return -1;
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()<=9 && a.getOpIndex()>=3 && b.getOpIndex()<=19 && b.getOpIndex()>=13) {
			if(a.getFlightCode().compareTo(b.getFlightCode())<0)
				return 1;
			else 
				return-1;
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()<=19 && a.getOpIndex()>=13 && b.getOpIndex()<=9 && b.getOpIndex()>=3) {
			if(a.getFlightCode().compareTo(b.getFlightCode())<0)
				return 1;
			else 
				return-1;
		}
		else if(a.getEnter()==b.getEnter()&& a.getOpIndex()<=19 && a.getOpIndex()>=13&& b.getOpIndex()<=19 && b.getOpIndex()>=13) {
			if(a.getFlightCode().compareTo(b.getFlightCode())<0)
				return 1;
			else 
				return-1;
		}		
		else 
			return -1;				
	}
}
