import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
public class finding_roots 
{
	
	public static void main(String []args) throws IOException
	{
		Scanner kb = new Scanner(System.in);
		
		double [] arr1 = {2,-6,2,-1};
		double x = 3;
		int n= arr1.length;
		
		
		double[] func1 = {2,-11.7,17.7,-5};
		double[] dxFunc1 = {6,-23.4,17.7};
		
		boolean function_picker = true;
		
		int max_iter = 100;
		int given_a = 0;
		int given_b = 1;
		double given_error = .01;
		double true_root = 0.365;
		int given_x = 3;
		
		int func_ans; 
		double test_result;
		
		String answer;
		
		do {
			System.out.print("Do you want to test function 1 or 2?:");
			func_ans = kb.nextInt();
			if(func_ans == 1)
			{
				function_picker = true;
				
			}
			
			else if(func_ans == 2)
			{
				function_picker = false;
			}
			else
			{
				System.out.println("Invalid answer defualted to function 1.");
				function_picker = true;
			}
			System.out.print("Enter position a:");
			given_a = kb.nextInt();
			System.out.print("Enter position b:");
			given_b = kb.nextInt();
			
			System.out.print("Enter x for Newton's Method:");
			given_x = kb.nextInt();
			
			System.out.println("Entering Bisection");
			
			Bisection(func1,given_a,given_b,max_iter,given_error,function_picker);
			
			System.out.println("Entering Newton Raphson");
			NewtonRaphson(func1, dxFunc1, given_x,max_iter,given_error,function_picker);
			
			
			System.out.println("Entering Secant");
			Secant(func1, given_a,given_b,max_iter,given_error,function_picker);
			
			System.out.println("Entering Modified Secant");
			ModifiedSecant(func1, given_a,given_b,max_iter,given_error,function_picker);
			
			System.out.println("Entering False Position");
			FalsePosition(func1, given_a,given_b,max_iter,given_error,function_picker);
			
			System.out.print("Would you like to find another root?:");
			answer = kb.next();
		} 
		while(answer.equalsIgnoreCase("y"));	
		kb.close();
	}
	
	public static void Bisection(double poly[],double given_a, double given_b, int iter_max, double given_error, boolean function_picker) throws IOException
	{
		
		double a,b,c,fa,fb,fc,error,approximate_relative_error;
		double true_percent_relative_error = 0;
		a = given_a;
		b = given_b;
		
		if(function_picker == true)
		{
			fa = horner(poly,poly.length,a);
			fb = horner(poly,poly.length,b);
		}
		
		else
		{
			fa = funcB(a);
			fb = funcB(b);
		}
		
		if(sameSign(fa, fb) == true)
		{
			System.out.println("a:"+given_a);
			System.out.println("b:"+given_b);
			System.out.println("fa:"+fa);
			System.out.println("fb:"+fb);
			System.out.println("Function has the same signs at a and b");
			return;
		}
		
		error = Math.abs(b-a);
		approximate_relative_error = (Math.abs(a-(a+b)/2) / Math.abs((a+b)/2));
					
		for(int i = 0;i<iter_max;i++)
		{
			error = error/2;
			c = a+error;
			fc = horner(poly, poly.length,c);
			System.out.println("\nn:"+i);
			System.out.println("a:"+a);
			System.out.println("f(a):"+fa);
			System.out.println("b:"+b);
			System.out.println("f(b):"+fb);
			System.out.println("c:"+c);
			System.out.println("f(c):"+fc);
			System.out.println("error:"+error);
			System.out.println("true_percent_relative_error:"+true_percent_relative_error);
			System.out.println("approximate_relative_error:"+approximate_relative_error);
			System.out.println("");
			
			if(error<given_error)
			{
				
				System.out.println("convergence");
				System.out.println("The root is "+ c);
				return;
			}
			
			if(!sameSign(fa,fc))
			{
				b = c;
				fb = fc;
			}
			
			else
			{
				a = c;
				fa = fc;
			}
			
			
			approximate_relative_error = (Math.abs(a-(a+b)/2) / Math.abs((a+b)/2));
		}
			
	}
	
	
	public static void NewtonRaphson(double[] func,double[] dxFunc, double given_x, int max_iter, double given_error, boolean function_picker)
	{
		double d,x,fx,fp,error;
		double approximate_relative_error=0;
		double true_percent_relative_error = 0;
		
		x = given_x;
		
		if(function_picker == true)
		{
			fx = horner(func,func.length,x);
		}
		else
		{
			fx = funcB(x);
		}
		
		
		System.out.println("n:"+0);
		System.out.println("x:"+x);
		System.out.println("f(x):"+fx);
		
		for(int i = 1 ;i<max_iter;i++)
		{
			//fp = horner(dxFunc,dxFunc.length,x);
			
			fp = dxFuncB(x);
			
			if(Math.abs(fp)<given_error)
			{
				System.out.println("Small Derivative");
				return;
			}
			
			d = fx / fp;
			
			error = Math.abs(d-x);
			
			
			x -= d;
		
			approximate_relative_error=(Math.abs(x-(x+d)/2) / Math.abs((x+d)/2));
			
			//fx = horner(func,func.length,x);
			fx = funcB(x);
			System.out.println();
			System.out.println("n:"+i);
			System.out.println("x:"+x);
			System.out.println("f(x):"+fx);
			System.out.println("d:"+d);
			System.out.println("error:"+error);
			System.out.println("true_percent_relative_error"+true_percent_relative_error);
			System.out.println("approximate_relative_error"+approximate_relative_error);
			
			if(Math.abs(d)<given_error)
			{
				System.out.println("Convergence");
				System.out.println("The root is " + x+"\n");
				return;
			}
			
		}
		
		
	}
	
