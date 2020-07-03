package review.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 破坏单例-序列化
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:42
 */
public class DestroySingleton03 {

    public static void destroyBySerialization() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("test");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {

            Singleton08 singleton1 = Singleton08.getSingleton();
            outputStream.writeObject(singleton1);

            FileInputStream fileInputStream = new FileInputStream(new File("test"));
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            Singleton08 singleton2 = (Singleton08) inputStream.readObject();
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
}
