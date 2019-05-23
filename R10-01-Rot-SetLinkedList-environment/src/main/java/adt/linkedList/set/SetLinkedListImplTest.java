package adt.linkedList.set;

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
      lista1.insert(2);
      lista1.insert(2);
      lista1.insert(2);
      lista1.insert(5);
      lista1.insert(5);
      lista1.insert(5);
      lista1.insert(1);
      lista1.insert(1);
      lista1.insert(1);
      lista1.insert(5);
      lista1.insert(5);
      lista1.insert(8);
      lista1.insert(5);
      lista2.insert(5);
      lista2.insert(5);
      lista2.insert(6);
      lista2.insert(6);
      lista2.insert(7);

   }

   private void getImplementations() {
      // TODO O aluno deve ajustar aqui para instanciar sua implementação
      lista1 = new SetLinkedListImpl<>();
      lista2 = new SetLinkedListImpl<>();

   }

   @Test
   public void testIsEmpty() {

      System.out.println("lista 1");
      System.out.println(Arrays.toString(lista1.toArray()));
      System.out.println("lista2");
      System.out.println(Arrays.toString(lista2.toArray()));
      SetLinkedList<Integer> a = lista1.intersection(lista2);
      System.out.println(Arrays.toString(a.toArray()));

   }

   @Test
   public void testSize() {
      System.out.println(Arrays.toString(lista1.toArray()));
      lista1.union(lista2);
      System.out.println("eai gay");
      //        System.out.println(Arrays.toString(lista1.toArray()));
      lista1.concatenate(lista2);
   }

   @Test
   public void testSearch() {
      Assert.assertTrue(2 == lista1.search(2));
      Assert.assertNull(lista1.search(4));
      Assert.assertFalse(3 == lista1.search(2));
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