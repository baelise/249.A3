import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Driver {
	public static void main(String[] args) {
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
		keyboard.nextLine();
		String fileName;
		String checkType;
		String[] fileNames = new String[numFiles];
		for(int i = 0; i < numFiles; i++) {
			System.out.println("Enter file name " + i + ":");
			fileName = keyboard.nextLine();
			if(fileName.length() >= 5) {
				checkType = (fileName.substring(fileName.length()-4, fileName.length())).toUpperCase();
				if(checkType.equals(".CSV")) {
					fileNames[i] = fileName;
				}
				else {
					System.out.println("Invalid file type, must be .CSV type");
					i = i -1;
				}
			}
			else {
				System.out.println("Invalid entry. Please enter a CSV file name (.csv or .CSV)");
				i = i -1;
			}
		}
		keyboard.close();
		
		//File inFile = new File;
		Scanner sc = null;
		//String[] outFiles = new String[fileNames.length];
		for(int j = 0; j < fileNames.length; j++) {
			try {
				sc = new Scanner(new FileInputStream(fileNames[j]));
				ConvertCSVtoHTML(fileNames[j], fileNames);
			} 
			catch (FileNotFoundException e) {
				System.out.println("File '" + fileNames[j] + "' could not be found or opened. Please check if file exists or is readable.");
				System.out.println("Program will now end");
				System.exit(0);
			}
		}
		
		//ConvertCSVtoHTML(fileNames);
		sc.close();
		System.out.println("LEAVING");
	}
	
	
	public static boolean TooSmall(int i) {
		if(i < 2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public static void ConvertCSVtoHTML(String file, String[] outFiles) {
		// checking if valid csv file
		StringTokenizer st= null;
		String line = null;
		String[] attributes = new String[4];
		//String[][] data = null;
		String htmlFile = "";
		PrintWriter pw = null;
		//PrintWriter pwE = null; // this is for Exceptions.log later
		try {
			int lineNum = 0, lessLine = 0;
			String strFile = "C:/eliseproulx/eclipse-workspace/Assignment3.249/src/" + file;
			FileReader fileRead = new FileReader(strFile);
			BufferedReader inputStream = new BufferedReader(fileRead);
			while((line = inputStream.readLine())!= null) {
				lineNum++;
				for(int k = 0; k < 5; k++) {
					st = new StringTokenizer(line, ",");
					try {
						if(lineNum == 1) {
							String tokenHead = st.nextToken();
							if(tokenHead == "") {
								// remove length one from outfiles 
								throw new CSVAttributeMissing(strFile);
							}
							else {
								attributes[k] = tokenHead;
							}
						}
						else {
							String tokenData = st.nextToken();
							if(tokenData == "") {
								//line = inputStream.readLine(); 
								throw new CSVDataMissing(strFile, lineNum, attributes[k], tokenData);
							}
							else {
								// creating corresponding html
								for(int i = 0; i < outFiles.length; i++) {
									try {
										htmlFile = outFiles[i].substring(0, (outFiles[i].length())-4);
										htmlFile = htmlFile + "html";
										System.out.println("This file: " + htmlFile);
										pw = new PrintWriter(new FileOutputStream(htmlFile), true);
										pw.write("**  " + tokenData + "  **");
									}
									catch (FileNotFoundException e) {
										System.out.println("Error creating or opening " + htmlFile);
									}
								}
							}
						}
					}
					catch (CSVAttributeMissing e) {
						inputStream.close();
						fileRead.close();
						System.exit(0);
					}
					catch (CSVDataMissing e) {
						lessLine++;
						line = inputStream.readLine(); // should skip line with missing data 
					}
				}
				//pw.println();
			}
			System.out.println("out of while, lineNum at: " + lineNum + ", lessLine at: " + lessLine);
			inputStream.close();
			fileRead.close();
			//data = new String[lineNum - lessLine - 1][4];
		}
		catch (FileNotFoundException e) {
		System.exit(0);
		// here print to Exceptions.log??
		}
		catch (IOException e) {
			System.exit(0);
			// here print to Exceptions.log??
		}
	}
}
