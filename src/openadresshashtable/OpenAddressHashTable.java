/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openadresshashtable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
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

    final Pair<Tkey, Tval>[] array;

    public OpenAddressHashTable(int n) {
        array = new Pair[n];
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (array[hash] == null || array[hash].isDeleted() || array[hash].getKey() == key) {
            array[hash] = new Pair(key, value);
            return value;
        }

        for (int i = hash + 1; i != hash; i = (i + 1) % array.length) {
            if (array[hash] == null || array[i].isDeleted() || array[i].getKey() == key) {
                array[i] = new Pair(key, value);
                return value;
            }
        }

        return value;
    }

    @Override
    public Tval remove(Object key) {
        final int hash = Math.abs(key.hashCode()) % array.length;
        if (array[hash] == null || array[hash].isDeleted() || array[hash].getKey() == key) {
            array[hash].deleted = true;
            return array[hash].val;
        }

        for (int i = hash + 1; i != hash; i = (i + 1) % array.length) {
            if (array[hash] == null || array[i].isDeleted() || array[i].getKey() == key) {
                array[hash].deleted = true;
                return array[hash].val;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends Tkey, ? extends Tval> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Tkey> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Tval> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Tkey, Tval>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
