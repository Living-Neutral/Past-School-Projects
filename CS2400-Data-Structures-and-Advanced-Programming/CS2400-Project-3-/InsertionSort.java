
public class InsertionSort {
	
	public static <T extends  Comparable<? super T>> void insertionSort(T []a, int first, int last)
	{
		if(first<last)
		{
			insertionSort(a,first,last-1);
			
			
			insertInorder(a[last],a, first,last-1);
		}
		
	}
	
	private static <T extends Comparable <? super T>>
	void insertInorder(T anEntry,T[] a,int begin, int end)
	{
		
		if(anEntry.compareTo(a[end])>=1)
		{
			a[end+1]=anEntry;
		}
		
		else if(begin<end)
		{
			a[end+1]=a[end];
			insertInorder(anEntry,a,begin,end-1);
		}
		
		else 
		{
			a[end+1]=a[end];
			a[end]=anEntry;
		}
		
	}

}
