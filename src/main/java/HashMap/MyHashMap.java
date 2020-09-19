package HashMap;

public class MyHashMap {

    private final int DEFAULT_CAPACITY = 16;
    private Entry[] table = new Entry[DEFAULT_CAPACITY];
    private int thresold = (int) (table.length * 0.75);
    private int size;

    /**
     * Returns the entry mapped to key in the HashMap.
     */
    public Entry get(Object k) {
        Entry entry;
        if (k == null) {
            entry = nodeThrough(table[0], k);
        } else {
            int hash = hash(k.hashCode());
            int index = indexFor(hash, table.length - 1);
            entry = nodeThrough(table[index], k);
        }
        if (entry != null) {
            return entry;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private Entry nodeThrough(Entry entry, Object key) {
        while (entry != null) {
            if (entry.getKey() != null) {
                if (entry.getKey() == key) {
                    return entry;
                }
            } else {
                if (key == null)
                    return entry;
            }
            entry = entry.getNext();
        }
        return null;
    }

    /**
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     */
    public void put(Object k, Object v) {
        Entry[] newEntry = new Entry[table.length * 2];
        for (Entry entry : table) {
            if (entry != null) {
                moving(entry, newEntry);
            }
        }
        if (k != null) {
            int hash = hash(k.hashCode());
            int index = indexFor(hash, table.length - 1);

            if (table[index] != null) {
                Entry entry = nodeThrough(table[index], k);
                if (entry != null) {
                    entry.setValue(v);
                } else {
                    Entry newentry = new Entry(k, v);
                    newentry.setNext(table[index]);
                    table[index] = newentry;
                    size++;
                }
            } else {
                table[index] = new Entry(k, v);
                size++;
            }
        } else {
            nullKey(v);
        }
    }

    private void nullKey(Object value) {
        if (table[0] != null) {
            Entry entry = nodeThrough(table[0], null);
            if (entry != null) {
                entry.setValue(value);
            } else {
                Entry newEntry = new Entry(null, value);
                newEntry.setNext(table[0]);
                table[0] = newEntry;
                size++;
            }
        }
        else {
            table[0] = new Entry(null, value);
            size++;
        }
    }

    private void moving(Entry entry, Entry[] newEntry) {
        while (entry != null) {
            int index = 0;
            if (entry.getKey() != null) {
                int hash = hash(entry.getKey().hashCode());
                index = hash & (table.length - 1);
            }
            if (newEntry[index] != null) {
                Entry entry1 = new Entry(entry.getKey(), entry.getValue());
                entry1.setNext(newEntry[index]);
                newEntry[index] = entry1;
            } else {
                newEntry[index] = new Entry(entry.getKey(), entry.getValue());
            }
            entry = entry.getNext();
        }
        table = newEntry;
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public boolean containsKey(Object key) {
        return true;
    }

    public boolean remove(Object key) {
        Entry result = indexFor(key);
        return result != null ? true : false;
    }

    private Entry indexFor(Object key) {
        int index = 0;
        if (key != null) {
            int hash = hash(key.hashCode());
            index = indexFor(hash, table.length - 1);
        }
        Entry entry = table[index];
        Entry result = null;

        if (entry != null) {
            if (key != null) {
                if (entry.getKey().equals(key) || entry.getKey() == key) {
                    result = entry;
                    table[index] = entry.getNext();
                    size--;
                    return result;
                }
                while (entry.getNext() != null) {
                    if (entry.getNext().getKey().equals(key) || entry.getNext().getKey() == key) {
                        break;
                    }
                    entry = entry.getNext();
                }
                if (entry.getNext() != null) {
                    Entry myNewEntry = entry.getNext().getNext();
                    entry.setNext(myNewEntry);
                    result = entry;
                    size--;
                    return result;
                }
            } else {
                if (entry.getKey() == null) {
                    table[index] = entry.getNext();
                    size--;
                    return entry;
                } else {
                    while (entry.getNext() != null) {
                        if (entry.getNext().getKey() == null) {
                            break;
                        }
                        entry = entry.getNext();
                    }
                    if (entry.getNext() != null) {
                        Entry myNewEntry = entry.getNext().getNext();
                        entry.setNext(myNewEntry);
                        result = entry;
                         size--;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();

        myHashMap.put("Pavel", 22);
        myHashMap.put("Sarah", 34);
        myHashMap.put("Gena", 11);

        System.out.println(myHashMap.get("Pavel"));
        System.out.println(myHashMap.get("Sarah"));
        System.out.println(myHashMap.get("Gena"));

    }
}