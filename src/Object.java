import components.CollisionBox2D;
import components.Vector2D;
import java.awt.*;

public class Object {
    CollisionBox2D hitBox;
    Vector2D pos;
    Vector2D size;
    String layer;

    public Object(Vector2D position, Vector2D size) {
        this.pos = position;
        this.size = size;
        this.hitBox = new CollisionBox2D(position, size);
    }

    public Object(Vector2D position, double width, double height) {
        this(position, new Vector2D(width, height));
    }

    public Object(double x, double y, Vector2D size) {
        this(new Vector2D(x, y), size);
    }
    
    public Object(double x, double y, double width, double height) {
        this(new Vector2D(x, y), new Vector2D(width, height));
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return this.layer;
    }

    public CollisionBox2D getHitBox() {
        return this.hitBox;
    }

    public Vector2D getPos() {
        return this.pos;
    }

    public Vector2D getSize() {
        return this.size;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(
            (int)(this.pos.getX() - this.size.getX() / 2), 
            (int)(this.pos.getY() - this.size.getY() / 2), 
            (int)this.size.getX(), 
            (int)this.size.getY());
    }
}
