package Bst;

import AVLTreeNode.AVLTreeNode;

import java.util.Comparator;

public abstract class BST<E> {
    public <E> BST(Comparator<E> c) {
    }

    public BST(E[] objects) {
    }

    protected abstract AVLTreeNode<E> createNewNode(E e);

    public void inorder() {
    }

    public void postorder() {
    }

    public void preorder() {
    }

    public String getSize() {
        return null;
    }

    protected boolean insert(E e) {
        return false;
    }

    public class TreeNode<E> {
    }
}
