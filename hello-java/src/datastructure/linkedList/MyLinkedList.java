package datastructure.linkedList;

public class MyLinkedList {

    private MyNode head; // 첫번째 노드
    int count;

    public MyLinkedList() {
        head = null;
        count = 0;
    }

    public MyNode addElement(String data) {
        MyNode newNode;

        if (head == null) {
            newNode = new MyNode(data);
            head = newNode;
        } else {
            newNode = new MyNode(data);
            MyNode temp = head;
            while (temp.next != null)  //맨 뒤로 가서
                temp = temp.next;
            temp.next = newNode;
        }
        count++;
        return newNode;
    }

    // 중간에 들어가는 경우
    public MyNode insertElement(int position, String data) {
        int i;
        MyNode tempNode = head;
        MyNode preNode = null;

        MyNode newNode = new MyNode(data);

        if (position < 0 || position > count) {
            // 넣을 수 없는 위치인 경우
            return null;
        }

        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            for (i = 0; i < position; i++) {
                preNode = tempNode;
                tempNode = tempNode.next;

            }
            newNode.next = preNode.next;
            preNode.next = newNode;
        }

        count++;
        return newNode;
    }

    public MyNode removeElement(int position) {
        int i;
        MyNode tempNode = head;

        if (position >= count) {
            System.out.println("삭제 할 위치 오류입니다. 현재 리스트의 개수는 " + count + "개 입니다.");
            return null;
        }

        if (position == 0) {  //맨 앞을 삭제하는
            head = tempNode.next;
        } else {
            MyNode preNode = null;
            for (i = 0; i < position; i++) {
                preNode = tempNode;
                tempNode = tempNode.next;
            }
            preNode.next = tempNode.next;
        }
        count--;
        System.out.println(position + "번째 항목 삭제되었습니다.");

        return tempNode;
    }

    public String getElement(int position) {
        int i;
        MyNode tempNode = head;

        if (position >= count) {
            System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count + "개 입니다.");
            return new String("error");
        }

        if (position == 0) {  //맨 인 경우

            return head.getData();
        }

        for (i = 0; i < position; i++) {
            tempNode = tempNode.next;

        }
        return tempNode.getData();
    }

    public MyNode getNode(int position) {
        int i;
        MyNode tempNode = head;

        if (position >= count) {
            System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count + "개 입니다.");
            return null;
        }

        if (position == 0) {  //맨 인 경우

            return head;
        }

        for (i = 0; i < position; i++) {
            tempNode = tempNode.next;

        }
        return tempNode;
    }

    public void removeAll() {
        head = null;
        count = 0;

    }

    public int getSize() {
        return count;
    }

    public void printAll() {
        if (count == 0) {
            System.out.println("출력할 내용이 없습니다.");
            return;
        }

        MyNode temp = head;
        while (temp != null) {
            System.out.print(temp.getData());
            temp = temp.next;
            if (temp != null) {
                System.out.print("->");
            }
        }
        System.out.println("");
    }

    public boolean isEmpty() {
        if (head == null) return true;
        else return false;
    }
}
