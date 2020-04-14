// CS 0401 Fall 2019
// Shell of MultiDS<T> class to be used with Assignment 4.
// You must complete this class so that:
// 1) It works with test program Assig4A.java
// 2) You can use it in your War program

import java.io.*;
import java.util.*;
public class MultiDS<T> implements PrimQ<T>, Reorder
{
	private T [] theData;  // Note that the data is an array of T
	// You will also need one or more state variables to keep track of your
	// logical data.  The variables you need depend on how you will manage
	// your queue.
	
	//my own state variables
	private int logical;
	//--------------
	
	private Random R;  // This is needed so that you can shuffle your data

	public MultiDS(int sz)
	{
		theData = (T []) new Object[sz];  // Note how this is created
		R = new Random();
		logical = 0;
		// You will also need to initialize your other state variable(s).
		// You will need to create a new array in a similar way in your
		// resize() method.
	}
	
	// Below you should implement all of the methods in the PrimQ<T> and Reorder
	// interfaces.  See the details for these methods in files PrimQ.java and
	// Reorder.java.  See how these are actually used in the test program Assig4A.java.
	// Once you have completed this class the program Assig4A.java should compile and
	// run and give output identical to that shown in file A4Out.txt (except for the
	// order of the data after shuffling, since that should be random).
	
	public T [] resize(T [] obj) { // resize method doubles the size of the array
		int present = obj.length*2;
		T [] temp = theData;
		theData = (T []) new Object[present];
		
		for (int i = 0; i < logical; i++) {
			theData[i] = temp[i];
		}
		
		return theData;
	}
	//--------------------------------------
	//PrimQ interface implementation below:
	public boolean addItem(T item) {
		if (logical+1 == theData.length) {
			resize(theData);
			theData[logical] = item;
			logical++;
			return true;
		} else {
			theData[logical] = item;
			logical++;
			return true;
		}
	}
	
	
	public T removeItem() {
		if (logical == 0) {
			return null;
		} else {
			T temp = theData[0];
			theData[0] = null;
			for (int i = 0; i < logical-1; i++) {
				theData[i] = theData[i+1];
			}
			logical--;
			return temp;
		}
	}
	
	public boolean full() {
		if (logical == theData.length) 
			return true;
		else 
			return false;
		
	}
	
	public boolean empty() {
		if (logical == 0)
			return true;
		else 
			return false;
		
	}
	
	public int size() {
		return logical;
	}
	
	public void clear() {
		logical = 0;
	}
	//------------------------
	//Reorder interface implementation below:
	public void reverse() {
		for (int i = 0; i < logical/2; i++) {
			T temp = theData[i];
			theData[i] = theData[(logical-1) - i];
			theData[(logical-1) - i] = temp;
		}
	}
	
	public void shiftRight() {
		T last = theData[logical-1];
		for(int i = logical-1; i > 0; i--)
			theData[i] = theData[i-1];
		theData[0] = last;
		
	}
	
	public void shiftLeft() {
		T first = theData[0];
		for (int i = 0; i < logical; i++) {
			theData[i] = theData[i+1];
		}
		theData[logical-1] = first;
	}
	
	public void shuffle() {
		for (int i = 0; i < logical; i++) {
			int rand = R.nextInt(logical-i) + i;
			T temp = (T) theData[rand];
			theData[rand] = theData[i];
			theData[i] = temp;
		}
	}

	//ToString
	public String toString() {
		StringBuilder obj = new StringBuilder();
		for (int i = 0; i < logical; i++) {
			obj.append(theData[i] + " ");
		}
		return obj.toString();
	}
	
	//My own methods:
	//--------------------------------
	
	public T getItem(int n) { //a getter for item at an index
		return theData[n];
	}
}

