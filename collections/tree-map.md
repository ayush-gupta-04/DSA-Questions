# 🌳 TreeMap

* Stores **key–value pairs**
* Keys are **unique**
* Values can be duplicate
* Maintains **sorted order of keys**
* Does **NOT allow null key**
* Allows **multiple null values**
* Backed internally by **Red-Black Tree**

Package:

```java
import java.util.TreeMap;
```

---

# 🌲 Internal Structure (Red-Black Tree)
Since it uses a **self-balancing BST**, operations take: **O(log n)**

---

# 🏗️ How to Create TreeMap

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

With custom comparator:

```java
TreeMap<Integer, String> map = new TreeMap<>((a, b) -> b - a);
```

---

# ⚡ Time Complexity

| Operation     | Time     |
| ------------- | -------- |
| put()         | O(log n) |
| get()         | O(log n) |
| remove()      | O(log n) |
| containsKey() | O(log n) |
| firstKey()    | O(log n) |
| lastKey()     | O(log n) |

---

# 🚀 Important Frequently Used Methods

---

## `V put(K key, V value)`

* work: Insert or update key-value pair.
* Arguments: Key, Value
* Returns: Previous value or null
* Time: O(log n)

```java
map.put(10, "Java");
```


## `V putIfAbsent(K key, V value)`
* work: Inserts only if key not present.
* Returns: Existing value if key present, otherwise null.
* Time: O(1)

```java
map.putIfAbsent(1, "Java");
```


---

## `V get(Object key)`

* work: Returns value associated with key.
* Argument: Key
* Returns: Value or null
* Time: O(log n)
```java
map.get(1);
```
---




## `V getOrDefault(Object key, V defaultValue)`

* work: Returns value if key exists, otherwise returns default value.
* Arguments:

  * key → key to search
  * defaultValue → value to return if key not found
* Returns: Value associated with key OR defaultValue
* Time: O(1) average

```java
int count = map.getOrDefault(1, 0);
```





---

## `V remove(Object key)`

* work: Removes key-value mapping.
* Returns: Removed value or null
* Time: O(log n)
```java
map.remove(1);
```
---



## `V replace(K key, V value)`

* work: Replaces value only if key exists.
* Returns: Old value or null.
* Time: O(1)

```java
map.replace(1, "C++");
```




---

## `boolean containsKey(Object key)`
* work: Checks if key exists.
* Time: O(log n)

---

## `K firstKey()`
* work: Returns smallest key.
* Time: O(log n)

---

## `K lastKey()`
* work: Returns largest key.
* Time: O(log n)

---

## `K ceilingKey(K key)`

* work: Smallest key ≥ given key.
* Time: O(log n)

---

## `K floorKey(K key)`

* work: Largest key ≤ given key.
* Time: O(log n)

---

## `K higherKey(K key)`
* work: Smallest key strictly greater than given key.

---

## `K lowerKey(K key)`
* work: Largest key strictly smaller than given key.

---

# Iterating TreeMap
## Using entrySet() (Best Method)

```java
for(Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " " + entry.getValue());
}
```



---



## Using keySet()

```java
for(Integer key : map.keySet()) {
    System.out.println(key);
}
```
