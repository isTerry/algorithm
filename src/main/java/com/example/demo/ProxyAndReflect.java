package com.example.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
代理模式，提供了对目标对象的“间接访问”方式，即“通过代理访问目标对象”。
在目标实现的基础上增加额外的功能操作，前拦截，后拦截等，扩展目标对象功能
“JDK动态代理目标对象必须实现接口，因为生成代理对象继承了Proxy类，而Java是单继承”
 */

/*
如果不知道某个对象的确切类型，RTTI可以告诉你，但有一个前提：这个类型在编译时必须已知，这样才能用RTTI来识别它。
 Run-Time Type Identification
反射机制并没有什么神奇之处，当通过反射与一个未知类型的对象打交道时，JVM只是简单地检查这个对象，
看它属于哪个特定的类。因此，“那个类的.class对于JVM来说必须是可获取的”，要么在本地机器上，要么从网络获取。
所以对于RTTI和反射之间的真正区别只在于：
RTTI，编译器在“编译时”打开和检查.class文件
反射，“运行时”打开和检查.class文件

对象信息可以在“运行时”被完全确定下来，而在编译时不需要知道关于类的任何事情。
在运行状态中，对于任意一个对象，都能调用它的任意一个方法和属性。
反射就是把Java类中的各个成分映射成一个个的Java对象。
Class类：代表一个类。 Field类：代表类的成员变量（成员变量也称为类的属性）。
Method类：代表类的方法。
Constructor类：代表类的构造方法。
Java反射机制主要提供了以下功能：
在运行时构造任意一个类的对象。
在运行时判断任意一个类所具有的成员变量和方法。
在运行时判断任意一个对象所属的类。
在运行时调用任意一个对象的方法。
生成动态代理。
 */

public class ProxyAndReflect {
    public static void main(String[] args){

    }

    /*
    方法是用来创建一个和参数objcet同样类型的对象，
    然后把object对象中的所有属性拷贝到新建的对象中，并将其返回。


而Class类是Reflection API中的核心类，主要方法如下：
getName()：获得类的完整名字。
getFields()：获得类的"public类型"的属性。
getDeclaredFields()：Declared获得类的所有属性。
getMethods()：获得类的public类型的方法。
getDeclaredMethods()：获得类的所有方法。
getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes参数指定方法的参数类型。
getConstrutors()：获得类的public类型的构造方法。
getConstrutor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes参数指定构造方法的参数类型。
newInstance()：通过类的不带参数的构造方法创建这个类的一个对象。
     */
    public static Object testReflectCopy(Object object) throws Exception{
        //获得对象的类型
        Class classType=object.getClass();
        System.out.println("Class:"+classType.getName());

        //通过默认构造方法创建一个新的对象
        Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

        //获得对象的所有属性
        Field fields[]=classType.getDeclaredFields();

        for(int i=0; i<fields.length;i++){
            Field field=fields[i];

            String fieldName=field.getName();
            String firstLetter=fieldName.substring(0,1).toUpperCase();
            //获得和属性对应的getXXX()方法的名字
            String getMethodName="get"+firstLetter+fieldName.substring(1);
            //获得和属性对应的setXXX()方法的名字
            String setMethodName="set"+firstLetter+fieldName.substring(1);

            //获得和属性对应的getXXX()方法
            Method getMethod=classType.getMethod(getMethodName,new Class[]{});
            //获得和属性对应的setXXX()方法
            Method setMethod=classType.getMethod(setMethodName,new Class[]{field.getType()});

            //调用原对象的getXXX()方法
            Object value=getMethod.invoke(object,new Object[]{});
            System.out.println(fieldName+":"+value);
            //调用拷贝对象的setXXX()方法
            setMethod.invoke(objectCopy,new Object[]{value});
        }
        return objectCopy;
    }
}



/**
 * 目标对象实现的接口
 */
interface BussinessInterface {
    void execute();
}

interface RelaxInterface {
    void relax();
}

/**
 * 目标对象实现类
 * @author jiyukai
 */
class People implements BussinessInterface,RelaxInterface{
    @Override
    public void execute() {
        System.out.println("执行业务逻辑...");
    }

    @Override
    public void relax() {
        System.out.println("下班...");
    }

    public void eat(){
        System.out.println("吃饭...");
    }
}

/*
静态代理的总结
优点：不“对目标对象进行修改”的前提下，对目标对象进行功能的扩展和拦截。
缺点：因为代理对象，需要实现与目标对象一样的接口，会导致代理类十分繁多，
同时一旦接口增加方法，则目标对象和代理类都“不易维护”。
 */
/**
 * 代理类，通过实现与目标对象相同的接口
 * "并维护一个代理对象"，通过构造器传入实际目标对象并赋值
 * 执行代理对象实现的接口方法，实现对目标对象实现的干预
 * @author jiyukai
 */
class PeopleStaticProxy implements BussinessInterface,RelaxInterface{

    private People people;

    public PeopleStaticProxy(People people) {
        this.people = people;
    }

    @Override
    public void execute() {
        System.out.println("前拦截...");
        people.execute();
        System.out.println("后拦截...");
    }

    @Override
    public void relax() {
        System.out.println("前拦截...");
        people.relax();
        System.out.println("后拦截...");
    }
}

/*
动态代理是指动态的在内存中构建代理对象（需要我们指定要代理的目标对象实现的接口类型），
即利用JDK的API“动态生成”指定接口的代理对象，也称之为JDK代理或者接口代理。
优点：无需手动编写代理类，和在接口增加方法时维护代理对象，
“只需在事件处理器中添加对方法的判断即可”。
缺点：“目标对象一定要实现接口”，否则无法使用JDK动态代理。
 */
/**
 * 通过传入任何类型的目标对象，指定同样的类加载器、接口、创建InvocationHandler
 * “每一个代理类对象都有一个事件处理器关联，调用代理类的方法，实际是调用事件处理器的invoke()方法进行拦截”
 */
class DynamicProxyFactory {

    //这里持有目标对象实例的引用，可能有多个接口
    private People targetObject;

    public DynamicProxyFactory(People targetObject) {
        this.targetObject = targetObject;
    }

    public static void main(String[] args){
        //不能cast为目标对象类的类型，只能cast为接口的类型，因为实现了所有接口，继承的是Proxy而非目标对象的类
//      Bussiness bussinessProxy = (Bussiness) new ProxyFactory(new Bussiness()).getProxyInstance();
        BussinessInterface bussinessProxy = (BussinessInterface) new DynamicProxyFactory(new People()).getProxyInstance();
        System.out.println(bussinessProxy.getClass());
        bussinessProxy.execute();

        RelaxInterface relaxProxy = (RelaxInterface)bussinessProxy;
        System.out.println(relaxProxy.getClass());
        relaxProxy.relax();
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                //和目标对象的类加载器保持一致
                targetObject.getClass().getClassLoader(),
                //目标对象实现的接口，因为需要根据接口动态生成对象
                targetObject.getClass().getInterfaces(),
                //InvocationHandler:事件处理器，即对目标对象方法的执行
                new InvocationHandler() {

                    //Object proxy:代理类实例
                    //Method method:要调用的方法
                    //Object[] args:方法调用时所需要参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("前拦截...");

                        //obj – the object the underlying method is invoked from
                        //不能调用proxy的方法，否则相当于循环调用自己
//                        Object result = method.invoke(proxy, args);
                        //这里调用目标对象的方法
                        Object result = method.invoke(targetObject, args);

                        System.out.println("后拦截...");
                        return result;
                    }
                });
    }
}