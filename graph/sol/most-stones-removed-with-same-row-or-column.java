// if a stone is at {r,c}
// i know for sure that i need to connect the whole row "r" with the whole col "c".
// we will provide a number to the row and the col.
// row -> r
// col -> c + (maxRow + 1)

class Solution {
    private class Disjoint {
        int[] size;
        int[] parent;
        public Disjoint(int n){
            this.size = new int[n + 1];
            this.parent = new int[n + 1];
            for(int i = 0 ; i < n + 1 ; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        int findParent(int u){
            if(u == this.parent[u]){
                return u;
            }
            int p = findParent(this.parent[u]);
            parent[u] = p;
            return p;
        }

        void union(int u, int v){
            int pu = findParent(u);
            int pv = findParent(v);
            if(pu == pv){
                return;
            }

            if(size[pu] > size[pv] ){
                parent[pv] = pu;
                size[pu] += size[pv];
            }else{
                parent[pu] = pv;
                size[pv] += size[pu];
            }
        }
    }
    public int removeStones(int[][] stones) {
        int mr = 0;  // max row
        int mc = 0;  // max col
        for(int[] it : stones){
            mr = Math.max(mr , it[0]);
            mc = Math.max(mc , it[1]);
        }

        Disjoint ds = new Disjoint(mr + mc + 1);

        // in the set, i just want to store every node which has stone in them.
        // since the whole row and the whole col is a node, we will store row and col here.
        HashSet<Integer> set = new HashSet<>();

        for(int[] node : stones){
            //i will treat r and c as nodes. the whole row as one node..whole col as one node.
            int r = node[0];
            int c = node[1] + mr + 1;
            //union these two nodes. r and c.
            ds.union(r,c);
            set.add(r);
            set.add(c);
        }
        int count = 0;
        for(int it : set){
            //if 'it' is the ultimate parent then count++,
            if(ds.findParent(it) == it){
                count++;
            }
        }

        return stones.length - count;
    }   
}
