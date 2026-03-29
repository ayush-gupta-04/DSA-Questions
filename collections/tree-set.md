# 🌳 TreeSet

* Stores **unique elements only**
* Maintains **sorted order**
* Does **NOT allow null**
* Is backed internally by a **TreeMap**
* Internally uses a **Red-Black Tree**

---

## 🌲 Internal Structure (Red-Black Tree)
* Self-balancing Binary Search Tree
* Left < Root < Right
* Height remains balanced
* All main operations take **O(log n)**
---

# 🏗️ How to Create TreeSet

```java
import java.util.TreeSet;

TreeSet<Integer> set = new TreeSet<>();
```

---

# ⚡ Time Complexity (Very Important)

| Operation  | Time     |
| ---------- | -------- |
| add()      | O(log n) |
| remove()   | O(log n) |
| contains() | O(log n) |
| first()    | O(log n) |
| last()     | O(log n) |

Since it uses a balanced tree, operations are logarithmic.

---

# 🚀 Important Frequently Used Methods

---

## 1️⃣ `boolean add(E e)`

* work: To add element into set in sorted position.
* Argument: Element to insert.
* Return: True if element added, otherwise false.
* Time: O(log n)

```java
set.add(10);
```

---

## 2️⃣ `boolean remove(Object o)`

* work: To remove element from set.
* Argument: Element to remove.
* Returns: True if element removed, otherwise false.
* Time: O(log n)

```java
set.remove(10);
```

---

## 3️⃣ `boolean contains(Object o)`

* work: To check if element exists.
* Argument: Element to check existence of.
* Returns: True if element exists, otherwise false.
* Time: O(log n)

```java
boolean exist = set.contains(x);
```

---

## 4️⃣ `E first()`

* work: Returns smallest element.
* Argument: None
* Returns: First (minimum) element.
* Time: O(log n)

```java
int min = set.first();
```

---

## 5️⃣ `E last()`

* work: Returns largest element.
* Argument: None
* Returns: Last (maximum) element.
* Time: O(log n)

```java
int max = set.last();
```

---

## 6️⃣ `E ceiling(E e)`

* work: Returns smallest element ≥ given element.
* Argument: Element to compare.
* Returns: Ceiling value or null if none.
* Time: O(log n)

```java
set.ceiling(25);
```

---

## 7️⃣ `E floor(E e)`

* work: Returns largest element ≤ given element.
* Argument: Element to compare.
* Returns: Floor value or null if none.
* Time: O(log n)

```java
set.floor(25);
```

---

## 8️⃣ `E higher(E e)`

* work: Returns smallest element strictly greater than given element.
* Argument: Element to compare.
* Returns: Higher value or null.
* Time: O(log n)

---

## 9️⃣ `E lower(E e)`

* work: Returns largest element strictly smaller than given element.
* Argument: Element to compare.
* Returns: Lower value or null.
* Time: O(log n)

---
### `E pollFirst()`
- polls the first entry and returns it , null if empty.
---

### `E pollLast()`
- polls the last entry and returns it , null if empty.
---

# 🔁 Ways to Iterate TreeSet

### 1️⃣ For-each loop (Sorted Order)

```java
for(Integer x : set) {
    System.out.println(x);
}
```

Always prints in sorted order.

---

### 2️⃣ Iterator

```java
Iterator<Integer> it = set.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}
```

---

### 3️⃣ Descending Iterator

```java
Iterator<Integer> it = set.descendingIterator();
```

Prints elements in reverse sorted order.

---

# 🔄 Bulk Operations

| Method      | Meaning      |
| ----------- | ------------ |
| addAll()    | Union        |
| retainAll() | Intersection |
| removeAll() | Difference   |

---

# 🧠 Sorting Mechanism

TreeSet sorts elements using:

1. **Comparable (Natural Ordering)**
   Example: Integer → ascending order

2. **Comparator (Custom Ordering)**

```java
TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);
```

This makes it descending order.

---

# 🔥 HashSet vs TreeSet Quick Comparison

| Feature      | HashSet | TreeSet  |
| ------------ | ------- | -------- |
| Order        | No      | Sorted   |
| Time         | O(1)    | O(log n) |
| Null allowed | Yes (1) | No       |
| Internal DS  | HashMap | TreeMap  |

---
