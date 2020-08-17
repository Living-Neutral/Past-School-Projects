package bc;
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> 
{
   private T[]stack;
   private int topIndex;
   private boolean integrityOK=false;
   private static final int DEFAULT_CAPACITY=50;
   private static final int MAX_CAPACITY=10000;
   
   public ArrayStack()
   {
	   this(DEFAULT_CAPACITY);
	   System.out.print("Constructor called");
   }
   
   public ArrayStack(int initalCapacity)
   {
	   integrityOK=false;
	   checkCapacity(initalCapacity);
	   
	   @SuppressWarnings("unchecked")
	   T[] tempStack=(T[]) new Object[initalCapacity];
	   stack=tempStack;
	   topIndex=-1;
	   integrityOK=true;
	   System.out.print("Constructor called");
   }
   
   public void push(T newEntry)
   {
	   checkIntegrity();
	   ensureCapacity();
	   stack[topIndex+1]=newEntry;
	   topIndex++;
   }
   
   public T pop()
   {
	   checkIntegrity();
	   if(isEmpty())
		   throw new EmptyStackException();

	   else
	   {
		   T top= stack[topIndex];
		   stack[topIndex]=null;
		   topIndex--;
		   return top;
	   }	   
	       
   }
   
   public boolean isEmpty()
   {
	   return topIndex<0;
   }
   
   public T peek()
   {
	   checkIntegrity();
	   if(isEmpty())
		   throw new EmptyStackException();
	   else
		   return stack[topIndex];
	   
   }
   
   public void clear()
   {
	   checkIntegrity();
	   
	   while(topIndex>-1)
	   {
		   stack[topIndex]=null;
		   topIndex--;
	   }
	   
   }
   
   private void checkIntegrity()
   {
	   if(!integrityOK)
		   throw new SecurityException("Bag is corrupted!");
   }

   private void checkCapacity(int capacity)
   {
	   if(capacity>MAX_CAPACITY)
		   throw new IllegalStateException("Attempt to create a bag whose"
				   +"capacity exeeds allowed" + "maximum of " + MAX_CAPACITY);
   }
   
   private void ensureCapacity()
   {
	   if(topIndex>=stack.length-1)
	   {
		   int newLength=2* stack.length;
		   checkCapacity(newLength);
		   stack=Arrays.copyOf(stack,newLength);
	   }
   }
   
   
}
