package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TreePanel extends JPanel {
	private BinaryTree tree;
	private final int HORIZONTAL_SPACING = 50;
	private final int VERTICAL_SPACING = 70;
	private final int NODE_DIAMETER = 40;

	public TreePanel() {
		setPreferredSize(new Dimension(800, 400));
		setBackground(Color.WHITE);
	}

	public void setTree(BinaryTree tree) {
		this.tree = tree;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (tree != null && !tree.isEmpty()) {
			drawTree(g, tree.getRoot(), getWidth() / 2, 50, getWidth() / 4);
		}
	}

	private void drawTree(Graphics g, TreeNode node, int x, int y, int horizontalSpacing) {
		if (node == null) {
			return;
		}

		g.setColor(Color.WHITE);
		g.fillOval(x - NODE_DIAMETER / 2, y - NODE_DIAMETER / 2, NODE_DIAMETER, NODE_DIAMETER);
		g.setColor(Color.BLACK);
		g.drawOval(x - NODE_DIAMETER / 2, y - NODE_DIAMETER / 2, NODE_DIAMETER, NODE_DIAMETER);

		// Draw the value
		FontMetrics fm = g.getFontMetrics();
		String value = String.valueOf(node.value);
		int stringWidth = fm.stringWidth(value);
		int stringHeight = fm.getAscent();
		g.drawString(value, x - stringWidth / 2, y + stringHeight / 2);

		// Draw left child
		if (node.left != null) {
			int childX = x - horizontalSpacing;
			int childY = y + VERTICAL_SPACING;
			g.drawLine(x, y, childX, childY);
			drawTree(g, node.left, childX, childY, horizontalSpacing / 2);
		}

		// Draw right child
		if (node.right != null) {
			int childX = x + horizontalSpacing;
			int childY = y + VERTICAL_SPACING;
			g.drawLine(x, y, childX, childY);
			drawTree(g, node.right, childX, childY, horizontalSpacing / 2);
		}
	}
}