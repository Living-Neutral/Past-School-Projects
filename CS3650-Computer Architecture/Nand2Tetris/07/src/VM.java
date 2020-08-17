import java.util.*;
import java.io.*;


public class VM 
{
	String file_out_path = "";
	
	
	File fileOut;
	
	static CodeWriter code_writer;
	
	static Parser parser;
	
	
	
	public static void main (String []args) throws IOException
	{
		String cwd = System.getProperty("user.dir");
		System.out.println(cwd);
		String file_name = "Main";
		parser = new Parser(file_name+".vm");
		code_writer = new CodeWriter(file_name+".asm");
		
		String type = "";
		int i = 0;
	
		try 
		{
			while(parser.hasMoreCommands() == true)
			{				
				parser.advance();
				
				System.out.println("\nLoop "+i);				
				type = parser.commandType();
				System.out.println("command type:"+parser.commandType());
				System.out.println("current command:" + parser.get_current_command());
				System.out.println("Argument 1:"+parser.arg1());
				System.out.println("Argument 2:"+parser.arg2());
					
				if(type.equals("C_ARITHMETIC"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeArithmetic(parser.arg1());
				}
					
				else if(type.equals("C_PUSH") || type.equals("C_POP"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writePushPop(type, parser.arg1(), parser.arg2());								
				}
					
				else if(type.equals("C_GOTO"))
				{
					code_writer.write_comment_command(parser.get_current_command());
						
					code_writer.writeGoto(parser.arg1());
				}
					
				else if(type.equals("C_FUNCTION"))
				{	
						
					if(parser.arg1().equals("Sys.init"))
					{
						code_writer.write_comment_command("BootStrap");
						code_writer.writeInit();
						break;
					}
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeFunc(parser.arg1(), parser.arg2());				
				}
					
				else if(type.equals("C_LABEL"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeLabel(parser.arg1());
				}
					
			
				else if(type.equals("C_CALL"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeCall(parser.arg1(), parser.arg2());
				}
					
				else if(type.equals("C_IF"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeIF(parser.arg1());				
				}
					
				else if(type.equals("C_RETURN"))
				{
					code_writer.write_comment_command(parser.get_current_command());
					code_writer.writeReturn();
				}
							
				System.out.println();
					
					
				i++;			
			}			

			
		}
		
		catch(NullPointerException NPE)
		{
			System.out.println("Reached the end of the file");
		}
				
		
		
		
				
		code_writer.close();
		
		System.out.println("File created");
	}
	

}
