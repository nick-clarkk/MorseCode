import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E>  {
    //Instance variable
    protected Node<E> mRoot;


    //Provides space for a tree but no actual tree
    public BinaryTree() {
        mRoot = null;
    }

    //Provides a root with actual data
    public BinaryTree(Node<E> root) {
        mRoot = root;
    }

    //Unites two binary trees by providing a root between them
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        mRoot = new Node<E>(data);
        if(leftTree == null) //If leftTree doesn't exist
            mRoot.mLeft = null;
        else
            mRoot.mLeft = leftTree.mRoot;

        if(rightTree == null) //If rightTree doesn't exist
            mRoot.mRight = null;
        else
            mRoot.mRight = rightTree.mRoot;
    }


    /**
     * Returns the subtree to the left of the root. Returns null if the tree is empty
     * or there is no left subtree.
     *
     * @return the left subtree of the root node.
     */
    public BinaryTree<E> getLeftSubtree() {
        if(mRoot != null && mRoot.mLeft != null)
            return new BinaryTree<>(mRoot.mLeft);
        else
            return null;
    }

    /**
     * Returns the subtree to the right of the root. Returns null if the tree is empty
     * or there is no right subtree.
     *
     * @return the right subtree if the root node.
     */
    public BinaryTree<E> getRightSubtree() {
        if(mRoot != null && mRoot.mRight != null)
            return new BinaryTree<>(mRoot.mRight);
        else
            return null;
    }


    /**
     * Checks to see if the current node is a leaf.
     *
     * @return boolean if the node is a leaf.
     */
    public boolean isLeaf() {
        return (mRoot.mLeft == null && mRoot.mRight == null);
    }


    /**
     * Returns the roots node data value.
     *
     * @return data of root node.
     */
    public E getData() {
        return mRoot.mData;
    }


    /**
     * Returns a representation of the tree where each subtree is indented by the number of its depth.
     * Calls preOrderTraverse to recursively traverse the tree.
     *
     * @return String representation of the tree.
     */
    public String toString() {
        //Creates mutable string
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(mRoot, 1, sb);
        return sb.toString();
    }

    /**
     * Recursively traverse the tree and adds to the string.
     *
     * @param node current node in the tree.
     * @param depth depth of the node in the tree.
     * @param sb String representation of the tree.
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for(int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null)
            sb.append("null\n");
        else {
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.mLeft, depth+1, sb);
            preOrderTraverse(node.mRight, depth+1, sb); //Inorder and postorder just change the place where you collect root
        }
    }


    /**
     * Reconstructs the tree created by the toString method. Returns a new binary tree
     * constructed by the tree constructor.
     *
     * @param scan scanner used to read a file.
     * @return BinaryTree constructed from the read file.
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if(data.equals("null")) {
            return null;
        }
        else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }




    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~Inner Class Node~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Tree node represents data in a binary tree.
     * @param <E> data type of the binary tree.
     */
    protected static class Node<E>  {
        //Data fields
        protected E mData;
        protected Node<E> mLeft;
        protected Node<E> mRight;

        public Node(E data) {
            //Don't know what is left and right of the new node
            mData = data;
            mLeft = null;
            mRight = null;
        }

        @Override
        public String toString() {
            return mData.toString();
        }
    }
}
