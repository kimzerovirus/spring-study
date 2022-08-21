package datastructure.queue;

import datastructure.linkedList.MyLinkedList;
import datastructure.linkedList.MyNode;

public class MyQueue extends MyLinkedList implements IQueue{

    MyNode front;
    MyNode rear;


    public MyQueue()
    {
        front = null;
        rear = null;
    }

    public void enQueue(String data)
    {
        MyNode newNode;
        if(isEmpty())  //처음 항목
        {
            newNode = addElement(data);
            front = newNode;
            rear = newNode;
        }
        else
        {
            newNode = addElement(data);
            rear = newNode;
        }
        System.out.println(newNode.getData() + " added");
    }

    public String deQueue()
    {
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return null;
        }
        String data = front.getData();
        front = front.next;
        if( front == null ){  // 마지막 항목
            rear = null;
        }
        return data;
    }

    public void printAll()
    {
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return;
        }
        MyNode temp = front;
        while(temp!= null){
            System.out.print(temp.getData() + ",");
            temp = temp.next;
        }
        System.out.println();
    }
}