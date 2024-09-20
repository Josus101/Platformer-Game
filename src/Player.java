import java.awt.*;

import components.*;

public class Player {
    // transforms
    Vector2D pos;
    Vector2D vel;
    Vector2D acc;
    Vector2D size;

    Vector2D moveDir;
    double walkSpeed = 400;
    double moveSpeed;

    double jumpScale = 0;
    double jumpScaleChange = 0.15;
    double jumpHeight = 180;

    Vector2D facingDir;


    double dashSpeed;
    double dashCoolDown;
    double dashDuration;
    double dashCooldownTimer = 0;
    double dashDurationTimer = 0;


    CollisionBox2D hitBox;
    CollisionBox2D groundCheckBox;
    Boolean isGrounded = false;

    float gravity = GameManager.gravity;
    double terminalVelocity = 2400;

    


    public Player(Vector2D position, Vector2D size) {
        this.pos = position;
        this.size = size;
        this.vel = new Vector2D();
        this.acc = new Vector2D();
        this.moveDir = new Vector2D();
        this.moveSpeed = walkSpeed;
        

        this.dashCoolDown = 1;
        this.dashDuration = .2;
        this.dashSpeed = 1200;


        this.facingDir = new Vector2D(1, 0);

        this.hitBox = new CollisionBox2D(position, size);
        this.groundCheckBox = new CollisionBox2D(
                Vector2D.add(
                    position, 
                    new Vector2D(0, this.size.getY()/2 - 10)), 
                new Vector2D(this.size.getX(), 20));
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

    public CollisionBox2D getGroundCheckBox() {
        return this.groundCheckBox;
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
        if (input.jump()){
            jumpScale += jumpScaleChange;
            if (jumpScale > 1) {
                jumpScale = 1;
                this.isGrounded = false;
            }
            if (this.isGrounded) {
                System.out.println(jumpScale);
                this.vel = new Vector2D(this.vel.getX(), -Math.sqrt(2 * gravity * (jumpHeight) * jumpScale));
                this.acc = new Vector2D(this.acc.getX(), 0);
            }
        } else {
            jumpScale = 0;
            this.isGrounded = false;
        }
        // dash
        if (input.dash() && dashCooldownTimer == 0) {
            this.dashCooldownTimer = this.dashCoolDown;
            this.dashDurationTimer = this.dashDuration;
            this.moveSpeed = dashSpeed;

        } else {
            dashCooldownTimer -= deltaTime;
            if (dashCooldownTimer < 0) dashCooldownTimer = 0;
        }

        if (dashDurationTimer > 0) {
            dashDurationTimer -= deltaTime;
            this.vel = new Vector2D(Math.abs(this.vel.getX())*facingDir.getX(), 0);
        } else {
            dashDurationTimer = 0;
            this.moveSpeed = walkSpeed;
            if (moveDir.getX() != 0) {
                facingDir.setX(moveDir.getX());
            }
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
