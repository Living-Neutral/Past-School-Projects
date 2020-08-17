
public class quickSort {
	
	public static void quicksort(int []array,int low, int high) {
		
		if(low<high) {
			int pivotpoint=partition(array,low,high);
			quicksort(array,low,pivotpoint-1);
			quicksort(array,pivotpoint+1,high);
		}
		
	}
	
	public static int partition(int []array,int low, int high) {
		int i;
		int j;
		int pivotItem=array[high];
		i=(low-1);
		for(j=low;j<high;j++) {
			if(array[j]<=pivotItem) {
				i++;
				int temp=array[i];
				array[i]=array[j];
				array[j]=temp;				
			}
			
		}

		int temp=array[i+1];
		array[i+1]=array[high];
		array[high]=temp;
		return i+1;
		
	}

}
