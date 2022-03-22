
public class CSVDataMissing extends Exception {
	
	public CSVDataMissing() {
		super("\n\n***Error 1: Input row cannot be parsed due to missing information");
	}
	
	public CSVDataMissing(String message) {
		super(message);
	}
}
