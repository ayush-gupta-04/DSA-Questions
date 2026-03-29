// TC -> N*logN
// SC -> N
// build a min-heap and a max-heap.
// -> We put the first half in the maxHeap.
// -> We put the next half in the minHeap.
// -> we maintain a size of | minHeapSize - maxHeapSize | <= 1
// 
// -> if minHeapSize == maxHeapSize ... then median = (minHeapTop + maxHeapTop)/2;
// -> else minHeapSize > maxHeapSize ... then median = (minHeapTop).
// ->      minHeapSize < maxHeapSize ... then median = (maxHeapTop).


class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>((x,y) -> Integer.compare(y,x));
    }
    
    public void addNum(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.offer(num);
        }
        else{
            minHeap.offer(num);
        }
        
        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.offer(minHeap.poll());
        }

    }
    
    public double findMedian() {
        if(minHeap.size() == maxHeap.size()){
            return (double)((double)minHeap.peek() + (double)maxHeap.peek())/2;
        }
        if(minHeap.size() > maxHeap.size()) return (double)minHeap.peek();
        return (double)maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
