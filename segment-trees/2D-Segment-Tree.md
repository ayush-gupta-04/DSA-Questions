## 2D Segment Tree — Concise Reference Guide

### 1. Conceptual Blueprint

A 2D Segment Tree is essentially a Segment Tree of Segment Trees.

**Structure**

* The matrix has size **N × M**.
* The tree is stored as a 2D array: `int[][] tree = new int[4*N][4*M]`

| Dimension | Meaning |
| --- | --- |
| `nodeX` | Node of the outer segment tree (rows) |
| `nodeY` | Node of the inner segment tree (columns) |

**Hierarchy**

```text
Outer Tree (Rows)
        |
        ├── nodeX
        |      |
        |      └── Inner Tree (Columns)
        |             |
        |             ├── nodeY
        |             ├── nodeY
        |             └── ...

```

**Node Indexing (0-based)**

* **Parent:** `node`
* **Left Child:** `2 * node + 1`
* **Right Child:** `2 * node + 2`

> **Note:** Both outer and inner trees follow the exact same indexing rule.

---

### 2. Building the Tree

Building happens in two levels:

1. `buildOuter` → divides rows
2. `buildInner` → divides columns

#### Outer Build (`buildOuter`)

Constructs the row segment tree.

* Start at the root node `nodeX = 0` representing all rows `[0 .. N-1]`.
* If the current node represents more than one row:
1. Split the rows into two halves.
2. Recursively build the left child (`2*nodeX + 1`).
3. Recursively build the right child (`2*nodeX + 2`).


* After the children are ready (or if this node is already a single row), build the inner tree for this node by calling `buildInner`.
* *Result:* Every outer node owns a complete column segment tree representing its specific row range.

#### Inner Build (`buildInner`)

Constructs the column segment tree for a specific outer node.

* Start from the inner root `nodeY = 0` covering columns `[0 .. M-1]`.
* **If the column range has only one column:**
* If the outer node is also a leaf row node: Store the matrix value directly from `grid[row][col]`.
* Otherwise: Combine the same column values from the left and right outer children.


* **If the column range contains multiple columns:**
* Divide the columns into two halves.
* Recursively build left and right inner children.
* Store the sum of the two children.


* *Result:* Every `(nodeX, nodeY)` stores the sum of a sub-rectangle of the matrix.

---

### 3. Query Operation

**Goal:** Compute the sum of a rectangle `[r1..r2] × [c1..c2]`.
The process again has two levels.

#### Outer Query (`queryOuter`)

Searches for the required row range.

* **No Overlap:** If the current row segment lies completely outside `[r1..r2]`, return `0`.
* **Total Overlap:** If the current row segment lies completely inside `[r1..r2]`:
* Stop exploring rows.
* Directly query the inner tree for the column range `[c1..c2]`.


* **Partial Overlap:** If the row segment partially overlaps:
* Recursively query the left outer child.
* Recursively query the right outer child.
* Add both results.



#### Inner Query (`queryInner`)

Performs a normal 1D segment tree query on columns.

* **No Overlap:** If the column segment lies outside `[c1..c2]`, return `0`.
* **Total Overlap:** If the column segment is completely inside `[c1..c2]`, return the stored value `tree[nodeX][nodeY]`.
* **Partial Overlap:** Split the column segment:
* Query left column child.
* Query right column child.
* Return their sum.



---

### 4. Update Operation

**Goal:** Update a single cell `grid[targetRow][targetCol] = newValue`.
Again, two levels are used.

#### Outer Update (`updateOuter`)

Finds the correct row path.

* Start from the root row node.
* If the node represents multiple rows:
* Determine whether the target row lies in the left half or right half.
* Recursively move into that child.


* At every outer node visited, update its inner tree by calling `updateInner`.
* *Result:* Ensures the update propagates through all row ranges containing the target row.

#### Inner Update (`updateInner`)

Updates the column tree.

* **Leaf Column (`startCol == endCol`):**
* If the outer node is also a leaf row node: Directly assign the new value.
* Otherwise: Merge the column value from the left and right outer children.


* **Internal Column Node:**
* Move to the child containing `targetCol`.
* After updating that child, recompute the node value as: `left child + right child`.


* *Result:* The change propagates up the column tree and also up the row tree.

---

### 5. Complete Java Implementation

