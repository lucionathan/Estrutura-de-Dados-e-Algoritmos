package adt.linkedList.set;

import adt.linkedList.LinkedList;
import adt.linkedList.SingleLinkedListImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class SetLinkedListImplTest {


    protected SetLinkedListImpl<Integer> lista1;
    protected SetLinkedListImpl<Integer> lista2;

    @Before
    public void setUp() throws Exception {

        getImplementations();

        // Lista com 3 elementos.
        lista1.insert(3);
        lista1.insert(2);
        lista1.insert(1);
        lista1.insert(1);
        lista1.insert(1);
        lista1.insert(1);
        lista1.insert(3);
        lista1.insert(2);
        lista1.insert(5);


    }

    private void getImplementations() {
        // TODO O aluno deve ajustar aqui para instanciar sua implementação
        lista1 = new SetLinkedListImpl<>();
        lista2 = new SetLinkedListImpl<>();


    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse(lista1.isEmpty());
        System.out.println(Arrays.toString(lista1.toArray()));
        lista1.removeDuplicates();
        System.out.println(Arrays.toString(lista1.toArray()));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(3, lista1.size());
        Assert.assertEquals(0, lista2.size());
    }

    @Test
    public void testSearch() {
        Assert.assertTrue(2 == lista1.search(2));
        Assert.assertNull(lista1.search(4));
        Assert.assertFalse(3 == lista1.search(2));
    }

    @Test
    public void testInsert() {
        Assert.assertEquals(3, lista1.size());
        lista1.insert(5);
        lista1.insert(7);
        Assert.assertEquals(5, lista1.size());

        Assert.assertEquals(0, lista2.size());
        lista2.insert(4);
        lista2.insert(7);
        Assert.assertEquals(2, lista2.size());
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(3, lista1.size());
        lista1.remove(2);
        lista1.remove(1);
        Assert.assertEquals(1, lista1.size());

    }

    @Test
    public void testToArray() {

        System.out.println(Arrays.toString(lista1.toArray()));
        lista1.removeDuplicates();
        System.out.println(Arrays.toString(lista1.toArray()));
    }
}