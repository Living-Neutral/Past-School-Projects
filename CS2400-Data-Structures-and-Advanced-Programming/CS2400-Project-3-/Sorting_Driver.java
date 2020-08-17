import java.util.Random;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Sorting_Driver 
{
	// random object for filling arrays
	static Random rando=new Random();
	static String redo;
	static char yes;
	
	// main method a menu for the different algorithms
	public static void main(String[] args) 
	{	
		int choice;
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to Algorithem test!");
		do {
			
		// Various options 	to choose from the menu 
		System.out.println("Choose an algorithm to test!");
		System.out.println("1.Selection Sort");
		System.out.println("2.Merge Sort");
		System.out.println("3. Bubble Sort");
		System.out.println("4.Quick Sort");
		System.out.println("5.Insertion Sort");
		System.out.println("0. Exit");
		System.out.print("Enter a number (1-5): ");
		choice=kb.nextInt();
				
		// depending on user input it will run the algorithm you choose
		// A do while loop with the various if else
		
		if(choice==1)
		testSelectionSort();
		
		else if(choice==2)
	    testMergeSort();
		
		else if(choice==3)
		testBubbleSort();
		
		else if(choice==4)
		testQuickSort();
			
		else if(choice==5) 			
		testInsertionSort();
	
		else
		System.exit(0);
		
		}while(choice!=0);
	}// end main
	
	/*Two print arrays to test the sorted arrays
	 *
	 *
	 * */
	 
	public static<T extends Comparable<? super T>> void printArray(T[] array)
	{
		for(int i=0;i<array.length;i++)
		{
			System.out.println(array[i]);
		}		
	}// end print array
	
	public static void printArray(int [] a)
	{
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}				
	}// end print array
	
	public static void testSelectionSort()
	{
		
		Scanner kb=new Scanner(System.in);
		System.out.println("\nStarting Selection Sort Method");
		
		/*grabs an Entry and creates an array of that length 
		 */
		System.out.print("How many Entries do you want?:");
		int arrayLength=kb.nextInt();
		Integer [] testArray=new Integer[arrayLength];
		
		// fills the array with random values to sort
		for(int i=0;i<testArray.length;i++)
		{
			testArray[i]=Integer.valueOf(rando.nextInt(arrayLength)+1);
		}
	
		// creates a selection sort
		SequenceSort selectionSort=new SequenceSort();
		
		// records at the start and ends after the sort
		Instant start=Instant.now();
		selectionSort.selectionSort(testArray,testArray.length);
		Instant end=Instant.now();
				
		// takes the the start and end and creates and interval from that
		
		/* From the interval returns the values in milliseconds and seconds		 
		 */
		Duration interval=Duration.between(start, end);
		System.out.println("Selection Sort Start");
		System.out.println("Execution time of "+arrayLength+" Items in milisecs "+interval.toMillis());		
		System.out.println("Execution time of "+arrayLength+" Items in secs "+interval.getSeconds());
	    System.out.println("Selection Sort End\n");			
	}// end selection sort
	
	
	public static void testMergeSort()
	{
		System.out.println("\nStarting Merge Sort Method");
		MergeSort merge=new MergeSort();
		
		
		/*grabs an Entry and creates an array of that length 
		 */
		Scanner kb=new Scanner(System.in);
		System.out.print("How many Entries do you want?:");
		int arrayLength=kb.nextInt();
		Integer [] testArray=new Integer[arrayLength];
		
		
		// fills the array with random values to sort
		for(int i=0;i<testArray.length;i++)
		{
			testArray[i]=Integer.valueOf(rando.nextInt(arrayLength)+1);
		}
		
		
		// records at the start and ends after the sort
		Instant start=Instant.now();
		merge.mergeSort(testArray, 0, testArray.length-1);
		Instant end=Instant.now();
		
		
		// takes the the start and end and creates and interval from that
		/* From the interval returns the values in milliseconds and seconds		 		 
		 */
		Duration interval=Duration.between(start, end);
		System.out.println("Selection Sort Start");
		System.out.println("Execution time of "+arrayLength+" Items in milisecs "+interval.toMillis());		
		System.out.println("Execution time of "+arrayLength+" Items in secs "+interval.getSeconds());	
	    System.out.println("Merge Sort End\n");		
	}// end merge sort
	
	public static void testBubbleSort()
	{
		
		/*grabs an Entry and creates an array of that length 
		 */
		System.out.println("\nStarting Bubble Sort");
		BubbleSort BubbleSort=new BubbleSort();									
		Scanner kb=new Scanner(System.in);
		System.out.print("How many Entries do you want?:");
		int arrayLength=kb.nextInt();
		
		//int [] testArray=new int[arrayLength];
		Integer [] testArray=new Integer[arrayLength];
		
		// fills the array with random values to sort
		for(int i=0;i<testArray.length;i++)
		{
			testArray[i]=Integer.valueOf(rando.nextInt(arrayLength)+1);
		}
		
		// takes the the start and end and creates and interval from that
		/* From the interval returns the values in milliseconds and seconds		 		 
		 */
		Instant start=Instant.now();
		BubbleSort.bubbleSort(testArray);
		Instant end=Instant.now();
			
		
		// takes the the start and end and creates and interval from that
		/* From the interval returns the values in milliseconds and seconds		 		 
		*/
		Duration interval=Duration.between(start, end);
		System.out.println("Bubble Sort Start");
		System.out.println("Execution time of "+arrayLength+" Items in milisecs "+interval.toMillis());		
		System.out.println("Execution time of "+arrayLength+" Items in secs "+interval.getSeconds());	
	    System.out.println();
	    System.out.println("Bubble Sort Done\n");
	}// end bubble sort
	
	

	
	public static void testQuickSort()
	{
		/*grabs an Entry and creates an array of that length 
		 */
		System.out.println("\nStarting Quick Sort");
		Scanner kb=new Scanner(System.in);
        System.out.print("How many entires do you want?");
        int arrayLength=kb.nextInt();       
		Integer [] testArray=new Integer[arrayLength];
		
		
		
		// fills the array with random values to sort
		for(int i=0;i<testArray.length;i++)
		{
			testArray[i]=Integer.valueOf(rando.nextInt(arrayLength)+1);
		}
				
		
		/* From the interval returns the values in milliseconds and seconds		 		 
		 */
		Instant start=Instant.now();
		QuickSort.quickSort(testArray, 0, testArray.length-1);
		Instant end=Instant.now();
			
		
		// takes the the start and end and creates and interval from that
		/* From the interval returns the values in milliseconds and seconds		 		 
	    */
		Duration interval=Duration.between(start, end);
		System.out.println("Quick Sort Start");
		System.out.println("Execution time of "+arrayLength+" Items in milisecs "+interval.toMillis());		
		System.out.println("Execution time of "+arrayLength+" Items in secs "+interval.getSeconds());	
	    System.out.println();
	    System.out.println("Quick Sort Done\n");
	}// end quick sort
	
	
	public static void testInsertionSort()
	{
		/*grabs an Entry and creates an array of that length 
		 */
		System.out.println("\nStarting Insertion Sort");
		Scanner kb=new Scanner(System.in);
		
        System.out.print("How many entires do you want?");
        int arrayLength=kb.nextInt();
		
        Integer [] testArray=new Integer[arrayLength];		
        
        
        // fills the array with random values to sort
		for(int i=0;i<testArray.length;i++)
		{
			testArray[i]=Integer.valueOf(rando.nextInt(arrayLength)+1);
		}
		
		
		
		Instant start=Instant.now();
		InsertionSort.insertionSort(testArray, 0,arrayLength-1);
		Instant end=Instant.now();
		
		
		// takes the the start and end and creates and interval from that
	    /* From the interval returns the values in milliseconds and seconds		 		 
	     */
		Duration interval=Duration.between(start, end);
		System.out.println("Insertion Sort Start");
		System.out.println("Execution time of "+arrayLength+" Items in milisecs "+interval.toMillis());		
		System.out.println("Execution time of "+arrayLength+" Items in secs "+interval.getSeconds());	
	    System.out.println();
	    System.out.println("Insertion Sort Done\n");				
	}// end Insertion Sort
}
