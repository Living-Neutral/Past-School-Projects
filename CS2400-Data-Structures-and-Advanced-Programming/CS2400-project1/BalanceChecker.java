package bc;

public class BalanceChecker 
{
static Stack<Character> openDelimiterStack=new LinkedStack<>();

	public static boolean checkBalance(String expression)
	{
		int characterCount=expression.length();
		boolean isBalanced=true;
		int index=0;
		char nextCharacter=' ';
		
		while (isBalanced &&(index<characterCount))
		{
		   nextCharacter=expression.charAt(index);
		   switch(nextCharacter)
		   {
		   case'(': case'[':case'{':
			   openDelimiterStack.push(nextCharacter);
			   break;
		   case')': case']':case'}':
			   if(openDelimiterStack.isEmpty())
				   isBalanced=false;
			   else
			   {
				   char openDelimiter=openDelimiterStack.pop();
				   isBalanced=isPaired(openDelimiter,nextCharacter);				   
			   }
			   break;
			   default:break;			   
		   }
		   index++;
		}
		
		if(!openDelimiterStack.isEmpty())
			isBalanced=false;
		return isBalanced;
	}
	private static boolean isPaired(char open,char close)
	{
		return ((open=='('&&close==')')||
				(open=='['&&close==']')||
				(open=='{'&&close=='}'));
	}
}
