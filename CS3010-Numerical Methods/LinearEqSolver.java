import java.io.*;
import java.util.*;
import java.util.Random;

public class LinearEqSolver {
	public static void main(String[] args) 
	{
		
		  double [] eq_solutions = null;
	
          Scanner kb = new Scanner(System.in); 
          String equation_file_name = null; 
          String repeat;
		  int row=0,col=0;
		  double[][] A_matrix = null;
		  System.out.println("Welcome to Linear Equation Solver"); 
		  do 
		  {
			  System.out.println("What file would you like to use?");
		  
			  System.out.println("1. Equation 1 (3x4)"); 
			  System.out.println("2. Equation 2 (4x4)");
			  System.out.println("3. Equation 3 (5x5)"); 
			  System.out.print("Enter a number 1-3:");
			  int choice = kb.nextInt(); 
			  
			  while(choice < 1 || choice > 3) 
			  {
		  
		         System.out.println("Please enter a choice between 1 and 5");
		         System.out.println("1. Equation 1"); 
		         System.out.println("2. Equation 2");
		         System.out.println("3. Equation 3");
		         System.out.print("Enter a number 1-3:");
		         choice = kb.nextInt(); 
		      }
		  
			  switch(choice) 
			  { 
			  case 1: 
				  equation_file_name = "Equation_1.txt";
				  row = 3;
				  col = 4;
				  break; 
			  case 2: 
				  equation_file_name = "Equation_2.txt"; 
				  row = 4;
				  col = 4;
				  break; 
			  case 3: 
				  equation_file_name = "Equation_3.txt";
				  row = 5;
				  col = 5;
				  break; 
		      }
			  
		  A_matrix = readMatrixFile(row,col,"equations",equation_file_name);
		  System.out.println("Here is the System of Equations you chose.");
		  printMatrix(A_matrix);
		  
		  System.out.println("Solutions of the above System of Equations");
		  eq_solutions = getEqSolutions(choice);
		  printArray(eq_solutions);
		  
		  System.out.println();
		  System.out.println("Which method would you like to use");
		  System.out.println("1. Guassian Scaled Partial Pivot");
		  System.out.println("2. Jacobi Iterative Method");
		  System.out.println("3. Guass Siedel Iterative Method");
		  System.out.print("Enter a number 1-3:"); 
		  choice = kb.nextInt(); 
		  while(choice < 1 || choice > 3) 
		  {
		  
			  System.out.println("Please enter a choice between 1 and 5");
			  System.out.println("1. Guassian Scaled Partial Pivot");
			  System.out.println("2. Jacobi Iterative Method");
			  System.out.println("3. Guass Siedel Iterative Method");
			  System.out.print("Enter a number 1-3:"); 
			  choice = kb.nextInt(); 
		  }
		  
		  double user_error = -.5E4;
		  if(choice == 2 || choice == 3)
		  {
			  System.out.print("Enter the error you want for the iterative method:");
			  user_error = Math.abs(kb.nextDouble());
		  }
		  
		  
		  double [] b_values = getBValues(A_matrix);
		  double [] init_values = getInitValues(A_matrix.length);
		  System.out.println("The coefficient matrix");
		  printMatrix(A_matrix);
		  System.out.println("The init values");
		  printArray(init_values);
		  System.out.println("The solutions");
		  printArray(b_values);
		 
		  
		  switch(choice) 
		  { 
		  	case 1: 
		  		System.out.println("Entering Scaled Partial Pivot");
		  		ScaledPartialPivot(A_matrix,eq_solutions);
		  		break; 
		  	case 2: 
		  		System.out.println("Jacobi Iterative Method");
		  		b_values = getBValues(A_matrix);
		  		Jacobi(A_matrix,user_error,b_values,init_values,eq_solutions);
		  		break; 
		  	case 3:
		  		System.out.println("Guass Siedel Iterative Method");
		  		b_values = getBValues(A_matrix);
		  		GuassSiedel(A_matrix,user_error,b_values,init_values,eq_solutions);
		  		break; 
		  }
		  
		  
		  System.out.print("Do you wish to retry?(y/n):"); 
		  kb.nextLine(); 
		  repeat = kb.nextLine();
		 }while(repeat.equalsIgnoreCase("y"));
		 

		System.out.println("Thank you for using the linear equation solver");
		System.exit(0);
	} // end main

