import java.io.IOException;

// parser for the hack assembler
public interface parser {
	
	public Boolean hasMoreCommands();
	
	public void advance () throws IOException;
	
	public String commandType();
	
	public String symbol();
	
	public String dest();
	
	public String comp();
	
	public String jump();
}
