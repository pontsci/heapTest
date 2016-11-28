import java.util.Arrays;

public class Heap 
{
	private int numElements, capacity, d;
	private int[] heap;
	
	//Constructor
	public Heap(int cap, int dInt, int[] arr)
	{
		capacity = cap;
		d = dInt;
		numElements = cap;
		heap = arr;
	}
	
	/********************************************************
	*  FUNCTION  insert :        	    			*
	*    insert a new element into the heap.         	*
	*  INPUT PARAMETERS : num, the number to be inserted	*    
	*  OUTPUT : VOID 		          		*    
	********************************************************/
	public void insert(int num)
	{
		if(isFull()) // if full, expand array by 1
		{
			int[] tmp = new int[capacity + 1];
			System.arraycopy(heap, 0, tmp, 0, capacity);
			capacity++;
			heap = tmp;
		}
        heap[numElements++] = num; 	// add element num to end of heap
        percUp(numElements - 1); 	// percolate up the heap from index of recently added element
		
	}
	
	/********************************************************
	*  FUNCTION  deleteMin :        	   		*
	*    removes the min element of the heap         	*
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : Returns the deleted element. 		*    
	********************************************************/
	public int deleteMin()
	{
        if(isEmpty()) 	//if empty, return 0
        {
        	return 0;
        }
        
        int deleted = heap[0]; 	// set deleted to the removed element in heap.
        
        // special case for numElements at 1, doesn't percolateDown to avoid errors
        if(numElements == 1){	
            heap[0] = heap[numElements - 1]; // sets root to last element
            numElements--;
    		int[] tmp = new int[capacity - 1]; // create a tmp array smaller than heap
    		System.arraycopy(heap, 0, tmp, 0, capacity - 1); // copy elements into tmp
    		capacity--;
    		heap = tmp; // set heap to tmp, now heap is +1 length with same elements.
        }
        else
        {
        	//code here is exact copy of above's, however it will percolate down from the root
	        heap[0] = heap[numElements - 1]; 
	        numElements--;
			int[] tmp = new int[capacity - 1];
			System.arraycopy(heap, 0, tmp, 0, capacity - 1);
			capacity--;
			heap = tmp;
	        percDown(0);
        }
        return deleted; // returns deleted element.
	}
	
	/****************************************************************
	*  FUNCTION  percUp :        	    				*
	*    percolates up the heap, following minHeap rules    	*
	*  INPUT PARAMETERS : cIndex, the index to begin percolating at	*    
	*  OUTPUT : returns 1 on successful run. 		        *    
	****************************************************************/
    private int percUp(int cIndex)
    {
        int tmpNode = heap[cIndex]; // create a tmpNode for checking against
        
        //loop until we've either gotten to the root, or until our tmpNode is greater than the parent node.
        while (cIndex > 0 && tmpNode < heap[parentIndex(cIndex)])
        {
        	// set child node to the parent node
            heap[cIndex] = heap[parentIndex(cIndex)]; 
            // change our index to the parent index
            cIndex = parentIndex(cIndex); 
        }
        // after the loop ends, we should be in the correct index for our tmpNode
        // we set our position in the heap to tmpNode's value.
        heap[cIndex] = tmpNode;
        return 1;
    }
	
	/****************************************************************
	*  FUNCTION  percDown :        	    				*
	*    percolates down the heap, following minHeap rules     	*
	*  INPUT PARAMETERS : index, the index to begin percolating at	*    
	*  OUTPUT : returns 1 on successful run.		        *    
	****************************************************************/
	private int percDown(int index)
	{
		int childToChk; // Child to check
		int tmpNode = heap[index];
		// loop while our index is within the upper bounds of our array
		while(kthChildIndex(index, 1) < numElements)
		{
			// find the minChild of our index.
			childToChk = minimumChild(index);
			
			// if tmpNode is greater than our child, set our index's val to child's val
			if(tmpNode > heap[childToChk])
			{
				heap[index] = heap[childToChk];
			}
			else
			{
				// break the loop, as our position is the correct position for our tmpNode
				break;
			}
			// move our index to the child's index we just checked
			index = childToChk;
		}
		// place our value we pulled from the beginning into it's correct position
		heap[index] = tmpNode;
		return 1;
	}
	
