// Time Complexity: O(2^9 * k)
//    - due to the exploration of all subsets of the set {1, 2, ..., 9}..
// Space Complexity: O(k)
//   - where k is the number of elements in the combination. This is due to the space used by the recursive call stack and the storage of valid combinations.

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    void fun(int n,int k,List<Integer> list,int i){
        if(n == 0 && k == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        if(n == 0 || k == 0 || i > 9) return;
        // not take.
        fun(n,k,list,i + 1);
        // take only if i can.
        if(k > 0 && n >= i){
            list.add(i);
            fun(n - i,k - 1,list,i + 1);
            list.removeLast();
        }
        return;
    }

    public List<List<Integer>> combinationSum3(int k,int n) {
        List<Integer> list = new ArrayList<>();
        fun(n,k,list,1);
        return result;
    }
}
