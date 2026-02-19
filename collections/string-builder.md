# 🔧 StringBuilder

`StringBuilder` is:

* Mutable (can modify content)
* Not thread-safe
* Faster than String
* Used for dynamic string operations

Package:

```java
import java.lang.StringBuilder;
```


# 🏗️ How to Create StringBuilder

```java
StringBuilder sb = new StringBuilder();
```

With initial value:

```java
StringBuilder sb = new StringBuilder("Hello");
```

---

# ⚡ Time Complexity

| Operation  | Time |
| ---------- | ---- |
| append()   | O(1) |
| insert()   | O(n) |
| delete()   | O(n) |
| charAt()   | O(1) |
| reverse()  | O(n) |
| toString() | O(n) |

---

# 🚀 Important Methods

---

## 1️⃣ `append(E e)`
* Argument E = char or String.
* work: Adds data at end.
* Time: O(1)

```java
sb.append("Java");
sb.append(10);
sb.append('a');
```

---

## 2️⃣ `insert(int offset, String str)`
* work: Inserts at given index.
* Time: O(n)

```java
sb.insert(1, "X");
```

---

## 3️⃣ `delete(int start, int end)`
* work: Removes substring.
---
## 4️⃣ `deleteCharAt(int index)`
Removes single character.

---

## 5️⃣ `replace(int start, int end, String str)`
Replaces substring.

---

## 6️⃣ `reverse()`
Reverses string.

---

## 7️⃣ `charAt(int index)`
Returns character.

---

## 8️⃣ `setCharAt(int index, char ch)`
Modifies character.

---

## 9️⃣ `length()`
Returns current length.

---

## 1️⃣2️⃣ `toString()`
Converts to String.
```

Will `sb1.equals(sb2)` return true or false?
And why? 👀🔥
