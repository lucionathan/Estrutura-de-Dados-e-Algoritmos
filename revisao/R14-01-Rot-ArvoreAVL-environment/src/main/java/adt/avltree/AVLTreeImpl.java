package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

import static adt.bt.Util.leftRotation;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
        AVLTree<T> {

    // TODO Do not forget: you must override the methods insert and remove
    // conveniently.

    // AUXILIARY
    protected int calculateBalance(BSTNode<T> node) {

        int returnAux = 0;

        if (!node.isEmpty()) {
            returnAux = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
        }

        return returnAux;
    }

    // AUXILIARY
    protected void rebalance(BSTNode<T> node) {
        int balance = calculateBalance(node);

        if (balance < -1) {
            if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
                rightRotation((BSTNode<T>) node.getRight());
            }
            leftRotation(node);
        } else if (balance > 1) {
            if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
                leftRotation((BSTNode<T>) node.getLeft());
            }
            rightRotation(node);
        }
    }

    private void rightRotation(BSTNode<T> node) {
        if (node != null && root.equals(node)) {
            this.root = Util.rightRotation(node);
        } else {
            BSTNode<T> aux = Util.rightRotation(node);
            if (aux.getData().compareTo(aux.getParent().getData()) > 0) {
                aux.getParent().setRight(aux);
            } else {
                aux.getParent().setLeft(aux);
            }
        }
    }

    private void leftRotation(BSTNode<T> node) {
        if (node != null && root.equals(node)) {
            this.root = Util.leftRotation(node);
        } else {
            BSTNode<T> aux = Util.leftRotation(node);
            if (aux.getData().compareTo(aux.getParent().getData()) > 0) {
                aux.getParent().setRight(aux);
            } else {
                aux.getParent().setLeft(aux);
            }
        }
    }


    // AUXILIARY
    protected void rebalanceUp(BSTNode<T> node) {
        BSTNode<T> parent = (BSTNode<T>) node.getParent();
        while (parent != null) {
            rebalance(parent);
            parent = (BSTNode<T>) parent.getParent();
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            insert(this.root, element);
        }

    }

    private void insert(BSTNode<T> node, T element) {
        if (node.isEmpty()) {
            node.setData(element);
            node.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
            node.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(node).build());
        } else {
            if (node.getData().compareTo(element) > 0) {
                insert((BSTNode<T>) node.getLeft(), element);
            } else if (node.getData().compareTo(element) < 0) {
                insert((BSTNode<T>) node.getRight(), element);
            }
            rebalance(node);
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            remove(search(element));
        }
    }

    private void remove(BSTNode<T> node) {
        if (node != null && !node.isEmpty()) {
            if (node.isLeaf()) {
                removeFolha(node);
                rebalanceUp(node);
            } else if (isOnlyChild(node) == 1) {
                removeComUmFilho(node);
                rebalanceUp(node);
            } else {
                removeComDoisFilhos(node);
            }
        }
    }

}
