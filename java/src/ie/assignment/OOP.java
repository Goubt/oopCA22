package ie.assignment;

import java.util.ArrayList;
import java.util.Random;

import damkjer.ocd.*;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class OOP extends PApplet
{
    float rotation = 0;
	float direction = 0;
	Camera camera1;
    Camera camera2;
    
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;
    BeatDetect beat;

    ParticleSystem tlps;
    ParticleSystem trps;
    ParticleSystem blps;
    ParticleSystem brps;

    int mode = 0;

    static final int FADE = 2500;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    int Choice = 4;
    int menu = 0;
    String[] Songs = {"POISON.mp3", "GUMMY.mp3", "DARE.mp3"};
    
    int start = 90;

    int screenBrightness = 0;

    public void keyPressed() {
        if (keyCode == LEFT && direction == 0) {
            direction -= 120;

            if(menu == 0) 
                menu = 2;

            else
                menu--;
            
            print(menu);

            loadMusic(Songs[menu]);
        }

        if (keyCode == RIGHT && direction == 0) {
            direction += 120;

            if(menu == 2)
                menu = 0;

            else
                menu++;

            print(menu);

            loadMusic(Songs[menu]);
        }
        if (key == ENTER) {
            
            Choice = menu;
            
        }

        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
        }

    public void settings()
    {
        size(1200, 1000, P3D);  
        //fullScreen();
    }

    public void setup()
    {
        minim = new Minim(this); 
        
        ap = minim.loadFile("POISON.mp3", 1024);
        ab = ap.mix;
        ap.play();
        
        
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

        beat = new BeatDetect(ap.bufferSize(), ap.sampleRate());
        beat.setSensitivity(300);


        camera1 = new Camera(this, 
							 width/2, height/2, 0,
							 width/2, height/2, -width,
							 0, 1, 0);

        PImage img = loadImage("images/shrek.png");
        tlps = new ParticleSystem(5, new PVector(0, 0), img, this);
        trps = new ParticleSystem(5, new PVector(0, width), img, this);
        blps = new ParticleSystem(5, new PVector(height, 0), img, this);
        brps = new ParticleSystem(5, new PVector(height, width), img, this);


    }

    float off = 0;

    public void draw()
    {
        switch (Choice) {
            case 4:
                textSize(100);
                textAlign(CENTER);
                background(0);
                camera1.feed();
                rectMode(CENTER);
                double third = width * 0.866;
                float move = (float)third;
                
                translate(width/2, height/2, -width);
                gooba();
                translate(move, 0, width+(width/2));
                rotateY(-2*PI/3);
                yaris();
                rotateY(2*PI/3);
                translate(-2*move, 0, 0);
                rotateY(2*PI/3);
                finn();


                hint(DISABLE_DEPTH_TEST); // 2D code starts here
                camera();
                noLights();
                if(frameCount % 60 < 30 && direction == 0) {
                    text("<Enter>", width/2, (height)-50);
                }

                hint(ENABLE_DEPTH_TEST); // 2D code ends here
                
                if (direction > 0) {
                    RotateRight();
                    direction--;
                }
                if (direction < 0) {
                    RotateLeft();
                    direction++;
                }
                
                break;
        
            case 0: // Gooba
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
                if(frameCount % 20 < 2 && direction == 0){
                    tlps.addParticle();
                    trps.addParticle();
                    blps.addParticle();
                    brps.addParticle();
                }
            
            break;

            case 1: // Yaris
            print("Yaris");
            break;

            case 2: // Finn
            print("Finn");
            break;
        }
        
    }
    
    public void start(){
        
	}
    
    public void yaris(){
        
		stroke(255,0,0);
		noFill();
		box(100);
        rect(0,0,width-100,height-100); 
	}

	public void finn(){
        
		stroke(0,255,0);
        noFill();
        box(100);
        rect(0,0,width-100,height-100);
	}

	public void gooba(){
		stroke(0,0,255);
		noFill();
		box(100);
        rect(0,0,width-100,height-100);
         
	}

    public void loadMusic(String Song) {
        ap.shiftGain(0, -50, FADE);
        ap = minim.loadFile(Song, 1024);
        ap.shiftGain(-50, 0, FADE);
        ab = ap.mix;
        ap.play();
    }

    public int backgroundBeat(Boolean type, int i) {

        if (type == true)
            i += 20;

        return i;
    }

    public void RotateRight(){
		camera1.look((float) (radians((float) (1))), 0);
	}

	public void RotateLeft(){
		camera1.look(-(float) (radians((float) (1))), 0);
	}
}

class BeatDetection {

    public Boolean readBeat(BeatDetect beat) {

        if (beat.isKick())
            return true;

        return false;
    }
}

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

class ParticleSystem {

    ArrayList<Particle> particles;    // An arraylist for all the particles
    PVector origin;                   // An origin point for where particles are birthed
    PImage img;
    PApplet pa;
    ParticleSystem(int num, PVector v, PImage img_, PApplet pa) {
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