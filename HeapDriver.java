package heapTest;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HeapDriver {
	public static void main(String[] args) 
	{
		int cap;	//capacity
		int d, i = 0;
		int[] num;
		String userIn;	
		String[] numStr;
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("Enter heap elements: ");
			userIn = sc.nextLine();
			numStr = userIn.split(" ");
			cap = numStr.length;
			num = new int[cap];
			System.out.println(cap);
			try{
				for(String s : numStr)
				{
					num[i] = Integer.parseInt(s);			
					i++;
				}
			
				if(i == cap){
					System.out.println(Arrays.toString(num));
					break;
				}
			}
			catch(NumberFormatException e){
			System.err.print("Not all inputs were integers.\n");
			i = 0;
			cap = 0;
			num = null;
			numStr = null;
			}
		}
		i = 0;
		while(true)
		{
			System.out.print("Enter d: ");
			
			try
			{
				d = sc.nextInt();
				if (d < 2)
				{
					System.err.print("Integer must be greater than or equal to 2.\n");
					continue;
				}
				break;
			}
			catch(InputMismatchException e)
			{
				System.err.print("Input was not an integer.\n");
			}
			sc.nextLine();
		}
		sc.close();
		
		//create Heap
		Heap h1 = new Heap(cap, d, num);
		System.out.println(h1.toString());
	}
}
