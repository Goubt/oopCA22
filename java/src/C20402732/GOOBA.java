package C20402732;

import ie.assignment.OOP;
import ie.assignment.Visual;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class GOOBA extends OOP{

    static ParticleSystem tlps;
    static ParticleSystem trps;
    static ParticleSystem blps;
    static ParticleSystem brps;

    public GOOBA(OOP oop) {
    }

    public void setup() {
        PImage img = loadImage("images/shrek.png");
        tlps = new ParticleSystem(5, new PVector(0, 0), img, this);
        trps = new ParticleSystem(5, new PVector(0, width), img, this);
        blps = new ParticleSystem(5, new PVector(height, 0), img, this);
        brps = new ParticleSystem(5, new PVector(height, width), img, this);
    }

    public static void render() {
        clear();
            
            float dx = map(mouseX, 0, width, (float)-0.5, (float)0.5);
                PVector wind = new PVector(dx, 0);
                tlps.applyForce(wind);
                trps.applyForce(wind);
                blps.applyForce(wind);
                brps.applyForce(wind);
                tlps.run();
                trps.run();
                blps.run();
                brps.run();
                if(frameCount % 20 < 2){
                    tlps.addParticle();
                    trps.addParticle();
                    blps.addParticle();
                    brps.addParticle();
                }
    }
}
