package heapTest;

/*************************************************** 
 *   Program Title: D-Heaps Implementation          *
 *   Author:  Mason Fleming                         *                    
 *   Class: CSCI3320,  Fall 2016           	   		*
 *   Assignment #2 		                     		*
 ****************************************************/

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
		
		//Loops until correct user input is received, integers only.
		while(true)
		{
			System.out.print("Enter heap elements: ");
			userIn = sc.nextLine();
			numStr = userIn.split(" "); // split the user input in separate entries in an array.
			cap = numStr.length;
			num = new int[cap];
			try{
				for(String s : numStr) // loop until we have parsed all strings into integer values.
				{
					num[i] = Integer.parseInt(s);			
					i++;
				}
			
				if(i == cap){
					break;
				}
			}
			catch(NumberFormatException e)
			{
				System.err.print("Not all inputs were integers.\n");
				i = 0;
				cap = 0;
				num = null;
				numStr = null;
			}
		}
		
		i = 0;
		while(true) //loop until correct input is given for d.
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
		
		//Create Heap
		Heap h1 = new Heap(cap, d, num);
		h1.buildHeap();
		System.out.println(h1.toString());
		choice = 0;
		while(choice != 4) //Loop until the choice of 4 is given, to exit. 
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
			
			//menu choice
			switch (choice){
			case 1: // case 1: ask for element to be inserted, loop until integer is given.
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

			case 2: // case 2: deleteMin element, print heap, print empty if heap has no elements.
				if(h1.deleteMin() == 0 && h1.getNumElements() == 0)
				{
					System.out.print("Heap is empty.\n");
				}
				else
				{
					System.out.println(h1.toString());
				}

				break;

			case 3: // case 3: ask for a new d value, loop until integer >= 2 is given. rebuild heap
					// if the heap is not empty.
				while(true)
				{
					System.out.print("Enter d: ");
					try 
					{
						d = sc.nextInt();
						if(d < 2){
							sc.nextLine();
							System.err.print("Integer must be greater than or equal to 2.\n");
							continue;
						}
						h1.setD(d);
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
				

			case 4: // case 4: exit the program.
				System.out.print("Program Terminated");
				break;

			default: // default: ask for valid integers, 1 to 4.
				System.err.print("Invalid number, please enter an integer from 1 to 4.\n");
				break;

			}
		}
		sc.close();
	}
}