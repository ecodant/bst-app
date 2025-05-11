package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BinaryTree {
	private TreeNode root;

	public BinaryTree() {
		root = null;
	}

	// Verifies if the tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	// Adds a new value to the tree
	public void add(int value) {
		root = addRec(root, value);
	}

	private TreeNode addRec(TreeNode node, int value) {
		if (node == null) {
			return new TreeNode(value);
		}

		if (value < node.value) {
			node.left = addRec(node.left, value);
		} else if (value > node.value) {
			node.right = addRec(node.right, value);
		}

		return node;
	}

	// Checks if a value exists in the tree
	public boolean exists(int value) {
		return existsRec(root, value);
	}

	private boolean existsRec(TreeNode node, int value) {
		if (node == null) {
			return false;
		}

		if (node.value == value) {
			return true;
		}

		if (value < node.value) {
			return existsRec(node.left, value);
		} else {
			return existsRec(node.right, value);
		}
	}

	// Gets the weight (number of nodes) of the tree
	public int getWeight() {
		return getWeightRec(root);
	}

	private int getWeightRec(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + getWeightRec(node.left) + getWeightRec(node.right);
	}

	// Gets the height of the tree
	public int getHeight() {
		return getHeightRec(root);
	}

	private int getHeightRec(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
	}

	// Gets the level of a node in the tree
	public int getLevel(int value) {
		return getLevelRec(root, value, 0);
	}

	private int getLevelRec(TreeNode node, int value, int level) {
		if (node == null) {
			return -1;
		}

		if (node.value == value) {
			return level;
		}

		int leftLevel = getLevelRec(node.left, value, level + 1);
		if (leftLevel != -1) {
			return leftLevel;
		}

		return getLevelRec(node.right, value, level + 1);
	}

	// Counts the number of leaf nodes
	public int countLeaves() {
		return countLeavesRec(root);
	}

	private int countLeavesRec(TreeNode node) {
		if (node == null) {
			return 0;
		}

		if (node.isLeaf()) {
			return 1;
		}

		return countLeavesRec(node.left) + countLeavesRec(node.right);
	}

	// Gets the minimum value in the tree
	public int findMin() {
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return findMinRec(root);
	}

	private int findMinRec(TreeNode node) {
		if (node.left == null) {
			return node.value;
		}
		return findMinRec(node.left);
	}

	// Gets the minimum node value
	public int getMinNode() {
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return findMinRec(root);
	}

	// Gets the maximum node value
	public int getMaxNode() {
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return findMaxRec(root);
	}

	private int findMaxRec(TreeNode node) {
		if (node.right == null) {
			return node.value;
		}
		return findMaxRec(node.right);
	}

	// Checks if a node is a leaf
	public boolean isLeaf(int value) {
		TreeNode node = findNode(root, value);
		return node != null && node.isLeaf();
	}

	private TreeNode findNode(TreeNode node, int value) {
		if (node == null || node.value == value) {
			return node;
		}

		if (value < node.value) {
			return findNode(node.left, value);
		} else {
			return findNode(node.right, value);
		}
	}

	// In-order traversal
	public ArrayList<Integer> inOrderTraversal() {
		ArrayList<Integer> result = new ArrayList<>();
		inOrderRec(root, result);
		return result;
	}

	private void inOrderRec(TreeNode node, ArrayList<Integer> result) {
		if (node != null) {
			inOrderRec(node.left, result);
			result.add(node.value);
			inOrderRec(node.right, result);
		}
	}

	// Pre-order traversal
	public ArrayList<Integer> preOrderTraversal() {
		ArrayList<Integer> result = new ArrayList<>();
		preOrderRec(root, result);
		return result;
	}

	private void preOrderRec(TreeNode node, ArrayList<Integer> result) {
		if (node != null) {
			result.add(node.value);
			preOrderRec(node.left, result);
			preOrderRec(node.right, result);
		}
	}

	// Post-order traversal
	public ArrayList<Integer> postOrderTraversal() {
		ArrayList<Integer> result = new ArrayList<>();
		postOrderRec(root, result);
		return result;
	}

	private void postOrderRec(TreeNode node, ArrayList<Integer> result) {
		if (node != null) {
			postOrderRec(node.left, result);
			postOrderRec(node.right, result);
			result.add(node.value);
		}
	}

	// Level-order traversal (breadth-first)
	public ArrayList<Integer> levelOrderTraversal() {
		ArrayList<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			result.add(node.value);

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}
		}

		return result;
	}

	// Deletes a node from the tree
	public boolean delete(int value) {
		if (root == null) {
			return false;
		}

		if (!exists(value)) {
			return false;
		}

		root = deleteRec(root, value);
		return true;
	}

	private TreeNode deleteRec(TreeNode node, int value) {
		if (node == null) {
			return null;
		}

		if (value < node.value) {
			node.left = deleteRec(node.left, value);
		} else if (value > node.value) {
			node.right = deleteRec(node.right, value);
		} else {
			// Node with only one child or no child
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}

			// Node with two children: Get the inorder successor (smallest in the right
			// subtree)
			node.value = findMinRec(node.right);

			// Delete the inorder successor
			node.right = deleteRec(node.right, node.value);
		}

		return node;
	}

	// Clears the tree
	public void clear() {
		root = null;
	}

	// Gets the root of the tree
	public TreeNode getRoot() {
		return root;
	}
}