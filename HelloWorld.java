package Controller;

public class HelloWorld {
    public String name;

    public HelloWorld(){
        System.out.println("创建helloworld对象成功!");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void hello(){
        System.out.println("hello , " + name);
    }
}
