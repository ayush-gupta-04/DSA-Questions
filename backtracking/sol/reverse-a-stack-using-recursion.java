import java.util.*;

public class Main {

    static void placeAtBottom(Deque<Integer> st, int ele) {
        if (st.isEmpty()) {
            st.offerLast(ele);
            return;
        }
        int a = st.pollLast();
        placeAtBottom(st, ele);
        st.offerLast(a);
    }

    static void reverse(Deque<Integer> st) {
        if (st.isEmpty()) {
            return;
        }

        int ele = st.pollLast();
        // reverse the remaining stack;
        reverse(st);
        // put the element to the bottom;
        placeAtBottom(st, ele);
    }

    public static void main(String[] args) {

        Deque<Integer> st = new ArrayDeque<>();

        st.offerLast(2);
        st.offerLast(1);
        st.offerLast(4);
        st.offerLast(3);
        st.offerLast(5);

        Deque<Integer> st1 = new ArrayDeque<>(st);

        reverse(st);

        System.out.println("Before Sorting !");

        while (!st1.isEmpty()) {
            System.out.print(st1.pollLast() + " ");
        }

        System.out.println();

        System.out.println("After Sorting !");

        while (!st.isEmpty()) {
            System.out.print(st.pollLast() + " ");
        }
    }
}
