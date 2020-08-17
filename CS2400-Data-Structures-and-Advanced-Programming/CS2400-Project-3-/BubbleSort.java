
public class BubbleSort {
	private static boolean swapped;
	
	public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {
		
		do {
			
		int	arrayLength=a.length;
		for(int i=0;i<arrayLength-1;i++)
		{	
			
		   for(int j=0;j<arrayLength-i-1;j++)
		   {
			
			   if(a[j].compareTo(a[j+1])==1)
			   {				
				   T temp=a[j];
				   a[j]=a[j+1];
				   a[j+1]=temp;				
			   }		
		   }
		}
		
	  }while(swapped==true);
		
	}
	
	 

}
