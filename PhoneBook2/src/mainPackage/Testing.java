package mainPackage;
public class Testing {
	

	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		
		// Importing from default text file
		pb.importFromFile();
		
		
		System.out.println("All records:");
		pb.printRecords();
		System.out.println("\nADDING \"Connor\"");
		
		
		// Adding record that doesn't exist
		print(pb.addRecord("Connor", "0 878-111-345"));
		// Adding record that exists
		print(pb.addRecord("Connor", "0 878-111-345"));
		

		System.out.println("\n");
		pb.printRecords();
		System.out.println("\nDELETING \"Connor\"");
		
		
		// Deleting record that exist)
		print(pb.deleteRecord("Connor"));
		// Deleting record that doesn't exist)
		print(pb.deleteRecord("Connor"));
		
		
		System.out.println("\n");
		pb.printRecords();
		System.out.println("\nADDING CALL RECORDS");
		
		
		//adding to existing records
		print(pb.addToCallsRecord("Famous Guy", 999));
		print(pb.addToCallsRecord("Jordan Michael", 8));
		print(pb.addToCallsRecord("Sam Summers", 15));
		print(pb.addToCallsRecord("An Quan", 24));
		//adding to a missing record
		print(pb.addToCallsRecord("Missing Name", 4444));

		
		System.out.println("\nMost active talkers:");
		pb.printMostActive();
		
		// Making a call to Ann
		pb.makeCall("+359 878 123 456");
		
		System.out.println("\nMost active talkers:");
		pb.printMostActive();
		
		
		//searching for record
		System.out.println("\nSearching for \"An Quan\"'s numbers:");
		for(String list : pb.searchRecord("an Quan"))
			System.out.println(list);

		
		System.out.println("\nThe end.");
		

	}
	
	
	
	public static void print(boolean o){
		if(o)
			System.out.println("Sucessful operation!");
		else
			System.out.println("Operation was unsucessful!");
	}
	

}
