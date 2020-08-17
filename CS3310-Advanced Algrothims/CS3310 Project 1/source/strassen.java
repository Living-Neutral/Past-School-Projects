
public class strassen {
	
   public static int[][] strassen(int[][]A,int[][]B,int[][]C,int n) {
		
		int halfLen=n/2;
		
		
		if(n<=2) {
			C[0][0]=A[0][0]*B[0][0]+A[0][1]*B[1][0];
			C[0][1]=A[0][0]*B[0][1]+A[0][1]*B[1][1];
			C[1][0]=A[1][0]*B[0][0]+A[1][1]*B[1][0];
		    C[1][1]=A[1][0]*B[0][1]+A[1][1]*B[1][1];			
		}
		
		else {
			int [][]a=new int[halfLen][halfLen];
			int [][]b=new int[halfLen][halfLen];
			int [][]c=new int[halfLen][halfLen];
			int [][]d=new int[halfLen][halfLen];
			int [][]e=new int[halfLen][halfLen];
			int [][]f=new int[halfLen][halfLen];
			int [][]g=new int[halfLen][halfLen];
			int [][]h=new int[halfLen][halfLen];
			
			int [][]P=new int[halfLen][halfLen];
			int [][]Q=new int[halfLen][halfLen];
			int [][]R=new int[halfLen][halfLen];
			int [][]S=new int[halfLen][halfLen];
			int [][]T=new int[halfLen][halfLen];
			int [][]U=new int[halfLen][halfLen];
			int [][]V=new int[halfLen][halfLen];
			
			int [][] C_one_one=new int[halfLen][halfLen];
			int [][] C_one_two=new int[halfLen][halfLen];
			int [][] C_two_one=new int[halfLen][halfLen];
			int [][] C_two_two=new int[halfLen][halfLen];
			
			for(int i=0;i<n/2;i++) {
				for(int j=0;j<n/2;j++) {
					a[i][j]=A[i][j]; //A11
					b[i][j]=A[i][j+halfLen];//A12
					c[i][j]=A[i+halfLen][j];//A21
					d[i][j]=A[i+halfLen][j+halfLen];//A22
					
					e[i][j]=B[i][j]; //B11
					f[i][j]=B[i][j+halfLen];//B12
					g[i][j]=B[i+halfLen][j];//B21
					h[i][j]=B[i+halfLen][j+halfLen];//B22
				}
			}
			
			P=matrixMult((matrixAdd(a,d)),matrixAdd(e,h));
			Q=matrixMult(matrixAdd(c,d),e);
			R=matrixMult(a,matrixSub(f,h));
			S=matrixMult(d,matrixSub(g,e));
			T=matrixMult(matrixAdd(a,b),h);
			U=matrixMult(matrixSub(c,a),matrixAdd(e,f));
			V=matrixMult(matrixSub(b,d),matrixAdd(g,h));
			
			C_one_one=matrixSub(matrixAdd(P,S),matrixAdd(T,V));
			C_one_two=matrixAdd(R,T);
			C_two_one=matrixAdd(Q,S);
			C_two_two=matrixSub(matrixAdd(P,R),matrixAdd(Q,U));
			
			for(int i=0;i<halfLen;i++) {
				for(int j=0;j<halfLen;j++) {
					C[i][j]=C_one_one[i][j];
					C[i][j+halfLen]=C_one_two[i][j];
					C[i+halfLen][j]=C_two_one[i][j];
					C[i+halfLen][j+halfLen]=C_two_two[i][j];
				}
				
				
			}
			
		}
		return C;	
	}
	
	public static int[][] matrixMult(int [][]A,int [][]B){
		int [][] C=new int[A.length][A.length];
		for(int i=0;i<A.length;i++) {			
			for(int j=0;j<A.length;j++) {
				C[i][j]=0;
				for(int k=0;k<A.length;k++) {
					C[i][j]=C[i][j]+A[i][k]*B[k][j];					
				}			
			}
		}
		return C;		
	}
	
	public static int[][] matrixAdd(int [][]A,int [][]B){
		int [][] C=new int[A.length][A.length];
		for(int i=0;i<A.length;i++) {			
			for(int j=0;j<A.length;j++) {
			    C[i][j]=A[i][j]+B[i][j];
		   }
		}
		return C;		
	}
	
	
	public static int[][] matrixSub(int [][]A,int [][]B){
		int [][] C=new int[A.length][A.length];
		for(int i=0;i<A.length;i++) {			
			for(int j=0;j<A.length;j++) {
			   C[i][j]=A[i][j]-B[i][j];
			}
		}
			
		return C;		
	}


}
