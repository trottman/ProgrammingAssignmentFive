
import java.io.*;
import java.util.*;

/**
 * CSCI-C 202 Lab7 BinarySearchTrees
 *
 * @author Tabitha Rottman
 * @since 4/6/2017
 * @version 1.5
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    /**
     *
     */
    protected TreeNode<E> root;

    /**
     *
     */
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e
     * @return
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     *Helper method to the search method above that returns true if the element is in the tree. 
     * @param e
     * @param count
     * @return
     */
    public boolean search(E e, int[] count) {
        TreeNode<E> temp = root; // Start from the root
        int i = 0;
        while (temp != null) {
            i++;
            count[0] = i;
            if (e.compareTo(temp.element) < 0) {
                temp = temp.left;
            } else if (e.compareTo(temp.element) > 0) {
                temp = temp.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     *
     * @param e
     * @return
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     *
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     *
     * @param root
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     *
     * @param root
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     *
     * @param root
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    void add(String temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inner class tree node
     *
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         *
         * @param e
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     *
     * @param e
     * @return
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        while (current != null && !current.element.equals(e)) {
            list.add(current.element);
            if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
        }
        if (current == null) {
            list.clear();
        } else {
            list.add(e);
        }
        return list; // Return an array of elements
    }

    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    /**
     * Determines the number of leaves(nodes with no children) within a BST.
     *
     * @return
     */
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    private int getNumberOfLeaves(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no  such element exists. */
    /**
     * Creates the left subtree.
     *
     * @param e
     * @return
     */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> lst = new ArrayList<>();
        TreeNode<E> temp = root;
        while (temp != null && temp.element.compareTo(e) != 0) {
            if (e.compareTo(temp.element) < 0) {
                temp = temp.left;
            } else if (e.compareTo(temp.element) > 0) {
                temp = temp.right;
            }
        }
        if (temp != null) {
            lst = poForSubTree(temp, lst);
        }
        return lst;
    }

    private ArrayList<E> poForSubTree(TreeNode<E> root, ArrayList<E> pst) {
        if (root != null) {
            pst.add(root.element);
            poForSubTree(root.left, pst);
            poForSubTree(root.right, pst);
        }
        return pst;
    }

    /**
     * Inorder traversal from a subtree
     * @param root
     * @param lst
     */
    protected void inorderForPredecessor(TreeNode<E> root, ArrayList<E> lst) {
        inorderForPredecessor(root.left, lst);
        lst.add(root.element);
        inorderForPredecessor(root.right, lst);
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no  such element exists. */
    /**
     * Creates the right subtree.
     *
     * @param e
     * @return
     */
    public ArrayList<E> rightSubTree(E e) {
        //left for you to implement in Lab 7
        ArrayList<E> lst = new ArrayList<>();
        TreeNode<E> temp = root;
        while (temp != null && temp.element.compareTo(e) != 0) {
            if (e.compareTo(temp.element) > 0) {
                temp = temp.right;
            } else if (e.compareTo(temp.element) < 0) {
                temp = temp.left;
            }
        }
        temp = temp.right;
        poForSubTree(temp, lst);
        return lst;
    }

    /**
     * Compares two trees and determines whether they are equal or not.
     *
     * @param tree
     * @return whether the comparison is true or false
     */
    public boolean sameTree(BinarySearchTree tree) {
        return sameTree(this.root, tree.root);

    }

    private boolean sameTree(TreeNode<E> root1, TreeNode<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            return root1.element.equals(root2.element) && sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     *
     * @param e
     * @return
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     *
     * @return
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     *
     * @return
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
