package heapTest;

import java.util.Arrays;

public class Heap 
{
	private int numElements, capacity, d;
	private int[] heap;
	
	public Heap(int cap, int dInt, int[] arr)
	{
		capacity = cap;
		d = dInt;
		numElements = cap;
		heap = arr;
	}
	
	public void insert(int num)
	{
		if(isFull())
		{
			int[] tmp = new int[capacity + 1];
			System.arraycopy(heap, 0, tmp, 0, capacity);
			capacity++;
			heap = tmp;
			//System.out.println("TMP: " + Arrays.toString(tmp));
			//System.out.println("HEAP: " + Arrays.toString(heap));
			//heap[capacity * 2 - 1] = 1;
			//System.out.println("HEAP after addition: " + Arrays.toString(heap));
		}
        heap[numElements++] = num;
        percUp(numElements - 1);
		
	}
	
	public int deleteMin()
	{
        if(isEmpty())
        {
        	
        }
        int deleted = heap[0];
        heap[0] = heap[numElements - 1];
        numElements--;
		int[] tmp = new int[capacity - 1];
		System.arraycopy(heap, 0, tmp, 0, capacity - 1);
		capacity--;
		heap = tmp;
        percDown(0);
        return deleted;
	}
	
    private int percUp(int cIndex)
    {
        int tmpNode = heap[cIndex];
        while (cIndex > 0 && tmpNode < heap[parentIndex(cIndex)])
        {
            heap[cIndex] = heap[parentIndex(cIndex)];
            cIndex = parentIndex(cIndex);
        }                   
        heap[cIndex] = tmpNode;
        return 1;
    }
	
	private int percDown(int index)
	{
		int childToChk;
		int tmpNode = heap[index];
		while(kthChildIndex(index, 1) < numElements)
		{
			childToChk = minimumChild(index);
			if(tmpNode > heap[childToChk])
			{
				heap[index] = heap[childToChk];
			}
			else
			{
				break;
			}
			index = childToChk;
		}
		heap[index] = tmpNode;
		return 1;
	}
	
	private int minimumChild(int index)
	{
		int minChild = kthChildIndex(index, 1);
		int k = 2;
		int indPos = kthChildIndex(index, k);
		while(k <= d + 1 && indPos < numElements)
		{
			if(heap[indPos] < heap[minChild])
			{
				minChild = indPos;
			}
			indPos = kthChildIndex(index, k++);
		}
		return minChild;
	}
	
	public int buildHeap()
	{
        for(int i = numElements / d; i > -1; i--)
        {
        	System.out.println(Arrays.toString(heap));
        	percDown(i);
        }
		return 1;
	}
	
	private int parentIndex(int i)
	{
		return Math.floorDiv(i - 1, d);
	}
	
    private int kthChildIndex(int i, int k) 
    {
        return d * i + k;
    }
	
	public boolean isEmpty()
	{
		return numElements == 0;
	}
	
	public boolean isFull()
	{
		return numElements == heap.length;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public int getD()
	{
		return d;
	}
	
	public int getNumElements()
	{
		return numElements;
	}
	
	public void setD(int dVal)
	{
		d = dVal;
	}
	
	@Override
	public String toString(){
		return "Output: Heap (d=" + d + "):" + Arrays.toString(heap);
	}
}