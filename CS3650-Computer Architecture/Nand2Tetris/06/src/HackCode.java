
public class HackCode
{

	/*
	 * Returns the binary code of the dest mnemonic
	 */
	public static String dest(String mnemonic) 
	{
		// TODO Auto-generated method stub
		String d1 = "0";
		String d2 = "0";
		String d3 = "0";
		
		if (mnemonic.contains("A")) 		
			d1 = "1";
				
		if (mnemonic.contains("D"))
			d2 = "1";
		
		if (mnemonic.contains("M"))
			d3 = "1";
		
		String dest = d1+d2+d3;
	
		return dest;
	}
	
	/*
	 * Returns the binary code of the comp mnemonic (7 bits)
	 */
	public static String comp(String mnemonic) 
	{
		 
		String a = "0";
		
		String c = "000000";
		
		if(mnemonic.contains("M")) 
		{
			a = "1";
			mnemonic = mnemonic.replace("M", "A"); 
		}
		
		switch(mnemonic) 
		{
		case "0":
			c = "101010";
			break;
			
		case "1":
			c = "111111";
			break;
		
		case "-1":
			c = "111010";
			break;
			
		case "D":
			c = "001100";
			break;
			
		case "A":
			c = "110000";
			break;
		
		case "!A":
			c = "110001";
			break;
			
		case "-D":
			c = "001111";
			break;
			
		case "-A":
			c = "110011";
			break;
			
		case "D+1":
			c = "011111";
			break;
			
		case "A+1":
			c = "110111";
			break;
			
		case "D-1":
			c = "001110";
			break;
		
		case "A-1":
			c = "110010";
			break;
			
		case "D+A":
			c = "000010";
			break;
			
		case "D-A":
			c = "010011";
			break;
		
		case "A-D":
			c = "000111";
			break;
			
		case "D&A":
			c = "000000";
			break;
			
		case "D|A":
			c = "010101";
			break;
		}
		
		String comp = a+c;
		
		return comp;
	}
	
	
	/*
	 * Returns the binary code of the jump mnemonic
	 */
	public static String jump(String mnemonic) 
	{
		// TODO Auto-generated method stub
		
		String jump="";
		switch(mnemonic) 
		{
		case "":
			return "000";
		case "JGT":
			return "001";
			
		case "JEQ":
			return "010";
			
		case "JLT":
			return "011";
			
		case "JNE":
			return "101";
			
		case "JLE":
			return "110";
			
		case "JMP":
			return "111";
		}
		
		return jump;
	}
	
	public static String toBinary(String symbol) 
	{
		int value = Integer.valueOf(symbol);
		String binary = Integer.toBinaryString(value);
		return String.format("%1$15s", binary).replace(' ', '0');
	}

}
