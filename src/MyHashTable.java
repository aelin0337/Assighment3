public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value, HashNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "{" + key + ": " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int size;
    private int capacity = 11;

    public MyHashTable() {
        this.capacity = 11;
        this.chainArray = new HashNode[capacity];
        this.size = 0;
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.chainArray = new HashNode[capacity];
        this.size = 0;
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }
    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        HashNode<K, V> current = head;
        while(current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            current = current.next;
        }
        HashNode<K, V> newNode = new HashNode<>(key, value, head);
        chainArray[index] = newNode;
        size++;
    }
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while (current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> prev = null;

        while(current != null){
            if(current.key.equals(key)){
                if(prev == null){
                    chainArray[index] = current.next;
                }
                else{
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    public boolean contains(K key){
        for(int i = 0; i< capacity; i++){
            HashNode<K, V> current = chainArray[i];
            while(current != null){
                if(current.key.equals(key)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public K getKey(V value){
        for(int i = 0; i< capacity; i++){
            HashNode<K, V> current = chainArray[i];
            while(current != null){
                if(current.value.equals(value)){
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
}