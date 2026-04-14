### 🧠 Approach 1: Breadth-First Search (BFS) - The State-Space Explorer

**1. Core Intuition & Thought Process**
When a problem asks for the "shortest sequence" or "minimum steps" in an unweighted scenario, your brain should immediately yell: **Breadth-First Search (BFS)!** Imagine the car's journey as a massive, infinite graph. Every node in this graph is a specific state defined by two variables: `(position, speed)`. From any node, you have exactly two edges (choices):
* **Edge 1 (Accelerate):** Transition to `(position + speed, speed * 2)`
* **Edge 2 (Reverse):** Transition to `(position, speed > 0 ? -1 : 1)`

BFS guarantees that the first time we land on a node where `position == target`, we have found the absolute shortest path.

**2. Why does the pure BFS fail or struggle? (TLE / MLE Reasons)**
* **Infinite State Space:** The number line is infinite. If we don't put boundaries on our BFS, the car could accelerate infinitely away from the target, flooding our queue and memory (MLE) and timing out (TLE).
* **Common Pitfall (The Fix):** We must prune the search space. A mathematical observation shows that you should never travel further than `2 * target` in either direction. If you cross `target * 2`, it's strictly mathematically worse than just reversing earlier.
* **String Hashing Overhead:** Storing visited states as strings like `"pos_speed"` in a HashSet is computationally heavy and can cause TLE in strict environments. 

**3. Step-by-Step Algorithm**
* Initialize a Queue holding arrays of `[position, speed, steps]`.
* Initialize a Visited Set to avoid cycles.
* Push the starting state `[0, 1, 0]` into the Queue.
* While the Queue is not empty:
    * Pop the current state. If `position == target`, return `steps`.
    * Calculate the 'Accelerate' state. If it falls within our reasonable bounds (`0 < next_pos < 2 * target`) and is unvisited, push it to the queue.
    * Calculate the 'Reverse' state. If unvisited, push it to the queue.

**4. Dry Run on Example 1 (`target = 3`)**
* Start: `(0, 1)`. Steps: 0.
* Pop `(0, 1)`:
    * 'A' -> `(1, 2)`. Steps: 1.
    * 'R' -> `(0, -1)`. Steps: 1.
* Pop `(1, 2)`:
    * 'A' -> `(3, 4)`. Steps: 2. -> **TARGET REACHED!**
* *Shortest sequence is "AA", length 2.*

**5. BFS Code**
```java
import java.util.*;

class Solution {
    public int racecar(int t) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> v = new HashSet<>();
        
        q.offer(new int[]{0, 1, 0});
        v.add("0_1");
        
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int p = c[0];
            int s = c[1];
            int d = c[2];
            
            if (p == t) return d;
            
            int np = p + s;
            int ns = s * 2;
            String k1 = np + "_" + ns;
            
            if (np > 0 && np < (t * 2) && !v.contains(k1)) {
                q.offer(new int[]{np, ns, d + 1});
                v.add(k1);
            }
            
            ns = s > 0 ? -1 : 1;
            String k2 = p + "_" + ns;
            
            if (!v.contains(k2)) {
                q.offer(new int[]{p, ns, d + 1});
                v.add(k2);
            }
        }
        return -1;
    }
}
```

**6. Complexity**
* **Time:** $O(T \log T)$. The bounds limit positions to $2T$. Speeds are powers of 2, so there are $\log T$ possible speeds at any position.
* **Space:** $O(T \log T)$ to store the states in the Queue and Visited Set. 

---

### 🏆 Approach 2: Dynamic Programming (The OPTIMAL Solution)

**1. Core Intuition & Thought Process**
Notice a repeating pattern: moving from `position 0` to `position X` requires the exact same logic as moving from `position Y` to `position Y + X` assuming starting speed is $1$. This structural repetition screams **Dynamic Programming (DP)**.

