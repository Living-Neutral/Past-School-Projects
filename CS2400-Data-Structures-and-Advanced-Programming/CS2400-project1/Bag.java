import java.util.Arrays;

public class Bag<T> implements BagInterface<T>
{
   private  T[] bag;
   private static final int DEFAULT_CAPACITY=25;
   private static final int MAX_CAPACITY=10000;
   private int numberOfEntries;
   private boolean integrityOK=false;

   public Bag()
   {
	   this(DEFAULT_CAPACITY);
   }
   
   public Bag(int desiredCapacity)
   {
	   if(desiredCapacity<=MAX_CAPACITY)
	   {

			@SuppressWarnings("unchecked")
			T[] tempbag = (T[])new Object[desiredCapacity];
			bag=tempbag;
			numberOfEntries=0;
			integrityOK=true;
	   }
	   else
		   throw new IllegalStateException("Attempt to create a bag+"
		   		+ "whose capacity exceeds max!");
   }
   
   public boolean add(T newEntry)
   {
	   checkIntegrity();
	   boolean result=true;
	   if(isFull())
	   {
		   doubleCapacity();
	   }
	   else
	   {
		   bag[numberOfEntries]=newEntry;
		   numberOfEntries++;
	   }
	   return result;
   }
   
   public T[] toArray()
   {
	   @SuppressWarnings("unchecked")
	   T[]result=(T[])new Object[numberOfEntries];
	   for(int index=0;index<numberOfEntries;index++)
	   {
		   result[index]=bag[index];
	   }   
	   return result;
   }
 
   public int getCurrentSize()
   {
	   return numberOfEntries;
   }
   
   public int getFrequencyOf(T anEntry)
   {
	   checkIntegrity();
	   int counter=0;
	   for(int index=0;index<numberOfEntries;index++)
	   {
		   if(anEntry.equals(bag[index]))
		   {
			   counter++;
		   }
	   }
	   return counter;
   }
   
   public boolean isEmpty()
   {
	   return numberOfEntries==0;
   }
   
   public T remove()
   {
	   checkIntegrity();
	   T result= removeEntry(numberOfEntries-1);
	   return result;
   }
   
   public boolean remove(T anEntry)
   {
	   checkIntegrity();
	   int index=getIndexOf(anEntry);
	   T result = removeEntry(index);
	   return anEntry.equals(result);
   }
   
   public boolean isFull()
   {
	   return numberOfEntries==bag.length;
   }
   
   public boolean contains(T anEntry)
   {
	   checkIntegrity();
	   return getIndexOf(anEntry)>=0;
   }
   
   public void clear()
   {
	   while(!isEmpty())
		   remove();
   }

   private int getIndexOf(T anEntry)
   {
       int where=-1;
       boolean found=false;
       int index=0;
      
       while ( !found&&(index<numberOfEntries))
       {
           if (anEntry.equals(bag[index]))
           {
        	   found=true;
        	   where=index;
       	   }
               index++;
       }
       
   return where;
   }   
   
   private void checkIntegrity()
   {
	   if(!integrityOK)
		   throw new SecurityException("Bag is broke!");
   }
      
   public T removeEntry(int givenIndex)
   {
	  T result = null;
	  if(!isEmpty()&&(givenIndex>=0))
	  {
		  result = bag[givenIndex];
		  bag[givenIndex]=bag[numberOfEntries-1];
		  bag[numberOfEntries-1]=null;
		  numberOfEntries--;
	  }
	  return result;   
   }
   
   private void ensureCapacity(int capacity)
   {
	   if(capacity>=MAX_CAPACITY)
		   throw new IllegalStateException("Attempt to create a bag whose"
				   +"capacity exeeds allowed" + "maximum of " + MAX_CAPACITY);
   }
   
   private void doubleCapacity()
   {
	   int newLength=2*bag.length;
	   ensureCapacity(newLength);
	   bag=Arrays.copyOf(bag, newLength);	   
   }
  
}