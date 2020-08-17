import java.util.*;
import java.io.*;
/*
 * Implements the Tokenizer for the jack language
 * 
 * */
public class JackTokenizer 
{
	private BufferedReader buffered_reader;
	

	private String user_file;
	
	private String current_commands;
	
	private String current_token="";
	
	private String previous_token="";
	
	private int token_counter;
	
	private String token_type;
	
	private List<String> possible_tokens = new ArrayList<String>();
	
	
	private static HashSet<String> KeyWordSet = new HashSet<String>();
	
	private static HashSet<String> symbolSet = new HashSet<String>();
	
		
	/*Opens the input file/stream and gets
	 * ready to tokenize it.
	 * */
	public JackTokenizer(String file) throws IOException
	{
		user_file = file;
				
		buffered_reader = new BufferedReader(new FileReader(user_file));
		
		initKeyWords();
		initSymbols();
		fillTokens();
		System.out.println("JackTokenizer created.");	
	}
	
				

	
	public void fillTokens() throws IOException
	{
		current_commands = buffered_reader.readLine();		
		while(current_commands!=null)
		{
			
			if(hasComments())
			{
				current_commands = removeComments(current_commands);
			}
			
			tokenizedLine(current_commands);
			
			if(possible_tokens!=null)
			{
				System.out.println(possible_tokens);	
			}
			current_commands = buffered_reader.readLine();
		}
		
	}// end fill tokens
	
	
	public boolean hasMoreTokens()
	{
		return token_counter<possible_tokens.size();
	}
	
	/*Gets the next token from the input and
	 * makes it the current token. This method 
	 * should only be called if hasMoreToken() is 
	 * True. Initially there is no current token
	 * */
	public void advance() throws IOException
	{
		current_token = possible_tokens.get(token_counter).toString(); // grabs the current token value
		
		if(token_counter>1) // to ensure no null values 
			previous_token = possible_tokens.get(token_counter-1).toString(); // grabs the prev. tkn value
		
		System.out.println();// space
		
		if(KeyWordSet.contains(current_token)) // found a Key word
		{
			token_type = "keyword";
		}
			
		else if(symbolSet.contains(current_token)) // found a symbol 
		{
			token_type = "symbol";
		}
			
		else // not a Keyword nor a symbol
		{
			
			if(isNumeric(current_token)) // found a const int
			{
				token_type = "int_const";
			}
			
			else if(previous_token.equals("\"")) // found a string const
			{
				token_type = "string_const";
			}
			
			else 
			{
				token_type = "idenifier";
			}
			
		}
		token_counter++;		
	}// end advance
	
	
	public String getCurrentToken()
	{
		return current_token;
	}// end current token
	
	public String getPreviousToken()
	{
		return previous_token;
	}// end previous token
	/*
	 * Returns the type of the current token.
	 */
	public String tokenType()
	{
		return token_type;	
	}//end keyword
	
	/*Returns the keyword which is the current
	 * token. Should be called only when tokenType()
	 * is KEYWORD
	 * */	
	public String keyWord()
	{
		return current_token;
	}// end keyword
	
	/*Returns the keyword which is the
	 * current token. Should be called only 
	 * when tokenType is KEYWORD.
	 * */
	
	public char symbol()
	{
		return current_token.charAt(0); // since it's just one char
	}
	
	/*Returns the character which is
	 * the current token. Should be called
	 * only when tokenType() is IDENTIFIER.
	 * 
	 */
	public String identifier()
	{
		return current_token;
	}// end String identifier
	
	/*Returns the integer value of
	 * of the current token. Should only
	 * be called only when tokenType() 
	 * is INT_CONST.
	 * */
	public int intVal()
	{
		return Integer.parseInt(current_token);
	}// end intval()
	
