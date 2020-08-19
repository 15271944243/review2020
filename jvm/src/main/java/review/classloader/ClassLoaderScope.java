package review.classloader;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/8/18 22:59
 */
public class ClassLoaderScope {

    public static void main(String[] args) {
        String bootpath = System.getProperty("sun.boot.class.path");
        System.out.println(bootpath.replaceAll(";", System.lineSeparator()));

        System.out.println("-------------");

        String extpath = System.getProperty("java.ext.dirs");
        System.out.println(extpath.replaceAll(";", System.lineSeparator()));

        System.out.println("-------------");

        String apppath = System.getProperty("java.class.path");
        System.out.println(apppath.replaceAll(";", System.lineSeparator()));
    }
}
