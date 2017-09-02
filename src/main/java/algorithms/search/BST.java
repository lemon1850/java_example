package algorithms.search;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by tianhe on 2017/7/26.
 */
public class BST<Key extends Comparable<Key>, Value>{

    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int N; //以该点为根的的子树的节点数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size(){
        return size(root);
    }
    public int size(Node x){
        if(x==null) {
            return 0;
        }
        return x.N;
    }
    public Value get(Key key){return  get(root, key);}

    private Value get(Node root, Key key) {
        if(root==null){
            return null;
        }
        int cmp = key.compareTo(root.key);
        if(cmp==0){
            return root.value;
        }else if(cmp>0){
            return get(root.right, key);
        }else {
            return get(root.left, key);
        }
    }

    public void put(Key key, Value value){
        put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if(root==null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(root.key);
        if(cmp>0){
            root.right = put(root.right, key, value);
        }else if(cmp<0){
            root.left = put(root.left, key, value);
        }else{
            root.value = value;
        }
        root.N = size(root.left)+ size(root.right)+ 1;
        return root;
    }
    public Key min(){
        return min(root).key;
    }
    public Key max(){
        return null;
    }
    private Node min(Node node){
//        if(node==null){
//            return null;
//        }
        if(node.left==null) {
            return node;
        }else{
            return min(node.left);
        }
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if(x==null) return null;
        return x.key;
    }

    private Node floor(Node root, Key key) {
        if(root==null)return null;
        int cmp = key.compareTo(root.key);
        if(cmp==0) return root;
        if(cmp<0) return floor(root.left, key);

        Node t = floor(root.right, key);
        if(t != null) return t;
        else return root;
    }

    public Key select(int k){
        return select(root, k).key;
    }

    private Node select(Node root, int k) {
        if(root==null) return null;
        int t = size(root.left);
        if(t==k)return root;
        if(t>k) return select(root.left, k);
        //cmp>0
        return select(root.right, k-t-1);
    }

    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node root, Key key) {
        if(root==null) return 0;
        int cmp = key.compareTo(root.key);
        if(cmp>0) return size(root.left) + 1 + rank(root.right, key);
        if(cmp<0) return rank(root.left, key);
        return size(root.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node root) {
            if(root.left==null)return root.right;

            root.left = deleteMin(root.left);
            root.N = size(root.left) + 1 + size(root.right);
            return root;

    }
    
    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if(root==null)return null;
        int comp = key.compareTo(root.key);
        if(comp>0) root.right = delete(root.right, key);
        if(comp<0) root.left = delete(root.left, key);
        if(comp==0){

            if(root.left==null) return root.right;
            if(root.right==null) return root.left;

            //删除两个节点
            Node t = root;
            root = min(t.right);
            root.right = deleteMin(root.right);
            root.left = t.left;
        }
        root.N = size(root.left) + 1 + size(root.right);
        return root;

    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new SynchronousQueue<Key>() {
        };
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        //中序遍历
        if(root==null) return;
        int complo = lo.compareTo(root.key);
        int comphi = hi.compareTo(root.key);
        if(complo<0) keys(root.left, queue, lo, hi);
        if(complo<=0 && comphi>=0) queue.add(root.key);
        if(comphi>0) keys(root.left, queue, lo, hi);
    }

}
