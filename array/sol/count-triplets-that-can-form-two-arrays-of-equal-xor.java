// i need to find subarray like -> [ a ][ b ].
//   -> a = xor of all ele.
//   -> b = xor of all ele.
//   -> since a == b , means -> a ^ b = 0;

// for a subarray [ a ][ a ] ... the number of triplets will be :
//    -> a1 ^ a2 ^ a3 ^ a4 ^ a5 ^ a6 => xor = 0;
//       i                        k   -> i can have (k - i) triplets such that [i -> j-1] ^ [j -> k] = 0;




// now this turns into a question.
// find subarrays whose xor = 0...... we will use prefix xor method.


// method : 1.
// using 2 pointers.
// -> have i , j,
// -> find all subarrays whose xor = 0;
// -> for every subarray : cnt = cnt + (j - i);





// method : 2.
// using prefix sum ;
// concept : 
//       -> [a1 , a2 , .... an] .. the whole xor = 0; .. we have to find these subarrays.
//       -> then if this is a subarray.
//       -> [x1 , x2 . . . . xm][a1 , a2 , .... an]
//     xor = x1              x                   x   -> since [a1 , a2 , .... an] xor = 0;
// 
// therefore , in a prefixXor array : 
// -> if i found , xor till 'i' = x.
// -> i will look for xor = 'x' in the map.
// -> let idx be the index of xor = 'x' in the map.

// pitfall : 
// -> x can occur multiple times in the array .. for each subarray between (i and idx)..i will have to calc the triplets.
// -> i must put (0 , {-1}) in the map initially.



class Solution {

    //using prefixXor method.
    public int countTriplets(int[] arr) {

        //we can have xor at multiple places and i will have to find ans for each subarray between i and idx.
        // therefore i am using map(xor -> {}).
        HashMap<Integer , List<Integer>> map = new HashMap<>();
        map.put(0,new ArrayList<>());
        map.get(0).add(-1);
        int xor = 0;
        int cnt = 0;

        for(int i = 0; i < arr.length ; i++){
            xor = xor ^ arr[i];
            if(map.containsKey(xor)){
                for(int idx : map.get(xor)){
                    cnt = cnt + (i - idx - 1);
                }
            }
            if(map.containsKey(xor)){
                map.get(xor).add(i);
            }else{
                map.put(xor,new ArrayList<>());
                map.get(xor).add(i);
            }
        }
        return cnt;
    }




    // // using 2 pointers.
    // public int countTriplets(int[] arr) {
    //     int cnt = 0;

    //     for(int i = 0; i < arr.length ; i++){
    //         int xor = 0;
    //         for(int j = i ; j < arr.length ; j++){
    //             xor = xor ^ arr[j];
    //             if(xor == 0){
    //                 cnt += (j-i);
    //             }
    //         }
    //     }

    //     return cnt;
    // }
}
