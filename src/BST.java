import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size = 0;

    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    public class Entry{
        private K key;
        private V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
    }
    public int size(){
        return size;
    }

    public void put(K key, V val){
        Node newNode = new Node(key, val);
        if(root == null){
            root = newNode;
            size++;
            return;
        }
        Node current = root;
        Node parent = null;

        while(current != null){
            int cmp = key.compareTo(current.key);
            parent = current;
            if(cmp < 0){
                current = current.left;
            }
            if(cmp > 0){
                current = current.right;
            }
            else{
                current.val = val;
                return;
            }
        }
        int cmt = key.compareTo(parent.key);
        if(cmt < 0) {
            parent.left = newNode;
        } else{
            parent.right = newNode;
        }
        size++;
    }

    public V get(K key){
        Node current = root;
        while(current != null){
            int cmp = key.compareTo(current.key);
            if(cmp == 0){return current.val;}
            else if(cmp < 0){ current = current.left;}
            else{current = current.right;}
        }
        return null;
    }


    public void delete(K key){
        Node current = root;
        Node parent = null;

        while(current != null && !current.key.equals(key)){
            parent = current;
            if(key.compareTo(current.key) < 0){
                current = current.left;
            } else{
                current = current.right;
            }
            if(current == null){
                return;
            }

            if(current.left == null && current.right == null){
                if(current == root){
                    root = null;
                } else if(parent.left == current){
                    parent.left = null;
                } else{
                    parent.right = null;
                }
            }
            else if(current.left == null||current.right == null){
                Node child = (current.left != null)? current.left : current.right;
                if(current == root){
                    root = child;
                } else if(parent.left == current){
                    parent.left = child;
                } else{
                    parent.right = child;
                }
            }
            else{
                Node successorParent = current;
                Node successor = current.left;
                while(successor.left == current){
                    successorParent = successor;
                    successor = successor.right;
                }
                current.key = successor.key;
                current.val = successor.val;

                if(successorParent.left == successor){
                    successorParent.left = successor;
                } else{
                    successorParent.right = successor;
                }
            }
            size--;
        }
    }


    public Iterable<Entry> iterator() {
        return new Iterable<Entry>() {
            public java.util.Iterator<Entry> iterator() {
                return new java.util.Iterator<Entry>() {
                    private java.util.Stack<Node> stack = new java.util.Stack<>();
                    private Node current = root;

                    {
                        while (current != null) {
                            stack.push(current);
                            current = current.left;
                        }
                    }
                    public boolean hasNext(){
                        return !stack.isEmpty();
                    }

                    @Override
                    public Entry next() {
                        Node node = stack.pop();
                        Entry result = new Entry(node.key, node.val);

                        Node right = node.right;
                        while(right != null){
                            stack.push(right);
                            right = right.left;
                        }
                        return result;
                    }
                };
            }
        };
    }
}
