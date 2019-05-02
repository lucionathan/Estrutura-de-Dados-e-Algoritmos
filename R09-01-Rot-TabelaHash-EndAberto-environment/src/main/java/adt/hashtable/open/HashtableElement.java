package adt.hashtable.open;

public class HashtableElement implements Storable {

    private Integer key;

    public HashtableElement(int key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }

}
