package review.tree;

/**
 * 手写红黑树:
 * 1. 定义颜色
 * 2. 创建节点RbNode
 * 3. 辅助方法定义  parentOf(node)、isRed(node)、setRed(node)、setBlack(node)、inOrderPrint()
 * 4. 左旋方法定义
 * 5. 右旋方法定义
 * 6. 节点插入方法定义
 * 7. 修复插入导致自平衡问题定义
 * @author: xiaoxiaoxiang
 * @date: 2020/7/19 20:39
 */
public class RbTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RbNode root;

    /**
     * 获取当前节点的父节点
     * @param node
     * @return
     */
    public RbNode parentOf(RbNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 节点是否为红色
     * @param node
     * @return
     */
    public boolean isRed(RbNode node) {
        return node != null && node.color;
    }

    /**
     * 节点是否为黑色
     * @param node
     * @return
     */
    public boolean isBlack(RbNode node) {
        return node != null && !node.color;
    }

    /**
     * 设置节点为红色
     * @param node
     * @return
     */
    public void setRed(RbNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     * @param node
     */
    public void setBlack(RbNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序遍历打印
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    /**
     * 新增节点
     * @param key
     * @param value
     */
    public void insertNode(K key, V value) {
        RbNode node = new RbNode();
        node.setKey(key);
        node.setValue(value);
        // 新增节点初始时是红色
        node.setColor(RED);
        insertNode(node);
    }

    /**
     * 新增节点
     * 1. 判断是否是第一次插入节点
     * 2. 查找当前新增node的父节点
     * @param node
     */
    private void insertNode(RbNode node) {
        // 判断是否是第一次插入节点
        if (this.root == null) {
            this.root = node;
            insertFixUp(node);
            return;
        }
        // 查找当前新增node的父节点
        RbNode parent = null;
        RbNode x = this.root;
        int cmp = 0;
        while (x != null) {
            parent = x;
            // 比较新增的节点和当前节点大小
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                // 需要到左子树进行查找
                x = x.left;
            } else if (cmp > 0) {
                // 需要到右子树进行查找
                x = x.right;
            } else {
                // 替换value值,直接返回,无须后续操作
                x.setValue(node.getValue());
                return;
            }
        }
        node.parent = parent;
        // 判断node是左节点还是右节点
        if (cmp < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        // 插入后修复红黑树平衡
        insertFixUp(node);
    }

    /**
     * 插入后修复红黑树平衡的方法
     *     |---情景1：红黑树为空树
     *     |---情景2：插入节点的key已经存在
     *     |---情景3：插入节点的父节点为黑色
     *
     *     情景4 需要咱们去处理
     *     |---情景4：插入节点的父节点为红色
     *          |---情景4.1：叔叔节点存在,并且为红色（父-叔 双红）
     *                  处理: 将父和叔叔节点染色为黑色,将爷爷染色为红色,如果爷爷的父节点是黑色,那么已达到自平衡;
     *                  如果爷爷的父节点是红色,则将爷爷当做当前插入节点,继续做自平衡操作
     *          |---情景4.2：叔叔节点不存在,或者为黑色,父节点为爷爷节点的左子树
     *               |---情景4.2.1：插入节点为其父节点的左子节点（LL情况）
     *                      处理: 将父节点染色为黑色,将爷爷节点染色为红色,对爷爷节点进行右旋
     *               |---情景4.2.2：插入节点为其父节点的右子节点（LR情况）
     *                      处理: 将父进行左旋,得到4.2.1(LL情况),指定父节点为当前节点,然后进行4.2.1的处理
     *          |---情景4.3：叔叔节点不存在,或者为黑色,父节点为爷爷节点的右子树
     *               |---情景4.3.1：插入节点为其父节点的右子节点（RR情况）
     *                      处理: 将父节点染色为黑色,将爷爷节点染色为红色,对爷爷节点进行左旋
     *               |---情景4.3.2：插入节点为其父节点的左子节点（RL情况）
     *                      处理: 将父节点进行右旋,得到4.3.1(RR情况),指定父节点为当前节点,然后进行4.3.1的处理
     */
    private void insertFixUp(RbNode node) {
        // 根节点是黑色
        setBlack(this.root);
        RbNode parent = parentOf(node);
        RbNode gparent = parentOf(parent);
        // 情景4：插入节点的父节点为红色
        if (parent != null && isRed(parent)) {
            // 如果父节点是红色,那么一定存在爷爷节点
            RbNode uncle = null;

            if (parent == gparent.left) {
                uncle = gparent.right;
                if (uncle != null && isRed(uncle)){
                    // 情景4.1: 叔叔节点存在,并且为红色（父-叔 双红）
                    // 处理: 将父和叔叔节点染色为黑色,将爷爷染色为红色,如果爷爷的父节点是黑色,那么已达到自平衡;
                    // 如果爷爷的父节点是红色,则将爷爷当做当前插入节点,继续做自平衡操作
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.2：叔叔节点不存在,或者为黑色,父节点为爷爷节点的左子树
                    if (node == parent.left) {
                        // 情景4.2.1：插入节点为其父节点的左子节点（LL情况）
                        // 处理: 将父节点染色为黑色,将爷爷节点染色为红色,对爷爷节点进行右旋
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    } else {
                        // 情景4.2.2：插入节点为其父节点的右子节点(LR情况)
                        // 处理: 将父进行左旋,得到4.2.1(LL情况),指定父节点为当前节点,然后进行4.2.1的处理
                        leftRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            } else {
                uncle = gparent.left;
                if (uncle != null && isRed(uncle)){
                    // 情景4.1: 叔叔节点存在,并且为红色（父-叔 双红）
                    // 处理: 将父和叔叔节点染色为黑色,将爷爷染色为红色,如果爷爷的父节点是黑色,那么已达到自平衡;
                    // 如果爷爷的父节点是红色,则将爷爷当做当前插入节点,继续做自平衡操作
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.3：叔叔节点不存在,或者为黑色,父节点为爷爷节点的右子树
                    if (node == parent.right) {
                        // 情景4.3.1：插入节点为其父节点的右子节点（RR情况）
                        // 处理: 将父节点染色为黑色,将爷爷节点染色为红色,对爷爷节点进行左旋
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    } else {
                        // 情景4.3.2：插入节点为其父节点的左子节点（RL情况）
                        // 处理: 将父节点进行右旋,得到4.3.1(RR情况),指定父节点为当前节点,然后进行4.3.1的处理
                        rightRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }
        }
    }


    /**
     * 中序遍历打印
     * @param node
     */
    private void inOrderPrint(RbNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key: " + node.key + ",value: " + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * 将节点x左旋:
     *   p                   p
     *   |                   |
     *   x                   y
     *  / \         ---->   / \
     * lx  y               x   ry
     *    / \             / \
     *  ly  ry          lx  ly
     * 1. 将x的右子节点更新为y的左子节点(ly）,并将y的左子节点(ly）的父节点更新为x
     * 2. 当x的父节点不为空时,更新y的父节点为x的父节点,并将x的父节点原先指向x的子树指向y
     * 3. 当x的父节点为空时,更新y为根节点
     * 4. 将x的父节点更新为y,将y的左子节点更新为x
     * @param x
     */
    private void leftRotate(RbNode x) {
        RbNode y = x.right;
        if (y == null) {
            // x的右子节点为null,无法左旋
            return;
        }
        // 将x的右子节点更新为y的左子节点
        x.right = y.left;
        // 将y的左子节点的父节点更新为x
        if (y.left != null) {
            y.left.parent = x;
        }
        // 当x的父节点不为空时
        RbNode p = x.parent;
        if (p != null) {
            // 更新y的父节点为x的父节点
            y.parent = p;
            // 并将x的父节点指定子树(当前x的子树位置)更新为y
            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        } else {
            this.setBlack(y);
            this.root = y;
            this.root.parent = null;
        }
        // 将x的父节点更新为y,将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /**
     * 将节点y右旋
     *     p                       p
     *     |                       |
     *     y                       x
     *    / \          ---->      / \
     *   x   ry                  lx  y
     *  / \                         / \
     * lx  ly                      ly  ry
     * 1. 将y的左子节点更新为x的右子节点ly,并将x的右子节点ly的父节点更新为y
     * 2. 当y的父节点不为空时,更新x的父节点为y的父节点,并将y的父节点原先指向y的子树指向x
     * 3. 当y的父节点为空时,更新x为根节点
     * 4. 将y的父节点更新为x,将x的右子节点更新为x
     * @param y
     */
    private void rightRotate(RbNode y) {
        RbNode x = y.left;
        if (x == null) {
            // y的左子节点为null,无法右旋
            return;
        }
        // 将y的左子节点更新为x的右子节点ly
        y.left = x.right;
        if (x.right != null) {
            // 将x的右子节点ly的父节点更新为y
            x.right.parent = y;
        }
        RbNode p = y.parent;
        if (p != null) {
            x.parent = p;
            if (p.left == y) {
                p.left = x;
            } else {
                p.right = x;
            }
        } else {
            this.setBlack(x);
            this.root = x;
            this.root.parent = null;
        }
        y.parent = x;
        x.right = y;
    }

    static class RbNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private RbNode parent;
        private RbNode left;
        private RbNode right;
        private boolean color;

        public RbNode() {
        }

        public RbNode(K key, V value, RbNode parent, RbNode left, RbNode right, boolean color) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RbNode getParent() {
            return parent;
        }

        public void setParent(RbNode parent) {
            this.parent = parent;
        }

        public RbNode getLeft() {
            return left;
        }

        public void setLeft(RbNode left) {
            this.left = left;
        }

        public RbNode getRight() {
            return right;
        }

        public void setRight(RbNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }

    public RbNode getRoot() {
        return root;
    }

    public void setRoot(RbNode root) {
        this.root = root;
    }
}
