
##### fixed-length
* We have 2 options :
    * We can run a fixed length window , but only when the TC to check the validity of the window is not very large.
    * We can use var length window and when it's size is favourable , we will add it. We know to check validity of var size window is much less bacause the invalidity is caused only by the rth element.
##### var-length
* We Have 2 types here : 
    * On increasing the window size the window becomes invalid and we need to shrink it.
    * On increasing the window size the window becomes more valid but we need the min window , hence we shrink it while it's valid and calc the min.
 
**Question - Count Subarray with a property P**
- We can use PrefixSum + HashMap.
- We can use Sliding Window (Subarrays with K Different Integers)
  - Use this only when The Subarray is Monotonic. So that i am 100% sure that to have a max len .. we move r , and if became invalid .. move l to make it valid again.
  - Use this when we don't have any confusion on moving the r and l.



## Questions

### Fixed Length
- (*)[Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/description/)
- [Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/)
- [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/)
- [Permutation in String](https://leetcode.com/problems/permutation-in-string/description/)

### Variable Length
- (*)[Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
- (*)[Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)⭐
- (*)[Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/)
- (*)[Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/description/)⭐
- (*)[Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)⭐
- (*)[Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/description/)⭐
- (*)[Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words)⭐
- (*)[Longest Substring with at most K distinct characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/)⭐
- (*)[Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/)⭐
- (*)[Fruit Into Baskets](https://takeuforward.org/data-structure/fruit-into-baskets)
- [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)
- [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/)
- [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/description/)
- [Minimum Size SubArray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)
- [Number of Subarrays with Bounded Maximum](https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum)
