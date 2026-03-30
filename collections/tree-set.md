# TreeSet

### `add(val)`
- adds val to the set.

### `remove(val)`  
- removes val from the set.

### `contains(val)`
- true is vak exist, otherwise false.

### `size()`
### `isEmpty()`
### `clear()`

### `first()`
- returns the first element, otherwise null
  
### `last()`
- returns the last element, otherwise null.
  
### `ceiling(val)`
- smallest val >= val, otherwise null.
  
### `floor(val)`
- largest val <= val, otherwise null.
  
### `higher(val)`
- smallest val > val, otherwise null.
  
### `lower(val)`
- largest val < val, otherwise null.

### `pollFirst()`
- removes + returns the first element.

### `pollLast()`
- removes + returns the last element.

## 🔁 ALL Ways to Iterate TreeSet
### Enhanced For Loop (Most Common)
```java
for (int x : set) {
    System.out.println(x);
}
```
### Ascending iterator
- useful when removing elements while iterating.
```java
Iterator<Integer> it = set.iterator();

while (it.hasNext()) {
    System.out.println(it.next());
}
```


### Descending iterator
- useful when removing elements while iterating.
```java
Iterator<Integer> it = set.descendingIterator();

while (it.hasNext()) {
    System.out.println(it.next());
}
```
