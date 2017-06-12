import java.util.Random;
import java.util.Scanner;

class SPLTDriver {
	public static void main(String[] args) {

		SPLT binary_tree = new SPLT();
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
		System.out.println("f: fill the tree with random strings");

		while (0 != 1) {
			System.out.println("What would you like to do?");

			String command = s.nextLine();

			if (command.equals("new")) {

				binary_tree = new SPLT();
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
						+ binary_tree.root.data);
			} else if (command.equals("s")) {
				System.out.println("The size of the tree is: "
						+ binary_tree.size);
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
			} else if (command.equals("f")) {
				System.out
						.println("How many random strings would you like in the tree?");
				int num;
				num = s.nextInt();
				for (int i = 0; i < num; i++) {
					MyRandom random = new MyRandom();
					binary_tree.insert(random.nextString(6, 10));
				}
			} else {
				System.out.println("That is not a command.");
			}
		}
	}

}

class TreeNode {
	TreeNode(String new_data) {
		data = new_data;
		left = right = null;
	}

	String data;
	TreeNode left;
	TreeNode right;
}

class SPLT {
	TreeNode root;
	int size;

	public SPLT() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {

		return size == 0;
	}

	public int size() {

		return size;
	}

	public String value() {

		return root.data;
	}

	int height(TreeNode node) {
		if (node == null)
			return -1;

		int height_left = height(node.left);
		int height_right = height(node.right);

		if (height_left > height_right)
			return height_left + 1;
		else
			return height_right + 1;
	}

	public boolean contains(String element) {

		return contains(root, element);
	}

	private boolean contains(TreeNode root, String element) {

		boolean inTree = false;

		while (root != null && inTree == false) {

			if (root.data.compareTo(element) > 0) {

				root = root.left;
			} else if (root.data.compareTo(element) < 0) {

				root = root.right;
			} else {

				inTree = true;
				break;
			}

			inTree = contains(root, element);
		}
		splay(element);
		return inTree;
	}

	public String get(String data) {
		if (root == null) {
			return null;
		}
		splay(data);
		if (root.data.compareTo(data) != 0) {

			return null;
		}
		return root.data;
	}

	public SPLT insert(String data) {

		TreeNode n;
		size++;
		if (root == null) {

			root = new TreeNode(data);
			return this;
		}
		splay(data);
		if ((data.compareTo(root.data)) == 0) {

			return this;
		}
		n = new TreeNode(data);
		if (data.compareTo(root.data) < 0) {

			n.left = root.left;
			n.right = root;
			root.left = null;
		} else {

			n.right = root.right;
			n.left = root;
			root.right = null;
		}
		root = n;
		return this;
	}

	public SPLT remove(String data) {

		TreeNode x;
		size--;
		splay(data);
		if (data.compareTo(root.data) != 0) {

			return this;
		}
		if (root.left == null) {

			root = root.right;
		} else {

			x = root.right;
			root = root.left;
			splay(data);
			root.right = x;
		}
		return this;
	}

	public String findMin() {

		return findMin(root);

	}

	public String findMin(TreeNode node) {

		if (node.left == null) {

			return node.data;
		} else {
			return findMin(node.left);
		}
	}

	public String findMax() {

		return findMax(root);
	}

	private String findMax(TreeNode node) {

		if (node.right == null) {

			return node.data;
		} else {
			return findMax(node.right);
		}
	}

	private void splay(String data) {

		TreeNode new_node = new TreeNode(null);
		TreeNode new_left, new_right, parent, x;
		new_left = new_node;
		new_right = new_node;
		parent = root;
		new_node.left = null;
		new_node.right = null;
		while (1 != 0) {

			if (data.compareTo(parent.data) < 0) {

				if (parent.left == null) {

					break;
				}
				if (data.compareTo(parent.left.data) < 0) {

					x = parent.left;
					parent.left = x.right;
					x.right = parent;
					parent = x;
					if (parent.left == null) {
						break;
					}
				}
				new_right.left = parent;
				new_right = parent;
				parent = parent.left;
			} else if (data.compareTo(parent.data) > 0) {

				if (parent.right == null) {

					break;
				}
				if (data.compareTo(parent.right.data) > 0) {

					x = parent.right;
					parent.right = x.left;
					x.left = parent;
					parent = x;

					if (parent.right == null) {

						break;
					}
				}

				new_left.right = parent;
				new_left = parent;
				parent = parent.right;
			} else {

				break;
			}
		}

		new_left.right = parent.left;
		new_right.left = parent.right;
		parent.left = new_node.right;
		parent.right = new_node.left;
		root = parent;
	}

	public void print() {
		print(root, 0);
	}

	private void print(TreeNode node, int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("   ");
		}
		System.out.println(node.data);
		if (node.left == null && node.right == null)
			return;
		if (node.left != null) {
			print(node.left, indent + 1);
		}
		if (node.right != null) {
			print(node.right, indent + 1);
		}
	}
}

class MyRandom {

	private static Random rn = new Random();

	MyRandom() {
	}

	public static int rand(int lo, int hi) {
		int n = hi - lo + 1;
		int i = rn.nextInt() % n;
		if (i < 0)
			i = -i;
		return lo + i;
	}

	public static String nextString(int lo, int hi) {
		int n = rand(lo, hi);
		byte b[] = new byte[n];
		for (int i = 0; i < n; i++)
			b[i] = (byte) rand('a', 'z');
		return new String(b, 0);
	}

	public static String nextString() {
		return nextString(5, 25);
	}

}