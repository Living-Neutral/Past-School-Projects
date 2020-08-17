package bc;
import java.util.EmptyStackException;
import java.util.Arrays;
public class LinkedStack<T> implements Stack<T>
{
   private Node topNode;
   
   public LinkedStack()
   {
	   topNode=null;
   }
   
   public void push(T newEntry)
   {
	   Node newNode=new Node(newEntry,topNode);
	   topNode=newNode;
   }
   
   public T pop()
   {
	   T top=peek();
	   topNode=topNode.getNextNode();
	   return top;
   }
   
   public T peek()
   {
	   if(isEmpty())
		   throw new EmptyStackException();
	   
	   else
		   return topNode.getData();
   }
   
   public boolean isEmpty()
   {
	   return topNode==null;
   }
   
   public void clear()
   {
	   topNode=null;
   }
   
   private class Node 
   {
      private T data;
      private Node next;
      
      private Node(T dataPortion)
      {
   	   this(dataPortion,null);
      }
      
      private Node(T dataPortion,Node nextNode)
      {
   	   data=dataPortion;
   	   next=nextNode;
      }
      
      private T getData()
      {
   	   return data;
      }
      
      private void setData(T newData)
      {
   	   data=newData;
      }
      
      private Node getNextNode()
      {
   	   return next;
      }
      
      private void setNextNode(Node nextNode)
      {
   	   next=nextNode;
      }
      
   }

}
