package review.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 破坏单例-序列化-防止破坏单例-使用枚举实现单例
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:42
 */
public class DestroySingleton04 {

    /**
     * 使用枚举实现单例,但不能进行包装,包装后就能被序列化破坏
     */
    public static void preventSerializationDestroy01() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("test");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {

            Singleton09 singleton1 = Singleton09.getSingleton();
            outputStream.writeObject(singleton1);

            FileInputStream fileInputStream = new FileInputStream(new File("test"));
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            Singleton09 singleton2 = (Singleton09) inputStream.readObject();
            System.out.println(singleton1.hashCode());
            System.out.println(singleton2.hashCode());
            System.out.println(singleton1 == singleton2);

            inputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void preventSerializationDestroy02() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("test");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {

            SingletonEnum singletonEnum1 = SingletonEnum.INSTANCE;
            outputStream.writeObject(singletonEnum1);

            FileInputStream fileInputStream = new FileInputStream(new File("test"));
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            SingletonEnum singletonEnum2 = (SingletonEnum) inputStream.readObject();
            System.out.println(singletonEnum1.hashCode());
            System.out.println(singletonEnum2.hashCode());
            System.out.println(singletonEnum1 == singletonEnum2);

            inputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
