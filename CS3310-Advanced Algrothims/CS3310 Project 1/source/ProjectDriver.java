import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;



public class ProjectDriver {

	public static void main(String[] args) {
		Scanner kb=new Scanner(System.in);
		char redo;
		do {
		System.out.println("\n1. Merge Sort");
		System.out.println("2. Quick Sort");
		System.out.println("3. Matrix Multiplication");
		System.out.println("4. Tower of hanoi");
		System.out.println("0. System Exit");

		System.out.print("Choose out the 1 through 4 which operation you want to do:");
		   int choice=kb.nextInt();
		   if(choice==1) {
			   System.out.print("How many items would you like to run the input with?:");
			   int amount=kb.nextInt();
			   showMergeSort(amount);
		   }
		
		   else if(choice==2) {
			   System.out.print("How many items would you like to run the input with?:");
			   int amount=kb.nextInt();
			   showQuickSort(amount);
		   }
		
		   else if(choice==3) {
			   System.out.print("How many items would you like to run the input with?:");
			   int amount=kb.nextInt();
			   showMatrixMult(amount);
		   }
		
		    else if(choice==4) {
		    System.out.print("How many items would you like to run the input with?:");
		    int amount=kb.nextInt();	
			showHanoi(amount);			
		   }
		
		
		    else if(choice==0){
		       System.out.println("The program will now end.");
			   System.exit(0);
		    }
		
		System.out.print("Would you like to do another calculation?:");
		String input=kb.next();
		redo=input.charAt(0);
		}while(redo=='Y'||redo == 'y');
		System.out.println("The program will now end.");
	}
	
	/*Shows mergesort algorithm
	 * 1. Recursively splits the array in half until there is only 2 items in the array
	 * 2. Combines two arrays by comparing the values locally between them and sorting them accordingly
	 * 3. Continues until the problem is solved
	 */
	public static void showMergeSort(int amount) {
		// Creates a test array to demonstrate that the algorithm works
		int []a=new int[] {2,4,1,7,2,7,25,56};
		System.out.println("Here is the test array");
		System.out.println("Before");
		printArray(a);
		mergeSort m=new mergeSort();
		m.mergesort(a,0,a.length-1);
		System.out.println("After");
		printArray(a);
		System.out.println();
		
		int [] test=new int[amount];
		test=createArray(test,test.length);
		long start=System.currentTimeMillis();
		m.mergesort(test, 0, test.length-1);
		long end=System.currentTimeMillis();
		long execution= end-start;
		System.out.println("The execution time of mergesort on "+amount+" items"+" is "+execution+" ms.");		
	}
	/*Shows quicksort algorithm
	 * 1. Selects a pivot position
	 * 2. Moves the pivot the beginning or end of the array depending on the implementation
	 * 3. Moves all values less than the pivot to the left of the array and Moves all values greater than the pivot position to the right of the array
	 * 4. returns the new pivot and calls it recursivley on both sides
	 * 
	 * 
	 * 
	 */
	public static void showQuickSort(int amount) {
		// Creates a test array to demonstrate that the algorithm works
		int []a=new int[] {2,4,1,7,2,7,25,56};
		System.out.println("Here is the test array");
		System.out.println("Before");
		printArray(a);
		quickSort q= new quickSort();
		q.quicksort(a, 0, a.length-1);
		printArray(a);
		
		// Creates the array to test the runtime on high amounts 
		int [] test=new int[amount];
		test=createArray(test,test.length);
		long start=System.currentTimeMillis();
		q.quicksort(test, 0, test.length-1);
		long end=System.currentTimeMillis();
		long execution= end-start;
		System.out.println("The execution time of quicksort on "+amount+" items"+" is "+execution+" ms.");
	}
	
