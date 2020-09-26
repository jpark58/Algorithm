'''
class Solution {
    
    public int makeConnected(int n, int[][] connections) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        int m = connections.length;
        int components = 0;
        int extraEdge = 0;
        for(int i = 0; i < m; i++) {
            int p1 = findParent(parent, connections[i][0]);
            int p2 = findParent(parent, connections[i][1]);
            if(p1 == p2) extraEdge++;
            else parent[p1] = p2;
        }
        for(int i = 0; i < n; i++) if(parent[i] == i) components++;
        return (extraEdge >= components - 1) ? components - 1 : -1;
    }
    
    public int findParent(int[] par, int i) {
        if(par[i] == i) return i;
        return par[i] = findParent(par, par[i]);
    }
}
'''


class Solution(object):
    def makeConnected(self, n, connections):
        """
        :type n: int
        :type connections: List[List[int]]
        :rtype: int
        """
        def find_parent(parent, i):
            if(parent[i] != i):
                parent[i] = find_parent(parent, parent[i])

            return parent[i]

        parent = [0]*(n)
        for i in range(0, n):
            parent[i] = i

        size = len(connections)
        components = 0
        extra_edges = 0

        for i in range(0, size):
            p1 = find_parent(parent, connections[i][0])
            p2 = find_parent(parent, connections[i][1])

            if p1 == p2:
                extra_edges += 1
            else:
                parent[p1] = p2

        for i in range(0, n):
            if parent[i] == i:
                components += 1

        return components-1 if (extra_edges >= components-1) else -1
