public class IntegerDemo
{
    public static void main(String [] args)
    {
        int x = 250;
        System.out.println("x is " + x);
        short a, b, c;
        c = 21;
        b = 9;
        a = (short) (b + c);
        System.out.println("a is " + a);
        long y = 12345678987654321L;
        System.out.println("y is " + y);
        y = x;
        byte s;
        s = (byte) c;
        System.out.println("y is now " + y + " and s is " + s);
    }
}
