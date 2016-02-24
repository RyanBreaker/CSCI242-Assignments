package rocks.breaker.playground;


public class References {

    private int a = 0;
    private String b = "";


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }


    @Override
    public String toString() {
        return "References{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }


    static void print(References a, References b) {
        System.out.println(a);
        System.out.println(b);
        System.out.println("/----------------/");
    }

    public static void main(String[] args) {
        References a, b;

        a = new References();
        b = new References();

        print(a, b);

        a.setA(1);
        b.setA(a.getA());

        print(a, b);

        b.setB("Foo");
        a.setB(b.getB());

        print(a, b);
    }
}
