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
// import java.io.File;

// public class Driver {
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
// 			System.out.println("Enter file name " + (i+1) + ":");
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
// 		// covidStatistics.csv
// 		// doctorList.csv
// 		String[] outFiles = new String[numFiles + 1];
// 		File inFile = null;
// 		Scanner sc = null;
// 		for(int j = 0; j < fileNames.length; j++) {
// 			try {
// 				inFile = new File("/Users/eliseproulx/eclipse-workspace/Assignment3.249/src/" + fileNames[j]);
// 				//System.out.println(inFile.getAbsolutePath() + ",  " + inFile.getName() + ",  " + inFile.getPath());
// 				sc = new Scanner(new FileInputStream(inFile));
// 				outFiles = ConvertCSVtoHTML(inFile, sc, j, outFiles);
// 				if(outFiles.length == fileNames.length) {
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
// 		System.out.println("Number of HTML files: " + outFiles.length);
// 		System.out.println("List of files you can chose to open: ");
// 		for(int k = 0; k < outFiles.length; k++) {
// 			System.out.println("\n" + outFiles[k]);
// 		}
		
// 		System.out.println("\nPlease enter a file name: ");
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
	
	
// 	public static String[] ConvertCSVtoHTML(File file, Scanner inputStream, int fileIndex, String outFiles[]) {
		
// 		// checking if valid csv file
// 		StringTokenizer st= null;
// 		String line = null;
// 		String[] attributes = new String[4];
// 		String htmlFile = "";
// 		PrintWriter pw = null;
// 		String tokenNote = null;
// 		String tokenCaption = null;
// 		PrintWriter pwE = null;
// 		int indexA = 0;
		
// 		try {
// 			pwE = new PrintWriter(new FileOutputStream("Exceptions.log"), true);
// 			outFiles[outFiles.length-1] = "Exceptions.log";
// 		}
// 		catch (FileNotFoundException e) {
// 			String message = "Error creating or opening 'Exceptions.log'";
// 			System.out.println(message);
// 			System.exit(0);
// 		}
		
// 		int lineNum = 0, lessLine = 0;		
// 		while(inputStream.hasNextLine()) {
// 			line = inputStream.nextLine();
// 			lineNum++;
// 			st = new StringTokenizer(line, ",");
// 			String tokenTest = st.nextToken();
// 			try {
// 				if(lineNum == 1) {
// 					// this is for first line in file, its a single data field "title" row 
// 					tokenCaption = tokenTest;
// 				}
// 				if(lineNum == 2) {
// 					String tokenHead = tokenTest;
// 					while(st.hasMoreTokens()) {		// exception wont be thrown because of while loop, fix
// 						for(int k = 0; k < 4; k++) {
// 							if(tokenHead == "") {
// 								// remove length one from outfiles at correct index (if its 3rd file remove that one)
// 								outFiles = removeFile(outFiles, fileIndex);
// 								throw new CSVAttributeMissing(file.getName());
// 							}
// 							else {
// 								attributes[k] = tokenHead;
// 								if(k < 3) {
// 									tokenHead = st.nextToken(); // change this
// 								}
// 							}
// 						}
// 					}
// 				}
				
// 				if(lineNum > 2) {
// 					if(tokenTest.length() >= 5) {
// 						// this is if the last line contains a note section
// 						if(tokenTest.substring(0, 5).equals("Note:")) {
// 							tokenNote = tokenTest;
// 						}
// 						else {
// 						while(st.hasMoreTokens()) {
// 							String tokenData = tokenTest;
// 							for(int l = 0; l < 4; l++) {
// 								if(tokenData == "") {
// 									indexA = l;
// 									throw new CSVDataMissing(file.getName(), lineNum, attributes[l]); // exception isnt being through because of while loop, fix
// 								}
// 								else {
// 									// creating corresponding html, still need call andreis method
// 									try {
// 										htmlFile = file.getName().substring(0, (file.getName().length())-3);
// 										htmlFile = htmlFile + "html";
// 										pw = new PrintWriter(new FileOutputStream(htmlFile), true);
// 										pw.append("THISTHISTHISTHIS");
// 										outFiles[fileIndex] = htmlFile;
// 										if(l < 3) {
// 											tokenData = st.nextToken(); // have to change this somehow
// 										}
// 									}
// 									catch (FileNotFoundException e) {
// 										String message = "\nError creating '" + htmlFile + "'\nNOT due to missing Attribute or Data";
// 										pwE.append("\n" + message);
// 										System.out.println(message);
// 										pwE.flush();
// 										pwE.close();
// 										System.exit(0);
// 									}
// 								}
// 							}
// 						}
// 					}
// 				}
// 			}
// 		}
// 			// covidStatistics.csv
// 			// doctorList.csv
// 			catch (CSVAttributeMissing e) {
// 				String message = e.getMessage(file.getName());
// 				pwE.append("\n" + message);
// 				pwE.flush();
// 				pwE.close();
// 				inputStream.close();
// 				System.exit(0);
// 			}
// 			catch (CSVDataMissing e) {
// 				String message = e.getMessage(file.getName(), lineNum, attributes, indexA);
// 				pwE.append("\n" + message);
// 				pwE.flush();
// 				lessLine++;
// 				line = inputStream.nextLine(); // skip line with missing data 
// 			}
// 		}
// 		System.out.println("Out of while, LineNum at: " + lineNum + ", LessLine at: " + lessLine);
// 		inputStream.close();
// 		return outFiles;
// 	}
// }
