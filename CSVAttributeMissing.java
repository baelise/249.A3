//--------------------------------------------------------------------
// Elise Proulx (40125538) and Andrei Barbulescu (40208635)
// COMP 249 - Section S
// Assignment 3
// 03/25/2022
//--------------------------------------------------------------------
/** CSVAttributeMissing class. Prints or returns an error message when called*/
public class CSVAttributeMissing extends Exception {
	
	public CSVAttributeMissing() {
		super("\n\n***Error 2: Input row cannot be parsed due to missing information");
	}
	
	public CSVAttributeMissing(String fileName) {
		System.out.println("**Error converting file '" + fileName + "' to HTML due to missing attribute.");
	}
	
	public String getMessage(String fileName) {
		return "**Error converting file '" + fileName + "' to HTML due to missing attribute.";
	}
}
