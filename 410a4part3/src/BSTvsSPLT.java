import java.util.Random;

class RandomGenerationDriver {

	public static void main(String[] args) {

		int[] BST_heights = new int[15];
		int[] SPLT_heights = new int[15];
		double avg_BST = 0;
		double avg_SPLT = 0;

		for (int i = 0; i < 15; i++) {
			BST fifteen_BST = new BST();
			int height = 0;
			for (int c = 0; c < 65535; c++) {
				MyRandom random = new MyRandom();
				fifteen_BST.insert(random.nextString(6, 10));
			}
			height = height + fifteen_BST.height(fifteen_BST.root);
			BST_heights[i] = height;
		}
		for (int i = 0; i < 15; i++) {
			avg_BST = avg_BST + BST_heights[i];
		}
		avg_BST = avg_BST / 15;
		for (int i = 0; i < 15; i++) {
			SPLT fifteen_SPLT = new SPLT();
			int height = 0;
			for (int c = 0; c < 65535; c++) {
				MyRandom random = new MyRandom();
				fifteen_SPLT.insert(random.nextString(6, 10));
			}
			height = height + fifteen_SPLT.height(fifteen_SPLT.root);
			SPLT_heights[i] = height;
		}
		for (int i = 0; i < 15; i++) {
			avg_SPLT = avg_SPLT + SPLT_heights[i];
		}
		avg_SPLT = avg_SPLT / 15;
		System.out.println("BST average height: " + avg_BST);
		System.out.println("SPLT average height: " + avg_SPLT);
		System.out.println("");
		System.out.println("BST runs, each tree with 65,535 random strings");
		for (int i = 0; i < 15; i++) {

			System.out.println((i + 1) + ": height is " + BST_heights[i]);
		}
		System.out.println("");
		System.out.println("SPLT runs, each tree with 65,535 random strings");
		for (int i = 0; i < 15; i++) {

			System.out.println((i + 1) + ": height is " + SPLT_heights[i]);
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
		return inTree;
	}

	public TreeNode get(String get_string) {

		return get(root, get_string);
	}

	private TreeNode get(TreeNode root, String get_string) {

		while (root != null) {

			if (root.data.compareTo(get_string) > 0) {

				root = root.left;
			} else if (root.data.compareTo(get_string) < 0) {

				root = root.right;
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
			if (new_data.compareTo(node.data) < 0) {

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
		} else if (node.data.compareTo(rem_data) < 0) {
			node.right = remove(node.right, rem_data);
		} else if (node.data.compareTo(rem_data) > 0) {
			node.left = remove(node.left, rem_data);
		} else {
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.right == null) {
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
