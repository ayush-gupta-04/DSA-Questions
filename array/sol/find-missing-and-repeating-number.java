// ------------------- Better Approach : Use freq array ---------------
// time : N
// space : N

class Solution {
    ArrayList<Integer> findTwoElement(int[] nums) {
        // code here
        int n = nums.length;
        int[] freq = new int[n+1];
        
        for(int a : nums) freq[a]++;
            
        int a = -1;
        int b = -1;
        for(int i = 1 ; i <= n;i++){
            if(freq[i] == 2){
                a = i;
            }
            if(freq[i] == 0){
                b = i;
            }
        }
            
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}





// ---------------------------- Optimal Apporoach : Cyclic Sort -----------------
// time : N
// space : 1

class Solution {
    void swap(int[] nums, int i , int j){
        int temp=nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    ArrayList<Integer> findTwoElement(int[] nums) {
        // code here
        int n = nums.length;
        
        int i = 0;
        while(i < n){
            int cor = nums[i]-1;
            if(nums[cor] != nums[i]){
                swap(nums,i,cor);
            }else{
                i++;
            }
        }
        
        int a = -1;
        int b = -1;
        
        for(i=0;i < n;i++){
            if(nums[i] != i+1){
                // i+1 is missing and nums[i] is repeating
                a = nums[i];
                b = i+1;
                break;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}



// -------------------- Optimal Approach 2 : Using buckets --------------
// time : 5*N

// xor1 = xor of all the array elements.
// xor2 = xor from 1 -> n
// xor3 = xor1 and xor2

class Solution {

    ArrayList<Integer> findTwoElement(int[] nums) {
        int xor1 = 0;
        int xor2 = 0;
        
        // finding xor1 and xor2
        int n = nums.length;
        for(int a : nums) xor1 = xor1^a;
        for(int i = 1;i <= n;i++){
            xor2 = xor2^i;
        }
        
        int xor3 = xor1 ^ xor2;
        
        int mask = xor3 ^ (xor3 & (xor3-1));
        
        
        // separate all the elements into one of the bucket.
        int buc0 = 0; // for 0
        int buc1 = 0; // for 1
        for(int a : nums){
            if((a&mask) == 0) buc0 = buc0 ^ a;
            else if((a&mask) != 0) buc1 = buc1 ^ a;
        }
        
        for(int i = 1;i <= n;i++){
            if((i&mask) == 0) buc0 = buc0 ^ i;
            else if((i&mask) != 0) buc1 = buc1 ^ i;
        }
        
        
        
        // verifying for repeating and mising number.
        ArrayList<Integer> list = new ArrayList<>();
        for(int a : nums){
            if(a == buc0){
                // buc0 -> repeating ... buc1 -> missing.
                list.add(buc0);
                list.add(buc1);
                break;
            }
        }
        
        if(list.isEmpty()){
            list.add(buc1);
            list.add(buc0);
        }
        return list;
    }
}
