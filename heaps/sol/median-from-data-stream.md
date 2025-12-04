# 📈 Data Stream Median Finder (Two-Heap Approach - Java)

This solution efficiently calculates the median of a constantly growing stream of numbers using **Two Priority Queues (Heaps)**. This approach ensures that both **insertion** (`addNum`) and **median retrieval** (`findMedian`) operate in optimal time complexities: $O(\log n)$ and $O(1)$, respectively.

## Key Data Structures

1.  **`low` (Max-Heap)**: Stores the **smaller half** of the numbers. Since the Java `PriorityQueue` is a Min-Heap by default, we use `Collections.reverseOrder()` to make it behave as a Max-Heap. The root of `low` (`low.peek()`) is the **largest** number in the smaller half.
2.  **`high` (Min-Heap)**: Stores the **larger half** of the numbers. The root of `high` (`high.peek()`) is the **smallest** number in the larger half.

## Core Invariants

The two heaps are maintained to satisfy two critical invariants:

1.  **Order Invariant**: All elements in `low` must be less than or equal to all elements in `high`.
2.  **Size Invariant**: The sizes of the two heaps can differ by at most one: $|size(low) - size(high)| \le 1$.

## 💻 Java Code and Detailed Logic

### 1. `addNum(int num)`: Insertion and Rebalancing ($O(\log n)$)

The `addNum` method performs the following steps:

1.  **Initial Placement**: The number is placed into the appropriate heap based on its value relative to the current median boundary (`low.peek()`). Smaller numbers go to `low`; larger numbers go to `high`.
    ```java
    if(low.isEmpty() || num <= low.peek()){
        low.offer(num);
    }else{
        high.offer(num);
    }
    ```
2.  **Rebalancing**: After insertion, we check the size invariant. If one heap becomes too large (size difference $> 1$), the root element (the boundary element) is moved from the larger heap to the smaller heap to restore balance. This maintains the Order Invariant by moving the correct middle element.

### 2. `findMedian()`: Retrieval ($O(1)$)

Retrieving the median is an $O(1)$ operation by simply checking the top elements of the heaps:

* **Even Count** (`n + m` is even): The median is the average of the two middle elements, which are the roots of the two heaps.
    $$\text{Median} = \frac{\text{low.peek()} + \text{high.peek()}}{2.0}$$
* **Odd Count** (`n + m` is odd): The median is the single middle element, which will be the root of the larger heap (either `low` or `high`). The code prioritizes returning the root of the heap that is larger.

```java
import java.util.PriorityQueue;
import java.util.Collections;

class MedianFinder {
    
    // Max-Heap for the lower half
    PriorityQueue<Integer> low;
    // Min-Heap for the upper half
    PriorityQueue<Integer> high;

    public MedianFinder() {
        // low: Max-Heap (simulated by reverse order)
        this.low = new PriorityQueue<Integer>(Collections.reverseOrder());
        // high: Min-Heap (default behavior)
        this.high = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        // Step 1: Initial placement based on value (maintains Order Invariant locally)
        if(low.isEmpty() || num <= low.peek()){
            low.offer(num);
        }else{
            high.offer(num);
        }

        // Step 2: Rebalancing (maintains Size Invariant)
        if(low.size() - high.size() > 1){
            // Move largest element from low to high
            high.offer(low.poll());
        }else if(high.size() - low.size() > 1){
            // Move smallest element from high to low
            low.offer(high.poll());
        }
    }
    
    public double findMedian() {
        int n = low.size();
        int m = high.size();
        
        // Even count: Average of the two middle elements
        if((n + m) % 2 == 0){
            return (double)((double)(low.peek() + high.peek()))/2;
        }
        
        // Odd count: Median is the root of the larger heap
        if(n > m){
            return (double)low.peek();
        }
        return (double)high.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
