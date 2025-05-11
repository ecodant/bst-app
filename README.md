# Binary Tree Application Documentation

## Overview

This Java application provides a graphical interface for working with binary search trees. It allows users to visualize and manipulate binary trees through various operations and displays the tree structure graphically in real-time.

## Available Methods

### Basic Operations

| Method              | Description                          | Return Type |
| ------------------- | ------------------------------------ | ----------- |
| `isEmpty()`         | Checks if the tree is empty          | `boolean`   |
| `add(int value)`    | Adds a new value to the tree         | `void`      |
| `exists(int value)` | Checks if a value exists in the tree | `boolean`   |
| `delete(int value)` | Deletes a node from the tree         | `boolean`   |
| `clear()`           | Removes all nodes from the tree      | `void`      |

### Tree Iterate Methods

| Method                  | Description                                                | Return Type          |
| ----------------------- | ---------------------------------------------------------- | -------------------- |
| `inOrderTraversal()`    | Returns nodes using in-order traversal (left-root-right)   | `ArrayList<Integer>` |
| `preOrderTraversal()`   | Returns nodes using pre-order traversal (root-left-right)  | `ArrayList<Integer>` |
| `postOrderTraversal()`  | Returns nodes using post-order traversal (left-right-root) | `ArrayList<Integer>` |
| `levelOrderTraversal()` | Returns nodes level by level from top to bottom            | `ArrayList<Integer>` |

### Tree Properties Methods

| Method                | Description                                                     | Return Type |
| --------------------- | --------------------------------------------------------------- | ----------- |
| `getWeight()`         | Returns the total number of nodes in the tree                   | `int`       |
| `getHeight()`         | Returns the height of the tree (longest path from root to leaf) | `int`       |
| `getLevel(int value)` | Returns the level of a specific node in the tree                | `int`       |
| `countLeaves()`       | Returns the number of leaf nodes (nodes with no children)       | `int`       |
| `isLeaf(int value)`   | Checks if a specific node is a leaf                             | `boolean`   |

### Additional Methods

| Method         | Description                           | Return Type |
| -------------- | ------------------------------------- | ----------- |
| `findMin()`    | Returns the minimum value in the tree | `int`       |
| `getMinNode()` | Returns the node with minimum value   | `int`       |
| `getMaxNode()` | Returns the node with maximum value   | `int`       |

## User Interface

The graphical interface is organized into several tabs:

1. **Operations** - Contains buttons for core operations like adding, checking existence, deleting nodes, and clearing the tree
2. **Iterate** - Contains buttons for different tree traversal methods
3. **Tree Properties** - Contains buttons for checking properties like emptiness, weight, height, etc.
4. **Other Methods** - Contains buttons for operations like finding minimum/maximum nodes

## How to Use

1. Enter a numeric value in the text field at the bottom of the application
2. Click on any operation button to perform that operation with the entered value
3. For operations that don't require a value (like checking if the tree is empty), simply click the button
4. Results of operations will be displayed in the text area at the bottom
5. The tree visualization will update automatically after operations that modify the tree structure

## Visual Representation

The application provides a graphical visualization of the binary tree with:

- Nodes represented as circles with the value
- Lines connecting parent nodes to their children
- Left children
- Right children

## Implementation Details

### TreeNode Class

Represents a single node in the binary tree with:

- An integer value
- References to left and right children
- Method to check if node is a leaf

### BinaryTree Class

Implements the binary search tree logic with:

- Recursive methods for all tree operations
- Proper tree balancing during node deletion
- Efficient traversal algorithms

### TreePanel Class

Handles the visual representation with:

- Custom painting of nodes and connections
- Dynamic spacing based on tree size and panel dimensions
- Automatic repositioning as tree structure changes
