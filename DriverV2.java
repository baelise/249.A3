// import java.util.Scanner;
// import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.InputMismatchException;
// import java.util.StringTokenizer;

// public class DriverV2 {
// 	public static void main(String[] args) {
// 		boolean validInt = false;
// 		int numFiles = 2;
// 		Scanner keyboard = new Scanner(System.in);
// 		while(!(validInt)) {
// 			try {
// 				System.out.println("How many CSV files would you like converted? (2 minimum)");
// 				numFiles = keyboard.nextInt();
// 				if(TooSmall(numFiles)) {
// 					validInt = true;
// 				}
// 				else {
// 					keyboard.nextLine();
// 					System.out.println("Number must be at least 2");
// 				}
// 			}
// 			catch (InputMismatchException e){
// 				keyboard.nextLine();
// 				System.out.println("Invalid input, please try again");
// 			}
// 		}
// 		keyboard.nextLine();
// 		String fileName;
// 		String checkType;
// 		String[] fileNames = new String[numFiles];
// 		for(int i = 0; i < numFiles; i++) {
// 			System.out.println("Enter file name " + i + ":");
// 			fileName = keyboard.nextLine();
// 			if(fileName.length() >= 5) {
// 				checkType = (fileName.substring(fileName.length()-4, fileName.length())).toUpperCase();
// 				if(checkType.equals(".CSV")) {
// 					fileNames[i] = fileName;
// 				}
// 				else {
// 					System.out.println("Invalid file type, must be .CSV type");
// 					i = i -1;
// 				}
// 			}
// 			else {
// 				System.out.println("Invalid entry. Please enter a CSV file name (.csv or .CSV)");
// 				i = i -1;
// 			}
// 		}
		
// 		String[] htmlFiles = new String[numFiles + 1];
// 		Scanner sc = null;
// 		for(int j = 0; j < fileNames.length; j++) {
// 			try {
// 				sc = new Scanner(new FileInputStream(fileNames[j]));
// 				htmlFiles = ConvertCSVtoHTML(fileNames[j], fileNames, j, htmlFiles);
// 				if(htmlFiles.length == fileNames.length) {
// 					fileNames = removeFile(fileNames, j);
// 				}
// 			} 
// 			catch (FileNotFoundException e) {
// 				String message = "File '" + fileNames[j] + "' could not be found or opened. Please check if file exists or is readable." + "\nProgram will now end";
// 				System.out.println(message);
// 				ExceptionsForCSV(message);
// 				System.exit(0);
// 			}
// 		}		
		
// 		// finally, display one output file 
// 		System.out.println("Number of HTML files: " + htmlFiles.length);
// 		System.out.println("List of files you can chose to open: ");
// 		for(int k = 0; k < htmlFiles.length; k++) {
// 			System.out.println("\n" + htmlFiles[k]);
// 		}
		
// 		System.out.println("Please enter a file name: ");
// 		String selectFile = keyboard.nextLine();
// 		try {
// 			FileReader fileRead = new FileReader(selectFile);
// 			BufferedReader inputStream = new BufferedReader(fileRead);
// 			display(selectFile, inputStream);
// 		}
// 		catch (FileNotFoundException e) {
// 			System.out.println("File not found or invalid file name, please try again");
// 			tryAgain(keyboard);
// 		}
// 		sc.close();
// 		keyboard.close();
// 		System.out.println("LEAVING");
// 	}
	
	
// 	public static boolean TooSmall(int i) {
// 		if(i < 2) {
// 			return false;
// 		}
// 		else {
// 			return true;
// 		}
// 	}
// 	public static String[] removeFile(String[] fileArr, int index) {
// 		fileArr[index] = "REMOVE ME";
// 		String[] tempArr = new String[fileArr.length];
// 		for(int i = 0; i < fileArr.length; i++) {
// 			tempArr[i] = fileArr[i];
// 		}
		
// 		String[] newArr = new String[fileArr.length - 1];
// 		for(int j = 0; j < newArr.length; j++) {
// 			if(!(fileArr[j].equals("REMOVE ME"))) {
// 				newArr[j] = fileArr[j];
// 			}
// 		}
// 		return newArr;
// 	}
// 	public static void tryAgain(Scanner keyboard) {
// 		System.out.println("Please enter a file name: ");
// 		String selectFile = keyboard.nextLine();
// 		try {
// 			FileReader fileRead = new FileReader(selectFile);
// 			BufferedReader inputStream = new BufferedReader(fileRead);
// 			display(selectFile, inputStream);
// 		}
// 		catch (FileNotFoundException e) {
// 			System.out.println("File not found or invalid file name, program will now terminate");
// 			System.exit(0);
// 		}
// 	}
	
