import java.util.*;
import java.io.*;

public class CodeWriter 
{
	// file writer
	private BufferedWriter buffered_writer;
	
	// output file name
	private String out_file;
	
	private String file_output_path = "";
	
	private int jumpFlag=0;
	
	private int label_counts = 0;
	
	
	/* Opens the output file/stream and gets 
	 * ready to write into it
	 * */
	public CodeWriter(String file) throws IOException
	{
		out_file = file;
		
		buffered_writer = new BufferedWriter(new FileWriter(out_file));
		System.out.println("Code Writer Created");
		
	}
	
	/*Informs the code writer that the 
	 * translation of a new VM file is started
	 * */
	public void setFileName(String filename)
	{
		
	}
	
	/* Writes the assembly code that is the translation
	 * of the given arithmetic command
	 * */
	public void writeArithmetic(String command) throws IOException
	{
		System.out.println("entering arithmetuc");
		
		if(command.equals("add"))
		{
			System.out.println(ArithTemp() + "M=M+D\n");
			buffered_writer.write(ArithTemp() + "M=M+D\n");
		}
		
		else if(command.equals("sub"))
		{
			System.out.println(ArithTemp() + "M=M-D\n");
			buffered_writer.write(ArithTemp() + "M=M-D\n");
		}
		
		else if(command.equals("and"))
		{
			System.out.println(ArithTemp() + "M=M&D\n");
			buffered_writer.write(ArithTemp() + "M=M&D\n");
		}
		
		else if(command.equals("or"))
		{
			System.out.println(ArithTemp() + "M=M|D\n");
			buffered_writer.write(ArithTemp() + "M=M|D\n");
		}
		
		else if(command.equals("gt"))
		{
			System.out.println(jumpTemp("JLE"));
			buffered_writer.write(jumpTemp("JLE"));
			jumpFlag++;
		}
		
		else if(command.equals("lt"))
		{
			System.out.println(jumpTemp("JGE"));
			buffered_writer.write(jumpTemp("JGE"));
			jumpFlag++;
		}
		
		else if(command.equals("eq"))
		{
			System.out.println(jumpTemp("JNE"));
			buffered_writer.write(jumpTemp("JNE"));
			jumpFlag++;			
		}
		
		else if(command.equals("not"))
		{
			
			System.out.println("@SP\n"+"A=M-1\n"+"M=!M\n");		
			buffered_writer.write("@SP\n" +"A=M-1\n"+"M=!M\n");
		}
		
		else if(command.equals("neg"))
		{
			System.out.println("D=0\n" + "A=M-1\n" + "M=D-M\n");	
			buffered_writer.write("D=0\n"+"@SP\n"+"A=M-1\n" + "M=D-M\n");
		}
		
		
	}
	
