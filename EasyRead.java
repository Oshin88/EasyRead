import java.io.*;
import java.util.regex.*;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.*;


// on hold for now
public class EasyRead {
	private String folderPath;
	private File folder;
	private ArrayList<String> keyWords = new ArrayList<String>();

	public EasyRead(String folderPath) {
		this.folderPath = folderPath;
		this.folder = new File(this.folderPath);
	}

	// add more key words
	public void addSearchWord(String searchWord) {
		this.keyWords.add(searchWord);
	}

	// read contents of the file line by line
	public List<String> readFile(String filePath) {
		//String folderPath = "C:\\Users\\I859415\\Desktop\\side_projects\\testlog.log";
		List<String> list = new ArrayList<>();

		try(BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
			//br returns as stream and convert it into List
			list = br.lines().collect(Collectors.toList());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return list;
		//list.forEach(System.out::println);
	}

	// read contents of the file line by line and filter them out based on the key words
	public void filterFileContent(String filePath, String outputFile) {
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String currLine;
			while((currLine = br.readLine()) != null) {
				for(String searchWord : keyWords) {
					if(this.doesExist(currLine, searchWord)) {
						this.writeToFile(outputFile, currLine);
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// get all the file paths
	public File[] getFiles(Boolean displayResults) {
		File[] listOfFiles = this.folder.listFiles();

		if(displayResults) {
			for (int i = 0; i < listOfFiles.length; i++) {
		    	if (listOfFiles[i].isFile()) {
		        	System.out.println("File: " + listOfFiles[i].getName());
		      	} else if (listOfFiles[i].isDirectory()) {
		        	System.out.println("Directory: " + listOfFiles[i].getName());
		      	}
	    	}
		}
	    return listOfFiles;
	}

	//check if the string contains the provided word
	public Boolean doesExist(String line, String pattern) {
		Pattern patternObj = Pattern.compile(pattern);
		Matcher matcher = patternObj.matcher(line);
		if(matcher.find()) {
			//System.out.println(line);
			return true;
		} else {
			//System.out.println("No Results Found.");
			return false;
		}
	}

	// output param 'line' to the specified file
	public void writeToFile(String strFilePath, String line) {
		Path filePath = Paths.get(strFilePath);
		try(BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			writer.write(line);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// read all the files in the specified directory
	public void readFilesInDir(String outputFile) {
		File[] filesList = this.getFiles(false);
		// iterate through all the files
		for(File file : filesList) {
			String fileName = file.getName();
			System.out.println("--------------------");
			this.filterFileContent(fileName, outputFile);
		}
	}

	// future consideration add function to unzip file 
}