	public static void ScaledPartialPivot(double[][] coeff_matrix,double[] true_solutions) {
		int i, j = 0, k, n;
		double r, r_max, s_max, x_mult;

		n = coeff_matrix.length;
		
		double[] scale_array = new double[n];

		int[] index_array = new int[n];

		for (i = 0; i < n; ++i) 
		{
			index_array[i] = i;
			s_max = 0;

			for (j = 0; j < n-1; ++j) 
			{
				s_max = Math.max(s_max, Math.abs(coeff_matrix[i][j]));
			} // end for
			scale_array[i] = s_max;
		} // end for

		

		for (k = 0; k < n; ++k) 
		{
			r_max = 0;
			for (i = k; i < n; ++i) 
			{
				r = Math.abs(coeff_matrix[index_array[i]][k] / scale_array[index_array[i]]);
				if (r > r_max) 
				{
					r_max = r;
					j = i;
				} // end if
			} // end for

			int temp = index_array[j];
			index_array[j] = index_array[k];
			index_array[k] = temp;

			for (i = k + 1; i < n; ++i) 
			{
				x_mult = coeff_matrix[index_array[i]][k] / coeff_matrix[index_array[k]][k];
				coeff_matrix[index_array[i]][k] = x_mult;

				for (j = k + 1; j < n; ++j) 
				{
					coeff_matrix[index_array[i]][j] -= x_mult * coeff_matrix[index_array[k]][j];
				} // end for
			} // end for

			System.out.println("Iteration:" + k + "\nmatrix");
			printMatrix(coeff_matrix);
			
		} // end for
		
		System.out.println("Maximum Iterations reached \n the solutions are");
		printArray(getBValues(coeff_matrix));
	} // end Scaled Partial Pivot

	public static void Jacobi(double[][] coeff_matrix,double given_error ,double[] solutions, double[] jacobi_iter_start_values,double[] true_solutions) {
		double delta,error;
		double diag, sum;

		double[] current_jacobi_values, old_jacobi_values;

		current_jacobi_values = jacobi_iter_start_values;

		int i, j, k, k_max, n;

		n = coeff_matrix.length;
		System.out.println("\nn:" + n);

		k_max = 100;
		delta = 10E-10;
		error = given_error;

		
		for (k = 0; k < k_max; ++k) {
			old_jacobi_values = current_jacobi_values;

			for (i = 0; i < n; ++i) {
				sum = solutions[i];
				diag = coeff_matrix[i][i];

				if (Math.abs(diag) < delta) {
					System.out.println("Diagonal element too small");
					return;
				}

				for (j = 0; j < n; ++j) {
					if (j != i) {
						sum -= coeff_matrix[i][j] * old_jacobi_values[j];
					}
				} // end for
				current_jacobi_values[i] = (sum / diag);
				
				
				
			} // end for
			
			for(int l = 0;l<true_solutions.length;l++)
			{
				if(Math.abs(true_solutions[l]-current_jacobi_values[l])<error)
				{
					System.out.println("Threshold reached");
					System.out.println("Iteration :"+k+"\nSolution is:");
					printArray(current_jacobi_values);
					return;
				}
			}

			System.out.println("Iteration :" + k);
			System.out.print("Current_jacobi_values:");
			printArray(current_jacobi_values);
		} // end for

		System.out.println("Maximum iterations reached\nSolution is:");
		printArray(current_jacobi_values);
	} // end jacobi

