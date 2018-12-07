public class SingleObject {

    /**
     * 懒汉式: 线程不安全
     */
    public static class Singleton1 {
        private static Singleton1 instance = null;
        private Singleton1(){}

        public static Singleton1 getInstance(){
            if(instance == null){
                instance = new Singleton1();
            }
            return instance;
        }
    }

    /**
     * 懒汉式: 线程安全
     */
    public static class Singleton2 {
        private static Singleton2 instance = null;
        private Singleton2(){}

        public static synchronized Singleton2 getInstance() {
            if(instance == null){
                instance = new Singleton2();
            }
            return instance;
        }
    }

    /**
     * 饿汉式: 线程安全
     */
    public static class Singleton3 {
        private static Singleton3 instance = new Singleton3();
        private Singleton3(){}

        public static Singleton3 getInstance(){
            return instance;
        }
    }

    /**
     * 双重检查锁: 注意准备相关问题，为什么要volatile, 为什么要双重检查
     */
    public static class Singleton4 {
        private static volatile Singleton4 instance = new Singleton4();
        private Singleton4(){}

        public static Singleton4 getInstance(){
            if(instance == null){
                synchronized (Singleton4.class){
                    if(instance == null){
                        instance = new Singleton4();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 静态内部类法
     */
    public static class Singleton5 {
        private static class InnerClass{
            private static Singleton5 instance = new Singleton5();
        }
        private Singleton5(){}

        public static Singleton5 getInstance(){
            return InnerClass.instance;
        }
    }

    /**
     * 枚举法: 注意枚举法对序列化的优点
     */
    public enum Singleton6 {
        INSTANCE;
    }





}
