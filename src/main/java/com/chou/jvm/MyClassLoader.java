package com.chou.jvm;


import java.io.*;

public class MyClassLoader extends ClassLoader{

    private final String SUFFIX = ".class";

    private String name;

    public String path = "/Users/liuchou/Documents/intelliJ_Workspace/jkd8/out/production/classes/";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.name = classLoaderName;
    }

    public MyClassLoader(String classLoaderName) {
        this.name = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        System.out.println("invoke findClass, args is : " + name);

        byte[] bytes = loadClassData(name);

        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadClassData(String name) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();

        byte [] result = null;
        try {
            String pathName = path + name;
            String pathString = pathName.replace(".", "/");
            fileInputStream = new FileInputStream(new File(pathString + SUFFIX));
            int ch ;
            while ( -1 !=  (ch = fileInputStream.read()) ){
                byteArrayOutputStream.write(ch);
            }

            result =  byteArrayOutputStream.toByteArray();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //注意loadClass的加载顺序，查看java.lang.ClassLoader.loadClass(java.lang.String, boolean)文档
        test1();
        test2();

        //因为应用类加载器会加载ClassPath路径下的class,
        // 再执行test3前，删掉classPath下test3方法要加载的class文件，
        // 会触发自定义类加载器到桌面上去加载
        test3();


    }

    private static void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader");
        myClassLoader.setPath("/Users/liuchou/Desktop/");
        Class<?> cClass = myClassLoader.loadClass("com.chou.jvm.NoModiferClass");
        System.out.println("cClass 的类加载器" +cClass.getClassLoader());

        /*
          当自定义类加载器，加载的类没有访问修饰符，在调用class.newInstance会抛出异常：
          Exception in thread "main" java.lang.IllegalAccessException:
                        Class com.chou.jvm.MyClassLoader can not access a member of class com.chou.jvm.NoModiferClass with modifiers ""
          因为： com.chou.jvm.MyClassLoader 和 om.chou.jvm.NoModiferClass 不是同一个run-time package。
          com.chou.jvm.MyClassLoader由应用类加载器加载的，com.chou.jvm.NoModiferClass则是由自定义类加载器MyClassLoader加载的。两者的run-time package 不一样.

          A class or interface C is accessible to a class or interface D if and only if either of the following conditions is true:
            C is public.
            C and D are members of the same run-time package (§5.3).

          但是应用类加载器，加载的类没有访问修饰符不会抛出异常。
        */
        Object c = cClass.newInstance();
        /*
        资料：
        https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms-5.3

         At run time, a class or interface is determined not by its name alone,
         but by a pair: its binary name  and its defining class loader.
         Each such class or interface belongs to a single run-time package.
         The run-time package of a class or interface is determined by the package name and defining class loader of the class or interface.
        */

        System.out.println(c);
        System.out.println();
    }

    private static void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //父类加载器默认是应用类加载器,loadClass方法会调用应用类加载器的findClass
        MyClassLoader hasDefaultClassLoaderParent = new MyClassLoader("hasDefaultClassLoaderParent");
        Class<?> bClass = hasDefaultClassLoaderParent.loadClass("com.chou.jvm.MyTest");
        System.out.println("bClass的类加载器： " + bClass.getClassLoader());
        Object b = bClass.newInstance();
        System.out.println(b);
        System.out.println();
    }

    private static void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //没有父加载器，loadClass方法会调用自定义加载去的findClass
        MyClassLoader nullParentClassLoader = new MyClassLoader(null,"nullParentClassLoader");
        Class<?>  aClass = nullParentClassLoader.loadClass("com.chou.jvm.ClassLoadTest");
        System.out.println("aClass的类加载器： " + aClass.getClassLoader());
        Object a = aClass.newInstance();
        System.out.println(a);
        System.out.println();
    }


}