	/*Returns the string value of the current
	 * token, without the double quotes.
	 * Should be called when tokenType() is
	 * STRING_CONST.
	 */
	public String stringVal()
	{
		return current_token;
	}// end string val
	
	
	/*Returns the current read command of the tokenizer
	 * 
	 * */
	public String getCurrentCommand()
	{
		return current_commands;
	}// end current command
	
	
	/*Removes the comments from a line*/
	public String  removeComments(String commands)
	{
		String no_comments = commands;
		int offset;// index for finding the value of where the offset should happen
		
		if(commands.startsWith(" *")) // finds the nth line of multi-line comments
			offset = commands.indexOf("*"); // grabs the index of where the symbol appears
		else if(commands.startsWith("/*")) // finds the 1st line of multi-line comments
			offset = commands.indexOf("/*");
		else 
			offset = commands.indexOf("//"); // finds the offset for single line comments
		no_comments = commands.substring(0,offset).trim(); // removes the comment
		
		return no_comments; 
	}// end of remove comments
	
	
	
	/*determines if the current command has comments*/
	public boolean hasComments()
	{
		return (current_commands.contains("//") || current_commands.contains("/*") || current_commands.contains(" *"));
	}
	
	
	/*adds the tokens found on the line
	 * Goes through the line one by one and
	 * adds the tokens 
	 * */
	public void tokenizedLine(String current_commands)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<current_commands.length();++i)
		{
			/*have to fix the conditional for detecting puncuation in string const 
			 * works for now
			 * */
			if(Character.isLetterOrDigit(current_commands.charAt(i)) || current_commands.charAt(i)=='?') // handles alpha chars
			{
				sb.append(current_commands.charAt(i)); 
				if(KeyWordSet.contains(sb.toString())) // a key word was found
				{
					possible_tokens.add(sb.toString()); // adding the keyword to the possible tokens list 
					sb.setLength(0);// resetting the length for a new symbol or word
				}
				
				
			}
			
			else if(String.valueOf(current_commands.charAt(i)).matches("[^a-zA-Z0-9\\s]")) // handles non-alpha chars
			{
				
				possible_tokens.add(sb.toString());// adds the current string from before the symbol 
				sb.setLength(0); // resets the length
				
				
				sb.append(current_commands.charAt(i)); // adds the symbol
				
				if(symbolSet.contains(sb.toString())) // checks the symbol
				{
					possible_tokens.add(sb.toString()); // adding symbol to the tokens list
					sb.setLength(0); // resetting the symbol found
				}
			}		
			
		System.out.println("Loop "+i+"'s StringBuilder:"+sb.toString());	
		}	
	}//return token_canidate;
	
	public int getTokenCounter()
	{
		return token_counter;
	}
	public String getFileName()
	{
		return user_file;
	}
		public void close() throws IOException
	{
		buffered_reader.close();
	}
	
	private void initKeyWords()
	{
		KeyWordSet.add("class");
		KeyWordSet.add("constructor");
		KeyWordSet.add("function");
		KeyWordSet.add("method");
		KeyWordSet.add("int");
		KeyWordSet.add("boolean");
		KeyWordSet.add("char");
		KeyWordSet.add("void");
		KeyWordSet.add("var");
		KeyWordSet.add("static");
		KeyWordSet.add("field");
		KeyWordSet.add("let");
		KeyWordSet.add("do");
		KeyWordSet.add("if");
		KeyWordSet.add("else");
		KeyWordSet.add("while");
		KeyWordSet.add("return");
		KeyWordSet.add("true");
		KeyWordSet.add("false");
		KeyWordSet.add("null");
		KeyWordSet.add("this");
	} // end init Key words;
	
	
	private void initSymbols()
	{
		symbolSet.add("{");
		symbolSet.add("}");
		symbolSet.add("(");
		symbolSet.add(")");
		symbolSet.add("[");
		symbolSet.add("]");
		symbolSet.add(".");
		symbolSet.add(",");
		symbolSet.add(";");
		symbolSet.add("+");
		symbolSet.add("-");
		symbolSet.add("*");
		symbolSet.add("/");
		symbolSet.add("&");
		symbolSet.add("~");
		symbolSet.add("|");
		symbolSet.add("<");
		symbolSet.add(">");
		symbolSet.add("=");
		symbolSet.add("\"");
	} // end init symbols
	
	private boolean isNumeric(String strNum)
	{
		if(strNum==null)
		{
			return false;
		}
		
		try {
			int d = Integer.parseInt(strNum);
		}
		
		catch(NumberFormatException nfe)
		{
			return false;
		}	
		
		return true;
	}


}
