package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		cleanTree();
		fillTree(array);
		return order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[size()];
		reverseOrder(array, root);
		return array;
	}

	private void reverseOrder(T[] array, BSTNode<T> root) {
		if (!root.isEmpty()) {
			reverseOrder(array, (BSTNode<T>) root.getRight());
			addToArray(array, root.getData());
			reverseOrder(array, (BSTNode<T>) root.getLeft());
		}
	}

	private void fillTree(T[] array) {
		for (T i : array) {
			insert(i);
		}
	}

	private void cleanTree() {
		while (!isEmpty()) {
			remove(getRoot().getData());
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
