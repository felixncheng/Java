# Java
探索Java语法特性，设计模式，第三方框架


## Java Core
- 线程
    1. CountDownLatch和Thread的join方法
       - 相同点：都可以控制主线程等待所有子线程执行完毕。
       - 不同点： CountDownLatch是异步执行，主线程在await方法之前可以继续执行，Thread的join是同步执行，主线程必须等待join的子线程执行完毕。
       - 注意：join方法需在子线程start后才会生效，否则主线程会直接执行。
       - 测试用例: com.chengmboy.core.thread.ThreadResearch  
    2. RejectedExecutionException
       
       线程池添加线程优先级为：（核心线程池）corePoolSize->(当核心线程池满时，申请新线程)maximumPoolSize->（当线程数大于等于最大线程数，则进入队列）BlockingQueue
       
       有以下几种情况会产生该异常：
       1. ThreadPoolExecute shutdown后，继续添加线程。
       2. BlockingQueue已满，并且线程池策略为ThreadPoolExecutor.AbortPolicy(),继续添加线程。 
          
          
## 设计模式

### 单例模式
单例模式实现传统方式有饿加载和懒加载两种，java有个语法特性的实现就是枚举。**传统方式实现的单例对象不能实现Serializable和Cloneable接口，包括父类。**

1. 饿加载
   
   把单例对象定义为静态变量，并直接初始化。
   - 实现原理：类加载只会执行一次。静态变量的初始化在类加载时执行。
   - 优点：简单
   - 缺点：如果该单例对象用的地方不多，浪费存储。GC不会回收。
   - 适用场景：单例对象用的地方非常多。
    
2. 懒加载
    
    把单例对象定义为volatile的静态变量，在需要时加载。
    - 实现原理：利用锁,volatile保证线程安全，double check减少锁次数，提升实例化效率。
    - 优点：需要时加载，降低存储。
    - 缺点：代码复杂
    - 适用场景：单例对象用的地方不多。
3. 枚举

    把单例对象定义为枚举项
    - 实现原理：枚举项的构造函数线程安全。并且构造函数私有。
    - 优点：编写简单。
    - 缺点：不能定义类型参数。
    - 注意：枚举的成员方法非线程安全。