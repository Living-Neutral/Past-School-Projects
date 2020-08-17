
public class MergeSort {
		
	
	public static <T extends Comparable<? super T>> void mergeSort(T[]a,int first,int last)
	{
		@SuppressWarnings("unchecked")
		T[] tempArray=(T[]) new Comparable<?>[a.length];
		mergeSort(a,tempArray,first,last);		
	}
	
	
	private static <T extends Comparable<? super T>> void mergeSort(T [] a,T[] tempArray,int first,int last)
	{
		
		if(first<last)
		{
			int mid=(first+last)/2;
			mergeSort(a,first,mid);
			mergeSort(a,mid+1,last);
			merge(a,tempArray,first,mid,last);
		}

	}
	
	private static<T extends Comparable<? super T>> void merge(T[] a,T[] tempArray,int first,int mid,int last)
	{
		int beginHalf1=first;
		int endHalf1=mid;
		int beginHalf2=mid+1;
		int endHalf2=last;
		
		int index=0;
		
		
		while((beginHalf1<=endHalf1)&&(beginHalf2<=endHalf2))
		{
			if(a[beginHalf1].compareTo(a[beginHalf2])==-1)
			{
				tempArray[index]=a[beginHalf2];
				beginHalf1++;
			}
			
			else 
			{
				tempArray[index]=a[beginHalf2];
				beginHalf2++;
			}
			index++;
			
		}
		
				
	}
	
	
}
