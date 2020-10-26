import java.util.Arrays;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 朋友圈
 * @date Date : 2020年10月18日 21:02
 */
public class FindCircleNumSolution {

    /**
     * 并查集
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int ML = M.length;
        int[] parent = new int[ML];
        Arrays.fill(parent, -1);
        for (int i = 0; i < ML; i++) {
            for (int j = 0; j < ML; j++) {
                // 每个节点遍历相邻节点 让相邻节点指向这个节点，使之在某种条件下成为一个组
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }

            }
            
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1){
                count++;
            }
            
        }
        return count;
    }

    /**
     * 创建并查集
     * @param parent
     * @param i
     * @param j
     */
    private void union(int[] parent, int i, int j) {
        int xSet = find(parent, i);
        int ySet = find(parent, j);
        if (xSet != ySet) {
            parent[xSet] = ySet;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] == -1) {
            return x;
        }
        return find(parent, parent[x]);
    }
}
