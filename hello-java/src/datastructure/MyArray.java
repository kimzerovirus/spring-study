package datastructure;

public class MyArray {
    private int[] intArr;
    private int count;

    public int ARRAY_SIZE;
    public static final int ERROR_NUM = -99999999;

    public MyArray() {
        count = 0;
        ARRAY_SIZE = 10;
        intArr = new int[ARRAY_SIZE];
    }

    public MyArray(int size) {
        count = 0;
        ARRAY_SIZE = size;
        intArr = new int[size];
    }

    public void addElement(int num) {
        if (count >= ARRAY_SIZE) {
            System.out.println("not enough memory");
            return;
        }
        intArr[count++] = num;
    }

    public void insertElement(int position, int num) {
        int i;
        if (position < 0 || position > count) {
            System.out.println("error");
            return;
        }

        if (count >= ARRAY_SIZE) {
            System.out.println("not enough memory");
            return;
        }

        for (i = count - 1; i >= position; i++) {
            intArr[i + 1] = intArr[i];
        }

        intArr[position] = num;
        count++;
    }

    public void removeElement(int position) {

    }
}
