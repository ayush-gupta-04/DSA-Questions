// -------------- Better --------------
// Time : O(1)
// spcae : o(2N)

class MinStack {
    Deque<int[]> stack;

    public MinStack() {
        this.stack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        if(stack.isEmpty()){
            stack.offerLast(new int[]{value, value});
            return;
        }
        stack.offerLast(new int[]{value, Math.min(value, stack.peekLast()[1])});
    }
    
    public void pop() {
        stack.pollLast();
    }
    
    public int top() {
        return stack.peekLast()[0];
    }
    
    public int getMin() {
        return stack.peekLast()[1];
    }
}



// -------------- Optimal --------------
// time : O(1)
// space : best -> O(N+1)
//         worst -> O(2*N)




import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    private Deque<Integer> stack;
    private int min;

    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        // If the new value is <= min, preserve the old min underneath it
        if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        // If we pop the current minimum, the element right below it is the previous minimum
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
