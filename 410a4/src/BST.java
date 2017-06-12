import java.util.Scanner;

class BSTDriver {
	public static void main(String[] args) {

		BST binary_tree = new BST();
		Scanner s = new Scanner(System.in);

		System.out.println("new: make a new empty tree");
		System.out.println("i: insert a string");
		System.out.println("r: remove a node contaning a string");
		System.out.println("c: contains a string");
		System.out.println("g: get a node that has a string as value");
		System.out.println("x: findMax, returns a string");
		System.out.println("n: findMin, returns a string");
		System.out.println("v: val, gets the value stored in the root node");
		System.out.println("s: size");
		System.out.println("e: empty");
		System.out.println("q: quit the tester loop");
		System.out.println("p: print the tree for inspection (see below)");

		while (0 != 1) {
			System.out.println("What would you like to do?");

			String command = s.nextLine();

			if (command.equals("new")) {

				binary_tree = new BST();
			}

			else if (command.equals("i")) {

				String insert_string;
				System.out
						.println("Type in a string you would like to insert.");
				insert_string = s.nextLine();
				binary_tree.insert(insert_string);
			}

			else if (command.equals("r")) {

				String remove_string;
				System.out
						.println("Type in a string you would like to remove.");
				remove_string = s.nextLine();
				binary_tree.remove(remove_string);
			} else if (command.equals("c")) {

				String find_string;
				System.out
						.println("Type a string you would like to find in the tree.");
				find_string = s.nextLine();
				if (binary_tree.contains(find_string)) {
					System.out.println("Yes, the tree contains this string.");
				} else {
					System.out
							.println("No, the tree does not contain this string.");
				}
			} else if (command.equals("g")) {

				String get_string;
				System.out
						.println("Type in a string you would like to retrieve.");
				get_string = s.nextLine();
				if (binary_tree.contains(get_string)) {
					System.out.println("Your string was found at this node: "
							+ binary_tree.get(get_string));
				} else {
					System.out
							.println("Your string was not found in the tree.");
				}
			} else if (command.equals("x")) {
				System.out.println("The maximum value in the tree is: "
						+ binary_tree.findMax());
			} else if (command.equals("n")) {
				System.out.println("The minimum value in the tree is: "
						+ binary_tree.findMin());
			} else if (command.equals("v")) {
				System.out.println("The value at the root of the tree is: "
						+ binary_tree.value());
			} else if (command.equals("s")) {
				System.out.println("The size of the tree is: "
						+ binary_tree.size());
			} else if (command.equals("e")) {
				if (binary_tree.isEmpty()) {
					System.out.println("Yes, the tree is empty.");
				} else {
					System.out.println("No, the tree is not empty.");
				}
			} else if (command.equals("q")) {
				System.exit(0);
			} else if (command.equals("p")) {
				binary_tree.print();
			} else {
				System.out.println("That is not a command.");
			}
		}
	}

}

class TreeNode {

	String data;
	TreeNode left = null;
	TreeNode right = null;

	public TreeNode(String s) {

		data = s;
	}

	public void setData(String s) {

		data = s;
	}

	public String getData() {

		return this.data;
	}

	public void setRight(TreeNode n) {

		right = n;
	}

	public void setLeft(TreeNode n) {

		left = n;
	}

	public TreeNode getRight() {

		return right;
	}

	public TreeNode getLeft() {

		return left;
	}

}

class BST {

	TreeNode root = null;
	int size = 0;

	public boolean isEmpty() {

		return size == 0;
	}

	public int size() {

		return size;
	}

	public String value() {

		return root.getData();
	}

	public boolean contains(String element) {

		return contains(root, element);
	}

	private boolean contains(TreeNode root, String element) {

		boolean inTree = false;

		while (root != null && inTree == false) {

			if (root.getData().compareTo(element) > 0) {

				root = root.getLeft();
			} else if (root.getData().compareTo(element) < 0) {

				root = root.getRight();
			} else {

				inTree = true;
				break;
			}

			inTree = contains(root, element);
		}
		return inTree;
	}

	public TreeNode get(String get_string) {

		return get(root, get_string);
	}

	private TreeNode get(TreeNode root, String get_string) {

		while (root != null) {

			if (root.getData().compareTo(get_string) > 0) {

				root = root.getLeft();
			} else if (root.getData().compareTo(get_string) < 0) {

				root = root.getRight();
			} else {

				return root;
			}
			get(root, get_string);
		}
		return null;
	}

	public BST insert(String s) {

		size++;
		root = insert(root, s);
		return this;
	}

	private TreeNode insert(TreeNode node, String new_data) {

		if (node == null) {

			node = new TreeNode(new_data);
		}

		else {
			if (new_data.compareTo(node.getData()) < 0) {

				node.left = insert(node.left, new_data);
			}

			else {

				node.right = insert(node.right, new_data);
			}
		}

		return node;
	}

	public BST remove(String rem_data) {

		size--;
		root = remove(root, rem_data);
		return this;
	}

	private TreeNode remove(TreeNode node, String rem_data) {

		if (node == null) {
		} else if (node.getData().compareTo(rem_data) < 0) {
			node.right = remove(node.right, rem_data);
		} else if (node.getData().compareTo(rem_data) > 0) {
			node.left = remove(node.left, rem_data);
		} else {
			if (node.getLeft() == null && node.getRight() == null) {
				node = null;
			} else if (node.getRight() == null) {
				node = node.left;
			} else if (node.left == null) {
				node = node.right;
			} else {
				node.right = remove(node.right, node.right.right.data);
				node.data = node.right.right.data;
			}
		}

		return node;
	}

	public String findMin() {

		return findMin(root);

	}

	public String findMin(TreeNode node) {

		if (node.getLeft() == null) {

			return node.getData();
		} else {
			return findMin(node.getLeft());
		}
	}

	public String findMax() {

		return findMax(root);
	}

	private String findMax(TreeNode node) {

		if (node.getRight() == null) {

			return node.getData();
		} else {
			return findMax(node.getRight());
		}
	}

	public void print() {
		print(root, 0);
	}

	private void print(TreeNode node, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}
		System.out.println(node.getData());
		if (node.getLeft() == null && node.getRight() == null)
			return;
		if (node.getLeft() != null) {
			print(node.getLeft(), indent + 1);
			}
			if (node.getRight() != null) {
			print(node.getRight(), indent + 1);
			}
	}
}