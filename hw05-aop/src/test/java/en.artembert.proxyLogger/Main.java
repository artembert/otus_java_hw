package en.artembert.proxyLogger;

public class Main {
    public static void main(String[] args) {
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.secureAccess("Some params");
    }
}