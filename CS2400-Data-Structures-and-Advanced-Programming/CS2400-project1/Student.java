
public class Student 
{
	private int studentId;
	private String name;
	private String academic_level;

	public Student()
	{
	this(0, "", "");
	}
	
	public Student(int num,String n,String grade)
	{
	   studentId=num;
	   name=n;
	   academic_level=grade;
	}
	
	public void setId(int num)
	{
	   studentId=num;
	}
	
	public void  setName(String given)
	{
		name= given;
	}

	
	public void setGradeLevel(String grade)
	{
		academic_level=grade;
	}
	
	public int getId()
	{
	   return studentId;
	}
	
	public String FirstName()
	{
	   return name;	
	}

	
	public String getGrade()
	{
	   return academic_level;	
	}
	
	public String toString()
	{
		String str="Name: "+ name+"\n"
				+"Id : "+ studentId+"\n"
				+"Grade: "+ academic_level;
		return str;
	}
}
