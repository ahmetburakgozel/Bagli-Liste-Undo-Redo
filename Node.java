package undoredo;

public class Node {
    public String data;
    public Node next, pre;
    public Node(String s, Node n, Node p){
        next = n;
        pre = p;
        data = s;
    }
}
