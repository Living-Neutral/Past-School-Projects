import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Arrays;

public class NewtonsDividedDiff 
{
	public static void main(String [] args)
	{
		// sample run 
		sampleRun();
		
		Scanner kb = new Scanner(System.in);
		String repeat = "y";
		int choice;
		System.out.println("Welcome to Newton's Divided Differences!");
		do
		{
			System.out.println("What polynomial would you like to solve?");
			System.out.println("1. Equation 1 (10 data points)");
			System.out.println("2. Equation 2 (10 data points)");
			System.out.println("3. Equation 3 (12 data points)");
			System.out.println("4. Equation 4 (10 data points)");
			System.out.println("5. Equation 5 (50 data points)");
			System.out.print("Enter your choice(1-5):");
			choice = kb.nextInt();
			
			while( choice < 1 || choice>5)
			{
				System.out.println("Invalid Input!\nPlease Select Within the range");
				System.out.println("1. Equation 1 (10 data points)");
				System.out.println("2. Equation 2 (10 data points)");
				System.out.println("3. Equation 3 (12 data points)");
				System.out.println("4. Equation 4 (10 data points)");
				System.out.println("5. Equation 5 (50 data points)");
				System.out.print("Please Enter a choice (1-5):");
				choice = kb.nextInt();
			}
			
			
			int n = 0;
			String path = "equations";
			String file_name = null;
			
			switch(choice)
			{
			case 1:
				System.out.println("Equation 1");
				file_name = "Equation_1.txt";
				n = 10;
				break;
				
			case 2:
				System.out.println("Equation 2");
				file_name = "Equation_2.txt";
				n = 10;
				break;
				
			case 3:
				System.out.println("Equation 3");
				file_name = "Equation_3.txt";
				n = 12;
				break;
				
			case 4:
				System.out.println("Equation 4");
				file_name = "Equation_4.txt";
			    n = 10;
				break;
				
			case 5:
				System.out.println("Equation 5");
				file_name = "Equation_5.txt";
				n = 50;
				break;
			}
			
			double[] xy_values = readXY(n,"x_y_values",file_name);
			printArray(xy_values);
			double[] x_values = Arrays.copyOfRange(xy_values, 0, xy_values.length/2);
			double [] y_values = Arrays.copyOfRange(xy_values, (xy_values.length+1)/2,xy_values.length);
			
			System.out.println("length of x:"+x_values.length);
			System.out.println("length of y:"+y_values.length);
			
			int numberOfPoints = x_values.length;
			double[][] divided_diff_matrix = simpleDividedDiff(x_values,y_values, numberOfPoints);
			double [] solved_coeffs = divided_diff_matrix[0]; 
			printNewtonsTable(divided_diff_matrix,numberOfPoints);
			printInterpolatedPoly(solved_coeffs,x_values);
			
			System.out.print("Do you want to solve another equation?:");
			kb.nextLine();
			repeat = kb.nextLine();
		}
		while(repeat.equalsIgnoreCase("y"));
	}
	
	public static void sampleRun()
	{
        double[] test_xy_values = readXY(8,"x_y_values", "func1.txt" );
		
        System.out.println();
		printArray(test_xy_values);
		
		double[] x_values,y_values = null;
		
		x_values = Arrays.copyOfRange(test_xy_values, 0, test_xy_values.length/2);
		y_values = Arrays.copyOfRange(test_xy_values, (test_xy_values.length+1)/2,test_xy_values.length);
		
		int n = x_values.length;
		double [][] test_matrix = simpleDividedDiff(x_values,y_values,n);
		
		double[] coeff_solutions = test_matrix[0];
		
		printNewtonsTable(test_matrix,n);
		
		printInterpolatedPoly(coeff_solutions,x_values);
	}
	
	public static double[][] simpleDividedDiff(double[] x_values, double[] y_values,int n)
	{
		double [][] coeff_matrix = new double[x_values.length][y_values.length];
		int i;
		for(i = 0;i<n;++i)
		{
			coeff_matrix[i][0] = y_values[i];
		} // end for
		
		System.out.println("Values of the matrix");
		printMatrix(coeff_matrix);
		
		for(int j = 1;j<n;++j)
		{
			for(i = 0;i<n-j;++i)
			{
				coeff_matrix[i][j] = (coeff_matrix[i+1][j-1] - coeff_matrix[i][j-1])/(x_values[i+j]-x_values[i]);
			}	
		}
		
		System.out.println("Values of the completed matrix");
		printMatrix(coeff_matrix);
		
		return coeff_matrix;
	}
	
	
	public static double[] readXY(int n,String path,String file_name)
	{
		double[] xy_values = new double[n];
		try
		{
			File f = new File(path+File.separator+file_name);
			Scanner xy_scan = new Scanner(f);
			//System.out.print(f.getAbsolutePath());
			int i =0;
			while(xy_scan.hasNextDouble() && i<n)
			{
				xy_values[i] = xy_scan.nextDouble();
				i++;
			}
			
			
			
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		
		finally
		{
			return xy_values;
		}
	}
	
	public static void printNewtonsTable(double[][] coeff_matrix,int n)
	{
		
		System.out.println("Printing Newton's Table of Divided Differences.");
		for(int i = 0;i<n;++i)
		{
			System.out.print("f[");
			for(int j = 0;j<i;++j)
			{
				System.out.print(" , ");
			}
			System.out.print("]\t");
		}
		System.out.println();
		
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		for(int i =0;i<n;++i)
		{
			for(int j = 0;j<n-i;++j)
			{
				String str = df.format(coeff_matrix[i][j]);
				System.out.print(str+"\t"+"   ");
			}
			System.out.println("");
		}
	}
	
	public static void printInterpolatedPoly(double[] coeff_solutions,double[] x_values)
	{
		System.out.println("Printing the interpolated polynomial");
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		for(int i = 0;i<coeff_solutions.length;++i)
		{
			
			String str = df.format(coeff_solutions[i]);
			
			if(coeff_solutions[i]>=0 && i>0)
			{
				System.out.print("+");
			}
			System.out.print(str);
			
			if(i>0)
			{
				
				for(int j = 0;j<i;++j)
				{
					if(x_values[j]>0)
					{
						System.out.print("(x-"+x_values[j]+")");
					}
					else if(x_values[j]==0)
					{
						System.out.print("(x)");
					}
					else
					{
						System.out.print("(x+"+Math.abs(x_values[j])+")");
					}
				}
			}
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void printArray(double [] array)
	{
		for(int i =0;i<array.length;++i)
		{
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	public static void printMatrix(double [][] matrix)
	{
		for(int i = 0;i<matrix.length;++i)
		{
			for(int j=0;j<matrix[i].length;++j)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void writeXYFiles()
	{
		File f1 = new File("x_y_values"+File.separator+"Equation_1.txt");
	}
	

}
