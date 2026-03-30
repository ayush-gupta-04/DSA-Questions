Let's break down **Ugly Number II** step-by-step.

### 🧠 1. Intuition and Thought Process

What exactly is an ugly number? It's a number whose only prime factors are 2, 3, and 5. 
* This means if you have an ugly number $X$, then $X \times 2$, $X \times 3$, and $X \times 5$ will **also** be ugly numbers.
* The sequence starts like this: $1, 2, 3, 4, 5, 6, 8, 9, 10, 12...$

**The Core Realization:** We don't need to check non-ugly numbers. We can just *generate* the ugly numbers from the ones we already have! It's like a factory where the outputs (ugly numbers) are fed right back into the machines (multipliers of 2, 3, and 5) to create the next batch of outputs.

### 🐢 2. The Brute Force Approach (The "I Hope This Doesn't TLE" Method)

Before doing anything clever, how would we solve this if constraints didn't matter?

* Start checking numbers one by one: 1, 2, 3, 4, 5...
* To check if a number $k$ is ugly, continuously divide it by 2 while it's divisible by 2. Do the same for 3 and 5.
* If the final result is 1, it's an ugly number! Keep a counter.
* Stop when your counter hits $n$.

**Why this is terrible:** The $n$-th ugly number grows exponentially. The 1690th ugly number is $2123366400$. You would have to iterate over two billion numbers, performing modulo operations on each. You will definitely get a **Time Limit Exceeded (TLE)**! 🛑

### ⚡ 3. The Optimal Solution (The "Codeforces" Way)

We need an $O(n)$ solution. Instead of checking every integer, we will **only generate the ugly numbers** in strictly increasing order.

**Standard Pattern Used:** **Dynamic Programming with Multiple Pointers** (also heavily related to the *Merge process in Merge Sort*).

Think of it as having three separate queues generating ugly numbers:
1.  **Queue 2:** $1 \times 2, 2 \times 2, 3 \times 2, 4 \times 2, 5 \times 2...$
2.  **Queue 3:** $1 \times 3, 2 \times 3, 3 \times 3, 4 \times 3, 5 \times 3...$
3.  **Queue 5:** $1 \times 5, 2 \times 5, 3 \times 5, 4 \times 5, 5 \times 5...$

Every new ugly number is just the minimum of the front elements of these three queues.
* We can use an array `dp` to store the ugly numbers we've generated so far.
* We use three pointers (`p2`, `p3`, `p5`) to point to the index in the `dp` array that hasn't been multiplied by 2, 3, or 5 yet.
* If `dp[p2] * 2` is chosen as the next smallest ugly number, we move pointer `p2` forward by 1.
* **Crucial detail:** What if two queues generate the same number? For example, $2 \times 3 = 6$ and $3 \times 2 = 6$. Simple! We just advance *both* pointers to avoid duplicates.

### 📝 4. Algorithm (Pseudo-code)

Here is the step-by-step battle plan:
* Create an array `dp` of size $n$, where `dp[i]` will store the $(i+1)$-th ugly number.
* Initialize `dp[0] = 1` (the first ugly number).
* Initialize three pointers: `p2 = 0`, `p3 = 0`, `p5 = 0`.
* Loop $i$ from 1 to $n-1$:
    * Calculate the next possible candidates: 
        * `next_2 = dp[p2] * 2`
        * `next_3 = dp[p3] * 3`
        * `next_5 = dp[p5] * 5`
    * Find the minimum of these three candidates. Assign it to `dp[i]`.
    * If `dp[i]` equals `next_2`, increment `p2`.
    * If `dp[i]` equals `next_3`, increment `p3`.
    * If `dp[i]` equals `next_5`, increment `p5`.
* Return the last element, `dp[n-1]`.

### 💻 5. Clean Java Code

Here is the production-ready, highly optimal Java code.

```java
class Solution {
    public int nthUglyNumber(int n) {
        // Base case: if n is 1, the answer is 1
        if (n <= 0) return 0;
        if (n == 1) return 1;

        // dp array to store the first n ugly numbers
        int[] dp = new int[n];
        dp[0] = 1; // The first ugly number is conventionally 1

        // Pointers for multiples of 2, 3, and 5
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        // Populate the dp array
        for (int i = 1; i < n; i++) {
            // Calculate the next potential ugly numbers
            int next2 = dp[p2] * 2;
            int next3 = dp[p3] * 3;
            int next5 = dp[p5] * 5;

            // The next ugly number is the minimum of the three
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            dp[i] = nextUgly;

            // Increment the pointers that contributed to this minimum.
            // Notice we use separate 'if' statements, NOT 'else if'.
            // This elegantly handles duplicates (e.g., 2*3 == 3*2 == 6).
            if (nextUgly == next2) p2++;
            if (nextUgly == next3) p3++;
            if (nextUgly == next5) p5++;
        }

        // Return the n-th ugly number (0-indexed)
        return dp[n - 1];
    }
}
```

### ⏱️ Complexity Analysis
* **Time Complexity:** $\mathcal{O}(N)$. We run a single loop $n$ times. Finding the minimum of 3 numbers is $\mathcal{O}(1)$. This is lightning fast! ⚡
* **Space Complexity:** $\mathcal{O}(N)$. We use an array of size $n$ to store our generated sequence.

Does the pointer logic make sense to you, or would you like to trace through an example like finding the 5th ugly number by hand?
