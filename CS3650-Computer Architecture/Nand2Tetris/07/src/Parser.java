import java.util.*;
import java.io.*;

public class Parser 
{
	// file name
	private String user_file;
	
	// 
	private BufferedReader buffered_reader;
	
	// reference to the current line
	private String current_line;
	
	// reference to the current_command
	private String current_command;
	
	private String command_type;
	
	private String[] arguments = new String[20];
	private String arg1=" ";
	
	private int arg2=-99999;
	
	public static  ArrayList<String> Arithmetic_commands = new ArrayList<String>();
	
	/*@javadoc
	 * Opens the input file/stream and
	 * gets ready to parse it
	 */
	
	public Parser(String file) throws IOException
	{
		
		user_file = file;
		
		buffered_reader = new BufferedReader(new FileReader(user_file));
		
		// assigning the first line
		current_command = buffered_reader.readLine();
		
		// sets the arguments of the 
		arguments = current_command.split(" ");
		
		// assigning the command type
		command_type = "blank";

		// adding to the table 
		Arithmetic_commands.add("add");
		Arithmetic_commands.add("sub");
		Arithmetic_commands.add("neg");
		Arithmetic_commands.add("eq");
		Arithmetic_commands.add("gt");
		Arithmetic_commands.add("lt");
		Arithmetic_commands.add("and");
		Arithmetic_commands.add("or");
		Arithmetic_commands.add("not");
					
		// declaring construction 
		System.out.println("Parser contsructed");
	}
	
	/*Are there more commands in the input?
	 */
	public boolean hasMoreCommands() 
	{
		return !(current_command==null);
	}
	
		
	/*
     * Reads the next command from the input and makes it
     * the current command
     * */
	public void advance() throws IOException 
	{	

		current_command = buffered_reader.readLine();
		
		
		if(current_command.contains("//"))
			current_command = removeComments(current_command);
					
		
		arguments=current_command.split(" ");
		
		System.out.println();
		for(int i = 0;i<arguments.length ; ++i)
		{
			System.out.println("Argument: " + i +" "+ arguments[i]);
		}
		
		if(commandType().equals("C_PUSH") || commandType().equals("C_POP")) 
		{
			arguments[2] = arguments[2].trim();
			arg1 = arguments[1];
			arg2 = Integer.parseInt(arguments[2]);
		}
			
		else if(commandType().equals("C_ARITHMETIC"))
		{
			
			arg1 = arguments[0];
		}
		
		else if(commandType().equals("C_FUNCTION"))
		{
			arg1 = arguments[1];
		}
		
		else if(commandType().equals("C_LABEL"))
		{
			arg1 = arguments[1];
		}
		
		else if(commandType().equals("C_IF"))
		{
			arg1 = arguments[1];			
		}
		
		else if(commandType().equals("C_GOTO"))
		{
			arg1 = arguments[1];
			
		}
		
		else if(commandType().equals("C_CALL"))
		{
			arg1 = arguments[1];
			arg2 = Integer.parseInt(arguments[2]);
		}						

				
	}
	
	/*Returns the type of the current VM command
	 * C_ARITHMETIC is returned
	 * for all the arithmetic commands
	 * */
	public String commandType()
	{
		
		if( Arithmetic_commands.contains(arguments[0]))
			command_type = "C_ARITHMETIC";
		
		else if(arguments[0].equals("push"))
			command_type = "C_PUSH";
		
		else if(arguments[0].equals("pop"))
			command_type = "C_POP";
		
		else if (arguments[0].equals("function"))
			command_type = "C_FUNCTION";
		
		else if (arguments[0].equals("call"))
			command_type = "C_CALL";
		
		else if (arguments[0].equals("if"))
			command_type = "C_IF";
		
		else if (arguments[0].equals("label"))
			command_type = "C_LABEL";
		
		else if(arguments[0].equals("call"))
			command_type = "C_CALL";
		
		else if(arguments[0].equals("goto"))		
			command_type = "C_GOTO";
		
		else if(arguments[0].equals("if-goto"))
			command_type = "C_IF";
		
		else if(arguments[0].equals("return"))
			command_type = "C_RETURN";
		else
			command_type=" comment/blank";
				
		return command_type;
	}
	
	/*
     * Returns the first argument of the current command
     * In the case of C_ARITHMETIC, the command itself(add, sub, etc.) 
     * is returned. Should not be called if the current command is C_RETURN
     * */
	public String arg1()
	{
	 
       return arg1;
	}
	
	
	/*Returns the second argument of the current
	 * command. Should be called only 
	 * if the current command is C_PUSH,C_POP
	 * C_FUNCTION, or 
	 * C_CALL
     * */
	public int arg2()
	{			
		return arg2;
	}
	
	private String removeComments(String COMMAND_LINE)
	{
		String[] without_comments = COMMAND_LINE.split("//"); 
		return  without_comments[0];
	}
	
	public String get_current_line()
	{
		return current_line+"\n";
	}
	
	public String get_current_command()
	{
		return current_command;
	}
	
	public String[] getArguments()
	{
		return arguments;
	}

}
