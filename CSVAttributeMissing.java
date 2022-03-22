
public class CSVAttributeMissing extends Exception {
	
	public CSVAttributeMissing() {
		super("\n\n***Error 2: Input row cannot be parsed due to missing information");
	}
	
	public CSVAttributeMissing(String message) {
		super(message);
	}

}