// 	public static void display(String file, BufferedReader bR) {
// 		String line = null;
// 		try {
// 			while((line = bR.readLine())!= null) {
// 				System.out.println(line + "\n");
// 			}
// 		}
// 		catch (IOException e) {
			
// 		}
// 	}
	
// 	// logs exceptions only when CSV file cannot be opened
// 	public static void ExceptionsForCSV(String message) {
// 		PrintWriter pw = null;
// 		try {
// 			pw = new PrintWriter(new FileOutputStream("Exceptions.log"), true);
// 			pw.append(message);
// 			pw.close();
// 		}
// 		catch (FileNotFoundException e) {
// 			System.out.println("Error creating or opening 'Exceptions.log'");
// 		}
// 	}
	
// 	public static String[] ConvertCSVtoHTML(String file, String[] userFiles, int fileIndex, String outFiles[]) {
		
// 		// checking if valid csv file
// 		StringTokenizer st= null;
// 		String line = null;
// 		String[] attributes = new String[4];
// 		String htmlFile = "";
// 		PrintWriter pw = null;
// 		String tokenNote = null;
// 		PrintWriter pwE = null;
		
// 		try {
// 			pwE = new PrintWriter(new FileOutputStream("Exceptions.log"), true);
// 			outFiles[outFiles.length-1] = "Exceptions.log";
// 		}
// 		catch (FileNotFoundException e) {
// 			String message = "Error creating or opening 'Exceptions.log'";
// 			System.out.println(message);
// 			System.exit(0);
// 		}
		
// 		try {
// 			int lineNum = 0, lessLine = 0;
// 			String strFile = "C:/eliseproulx/eclipse-workspace/Assignment3.249/src/" + file;
// 			FileReader fileRead = new FileReader(strFile);
// 			BufferedReader inputStream = new BufferedReader(fileRead);
// 			while((line = inputStream.readLine())!= null) {
// 				lineNum++;
// 				for(int k = 0; k < 5; k++) {
// 					st = new StringTokenizer(line, ",");
// 					String tokenTest = st.nextToken();
// 					try {
// 						if(lineNum == 1) {
// 							// this is for first line in file, its a single data field "title" row 
// 							String tokenCaption = tokenTest;
// 							k = 5;
// 						}
// 						if(lineNum == 2) {
// 							String tokenHead = tokenTest;
// 							if(tokenHead == "") {
// 								// remove length one from outfiles at correct index (if its 3rd file remove that one)
// 								outFiles = removeFile(outFiles, fileIndex);
// 								throw new CSVAttributeMissing(file);
// 							}
// 							else {
// 								attributes[k] = tokenHead;
// 							}
// 						}
// 						if(tokenTest.length() >= 5) {
// 							// this is if the last line contains a note section
// 							if(tokenTest.substring(0, 4).equals("Note:")) {
// 								tokenNote = tokenTest;
// 							}
// 						}
// 						else {
// 							String tokenData = tokenTest;
// 							if(tokenData == "") {
// 								throw new CSVDataMissing(file, lineNum, attributes[k]);
// 							}
// 							else {
// 								// creating corresponding html need to add html format method from andrei
// 								try {
// 									htmlFile = outFiles[fileIndex].substring(0, (outFiles[fileIndex].length())-4);
// 									htmlFile = htmlFile + "html";
// 									System.out.println("This file: " + htmlFile);
// 									pw = new PrintWriter(new FileOutputStream(htmlFile), true);
// 									outFiles[fileIndex] = htmlFile;
// 								}
// 								catch (FileNotFoundException e) {
// 									String message = "\nError creating '" + htmlFile + "'";
// 									pwE.append("\n" + message);
// 									System.out.println(message);
// 									pwE.flush();
// 									pwE.close();
// 									System.exit(0);
// 								}
// 							}
// 						}
// 					}
					
// 					catch (CSVAttributeMissing e) {
// 						String message = e.getMessage(file);
// 						pwE.append("\n" + message);
// 						pwE.flush();
// 						pwE.close();
// 						inputStream.close();
// 						fileRead.close();
// 						System.exit(0);
// 					}
// 					catch (CSVDataMissing e) {
// 						String message = e.getMessage(file, lineNum, attributes[k]);
// 						pwE.append("\n" + message);
// 						pwE.flush();
// 						lessLine++;
// 						line = inputStream.readLine(); // skip line with missing data 
// 					}
// 				}
// 			}
// 			System.out.println("out of while, lineNum at: " + lineNum + ", lessLine at: " + lessLine);
// 			inputStream.close();
// 			fileRead.close();
// 			//return outFiles;
// 		}
// 		catch (FileNotFoundException e) {
			
// 		System.exit(0);
// 		}
// 		catch (IOException e) {
// 			System.exit(0);
// 		}
// 		return outFiles;
// 	}
// }

