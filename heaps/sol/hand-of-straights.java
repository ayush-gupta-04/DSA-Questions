// TC -> N*logN
// SC -> N
// 1 -> 4 ... 1 is 4 times.
// it means the group items must have freq >= 4.
// if any one in group has freq < 4 then they will not be a part of the group.
// hence we can just return false in this case.

class Solution {
    public boolean isNStraightHand(int[] hand, int N) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int h : hand){
            map.put(h , map.getOrDefault(h , 0) + 1);
        }

        while(!map.isEmpty()){
            int start = map.firstKey();
            int count = map.get(start);  // min freq of every ele of the grp.

            for(int i = 0 ;i < N ; i++){
                int next = start + i;

                // if next is not there ... or its freq is < min freq of the group.
                if(!map.containsKey(next) || map.get(next) < count) return false;

                map.put(next , map.get(next) - count);
                if(map.get(next) == 0) map.remove(next);
            }
        }
        return true;
    }
}
