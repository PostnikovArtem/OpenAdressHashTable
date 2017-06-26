/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openadresshashtable;

import java.util.*;

/**
 * @author Admin
 */
public class OpenAddressHashTable<Tkey, Tval> implements Map<Tkey, Tval> {

    static class Pair<Tkey, Tval> {

        Tkey key;
        Tval val;
        boolean deleted;

        public Pair() {
            deleted = true;
        }

        public Pair(Tkey key, Tval val) {
            this.key = key;
            this.val = val;
            deleted = false;
        }

        public Tkey getKey() {
            return key;
        }

        public Tval getVal() {
            return val;
        }

        public boolean isDeleted() {
            return deleted;
        }
    }

    private final Pair<Tkey, Tval>[] array;

    private int size = 0;

    public OpenAddressHashTable(int n) {
        array = new Pair[n];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        final int hash = Math.abs(key.hashCode()) % array.length;

        for (int i = 0; i < array.length; i++) {
            int indexedHash = (hash + i) % array.length;
            if (array[indexedHash] != null && !array[indexedHash].isDeleted() && array[indexedHash].getKey() == key) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].getVal() == value) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Tval get(Object key) {
        final int hash = Math.abs(key.hashCode()) % array.length;
        if (array[hash] != null && !array[hash].isDeleted() && array[hash].getKey() == key) {
            return array[hash].val;
        }

        for (int i = hash + 1; i != hash; i = (i + 1) % array.length) {
            if (array[hash] != null && !array[hash].isDeleted() && array[hash].getKey() == key) {
                return array[hash].val;
            }
        }
        return null;
    }

    @Override
    public Tval put(Tkey key, Tval value) {

        final int hash = Math.abs(key.hashCode()) % array.length;

        for (int i = 0; i < array.length; i++) {
            int indexedHash = (hash + i) % array.length;
            if (array[indexedHash] == null || array[indexedHash].isDeleted() || array[indexedHash].getKey() == key) {
                size++;
                if (size > array.length) {
                    size = array.length;
                }

                array[indexedHash] = new Pair(key, value);
                return value;

            }
        }

        return value;

    }

    @Override
    public Tval remove(Object key) {
        final int hash = Math.abs(key.hashCode()) % array.length;

        for (int i = 0; i < array.length; i++) {
            int indexedHash = (hash + i) % array.length;
            if (array[indexedHash] != null && !array[indexedHash].isDeleted() && array[indexedHash].getKey() == key) {

                size--;
                array[indexedHash].deleted = true;
                return array[indexedHash].getVal();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Tkey, ? extends Tval> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                array[i].deleted = true;
            }
        }

        size = 0;
    }

    @Override
    public Set<Tkey> keySet() {
        Set<Tkey> res = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                res.add(array[i].getKey());
            }
        }
        return res;
    }

    @Override
    public Collection<Tval> values() {
        List<Tval> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                res.add(array[i].getVal());
            }
        }
        return res;
    }

    @Override
    public Set<Map.Entry<Tkey, Tval>> entrySet() {
        Set<Map.Entry<Tkey, Tval>> res = new HashSet();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                res.add(new AbstractMap.SimpleEntry<>(array[i].getKey(), array[i].getVal()));
            }
        }

        return res;
    }

}
