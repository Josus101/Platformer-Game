import java.awt.*;

import components.*;

public class Player {
    // transforms
    Vector2D pos;
    Vector2D vel;
    Vector2D acc;
    Vector2D size;

    Vector2D moveDir;
    double moveSpeed;
    double jumpHeight = 180;

    CollisionBox2D hitBox;
    Boolean isGrounded = false;

    float gravity = GameManager.gravity;
    double terminalVelocity = 2400;


    public Player(Vector2D position, Vector2D size) {
        this.pos = position;
        this.size = size;
        this.vel = new Vector2D();
        this.acc = new Vector2D();
        this.moveDir = new Vector2D();
        this.moveSpeed = 400;

        this.hitBox = new CollisionBox2D(position, size);
    }

    public Player(Vector2D position) {
        this(position, new Vector2D(50, 50));
    }

    public void setPosition(Vector2D newPosition) {
        this.pos = newPosition;
    }

    public void setGrounded(boolean value) {
        this.isGrounded = value;
    }

    public void setGrounded() {
        this.isGrounded = true;
    }

    public Vector2D getPos() {
        return this.pos;
    }

    public Vector2D getVel() {
        return this.vel;
    }

    public Vector2D getSize() {
        return this.size;
    }

    public CollisionBox2D getHitBox() {
        return this.hitBox;
    }

    public void move(Vector2D moveVec) {
        this.pos.add(moveVec);
    }

    public void setVelocity(Vector2D velocity) {
        this.vel = velocity;
    }

    public void update(InputHandler input, double deltaTime) {
        moveDir = new Vector2D(input.horizontal(), input.vertical());

        // calcualte acceleration
        this.acc = new Vector2D(this.acc.getX(), gravity);
        
        // calculate velocity   
        this.vel.add(Vector2D.multiply(acc, deltaTime));
        
        this.vel = (new Vector2D(moveDir.getX() * moveSpeed, this.vel.getY()));

        // jump
        if (input.jump() && this.isGrounded) {
            this.isGrounded = false;
            this.vel = new Vector2D(this.vel.getX(), -Math.sqrt(2*gravity*jumpHeight));
            this.acc = new Vector2D(this.acc.getX(), 0);
        }
        // limit the velocity to the terminalVelocity
        if (this.vel.getY() >= terminalVelocity) this.vel.setY(terminalVelocity);
        
        // calculate position
        this.pos.add(Vector2D.multiply(vel, deltaTime));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(
            (int)(this.pos.getX() - this.size.getX() / 2), 
            (int)(this.pos.getY() - this.size.getY() / 2), 
            (int)this.size.getX(), 
            (int)this.size.getY());
    }
}