	/*Function shows both the normal and strassen multiplication algorithms
	 * Normal multiplication algorithms:
	 * Simply cycles through the rows and coulumns of both matrixes and assigns them to the third matrix
	 * 
	 * Strassen multiplication algorithms:
	 * recursively breaks down the two matrixes in half to shorten the 
	 * now with some algebraic manipulation it is shortened from 8 multiplications to 7
	 */
	public static void showMatrixMult(int amount) {
	   int[][] a=new int[2][2];
	   int[][] b=new int[2][2];
	   int[][] c=new int[2][2];
	   int[][] d=new int[8][8];
	   int[][] e=new int[8][8];
	   int[][] f=new int[8][8];
	   
	   strassen S=new strassen();
	   matrixMultiplication m=new matrixMultiplication();
	   
	   for(int i=0;i<2;i++) {		   
		   for(int j=0;j<2;j++) {
			   a[i][j]=2;
			   b[i][j]=4;
			   c[i][j]=0;
		   }		   
	   }
	   
	   for(int i=0;i<8;i++) {
		   for(int j=0;j<8;j++) {
			   d[i][j]=2;
			   e[i][j]=2;
			   f[i][j]=0;			   
		   }
	   }
	   
	   System.out.println("The normal matrix test of proof");
	   c=m.normMatMult(2, a, b, c);
	   printMatrix(c);
	   
	   System.out.println("The Strassen test of proof");
	   f=S.strassen(d, e, f, 8);
	   printMatrix(f);
	   
	   
	   
	   // Normal matrix test run
	   int [][] testm1=new int[amount][amount];
	   int [][] testm2=new int[amount][amount];
	   int [][] testm3=new int[amount][amount];
	   long start1=System.currentTimeMillis();
	   testm3=m.normMatMult(amount, testm1, testm2, testm3);
	   long end1=System.currentTimeMillis();
	   long execution1=end1-start1;
	   System.out.println("The execution time of normal matrix multiplication on "+amount+
			   " items"+" is "+execution1+" ms.");
	   
	   
	   // Strassen test run
	   int [][] testM1=new int[amount][amount];
	   int [][] testM2=new int[amount][amount];
	   int [][] testM3=new int[amount][amount];
	   testM1=createMatrix(testM1,testM1.length);
	   testM2=createMatrix(testM2,testM2.length);
	   testM3=createMatrix(testM3,testM3.length);
	   
	   long start=System.currentTimeMillis();
	   testM3=S.strassen(testM1, testM2, testM3, testM3.length);
	   long end=System.currentTimeMillis();
	   long execution= end-start;
	   System.out.println("The execution time of strassen on "+amount+" items"+" is "+execution+" ms.");  
	}
	
	// Recursive implementation of tower of hanoi problem
	/*Takes the amount and then recursively solves it with 3 steps
	 * 1.move n-1 disk from the 1st to 2nd disk
	 * 2.move the last peg from the 1st to 3rd peg
	 * 3.move the n-1 disks from 2nd peg to 3rd peg
	 */
	public static void showHanoi(int amount) {
		towerOfHanoi T=new towerOfHanoi();
		long start=System.currentTimeMillis();
		towerOfHanoi.towerOfHanoi(amount, 'A', 'B', 'C');
		long end=System.currentTimeMillis();
		long execution=end-start;
		System.out.println("The execution time of hanoi on "+amount+" discs is "+execution+" ms.");
	}
	
	// helper method to create large arrays and fill them with values
	// takes an empty array c, and the length n
	public static int[] createArray(int[]c,int n){
		Random r=new Random();
		for(int i=0;i<n;i++) {
			c[i]=r.nextInt(100)+1;			
		}
		return c;
	}
	
	// helper method to create large arrays and fill them with values
	// takes an empty 2d array, and the length of n for both
	public static int[][] createMatrix(int[][] c,int n){
		Random r=new Random();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++){
			c[i][j]=r.nextInt(100)+1;
			}
		}
		return c;
		
	}
	
	
	
	public static void printArray(int arr[]) { 
	        int n = arr.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(arr[i]+" "); 
	        System.out.println(); 
	}
	
	public static void printMatrix(int m[][]){
		for(int [] row: m) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}
	
	
}
