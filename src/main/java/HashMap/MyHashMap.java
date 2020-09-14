package HashMap;

import java.util.Arrays;

public class MyHashMap {

    private static final int SIZE = 16;

    private Entry table[] = new Entry[SIZE];

    class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object k, Object v) {
            key = k;
            value = v;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getKey() {
            return key;
        }
    }

    public Entry get(Object k) {
        int hash = k.hashCode() % SIZE;
        Entry e = table[hash];

        while(e != null) {
            if(e.key.equals(k)) {
                return e;
            }
            e = e.next;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap myHashMap = (MyHashMap) o;
        return Arrays.equals(table, myHashMap.table);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }

    public void put(Object k, Object v) {
        int hash = k.hashCode() % SIZE;
        Entry e = table[hash];

        if(e != null) {

            if(e.key.equals(k)) {
                e.value = v;
            } else {
                while(e.next != null) {
                    e = e.next;
                }
                Entry entryInOldBucket = new Entry(k, v);
                e.next = entryInOldBucket;
            }
        } else {
            // create new bucket for new element in the map.
            Entry entryInNewBucket = new Entry(k, v);
            table[hash] = entryInNewBucket;
        }
    }

    public boolean containsKey(Object key) {
        return true;
    }

    public int size() {

        return SIZE;
    }

    public Object/*value*/ remove(Object key) {
        return 45;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();

        myHashMap.put("Pavel", 22);
        myHashMap.put("Sara", 22);
        myHashMap.put("Gena", 11);
        myHashMap.put("Vova", 4);

        System.out.println(myHashMap.size());

        Entry e = myHashMap.get("Pavel");
        System.out.println("" + e.getValue());
    }
}