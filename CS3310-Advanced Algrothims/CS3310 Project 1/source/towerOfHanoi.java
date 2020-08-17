
public class towerOfHanoi {
	
	public static void towerOfHanoi(int n,char A,char B,char C) {
		
		if(n==1) {
			System.out.println("Move disc 1 from "+A+" to "+B);
			return;
		}		
			towerOfHanoi(n-1,A,C,B);
			System.out.println("Move disc "+n+" from "+A+" to "+B);
			towerOfHanoi(n-1,C,B,A);
		
	}

}
