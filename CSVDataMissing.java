//--------------------------------------------------------------------
// Elise Proulx (40125538) and Andrei Barbulescu (40208635)
// COMP 249 - Section S
// Assignment 3
// 03/25/2022
//--------------------------------------------------------------------
/** CSVDataMissing class. Prints or returns an error message when called*/
public class CSVDataMissing extends Exception {
	
	/**Default Constructor*/
	public CSVDataMissing() {
		super("\n\n***Error 1: Input row cannot be parsed due to missing information");
	}
	/**Constructor will print an error message to console*/
	public CSVDataMissing(String fileName, int lineNumber, String attribute) {
		System.out.println("**Error converting file '" + fileName + "' to HTML at line " + lineNumber + " due to missing data: " + attribute);
	}
	/**Method will return an error message*/
	public String getMessage(String fileName, int lineNumber, String[] attribute, int index) {
		return "**Error, file '" + fileName + "' at line " + lineNumber + " has missing data: " + attribute[index];
	}
}
