// --------------- Line Sweep -------------------
 // line sweep soluiton.
// find the max overlappings.
// since the [arr,dep] both are inclusive.
// if [20, 40] , [40 , 50] .. since arr == dep 
//    so , 40 -> 0 (+1-1).
//    but ideally we sould process (40->1) then (40->-1)
// Sol : inc dep time by 1 for everyone and make dep time exclusive.


class Solution {
    public int minPlatform(int arr[], int dep[]) {
        //  code here
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int n = arr.length;
        
        for(int i = 0;i < n;i++){
            map.put(arr[i] , map.getOrDefault(arr[i],0)+1);
            map.put(dep[i]+1 , map.getOrDefault(dep[i]+1,0)-1);
        }
        
        int cnt = 0;
        int max = 0;
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            cnt += e.getValue();
            max = Math.max(max , cnt);
        }
        return max;
    }
}




// ------------------- 2 pointers --------------------

// in line sweep, we move in order of the time.
// why not we have 2 pointers and we will move in order of the time.


class Solution {
    public int minPlatform(int arr[], int dep[]) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i = 0;
        int j = 0;
        int n = arr.length;
        int max = 0;
        int cnt = 0;
        while(i < n && j < n){
            if(arr[i] <= dep[j]){   // if they are equal .. we need to inc the count.
                cnt++;
                i++;
            }else{
                cnt--;
                j++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