```java
class SegmentTree2D {

    int[][] tree;
    int[][] grid;
    int n, m;

    public SegmentTree2D(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;

        tree = new int[4 * n][4 * m];

        buildOuter(0, 0, n - 1);
    }

    // -------- BUILD --------

    private void buildOuter(int nodeX, int startRow, int endRow) {
        if (startRow != endRow) {
            int midRow = (startRow + endRow) / 2;

            buildOuter(2 * nodeX + 1, startRow, midRow);
            buildOuter(2 * nodeX + 2, midRow + 1, endRow);
        }
        buildInner(nodeX, startRow, endRow, 0, 0, m - 1);
    }

    private void buildInner(int nodeX, int startRow, int endRow,
                            int nodeY, int startCol, int endCol) {

        if (startCol == endCol) {
            if (startRow == endRow)
                tree[nodeX][nodeY] = grid[startRow][startCol];
            else
                tree[nodeX][nodeY] = tree[2 * nodeX + 1][nodeY] + tree[2 * nodeX + 2][nodeY];
        } else {
            int midCol = (startCol + endCol) / 2;

            buildInner(nodeX, startRow, endRow, 2 * nodeY + 1, startCol, midCol);
            buildInner(nodeX, startRow, endRow, 2 * nodeY + 2, midCol + 1, endCol);

            tree[nodeX][nodeY] = tree[nodeX][2 * nodeY + 1] + tree[nodeX][2 * nodeY + 2];
        }
    }

    // -------- QUERY --------

    public int querySum(int r1, int c1, int r2, int c2) {
        return queryOuter(0, 0, n - 1, r1, r2, c1, c2);
    }

    private int queryOuter(int nodeX, int startRow, int endRow,
                           int r1, int r2, int c1, int c2) {

        if (r2 < startRow || r1 > endRow)
            return 0;

        if (r1 <= startRow && endRow <= r2)
            return queryInner(nodeX, 0, 0, m - 1, c1, c2);

        int midRow = (startRow + endRow) / 2;

        int left = queryOuter(2 * nodeX + 1, startRow, midRow, r1, r2, c1, c2);
        int right = queryOuter(2 * nodeX + 2, midRow + 1, endRow, r1, r2, c1, c2);

        return left + right;
    }

    private int queryInner(int nodeX, int nodeY, int startCol, int endCol,
                           int c1, int c2) {

        if (c2 < startCol || c1 > endCol)
            return 0;

        if (c1 <= startCol && endCol <= c2)
            return tree[nodeX][nodeY];

        int midCol = (startCol + endCol) / 2;

        int left = queryInner(nodeX, 2 * nodeY + 1, startCol, midCol, c1, c2);
        int right = queryInner(nodeX, 2 * nodeY + 2, midCol + 1, endCol, c1, c2);

        return left + right;
    }

    // -------- UPDATE --------

    public void update(int targetRow, int targetCol, int val) {
        updateOuter(0, 0, n - 1, targetRow, targetCol, val);
    }

    private void updateOuter(int nodeX, int startRow, int endRow,
                             int targetRow, int targetCol, int val) {

        if (startRow != endRow) {
            int midRow = (startRow + endRow) / 2;

            if (targetRow <= midRow)
                updateOuter(2 * nodeX + 1, startRow, midRow, targetRow, targetCol, val);
            else
                updateOuter(2 * nodeX + 2, midRow + 1, endRow, targetRow, targetCol, val);
        }

        updateInner(nodeX, startRow, endRow, 0, 0, m - 1, targetCol, val);
    }

    private void updateInner(int nodeX, int startRow, int endRow,
                             int nodeY, int startCol, int endCol,
                             int targetCol, int val) {

        if (startCol == endCol) {
            if (startRow == endRow)
                tree[nodeX][nodeY] = val;
            else
                tree[nodeX][nodeY] = tree[2 * nodeX + 1][nodeY] + tree[2 * nodeX + 2][nodeY];
        } else {
            int midCol = (startCol + endCol) / 2;

            if (targetCol <= midCol)
                updateInner(nodeX, startRow, endRow, 2 * nodeY + 1, startCol, midCol, targetCol, val);
            else
                updateInner(nodeX, startRow, endRow, 2 * nodeY + 2, midCol + 1, endCol, targetCol, val);

            tree[nodeX][nodeY] = tree[nodeX][2 * nodeY + 1] + tree[nodeX][2 * nodeY + 2];
        }
    }
}

```

---

### 6. Complexity Summary

| Operation | Complexity | Note |
| --- | --- | --- |
| **Space** | **O(N × M)** | Structure allocates `4N × 4M` nodes, proportional to N × M. |
| **Build** | **O(N × M)** | Visits every allocated node once. |
| **Query** | **O(log N × log M)** | Traverses `log N` row nodes; for each, performs a `log M` column query. |
| **Update** | **O(log N × log M)** | Follows the same height-based traversal path as querying. |
