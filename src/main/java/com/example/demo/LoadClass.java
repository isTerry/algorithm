package com.example.demo;

/*
Class对象的生成方式如下：
1.类名.class
说明： JVM将使用类装载器, 将类装入内存(前提是:类还没有装入内存),"不做类的初始化工作".返回Class的对象
2.Class.forName("类名字符串")  （注：类名字符串是包名+类名）
说明：装入类,并做类的静态初始化，返回Class的对象
3.实例对象.getClass()
说明：对类进行静态初始化、非静态初始化；返回引用o运行时真正所指的对象(因为:子对象的引用可能会赋给父对象的引用变量中)所属的类的Class的对象

类加载时机：
先检查是否加载过
只有在第一次获取类对象或者访问类的静态变量、方法，new 实例化对象时加载

类加载过程及双亲委派模型：

则系统会加载，连接，初始化三步：
加载：class文件、网络、获取二进制字节流 加载到虚拟机方法区 创建class对象
连接：验证是否符合虚拟机规范 准备赋值默认初始值（0 false null） 解析符号引用替换为直接引用（内存地址）
初始化：静态变量和块的初始化（父类优先）
上述的流程只是描述了逻辑上各个阶段的开始顺序，实际过程中，各个阶段可能是交错进行，
并不是一个阶段等到另一个阶段完全完成才开始执行。

加载：
加载一个Class需要完成以下3件事：
1通过Class的全限定名获取Class的“二进制字节流”
2将Class的二进制内容加载到虚拟机的“方法区”
3在内存中生成一个java.lang.“Class对象”表示这个Class
获取Class的二进制字节流这个步骤有多种方式：
从文件中读取，如：从jar、war、ear等格式的文件中读取Class文件内容
从网络中获取，如：Applet
动态生成，如：动态代理、ASM框架等都是基于此方式

链接：
包括验证、准备以及解析三个阶段
（1）验证阶段。主要的目的是确保被加载的类（.class文件的字节流）“满足Java虚拟机规范”，不会造成安全错误。
（2）准备阶段。为类的静态成员分配内存，并“设置默认初始值”。如：基本类型初始化为0或false，引用类型初始化为null。
（3）解析阶段。将类的二进制数据中的“符号引用替换为直接引用”。
说明：
符号引用。即一个字符串，但是这个字符串给出了一些能够唯一性识别一个方法，一个变量，一个类的相关信息。
直接引用。可以理解为一个内存地址，或者一个偏移量。
比如类方法，类变量的直接引用是指向方法区的指针；
而实例方法，实例变量的直接引用则是从实例的头指针开始算起到这个实例变量位置的偏移量。
举个例子来说，现在调用方法hello()，这个方法的地址是0xaabbccdd，那么hello就是符号引用，0xaabbccdd就是直接引用。
在解析阶段，虚拟机会把所有的类名，方法名，字段名这些符号引用替换为具体的内存地址或偏移量，也就是直接引用。

初始化
初始化，只对static修饰的变量或语句块进行初始化。
如果初始化一个类的时候，其父类尚未初始化，则优先初始化其父类。
如果同时包含多个静态变量和静态代码块，则按照自上而下的顺序依次执行。

最后还有使用和卸载阶段

java类静态域、块，非静态域、块，构造函数的初始化顺序
父类--静态变量
父类--静态初始化块
子类--静态变量
子类--静态初始化块

父类--变量
父类--初始化块
父类--构造器
子类--变量
子类--初始化块
子类--构造器
 */
public class LoadClass {
    public static void main(String[] args) throws ClassNotFoundException {
        initOrder();
    }

