package C20402732;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class Particle extends PApplet {
  // Initialising Pvectors
  PVector loc;
  PVector vel;
  PVector acc;
  PImage img;
  PApplet pa;

  float lifespan;

  Particle(PVector l, PImage img_, PApplet pa) {
    this.pa = pa;

    acc = new PVector(0, 0);

    float vx = pa.randomGaussian() * 1.7f;
    float vy = pa.randomGaussian() * 0.3f - 4.0f;
    if (l.x > 0) {
      vx = -vx;
    }
    if (l.y < 0) {
      vy = -vy;
    }
    vel = new PVector(vx, vy);

    loc = l.copy();
    lifespan = (float) 100.0;
    img = img_;
  }

  void run() {
    update();
    pa.pushMatrix();
    pa.translate(0, 0, -200);
    render();
    pa.popMatrix();
  }

  // Moving the Particle in the direction of the mouse
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

  // Method to display particle
  void render() {
    pa.imageMode(PApplet.CENTER);
    
    pa.tint(255, lifespan);
    pa.image(img, loc.x, loc.y);
  }

  // Checking if the particle has run out its lifespan
  boolean isDead() {
    if (lifespan <= 0.0) {
      return true;
    } else {
      return false;
    }
  }
}