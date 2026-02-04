# 🐴 Knight Probability in Chessboard — DP Notes

---

## 🔍 Key Observation

The probability that the knight **remains on the board after `k` moves** is:

> 👉 the **sum of probabilities** of the knight being in **every cell** after `k` moves.

So instead of tracking paths, we track **probabilities per cell**.

---

## 🎯 State Definition (DP)

We only need **three parameters** to describe the state:

* `moves` → how many moves made so far
* `i, j` → current cell on the board

### DP State

```
dp[moves][i][j] = Probability that the knight is at cell (i, j) after exactly `moves` moves
```

---

## 🧱 Base Case

When no moves are made:

```
dp[0][row][column] = 1

```

* The knight **starts at (row, column)** with probability 1
* All other cells have probability 0
 
 ---

## 🔄 DP Computation Order
![Image](https://assets.leetcode.com/static_assets/media/original_images/688/688_dp_example.drawio.png)

We compute DP **layer by layer**:

```
dp[0] → dp[1] → dp[2] → ... → dp[k]

```

## 🔁 DP Transition

To compute `dp[moves][i][j]`:

* Look at all cells `(ni, nj)` from which a knight can jump **to `(i, j)`**
* Each contributes `dp[moves-1][ni][nj] / 8`

### Transition Formula

```
dp[moves][i][j] = Σ ( dp[moves-1][ni][nj] / 8 ) for all valid knight moves (ni, nj)

```

For each `moves`:

* Iterate over every cell `(i, j)`
* Accumulate probabilities from previous step

This guarantees all dependencies are already computed.

---

## 🧮 Final Answer

After exactly `k` moves:

```
Answer = Σ dp[k][i][j]  for all cells (i, j)
```

Why?

* These represent **all ways the knight can still be on the board**
* Any probability that went off the board is automatically excluded

---

## 🧠 Intuition Summary

* We **spread probability mass** instead of tracking paths
* Each move redistributes probability to reachable cells
* Invalid moves simply disappear
* The final sum tells us how much probability remains on the board

---

## ⏱️ Complexity

| Metric | Value                         |
| ------ | ----------------------------- |
| Time   | `O(k × n² × 8)` → `O(k × n²)` |
| Space  | `O(k × n²)`                   |

(Can be optimized to `O(n²)` using rolling arrays)

---

## 🧪 Mental Picture

Think of it like **ink spreading on a chessboard**:

* Starts as a single drop
* Each move spreads the ink to knight-reachable cells
* Ink that flows off the board is lost
* After `k` spreads, measure how much ink remains


``` cpp
class Solution {
public:
    bool valid(int n ,int r, int c){
        return (r >= 0 && r < n && c >= 0 && c < n);
    }
    double knightProbability(int n, int k, int row, int column) {
        vector<int> dr = {-2,-2, +2,+2, +1,-1, +1,-1};
        vector<int> dc = {+1,-1, +1,-1, -2,-2, +2,+2};

        vector dp(k + 1, vector (n, vector<double>(n, 0.0)));

        dp[0][row][column] = 1;

        for (int moves = 1; moves <= k; moves++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0 ;l < 8 ; l++) {
                        int ni = i + dr[l];
                        int nj = j + dc[l];
                        if(!valid(n,ni,nj)) continue;
                        dp[moves][i][j] += dp[moves - 1][ni][nj] / 8;
                    }
                }
            }
        }

        double totalProbability = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalProbability += dp[k][i][j];
            }
        }

        return totalProbability;
    }
};
```



