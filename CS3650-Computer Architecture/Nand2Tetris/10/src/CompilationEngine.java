import java.io.*;
import java.util.*;

/*Grabs the input from the jack tokenizer and 
 * Writes to the 
 * 
 * */
public class CompilationEngine 
{
	private String file_name;
	
	private String offSet = "	"; // indent space for the tags
	
	private BufferedWriter buffered_writer;
	
	private String another_subRoutine = "";
	
	private String previous_token = "";
	/*Creates a new compilation engine with the given input and output.
	 * The next routine called must be compileClass().
	 * */
	public CompilationEngine(String file) throws IOException
	{
		file_name = file;
		
		buffered_writer = new BufferedWriter(new FileWriter(file_name+".xml"));
		
		buffered_writer.write("<tokens>"); // writes the opening tag to the xml
		buffered_writer.newLine();// newline
		
		System.out.println("Compilation Engine Created");
	}
	
	/*closes the buffered_writer
	 * */
	public void close() throws IOException
	{
		buffered_writer.write("</tokens>");
		buffered_writer.close();
	}
	
	/* 
	 * 
	 * 
	 * */
	public void CompileCode(String token, String type) throws IOException
	{
		if(token.equals("class"))
		{
			buffered_writer.write(offSet+"<class>");
			buffered_writer.newLine();
			
			buffered_writer.write(offSet+offSet+"<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}
		
		else if(token.equals("function") || token.equals("method"))
		{
			buffered_writer.write(offSet+offSet+"<"+"subroutineDec"+">");
			buffered_writer.newLine();
			buffered_writer.write(offSet+offSet+ offSet+"<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}	
		
		
		else 
		{
			buffered_writer.write(offSet+offSet+ offSet +"   "+ "<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}		
	}
	
	/*Compiles a complete class
	 * */
	public void CompileClass(String token, String type) throws IOException
	{
		if(token.equals("class"))
		{
			buffered_writer.write(offSet+"<class>");
			buffered_writer.newLine();
			
			buffered_writer.write(offSet+offSet+"<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}
		
		else if(token.equals("function") || token.equals("method"))
		{
			
			buffered_writer.write(offSet+offSet+"<"+"subroutineDec"+">");
			buffered_writer.newLine();
			buffered_writer.write(offSet+offSet+ offSet+"<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}	
		else 
		{
			buffered_writer.write(offSet+offSet+ offSet +"   "+ "<"+type+">"+token+"</"+type+">");
			buffered_writer.newLine();
		}
	} // end Compile Class
	
    /*Compiles a static declaration or a field declaration
     * */
	public void CompileClassVarDec(String token) throws IOException
	{
		
	}
	
	/*Compiles a complete method, function, or constructor.
	 * */
	public void CompileSubroutine(String token) throws IOException
	{
		
		
	}
	
    /*Compiles a (possibly empty)
     * parameter list, not including the
     * enclosing "()".
     * */
	public void CompileParameterList(String token) throws IOException
	{
		
	}
	
	/*Compiles a var declaration
	 * */
	public void CompileVarDec(String token) throws IOException
	{
		
	}
	
	/*Compiles a sequence of statements,
	 * not including the enclosing "{}".
	 * */
	public void CompileDo(String token) throws IOException
	{
		
	}// end CompileDo
	
	/*Compiles a let statement
	 * 
	 * */
	public void CompileLet(String token) throws IOException
	{
		
	}
	
	
	/*Compiles a while statement.
	 * */
	public void  CompileWhile(String token) throws IOException
	{
		
	}
	
	/*Compiles a return statement
	 * */
	public void CompileReturn() throws IOException
	{
		
	}
	
	/*Compiles an if statement, 
	 * possibly with a trailing else clause.
	 * */
	public void CompileIf() throws IOException
	{
		
	}
	
	/*Compiles an expression
	 * */
	public void CompileExpression() throws IOException
	{
		
	}
	
	/*Compiles a term. This routine is faced
	 * with a slight difficulty when trying
	 * to decide between some of the alternative
	 * parsing rules. Specifically, if the current
	 * token is an identifier, the routine must
	 * distinguish between a variable, an array
	 * entry, and a subroutine call. A single look-
	 * ahead token, which may be one of "[", "(", or "."
	 * suffices to distinguish between the three possibilities.
	 * Any other token is not part of this term and should not
	 * be advanced over.
	 * */
	public void CompileTerm() throws IOException
	{
		
	}
	
	/*Compiles a (possibly empty)
	 * comma-separated list of expressions
	 * */
	public void CompileExpressionList() throws IOException
	{
		
	}
	
	/*finishes up the class writing*/
	public void closeClass() throws IOException
	{
		buffered_writer.write(offSet+"</class>");
		buffered_writer.newLine();
	}
	
}
