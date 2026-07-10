// A very simple problem


// we will keep 2 HashMap.
// freqSt<Integer,Deque<Integer>> : 
// - frequency -> [a stack]
// - all the numbers having same freq will be stored in a stack.
// - it will make sure that we will pop in lifo manner from max freq stack.

// freqMap <Integer, Integer> : 
// - it stores freq of numbers.
// - if a numbers comes, we can easily see it's old freq so we can put it in the higher freq stack.

// maxFreq : to know what is the maxfreq now.


class FreqStack {
    HashMap<Integer,Deque<Integer>> freqSt;
    HashMap<Integer,Integer> freqMap;
    int maxFreq;
    public FreqStack() {
        this.freqSt = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.maxFreq = 0;
    }
    
    public void push(int val) {
        // get the old freq.
        // put the number to the higher freq st.

        int freq = freqMap.getOrDefault(val, 0);
        freqSt.computeIfAbsent(freq + 1, k -> new ArrayDeque<>()).offerLast(val);
        freqMap.put(val , freq + 1);

        if(freq + 1 > this.maxFreq) this.maxFreq = freq + 1;
        return;
    }
    
    public int pop() {
        // get the max freq stack from freqSt.
        // poll the top element.
        
        Deque<Integer> d = freqSt.get(this.maxFreq);
        int num = d.pollLast();
        freqMap.put(num , freqMap.get(num)-1);
        if(freqMap.get(num)==0) freqMap.remove(num);

        if(d.isEmpty()){
            freqSt.remove(this.maxFreq);
            this.maxFreq--;
        }
        return num;
    }
}
