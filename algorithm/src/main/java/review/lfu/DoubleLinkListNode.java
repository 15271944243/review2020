package review.lfu;

/**
 * 已废弃,未完成
 * @author: xiaoxiaoxiang
 * @date: 2020/8/7 14:39
 */
public class DoubleLinkListNode<E> {

    int frequency;

    E item;

    DoubleLinkListNode<E> next;

    DoubleLinkListNode<E> prev;

    DoubleLinkListNode(DoubleLinkListNode prev, E item, DoubleLinkListNode next) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public DoubleLinkListNode<E> getNext() {
        return next;
    }

    public void setNext(DoubleLinkListNode<E> next) {
        this.next = next;
    }

    public DoubleLinkListNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkListNode<E> prev) {
        this.prev = prev;
    }
}