    public static void initOrder() throws ClassNotFoundException {
        //测试.class
        System.out.println("testTypeClass---------------");
        Class testTypeClass=TestClassSon.class;
        System.out.println("testTypeClass---"+testTypeClass);

        //测试Class.forName()
        System.out.println("testTypeForName---------------");
        Class testTypeForName=Class.forName("com.example.demo.TestClassSon");
        System.out.println("testTypeForName---"+testTypeForName);

        //测试Object.getClass()
        System.out.println("testTypeGetClass---------------");
        TestClassSon testTypeGetClass= new TestClassSon();
        System.out.println("testTypeGetClass---"+testTypeGetClass.getClass());

        System.out.println("ClassLoader---"+testTypeGetClass.getClass().getClassLoader());
        System.out.println("ClassLoader---"+testTypeGetClass.getClass().getClassLoader().getParent());
        System.out.println("ClassLoader---"+testTypeGetClass.getClass().getClassLoader().getParent().getParent());
    }
}

class TestClassSon extends TestClassFather{
    private static String sonStaticField = "sonStaticField";
    private  String sonField = "sonField";

    static {
        System.out.println("son static block");
    }

    {
        System.out.println("son block");
    }

    /*
    super是指向父类的引用，如果构造方法没有显示地调用父类的构造方法，
    那么编译器会自动为它加上一个默认的super()方法调用。
    如果父类由没有默认的无参构造方法，编译器就会报错，super()语句必须是构造方法的第一个子句。\

    默认会添加无参的构造函数，但如果自己添加了有参数的构造函数，则不会默认自动添加
     */
    TestClassSon(){
        super();
        System.out.println("son construct");
    }
}

//先静态、先父类、变量-初始化块-构造函数（如下书写顺序）
class TestClassFather{
    private static String fatherStaticField = "fatherStaticField";
    private  String fatherField = "fatherField";

    static {
        System.out.println("father static block");
    }

    {
        System.out.println("father block");
    }

    TestClassFather(){
        this(new Object());
        System.out.println("father construct");
    }

    TestClassFather(Object object){
        System.out.println("father construct with args");
    }
}


/*
常见问题：在自己的项目里新建一个java.lang包，里面新建了一个String类，能代替系统String吗？
不能，因为根据类加载的双亲委派机制，会将请求转发给父类加载器，父类加载器发现冲突了String就不会加载了。

当一个类加载器接收到一个类加载的任务时，不会立即展开加载，而是将加载任务委托给它的父类加载器去执行，
每一层的类都采用相同的方式，直至委托给最顶层的启动类加载器为止。如
果父类加载器无法加载委托给它的类，便将类的加载任务“退回”给下一级类加载器去执行加载。

优点：
能够有效确保一个类的全局唯一性：当程序中出现多个限定名相同的类时，始终只会加载其中的某一个类。
保证安全：Java自带的类随着它的类加载器一起具备了一种带有优先级。
启动类加载器：负责 <JAVA_HOME>/lib下的核心类库
扩展类加载器：负责 <JAVA_HOME >/lib/ext下的类库
应用程序类加载器：负责用户类路径(java -classpath），即当前类所在路径及其引用的第三方类库的路径下的类库
自定义加载器:如果说现在你的"类的加载路径可能是网络、文件"，这个时候就必须实现类加载器。

// First, check if the class has already been loaded是否已经被加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {//交给父加载器的loadClass()方法
                        c = parent.loadClass(name, false);
                    } else {//如果已经到了最顶层加载器，由启动类加载器加载
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.//如果还是没找到，findClass()方法
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    PerfCounter.getFindClasses().increment();
                }
            }

为什么要自定义类加载器？
你的"类的加载路径可能是网络、文件"，这个时候就必须实现类加载器。

如何自定义类加载器，是否打破双亲委派模型？
继承ClassLoader

覆写ClassLoader#loadClass()是非常常见的。
JNDI、OSGi等为了实现各自的需求，也在一定程度上破坏了双亲委派模型。

JDK1.2之后已不再提倡用户再去覆盖loadClass()方法，应当把自己的类加载逻辑写到findClass()方法中，
在loadClass()方法的逻辑里，如果父类加载器加载失败，则会调用自己的findClass()方法来完成加载，
这样就可以保证新写出来的类加载器是符合双亲委派模型的。
 */
class DefineClassLoader extends ClassLoader{
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

}