	public static void GuassSiedel(double[][] coeff_matrix,double given_error ,double[] solutions, double[] guass_iter_start_values,double[] true_solutions) 
	{
		double delta, error;
		double diag, sum;

		double[] current_guass_values, old_guass_values;

		current_guass_values = guass_iter_start_values;

		int i, j, k, k_max, n;

		n = coeff_matrix.length;
		System.out.println("\nn:" + n);

		k_max = 50;
		delta = 10E-10;
		error = .5E-4;

		for (k = 0; k < k_max; ++k) {
			old_guass_values = current_guass_values;

			for (i = 0; i < n; ++i) {
				sum = solutions[i];
				diag = coeff_matrix[i][i];

				if (Math.abs(diag) < delta) {
					System.out.println("Diagonal element too small");
					return;
				}

				for (j = 0; j < i; ++j) {
					sum -= coeff_matrix[i][j] * current_guass_values[j];
				}
				for (j = i + 1; j < n; ++j) {
					sum -= coeff_matrix[i][j] * current_guass_values[j];
				}

				current_guass_values[i] = (sum / diag);
			}
			
			for(int l = 0;l<true_solutions.length;l++)
			{
				if(Math.abs(true_solutions[l]-current_guass_values[l])<error)
				{
					System.out.println("Threshold reached");
					System.out.println("Iteration :"+k+"\nSolution is:");
					printArray(current_guass_values);
					return;
				}
			}
			

			System.out.println("Iteration :" + k);
			System.out.print("Current_Guass_Siedel_values:");
			printArray(current_guass_values);
		}

		System.out.println("Maximum iterations reached\n Solution is:");
		printArray(current_guass_values);
	} // end

	public static double[] getBValues(double[][] matrix) {
		double[] solutions = new double[matrix.length];
		

		for (int i = 0; i < matrix.length; ++i) 
		{
			solutions[i] = matrix[i][matrix[i].length-1];
		}
		return solutions;
	}
	
	public static double[] getEqSolutions(int choice)
	{
		double[] solutions = null;
		
		//double[] equation_1_sol = {2.0, 3.0, -1.0};
        //double[] equation_2_sol = {-2,3,0.0,0.0};
        //double[] equation_3_sol = {-3,-4,0.0,0.0,0.0};
		
		switch (choice) {
		case 1:
			solutions = new double [3];
			solutions[0] = 2.0;
			solutions[1] = 3.0;
			solutions[2] = -1.0;
			break;
		case 2:
			solutions = new double[4];
			solutions[0] = -2.0;
			solutions[1] = 4.0;
			solutions[2] = 0.0;
			solutions[3] = 0.0;
			break;
		case 3:
			solutions = new double[5];
			solutions[0] = -3.0;
			solutions[1] = -4.0;
			solutions[2] = 0.0;
			solutions[3] = 0.0;
			solutions[4] = 0.0;
		}
		return solutions;
	}
	
	public static double[][] createMatrix(int row, int col)
	{
		Random matrix_random = new Random();
		
		double[][] matrix = new double [row][col];
		
		for (int i = 0;i<row;++i)
		{
			for(int j=0;j<col;++j)
			{
				matrix[i][j] = (double)matrix_random.nextInt(100);
			}
		}
		return matrix;
	}

	public static double[][] readMatrixFile(int row, int col, String path, String file_name) {
		double[][] equations = new double[row][col];
		try {
			File f = new File(path + File.separator + file_name);
			Scanner mat_scan = new Scanner(f);
			double coeff;
			int i = 0, j = 0;

			while (mat_scan.hasNextDouble()) {
				coeff = mat_scan.nextDouble();
				equations[i][j] = coeff;

				j++;
				if (j == col) {
					i++;
					j = 0;
				}
				
			}

		} 
		catch (FileNotFoundException ex) {
			System.out.println(ex.getStackTrace());
			
		}
		
		return equations;
	}

	public static double [] getInitValues(int n)
	{
		double [] init_values = new double[n];
		for(int i = 0;i<init_values.length;++i)
		{
			init_values[i] = 0;
		}
		return init_values;
		
	}
	
	public static void printMatrix(double[][] matrix) 
	{	
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("\n");
		}

	}

	public static void printArray(double[] array) {
		if(array == null) {return;}
		for (int i = 0; i < array.length; ++i) {
			
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void printArray(int[] array) {
		
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
