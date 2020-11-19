package review.lfu;

/**
 * 已废弃,未完成
 * @author: xiaoxiaoxiang
 * @date: 2020/8/7 11:34
 */
public class DoubleLinkList<E> {

    private int size = 0;

    private DoubleLinkListNode<E> first;

    private DoubleLinkListNode<E> last;

    /**
     * 向链表头部添加
     * @param e
     */
    public DoubleLinkListNode addFirst(E e) {
        final DoubleLinkListNode f = first;
        final DoubleLinkListNode newNode = new DoubleLinkListNode(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        return newNode;
    }

    public int size() {
        return size;
    }

}