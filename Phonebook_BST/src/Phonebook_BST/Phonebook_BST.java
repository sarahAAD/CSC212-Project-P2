package Phonebook_BST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Phonebook_BST {
	static BST Contacts = new BST();
	static LinkedList<Event> eventList = new LinkedList<>();

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
					if (c == null)
						System.out.println("There is no contact with this name");
					else
						System.out.println(c.toString());
					break;

				case "2":
					System.out.print("Enter the contact's phone number:");
					String contactPhoneNumber = scan.nextLine();
					Contact c2 = Contacts.SearchByPhoneNumber(contactPhoneNumber);
					if (c2 == null)
						System.out.println("There is no contact with this phone number");
					else
						System.out.println(c2.toString());

					break;

				case "3":
					System.out.print("Enter the contact's email address:");
					String contactEmailAddress = scan.nextLine();
					LinkedList<Contact> result = Contacts.Search("Email", contactEmailAddress);
					if (result != null) {
						result.FindFirst();
						while (!result.last()) {
							System.out.println(result.retrieve());
							result.FindNext();
						}
						System.out.println(result.retrieve());
					} else if (result == null)
						System.out.println("There is no contact with this email");

					break;

				case "4":
					System.out.print("Enter the contact's address:");
					String contactAddress = scan.nextLine();
					LinkedList<Contact> result1 = Contacts.Search("Address", contactAddress);
					if (result1 != null) {
						result1.FindFirst();
						while (!result1.last()) {
							System.out.println(result1.retrieve());
							result1.FindNext();
						}
						System.out.println(result1.retrieve());
					} else
						System.out.println("There is no contact with this address");

					break;

				case "5":
					System.out.print("Enter the contact's birthday:");
					String contactBirthday = scan.nextLine();
					LinkedList<Contact> result2 = Contacts.Search("Birthday", contactBirthday);
					if (result2 != null) {
						result2.FindFirst();
						while (!result2.last()) {
							System.out.println(result2.retrieve());
							result2.FindNext();
						}
						System.out.println(result2.retrieve());
					} else
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

					if (PhoneBook.DeleteContact(deleteName))
						System.out.println("Contact deleted successfully");
					else
						System.out.println("Contact not found");

					break;

				case "2":
					System.out.print("Enter contact phone number:");
					String deletePhoneNumber = scan.nextLine();
					Contact ContactToDelete = Contacts.SearchByPhoneNumber(deletePhoneNumber);// search for the contact
					if (ContactToDelete != null) { // if the contact found

						if (PhoneBook.DeleteContact(ContactToDelete.getName()))
							System.out.println("Contact deleted successfully");
						else
							System.out.println("Contact not found");
					} else
						System.out.println("Contact not found");

					break;

				}
				break;

			case "4": // schedule an event
				System.out.println("Enter type:\n1.Event\n2.Appointment");
				String type = scan.nextLine();

				switch (type) {
				case "1":
					System.out.print("Enter event title:");
					String eventTitle = scan.nextLine();
					System.out.print("Enter contacts name separated by a comma: ");
					String contactsName = scan.nextLine();
					System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
					String eventTimeAndDate = scan.nextLine();
					// PhoneBook.validate(contactsName);
					System.out.print("Enter event location:");
					String eventLocation = scan.nextLine();
					Event NewEvent = new Event(eventTitle, eventTimeAndDate, eventLocation, "Event");

					String ContactsNotFound = ""; // contacts not found in the Contacts BST.
					String ContactsAdded = ""; // contacts that have successfully scheduled for the event.
					String Exist = ""; // contacts that have already successfully scheduled for the event.
					String[] names = contactsName.split(",");

					Event event = eventList.search(NewEvent); // check whether the event has already been added by
																// searching.

					if (event != null) { // "if the event already exists in eventList.
						for (int i = 0; i < names.length; i++) {
							Contact c = Contacts.SearchByName(names[i]); // search for every contact.
							if (c != null) { // if the contacts found
								if (event.isExist(names[i])) { // check if it exists in the event.
									Exist += ", " + names[i];
								} else {
									ContactsAdded += ", " + names[i];
									event.setContactInEvent(names[i]);
									c.setevents_appointments(event);
								}

							} else { // if the contact is not found
								if (!ContactsNotFound.isEmpty())
									ContactsNotFound += ", ";

								ContactsNotFound += names[i];
							}

						} // end for
						event.setContactName(event.getContactName() + ContactsAdded);

					} // end if

					else {
						if (PhoneBook.Conflict(eventTimeAndDate)) {
							System.out.println("Contact has a conflict, can't add the event.");
							break;
						} else if (!PhoneBook.Conflict(eventTimeAndDate)) {// If it's a new event.
							for (int i = 0; i < names.length; i++) {
								Contact c = Contacts.SearchByName(names[i]); // search for every contact.
								if (c != null) { // if the contact exist

									NewEvent.setContactInEvent(names[i]); // add the contact name to the list of
																			// contacts in
																			// the event.
									c.setevents_appointments(NewEvent); // include the event in the contact's
																		// events_appointments list
									ContactsAdded += ", " + names[i]; // add the name here for printing

								} else { // if the contact does not exist
									if (!ContactsNotFound.isEmpty())
										ContactsNotFound += ", ";

									ContactsNotFound += names[i];
								}

							}

							if (ContactsAdded.isEmpty())
								System.out.println("can't add the event");
							else {
								PhoneBook.AddEvent(NewEvent); // if a contact is added, call the method AddEvent to
								NewEvent.setContactName(ContactsAdded);
							}
						}

					} // create the object and add it to the eventList.

					if (!ContactsNotFound.isEmpty()) // print the names of contacts that do not exist
						System.out.println(ContactsNotFound + " not exist");

					if (!ContactsAdded.isEmpty()) // print the names of contacts that have successfully
													// scheduled for the event
						System.out.println("Event scheduled successfully for: " + ContactsAdded);

					if (!Exist.isEmpty()) // print the names of contacts that are already scheduled for
											// the
											// event
						System.out.println("The event has been scheduled before for: " + Exist);

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
					Contact c = Contacts.SearchByName(contactname);
					Event appointment = new Event(contactname, AppointmentTitle, AppointmentTimeAndDate,
							AppointmentLocation, "Appointment");
					if (c != null) {
						if (eventList.search(appointment) != null) {
							System.out.println("This appointment has been added before.");
							break;
						} else if (!PhoneBook.Conflict(AppointmentTimeAndDate)) {

							if (PhoneBook.addAppointment(appointment))
								System.out.println("Appointment added successfully!");
							c.setevents_appointments(appointment); // include the appointment in the contact's
																	// events_appointments list
						}

						else {
							System.out.println("Contact has a conflict, can't add the appointment.");
						}
					} else {
						System.out.println("Contact doesn't exist, please add the contact first.");
					}

				}
				break;

			case "5": // print event/appointment detail
				String ContactName = null;
				String EventORAppointmentTitle = null;

				System.out.println(
						"Choose whether you want to print the details of an event or appointment:\n1.event\n2.appointment");
				String Type = scan.nextLine();

				System.out.print("Enter search criteria:\n1.contact name\n2.event\\appointment title\n");
				String criteria = scan.nextLine();

				switch (criteria) {

				case "1": // when search by contact name is chosen

					switch (Type) {

					case "1": // search by contact name for event

						System.out.print("Enter contacts name seperated by a comma:");
						ContactName = scan.nextLine();
						break;

					case "2": // search by contact name for appointment
						System.out.print("Enter contact name:");
						ContactName = scan.nextLine();
						break;
					}
					break;

				case "2": // when search by title is chosen

					switch (Type) {

					case "1": // search by title for event

						System.out.print("Enter event title:");
						EventORAppointmentTitle = scan.nextLine();
						break;

					case "2": // search by title for appointment
						System.out.print("Enter appointment title:");
						EventORAppointmentTitle = scan.nextLine();
						break;
					}
					break;
				}

				switch (Type) {

				case "1": // search for event
					switch (criteria) {
					case "1": // contacts names

						if (PhoneBook.printEventDetails("name", ContactName) == false)
							System.out.println("No event with these contacts was found");
						break;

					case "2": // event title

						if (PhoneBook.printEventDetails("title", EventORAppointmentTitle) == false)
							System.out.println("No event with this title was found");
						break;
					}
					break;

				case "2": // search for appointment

					switch (criteria) {
					case "1": // contact name

						if (PhoneBook.printAppointmentDetails("name", ContactName) == false)
							System.out.println("No appointment with this contact was found");
						break;

					case "2": // appointment title

						if (PhoneBook.printAppointmentDetails("title", EventORAppointmentTitle) == false)
							System.out.println("No appointment with this title was found");
						break;
					}
					break;
				}

				break;

			case "6": // print contacts by first name

				System.out.print("Enter the first name:");
				String firstName = scan.nextLine();
				PhoneBook.printByFirstName(firstName);
				break;

			case "7":
				PhoneBook.printAllEvent();
				break;

			case "8":
				System.out.println("Enter event title");
				String eventTitle = scan.nextLine();
				System.out.println("Enter event date and time (MM/DD/YYYY HH:MM):");
				String DateAndTime = scan.nextLine();
				System.out.println("Enter event location:");
				String Location = scan.nextLine();
				PhoneBook.PrintSameEvent(eventTitle, DateAndTime, Location);
				break;

			case "9":
				return;
			}

		} while (choice != "9");

	}

	public boolean AddContact(Contact contact) {
		if (Contacts.SearchByPhoneNumber(contact.getPhoneNumber()) != null)
			return false;
		return Contacts.insertContact(contact.getName().toLowerCase(), contact);
	}

	public boolean AddEvent(Event event) {
		if (eventList.empty()) {
			eventList.Add(event);

			return true;
		}

		if (!Conflict(event.getDateAndTime())) {
			eventList.Add(event);
			return true;
		} else
			return false;
	}

	public boolean addAppointment(Event appointment) {
		if (eventList.empty()) {
			eventList.Add(appointment);
			return true;
		}

		if (!Conflict(appointment.getDateAndTime())) {
			eventList.Add(appointment);
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

	public boolean printEventDetails(String criteria, String nameORTitle) {

		boolean found = false;

		if (eventList.empty())
			System.out.println("There are no events");

		switch (criteria) {
		case "name":
			int count = 0;
			String contactNames[] = nameORTitle.split(",");

			eventList.FindFirst();
			while (!eventList.last()) {
				for (int i = 0; i < contactNames.length; i++) {
					if (eventList.retrieve().getType().equalsIgnoreCase("Event")
							&& eventList.retrieve().getContactName().contains(contactNames[i]))
						count++;
					else
						count--;
				}
				if (count == eventList.retrieve().getSize()) {
					eventList.retrieve().displayEvent();
					found = true;
				}
				count = 0;
				eventList.FindNext();
			}

			for (int i = 0; i < contactNames.length; i++) {
				if (eventList.retrieve().getType().equalsIgnoreCase("Event")
						&& eventList.retrieve().getContactName().contains(contactNames[i]))
					count++;
				else
					count--;
			}
			if (count == eventList.retrieve().getSize()) {
				eventList.retrieve().displayEvent();
				found = true;
			}

			break;

		case "title":
			eventList.FindFirst();
			while (!eventList.last()) {
				if (eventList.retrieve().getType().equalsIgnoreCase("Event")
						&& eventList.retrieve().getTitle().equals(nameORTitle)) {
					eventList.retrieve().displayEvent();
					found = true;
				}
				eventList.FindNext();
			}
			if (eventList.retrieve().getType().equalsIgnoreCase("Event")
					&& eventList.retrieve().getTitle().equals(nameORTitle)) {
				eventList.retrieve().displayEvent();
				found = true;
			}
			eventList.FindNext();

			break;

		}
		return found;
	}

	public boolean printAppointmentDetails(String criteria, String nameORTitle) {

		boolean found = false;

		if (eventList.empty())
			System.out.println("There are no appointments");

		switch (criteria) {
		case "name":
			if (!eventList.empty()) {
				eventList.FindFirst();
				while (!eventList.last()) {
					if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
							&& eventList.retrieve().getContactName().equals(nameORTitle)) {
						eventList.retrieve().displayEvent();
						found = true;
					}
					eventList.FindNext();
				}

				if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
						&& eventList.retrieve().getContactName().equals(nameORTitle)) {
					eventList.retrieve().displayEvent();
					found = true;
				}
				eventList.FindNext();
			}

			break;

		case "title":
			if (!eventList.empty()) {
				eventList.FindFirst();
				while (!eventList.last()) {
					if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
							&& eventList.retrieve().getTitle().equals(nameORTitle)) {
						eventList.retrieve().displayEvent();
						found = true;
					}
					eventList.FindNext();
				}
				if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
						&& eventList.retrieve().getTitle().equals(nameORTitle)) {
					eventList.retrieve().displayEvent();
					found = true;
				}
				eventList.FindNext();
			}
			break;

		}
		return found;
	}

	public void PrintSameEvent(String eventTitle, String DateAndTime, String Location) {
		LinkedList<String> ContactsShareEvent = new LinkedList<String>();
		if (eventList.empty())
			System.out.println("There is no event with this name");
		else {
			eventList.FindFirst();
			while (!eventList.last()) {
				if (eventList.retrieve().getType().equalsIgnoreCase("Event")
						&& eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle)
						&& eventList.retrieve().getDateAndTime().equals(DateAndTime)
						&& eventList.retrieve().getLocation().equals(Location))
					ContactsShareEvent = eventList.retrieve().getContactInEvent();
				eventList.FindNext();

			}
			if (eventList.retrieve().getType().equalsIgnoreCase("Event")
					&& eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle)
					&& eventList.retrieve().getDateAndTime().equals(DateAndTime)
					&& eventList.retrieve().getLocation().equals(Location)) {
				ContactsShareEvent = eventList.retrieve().getContactInEvent();

			}
		}
		if (!ContactsShareEvent.empty()) {
			ContactsShareEvent.FindFirst();
			while (!ContactsShareEvent.last()) {
				System.out.println(Contacts.SearchByName(ContactsShareEvent.retrieve()));
				ContactsShareEvent.FindNext();
			}
			System.out.println(Contacts.SearchByName(ContactsShareEvent.retrieve()));

		}
	}

	public boolean Conflict(String DateAndTime) {
		if (!eventList.empty()) {

			eventList.FindFirst();
			while (!eventList.last()) {
				if (eventList.retrieve().getDateAndTime().equals(DateAndTime))
					return true;
				eventList.FindNext();
			}
			if (eventList.retrieve().getDateAndTime().equals(DateAndTime)) {
				return true;
			}
		}
		return false;
	}

	public boolean DeleteContact(String name) {
		if (Contacts.empty())
			return false;
		else {
			if (!eventList.empty()) {
				eventList.FindFirst();
				while (!eventList.last()) {
					if (eventList.retrieve().getType().equalsIgnoreCase("Event")
							&& eventList.retrieve().isExist(name)) {
						eventList.retrieve().DeleteContactInEvent(name);
						if (eventList.retrieve().getSize() == 0) {
							eventList.remove();
						}
					} else if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
							&& eventList.retrieve().getContactName().equalsIgnoreCase(name)) {
						eventList.remove();
					} else
						eventList.FindNext();
				}

				if (eventList.retrieve().getType().equalsIgnoreCase("Event") && eventList.retrieve().isExist(name)) {
					eventList.retrieve().DeleteContactInEvent(name);
					if (eventList.retrieve().getSize() == 0) {
						eventList.remove();
					}
				} else if (eventList.retrieve().getType().equalsIgnoreCase("Appointment")
						&& eventList.retrieve().getContactName().equalsIgnoreCase(name)) {
					eventList.remove();
				}
			}
		}
		return Contacts.removekey(name);

	}

	public boolean validate(String DateAndTime) {
		try {
			String date = DateAndTime.substring(0, DateAndTime.indexOf(" "));
			String time = DateAndTime.substring(DateAndTime.indexOf(" ") + 1);
			System.out.println(date + time);
			SimpleDateFormat validateTime = new SimpleDateFormat(" hh:mm");
			DateTimeFormatter validateDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Date Time = validateTime.parse(time);
			validateTime.format(Time);
			validateDate.parse(date);
		} catch (ParseException e) {
			System.out.println("Invalid date and time");
			return false;
		}
		return true;
	}

	public void printByFirstName(String name) {
		LinkedList<Contact> result = Contacts.Search("FirstName", name); 
		if (!result.empty()) {
			result.FindFirst();
			while (!result.last()) {
				System.out.println(result.retrieve());
				result.FindNext();
			}
			System.out.println(result.retrieve());
		} else
			System.out.println("There is no contact with this name");
	}

}
