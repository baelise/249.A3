
public class CSVAttributeMissing extends Exception {
	
	public CSVAttributeMissing() {
		super("\n\n***Error 2: Input row cannot be parsed due to missing information");
	}
	
	public CSVAttributeMissing(String fileName) {
		System.out.println("Error converting file '" + fileName + "' to HTML due to missing attribute.");
	}
	
	public String getMessage(String fileName) {
		return "Error converting file '" + fileName + "' to HTML due to missing attribute.";
	}
}