	/********************************************************
	*  FUNCTION  minimumChild :        	    		*
	*    find the minimum child of the given index         	*
	*  INPUT PARAMETERS : index, the index of the parent	*    
	*  OUTPUT : returns the minimum child			*    
	********************************************************/
	private int minimumChild(int index)
	{
		// set our minimum child as the first child of our given index.
		int minChild = kthChildIndex(index, 1);
		// set our counter, k, to 2, and begin looping from the second child
		int k = 2;
		// set our index position to the second child of our given index
		int indPos = kthChildIndex(index, k);
		
		// loop through all the children, or until we've hit our upper bounds on our array
		while(k <= d + 1 && indPos < numElements)
		{
			// check our minimum vs our current position, swap if current < minimum
			if(heap[indPos] < heap[minChild])
			{
				minChild = indPos;
			}
			// move our index position to the next child
			indPos = kthChildIndex(index, k++);
		}
		return minChild;
	}
	
	/****************************************************************************
	*  FUNCTION  buildHeap :						    *
	*    builds the heap by percolating down every parent node with the given d *
	*  INPUT PARAMETERS : NONE                                                  *    
	*  OUTPUT : returns 1 on successful run.                                    *    
	****************************************************************************/
	public int buildHeap()
	{
        for(int i = numElements / d; i > -1; i--)
        {
        	percDown(i);
        }
		return 1;
	}
	
	/********************************************************
	*  FUNCTION  parentIndex :        	    		*
	*    calculates the given index's parent node      	*
	*  INPUT PARAMETERS : i, the child index		*    
	*  OUTPUT : returns the parent index of the given index.*    
	********************************************************/
	private int parentIndex(int i)
	{
		return Math.floorDiv(i - 1, d);
	}
	
	/************************************************************************************
	*  FUNCTION  kthChildIndex :							    *
	*    calculates the index of the given k value, child of parent			    *
	*  INPUT PARAMETERS : i, the index, k, the requested kth child of i.		    *    
	*  OUTPUT : returns the child index of the corresponding k value and given index    *    
	************************************************************************************/
    private int kthChildIndex(int i, int k) 
    {
        return d * i + k;
    }
	
	/********************************************************
	*  FUNCTION  isEmpty :        	    			*
	*    checks if the heap is empty		        *
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns true if empty, false if !empty	*    
	********************************************************/
	public boolean isEmpty()
	{
		return numElements == 0;
	}
	
	/********************************************************
	*  FUNCTION  isFull :        	    			*
	*    checks if the heap is full			        *
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns true if full, false if !full	*    
	********************************************************/
	public boolean isFull()
	{
		return numElements == heap.length;
	}
	
	/********************************************************
	*  FUNCTION  getCapacity :     	    			*
	*    returns the capacity variable		        *
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns the capacity variable		*    
	********************************************************/
	public int getCapacity()
	{
		return capacity;
	}
	
	/********************************************************
	*  FUNCTION  getD :     	    			*
	*    returns the d variable		         	*
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns the d variable			*    
	********************************************************/
	public int getD()
	{
		return d;
	}
	
	/********************************************************
	*  FUNCTION  getNumElements :     	    		*
	*    returns the numElements variable			*
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns the numElemenets variable		*    
	********************************************************/
	public int getNumElements()
	{
		return numElements;
	}
	
	/********************************************************
	*  FUNCTION  setD :     		    		*
	*    sets a new d value	according to given parameter    *
	*  INPUT PARAMETERS : dVal, the new d value		*    
	*  OUTPUT : VOID					*    
	********************************************************/
	public void setD(int dVal)
	{
		d = dVal;
	}
	
	/********************************************************
	*  FUNCTION  toString :     	    			*
	*    puts the heap into a readable string form		*
	*  INPUT PARAMETERS : NONE				*    
	*  OUTPUT : returns the string to be output,	 	*
	*  returns empty if heap is empty.			*    
	********************************************************/
	@Override
	public String toString(){
		String formattedString = Arrays.toString(heap);
		formattedString = formattedString.replaceAll(",", "");
		formattedString = formattedString.replace("[", " ");
		formattedString = formattedString.replace("]", "");
		if(numElements != 0)
		{
		return "Output: Heap (d=" + d + "):" + formattedString;
		}
		else
		{
			return "Output: Heap (d=" + d + "):" + " (empty)";
		}
	}
}