	public static void Secant(double[] poly,double given_a, double given_b, int max_iter, double given_error, boolean function_picker)
	{
		
		double a,b,fa,fb,error,approximate_relative_error,d;
		double true_percent_relative_error;
		a = given_a;
		b = given_b;
		
		if(function_picker==true)
		{
			fa = horner(poly,poly.length,a);
			fb = horner(poly,poly.length,b);
		}
		
		else
		{
			fa = funcB(a);
			fb = funcB(b);
		}
		
		if(Math.abs(fa) > Math.abs(fb))
		{
			double temp_1,temp_2;
			temp_1 = a;
			temp_2 = fa;
			a = b;
			b = temp_1;
			
			fa = fb;
			fb = temp_2;
		}
		
		for(int i =2;i<max_iter;i++)
		{
			if(Math.abs(fa) > Math.abs(fb))
			{
				double temp_1,temp_2;
				temp_1 = a;
				temp_2 = fa;
				
				a = b;
				b = temp_1;
				
				fa = fb;
				fb = temp_2;
			}
			
			d = (b-a) / (fb-fa);
			
			b = a;
			
			fb = fa;
			
			d *=fa;
			
			error = Math.abs(d-a);
			approximate_relative_error = (Math.abs(d-(a+d)/2) / Math.abs((a+d)/2));
			
			System.out.println("n:"+i);
			System.out.println("a:"+a);
			System.out.println("f(a):"+fa);
			System.out.println("b:"+b);
			System.out.println("f(b):"+fb);
			System.out.println("d:"+d);
			System.out.println("error:"+error);
			System.out.println("approximate_relative_error:"+approximate_relative_error);
			System.out.println("");
			
			if(Math.abs(d)<given_error)
			{
				System.out.println("Convergence");
				System.out.println("The root is "+b);
				return;
			}
			
			a -= d;
			if(function_picker==true)
			{
				fa = horner(poly,poly.length,a);
			}
			else
			{
				fa = funcB(a);
			}	
		}
		System.out.println("Ended Secant");
	}
	
