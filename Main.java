import Controller.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1.创建spring ioc容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        // 2.从ioc容器中获取bean实例
        HelloWorld helloWorld = (HelloWorld) ac.getBean("HelloWorld");
        helloWorld.hello();
    }
}
