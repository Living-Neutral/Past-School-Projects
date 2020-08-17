public class mergeSort {
		
	
	public static void mergesort(int[] a, int first,int last) {
				
		if(first<last) {
			int mid=(first+last)/2;
			mergesort(a,first,mid);
			mergesort(a,mid+1,last);
			merge(a,first,mid,last);
		}
	
	}
	
	public static void merge(int []a,int first,int mid,int last) {
		
		int size1=mid-first+1;
		int size2=last-mid;
		
		int []left_array=new int[size1];
		int []right_array=new int[size2];
		
		for(int i=0;i<size1;i++) {
			left_array[i]=a[i];
		}
		
		for(int j=0;j<size2;j++) {
			right_array[j]=a[mid+1+j];
		}
		
		int i=0 ,j=0,k=first;
		
		while(i<size1&&j<size2) {
			if(left_array[i]<=right_array[j]) {
				a[k]=left_array[i];
				i++;
			}
			
			else {
				a[k]=right_array[j];
				j++;
			}
		k++;	
		}
		
		while(i<size1) {
			a[k]=left_array[i];
			i++;
			k++;
		}
			
		while(j<size2) {
			a[k]=right_array[j];
			j++;
			k++;
		}
					
	}
	
}
