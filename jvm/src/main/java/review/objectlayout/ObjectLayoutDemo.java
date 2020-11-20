package review.objectlayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * 使用jol工具包查看对象布局
 * 参考: https://cloud.tencent.com/developer/article/1658707
 * @author: xiaoxiaoxiang
 * @date: 2020/11/19 17:46
 */
public class ObjectLayoutDemo {
    /**
     * jdk8版本是默认开启指针压缩的 -XX:-UseCompressedOops
     *
     * OFFSET：偏移地址，单位字节；
     * SIZE：占用的内存大小，单位为字节；
     * TYPE DESCRIPTION：类型描述，其中object header为对象头；
     * VALUE：对应内存中当前存储的值 (注意Little-Endian和Big-Endian, 这里是Little-Endian)
     *
     * 不开启压缩指针的情况下,对象头占16个字节,由于本机是64位机器,正好是64bit的markword和64bit的类型指针(Klass Pointer),共16字节
     * 开启压缩指针情况下,对象头占12个字节,由于本机是64位机器,markword占64bit,而类型指针被压缩,占32bit(compressed klass pointer)
     */
    public static void main(String[] args) {
        User user = new User();
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }
}
