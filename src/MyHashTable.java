import java.util.Stack;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = Math.max(M, 1);
        chainArray = new HashNode[this.M];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        if((float) size / M > 0.75f) resize();
        size++;
        int hash = hash(key);
        HashNode<K, V> head = chainArray[hash];

        if(head == null) {
            chainArray[hash] = new HashNode<>(key, value);
            return;
        }

        while(head.next != null) {
            head = head.next;
        }
        head.next = new HashNode<>(key, value);
    }

    public V get(K key) {
        int hash = hash(key);
        HashNode<K, V> head = chainArray[hash];
        while(head != null) {
            if(head.key == key) return head.value;
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        size--;
        V value;
        int hash = hash(key);
        HashNode<K, V> head = chainArray[hash];

        if(head.key == key) {
            value = head.value;
            chainArray[hash] = head.next;
            return value;
        }

        if(head.next.key == key) {
            value = head.next.value;
            head.next = null;
            return value;
        }

        while(head.next.next != null) {
            if(head.next.key == key) {
                value = head.next.value;
                head.next = head.next.next;
                return value;
            }
            head = head.next;
        }

        if(head.next.key == key) {
            value = head.next.value;
            head.next = null;
            return value;
        }
        size++;
        return null;
    }

    public boolean contains(V value) {
        for(HashNode<K, V> head : chainArray) {
            while(head != null) {
                if(head.value.equals(value)) return true;
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for(HashNode<K, V> head : chainArray) {
            while(head != null) {
                if(head.value.equals(value)) return head.key;
                head = head.next;
            }
        }
        return null;
    }

    private void resize() {
        M = M * 2;
        Stack<HashNode<K, V>> nodes = new Stack<>();

        for(HashNode<K, V> head : chainArray) {
            while(head != null) {
                nodes.push(head);
                head = head.next;
            }
        }

        size = 0;
        chainArray = new HashNode[M];

        while(!nodes.isEmpty()) {
            HashNode<K, V> node = nodes.pop();
            put(node.key, node.value);
        }
    }

    public void printNumberOfElementsInEachBucket() {
        for(int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            int number = 0;
            while(head != null) {
                head = head.next;
                number++;
            }
            System.out.println(i + " bucket - " + number + " elements");
        }
    }
}
