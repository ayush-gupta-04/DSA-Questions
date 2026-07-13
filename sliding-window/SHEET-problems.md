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

- (*)[Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
- (*)[Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)
- (*)[Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)⭐
- (*)[Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/)
- (*)[Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/)
- (*)[Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/)⭐
- (*)[Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/description/)⭐
- (*)[Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)⭐
- (*)[Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/description/)⭐
- (*)[Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words)⭐
