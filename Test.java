import java.io.*;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		/*
		String folderPath = "C:\\Users\\I859415\\Desktop\\side_projects\\testlog.log";
		
		try(BufferedReader br = new BufferedReader(new FileReader(folderPath))) {
			String currLine;
			while ((currLine = br.readLine()) != null) {
				System.out.println(currLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		String folderPath = 
		"/Users/Oleg/Desktop/side_projects/java/EasyRead";
		EasyRead er = new EasyRead(folderPath);
		System.out.println("\nTESTING readFile: ");
		List<String> listOfLines = er.readFile(folderPath + "/testlog.log");
		listOfLines.forEach(item->System.out.println(item));
		System.out.println();
		System.out.println("\nTESTING getFiles: ");
		File[] filesList = er.getFiles(true);
		System.out.println("\nTESTING doesExist: ");
		String line = "15:52:13.768	loglevel:    [5]";
		er.doesExist(line, "15:52");
		System.out.println("\nTESTING writeToFile: ");
		er.writeToFile("outputTest.txt", "TEST");
		er.writeToFile("outputTest.txt", "TEST1");
		System.out.println("\nTESTING addSearchWord: ");
		er.addSearchWord("ERR"); 	
		System.out.println("\nTESTING readFilesInDir: ");
		er.readFilesInDir(folderPath + "/outputTest.txt");
	}
}