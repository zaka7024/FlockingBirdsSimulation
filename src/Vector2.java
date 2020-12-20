public class Vector2 implements Comparable<Vector2> {
    public double x, y;
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 a, Vector2 b){
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 subtraction(Vector2 a, Vector2 b){
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static Vector2 divide(Vector2 a, double factor){
        return new Vector2(a.x / factor, a.y / factor);
    }

    public static Vector2 mul(Vector2 a, double factor){
        return new Vector2(a.x * factor, a.y * factor);
    }

    public double magnitude(){
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public int compareTo(Vector2 that) {
        return (this.x == that.y && this.x == that.y) ? 0 : -1;
    }
}
