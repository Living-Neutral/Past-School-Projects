import java.io.*;
import java.util.*;

public class hack_assembler 
{
	
	// input file
	private String input_file;
    // output file
	private PrintWriter pw;
	
	private hackSymTable hack_table= new hackSymTable();
	
	public hack_assembler(String user_file) throws IOException
	{
		input_file = user_file;
		
		String output_file=input_file.replaceAll("\\..*", "") + ".hack";
		
		pw = new PrintWriter(new FileWriter(output_file));
		
		System.out.println("The assm. input file "+ input_file);
	}
	
	// first pass 
	// builds the symbol table
	// only labels are handled
	public void first_pass() throws FileNotFoundException, IOException
	{
		
		HackParser h_parser = new HackParser(input_file);
		int rom_address = 0;
		String symbol;
		
		System.out.println(); // debug comment
		System.out.println("Entering first pass"); // debug comment
				
		while(h_parser.hasMoreCommands() == true) 
		{
			System.out.println("ROM address:"+ rom_address); // debug comment
			System.out.println("The command type of the pass "+h_parser.commandType()); // debug comment
			
			if(h_parser.commandType().equals("L_COMMAND")) 
			{
				System.out.println("Doing a L_Commmand");
				symbol = h_parser.symbol();
				
				if(!hack_table.contains(symbol)) 				
					hack_table.addEntry(symbol, rom_address);
			}
			
			else 
			{
				rom_address++;
				if(rom_address>32768) 
				{
					System.err.println("Warning: all ROM is in use");
				}
			}
		}
		
		h_parser.close();
		return;
	}
	
	
	public void second_pass() throws FileNotFoundException, IOException
	{
		System.out.println(); // debug
		System.out.println("Entering the Second pass"); // debug
		
		HackParser h_parser = new HackParser(input_file);
		String dest, comp, jump;
		String symbol,value;
		
		int ram_address = 16;
		
		
		while(h_parser.hasMoreCommands()==true) 
		{

			if(h_parser.commandType().equals("C_COMMAND")) 
			{
				dest = h_parser.dest();
				comp = h_parser.comp();
				jump = h_parser.jump();
				
				System.out.println("111" + " comp:" + HackCode.comp(comp) +" dest:" + HackCode.dest(dest)+ " jump:" + HackCode.jump(jump));
				System.out.println("111"+ HackCode.comp(comp)+ HackCode.dest(dest)+ HackCode.jump(jump));
			}
			
			else if(h_parser.commandType().equals("A_COMMAND")) 
			{
				symbol = h_parser.symbol();
				
				if(Character.isDigit(symbol.charAt(0))) 
				{
					value = HackCode.toBinary(symbol);
				}
				
				else if(hack_table.contains(symbol)) 
				{
					value = Integer.toString(hack_table.getAddress(symbol));
					value = HackCode.toBinary(value);
				}
				
				else 
				{
					if(ram_address>16383) 
						System.err.println("Warning: allocating variable in INPUT/OUTPUT memory map");
						
					
					if(ram_address>24576)
						System.err.println("Ran out of RAM");
					
					hack_table.addEntry(symbol, ram_address);
					value = HackCode.toBinary("" +ram_address);
					ram_address++;
				}
				
				System.out.println("0"+value);
			}
		
			
		}
		
		h_parser.close();
		return;
	}
	
	public void close() throws IOException{
		pw.close();
		return;
	}
	
	

}
