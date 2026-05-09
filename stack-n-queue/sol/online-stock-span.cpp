// We will maintain a monotonically DEC stack.
// If any element pops out from the stack .. then i must have the knowledge of the index of those elements.
// If the indexs are known then we can easily cacl the number of element in between.
// We will store {num , index} in the stack.


class StockSpanner {
    private Deque<int[]> stack;
    private int index;

    public StockSpanner() {
        stack = new ArrayDeque<>();
        index = 0;
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peekLast()[0] <= price) {
            stack.pollLast();
        }

        int span = stack.isEmpty() ? index + 1 : index - stack.peekLast()[1];

        stack.offerLast(new int[]{price, index});
        index++;

        return span;
    }
}
