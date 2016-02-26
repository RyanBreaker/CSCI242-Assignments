package rocks.breaker.playground;


public class References {

    public static void main(String[] args) {
        Foo foo = new Foo();


    }
}

class Foo {
    int a = 0;
    String b = "foo";
    Foo foo = new Foo();
}
