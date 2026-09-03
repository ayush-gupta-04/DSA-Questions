The restriction "no more than two consecutive posts have the same color" means a color streak can only have a length of **1** or **2**. A streak of **3** is invalid.

## Intuition from Scratch

### Step 1: Why the Exact Colors Do Not Matter

If you have 3 posts and 2 colors (Red, Blue):

* Painting post 1 **Red** leaves the exact same number of future valid choices as painting post 1 **Blue**.
* The colors are completely interchangeable (symmetric).

Therefore, tracking *which* color was painted is unnecessary. You only need to track the **relationship between the current post and the previous post**:

1. Does post $i$ have the **same** color as post $i - 1$?
2. Does post $i$ have a **different** color from post $i - 1$?



### Step 2: Breaking Down Post $i$

Let:

* $same[i]$ = number of valid ways to paint $i$ posts such that post $i$ and post $i - 1$ have the **same** color.
* $diff[i]$ = number of valid ways to paint $i$ posts such that post $i$ and post $i - 1$ have **different** colors.
* $total[i]$ = total valid ways to paint $i$ posts ($total[i] = same[i] + diff[i]$).

Now examine what happens when placing post $i$:

#### Case A: Post $i$ has the same color as Post $i - 1$

* If post $i$ has the same color as post $i - 1$, then post $i - 1$ **cannot** have the same color as post $i - 2$. If it did, posts $i - 2$, $i - 1$, and $i$ would all share the same color (3 in a row), violating the rule.
* This forces post $i - 1$ and post $i - 2$ to have **different** colors.
* The number of valid ways where post $i - 1$ differed from post $i - 2$ is $diff[i - 1]$.
* Post $i$ must pick the exact same color as post $i - 1$ (only **1** choice).

$$same[i] = diff[i - 1] \times 1 = diff[i - 1]$$

#### Case B: Post $i$ has a different color from Post $i - 1$

* If post $i$ is painted a different color from post $i - 1$, it resets the streak to 1.
* It does not matter whether post $i - 1$ matched post $i - 2$ or not. A streak of 3 cannot occur here because post $i$ changes color.
* All valid configurations of length $i - 1$ are eligible: $total[i - 1] = same[i - 1] + diff[i - 1]$.
* Post $i$ can choose any of the available colors except the one used on post $i - 1$, giving $(k - 1)$ choices.

$$diff[i] = total[i - 1] \times (k - 1)$$

---

### Step 3: Deriving the Unified Formula

Combining both cases gives the total configurations for $i$ posts:

$$total[i] = same[i] + diff[i]$$

$$total[i] = diff[i - 1] + total[i - 1] \times (k - 1)$$

From Case B applied to post $i - 1$, we know:


$$diff[i - 1] = total[i - 2] \times (k - 1)$$

Substitute this into the total equation:


$$total[i] = total[i - 2] \times (k - 1) + total[i - 1] \times (k - 1)$$

$$total[i] = (total[i - 1] + total[i - 2]) \times (k - 1)$$

This is a recurrence relation identical to Fibonacci, scaled by $(k - 1)$.

---

### Step 4: Establishing Base Cases

* **For $n = 1$:**
Only one post exists. You can pick any of the $k$ colors.

$$total[1] = k$$


* **For $n = 2$:**
Post 1 has $k$ choices. Post 2 can either match post 1 ($k \times 1$) or differ ($k \times (k - 1)$).

$$total[2] = k + k(k - 1) = k^2$$



---

## Top-Down Implementation (Memoization)
* **Time Complexity:** $O(n)$ — each state from $1$ to $n$ is evaluated exactly once.
* **Space Complexity:** $O(n)$ — $O(n)$ space for the `memo` array plus $O(n)$ recursion call stack frames.

```java
class Solution {
    int solve(int n, int k,int[] dp){
        if(n==1) return k;
        if(n==2) return k*k;
        
        if(dp[n] != -1) return dp[n];
        
        return dp[n] = (solve(n-1,k, dp) + solve(n-2, k, dp)) * (k-1);
    }
    public int countWays(int n, int k) {
        int[] dp = new int[n+1];
        Arrays.fill(dp , -1);
        return solve(n,k, dp);
    }
}

```

---

## Bottom-Up Implementation (Tabulation)
* **Time Complexity:** $O(n)$ — a simple loop from $3$ to $n$.
* **Space Complexity:** $O(n)$ — storing the `dp` array.

```java
class Solution {
    public int countWays(int N, int K) {
        if(N==1) return K;
        if(N==2) return K*K;
        
        int[] dp = new int[N+1];
        dp[1] = K;
        dp[2] = K*K;
        for(int n = 3; n <= N; n++){
            dp[n] = (dp[n-1] + dp[n-2]) * (K-1);
        }
        return dp[N];
    }
}

```

---

## Space Optimization to $O(1)$

* **Time Complexity:** $O(n)$
* **Space Complexity:** $O(1)$

```java
class Solution {
    public int countWays(int N, int K) {
        if(N==1) return K;
        if(N==2) return K*K;
        
        int a = K;
        int b = K*K;
        for(int n = 3; n <= N; n++){
            int c = (a + b) * (K-1);
            a = b;
            b = c;
        }
        return b;
    }
}

```
