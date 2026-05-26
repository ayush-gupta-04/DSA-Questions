// ---------------------------- Approach : Using set --------------------
// time : (N + M)log(N + M)
// space : (N + M)

// add all elements to the set.
// make a list from the set.

class Solution {
    public static ArrayList<Integer> findUnion(int nums1[], int nums2[]) {
        // code here
        TreeSet<Integer> set = new TreeSet<>();
        for(int a : nums1) set.add(a);
        for(int a : nums2) set.add(a);
        
        int n = set.size();
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int a : set){
            list.add(a);
        }
        return list;
    }
}






// -------------- CODE -----------------
// TC = O(m+n) at worst case
// SC = O(m+n)

class Solution {
    public static ArrayList<Integer> findUnion(int nums1[], int nums2[]) {
        // code here
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        int i1 = 0;
        int i2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(i1 < n1 && i2 < n2){
            if(nums1[i1] < nums2[i2]){
                if(list.isEmpty() || list.getLast() != nums1[i1]){   // do not add duplicates
                    list.add(nums1[i1]);
                }
                i1++;
            }else if(nums2[i2] <= nums1[i1]){
                if(list.isEmpty() || list.getLast() != nums2[i2]){   // do not add duplicates
                    list.add(nums2[i2]);
                }
                i2++;
            }
        }
        
        while(i1 < n1){
            if(list.isEmpty() || list.getLast() != nums1[i1]){   // do not add duplicates
                list.add(nums1[i1]);
            }
            i1++;
        }
        
        while(i2 < n2){
            if(list.isEmpty() || list.getLast() != nums2[i2]){   // do not add duplicates
                list.add(nums2[i2]);
            }
            i2++;
        }
        return list;
    }
}
