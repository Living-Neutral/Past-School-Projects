public class QuickSort 
{
	private static final int MIN_SIZE=10;
	
	public static <T extends Comparable<? super T>> void quickSort(T []a,int first , int last)
	{
		if(last-first+1<MIN_SIZE)
		{
			InsertionSort insertionSort=new InsertionSort();
			insertionSort.insertionSort(a, first, last);
		}
		
		else
		{
			int pivotIndex= paratition(a,first,last);
			quickSort(a,first,pivotIndex-1);
			quickSort(a,pivotIndex+1,last);
		}		
	}
	

	private static <T extends Comparable <? super T>>  void sortFirstMiddleLast(T[] a,int first,int mid,int last)	
	{
		order(a,first,last);
		order(a,mid,last);
		order(a,first,mid);		
	}
	
	private static <T extends Comparable <? super T>> void order(T[] a,int i ,int j)
	{
		if(a[i].compareTo(a[j])>0)
			swap(a,i,j);		
	}
	
	
	
	private static void swap(Object[] array,int i, int j)
	{
		Object temp=array[i];
		
		array[i]=array[j];
		array[i]=temp;
		
	}

	
	private static <T extends Comparable <? super T>> int paratition(T []a,int first,int last)
	{
		int mid=(first+last)/2;		
		sortFirstMiddleLast(a,first,mid,last);		
		swap(a,mid,last-1);		
		int pivotIndex=last-1;		
		T pivot=a[pivotIndex];		
		int indexFromLeft=first+1;
		int indexFromRight=last-2;		
		boolean done=false;		
		
		while(!done)
		{			
			while(a[indexFromLeft].compareTo(pivot)<0)
				indexFromLeft++;
			
			while(a[indexFromRight].compareTo(pivot)>0)
				indexFromRight--;
			
			assert a[indexFromLeft].compareTo(pivot)>=0 && a[indexFromRight].compareTo(pivot)<=0;
			
			if(indexFromLeft<indexFromRight)
			{
				swap(a,pivotIndex,indexFromLeft);
				indexFromLeft++;
				indexFromRight++;
			}
			
			else
			done=true;			
		}
		
		swap(a, pivotIndex, indexFromLeft);
		pivotIndex=indexFromLeft;
		
		return pivotIndex;
	}
	
}
