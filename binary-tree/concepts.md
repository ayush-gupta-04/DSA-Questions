### Height vs Depth vs Level
```java
       A  <-- Root Node    
      / \
     B   C
    /
   D      <-- Leaf Node
```
- Height of a Node : 
  - Calculated from Bottom -> Top.
  - Height of leaf = 0;
  - It counts edges (the max number of edges from that Node to leaf node).
 
- Depth of a Node
  - Calculated from Top -> Bottom.
  - Depth of root = 0;
  - It counts edges (the max number of edges from that Root to that Node).
 
- Level of a Node = Depth of a Node
