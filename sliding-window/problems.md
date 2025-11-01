## Questions
##### fixed-length
* We have 2 options :
    * We can run a fixed length window , but only when the TC to check the validity of the window is not very large.
    * We can use var length window and when it's size is favourable , we will add it. We know to check validity of var size window is much less bacause the invalidity is caused only by the rth element.
##### var-length
* We Have 2 types here : 
    * On increasing the window size the window becomes invalid and we need to shrink it.
    * On increasing the window size the window becomes more valid but we need the min window , hence we shrink it while it's valid and calc the min.
### Fixed Length
- [Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/)
- [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/)
- [Permutation in String](https://leetcode.com/problems/permutation-in-string/description/)
### Variable Length
- [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/description/)
- [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)
