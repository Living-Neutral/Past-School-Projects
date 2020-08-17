import java.io.*;
public class HackParser 
{

	// file reader for the input file
	private BufferedReader br;
	
	// current command
	public String current_command="";
	
	// line being assembled
	public String current_line="";
	
	// file being assembeled
	public String user_file;
	
	// curr line number in file
	public int line_num;
	
	
	/*
	 * @javadoc 
	 * Opens the input file/stream and gets ready to parse it 
	*/
	public HackParser(String file) throws FileNotFoundException 
	{
		user_file = file;
		br = new BufferedReader(new FileReader(user_file));
		line_num=0;
	}
	
	/*
	 *Are there more commands in the input? 
	 */
	public Boolean hasMoreCommands() throws IOException
	{
		if(current_line == null) {
			System.out.println("The current line value is:"+current_line);
			return false;
			
		}
		else {
			System.out.println("The current line value is:"+current_line);
			advance();
			return true;
		}
	}
	
	/*
	 *Reads the next command from the input and makes
	 * it the current command
	 * Should only be called if hasMoreCommands() is true.
	 * Initially there is no current command.
	 */
	public void advance() throws IOException
	{
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Entering advancing commands");
		
		try {
		current_line = br.readLine();
		System.out.println("Here is the current line " + current_line); 
		
		current_command = current_line.replaceAll("//.*$", "").trim();
		}
		
		catch(java.lang.NullPointerException nullE) {
			System.err.println("Reached end of file");
		}
		
	}
	
	
	/*
	 * A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
	 * C_COMMAND for dest=comp;jump;
	 * L_COMMAND (actually, psuedo-command) for (Xxx) where Xxx is a symbol.
	 */
	public String commandType() 
	{
		// TODO Auto-generated method stub
		
		if(current_command.contains("@")) {
			return "A_COMMAND";
		}
		
		else if(current_command.startsWith("(")) {
			return "L_COMMAND";	
		}
		
		else {
			return "C_COMMAND";
			
		}
	}
	
	/*
	 * Returns the symbol or decimal Xxx of the current command 
	 * @Xxx or (Xxx). 
	 * Should be called only when A_COMMAND or L_COMMAND
	 */

	public String symbol() 
	{
		// TODO Auto-generated method stub
		String Symbol=Character.toString(current_command.charAt(1));
		Symbol = Symbol.replace(")", "");
		return Symbol;		
	}
	
	
	/*Returns the dest mnemonic in the current C-Command (8 Poss.).
	 * Should be called only when commandType() is C_COMMAND.
	 */

	public String dest() 
	{
		// TODO Auto-generated method stub
		String dest = "";
		if(current_command.contains("=")) {
			String [] instruction = current_command.split("=");
			dest = instruction[0];
		}
		return dest;
	}
	
	/*Returns the comp mnemonic in the current C-command(28 possibilities).
	 * Should be called only when commandType() is C_COMMAND.
	 */
	public String comp() 
	{
		// TODO Auto-generated method stub
		String comp = "";
		if(current_command.contains("=")) 
		{
			String[] instruction = current_command.split("=");
			String[] sub_instruction = instruction[1].split(";");
			comp = sub_instruction[0];
		}
		
		else 
		{
			String[] instruction = current_command.split(";");
			comp = instruction[0];
		}
		
		return comp;
	}
	
	/*Returns the jump mnemonic in the current C-command (8 poss.).
	 * Should be called only when commandType() is C_COMMAND.
	 */


	public String jump() 
	{
		// TODO Auto-generated method stub
		
		String jmp="";
		if(current_command.contains("=")) 
		{
			String [] instruction = current_command.split("=");
			jmp = instruction[1];
		}
		return jmp;
	}
	
	public void close() throws IOException 
	{
		br.close();
		return;
	}

}
