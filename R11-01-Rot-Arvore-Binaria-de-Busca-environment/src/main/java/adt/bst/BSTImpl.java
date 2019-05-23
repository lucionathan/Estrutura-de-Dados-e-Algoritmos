package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

    protected BSTNode<T> root;

    public BSTImpl() {
        root = new BSTNode<T>();
    }

    public BSTNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int height() {
        return height(this.root);
    }

    private int height(BSTNode<T> node) {
        int treeHeight;

        if (node.isEmpty()) {
            treeHeight = -1;
        } else {
            int left = height((BSTNode<T>) node.getLeft());
            int right = height((BSTNode<T>) node.getRight());

            treeHeight = Math.max(left, right) + 1;
        }

        return treeHeight;
    }

    @Override
    public BSTNode<T> search(T element) {
        if (element != null) {
            return search(this.root, element);
        }
        return null;
    }

    private BSTNode<T> search(BSTNode<T> node, T element) {
        BSTNode<T> result = null;

        if (node.isEmpty()) {
            result = (BSTNode<T>) new BSTNode.Builder<T>().build();
        } else if (element.equals(node.getData())) {
            result = node;
        } else if (element.compareTo(node.getData()) < 0) {
            result = search((BSTNode<T>) node.getLeft(), element);
        } else if (element.compareTo(node.getData()) > 0) {
            result = search((BSTNode<T>) node.getRight(), element);
        }

        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            insert(this.root, element);
        }

    }

    private void insert(BSTNode<T> root, T element) {
        if (root.isEmpty()) {
            root.setData(element);
            root.setLeft((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
            root.setRight((BSTNode<T>) new BSTNode.Builder<T>().parent(root).build());
        } else {
            if (root.getData().compareTo(element) > 0) {
                insert((BSTNode<T>) root.getLeft(), element);
            } else if (root.getData().compareTo(element) < 0) {
                insert((BSTNode<T>) root.getRight(), element);
            }
        }
    }

    @Override
    public BSTNode<T> maximum() {
        BSTNode<T> result;

        if (root.isEmpty()) {
            result = null;
        }
        else {
            result = maximum(root);
        }

        return result;
    }

    private BSTNode<T> maximum(BSTNode<T> node) {
        while (!node.getRight().isEmpty()) {
            node = (BSTNode<T>) node.getRight();
        }

        return node;
    }

    @Override
    public BSTNode<T> minimum() {
        BSTNode<T> result;

        if (root.isEmpty()) {
            result = null;
        }
        else {
            result = minimum(root);
        }

        return result;
    }

    private BSTNode<T> minimum(BSTNode<T> node) {
        while (!node.getLeft().isEmpty()) {
            node = (BSTNode<T>) node.getLeft();
        }

        return node;
    }

    @Override
    public BSTNode<T> sucessor(T element) {
        BSTNode<T> node = search(element);
        return sucessor(node);
    }

    private BSTNode<T> sucessor(BSTNode<T> node) {
        BSTNode<T> sucessor;

        if (node.isEmpty()) {
            sucessor = null;
        }
        else if (!node.getRight().isEmpty()) {
            sucessor = minimum((BSTNode<T>) node.getRight());
        }
        else {
            BSTNode<T> parent = (BSTNode<T>) node.getParent();

            while (parent != null && isRightChild(node, parent)) {
                node = parent;
                parent = (BSTNode<T>) parent.getParent();
            }

            sucessor = parent;
        }

        return sucessor;
    }

    private boolean isRightChild(BSTNode<T> child, BSTNode<T> parent) {
        return !parent.getRight().isEmpty() && parent.getRight().getData().compareTo(child.getData()) == 0;
    }

    @Override
    public BSTNode<T> predecessor(T element) {
        BSTNode<T> node = search(element);
        return predecessor(node);
    }

    private BSTNode<T> predecessor(BSTNode<T> node) {
        BSTNode<T> result;

        if (node.isEmpty()) {
            result = null;
        }
        else if (!node.getLeft().isEmpty()) {
            result = maximum((BSTNode<T>) node.getLeft());
        }
        else {
            BSTNode<T> parent = (BSTNode<T>) node.getParent();

            while (parent != null && isLeftChild(node, parent)) {
                node = parent;
                parent = (BSTNode<T>) parent.getParent();
            }

            result = parent;
        }

        return result;
    }

    private boolean isLeftChild(BSTNode<T> child, BSTNode<T> parent) {
        return !parent.getLeft().isEmpty() && parent.getLeft().getData().compareTo(child.getData()) == 0;
    }

    @Override
    public void remove(T element) {
        BSTNode<T> node = search(element);
        if (!node.isEmpty()) {
            remove(node);
        }
    }

    private void remove(BSTNode<T> node) {
        if (node.isLeaf()) {
            node.setData(null);
            node.setLeft(null);
            node.setRight(null);
        }
        else {

            BSTNode<T> sucessor = sucessor(node);
            
            if (sucessor != null && sucessor.getData().compareTo(node.getData()) != 0) {
                node.setData(sucessor.getData());
                remove(sucessor);
            }
            else {
                BSTNode<T> predecessor = predecessor(node);
                if (predecessor != null && predecessor.getData().compareTo(node.getData()) != 0) {
                    node.setData(predecessor.getData());
                    remove(predecessor);
                }
            }
        }
    }

    @Override
    public T[] preOrder() {
        T[] array = (T[]) new Comparable[size()];
        preOrderRecursive(array, root);
        return array;
    }

    private void preOrderRecursive(T[] array, BSTNode<T> node) {
        if (!node.isEmpty()) {
            addToArray(array, node.getData());
            preOrderRecursive(array, (BSTNode<T>) node.getLeft());
            preOrderRecursive(array, (BSTNode<T>) node.getRight());
        }
    }

    @Override
    public T[] order() {
        T[] array = (T[]) new Comparable[size()];
        orderRecursive(array, root);
        return array;
    }

    private void orderRecursive(T[] array, BSTNode<T> node) {
        if (!node.isEmpty()) {
            orderRecursive(array, (BSTNode<T>) node.getLeft());
            addToArray(array, node.getData());
            orderRecursive(array, (BSTNode<T>) node.getRight());
        }
    }

    @Override
    public T[] postOrder() {
        T[] array = (T[]) new Comparable[size()];
        postOrderRecursive(array, this.root);
        return array;
    }

    private void postOrderRecursive(T[] array, BSTNode<T> node) {
        if (!node.isEmpty()) {
            postOrderRecursive(array, (BSTNode<T>) node.getLeft());
            postOrderRecursive(array, (BSTNode<T>) node.getRight());
            addToArray(array, node.getData());
        }
    }

    private void addToArray(T[] array, T data) {
        int i = 0;

        while (i < array.length) {
            if (array[i] == null) {
                array[i] = data;
                break;
            }

            i++;
        }
    }

    /**
     * This method is already implemented using recursion. You must understand
     * how it work and use similar idea with the other methods.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode<T> node) {
        int result = 0;
        // base case means doing nothing (return 0)
        if (!node.isEmpty()) { // indusctive case
            result = 1 + size((BSTNode<T>) node.getLeft())
                    + size((BSTNode<T>) node.getRight());
        }
        return result;
    }

}
