import java.util.Scanner;
import java.util.Arrays;

// Driver program for the student task
public class StudentTest 
{
	// Main of the driver handles the 4 choices of add,remove,search and quit
	public static void main(String[] args) 
	{		
    	Scanner kb=new Scanner(System.in);
		char yes,yes1;
		String input,input1;
        Bag<Student> studentBody = new Bag<Student>();
        LinkedBag<Student> studentBody2=new LinkedBag<Student>();
		int BroncoId;
	   do {	
		
		int choice=Menu();

        switch(choice)
        {
        
        // Ask for input of how many students to make and adds to bag
        case 1:
        	int userStudents;        	        		
        		System.out.print("How many students do you want to add?: ");
        		userStudents=kb.nextInt();
        		Student [] add=new Student[userStudents];
        		
        		// this for loop calls a method that returns a student object
        		for(int i=0;i<add.length;i++)
        		{
        			add[i]=studentMaker();
        			studentBody.add(add[i]);
        			studentBody2.add(add[i]);
        		}
        		//continues for as many times that the user enters
        		
        	kb.nextLine();
        	DisplayBag(studentBody,studentBody2);
        	break;
        	
        //Removes a student if the input is yes	
        case 2:       	
        	// removes a student from the bag
        	// wasn't able to get the search id to work so it removes from the end of the bag
        	
            System.out.print("Do you want to remove a student(y/n): ");
        	 input=kb.nextLine();
        	   if((input.charAt(0)=='y')||(input.charAt(0)=='Y')) 
        	   {
               studentBody.remove();
               DisplayBag(studentBody,studentBody2);
        	   }
        	   else	
                DisplayBag(studentBody,studentBody2);                      
        	break;
        	
        // Searches the bag and compares it to the id given 	
        case 3:
        	Student obj=new Student();
        	System.out.print("Enter the id:");
        	int id;
        	id=kb.nextInt();
        	
        	// this boolean was suppose to compare the id to the student objs
        	// however the method keeps returning false
        	
        	boolean Has=studentBody.contains(obj);
        	if(Has)
        	{
        		boolean has_Id =obj.getId()==id;
        		if(has_Id)
        			System.out.println("Found student!");
        	}
        	
        	else
        	{
        		System.out.println("Couldn't not be found!");
        	}
        	 kb.nextLine();
        	break;
        // Quits out of the menu	
        case 4:
        	System.exit(0);
        	break;       	
        }	
      // A while loop that continues through the menu as many times the user wants       
	   System.out.print("Do you want to do anything else(y/n):");	  
	   input1=kb.nextLine();
	   yes1=input1.charAt(0);
	   }while((yes1=='Y')||(yes1=='y'));
     }        		
             
	// creates a student object 
	public static Student studentMaker()
	{	
		String name,Grade;		
		int BroncoId;
		do {
		Scanner kb=new Scanner(System.in);
    	System.out.print("Enter the name of the student: ");
    	name=kb.nextLine();
    	

		System.out.print("Enter the Id of the student:");
    	BroncoId=kb.nextInt();
        kb.nextLine();
    
    	
    	System.out.print("Enter the grade of the student:");
        Grade=kb.nextLine();

	    }while(BroncoId<0);
		
		//creates a student object with the info provided
        Student temp=new Student(BroncoId,name,Grade);
        return temp;
	}
	

	// returns the int to choose the option from the menu
	
	public static int Menu()
	{
		int temp;
		Scanner kb=new Scanner(System.in);
		do {			
		System.out.println("Welcome to class maker!");
		System.out.println("What do you want to do.");
		System.out.println("1.Add a student");
		System.out.println("2.Drop a student");
		System.out.println("3.Search for a student");
		System.out.println("4.Quit");
		System.out.println();		
		System.out.print("Your choice:");
		temp = kb.nextInt();
		}while((temp<1)||(temp>4));
		return temp;		
	}
	
	// returns the student obj in the search_id option
    public static Student studentReturner(BagInterface<Student> bagArray)
    {
    	//Bag<Student> studentBody = new Bag<Student>();
    	Student[] A=bagArray.toArray();
    	Scanner kb=new Scanner(System.in);		    		
        System.out.print("Enter Id of the student that you want to find: ");
        int SearchId=kb.nextInt();  
        for(int k=0;k<A.length;k++)
        {
            // searches the array and comparing the int to the search_id 	
        	if(A[k].getId()==SearchId)
        	{        		
        		System.out.print(A[k]);
        		return A[k];
        	}	
        	
        	// if it can't find the student    
        	else
        	{
        		System.out.println("\n Couldn't find the student!"); 
        		return null;
        	}
        	
        
        }
    	return null;
    }
    
    //Simply finds the students id
    public static void studentFinder(Student[] A)
    {
        //Student [] A=bagArray.toArray();	
    	
        Scanner kb=new Scanner(System.in);		
        System.out.print("Enter Id of the student that you want to find: ");
        int SearchId=kb.nextInt();  
        for(int j=0;j<A.length;j++)
        {
        	if(A[j].getId()==SearchId)
        		System.out.print(A[j]);
        		
        	else        	
        		System.out.println("\n Couldn't find the student!");         
        }    	
    }
    
    //prints out the bag 
	public static void DisplayBag(BagInterface<Student> bagArray,BagInterface<Student> linkedBag)
	{
        Object[] Students=bagArray.toArray();
        Object[] Students2=linkedBag.toArray();
        // to show the two bags I have one for a normal bag
        for(int i=0;i<Students.length;i++)
        {
        	System.out.println("Normal Bag entry");
        	System.out.println(Students[i]);
        	System.out.println();
        }
        // the print for the Linked bag        
        for(int j=0;j<Students2.length;j++)
        {
        	System.out.println("Linked Bag entry");
        	System.out.println(Students2[j]);
        	System.out.println();        	
        }
        
        // prints class size
        if(bagArray.isEmpty())
        	System.out.println();
        else
        	System.out.println("The class size is "+bagArray.getCurrentSize());
        
	}

}
