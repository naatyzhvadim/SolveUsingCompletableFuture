public class QuadraticEquation {
    private double a, b, c;
    QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Solution getSolution() {
        double d = b * b - 4 * a * c;
        System.out.println("SOLVING");
        /*if (d >= 0) {
            return new Solution((-b + Math.sqrt(d))/(2 * a), (-b - Math.sqrt(d))/(2 * a));
        } else {

        }*/
        return null;
    }

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public void setA(double a) {
        this.a = a;
    }
    public void setB(double b) {
        this.b = b;
    }
    public void setC(double c) {
        this.c = c;
    }
}
