# TreeMap

### `put(key, value)`
- Insert or Updates the key-value pair.

### `V putIfAbsent(K key, V value)`
* work: Inserts only if key not present.
* If 1 is present -> it simply returns the value.
* If 1 is not present -> put new value and returns null
* Time: O(1)

```java
map.putIfAbsent(1, "Java");
```

### `V computeIfAbsent(K key , M mapping)`
```java
map.computeIfAbsent(5, k -> new HashSet<>()).add(2);
```
Why this works
- If 5 is not present → it creates a new HashSet and puts it and returns newly created set.
- If 5 exists → it simply returns the existing set
- Then .add(2) runs in both cases


### `get(key)`
- returns value or null.

### `getOrDefault(key , defaultVal)`
- returns value or defaultVal (if key not present).

### `remove(key)`
- remove the key-value entry.

### `containsKey(key)`
- return true if key exist, otherwise false;

### `size()`
- returns the size of the map (number of key-value pairs)

### `isEmpty()`
- true if map is empty, otherwise false;

### `clear()`
- clears the map.

---

### `firstKey()`
- returns first key i.e, smallest key (if sorted ASC) or largest key (if sorted DESC)

### `lastKey()`
- returns last key i.e, largest key (if sorted ASC) or smallest key (if sorted DESC)

### `ceilingKey(k)`
- returns smallest key >= k, otherwise null

### `higherKey(k)`
- smallest key > k, otherwise null

### `floorKey(k)`
- largest key <= k, otherwise null

### `lowerKey(k)`
- largest key < k, otherwise null

---


### `firstEntry()`
- returns first entry i.e, smallest entry (if sorted ASC) or largest entry (if sorted DESC)

### `lastEntry()`
- returns last entry i.e, largest key (if sorted ASC) or smallest key (if sorted DESC)

### `pollFirstEntry()`
- removes + returns the first entry.

### `pollLastEntry()`
- removes + returns the last entry.

---

### `keySet()`
```java
for(int key : map.keySet()){
    System.out.println(key);
}
```

### `values()`
```java
for(int val : map.values()){
    System.out.println(val);
}
```

### `entrySet()`
```java
for(Map.Entry<Integer,Integer> e : map.entrySet()){
    System.out.println(e.getKey() + " " + e.getValue());
}
```





