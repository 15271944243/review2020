package review.sdf;

import org.junit.Test;
import review.juc.NamedThreadFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * simpleDateFormat是线程不安全的
 * @author: xiaoxiaoxiang
 * @date: 2020/7/14 09:07
 */
public class SdfThreadUnsafeTest {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 定义线程池
     */
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), new NamedThreadFactory("sdftest"),
    new ThreadPoolExecutor.AbortPolicy());

    /**
     * 定义一个CountDownLatch,保证所有子线程执行完之后主线程再执行
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    /**
     * 你会发现SimpleDateFormat打印的个数不是100;
     * 而DateTimeFormatter打印的个数是100;
     * 说明SimpleDateFormat是线程不安全的
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        // 不管是使用静态成员变量还是局部变量,本质上都是一个sdf实例,都会有问题
//         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 定义一个线程安全的HashSet
        Set<String> dateSet = Collections.synchronizedSet(new HashSet<String>());
        Set<String> dateSet2 = Collections.synchronizedSet(new HashSet<String>());
        Set<String> dateSet3 = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            // 获取当前时间
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                // 时间增加
                calendar.add(Calendar.DATE, finalI);
                Date date = calendar.getTime();
                // 通过simpleDateFormat把时间转换成字符串
                String dateString = simpleDateFormat.format(date);
                // 通过dateTimeFormatter把时间转换成字符串
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                String dateString2 = dateTimeFormatter.format(localDateTime);
                // 通过SimpleDateFormatUtil把时间转换成字符串
                String dateString3 = SimpleDateFormatUtil.format(date, "yyyy-MM-dd HH:mm:ss");
                //把字符串放入Set中
                dateSet.add(dateString);
                dateSet2.add(dateString2);
                dateSet3.add(dateString3);
                //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        countDownLatch.await();
        //输出去重后的时间个数
        System.out.println(dateSet.size());
        System.out.println(dateSet2.size());
        System.out.println(dateSet3.size());
    }
}
