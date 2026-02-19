# 🚀 ArrayDeque

* Implements **Deque interface**
* Can act as **Stack (LIFO)**
* Can act as **Queue (FIFO)**
* Backed by **resizable circular array**
* Does **NOT allow null**
* Faster than Stack and LinkedList (in most cases)

Package:

```java
import java.util.ArrayDeque;
```

---

# 🏗️ How to Create ArrayDeque

```java
ArrayDeque<Integer> dq = new ArrayDeque<>();
```

---

# ⚡ Time Complexity

| Operation     | Time |
| ------------- | ---- |
| offerFirst()  | O(1) |
| offerLast()   | O(1) |
| pollFirst()   | O(1) |
| pollLast()    | O(1) |
| peekFirst()   | O(1) |
| peekLast()    | O(1) |
| size()        | O(1) |

All operations are **constant time** because it uses circular array.

---

# 🔥 Internal Working

ArrayDeque uses a **circular array**.
It maintains:
* `head` pointer
* `tail` pointer
When array fills → it resizes (doubles capacity).

---

# 🚀 Important Frequently Used Methods

---

## 1️⃣ `boolean offerFirst(E e)`

* work: Inserts element at front.
* Argument: Element to insert.
* Return: True if added.
* Time: O(1)

```java
dq.offerFirst(2);
```

---

## 2️⃣ `E pollFirst()`

* work: Removes and returns first element.
* Argument: None
* Return: First element or null if empty.
* Time: O(1)

```java
dq.pollFirst();
```

---

## 3️⃣ `E peekFirst()`

* work: Returns first element without removing.
* Argument: None
* Return: First element or null if empty.
* Time: O(1)

```java
dq.peekFirst();
```

---

## 4️⃣ `boolean offerLast(E e)`

* work: Inserts element at rear.
* Argument: Element to insert.
* Return: True if added.
* Time: O(1)

```java
dq.offerLast(30);
```

---

## 5️⃣ `E pollLast()`

* work: Removes and returns last element.
* Argument: None
* Return: Last element or null if empty.
* Time: O(1)

```java
dq.pollLast();
```

---

## 6️⃣ `E peekLast()`

* work: Returns last element without removing.
* Argument: None
* Return: Last element or null if empty.
* Time: O(1)

```java
dq.peekLast();
```

---

## 7️⃣ `boolean isEmpty()`

* work: Checks if deque empty.
* Return: True if empty.

---

## 8️⃣ `int size()`

* work: Returns number of elements.
* Time: O(1)

---

## 9️⃣ `void clear()`

* work: Removes all elements.

---

# 🔁 Using as Stack (LIFO)

```java
dq.push(10);   // same as addFirst()
dq.pop();      // same as removeFirst()
dq.peek();     // same as peekFirst()
```

Better than old `Stack` class.

---

# 🔁 Using as Queue (FIFO)

```java
dq.offer(10);   // addLast()
dq.poll();      // removeFirst()
dq.peek();      // peekFirst()
```

---

# ⚠️ Important Rules

* ❌ Does NOT allow null
* ✔ Faster than Stack
* ✔ Faster than LinkedList for deque
* ✔ Resizable array
* ❌ Not thread-safe

---

# 🔥 ArrayDeque vs LinkedList

| Feature      | ArrayDeque     | LinkedList         |
| ------------ | -------------- | ------------------ |
| Internal     | Circular Array | Doubly Linked List |
| Memory       | Less           | More               |
| Speed        | Faster         | Slower             |
| Null allowed | No             | Yes                |

---

# 🎯 Interview Patterns

ArrayDeque is used in:

* Monotonic stack problems
* Sliding window maximum
* BFS
* Implementing stack
* Implementing queue

---

# 🧠 Quick Concept Check

1. Why does ArrayDeque not allow null?
2. Why is it faster than LinkedList?
3. Why is it better than Stack class?

Answer first one 👀
