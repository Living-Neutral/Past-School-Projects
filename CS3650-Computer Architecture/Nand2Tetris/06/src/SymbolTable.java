
public interface SymbolTable {
	
	public void addEntry(String symbol, int address);
	public boolean contains(String symbol);
	public int getAddress(String symbol);
}
