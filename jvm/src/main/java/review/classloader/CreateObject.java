package review.classloader;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/11/19 14:20
 */
public class CreateObject {

    /**
     * 创建对象过程中, 内存分配完成后, 虚拟机必须将分配到的内存空间(不包括对象头)都初始化为默认值.
     * 这步操作保证了对象的实例字段在Java代码中可以不赋初始值就直接使用
     */
    public String aaa;

    public int bbb;
}
