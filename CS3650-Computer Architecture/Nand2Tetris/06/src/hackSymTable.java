import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class hackSymTable{

	private HashMap<String, Integer> symbolTable; 
	
	/*
	 * Constructor 
	 * Creates a new empty symbol table
	 */
	public hackSymTable() {
		
		symbolTable = new HashMap<String, Integer>();
		
		// initilize the symbol Table
		for(int i=0;i<16;++i){
			this.addEntry("R" + i,i);
		}
		
		this.addEntry("SP", 0);
		this.addEntry("LCL", 1);
		this.addEntry("ARG", 2);
		this.addEntry("THIS", 3);
		this.addEntry("THAT", 4);
		this.addEntry("SCREEN", 16384);
		this.addEntry("KBD", 24576);		
	}
	
	/*
	 * Adds the pair (symbol, address) to the table. 
	 * 
	 */
	public void addEntry(String symbol, int address) {
		// TODO Auto-generated method stub
		symbolTable.put(symbol, address);		
	}
	
	/*
	 * Does the symbol table contain the given symbol? 
	 */
	public boolean contains(String symbol) {
		// TODO Auto-generated method stub
		return symbolTable.containsKey(symbol);
	}
	
	/*
	 * Returns the address associated with the symbol. 
	 */
	public int getAddress(String symbol) {
		// TODO Auto-generated method stub
		return symbolTable.get(symbol);
	}
	
}
