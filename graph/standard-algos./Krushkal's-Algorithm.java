import java.util.*;

class DSU{
    private int[] size;
    private int[] parent;
    
    public DSU(int n){
        this.size = new int[n+1];
        this.parent = new int[n+1];
        for(int i = 0;i <= n;i++){
            this.size[i] = 1;
            this.parent[i] = i;
        }
    }

    public int findParent(int u){
        if(u == this.parent[u]){
            return u;
        }
        return this.parent[u] = this.findParent(parent[u]);
    }

    public int parent(int u){
        return this.parent[u];
    }
    public int size(int u){
        return this.size[u];
    }

    public void union(int u , int v){
        if(isConnected(u,v)) return;

        int pu = findParent(u);
        int pv = findParent(v);

        if(size[pu] > size[pv]){
            this.size[pu] += this.size[pv];
            this.parent[pv] = pu;
        }else{
            this.size[pv] += this.size[pu];
            this.parent[pu] = pv;
        }
    }

    public boolean isConnected(int u, int v){
        return this.findParent(u) == this.findParent(v);
    }
}

public class Main {
    public static List<int[]> computeMST(int N , List<int[]> edges){
        List<int[]> mst = new ArrayList<>();
        int totalWeight = 0;
        DSU ds = new DSU(N);

        Collections.sort(edges , (x,y) -> Integer.compare(x[2], y[2]));

        for(int[] e : edges){
            if(mst.size() == N-1) break;
            int u = e[0];
            int v = e[1];
            int wt = e[2];

            if(ds.isConnected(u, v)) continue;

            ds.union(u, v);
            totalWeight += wt;
            mst.add(e);
        }
        System.out.println("Total Kruskal MST Weight : " + totalWeight);
        return mst;
    }

    public static void main(String[] args) {
        int N = 4;
        List<int[]> edges = new ArrayList<>();

        // Populating using {u, v, wt} arrays
        edges.add(new int[]{0, 1, 1});
        edges.add(new int[]{2, 3, 2});
        edges.add(new int[]{1, 2, 3});
        edges.add(new int[]{0, 2, 4});
        edges.add(new int[]{1, 3, 5});

        List<int[]> mst = computeMST(N, edges);

        System.out.println("Edges selected for the MST:");
        for (int[] e : mst) {
            System.out.println(e[0] + " --- w = " + e[2] + " --- " + e[1]);
        }
    }
}


