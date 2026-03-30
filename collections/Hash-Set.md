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
