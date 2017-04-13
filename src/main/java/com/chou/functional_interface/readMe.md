### 函数式接口

1. 函数式接口只能有一个抽象方法
2. 可以有多个默认方法
3. 可以有`java.lang.Object`中的`public`方法,不会增加抽象方法的个数
4. `instances of functional interfaces can be created with lambda expressions, method references, or constructor references`
5. 只有一个抽象方法，并且没有加 `@functionalInterface` 的接口也会被编译器看着是函数式接口


### 采用函数接口

lambada表达式的入口就是函数式接口，例如：
```
    public static void doSomething(Runnable r)
```
方法参数的Runnable类符合函数式接口的定义，可以直接这样写。
**只要方法参数类型符合函数式接口定义，就可以使用lambada表达式或方法引用**
```
    doSomething(() -> System.out.print())
    
    //new Thread(Runnable r );
    new Thread(() -> System.out.println() );
```


利用Lambda表达式带来的灵活性，它们分别是：*有条件的延迟执行*和*环绕执行*

1. **有条件的延迟执行**
    ```
    if (logger.isLoggable(Log.FINER)){
        logger.finer("Problem: " + generateDiagnostic());
    }
    ```
    这段代码有什么问题吗？其实问题不少。
    * 日志器的状态（它支持哪些日志等级）通过isLoggable方法暴露给了客户端代码。
    * 为什么要在每次输出一条日志之前都去查询日志器对象的状态？这只能搞砸你的代码
    
    更好的方案:
    ```
    logger.log(Level.FINER, "Problem: " + generateDiagnostic());
    ```
    这种方式更好的原因是你不再需要在代码中插入那些条件判断，与此同时日志器的状态也不
    再被暴露出去。不过，这段代码依旧存在一个问题。日志消息的输出与否每次都需要判断，即使
    你已经传递了参数，不开启日志。
    
    使用Lambda表达式,**延迟消息构造**:
    ```
    public void log(Level level, Supplier<String> msgSupplier)
    ```
    ```
    logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
    ```
    Log方法的内部实现如下：
    ```
    public void log(Level level, Supplier<String> msgSupplier){
        if(logger.isLoggable(level)){
            log(level, msgSupplier.get());
        }
    }
    ```
    如果你发现你需要频繁地从客户端代码去查询一个对象的状态（比如前文例子中的日志器的状态），
    只是为了传递参数、调用该对象的一个方法（比如输出一条日志），那么可以考虑实现一个新的方法
    **以Lambda或者方法表达式作为参数，新方法在检查完该对象的状态之后才调用原来的方法`generateDiagnostic()`**
    
 2. **环绕执行**   
    如果你发现虽然你的业务代码千差万别，但是它们拥有同样的准备和清理阶段，这时，你完全可以将这部分代码用Lambda
    实现
    ```
    String oneLine =
        processFile((BufferedReader b) -> b.readLine());
    String twoLines =
        processFile((BufferedReader b) -> b.readLine() + b.readLine());

    public static String processFile(BufferedReaderProcessor p) throws IOException {
            try(BufferedReader br = new BufferedReader(new FileReader("java8inaction/
            chap8/data.txt"))){
                return p.process(br);
            }
    }

    public interface BufferedReaderProcessor{
             String process(BufferedReader b) throws IOException;
    }    

    ```
    
    
    