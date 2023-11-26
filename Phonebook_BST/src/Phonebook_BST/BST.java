package Phonebook_BST;

enum Order {
	preOrder, inOrder, postOrder
}

enum Relative {
	Root, Parent, LeftChild, RightChild
}

class BSTNode {
	public String key;
	public Contact data;
	public BSTNode left, right;

	public BSTNode(String key, Contact val) {
		this.key = key;
		data = val;
		left = right = null;
	}

	public BSTNode(String k, BSTNode l, BSTNode r) {
		key = k;
		left = l;
		right = r;
	}
}

public class BST {

	private BSTNode root, current;

	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public void inOrder(Order ord) {
		inOrder(root);

	}

	public void inOrder(BSTNode p) { // this method is for printing the contacts LNR
		if (p != null) {
			inOrder(p.left);
			System.out.println(p.data.toString());
			inOrder(p.right);
		}
	}

	public boolean insertContact(String key, Contact val) {
		BSTNode p, q = current;

		if (findkey(key)) {
			current = q;
			return false;
		}
		p = new BSTNode(key, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {
			if (key.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}

	}

	public boolean findkey(String key) {
		return findkey(root, key);
	}

	private boolean findkey(BSTNode root, String key) {
		BSTNode p = root, q = root;
		if (empty())
			return false;
		while (p != null) {
			q = p;
			if (p.key.compareTo(key) == 0) {
				current = p;
				return true;
			} else if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}

	private boolean isExist(BSTNode root, String phoneNum) {
		if (root == null) {
			return false;
		}
		boolean left = isExist(root.left, phoneNum);

		if (root.data.getPhoneNumber().equalsIgnoreCase(phoneNum))
			return true;

		boolean right = isExist(root.right, phoneNum);

		return right || left;
	}

	public boolean isExist(Contact contact) {
		return isExist(root, contact.getPhoneNumber());
	}

	private boolean Search(BSTNode p, String n, String data) {
		boolean found = false, left, right;
		switch (n) {

		case "FirstName":
			if (p == null)
				return false;

			left = Search(p.left, n, data);

			int index = p.data.getName().indexOf(" ");
			if (index != -1) {
				String firstName = p.data.getName().substring(0, index);
				if (firstName.equalsIgnoreCase(data))
					System.out.println(p.data.toString());
				return true;
			}

			right = Search(p.right, n, data);
			found = left || right;
			break;

		case "Email":
			if (p == null)
				return false;

			left = Search(p.left, n, data);

			if (p.data.getEmail().equals(data)) {
				System.out.println(p.data.toString());
				return true;
			}
			right = Search(p.right, n, data);
			found = left || right;
			break;

		case "Birthday":
			if (p == null)
				return false;

			left = Search(p.left, n, data);

			if (p.data.getBirthday().equals(data)) {
				System.out.println(p.data.toString());
				return true;
			}
			right = Search(p.right, n, data);
			found = left || right;
			break;

		case "Address":
			if (p == null)
				return false;

			left = Search(p.left, n, data);

			if (p.data.getAddress().equals(data)) {
				System.out.println(p.data.toString());
				return true;
			}
			right = Search(p.right, n, data);
			found = left || right;
			break;
		}
		return found;
	}

	public boolean Search(String n, String data) {
		return Search(root, n, data);
	}

	public Contact SearchByName(String name) {
		return SearchByName(root, name);
	}

	private Contact SearchByName(BSTNode p, String name) {
		if (p == null)
			return null;

		if (p.data.getName().equalsIgnoreCase(name)) {
			return p.data;
		} else if (name.compareTo(root.data.getName()) < 0)
			return SearchByName(p.left, name);
		else
			return SearchByName(p.right, name);
	}

	public Contact SearchByPhoneNumber(String PhoneNumber) {
		return SearchByPhoneNumber(root, PhoneNumber);
	}

	private Contact SearchByPhoneNumber(BSTNode p, String phoneNumber) {
		if (p == null) {
			return null;
		}

		if (p.data.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
			return p.data;
		}

		Contact leftResult = SearchByPhoneNumber(p.left, phoneNumber);
		if (leftResult != null) {
			return leftResult;
		}

		return SearchByPhoneNumber(p.right, phoneNumber);
	}

	public boolean removekey(String key) {

		String k1 = key;
		BSTNode p = root;
		BSTNode q = null;

		while (p != null) {
			if (k1.compareToIgnoreCase(p.key) < 0) {
				q = p;
				p = p.left;
			} else if (k1.compareToIgnoreCase(p.key) > 0) {
				q = p;
				p = p.right;
			} else {

				if ((p.left != null) && (p.right != null)) {

					BSTNode min = p.right;
					q = p;
					while (min.left != null) {

						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
				}

				if (p.left != null) {
					p = p.left;

				} else {
					p = p.right;
				}

				if (q == null) {
					root = p;

				} else {
					if (k1.compareToIgnoreCase(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}

				}
				current = root;
				return true;

			}

		}
		return false;

	}

}
