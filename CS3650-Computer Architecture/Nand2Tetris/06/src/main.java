import java.util.*;
import java.io.*;

public class main {
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Hack Assembler.");
		System.out.print("");
		File myFile = new File("RectL.asm");
		System.out.println("Attempting to read from file in :"+myFile.getCanonicalPath());
		
		String inputFile = "RectL.asm";
		
		hack_assembler assembler = new hack_assembler(inputFile);
		
		assembler.first_pass();
		assembler.second_pass();
		assembler.close();
		
		System.out.println("Done");
			
		
	}
	
	

}