	public static void ModifiedSecant(double[] poly,double given_a, double given_b, int max_iter, double given_error, boolean function_picker) throws IOException
	{
		double a,b,fa,fb,f_x_plus_delta,error,relative_error,d;
		double DELTA = .01;
		
		int same_counter=0;
		
		a = given_a;
		b = given_b;
		
		if(function_picker==true)
		{
			fa = horner(poly,poly.length,a);
			fb = horner(poly,poly.length,b);
			f_x_plus_delta = horner(poly,poly.length,a+DELTA);
		}
		
		else
		{
			fa = funcB(a);
			fb = funcB(b);
			f_x_plus_delta = funcB(a+DELTA);
		}
		
		
		
		if(Math.abs(fa) > Math.abs(fb))
		{
			double temp_1,temp_2;
			temp_1 = a;
			temp_2 = fa;
			
			a = b;
			b = temp_1;
			
			fa = fb;
			fb = temp_2;
		}
		
		for(int i =2;i<max_iter;i++)
		{
			if(Math.abs(fa) > Math.abs(fb))
			{
				double temp_1,temp_2;
				temp_1 = a;
				temp_2 = fa;
				
				a = b;
				b = temp_1;
				
				fa = fb;
				fb = temp_2;
			}
			
			
			
			d =  a - fa * (DELTA*a/f_x_plus_delta-fa) ;
			
			b = a;
			
			fb = fa;
			
			d *=a;
			
			error = Math.abs(d-a);
			relative_error = (Math.abs(a-(a+d)/2) / Math.abs((a+d)/2));
			
			System.out.println("n:"+i);
			System.out.println("a:"+a);
			System.out.println("f(a):"+fa);
			System.out.println("b:"+b);
			System.out.println("f(b):"+fb);
			System.out.println("f(x+delta):"+f_x_plus_delta);
			System.out.println("d:"+d);
			System.out.println("error:"+error);
			System.out.println("relative_error:"+relative_error);
			System.out.println("");
			
			if(Math.abs(d)<given_error)
			{
				System.out.println("Convergence");
				return;
			}
			
			a -= d;
			
			
			
			if(function_picker == true)
			{
				fa = horner(poly,poly.length,a);
				f_x_plus_delta = horner(poly,poly.length,a+DELTA);
			}
			
			else
			{
				fa = funcB(a);
				f_x_plus_delta = funcB(a+DELTA);
				
			}
	        double last_f_delta_value = f_x_plus_delta;
			
			
			if(Math.abs(last_f_delta_value-f_x_plus_delta)<=1)
			{
				same_counter++;
			}
			else
			{
				same_counter=0;
			}
			if(same_counter>3)
			{
				break;
			}
			
			
			
		}
		System.out.println("Ended Modified Secant");
		System.out.println("The closest root it found was"+a);
	}
	
	
	public static void FalsePosition(double[] poly,double given_a, double given_b,int max_iter,double given_error, boolean function_picker) throws IOException
	{
		double a,b,c,fa,fb,fc,error,relative_error;
		
		a = given_a;
		b = given_b;
		
		if(function_picker==true)
		{
			fa = horner(poly,poly.length,a);
			fb = horner(poly,poly.length,b);
		}
		
		else
		{
			fa = funcB(a);
			fb = funcB(b);
		}
		
		error = Math.abs(b-a);
		relative_error=(Math.abs(a-(a+b)/2) / Math.abs((a+b)/2));
		c = 0;
		
		for(int i = 0;i<max_iter;i++)
		{
			c = ((a*fb-b*fa) / (fb-fa));
			
			fc = horner(poly,poly.length,c);
			
			System.out.println("n:"+i);
			System.out.println("a:"+a);
			System.out.println("f(a):"+fa);
			System.out.println("b:"+b);
			System.out.println("f(b)"+fb);
			System.out.println("c:"+c);
			System.out.println("f(c):"+fc);
			System.out.println("error:"+error);
			System.out.println("relative_error:"+relative_error);
			System.out.println();
			
			
			if(error<given_error)
			{
				System.out.println("Convergence");
				System.out.println("Root found at " +c);
				return;
			}
			
			if(fc==0)
			{
				break;
			}
			
			else if(fc-fa<0)
				b = c;
			
			else
				a = c;
			
			error = Math.abs(b-a);
			relative_error = (Math.abs(a-(a+b)/2) / Math.abs((a+b)/2));;
			System.out.println();
			System.out.println();
			
		}
		
		System.out.println("The value of the root is " + c );
			
	}
	
	// effective way to evaluate any normal polynomials without sine
	// for finding out the values for func a
	public static double horner(double poly[] , int n , double x)
	{
		double result = poly[0];
		
		for(int i = 1;i<n;i++)
		{
			result = poly[i] + x * result;  
		}
		
		return result;
	}
	
	// mathematical representation of function b
	public static double funcB(double x)
	{
		double result;
		result = x+10 -x*Math.cosh(50/x);
		
		return result;
	}
	
	public static double dxFuncB(double x)
	{
		double result;
		result = (50*Math.sinh(50/x)/x)-(Math.cosh(x))+1;
		return result;
	}
	
	public static boolean sameSign(double x, double y)
	{	
		return x*y>0;
	}
	
}
