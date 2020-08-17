
public class matrixMultiplication {
	
	public static int[][] normMatMult(int n, int[][] A,int[][] B,int [][]C){
		int i,j,k;
		
		for(i=0;i<n;i++) {			
			for(j=0;j<n;j++) {
				C[i][j]=A[i][1]*B[j][1];
				for(k=2;k<n;k++) {
					C[i][j]= C[i][j]+ A[i][k]*B[k][j];
				}
				
			}
		}
		return C;
	}
}
