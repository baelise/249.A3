
public class CSVDataMissing extends Exception {
	
	public CSVDataMissing() {
		super("\n\n***Error 1: Input row cannot be parsed due to missing information");
	}
	
	public CSVDataMissing(String fileName, int lineNumber, String attribute, String tokenData) {
		System.out.println("Error converting file '" + fileName + "' to HTML at line " + lineNumber + " due to missing data: " + attribute);
//		if(tokenData == "") {
//		}
//		else {
//			
//		}
	}
}
