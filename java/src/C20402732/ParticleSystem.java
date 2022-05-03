package C20402732;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class ParticleSystem {

    ArrayList<Particle> particles;    // An arraylist for all the particles
    PVector origin;                   // An origin point for where particles are birthed
    PImage img;
    PApplet pa;
    public ParticleSystem(int num, PVector v, PImage img_, PApplet pa) {
      particles = new ArrayList<Particle>();              // Initialize the arraylist
      origin = v.copy();                                   // Store the origin point
      img = img_;
      this.pa = pa;
      for (int i = 0; i < num; i++) {
        particles.add(new Particle(origin, img, pa));         // Add "num" amount of particles to the arraylist
      }
    }
  
    void run() {
      for (int i = particles.size()-1; i >= 0; i--) {
        Particle p = particles.get(i);
        p.run();
        if (p.isDead()) {
          particles.remove(i);
        }
      }
    }
  
    // Method to add a force vector to all particles currently in the system
    void applyForce(PVector dir) {
      // Enhanced loop!!!
      for (Particle p : particles) {
        p.applyForce(dir);
      }
    }  
  
    void addParticle() {
      particles.add(new Particle(origin, img, pa));
    }
}