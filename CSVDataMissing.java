
public class CSVDataMissing extends Exception {
	
	public CSVDataMissing() {
		super("\n\n***Error 1: Input row cannot be parsed due to missing information");
	}
	
	public CSVDataMissing(String fileName, int lineNumber, String attribute) {
		System.out.println("Error converting file '" + fileName + "' to HTML at line " + lineNumber + " due to missing data: " + attribute);
	}
	
	public String getMessage(String fileName, int lineNumber, String[] attribute, int index) {
		return "Error, file '" + fileName + "' at line " + lineNumber + " has missing data: " + attribute[index];
	}
}
