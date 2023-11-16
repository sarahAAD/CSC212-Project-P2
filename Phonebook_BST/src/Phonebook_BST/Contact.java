package Phonebook_BST;

public class Contact implements Comparable<Contact> {
    private String Name, PhoneNumber, Email, Address, Birthday, Notes;
	private Event event;
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

	@Override
	public int compareTo(Contact contact) {

		return Name.compareTo(contact.Name);
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


}

