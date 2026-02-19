# 🧰 Collections Class (Utility for Collections)

`Collections` is a **utility class** containing static methods to operate on:

* List
* Set
* Queue
* etc.

Package:

```java
import java.util.Collections;
```

---

# ⚡ Important Methods — Collections

---

## 1️⃣ `sort(List<T> list)`
* work: Sorts list in ascending or descending order.

```java
Collections.sort(list); // ascending by default
```

Custom sorting:

```java
Collections.sort(list, (a,b) -> b-a);
```

---

## 2️⃣ `reverse(List<?> list)`
* work: Reverses list.

```java
Collections.reverse(list);
```

---

## 3️⃣ `shuffle(List<?> list)`
* work: Randomly shuffles list.

```java
Collections.shuffle(list);
```

---

## 4️⃣ `min(Collection<T> c)`
* work: Returns minimum element.

```java
int min = Collections.min(list);
```

---

## 5️⃣ `max(Collection<T> c)`
* work: Returns maximum element.
```java
int max = Collections.max(list);
```

---

## 6️⃣ `binarySearch(List<T> list, T key)`
* work: Searches element in sorted list.
* Returns: index or negative value

```java
Collections.binarySearch(list, 10);
```

---

## 7️⃣ `frequency(Collection<?> c, Object o)`
* work: Counts occurrences.

```java
Collections.frequency(list, 5);
```

---

## 8️⃣ `swap(List<?> list, int i, int j)`
* work: Swaps two elements.
```java
Collections.swap(list, 0, 1);
```

---

## 9️⃣ `fill(List<? super T> list, T obj)`
* work: Replaces all elements with obj.

---

# 📦 Arrays Class (Utility for Arrays)

Used for operations on **primitive arrays + object arrays**

Package:

```java
import java.util.Arrays;
```

---

# ⚡ Important Methods — Arrays

---

## 1️⃣ `sort()`
```java
Arrays.sort(arr);  // asceding by default
```

Custom comparator (objects only):
```java
Arrays.sort(arr, Collections.reverseOrder());
```

---

## 2️⃣ `binarySearch()`
* work: Search element in sorted array.

```java
Arrays.binarySearch(arr, 10);
```

---

## 3️⃣ `fill()`
* work: Fill array with value.
```java
Arrays.fill(arr, 0);
```

---

## 5️⃣ `toString()`

* work: Converts array to string.

```java
System.out.println(Arrays.toString(arr));
```

---

## 6️⃣ `deepToString()`

* work: Prints 2D arrays.

```java
Arrays.deepToString(matrix);
```

---

## 7️⃣ `copyOf()`

* work: Copies array.

```java
int[] b = Arrays.copyOf(a, n);
```

---

## 8️⃣ `copyOfRange()`
* work: Copy subarray.

```java
Arrays.copyOfRange(arr, l, r);
```

---

## 9️⃣ `asList()`

* work: Converts array → List

```java
List<Integer> list = Arrays.asList(arr);
```

---

Answer first one 👀🔥
