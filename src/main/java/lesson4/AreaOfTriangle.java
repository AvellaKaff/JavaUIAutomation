package lesson4;

public class AreaOfTriangle {
    public static double areaOfTriangle(int a, int b, int c) throws Exception {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new Exception("side of the triangle <= 0");
        }
        if ((a >= (b + c)) || (b >= (a + c)) || (c >= (a + b))) {
            throw new Exception("the triangle does not exist");
        }
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
}
