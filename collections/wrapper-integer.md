# 🚀 Important Integer Methods

---

## 1️⃣ `parseInt(String s)`
* work: Converts String → int
* Returns: primitive int

```java
int x = Integer.parseInt("123");
```

---

## 2️⃣ `valueOf(String s)`
* work: Converts String → Integer object
* Returns: Integer

```java
Integer obj = Integer.valueOf("123");
```

Difference:
* parseInt → int
* valueOf → Integer

---

## 3️⃣ `toString(int i)`

---

## 4️⃣ `compare(int x, int y)`
* work: Compares two ints
* Returns:
  * 0 if equal
  * +ve if x > y
  * -ve if x < y
  
```java
Integer.compare(10, 20);
```

---

## 5️⃣ `compareTo(Integer anotherInteger)`
Used when sorting objects.
```java
Integer a = 10;
Integer b = 20;
a.compareTo(b);
```


---

## 🔟 `toBinaryString(int i)`
## 1️⃣1️⃣ `toHexString(int i)`
## 1️⃣2️⃣ `toOctalString(int i)`

# 🚨 `==` vs `equals()` with Integer

```java
Integer a = 128;
Integer b = 128;

a == b   ❌
a.equals(b)  ✔
```

Always use `equals()` for object comparison.


