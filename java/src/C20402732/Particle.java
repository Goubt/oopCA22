package C20402732;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class Particle extends PApplet{
    PVector loc;
    PVector vel;
    PVector acc;
    float lifespan;
    PImage img;
    PApplet pa;
    Particle(PVector l, PImage img_, PApplet pa) {
      acc = new PVector(0, 0);
      this.pa = pa;
      float vx = pa.randomGaussian()*1.0f;
      float vy = pa.randomGaussian()*0.3f-4.0f;
      if (l.x != 0) {
        vx = -vx; 
      }
      if (l.y == 0){
        vy = -vy;
      }  
      vel = new PVector(vx, vy);
      loc = l.copy();
      lifespan = (float)100.0;
      img = img_;
    }
  
    void run() {
      update();
      render();
    }
  
    // Method to apply a force vector to the Particle object
    // Note we are ignoring "mass" here
    void applyForce(PVector f) {
      acc.add(f);
    }  
  
    // Method to update position
    void update() {
      vel.add(acc);
      loc.add(vel);
      lifespan -= 0.5;
      acc.mult(0); // clear Acceleration
    }
  
    // Method to display
    void render() {
      pa.imageMode(PApplet.CENTER);
      pa.tint(255, lifespan);
      pa.image(img, loc.x, loc.y);
    }
  
    // Is the particle still useful?
    boolean isDead() {
      if (lifespan <= 0.0) {
        return true;
      } else {
        return false;
      }
    }
  }