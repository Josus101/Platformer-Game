package components;

public class CollisionBox2D {
    Vector2D pos;
    Vector2D size;
    
    public CollisionBox2D(Vector2D position, Vector2D size) {
        this.pos = position;
        this.size = size;
    }

    public boolean isColliding(CollisionBox2D other) {
        // Calculate the difference in position between the two objects
        Vector2D positionDifference = Vector2D.subtract(this.pos, other.getPos());

        // Check for overlap in the Y-axis (height)
        boolean heightCheck = Math.abs(positionDifference.getY()) <= (this.getSize().getY() + other.getSize().getY()) / 2;

        // Check for overlap in the X-axis (width)
        boolean widthCheck = Math.abs(positionDifference.getX()) <= (this.getSize().getX() + other.getSize().getX()) / 2;

        // If both height and width overlap, there is a collision
        return heightCheck && widthCheck;
    }

    public Vector2D getSize() {
        return size;
    }

    public Vector2D getPos() {
        return pos;
    }

    public double above() {
        return this.pos.getY() - this.size.getY() / 2;
    }

    public double below() {
        return this.pos.getY() + this.size.getY() / 2;
    }

    public double left() {
        return this.pos.getX() - this.size.getX() / 2;
    }

    public double rigtht() {
        return this.pos.getX() + this.size.getX() / 2;
    }

}
