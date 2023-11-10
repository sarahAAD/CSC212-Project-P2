package Phonebook_BST;
import java.util.Scanner;

public class Phonebook_BST {
	   static  BST Contacts = new BST();

	    public static void main(String[] args) {
			Phonebook_BST PhoneBook = new Phonebook_BST();
			String choice = "0";
			System.out.println("Welcome to the Linked Tree Phonebook!");
			do {
				System.out.println("\nPlease choose an option:\r\n" + "1. Add a contact\r\n" + "2. Search for a contact\r\n"
						+ "3. Delete a contact\r\n" + "4. Schedule an event\r\n" + "5. Print event details\r\n"
						+ "6. Print contacts by first name\r\n" + "7. Print all events alphabetically\r\n"
						+ "8. print all contacts that share an event\r\n" + "9. Exit");

				Scanner scan = new Scanner(System.in);

				System.out.print("Enter your choice:");
				choice = scan.nextLine();

				switch (choice) {
				case "1":
					Contact c1 = new Contact();
					System.out.print("Enter the contact's name:");
					String name = scan.nextLine();
					c1.setName(name);
					System.out.print("Enter the contact's phone number:");
					String number = scan.nextLine();
					c1.setPhoneNumber(number);
					System.out.print("Enter the contact's email address:");
					String email = scan.nextLine();
					c1.setEmail(email);
					System.out.print("Enter the contact's address:");
					String address = scan.nextLine();
					c1.setAddress(address);
					System.out.print("Enter the contact's birthday:");
					String birthday = scan.nextLine();
					c1.setBirthday(birthday);
					System.out.print("Enter any notes for the contact:");
					String notes = scan.nextLine();
					c1.setNotes(notes);
	                             if(PhoneBook.AddContact(c1))
				      System.out.println("\nContact added successfully!");
					else
						System.out.println("\nThis Contact has been added before!");
					break;

				case "2": // search for a contact
					Contact contact = new Contact();
					System.out.println(
							"Enter search criteria:\n1.Name\n2.Phone Number\n3.Email Address\n4.Address\n5.Birthday");
					String searchInput = scan.nextLine();

					switch (searchInput) { // nested switch
					case "1":
						System.out.print("Enter the contact's name:");
						String contactName = scan.nextLine();
	                                        if(!Contacts.Search("Name", contactName))
	                                        System.out.println("There is no contact with this name");


						break;

					case "2":
						System.out.print("Enter the contact's phone number:");
						String contactPhoneNumber = scan.nextLine();
	                                        if(!Contacts.Search("PhoneNumber", contactPhoneNumber))
	                                        System.out.println("There is no contact with this phone number");


						break;

					case "3":
						System.out.print("Enter the contact's email address:");
						String contactEmailAddress = scan.nextLine();
	                                        if(!Contacts.Search("Email", contactEmailAddress))
	                                        System.out.println("There is no contact with this email");



						break;

					case "4":
						System.out.print("Enter the contact's address:");
						String contactAddress = scan.nextLine();
	                                        if(!Contacts.Search("Address", contactAddress))
	                                        System.out.println("There is no contact with this address");



						break;

					case "5":
						System.out.print("Enter the contact's birthday:");
						String contactBirthday = scan.nextLine();
	                                        if(!Contacts.Search("Birthday", contactBirthday))
				                System.out.println("There is no contact with this birthday");


						break;
					}

					break;

				case "3": // delete contact
					System.out.println("Enter delete criteria:\n1.Name\n2.Phone Number");
					String deleteInput = scan.nextLine();

					switch (deleteInput) { // nested switch
					case "1":
						System.out.print("Enter contact name:");
						String deleteName = scan.nextLine();
						
						/*if (PhoneBook.DeleteContactByName(deleteName))
							System.out.println("Contact deleted successfully");
						else
							System.out.println("Contact not found");*/

						break;

					case "2":
						System.out.print("Enter contact phone number:");
						String deletePhoneNumber = scan.nextLine();
						

						/*if (PhoneBook.DeleteContactByPhoneNumber(deletePhoneNumber))
							System.out.println("Contact deleted successfully");
						else
							System.out.println("Contact not found");*/

					}
					break;

				case "4": // schedule an event
					System.out.print("Enter event title:");
					String eventTitle = scan.nextLine();
					System.out.print("Enter contact name:");
					String contactName = scan.nextLine();
					System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
					String eventTimeAndDate = scan.nextLine();
					System.out.print("Enter event location:");
					String eventLocation = scan.nextLine();
					Contact c = null;



					break;

				case "5": // print event detail
					System.out.print("Enter search criteria:\n1.contact name\n2.event title\n");
					String eventInput = scan.nextLine();

					switch (eventInput) { // nested switch
					case "1":

						System.out.print("Enter contact name:");
						String eventContactName = scan.nextLine();

						break;

					case "2":
						System.out.print("Enter event title:");
						String EventTitle = scan.nextLine();
						/*Event eventSameTitle = PhoneBook.SearchEventByTitle(EventTitle);
						if (eventSameTitle != null)
							System.out.println(eventSameTitle.toString());
						else
							System.out.println("Event not found");*/
						break;
					}

					break;

				case "6": // print contacts by first name

					System.out.print("Enter the first name:");
					String firstName = scan.nextLine();
	                                if(!Contacts.Search("FirstName", firstName))
	                                System.out.println("There is no contact with this name");

					break;

				case "7":
					//PhoneBook.printAllEvent();
					break;
				case "8":
					System.out.println("Enter event title");
					String EventTitle = scan.nextLine();
					System.out.println("Enter event date and time (MM/DD/YYYY HH:MM):");
					String DateAndTime = scan.nextLine();
					System.out.println("Enter event location:");
					String Location = scan.nextLine();
					//PhoneBook.PrintSameEvent(EventTitle, DateAndTime, Location);
					break;

				case "9":
	Contacts.inOrder(Order.inOrder);
			
					
				}
			} while (choice != "9");
	    }
	    
	    public boolean AddContact(Contact contact) {
	 if (Contacts.isExist(contact))
	     return false; 
	    Contacts.insertContact(contact);
	        return true;
	        
	}

}
