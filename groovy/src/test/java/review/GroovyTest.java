package review;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/27 16:15
 */
@Slf4j
public class GroovyTest {

    /**
     * https://developer.aliyun.com/article/2357
     * https://www.jianshu.com/p/e8dec95c4326
     */

    /**
     * java 执行groovy 表达式
     *
     * GroovyShell允许在Java类中（甚至Groovy类）求任意Groovy表达式的值
     * 您可使用Binding对象输入参数给表达式,并最终通过GroovyShell返回Groovy表达式的计算结果
     *
     * @throws IOException
     */
    @Test
    public void groovyTest01() throws IOException {
        GroovyShell groovyShell = new GroovyShell();
        Object result = groovyShell.evaluate("println 'My First Groovy shell.'; return 'sdf23324' ");
        log.info(result.toString());
    }

    /**
     * java 执行groovy 脚本
     * @throws IOException
     */
    @Test
    public void groovyTest02() throws IOException {
        // 使用Binding类来绑定参数
        Binding binding = new Binding();
        binding.setProperty("name", "xiaoxx");

        GroovyShell groovyShell = new GroovyShell(binding);
        Object result = groovyShell.evaluate(new File("src/main/java/review/groovy/HelloWorld.groovy"));
        log.info(result.toString());
    }

    /**
     * 通过GroovyClassLoader动态加载Groovy Class
     *
     * 用 Groovy 的 GroovyClassLoader,动态地加载一个脚本并执行它的行为
     * GroovyClassLoader是一个定制的类装载器,负责解释加载Java类中用到的Groovy类
     *
     * @throws IOException
     */
    @Test
    public void groovyTest03() throws IOException {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");

        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);

        // 过GroovyClassLoader加载HelloWorldClass，并反射调用其sayHello(String name, String sex, int age)方法
        File groovyFile = new File("src/main/java/review/groovy/HelloWorldClass.groovy");

        String result = null;
        try {
            // 获得HelloWorldClass加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
            // 获得GroovyShell_2的实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用sayHello方法得到返回值
            Object methodResult = groovyObject.invokeMethod("sayHello", new Object[] {"xiaoxx", "male", 28});
            if (methodResult != null) {
                result = methodResult.toString();
            }
        } catch (Exception e) {
            log.warn("加载groovy类失败", e);
        }
        log.info(result);
    }


    /**
     * GroovyShell多用于推求对立的脚本或表达式,如果换成相互关联的多个脚本,使用GroovyScriptEngine会更好些
     * GroovyScriptEngine从指定的位置(文件系统、URL、数据库、等等)加载Groovy脚本,并且随着脚本变化而重新加载它们
     * 如同GroovyShell一样,GroovyScriptEngine也允许您传入参数值,并能返回脚本的值
     */
    @Test
    public void groovyTest04() throws IOException {
        GroovyScriptEngine scriptEngine = new GroovyScriptEngine("src/test/java/review/script");
        Binding binding = new Binding();
        binding.setVariable("name", "xiao");
        binding.setVariable("age", 28);
        int i = 10;
        while (i > 0){
            i--;
            try {
                // 不指定方法名,直接调用整个脚本
                // 可以在执行过程中修改脚本,不需要重启服务
                scriptEngine.run("HelloWorld.groovy", binding);
                TimeUnit.SECONDS.sleep(5);
                scriptEngine.run("HelloWorld2.groovy", binding);
            } catch (ResourceException e) {
                e.printStackTrace();
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GroovyScriptEngine调用脚本指定方法
     * @throws IOException
     */
    @Test
    public void groovyTest05() throws IOException {
        try {
            GroovyScriptEngine scriptEngine = new GroovyScriptEngine("src/test/java/review/script");
            GroovyObject groovyObject = (GroovyObject) scriptEngine.loadScriptByName("HelloWorld2.groovy").newInstance();
            Object[] param = new Object[]{"xiao", 28};
            Object result = groovyObject.invokeMethod("sayHello", param);
            log.info(result.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}