	/*Writes the assembly code that is the translation 
	 * of the given command, where command is either
	 * C_PUSH or C_POP
	 * */
	public void writePushPop(String command, String segment, int index) throws IOException
	{
		if (command.equals("C_PUSH"))
		{
			
			if(segment.equals("local"))
			{
				System.out.println(pushTemp1("LCL",index,false));
				buffered_writer.write(pushTemp1("LCL",index,false));
			}
			
			else if(segment.equals("constant"))
			{
				System.out.println("@" + index + "\n" + "D=A\n" + "@SP\n" + "A=M\n" + "M=D\n"+ "@SP\n"+ "M=M+1\n");
				buffered_writer.write("@" + index + "\n" + "D=A\n"+"@SP\n" + "A=M\n" + "M=D\n" + "@SP\n" + "M=M+1\n");				
			}
			
			else if(segment.equals("argument"))
			{
				System.out.println(pushTemp1("ARG",index,false));
				buffered_writer.write(pushTemp1("ARG",index,false));
			}
			
			else if(segment.equals("this"))
			{
				System.out.println(pushTemp1("THIS",index,false));
				buffered_writer.write(pushTemp1("THIS",index,false));
			}
				
			else if(segment.equals("that"))
			{
				System.out.println(pushTemp1("THAT",index,false));
				buffered_writer.write(pushTemp1("THAT",index,false));
			}
			
			else if(segment.equals("temp"))
			{
				System.out.println(pushTemp1("R5",index+5,false));
				buffered_writer.write(pushTemp1("R5",index+5,false));
				
			}
			
			else if(segment.equals("pointer") && index == 0)
			{
				System.out.println(pushTemp1("THIS", index, true));
				buffered_writer.write(pushTemp1("THIS", index, true));
			}
			
			else if(segment.equals("pointer") && index ==1)
			{
				System.out.println(pushTemp1("THAT", index, true));
				buffered_writer.write(pushTemp1("THAT", index, true));
			}
			
			
			else if(segment.equals("static"))
			{
				System.out.println(pushTemp1(String.valueOf(16+index),index,true));
				buffered_writer.write(pushTemp1(String.valueOf(16+index),index,true));
			}
			
			
		}
		
		else if(command.equals("C_POP"))
		{
			if(segment.equals("local"))
			{
				System.out.println(popTemp1("LCL",index,false));
				buffered_writer.write(popTemp1("LCL",index,false));
			}
			
			else if(segment.equals("argument"))
			{
				System.out.println(popTemp1("ARG",index,false));
				buffered_writer.write(popTemp1("ARG",index,false));
			}
			
			else if(segment.equals("this"))
			{
				System.out.println(popTemp1("THIS",index,false));
				buffered_writer.write(popTemp1("THIS",index,false));
			}
				
			else if(segment.equals("that"))
			{
				System.out.println(popTemp1("THAT",index,false));
				buffered_writer.write(popTemp1("THAT",index,false));
			}
			
			else if(segment.equals("temp"))
			{
				System.out.println(popTemp1("R5",index + 5,false));
				buffered_writer.write(popTemp1("R5",index +5,false));				
			}
			
			else if(segment.equals("pointer") && index == 0)
			{
				System.out.println(popTemp1("THIS", index, true));
				buffered_writer.write(popTemp1("THIS", index, true));
			}
			
			else if(segment.equals("pointer") && index == 1)
			{
				System.out.println(popTemp1("THAT", index, true));
				buffered_writer.write(popTemp1("THAT", index, true));
			}
			
			
			else if(segment.equals("static"))
			{
				System.out.println(popTemp1(String.valueOf(16+index),index,true));
				buffered_writer.write(popTemp1(String.valueOf(16+index),index,true));
			}
			
		}
		
	}
	
	// the template for the add,sub,and or operations
	public String ArithTemp()
	{
		return "@SP\n"+
	           "AM=M-1\n"+
				"D=M\n"+
	           "A=A-1\n";	            
	}	
	
	// Template for greater than, less than, equal
	public String jumpTemp(String type)
	{
		return "@SP\n"+
		       "AM=M-1\n"+
			   "D=M\n"+
		       "A=A-1\n"+
			   "D=M-D\n"+
		       "@FALSE" + jumpFlag +"\n"+
			   "D;" + type + "\n" +
		       "@SP\n"+
			   "A=M-1\n"+
		       "M=-1\n"+
			   "@CONTINUE"+ jumpFlag + "\n" +
		       "0;JMP\n"+
			   "(FALSE" + jumpFlag + ")\n" +
		       "@SP\n" +
			   "A=M-1\n" +
		       "M=0\n"+
			   "(CONTINUE" + jumpFlag + ")\n";		
	}
	

		
	
	/*
	 * Template for pushing the 
	 * local, this, that,
	 * argument, temp,pointer, static 
	 * mem segments
	 * */
	
	public String pushTemp1(String segment, int index, boolean isDirect)
	{
		String noPointer = "";
		
		if(isDirect==false)
			noPointer = "@" + index+ "\n" + "A=D+A\n" + "D=M\n";
				
		return "@" + segment + "\n" +
		       "D=M\n"+
				noPointer +
				"@SP\n" +
				"A=M\n" +
				"M=D\n" +
				"@SP\n" +
				"M=M+1\n";				
	}
	
