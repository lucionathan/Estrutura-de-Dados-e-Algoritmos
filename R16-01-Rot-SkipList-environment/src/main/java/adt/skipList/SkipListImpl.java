package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;

    public SkipListImpl(int maxHeight) {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
            root.forward[i] = NIL;
        }
    }


    @Override
    public void insert(int key, T newValue, int height) {
        if (newValue != null) {
            SkipListNode<T>[] update = new SkipListNode[maxHeight];
            SkipListNode<T> auxNode = getRoot();

            auxNode = loopUpdate(key, update, auxNode);

            auxNode = auxNode.getForward(0);

            if (auxNode.getKey() == key) {
                auxNode.setValue(newValue);
            } else {

                SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);

                for (int i = 0; i < height; i++) {
                    newNode.forward[i] = update[i].forward[i];
                    update[i].forward[i] = newNode;
                }
            }
        }
    }

    @Override
    public void remove(int key) {
        if (size() > 0) {

            SkipListNode<T>[] update = new SkipListNode[maxHeight];

            SkipListNode<T> auxNode = getRoot();

            auxNode = loopUpdate(key, update, auxNode);

            auxNode = auxNode.getForward(0);

            if (auxNode.getKey() == key) {
                for (int i = 0; i < auxNode.height(); i++) {
                    if (!update[i].forward[i].equals(auxNode)) {
                        break;
                    } else {
                        update[i].forward[i] = auxNode.forward[i];
                    }
                }
            }
        }
    }

    private SkipListNode<T> loopUpdate(int key, SkipListNode<T>[] update, SkipListNode<T> auxNode) {
        for (int i = maxHeight - 1; i >= 0; i--) {
            while (auxNode.getForward(i).getKey() < key) {
                auxNode = auxNode.getForward(i);
            }
            update[i] = auxNode;
        }
        return auxNode;
    }

    @Override
    public int height() {
        int max = 0;

        if (size() > 0) {
            SkipListNode<T> auxNode = getRoot().getForward(0);
            while (!auxNode.getForward(0).equals(NIL)) {
                if (auxNode.forward.length > max) {
                    max = auxNode.forward.length;
                }

                auxNode = auxNode.getForward(0);
            }
        }

        return max;
    }

    @Override
    public SkipListNode<T> search(int key) {
        SkipListNode<T> varReturn = null;

        if (size() > 0) {

            SkipListNode<T> auxNode = getRoot();

            for (int i = maxHeight - 1; i >= 0; i--) {
                while (auxNode.getForward(i).getKey() < key) {
                    auxNode = auxNode.getForward(i);
                }
            }

            auxNode = auxNode.getForward(0);

            if (auxNode.getKey() == key) {
                varReturn = auxNode;
            }

        }

        return varReturn;

    }

    @Override
    public int size() {
        SkipListNode<T> auxNode = getRoot();

        int count = 0;

        while (!auxNode.getForward(0).equals(NIL)) {
            auxNode = auxNode.getForward(0);
            count++;
        }

        return count;
    }

    @Override
    public SkipListNode<T>[] toArray() {
        SkipListNode<T>[] array = new SkipListNode[size() + 2];
        SkipListNode<T> auxNode = getRoot();

        int count = 0;

        do {

            array[count] = auxNode;
            auxNode = auxNode.getForward(0);
            count++;

        } while (!auxNode.equals(NIL));

        array[count] = NIL;

        return array;
    }

    private SkipListNode<T> getRoot() {
        return this.root;
    }

}

