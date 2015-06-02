package Lab7;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/*
 * Name: Drew Hamm
 * Class: CS260 Spring 2015
 * Date: 6/02/2015
 * Program Name: Driver.java
 * Program Description: Driver program to work with binary search trees.
 * Pseudocode:
 * 	1. Declare and populate a sorted array that contains at least 15 integers
 *  2. Create a balanced binary search tree from the above integer array
 *  3. Output the tree using a traversal algorithm
 */
public class Driver {
	public static void main(String[] args)
	{
		//Test createBTFromSortedArray
		System.out.println("Begining tests for the required static method...");
		UnitTester tester = createBTFromSortedArrayTest();
		System.out.print(tester.getResults());
		if(tester.hasFailed()) System.out.println("A test has failed");
		System.out.println("Finished with tests...\n");
		
		// Declare and populate a sorted array that contains at least 15 integers
		int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		System.out.println("The following array has been created:");
		for(int i = 0; i<sortedArray.length; i++){
			System.out.println(sortedArray[i]);
		}
		
		// Create a balanced binary search tree from the above integer array
		BTNode myBalancedTree = createBTFromSortedArray(sortedArray, 0, sortedArray.length);
		
		// Output the tree using a traversal algorithms
		System.out.println("A binary tree has been created with the above integer array:");
		System.out.println("Lets output the tree using several transversal algorithms");
		
		System.out.println("In-order:");
		myBalancedTree.inorderPrint();
		System.out.println("Pre-order:");
		myBalancedTree.preorderPrint();
		System.out.println("Post-order:");
		myBalancedTree.postorderPrint();
		System.out.println("Depth:");
		myBalancedTree.print(0);
	}
	
	/**
   * Recursively generates a new balanced tree.
   * @param <CODE>sortedArray</CODE>
   *   an integer array that is sorted from smallest to greatest.
   * @param <CODE>start</CODE>
   *   the index of the first valid location in the sorted array
   *   the valid left edge
   * @param <CODE>end</CODE>
   *   the index of the first invalid location outside of the sorted array.
   *   the invalid right edge
   * @return
   *   a new balanced binary tree: BTNode
   * <dt><b>Precondition:</b><dd>
   *   <CODE>sortedArray</CODE> is not null;
   * <dt><b>Precondition:</b><dd>
   *   <CODE>start</CODE> is greater than or equal to 0;  
   * <dt><b>Precondition:</b><dd>
   *   <CODE>start</CODE> is less than or equal to <CODE>end</CODE>
   * <dt><b>Precondition:</b><dd>
   *   <CODE>end</CODE> is less than or equal to <CODE>sortedArray.length</CODE>
   * <dt><b>Note:</b><dd>
   *   Error occurs for trees larger than;
   *   <CODE>INT.MAX_VALUE</CODE>.    
   **/ 
	public static BTNode<Integer> createBTFromSortedArray(int[] sortedArray, int start, int end){
		//Preconditions:
		if(sortedArray == null) throw new NullPointerException("Precondition: sortedArray != null");
		if(start < 0) throw new IllegalStateException("Precondition: start >= 0");
		if(end > sortedArray.length) throw new IllegalStateException("Precondition: end <= sortedArray.lenght");
		if(start > end) throw new IllegalStateException("Precondition: start <= end");
		
		int middle = ((start + end) /2);
		if(start == middle && end == middle) return null;
		else if(start == middle || end == middle) return new BTNode<Integer>(sortedArray[middle], null, null);
		return new BTNode<Integer>(sortedArray[middle],
				createBTFromSortedArray(sortedArray, start, middle),
				createBTFromSortedArray(sortedArray, middle+1, end));
	}
	
	public static UnitTester createBTFromSortedArrayTest(){
		UnitTester tester = new UnitTester();
		
		BTNode<Integer> testTree;
		int[] one = {1};
		int[] two = {1, 2};
		int[] three = {1, 2, 3};
		int[] fifteen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		
		//Test with one value
		testTree = createBTFromSortedArray(one, 0, one.length);
		tester.test(BTNode.treeSize(testTree), 1);
		tester.test(testTree.getData(), 1);
		tester.test(testTree.getRight() == null, true);
		tester.test(testTree.getLeft() == null, true);
		
		//Test with two values
		testTree = createBTFromSortedArray(two, 0, two.length);
		tester.test(BTNode.treeSize(testTree), 2);
		tester.test(testTree.getData(), 2);
		tester.test(testTree.getRight() == null, true);
		tester.test(testTree.getLeft().getData(), 1);
		
		//Test with three values
		testTree = createBTFromSortedArray(three, 0, three.length);
		tester.test(BTNode.treeSize(testTree), 3);
		tester.test(testTree.getData(), 2);
		tester.test(testTree.getRight().getData(), 3);
		tester.test(testTree.getLeft().getData(), 1);
		
		//Test with four values
		testTree = createBTFromSortedArray(fifteen, 0, fifteen.length);
		tester.test(BTNode.treeSize(testTree), 15);
		tester.test(testTree.getData(), 8);
		tester.test(testTree.getRight().getData(), 12);
		tester.test(testTree.getRight().getRight().getData(), 14);
		tester.test(testTree.getRight().getRight().getRight().getData(), 15);
		tester.test(testTree.getRight().getRight().getLeft().getData(), 13);
		tester.test(testTree.getRight().getLeft().getData(), 10);
		tester.test(testTree.getRight().getLeft().getRight().getData(), 11);
		tester.test(testTree.getRight().getLeft().getLeft().getData(), 9);
		tester.test(testTree.getLeft().getData(), 4);
		tester.test(testTree.getLeft().getRight().getData(), 6);
		tester.test(testTree.getLeft().getRight().getRight().getData(), 7);
		tester.test(testTree.getLeft().getRight().getLeft().getData(), 5);
		tester.test(testTree.getLeft().getLeft().getData(), 2);
		tester.test(testTree.getLeft().getLeft().getRight().getData(), 3);
		tester.test(testTree.getLeft().getLeft().getLeft().getData(), 1);
		
		return tester;
	}
}