	public String popTemp1(String segment, int index, boolean isDirect)
	{
		String noPointer = "D=A\n";
		
		if(isDirect==false)
			noPointer="D=M\n" +"@" +index+"\n"+"D=D+A\n";
		
		return "@" + segment +"\n"+
	           noPointer+
	           "@R13\n"+
	           "M=D\n"+
	           "@SP\n"+
	           "AM=M-1\n"+
	           "D=M\n"+
	           "@R13\n"+
	           "A=M\n"+
	           "M=D\n";          
	}
	
	
	public void writeCall(String func_name, int args) throws IOException
	{
		String return_label= "RETURN_LABEL" + (label_counts++);
		
		String saved_frame = "@SP\n" +
                "D=M\n" +
                "@5\n" +
                "D=D-A\n" +
                "@" + args + "\n" +
                "D=D-A\n" +
                "@ARG\n" +
                "M=D\n" +
                "@SP\n" +
                "D=M\n" +
                "@LCL\n" +
                "M=D\n" +
                "@" + func_name + "\n" +
                "0;JMP\n" +
                "(" + return_label + ")\n";
		
		buffered_writer.write("@" + return_label + "\n" + "D=A\n" + "@SP\n" + "A=M\n" + "M=D\n" + "@SP\n" + "M=M+1\n"); // pushing the ru
		buffered_writer.write(pushTemp1("LCL",0,true));
		buffered_writer.write(pushTemp1("ARG",0,true));
		buffered_writer.write(pushTemp1("THIS",0,true)); 
		buffered_writer.write(pushTemp1("THAT",0,true));
		buffered_writer.write(saved_frame);		
	}
	
	public void writeFunc(String func_name, int args) throws IOException
	{
		String new_label = "(" + func_name +")\n";
		
		buffered_writer.write(new_label);
		
		for(int i=0; i<args;++i)
		{
			writePushPop("C_PUSH","constant",0);				
		}
					
	}
	
	public void writeReturn() throws IOException
	{
		buffered_writer.write(return_template());
	}
	
	public String return_template()
	{
		String end_frame= "@LCL\n" +
	                      "D=M\n"+
				          "@R11\n"+
	                      "M=D";
		
		String return_address = "@5\n"+
		                        "A=D-A\n"+
				                "D=M\n"+
		                        "@R12\n"+
				                "M=D\n";
		
		
		String pointer_Arg = popTemp1("ARG", 0 ,false) ;
		
		String stack_pointer = "@ARG\n"+
				                "D=M\n"+
				                "@SP\n"+
				                "M=D+1\n";
				
		String THAT = saved_frame_template("THAT");
		String THIS = saved_frame_template("THIS");
		String ARG = saved_frame_template("ARG");
		String LCL = saved_frame_template("LCL");
		
		String gotoReturn = "@R12\n"+
		                     "A=M\n"+
				            "0;JMP\n";
		
		return end_frame+return_address+pointer_Arg+stack_pointer+THAT+THIS+ARG+LCL+gotoReturn;
		
	}
	
	public String saved_frame_template(String mem_segment)
	{
		return "@R11\n" +
                "D=M-1\n" +
                "AM=D\n" +
                "D=M\n" +
                "@" + mem_segment + "\n" +
                "M=D\n";	
	}
	
	public void writeInit() throws IOException
	{
		String init = "@256\n"+
	                  "D=A\n"+
				      "@SP\n"+
	                  "M=D\n";
		buffered_writer.write(init);
		
		writeCall("Sys.init", 0);
	}
	
	public void writeLabel(String label) throws IOException
	{
		buffered_writer.write("("+ label + ")\n");		
	}
	
	public void writeGoto(String label) throws IOException
	{
		buffered_writer.write("@" + label + "\n0;JMP\n");		
	}
	
	public void writeIF(String label) throws IOException
	{
		buffered_writer.write(ArithTemp() + "@" + label +"\nD;JNE\n");				
	}

	
	public void write_comment_command (String command) throws IOException
	{
		buffered_writer.write("\t"+"//"+command+"\n");
	}
	
	
	
	
	/*
	 * closes the output file
	*/
	public void close() throws IOException
	{
		buffered_writer.close();
	}
	
}
