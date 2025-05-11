package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BinaryTreeApp extends JFrame {
	private BinaryTree tree;
	private TreePanel treePanel;
	private JTextField inputField;
	private JTextArea outputArea;
	private JTabbedPane tabbedPane;

	public BinaryTreeApp() {
		tree = new BinaryTree();
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Binary Tree Application");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		// Panels

		treePanel = new TreePanel();
		add(treePanel, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane();

		// Basic operations panel
		JPanel basicOperationsPanel = createBasicOperationsPanel();
		tabbedPane.addTab("Operations", basicOperationsPanel);

		// Traversal panel
		JPanel traversalPanel = createTraversalPanel();
		tabbedPane.addTab("Iterate", traversalPanel);

		// Tree properties panel
		JPanel propertiesPanel = createPropertiesPanel();
		tabbedPane.addTab("Tree Properties", propertiesPanel);

		// Additional methods panel
		JPanel additionalPanel = createAdditionalMethodsPanel();
		tabbedPane.addTab("Other Methods", additionalPanel);

		// Input and output panel
		JPanel ioPanel = new JPanel(new BorderLayout());

		// Input panel
		JPanel inputPanel = new JPanel(new FlowLayout());
		inputField = new JTextField(15);
		inputPanel.add(new JLabel("Value:"));
		inputPanel.add(inputField);

		// Output panel
		outputArea = new JTextArea(6, 40);
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);

		ioPanel.add(inputPanel, BorderLayout.NORTH);
		ioPanel.add(scrollPane, BorderLayout.CENTER);

		// Combine tabs and IO panel
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(tabbedPane, BorderLayout.CENTER);
		southPanel.add(ioPanel, BorderLayout.SOUTH);

		add(southPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
	}

	private JPanel createBasicOperationsPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 5, 5));

		JButton addButton = new JButton("Add");
		JButton existsButton = new JButton("Exists");
		JButton deleteButton = new JButton("Delete");
		JButton clearButton = new JButton("Clear");

		addButton.addActionListener(e -> addNode());
		existsButton.addActionListener(e -> existsNode());
		deleteButton.addActionListener(e -> deleteNode());
		clearButton.addActionListener(e -> clearTree());

		panel.add(addButton);
		panel.add(existsButton);
		panel.add(deleteButton);
		panel.add(clearButton);

		return panel;
	}

	private JPanel createTraversalPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 5, 5));

		JButton inOrderButton = new JButton("In-Order");
		JButton preOrderButton = new JButton("Pre-Order");
		JButton postOrderButton = new JButton("Post-Order");
		JButton levelOrderButton = new JButton("Level Order");

		inOrderButton.addActionListener(e -> traverseInOrder());
		preOrderButton.addActionListener(e -> traversePreOrder());
		postOrderButton.addActionListener(e -> traversePostOrder());
		levelOrderButton.addActionListener(e -> traverseLevelOrder());

		panel.add(inOrderButton);
		panel.add(preOrderButton);
		panel.add(postOrderButton);
		panel.add(levelOrderButton);

		return panel;
	}

	private JPanel createPropertiesPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 5, 5, 5));

		JButton isEmptyButton = new JButton("IsEmpty");
		JButton weightButton = new JButton("getWeight");
		JButton heightButton = new JButton("getHeight");
		JButton leafCountButton = new JButton("LeafCounter");
		JButton levelButton = new JButton("getLevelNode");

		isEmptyButton.addActionListener(e -> checkIsEmpty());
		weightButton.addActionListener(e -> getWeight());
		heightButton.addActionListener(e -> showTreeHeight());
		leafCountButton.addActionListener(e -> countLeaves());
		levelButton.addActionListener(e -> getLevel());

		panel.add(isEmptyButton);
		panel.add(weightButton);
		panel.add(heightButton);
		panel.add(leafCountButton);
		panel.add(levelButton);

		return panel;
	}

	private JPanel createAdditionalMethodsPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 5, 5));

		JButton minNodeButton = new JButton("Min Node");
		JButton maxNodeButton = new JButton("Max Node");
		JButton findMinButton = new JButton("Find Min");
		JButton isLeafButton = new JButton("Is Leaf?");

		minNodeButton.addActionListener(e -> getMinNode());
		maxNodeButton.addActionListener(e -> getMaxNode());
		findMinButton.addActionListener(e -> findMin());
		isLeafButton.addActionListener(e -> isLeaf());

		panel.add(minNodeButton);
		panel.add(maxNodeButton);
		panel.add(findMinButton);
		panel.add(isLeafButton);

		return panel;
	}

	private void addNode() {
		try {
			int value = Integer.parseInt(inputField.getText());
			tree.add(value);
			treePanel.setTree(tree);
			repaint();
			outputArea.setText("Value " + value + " added successfully.");
			inputField.setText("");
		} catch (NumberFormatException e) {
			outputArea.setText("Error: Please enter a valid number.");
		}
	}

	private void existsNode() {
		try {
			int value = Integer.parseInt(inputField.getText());
			boolean exists = tree.exists(value);
			if (exists) {
				outputArea.setText("Value " + value + " exists in the tree.");
			} else {
				outputArea.setText("Value " + value + " does not exist in the tree.");
			}
			inputField.setText("");
		} catch (NumberFormatException e) {
			outputArea.setText("Error: Please enter a valid number.");
		}
	}

	private void deleteNode() {
		try {
			int value = Integer.parseInt(inputField.getText());
			boolean deleted = tree.delete(value);
			treePanel.setTree(tree);
			repaint();
			if (deleted) {
				outputArea.setText("Value " + value + " deleted successfully.");
			} else {
				outputArea.setText("Value " + value + " not found in the tree.");
			}
			inputField.setText("");
		} catch (NumberFormatException e) {
			outputArea.setText("Error: Please enter a valid number.");
		}
	}

	private void traverseInOrder() {
		ArrayList<Integer> result = tree.inOrderTraversal();
		displayTraversalResult("In-Order (Inorden)", result);
	}

	private void traversePreOrder() {
		ArrayList<Integer> result = tree.preOrderTraversal();
		displayTraversalResult("Pre-Order (Preorden)", result);
	}

	private void traversePostOrder() {
		ArrayList<Integer> result = tree.postOrderTraversal();
		displayTraversalResult("Post-Order (Postorden)", result);
	}

	private void traverseLevelOrder() {
		ArrayList<Integer> result = tree.levelOrderTraversal();
		displayTraversalResult("Level Order (Amplitud)", result);
	}

	private void displayTraversalResult(String traversalType, ArrayList<Integer> result) {
		StringBuilder sb = new StringBuilder();
		sb.append(traversalType).append(" traversal: ");
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i));
			if (i < result.size() - 1) {
				sb.append(", ");
			}
		}
		outputArea.setText(sb.toString());
	}

	private void checkIsEmpty() {
		boolean isEmpty = tree.isEmpty();
		outputArea.setText("Is tree empty? " + isEmpty);
	}

	private void getWeight() {
		int weight = tree.getWeight();
		outputArea.setText("Tree weight: " + weight);
	}

	private void showTreeHeight() {
		int height = tree.getHeight();
		outputArea.setText("Tree height: " + height);
	}

	private void countLeaves() {
		int leafCount = tree.countLeaves();
		outputArea.setText("Number of leaves: " + leafCount);
	}

	private void getLevel() {
		try {
			int value = Integer.parseInt(inputField.getText());
			int level = tree.getLevel(value);
			if (level >= 0) {
				outputArea.setText("Level of node " + value + ": " + level);
			} else {
				outputArea.setText("Node " + value + " not found in the tree.");
			}
			inputField.setText("");
		} catch (NumberFormatException e) {
			outputArea.setText("Error: Please enter a valid number.");
		}
	}

	private void getMinNode() {
		if (tree.isEmpty()) {
			outputArea.setText("Tree is empty.");
			return;
		}
		int min = tree.getMinNode();
		outputArea.setText("Minimum node value: " + min);
	}

	private void getMaxNode() {
		if (tree.isEmpty()) {
			outputArea.setText("Tree is empty.");
			return;
		}
		int max = tree.getMaxNode();
		outputArea.setText("Maximum node value: " + max);
	}

	private void findMin() {
		if (tree.isEmpty()) {
			outputArea.setText("Tree is empty.");
			return;
		}
		int min = tree.findMin();
		outputArea.setText("Minimum value: " + min);
	}

	private void isLeaf() {
		try {
			int value = Integer.parseInt(inputField.getText());
			boolean isLeaf = tree.isLeaf(value);
			if (isLeaf) {
				outputArea.setText("Node " + value + " is a leaf.");
			} else {
				outputArea.setText("Node " + value + " is not a leaf or does not exist.");
			}
			inputField.setText("");
		} catch (NumberFormatException e) {
			outputArea.setText("Error: Please enter a valid number.");
		}
	}

	private void clearTree() {
		tree.clear();
		treePanel.setTree(tree);
		repaint();
		outputArea.setText("Tree cleared successfully.");
		inputField.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BinaryTreeApp app = new BinaryTreeApp();
			app.setVisible(true);
		});
	}
}