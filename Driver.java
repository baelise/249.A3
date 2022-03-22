import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
					System.out.println("Invalid file type, must be .csv or.CSV type");
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
		String[] outFiles = new String[fileNames.length];
		for(int j = 0; j < fileNames.length; j++) {
			try {
				sc = new Scanner(new FileInputStream(fileNames[j]));
				outFiles[j] = ConvertCSVtoHTML(fileNames[j], fileNames);
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
	
	
	public static String ConvertCSVtoHTML(String file, String[] outFiles) {
		// checking if valid csv file
		StringTokenizer st= null;
		String line = null;
		try {
			FileReader fileRead = new FileReader("C:/eliseproulx/eclipse-workspace/Assignment3.249/src");
			BufferedReader inputStream = new BufferedReader(fileRead);
			while((line = inputStream.readLine())!= null) {
				st = new StringTokenizer(line, ",");
				st.nextToken();
			}
		}
		catch (FileNotFoundException e) {
			
		}
		catch (IOException e) {
			
		}
//		catch (CSVAttributeMissing e) {
//			
//		}
//		catch (CSVDataMissing e) {
//			
//		}

		
		
		// creating corresponding html
		String thisFile = "";
		PrintWriter pw = null;
		PrintWriter pwE = null;
		for(int i = 0; i < outFiles.length; i++) {
			try {
				thisFile = outFiles[i].substring(0, (outFiles[i].length())-4);
				thisFile = thisFile + "html";
				System.out.println("This file: " + thisFile);
				pw = new PrintWriter(new FileOutputStream(thisFile), true);
			}
			catch (FileNotFoundException e) {
				System.out.println("Error creating or opening " + thisFile);
			}
		}
		
		try {
			pwE = new PrintWriter(new FileOutputStream("Exceptions.log"), true);
		}
		catch (FileNotFoundException e) {
			System.out.println("Error creating or opening Exceptions.log");
		}
		pw.close();
		pwE.close();
		return "";
	}
}
