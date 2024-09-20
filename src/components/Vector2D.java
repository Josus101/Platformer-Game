package components;

public class Vector2D {
    private double x;
    private double y;

    /*
     * constructor with x and y as parameter
     */
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    /*
     * constructor with no parameter
     */
    public Vector2D() {
        this(0, 0);
    }

    // methods

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public void add(Vector2D other) {
        this.x += other.getX();
        this.y += other.getY();
    }

    public static Vector2D add(Vector2D vec1, Vector2D vec2) {
        double x = vec1.getX() + vec2.getX();
        double y = vec1.getY() + vec2.getY();
        return new Vector2D(x, y);
    }

    public void subtract(Vector2D other) {
        this.x = other.getX() - this.x;
        this.y = other.getY() - other.y;
    }

    public static Vector2D subtract(Vector2D vec1, Vector2D vec2) {
        double x = vec2.getX() - vec1.getX();
        double y = vec2.getY() - vec1.getY();
        return new Vector2D(x, y);
    }


    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public static Vector2D multiply(Vector2D vec1, double scalar) {
        double x = vec1.getX() * scalar;
        double y = vec1.getY() * scalar;
        return new Vector2D(x, y);
    }


    public void divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    public static Vector2D divide(Vector2D vec1, Vector2D vec2) {
        double x = vec1.getX() / vec2.getX();
        double y = vec1.getY() / vec2.getY();
        return new Vector2D(x, y);
    }


    public void normalize() {
        this.x /= length();
        this.y /= length();
    }

    public static Vector2D normalize(Vector2D vec) {
        double x = vec.getX() / vec.length();
        double y = vec.getY() / vec.length();
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        String out = "x: " + this.getX() + " y: " + this.getY();
        return out;
    }
}
