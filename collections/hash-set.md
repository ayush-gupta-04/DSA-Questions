* Stores **unique elements only**
* Does **NOT maintain insertion order**
* Allows **one null**
* Is backed internally by a **HashMap**

# 🏗️ How to Create HashSet

```java
import java.util.HashSet;

HashSet<Integer> set = new HashSet<>();
```
# ⚡ Time Complexity (Very Important)

| Operation  | Average | Worst Case |
| ---------- | ------- | ---------- |
| add()      | O(1)    | O(n)       |
| remove()   | O(1)    | O(n)       |
| contains() | O(1)    | O(n)       |
| size()     | O(1)    | O(1)       |

Worst case happens when too many elements fall in same bucket.

---

# 🚀 Important Frequently Used Methods
---

## 1️⃣ `boolean add(E e)`
work: To add element into set.
Argument: Element to insert.
Return: True if element added , otherwise false.
O(1) average

```java
set.add(10);
```

---

## 2️⃣ `boolean remove(Object o)`
- work: To remove element into set.
- Argument: Element to Remove.
- Returns: True if element added , otherwise false.
O(1) average

```java
set.remove(10);
```

---

## 3️⃣ `boolean contains(Object o)`
- work: To check if element exits.
- Argument: Element to check existance of.
- Returns: True if element exists , otherwise false.
O(1) average

```java
boolean exist = set.contains(x)
```

---

## 4️⃣ `int size()`
Returns number of elements.
O(1)

---

## 5️⃣ `boolean isEmpty()`
Returns true if set empty.
---

## 6️⃣ `void clear()`
Removes all elements.
---

## 7️⃣ `Iterator<E> iterator()`
Used to traverse.

```java
Iterator<Integer> it = set.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}
```

---

# 🔁 Ways to Iterate HashSet

### 1️⃣ For-each loop (most common)

```java
for(Integer x : set) {
    System.out.println(x);
}
```

---

### 2️⃣ Iterator

Useful when removing elements while iterating.

---

### Important Methods:

| Method      | Meaning      |
| ----------- | ------------ |
| addAll()    | Union        |
| retainAll() | Intersection |
| removeAll() | Difference   |

---
