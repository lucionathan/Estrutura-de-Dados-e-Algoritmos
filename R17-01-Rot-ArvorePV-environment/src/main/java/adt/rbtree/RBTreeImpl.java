package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {

        return blackHeightRecursive((RBNode<T>) getRoot());
    }

    private int blackHeightRecursive(RBNode<T> node) {
        int altura = 0;

        if (!node.isEmpty()) {
            if (node.getColour() == Colour.BLACK) {
                altura = 1 + Math.max(blackHeightRecursive((RBNode<T>) node.getLeft()), blackHeightRecursive((RBNode<T>) node.getRight()));
            } else {
                altura = Math.max(blackHeightRecursive((RBNode<T>) node.getLeft()), blackHeightRecursive((RBNode<T>) node.getRight()));
            }
        }

        return altura;
    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour()
                && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        return verifyChildrenOfRedNodes((RBNode<T>) root);
    }

    private boolean verifyChildrenOfRedNodes(RBNode<T> node) {

        boolean var = true;

        if (node.getColour() == Colour.RED) {
            if (((RBNode<T>) node.getRight()).getColour() == Colour.BLACK && ((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK) {
                var = true && verifyChildrenOfRedNodes((RBNode<T>) node.getRight()) && verifyChildrenOfRedNodes((RBNode<T>) node.getLeft());
            } else {
                var = false;
            }
        }

        return var;
    }


    /**
     * Verifies the black-height property from the root.
     */
    private boolean verifyBlackHeight() {

        return verifyBlackHeight(root);

    }

    private boolean verifyBlackHeight(BSTNode<T> node) {
        boolean returnAux = true;
        if (blackHeightRecursive((RBNode<T>) node.getLeft()) == blackHeightRecursive((RBNode<T>) node.getRight())) {
            returnAux = true;
        } else {
            returnAux = false;
        }
        returnAux = verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());

        return returnAux;
    }

    @Override
    public void insert(T value) {

        insert((RBNode<T>) root, value);

    }

    private void insert(RBNode<T> node, T element) {
        if (node.isEmpty()) {
            node.setData(element);
            node.setLeft(new RBNode<T>());
            node.setRight(new RBNode<T>());

            node.setColour(Colour.RED);

            fixUpCase1((RBNode<T>) node);

        } else {
            if (node.getData().compareTo(element) > 0) {
                insert((RBNode<T>) node.getLeft(), element);
            } else if (node.getData().compareTo(element) < 0) {
                insert((RBNode<T>) node.getRight(), element);
            }
        }
    }


    @Override
    public RBNode<T>[] rbPreOrder() {

        RBNode<T>[] array = new RBNode[2 * size() + 2];
        rbPreOrder((RBNode<T>) root, array, 0);
        array = returnCleanArray(array);
        return array;
    }

    private RBNode<T>[] returnCleanArray(RBNode<T>[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                index++;
            }
        }

        RBNode<T>[] arrayToReturn = new RBNode[index];
        int indexAux = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                arrayToReturn[indexAux++] = array[i];
            }
        }
        return arrayToReturn;
    }

    protected void rbPreOrder(RBNode<T> node, RBNode<T>[] array, int index) {
        if (!node.isEmpty()) {
            array[index] = node;
            rbPreOrder((RBNode<T>) node.getLeft(), array, 2 * index + 1);
            rbPreOrder((RBNode<T>) node.getRight(), array, 2 * index + 2);
        }
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (root.equals(node)) {
            node.setColour(Colour.BLACK);
        } else {
            fixUpCase2(node);
        }
    }

    protected void fixUpCase2(RBNode<T> node) {
        if (!((RBNode<T>) node.getParent()).getColour().equals(Colour.BLACK)) {
            fixUpCase3(node);
        }
    }

    protected void fixUpCase3(RBNode<T> node) {
        RBNode<T> uncle = getUncle(node);
        RBNode<T> grandpa = getGrandpa(node);
        RBNode<T> parent = (RBNode<T>) node.getParent();

        //fazer checagem no get parent, get uncle, e get grandpa de null e nil.

        if (!uncle.isEmpty() && uncle.getColour().equals(Colour.RED)) {
            parent.setColour(Colour.BLACK);
            uncle.setColour(Colour.BLACK);
            grandpa.setColour(Colour.RED);

            fixUpCase1(grandpa);
        } else {
            fixUpCase4(node);
        }
    }

    private RBNode<T> getGrandpa(RBNode<T> node) {
        return (RBNode<T>) node.getParent().getParent();
    }

    private RBNode<T> getUncle(RBNode<T> node) {
        RBNode<T> uncle = null;
        if (!node.equals(root)) {

            if (node.getParent().getParent().getLeft().equals(node.getParent())) {
                uncle = (RBNode<T>) node.getParent().getParent().getRight();
            } else {
                uncle = (RBNode<T>) node.getParent().getParent().getLeft();
            }
        }

        return uncle;
    }

    protected void fixUpCase4(RBNode<T> node) {

        RBNode<T> grandpa = getGrandpa(node);
        RBNode<T> parent = (RBNode<T>) node.getParent();
        RBNode<T> next = node;

        if (isLeftChild(node, parent) && isRightChild(parent, grandpa)) {
            Util.rightRotation(parent);
            next = (RBNode<T>) node.getRight();
        } else if (isRightChild(node, parent) && isLeftChild(parent, grandpa)) {
            Util.leftRotation(parent);
            next = (RBNode<T>) node.getLeft();
        }

        fixUpCase5(next);
    }

    protected void fixUpCase5(RBNode<T> node) {
        RBNode<T> grandpa = getGrandpa(node);
        RBNode<T> parent = (RBNode<T>) node.getParent();

        if (isLeftChild(node, parent)) {
            Util.rightRotation(grandpa);
        } else {
            Util.leftRotation(grandpa);
        }
    }
}
