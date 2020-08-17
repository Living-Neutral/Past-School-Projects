package bc;
import java.util.Scanner;

public class Stack_Expression 
{

	public static void main(String[] args) 
	{
		
       	Scanner kb =new Scanner(System.in);
		System.out.print("Enter an Expression:");
		String input=kb.nextLine();
		String postFix=convertPostfix(input);
		System.out.print(postFix);
		
	}
	
	public static String convertPostfix(String infix)
	{
		
	   Stack<Character> operatorStack=new ArrayStack<>();
	   
	   String post_fix="";
	   int stackSize=infix.length();
	   for(int i=0;i<infix.length();i++)
	   {
		   char c=infix.charAt(i);
		   if(Character.isLetterOrDigit(c))
			   post_fix+=c;
		   else if(c=='(')
			   operatorStack.push(c);
		   else if(c==')')
		   {
			   while(!operatorStack.isEmpty()&&operatorStack.peek()!='(')
				   post_fix+=operatorStack.pop();
			   if(!operatorStack.isEmpty()&&operatorStack.peek()!='(')
				   return "invalid expression";
			   else
				   operatorStack.pop();
		   }
		   else
		   {
			   while(!operatorStack.isEmpty()&&checkPrecedence(c)<=checkPrecedence(operatorStack.peek()))
					   post_fix+=operatorStack.pop();
			   operatorStack.push(c);			   
		   }		   
	   while(!operatorStack.isEmpty())
		   post_fix+=operatorStack.pop();
	   }		 
	   return post_fix;
		  
   }
	   
   public static int checkPrecedence(char op)
   {
      switch (op)
	  {
	   case '+':
	   case '-':
       return 0;
           
	   case '*':
	   case '/':
	   return 1;
		   
	   case '^':
	   return 2;
		   
	   default:
	   throw new IllegalArgumentException("Operator unknown: " + op);
	  }	   
		   
   }	   

   
}	
	



