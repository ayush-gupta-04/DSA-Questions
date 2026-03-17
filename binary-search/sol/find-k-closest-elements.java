// Obv : 
// 1. The k elements will be consecutive.
// 2. we need to find the start of the window.

// if ( x - arr[m] <= arr[m+k]- x){
//      my window is in the left .. including m.
// }else{
//      my window is in the right excluding m.
// }
// we have to have while(s < e).
// initially s = 0 .... e = n - k.

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;

        int s = 0;
        int e = n-k;
        while(s < e){
            int m = s + (e - s)/2;
            int a = arr[m];
            int b = arr[m+k];
            if(x - a <= b - x){
                e = m;
            }else{
                s = m+1;
            }
        }
        int cnt = 0;
        for(int i = s ; i < n && cnt < k ; i++){
            list.add(arr[i]);
            cnt++;
        }
        return list;
    }
}
