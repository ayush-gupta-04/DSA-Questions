# 🗺️ HashMap

* Stores **key–value pairs**
* Keys are **unique**
* Values can be duplicate
* Allows **one null key**
* Allows **multiple null values**
* Does **NOT maintain insertion order**
* Backed by **array + hashing**
* Since Java 8 → uses **Tree (Red-Black Tree)** for high collisions

Package:

```java
import java.util.HashMap;
```

---

# 🏗️ How to Create HashMap

```java
HashMap<Integer, String> map = new HashMap<>();
```

---

# ⚡ Time Complexity

| Operation     | Average | Worst Case |
| ------------- | ------- | ---------- |
| put()         | O(1)    | O(n)       |
| get()         | O(1)    | O(n)       |
| remove()      | O(1)    | O(n)       |
| containsKey() | O(1)    | O(n)       |

Worst case happens due to collisions.

---

# 🔥 Internal Working

HashMap uses:

1. **Array of buckets**
2. Each bucket stores:
   * LinkedList (before Java 8)
   * Red-Black Tree (after threshold in Java 8)
---

### 🔥 Important (Java 8):

If bucket size > 8 → converts to **Red-Black Tree**
If bucket size < 6 → converts back to LinkedList

---

# 🚀 Important Frequently Used Methods
---

## `V put(K key, V value)`

* work: Insert or update key-value pair.
* Arguments: Key, Value
* Returns: Previous value associated with key (or null)
* Time: O(1) average

```java
map.put(1, "Ayush");
```


---


## `V putIfAbsent(K key, V value)`
* work: Inserts only if key not present.
* If 1 is present -> it simply returns the value.
* If 1 is not present -> put new value and returns null
* Time: O(1)

```java
map.putIfAbsent(1, "Java");
```

## `V computeIfAbsent(K key , M mapping)`
```java
map.computeIfAbsent(5, k -> new HashSet<>()).add(2);
```
Why this works
- If 5 is not present → it creates a new HashSet and puts it and returns newly created set.
- If 5 exists → it simply returns the existing set
- Then .add(2) runs in both cases


---



## `V get(Object key)`

* work: Returns value associated with key.
* Argument: Key
* Returns: Value or null
* Time: O(1) average

```java
String name = map.get(1);
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
int count = map.getOrDefault("java", 0);
```



---




## `V remove(Object key)`
* work: Removes mapping.
* Argument: Key
* Returns: Removed value or null
* Time: O(1)

---


##  `V replace(K key, V value)`

* work: Replaces value only if key exists.
* Returns: Old value or null.
* Time: O(1)

```java
map.replace(1, "C++");
```

---

##  `boolean replace(K key, V oldValue, V newValue)`

* work: Replaces value only if oldValue matches.
* Returns: true if replaced.
* Time: O(1)


---




## `boolean containsKey(Object key)`
* work: Checks if key exists.
* Returns: True/False

---

## `boolean containsValue(Object value)`
* work: Checks if value exists.
* Time: O(n) (because it must scan all entries)

---

## `int size()`
Returns number of key-value pairs.

---

## `boolean isEmpty()`
Checks if map empty.

---

## `void clear()`
Removes all entries.


---

## `String toString()`
* work: Converts map to String format.
* Argument: None
* Returns: String representation
* Time: O(n)
```java
System.out.println(map.toString());
```



---



# 🔁 Iterating HashMap

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
