### Streams differ from collections in several ways:
* **No storage**. A stream is not a data structure that stores elements; instead, it conveys elements from a source such as a data structure, an array, a generator function, or an I/O channel, through a pipeline of computational operations.
* **Functional in nature**. An operation on a stream produces a result, but does not modify its source. For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements, rather than removing elements from the source collection.
* **Laziness-seeking**. Many stream operations, such as filtering, mapping, or duplicate removal, can be implemented lazily, exposing opportunities for optimization. For example, "find the first String with three consecutive vowels" need not examine all the input strings. Stream operations are divided into intermediate (Stream-producing) operations and terminal (value- or side-effect-producing) operations. Intermediate operations are always lazy.
* **Possibly unbounded**. While collections have a finite size, streams need not. Short-circuiting operations such as `limit(n)` or `findFirst()` can allow computations on infinite streams to complete in finite time.
* **Consumable**. The elements of a stream are only visited once during the life of a stream. Like an `Iterator`, a new stream must be generated to revisit the same elements of the source.


### Streams can be obtained in a number of ways. Some examples include:

* From a [Collection](http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) via the `stream()` and `parallelStream()` methods;
* From an array via [`Arrays.stream(Object[])`](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#stream-T:A-);
* From static factory methods on the stream classes, such as [`Stream.of(Object[])`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#of-T...-), [IntStream.range(int, int)](http://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#range-int-int-) or [Stream.iterate(Object, UnaryOperator)](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#iterate-T-java.util.function.UnaryOperator-);
* The lines of a file can be obtained from [BufferedReader.lines()](http://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html#lines--);
* Streams of file paths can be obtained from methods in [Files](http://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html);
* Streams of random numbers can be obtained from [Random.ints()](http://docs.oracle.com/javase/8/docs/api/java/util/Random.html#ints--);
* Numerous other stream-bearing methods in the JDK, including [BitSet.stream()](http://docs.oracle.com/javase/8/docs/api/java/util/BitSet.html#stream--), [Pattern.splitAsStream(java.lang.CharSequence)](http://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#splitAsStream-java.lang.CharSequence-), and [JarFile.stream()](http://docs.oracle.com/javase/8/docs/api/java/util/jar/JarFile.html#stream--).

### Stream operations and pipelines

Intermediate operations 

    Intermediate operations return a new stream.They are always lazy;
    executing an intermediate operation such as filter() does not actually perform any filtering,
    but instead creates a new stream that, when traversed, contains the elements of the initial stream that match the given predicate. 
    Traversal of the pipeline source does not begin until the terminal operation of the pipeline is executed.

terminal operation
    
    After the terminal operation is performed, the stream pipeline is considered consumed, and can no longer be used;
    if you need to traverse the same data source again, you must return to the data source to get a new stream.

### Target Typing ( the type of a lambda expression)

 [Target Types](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html)

 [the type of a lambda expression](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing)

 The data type that these methods expect is called the target type. To determine the type of a lambda expression, the Java compiler uses the target type of the context or situation in which the lambda expression was found. It follows that you can only use lambda expressions in situations in which the Java compiler can determine a target type:

* Variable declarations
* Assignments
* Return statements
* Array initializers
* Method or constructor arguments
* Lambda expression bodies
* Conditional expressions, ?:
* Cast expressions
