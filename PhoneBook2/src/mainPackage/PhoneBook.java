package mainPackage;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PhoneBook {
	Map<String, LinkedList<String>> phoneMap = new TreeMap<String, LinkedList<String>>();
	Map<String, Integer> callsMap = new HashMap<String, Integer>();
	Map<String, Integer> topFive = new HashMap<String, Integer>();
	int indexOfElements;
	
	
	public PhoneBook(){}
	/*
	 * Constructor for creating a new phone book from a text file
	 */
	public PhoneBook(File file){
		importFromFile(file);
	}
	
	
	/*
	 * Adds one record to the Phone Book
	 * Adds one record to the @callsMap with value 0
	 * Returns false if the record exists or if the number is invalid
	 */
	public boolean addRecord(String name, String phoneNum) {
		String tempNum;
		name = name.toUpperCase(Locale.ROOT);
		if(phoneMap.containsKey(name) || (tempNum = checkRecord(phoneNum))==null)
			return false;
		
		addCallsRecord(name, 0);
		phoneMap.put(name, new LinkedList<String>(Arrays.asList(tempNum)));
		return true;
	}
	
	
	/*
	 * Adds multiple records to the phone book
	 * Adds one record to the @callsMaps with value 0
	 * Returns false if the record exists or if all the numbers are invalid
	 */
	public boolean addRecord(String name, LinkedList<String> phoneNum) {
		name = name.toUpperCase(Locale.ROOT);
		if(phoneMap.containsKey(name))
			return false;
		
		LinkedList<String> tempList = new LinkedList<String>();
		String tempNum;
		
		for(int i=0; i<phoneNum.size(); i++)
			if((tempNum = checkRecord(phoneNum.get(i)))!=null)
				tempList.add(tempNum);
		
		if(tempList.size()<1)
			return false;
		
		addCallsRecord(name, 0);
		phoneMap.put(name, tempList);
		return true;
	}
	
	
	/*
	 * Adds to the value of the corresponding callsMap
	 * Sorts the record in descending order
	 * Returns false if record doesn't exist
	 */
	public boolean addToCallsRecord(String name, int calls) {
		name = name.toUpperCase(Locale.ROOT);
		if(!callsMap.containsKey(name) || calls==0)
			return false;
		
		int currentCalls = callsMap.get(name) + calls;
		callsMap.put(name, currentCalls);
		String smallestKey = getSmallestKey(this.topFive);
		if(topFive.get(smallestKey) < currentCalls){
			if(!topFive.containsKey(name)) {
				topFive.remove(smallestKey);
			}
			topFive.put(name, currentCalls);
		}
		return true;
	}
	
	
	public boolean makeCall(String receiver) {
		if((receiver = PhoneNumbers.compile(receiver))==null)	// calling an invalid number
			return false;
		
		String keyName = "";
		boolean found = false;
		
		for(Map.Entry<String, LinkedList<String>> map : phoneMap.entrySet()) {
			for(int i=0; i<map.getValue().size(); i++){
				if(receiver.equals(map.getValue().get(i))) {
						keyName = map.getKey();
						found = true;
						break;
				}
			} //for(int i=0; i<map.getValue().size(); i++){			
			if(found){
				break;
			}			
		} //for(Map.Entry<String, LinkedList<String>> map : phoneMap.entrySet())
		
		return addToCallsRecord(keyName, 1);
	}
	
	
	/*
	 * Sets the value of the corresponding callsMap
	 * Sorts the records in descending order by value
	 * Returns false if record already exists
	 */
	public boolean addCallsRecord(String name, int calls) {
		name = name.toUpperCase(Locale.ROOT);
		if(callsMap.containsKey(name))
			return false;
		callsMap.put(name, calls);
		if(this.indexOfElements < 5) {
			if(topFive.size() > 5) {	// this might not be needed with the right checks
				topFive.remove(getSmallestKey(this.topFive));
			}
			topFive.put(name, calls);
		} else {
			if(topFive.get(getSmallestKey(this.topFive)) < calls){
				topFive.remove(getSmallestKey(this.topFive));
				topFive.put(name, calls);
			}
		}
		indexOfElements++;
		return true;
	}
	
	
	/*
	 * Finds the smallest element in the Map and returns the key to it
	 */
	public String getSmallestKey(Map<String, Integer> argumentMap) {
		int smallestValue = Integer.MAX_VALUE;
		String smallestKey = "";
		for(Map.Entry<String, Integer> map : argumentMap.entrySet()) {
			if(smallestValue > map.getValue()) {
				smallestValue = map.getValue();
				smallestKey = map.getKey();
			}
		}
		return smallestKey;
	}
	
	
	/*
	 * Finds the smallest element in the Map and returns the key to it
	 */
	public String getBiggestKey(Map<String, Integer> argumentMap) {
		int biggestValue = Integer.MIN_VALUE;
		String biggestKey = "";
		for(Map.Entry<String, Integer> map : argumentMap.entrySet()) {
			if(biggestValue < map.getValue()) {
				biggestValue = map.getValue();
				biggestKey = map.getKey();
			}
		}
		return biggestKey;
	}
	

	
	/*
	 * Prints the top 5 Calls Records sorted descending by value
	 */
	public void printMostActive(){
		int i = 1;
		for(Map.Entry<String, Integer> map : topFive.entrySet()){
			if(i>5) {
				break;
			}
			System.out.println((i++) + ".Name: " + map.getKey());
			System.out.println("  Incoming Calls: " + map.getValue());
		}
	}
	
	
	
	/*
	 * Tests the phone number string if it is valid and converts it appropriately
	 */
	private String checkRecord(String phoneNum){
		String tempNum = PhoneNumbers.compile(phoneNum);
		if(tempNum==null)
			return null;
		else 
			return tempNum;
	}
	
	
	/*
	 * Reads data from default text file and calls
	 * private void importFromFile(LinkedList<LinkedList<String>> tempDataList)
	 */
	public void importFromFile(){
		LinkedList<LinkedList<String>> tempDataList = ImportFromFile.read();
		importFromFile(tempDataList);
	}
	
	
	/*
	 * Reads data from a specific text file and calls
	 * private void importFromFile(LinkedList<LinkedList<String>> tempDataList)
	 */
	public void importFromFile(File file){
		LinkedList<LinkedList<String>> tempDataList = ImportFromFile.read(file);
		importFromFile(tempDataList);
		
	}
	
	
	/*
	 * Reads data from a text file and adds it to the PhoneNumbers
	 * Creates calls data records for each valid record added to the phone book
	 * Only adds data if it doesn't exist already
	 */
	private void importFromFile(LinkedList<LinkedList<String>> tempDataList) {
		LinkedList<String> tempNumbersList;
		String tempName;
		
		for(int i=0; i<tempDataList.size(); i++){
			tempName = tempDataList.get(i).removeFirst().toUpperCase();
			tempNumbersList = PhoneNumbers.compile(tempDataList.get(i));
			if(tempNumbersList.size() >= 1 && !phoneMap.containsKey(tempName)) {
					phoneMap.put(tempName, tempNumbersList);
					addCallsRecord(tempName, 0);
				}
		} // for
	}
	
	
	/*
	 * Deletes a record by key
	 * Also deletes the calls record corresponding to the same key
	 * Returns false if record doesn't exist
	 */
	public boolean deleteRecord(String name){
		name = name.toUpperCase(Locale.ROOT);
		if(phoneMap.remove(name)==null)
			return false;
		callsMap.remove(name);
		this.topFive.remove(name);
		String biggestKey = getBiggestKey(this.callsMap);
		topFive.put(biggestKey, this.callsMap.get(biggestKey));
		this.indexOfElements--;
		return true;
	}
	
	
	/*
	 * Returns the number/s corresponding to the key argument as LinkedList
	 */
	public LinkedList<String> searchRecord(String name){
		name = name.toUpperCase(Locale.ROOT);
		LinkedList<String> list = phoneMap.get(name);
		if(list == null)
			list = new LinkedList<String>(Arrays.asList("no record"));
		return list;
	}
	
	
	/*
	 * Prints all record in a descending order by key
	 */
	public void printRecords(){
		int i = 1;
		for (Map.Entry<String, LinkedList<String>> entry : phoneMap.entrySet())
		     System.out.printf("%d.Name: %s \n  Numbers: %s\n", i++, entry.getKey(), entry.getValue());
	}
	
	
}
