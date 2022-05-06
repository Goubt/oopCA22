package C20402732;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class ParticleSystem {

  ArrayList<Particle> particles; 
  PVector origin; 
  PImage img;
  PApplet pa;

  public ParticleSystem(int num, PVector v, PImage img_, PApplet pa) {
    this.pa = pa;
    
    // Initialize the arraylist
    particles = new ArrayList<Particle>();

    origin = v.copy();
    img = img_;
    for (int i = 0; i < num; i++) {
      particles.add(new Particle(origin, img, pa)); // Add "num" amount of particles to the arraylist
    }
  }

  void run() {
    for (int i = particles.size() - 1; i >= 0; i--) {
      Particle p = particles.get(i);
      p.run();
      if (p.isDead()) {
        particles.remove(i);
      }
    }
  }

  // Applies force which is directed at the mouse
  void applyForce(PVector dir) {
    for (Particle p : particles) {
      p.applyForce(dir);
    }
  }

  // Creates new particle
  void addParticle() {
    particles.add(new Particle(origin, img, pa));
  }
}