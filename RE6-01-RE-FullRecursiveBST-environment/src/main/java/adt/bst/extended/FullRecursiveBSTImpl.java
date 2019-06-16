package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

import java.util.ArrayList;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

    /**
     * Sobrescreva este metodo usando recursao.
     */
    @Override
    public BSTNode<T> maximum() {
        BSTNode<T> result;

        if (root.isEmpty()) {
            result = null;
        } else {
            result = maximum(root);
        }

        return result;
    }

    protected BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> result = node;

        if (!node.getRight().isEmpty()) {
            result = maximum((BSTNode<T>) node.getRight());
        }

        return result;
    }

    /**
     * Sobrescreva este metodo usando recursao.
     */
    @Override
    public BSTNode<T> minimum() {
        BSTNode<T> result;

        if (root.isEmpty()) {
            result = null;
        } else {
            result = minimum(root);
        }

        return result;
    }

    protected BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> result = node;

        if (!node.getLeft().isEmpty()) {
            result = minimum((BSTNode<T>) node.getLeft());
        }

        return result;
    }

    /**
     * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
     * entao o sucessor sera o minimum do filho a direita. Caso contrario
     * o sucessor sera o primeiro ascendente a ter um valor maior.
     */
    @Override
    public BSTNode<T> sucessor(T element) {
        BSTNode<T> node = search(element);
        return sucessor(node);
    }

    private BSTNode<T> sucessor(BSTNode<T> node) {
        BSTNode<T> result;

        if (node.isEmpty()) {
            result = null;
        } else if (!node.getRight().isEmpty()) {
            result = minimum((BSTNode<T>) node.getRight());
        } else {
            result = getSucessorAcima(node);
        }

        return result;
    }

    private BSTNode<T> getSucessorAcima(BSTNode<T> node) {
        BSTNode<T> result = null;
        BSTNode<T> parent = (BSTNode<T>) node.getParent();

        if (parent != null) {
            if (isRightChild(node, parent)) {
                result = getSucessorAcima(parent);
            } else {
                result = parent;
            }
        }

        return result;
    }

    /**
     * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
     * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
     * o predecessor sera o primeiro ascendente a ter um valor menor.
     */
    @Override
    public BSTNode<T> predecessor(T element) {
        BSTNode<T> node = search(element);
        return predecessor(node);
    }

    private BSTNode<T> predecessor(BSTNode<T> node) {
        BSTNode<T> result;

        if (node.isEmpty()) {
            result = null;
        } else if (!node.getLeft().isEmpty()) {
            result = maximum((BSTNode<T>) node.getLeft());
        } else {
            result = getPredecessorAcima(node);
        }

        return result;
    }

    private BSTNode<T> getPredecessorAcima(BSTNode<T> node) {
        BSTNode<T> result = null;

        BSTNode<T> parent = (BSTNode<T>) node.getParent();

        if (parent != null) {
            if (isLeftChild(node, parent)) {
                result = getPredecessorAcima(parent);
            } else {
                result = parent;
            }
        }

        return result;
    }

    @Override
    public T[] elementsAtDistance(int k) {
        ArrayList<T> result = new ArrayList<T>();

        addToArray(result, k, root);

        return (T[]) result.toArray(new Comparable[0]);
    }

    private void addToArray(ArrayList<T> result, int k, BSTNode<T> node) {
        if (k >= 0) {

            if (k == 0) {
                result.add(node.getData());
            } else {
                addToArray(result, k - 1, (BSTNode<T>) node.getLeft());
                addToArray(result, k - 1, (BSTNode<T>) node.getRight());
            }
        }
    }
}
