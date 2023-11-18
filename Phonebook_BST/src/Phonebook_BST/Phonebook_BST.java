package Phonebook_BST;

import java.util.Scanner;

public class Phonebook_BST {
	static BST Contacts = new BST();
	static LinkedList<Event> eventList = new LinkedList<>();
	static LinkedList<Event> appointmentList = new LinkedList<>();

	public static void main(String[] args) {
		Phonebook_BST PhoneBook = new Phonebook_BST();
		String choice = "0";
		System.out.println("Welcome to the BST Phonebook!");
		do {
			System.out.println("\nPlease choose an option:\r\n" + "1. Add a contact\r\n" + "2. Search for a contact\r\n"
					+ "3. Delete a contact\r\n" + "4. Schedule an event\\appointment\r\n"
					+ "5. Print event\\appointment details\r\n" + "6. Print contacts by first name\r\n"
					+ "7. Print all events\\appointment alphabetically\r\n"
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
				if (PhoneBook.AddContact(c1))
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
					Contact c = Contacts.SearchByName(contactName);
					if (c==null)
						System.out.println("There is no contact with this name");
					else 
						System.out.println(c.toString());
					break;

				case "2":
					System.out.print("Enter the contact's phone number:");
					String contactPhoneNumber = scan.nextLine();
					if (!Contacts.Search("PhoneNumber", contactPhoneNumber))
						System.out.println("There is no contact with this phone number");

					break;

				case "3":
					System.out.print("Enter the contact's email address:");
					String contactEmailAddress = scan.nextLine();
					if (!Contacts.Search("Email", contactEmailAddress))
						System.out.println("There is no contact with this email");

					break;

				case "4":
					System.out.print("Enter the contact's address:");
					String contactAddress = scan.nextLine();
					if (!Contacts.Search("Address", contactAddress))
						System.out.println("There is no contact with this address");

					break;

				case "5":
					System.out.print("Enter the contact's birthday:");
					String contactBirthday = scan.nextLine();
					if (!Contacts.Search("Birthday", contactBirthday))
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

					/*
					 * if (PhoneBook.DeleteContactByName(deleteName))
					 * System.out.println("Contact deleted successfully"); else
					 * System.out.println("Contact not found");
					 */

					break;

				case "2":
					System.out.print("Enter contact phone number:");
					String deletePhoneNumber = scan.nextLine();

					/*
					 * if (PhoneBook.DeleteContactByPhoneNumber(deletePhoneNumber))
					 * System.out.println("Contact deleted successfully"); else
					 * System.out.println("Contact not found");
					 */

				}
				break;

			case "4": // schedule an event
				System.out.println("Enter type:\n1.Event\n2.Appointment");
				String type = scan.nextLine();

				switch (type) {
				case "1":
					System.out.print("Enter event title:");
					String eventTitle = scan.nextLine();
					System.out.print("Enter contact name:");
					String contactName = scan.nextLine();
					System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
					String eventTimeAndDate = scan.nextLine();
					System.out.print("Enter event location:");
					String eventLocation = scan.nextLine();
					if (Contacts.SearchByName(contactName)!=null) {
						Event event = new Event(contactName, eventTitle, eventTimeAndDate, eventLocation, "Event");
						if (PhoneBook.AddEvent(event))
							System.out.println("Event added successfully!");
						else
							System.out.println("event has been added before!");
					} else
						System.out.println("Contact doesn't exist, please add the contact first");

					break;

				case "2":
					System.out.print("Enter Appointment title:");
					String AppointmentTitle = scan.nextLine();
					System.out.print("Enter contact name:");
					String contactname = scan.nextLine();
					System.out.print("Enter Appointment date and time (MM/DD/YYYY HH:MM):");
					String AppointmentTimeAndDate = scan.nextLine();
					System.out.print("Enter Appointment location:");
					String AppointmentLocation = scan.nextLine();
					if (Contacts.SearchByName(contactname)!=null) {
						Event event1 = new Event(contactname, AppointmentTitle, AppointmentTimeAndDate,
								AppointmentLocation, "Appointment");
						if (PhoneBook.addAppointment(event1))
							System.out.println("Appointment added successfully!");
						else
							System.out.println("Appointment has been added before!");
					} else
						System.out.println("Contact doesn't exist, please add the contact first");

				}
				break;

			case "5": // print event/appointment detail
				String ContactName=null;
				String EventORAppointmentTitle=null;
				
				
				
				System.out.println("Choose whether you want to print the details of an event or appointment:\n1.event\n2.appointment");
				String detailsInput = scan.nextLine();
				
				System.out.print("Enter search criteria:\n1.contact name\n2.event\\appointment title\n");
				String DSearchInput = scan.nextLine();
				
				

				switch(DSearchInput) {
				case "1":

					System.out.print("Enter contact name:");
					/*String*/ ContactName = scan.nextLine();
					break;
					
				case "2":
					System.out.print("Enter event\\appointment title:");
					/*String*/ EventORAppointmentTitle = scan.nextLine();
					break;
				}
				
				switch(detailsInput) {
				
				case "1":
					switch(DSearchInput) {
					case "1":
					
						PhoneBook.printEventDetails("name", ContactName);
						break;
						
					case "2":
						
						PhoneBook.printEventDetails("title", EventORAppointmentTitle);
						break;
				}
				break;
				
				case "2":
					switch(DSearchInput) {
					case "1":
					
						PhoneBook.printAppointmentDetails("name", ContactName);
						break;
						
					case "2":
						
						PhoneBook.printAppointmentDetails("title", EventORAppointmentTitle);
						break;
					}
					break;
					}
				
				break;
				

			case "6": // print contacts by first name

				System.out.print("Enter the first name:");
				String firstName = scan.nextLine();
				if (!Contacts.Search("FirstName", firstName))
					System.out.println("There is no contact with this name");

				break;

			case "7":
				System.out.println("Enter type:\n1.Event\n2.Appointment");
				String type1 = scan.nextLine();

				switch (type1) {
				case "1":
					PhoneBook.printAllEvent();
					break;
				case "2":
					PhoneBook.printAllappointment();
					break;
				}
				break;

			case "8":
				System.out.println("Enter event title");
				String eventTitle = scan.nextLine();
				System.out.println("Enter event date and time (MM/DD/YYYY HH:MM):");
				String DateAndTime = scan.nextLine();
				System.out.println("Enter event location:");
				String Location = scan.nextLine();
				// PhoneBook.PrintSameEvent(EventTitle, DateAndTime, Location);
				break;

			case "9":
				Contacts.inOrder(Order.inOrder);
				return;
			}
				
		} while (choice != "9");

	}

	public boolean AddContact(Contact contact) {
		if (Contacts.isExist(contact))
			return false;
		Contacts.insertContact(contact);
		return true;

	}

	public boolean AddEvent(Event event) {
		boolean found = false;
		if (eventList.empty()) {
			eventList.AddEvent(event);

			return true;
		}
		eventList.FindFirst();
		while (!eventList.last()) {

			if (eventList.retrieve().getTitle().equals(event.getTitle())
					&& eventList.retrieve().getDateAndTime().equals(event.getDateAndTime())) {
				if (eventList.retrieve().isExist(event.getContactName()))
					found = true;
				else {
					eventList.retrieve().setContactInEvent(event.getContactName());
					return true;
				}
			}
			eventList.FindNext();

		}
		if (eventList.retrieve().getTitle().equals(event.getTitle())
				&& eventList.retrieve().getDateAndTime().equals(event.getDateAndTime())) {
			if (eventList.retrieve().isExist(event.getContactName()))
				found = true;
			else {
				eventList.retrieve().setContactInEvent(event.getContactName());
				return true;
			}
		}

		if (!found) {
			eventList.AddEvent(event);
			return true;
		} else
			return false;
	}


	public boolean addAppointment(Event event) {
		boolean found = false;
		if (appointmentList.empty()) {
			appointmentList.AddEvent(event);
			return true;
		}
		appointmentList.FindFirst();
		while (!appointmentList.last()) {

			if (appointmentList.retrieve().getTitle().equals(event.getTitle())
					|| appointmentList.retrieve().getDateAndTime().equals(event.getDateAndTime())) {
				if (appointmentList.retrieve().getContactName().equals(event.getContactName()))
					found = true;
			}
			appointmentList.FindNext();

		}
		if (appointmentList.retrieve().getTitle().equals(event.getTitle())
				|| appointmentList.retrieve().getDateAndTime().equals(event.getDateAndTime())) {
			if (appointmentList.retrieve().getContactName().equals(event.getContactName()))
				found = true;
		}

		if (!found) {
			appointmentList.AddEvent(event);
			return true;
		} else
			return false;
	}

	public void printAllEvent() {
		if (eventList.empty()) {
			System.out.println("there are no events");
			return;
		}
		eventList.FindFirst();
		while (!eventList.last()) {
			eventList.retrieve().displayEvent();
			eventList.FindNext();
		}
		eventList.retrieve().displayEvent();

	}

	public void printAllappointment() {
		if (appointmentList.empty()) {
			System.out.println("there are no appointments");
			return;
		}
		appointmentList.FindFirst();
		while (!appointmentList.last()) {
			appointmentList.retrieve().displayAppointment();;
			appointmentList.FindNext();
		}
		appointmentList.retrieve().displayAppointment();;
	}
	
	public void printEventDetails(String criteria, String nameORTitle) {
		
		if (eventList.empty()) 
			System.out.println("There are no events");
		
		switch (criteria) {
		case "name":
			eventList.FindFirst();
			while (!eventList.last()) {
				if (eventList.retrieve().getContactName().equals(nameORTitle)) //should i add an and to the condition that checks the type?
					eventList.retrieve().displayEvent();
				eventList.FindNext();
			}
			if (eventList.retrieve().getContactName().equals(nameORTitle)) 
				eventList.retrieve().displayEvent();
			eventList.FindNext();
			
			break;
			
		case "title":
			eventList.FindFirst();
			while (!eventList.last()) {
				if (eventList.retrieve().getTitle().equals(nameORTitle)) 
					eventList.retrieve().displayEvent();
				eventList.FindNext();
			}
			if (eventList.retrieve().getTitle().equals(nameORTitle)) 
				eventList.retrieve().displayEvent();
			eventList.FindNext();
			
			break;	
			
		
		}	
	}
	
	public void printAppointmentDetails(String criteria, String nameORTitle) {
		
		if (appointmentList.empty()) 
			System.out.println("There are no appointments");
		
		switch (criteria) {
		case "name":
			appointmentList.FindFirst();
			while (!appointmentList.last()) {
				if (appointmentList.retrieve().getContactName().equals(nameORTitle)) 
					appointmentList.retrieve().displayAppointment(); 
				appointmentList.FindNext();
			}
			
			if (appointmentList.retrieve().getContactName().equals(nameORTitle)) 
				appointmentList.retrieve().displayAppointment(); 
			appointmentList.FindNext();
			
			break;
			
		case "title":
			appointmentList.FindFirst();
			while (!appointmentList.last()) {
				if (appointmentList.retrieve().getTitle().equals(nameORTitle)) 
					appointmentList.retrieve().displayAppointment(); 
				appointmentList.FindNext();
			}
			if (appointmentList.retrieve().getTitle().equals(nameORTitle)) 
				appointmentList.retrieve().displayAppointment();  
			appointmentList.FindNext();
			
			break;	
			
		}	
	}

}
