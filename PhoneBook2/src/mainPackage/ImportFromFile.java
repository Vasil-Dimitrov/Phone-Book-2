package mainPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class ImportFromFile {
	private static String DEFAULT_FILE_NAME = "phonebook.txt";
	private static String REGEX = ",|;";
	
	
	/*
	 * Reads a default text file and converts the data in a
	 * LinkedList which gets returned at the end
	 */
	public static LinkedList<LinkedList<String>> read() {
		return read(new File(DEFAULT_FILE_NAME));
	}
	
	
	/*
	 * Read a text file corresponding to the argument and converts the data
	 * in a LinkedList which gets returned at the end
	 */
	public static LinkedList<LinkedList<String>> read(File file) {
		LinkedList<LinkedList<String>> list = new LinkedList<LinkedList<String>>();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while((line=reader.readLine())!=null)
				list.add(new LinkedList<String>(Arrays.asList(line.split(REGEX))));
		} catch (FileNotFoundException e) {
			System.err.println("Error: file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error reading data from file");
			e.printStackTrace();
		}
		return list;
	}
}
