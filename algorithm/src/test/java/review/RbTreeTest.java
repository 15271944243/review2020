package review;

import review.tree.RbTree;
import review.tree.RbTreeOperation;

import java.util.Scanner;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/19 23:11
 */
public class RbTreeTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RbTree<Integer, Object> rbTree = new RbTree<>();

        int i=0;
        while (i < 9) {
            i++;
//            System.out.println("请输入数字: ");
//            String key = scanner.next();
//            System.out.println();
//            rbTree.insertNode(Integer.valueOf(key), null);
            rbTree.insertNode(i, null);
        }
        RbTreeOperation.show(rbTree.getRoot());
    }
}
