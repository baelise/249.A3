import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.File;

public class Driver {
	public static void main(String[] args) {
		// ask user for number of files they would like converted
		boolean validInt = false;
		int numFiles = 2;
		Scanner keyboard = new Scanner(System.in);
		while(!(validInt)) {
			try {
				System.out.println("How many CSV files would you like converted? (2 minimum)");
				numFiles = keyboard.nextInt();
				if(TooSmall(numFiles)) {
					validInt = true;
				}
				else {
					keyboard.nextLine();
					System.out.println("Number must be at least 2");
				}
			}
			catch (InputMismatchException e){
				keyboard.nextLine();
				System.out.println("Invalid input, please try again");
			}
		}
		// ask user for file names
		keyboard.nextLine();
		String fileName;
		String checkType;
		String[] fileNames = new String[numFiles];
		for(int i = 0; i < numFiles; i++) {
			System.out.println("Enter file name " + (i+1) + ":");
			fileName = keyboard.nextLine();
			if(fileName.length() >= 5) {
				checkType = (fileName.substring(fileName.length()-4, fileName.length())).toUpperCase();
				if(checkType.equals(".CSV")) {
					fileNames[i] = fileName;
				}
				else {
					System.out.println("Invalid file type, must be .CSV type");
					fileNames[i] = null;
					i = i -1;
				}
			}
			else {
				System.out.println("Invalid entry. Please enter a CSV file name (.csv or .CSV)");
				i = i -1;
			}
		}	
		// try to convert each file one at a time 
		String[] outFiles = new String[numFiles + 1];
		File inFile = null;
		Scanner sc = null;
		for(int j = 0; j < fileNames.length; j++) {
			try {
				inFile = new File("/Users/eliseproulx/eclipse-workspace/Assignment3.249/allFiles/" + fileNames[j]);
				sc = new Scanner(new FileInputStream(inFile));
				outFiles = ConvertCSVtoHTML(inFile, sc, j, outFiles);
			} 
			catch (FileNotFoundException e) {
				String message = "File '" + fileNames[j] + "' could not be found or opened. Please check if file exists or is readable." + "\nProgram will now end";
				System.out.println(message);
				ExceptionsForCSV(message);
				System.exit(0);
			}
		}
		
		// display one output file 
		System.out.println("Number of HTML files: " + outFiles.length);
		System.out.println("\nList of files you can chose to open: ");
		for(int k = 0; k < outFiles.length; k++) {
			System.out.println("  " + (k+1) + ". " + outFiles[k]);
		}
		
		System.out.println("\nPlease enter a file name: ");
		String selectFile = keyboard.nextLine();
		BufferedReader bR = null;
		String readLine;
		try {
			bR = new BufferedReader(new FileReader("/Users/eliseproulx/eclipse-workspace/Assignment3.249/allFiles/"+selectFile));
			while((readLine = bR.readLine()) != null) {
				System.out.println(readLine);
			}
		}
		catch (IOException e) {
			System.out.println("File not found or invalid file name, please try again");
			tryAgain(keyboard);
		}
		sc.close();
		keyboard.close();
		System.out.println("LEAVING");
	}
	// try again method if user inputs invalid file name for displaying
	public static void tryAgain(Scanner keyboard) {
		System.out.println("Please enter a file name: ");
		String selectFile = keyboard.nextLine();
		BufferedReader bR = null;
		String readLine;
		try {
			bR = new BufferedReader(new FileReader(selectFile));
			while((readLine = bR.readLine()) != null) {
				System.out.println(readLine);
			}	
		}
		catch (IOException e) {
			System.out.println("File not found or invalid file name, program will now terminate");
			System.exit(0);
		}
	}
	// checks to see if user has entered proper number of files
	public static boolean TooSmall(int i) {
		if(i < 2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// logs exceptions only when CSV file cannot be opened
	public static void ExceptionsForCSV(String message) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("/Users/eliseproulx/eclipse-workspace/Assignment3.249/allFiles/Exceptions.log", true), true);
			pw.append("----------------------------------------------------------------------------------------------");
			pw.append("\n" + message);
			pw.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Error creating or opening 'Exceptions.log'");
		}
	}
	
	// formats beginning of html file for convenience
	public static String HTMLFormat(String c, String[] a) {
        String htmlBeginning = """
                <!DOCTYPE html>
                <html>
                <style>
                table {font-family: arial, sans-serif;border-collapse: collapse;}
                td, th {border: 1px solid #000000;text-align: left;padding: 8px;}
                tr:nth-child(even) {background-color: #dddddd;}
                span{font-size: small}
                </style>
                <body>
                
                <table>
                <caption>""";

        String htmlAfterTitle = """
                </caption>
                """;

        String htmlAttFormat =
                "<tr>\n" +
                    "\t<th>"+a[0]+"</th>\n"+
                    "\t<th>"+a[1]+ "</th>\n"+
                    "\t<th>"+a[2]+"</th>\n" +
                    "\t<th>"+a[3]+"</th>\n" +"</tr>\n";

        String start = htmlBeginning + c + htmlAfterTitle + htmlAttFormat;
        return start;
    }

	public static String[] ConvertCSVtoHTML(File file, Scanner inputStream, int fileIndex, String outFiles[]) {
		String caption = null;
		String[] attributes = new String[4];
		String line = null;
		String htmlFile = file.getName().substring(0, (file.getName().length())-3);
		htmlFile = htmlFile + "html";
		outFiles[fileIndex] = htmlFile;
		PrintWriter pw = null;
		PrintWriter pwE = null;
		int lineNum = 0;
		int indexA = 0;
		File oFile = null;
		
		try {
			pwE = new PrintWriter(new FileOutputStream("/Users/eliseproulx/eclipse-workspace/Assignment3.249/allFiles/Exceptions.log", true));
			outFiles[outFiles.length-1] = "Exceptions.log";
			pw.append("----------------------------------------------------------------------------------------------");
		}
		catch (FileNotFoundException e) {
			String message = "Error creating or opening 'Exceptions.log'";
			System.out.println(message);
			System.exit(0);
		}
		try {
			oFile = new File("/Users/eliseproulx/eclipse-workspace/Assignment3.249/allFiles/" + htmlFile);
			pw = new PrintWriter(new FileOutputStream(oFile.getName()), true);
		}
		catch (FileNotFoundException e) {
			String message = "\nError creating '" + htmlFile + "'\nNOT due to missing Attribute or Data";
			pwE.append("\n" + message);
			System.out.println(message);
			pwE.flush();
			pwE.close();
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			String[] lineArr = line.split(",");
			lineNum++;
			System.out.println("LineNum at: " + lineNum);
			if(lineNum == 1) {
				caption = lineArr[0];
				System.out.println(caption);
			}
			if(lineNum == 2) {
				try {
					for(int i  = 0; i < lineArr.length; i++) {
						if(lineArr[i].equals("")) {
							throw new CSVAttributeMissing(file.getName());
						}
						else {
							attributes[i] = lineArr[i];
							System.out.println(attributes[i]);
						}
					}
					pw.write(HTMLFormat(caption, attributes));
				}
				catch (CSVAttributeMissing e) {
					String message = e.getMessage(file.getName());
					pwE.append("\n" + message);
					pwE.flush();
					pwE.close();
					inputStream.close();
					if(!oFile.delete()) {
						System.out.println("Unable to delete file '" + oFile.getName() + "'.");
					}
					System.exit(0);
				}
			}
			if(lineNum > 2) {
				if(lineArr[0].length() >= 5 && lineArr[0].substring(0, 5).equals("Note:")) {
					pw.print("\n<span>" + lineArr[0] + "</span>");
				}
				else {
					try {
						for(int j = 0; j < lineArr.length; j ++) {
							indexA = j;
							if(lineArr[j].equals("")) {
								throw new CSVDataMissing();
							}
						}	
					}
					catch (CSVDataMissing e) {
						String message = e.getMessage(file.getName(), lineNum, attributes, indexA);
						System.out.println(message);
						pwE.append("\n" + message);
						pwE.flush();
						continue;
					}
					pw.print("\n<tr>");
					for(int j = 0; j < lineArr.length; j ++) {
						indexA = j;						
						pw.print("\n	<td>" +lineArr[j]+ "</td>");
					}
					pw.print("\n</tr>");
				}
			}
		}
		pw.print("""
		        \n</body>
		        </html>""");
		pwE.flush();
		pwE.close();
		pw.flush();
		pw.close();
		return outFiles;
	}
}
