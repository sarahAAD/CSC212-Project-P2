package Phonebook_BST;

public class Event {
	private String Title;
	private String DateAndTime;
	private String location;
	private String contactName;
	private LinkedList<String> ContactInEvent = new LinkedList<String>();
	private String Type;

	public Event() {
	}

	public Event(Event event) {
		Title = event.Title;
		DateAndTime = event.DateAndTime;
		location = event.location;
		Type = event.Type;
		contactName = event.contactName;

	}

	public Event(String contactName, String eventTitle, String DateAndTime, String location, String type) {
		this.Title = eventTitle;
		this.DateAndTime = DateAndTime;
		this.location = location;
		Type = type;
		this.contactName = contactName;

	}

	public int compareTo(Event event) {
		return Title.compareTo(event.Title);
	}

	public boolean isExist(String ContactName) {
		
		boolean found = false;
		if (ContactInEvent.empty())
			return false;
		else {
			ContactInEvent.FindFirst();
			while (!ContactInEvent.last()) {
				if (ContactInEvent.retrieve().equalsIgnoreCase(ContactName))
					found = true;
				ContactInEvent.FindNext();
			}
			if (ContactInEvent.retrieve().equalsIgnoreCase(ContactName))
				found = true;
			return found;

		}
	}

	public String getDateAndTime() {
		return DateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		DateAndTime = dateAndTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public LinkedList<String> getContactInEvent() {
		return ContactInEvent;
	}

	public void printContactInEvent() {

		ContactInEvent.FindFirst();
		while (!ContactInEvent.last()) {
			System.out.print(ContactInEvent.retrieve() + " , ");
			ContactInEvent.FindNext();
		}
		System.out.print(ContactInEvent.retrieve() + "\n");
	}

	public void setContactInEvent(String contactName) {
		ContactInEvent.insert(contactName);
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public void displayEvent() {
		System.out.println(
				"Title: " + Title + "\nDateAndTime: " + DateAndTime + "\nlocation: " + location + "\nContacts name:");

		ContactInEvent.FindFirst();
		while (!ContactInEvent.last()) {
			System.out.println(ContactInEvent.retrieve());
			ContactInEvent.FindNext();
		}
		System.out.println(ContactInEvent.retrieve() + "\n");

	}

	public void displayAppointment() {
		System.out.println("Title: " + Title + "\nDateAndTime: " + DateAndTime + "\nlocation: " + location
				+ "\nContact name: " + contactName + "\n");
	}
}
