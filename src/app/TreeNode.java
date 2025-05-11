package app;

public class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
}
