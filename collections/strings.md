# ⚡ Important String Methods

---

## 1️⃣ `int length()`
```java
int size = s.length();
```

---

## 2️⃣ `char charAt(int index)`
* work: Returns character at index.
* Time: O(1)
```java
char ch = s.charAt(0);
```

---

## 3️⃣ `String substring(int beginIndex)`

## 4️⃣ `String substring(int begin, int end)`
* work: Extracts substring from [begin , end).
```java
String str = s.substring(1,4);
```

---

## 5️⃣ `boolean equals(Object obj)`
* work: Compares content.
* Time: O(n)

```java
s1.equals(s2);
```
⚠️ Never use `==` for content comparison.

---

## 6️⃣ `boolean equalsIgnoreCase(String anotherString)`
* work: Case-insensitive comparison.

---

## 7️⃣ `int compareTo(String anotherString)`
Lexicographical comparison.

Returns:

* 0 → equal
* +ve → if string is greater then anotherString
* -ve → if string is smaller then anotherString

---

## 8️⃣ `boolean contains(CharSequence s)`
Checks substring presence.
---
## 9️⃣ `boolean startsWith(String prefix)`
---
## 🔟 `boolean endsWith(String suffix)`
---
## 1️⃣1️⃣ `String toLowerCase()`
---
## 1️⃣2️⃣ `String toUpperCase()`
---
## 1️⃣3️⃣ `String trim()`
Removes leading & trailing spaces.

---

## 1️⃣6️⃣ `String[] split(String regex)`
work : Splits string based on regex.

```java
String[] arr = s.split(" ");
```

---

## 1️⃣7️⃣ `int indexOf(char ch)`
---
## 1️⃣8️⃣ `int lastIndexOf(char ch)`
---
## 1️⃣9️⃣ `String concat(String str)`
Adds string (creates new object).

---

