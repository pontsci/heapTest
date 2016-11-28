package heapTest;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HeapDriver {
	public static void main(String[] args) 
	{
		int cap;	//capacity
		int choice, d, i = 0;
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
				sc.nextLine();
			}
			
		}
		
		Heap h1 = new Heap(cap, d, num);
		h1.buildHeap();
		System.out.println(h1.toString());
		choice = 0;
		while(choice != 4)
		{
			System.out.print("Press 1) for insert, 2) for deleteMin, 3) for new d value, 4) to quit\n");
			try
			{
				System.out.print("Enter choice: ");
				choice = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.err.print("Input was not an integer.\n");
				sc.nextLine();
				continue;
			}
			
			switch (choice){
			case 1:
				while(true)
				{
					System.out.print("Enter element to insert: ");
					try 
					{
						h1.insert(sc.nextInt());
						System.out.println(h1.toString());
						break;
					} 
					catch (InputMismatchException e) 
					{
						System.err.print("Input was not an integer.\n");
						sc.nextLine();
						continue;
					}
				}
				break;

			case 2:
				if(h1.deleteMin() == 0 && h1.getNumElements() == 0)
				{
					System.out.print("Heap is empty.\n");
				}
				else
				{
					System.out.println(h1.toString());
				}

				break;

			case 3:
				while(true)
				{
					System.out.print("Enter d: ");
					try 
					{
						h1.setD(sc.nextInt());
						if(!h1.isEmpty()){
							h1.buildHeap();
						}
						System.out.println(h1.toString());
						break;
					} 
					catch (InputMismatchException e)
					{
						System.err.print("Input was not an integer.\n");
						sc.nextLine();
						continue;
					}
				}
				break;
				

			case 4:
				System.out.print("Program Terminated");
				break;

			default:
				System.err.print("Invalid number, please enter an integer from 1 to 4.\n");
				break;

			}
		}
		sc.close();
	}
}