package datastructure.linkedList;

public class MyNode {

    private String data; // 데이터
    public MyNode next; // 다음 노드를 가르키는 링크

    public MyNode() {
        this.data = null;
        this.next = null;
    }

    public MyNode(String data) {
        this.data = data;
        this.next = null;
    }

    public MyNode(String data, MyNode next) {
        this.data = data;
        this.next = next;
    }

    public String getData() {
        return data;
    }

}
