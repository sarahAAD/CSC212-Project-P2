package Phonebook_BST;

public class Contact implements Comparable<String> {
    private String Name, PhoneNumber, Email, Address, Birthday, Notes;
	private LinkedList<Event> events_appointments = new LinkedList<>();
    public Contact() {
	}

	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		Name = name;
		PhoneNumber = phoneNumber;
		Email = email;
		Address = address;
		Birthday = birthday;
		Notes = notes;
	}

	public Contact(Contact obj) {
		Name = obj.Name;
		PhoneNumber = obj.PhoneNumber;
		Email = obj.Email;
		Address = obj.Address;
		Birthday = obj.Birthday;
		Notes = obj.Notes;

		
	}

	
	public int compareTo(String contactName) {
		return Name.compareTo(contactName);
	}

	public String toString() {
		return "Name:" + Name + "\nPhoneNumber:" + PhoneNumber + "\nEmail:" + Email + "\nAddress:" + Address
				+ "\nBirthday:" + Birthday + "\nnotes:" + Notes + "\n";
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String Birthday) {
		this.Birthday = Birthday;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		this.Notes = notes;
	}

	public LinkedList<Event> getevents_appointments() {
		return events_appointments;
	}

	public void setevents_appointments(Event event) {
		this.events_appointments.Add(event);;
	}




}

