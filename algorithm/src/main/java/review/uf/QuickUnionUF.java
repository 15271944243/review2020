package review.uf;

/**
 * 并查集demo - 路径压缩优化
 * @author: xiaoxiaoxiang
 * @date: 2021/3/12 09:05
 */
public class QuickUnionUF {
    /**
     * 并查集用数组实现,数组的每个元素表示数组集合,数组下标表示元素1,2,3....n,数组的值表示元素1,2,3...n对应的根节点
     */
    private int[] roots;

    /**
     *
     * @param n 并查集大小
     */
    public QuickUnionUF(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化时,roots[i]的根节点是它自己
            roots[i] = i;
        }
    }

    private int findRoot(int i) {
        int root = i;
        // 找到根节点了
        while (root != roots[root]) {
            root = roots[root];
        }
        // 路径压缩优化
        while (i != roots[i]) {
            int tmp = roots[i];
            roots[i] = root;
            i = tmp;
        }
        return root;
    }

    /**
     * p和q是不是同一个根节点下
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    /**
     * 将p所在的集合与q所在的集合合并
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int qroot = findRoot(q);
        int proot = findRoot(p);
        roots[proot] = qroot;
    }
}
