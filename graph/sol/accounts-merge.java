class Solution {
    private class Disjoint{
        int[] size;
        int[] parent;

        public Disjoint(int n){
            this.size = new int[n + 1];
            this.parent = new int[n + 1];
            for(int i = 0; i < n + 1 ; i++){
                size[i] = 1;
                parent[i] = i;
            }
        }

        int findParent(int u){
            if(u == parent[u]){
                return u;
            }
            int p = findParent(parent[u]);
            parent[u] = p;
            return p;
        }

        void union(int u , int v){
            int pu = findParent(u);
            int pv = findParent(v);
            if(pu == pv){
                return;
            }

            if(size[pu] > size[pv]){
                parent[pv] = pu;
                size[pu] += size[pv];
            }else{
                parent[pu] = pv;
                size[pv] += size[pu];
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Disjoint ds = new Disjoint(n + 1);
        HashMap<String,Integer> map = new HashMap<>();   // email to index.
        for(int i = 0; i < n ;i++){
            List<String> acc = accounts.get(i);
            for(int j = 1; j < acc.size() ; j++ ){
                Integer index = map.get(acc.get(j));
                if(index == null){
                    map.put(acc.get(j),i);
                }else{
                    //i have got the email before .. merge i and index.
                    ds.union(i,index);
                }
            }
        }

        HashMap<Integer,HashSet<String>> ansMap = new HashMap<>();  // ultimate parent to <Strings>

        //merging the ans.
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            int val = entry.getValue();
            //we need to attach the key to the ultimate parent of val.
            //key is email
            //val is index of email.
            int up = ds.findParent(val);
            HashSet<String> set = ansMap.get(up);
            if(set == null){
                HashSet<String> newSet = new HashSet<>();
                newSet.add(key);
                ansMap.put(up,newSet);
            }else{
                set.add(key);
                ansMap.put(up,set);
            }
        }

        List<List<String>> list = new ArrayList<>();

        for(Map.Entry<Integer,HashSet<String>> entry : ansMap.entrySet()){
            int index = entry.getKey();
            HashSet<String> emails = entry.getValue();
            
            String name = accounts.get(index).get(0);
            List<String> temp = new ArrayList<>();
            temp.add(name);
            for(String email : emails){
                temp.add(email);
            }
            temp.subList(1,temp.size()).sort(null);
            list.add(temp);
        }
        return list;
    }
}
