import java.io.*;
import java.util.ArrayList;


public class JackAnalyzer 
{
	public static void main(String[] args) throws IOException
	{
		JackTokenizer jk_tkn = null;
		
		ArrayList<String> jack_files = new ArrayList<String>();
		
		jack_files = addJackFiles();
		
		System.out.println(jack_files);
		
		try 
		{	
			
			String file_name = "Main";
		    jk_tkn = new JackTokenizer(file_name+".jack"); 			
		    jk_tkn.close();
		}
		
		catch (IOException io) // if the file can't be found
		{
			// Prints the error and where in the stack it occurred
			io.printStackTrace();
		} 
		
		catch(NullPointerException np) // if a null pointer occurs in the construction
		{
			np.printStackTrace();
		}
		
		CompilationEngine comp_eng = new CompilationEngine("Main");
		
		System.out.println("Entering advance loop");
		
		while(jk_tkn.hasMoreTokens())
		{
			jk_tkn.advance();
			System.out.println("Loop "+jk_tkn.getTokenCounter());
			comp_eng.CompileCode(jk_tkn.getCurrentToken() , jk_tkn.tokenType());
			
		}
		comp_eng.closeClass();
		comp_eng.close();
		
		/*
		System.out.println("Entering advance loop"); // entering the loop to handle 
		int i = 2;
		while(jk_tkn.hasMoreTokens())
		{
			jk_tkn.advance();
			System.out.println("Current token:"+jk_tkn.getCurrentToken());
			System.out.println("Loop "+ i +":" + jk_tkn.tokenType());
			switch(jk_tkn.tokenType())
			{
			
			case "KEYWORD":
				System.out.println("current keyword:"+jk_tkn.keyWord());
				break;
			case "SYMBOL":
				System.out.println("current symbol:"+jk_tkn.symbol());
				break;
			case "INT_CONST":
				System.out.println("current INT_CONST:"+jk_tkn.intVal());
				break;
			case "STRING_CONST":
				System.out.println("current STRING_CONST:"+jk_tkn.stringVal());
				break;
			case "IDENTIFIER":
				System.out.println("current IDENTIFIER:"+jk_tkn.identifier());
				System.out.println("Length of identifier:"+jk_tkn.identifier().length());
				break;
			default:
				continue;
			}
			
		
		}		
		i++;
		*/
	}
	
	
	public static ArrayList<String> addJackFiles() throws IOException
	{

		String twd = "C:\\Users\\jffrm\\Documents\\nand2tetris\\projects\\10\\ArrayTest";
		File dir = new File(twd);
		String [] fileList = dir.list();
		
		ArrayList<String> all_jacks = new ArrayList<String>();
		for(String name: fileList)
		{
			//System.out.println(name);
			if(name.contains(".jack"))
			{
				System.out.println(name.substring(0,name.lastIndexOf(".")));
				all_jacks.add(name.substring(0,name.lastIndexOf(".")));
			}				
		}
		
		System.out.println(all_jacks);
		
		return all_jacks;
	}

}
