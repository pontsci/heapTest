package heapTest;

import java.util.Arrays;

public class Heap 
{
	private int heapSize, capacity, d;
	private int[] heap;
	
	public Heap(int cap, int dInt, int[] arr)
	{
		capacity = cap;
		d = dInt;
		heapSize = cap;
		heap = arr;
	}
	
	public void insert(int num)
	{
		if(isFull())
		{
			int[] tmp = new int[capacity * 2];
			System.arraycopy(heap, 0, tmp, 0, capacity);
			heap = tmp;
			//System.out.println("TMP: " + Arrays.toString(tmp));
			//System.out.println("HEAP: " + Arrays.toString(heap));
			//heap[capacity * 2 - 1] = 1;
			//System.out.println("HEAP after addition: " + Arrays.toString(heap));
		}
	}
	
	private int buildHeap()
	{
		return 1;
	}
	
	private int parentIndex(int i)
	{
		return Math.floorDiv(i - 1, d);
	}
	
    private int kthChild(int i, int k) 
    {
        return d * i + k;
    }
	
	public boolean isEmpty()
	{
		return heapSize == 0;
	}
	
	public boolean isFull()
	{
		return heapSize == heap.length;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public int getD()
	{
		return d;
	}
	
	public int getHeapSize()
	{
		return heapSize;
	}
	
	@Override
	public String toString(){
		return "Output: Heap (d=" + d + "):" + Arrays.toString(heap);
	}
}