How do we reach a target distance $i$? We accelerate continuously. The distance covered after $n$ 'A' instructions is exactly $2^n - 1$. 
There are three possibilities when trying to reach distance $i$:
1.  **Exact Hit:** $2^n - 1 = i$. We perfectly land on the target. Cost = $n$ instructions.
2.  **Overshoot & Reverse:** We accelerate $n$ times until we *just pass* $i$. We are at $2^n - 1$. We hit 'R' (1 step), and now we need to travel backwards by a distance of $(2^n - 1) - i$.
3.  **Undershoot & Reverse:** We accelerate $n-1$ times, landing *just before* $i$ at $2^{n-1} - 1$. If we keep going, we overshoot. So we hit 'R' (1 step), go backwards $m$ times (costing $m$ steps and covering $2^m - 1$ distance), hit 'R' again to face forward (1 step), and then travel the remaining distance to $i$.

**2. Why is this better than BFS?**
Instead of blindly traversing every possible state and storing heavy objects in memory, DP solves the problem via mathematical subproblems. It drops the massive space overhead entirely and solves the target extremely fast.

**3. Step-by-Step Algorithm**
* Create a DP array of size `target + 1`. `dp[i]` will store the minimum steps to reach distance `i`.
* Iterate `i` from 1 to `target`.
* Find the smallest integer `n` such that $2^n - 1 \ge i$.
* If $2^n - 1 == i$, `dp[i] = n`.
* Otherwise, try the **Overshoot** strategy: cost is `n + 1 + dp[(2^n - 1) - i]`.
* Also try all possible **Undershoot** strategies (loop `m` from 0 to $n-1$): cost is `(n - 1) + 1 + m + 1 + dp[i - (distance_covered_so_far)]`.
* Take the minimum of all these valid strategies.

**4. Dry Run on Target 6 (`i = 6`)**
* Target is 6. `n = 3` because $2^3 - 1 = 7$ (which is $\ge 6$).
* *Overshoot:* Go 3 steps to 7. Reverse. Distance left is $7 - 6 = 1$. Total = $3 (\text{A}) + 1 (\text{R}) + dp[1] (1) = 5$.
* *Undershoot:* Go 2 steps to 3 ($n-1$). Reverse. Go back $m=1$ steps (distance 1). Reverse. Distance left = $6 - (3 - 1) = 4$. Total = $2 (\text{A}) + 1 (\text{R}) + 1 (\text{A}) + 1 (\text{R}) + dp[4]$.
* `dp[6]` records the minimum, which is 5.

**5. Optimal DP Code**
```java
class Solution {
    public int racecar(int t) {
        int[] dp = new int[t + 1];
        
        for (int i = 1; i <= t; i++) {
            int n = 1;
            while ((1 << n) - 1 < i) {
                n++;
            }
            
            if ((1 << n) - 1 == i) {
                dp[i] = n;
                continue;
            }
            
            dp[i] = n + 1 + dp[(1 << n) - 1 - i];
            
            for (int m = 0; m < n - 1; m++) {
                int p = (1 << (n - 1)) - 1 - ((1 << m) - 1);
                dp[i] = Math.min(dp[i], n + m + 1 + dp[i - p]);
            }
        }
        
        return dp[t];
    }
}
```

**6. Complexity**
* **Time:** $O(T \log^2 T)$. The outer loop runs $T$ times. The inner `while` loop takes $\log T$ time. The inner `for` loop runs up to $n$ times, where $n \approx \log T$.
* **Space:** $O(T)$ to store the DP array. Extremely memory efficient.

---

### 🧩 The Key Pattern: "State-Space BFS to 1D DP Reduction"

This problem belongs to a beautiful class of problems where an intuitive Graph/BFS solution can be mathematically flattened into a **1D Dynamic Programming** approach by observing that state transitions scale predictably (in this case, via powers of 2).

Whenever you see a problem asking for the "shortest path/minimum operations" where the operations involve geometric progression (multiplying by 2) combined with reversing/subtracting, look for DP bounds.

**Similar Problems to Practice This Pattern:**
1.  **LeetCode 397: Integer Replacement** (Bitwise DP / Math BFS)
2.  **LeetCode 991: Broken Calculator** (Work backward using Math/Greedy)
3.  **LeetCode 1345: Jump Game IV** (BFS state pruning)
4.  **LeetCode 322: Coin Change** (The foundational 1D DP target reduction)
