package ie.assignment;

import java.util.ArrayList;

import damkjer.ocd.*;

import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class OOP extends Visual
{
    float rotation = 0;
    float direction = 0;
    Camera camera1;
    Camera camera2;

    ParticleSystem tlps;
    ParticleSystem trps;
    ParticleSystem blps;
    ParticleSystem brps;

    static final int FADE = 2500;

    int Choice = 4;
    int menu = 0;
    String[] Songs = {"POISON.mp3", "GUMMY.mp3", "DARE.mp3"};
    
    int start = 90;

    int screenBrightness = 0;

    float[] lerpedBuffer;

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
            print("hello");
            Choice = menu;
            getAudioPlayer().rewind();
            
        }
        if (key == BACKSPACE) {
          Choice = 4;
          print("hello");
          getAudioPlayer().rewind();
        }

        if (keyCode == ' ') {
          getAudioPlayer().cue(110000);
        }
        }

    public void settings()
    {
        size(1200, 1000, P3D);  
        //fullScreen();
    }

    public void setup()
    { 
        startMinim();
        loadAudio("POISON.mp3");
        getAudioPlayer().play();
        
        colorMode(RGB);

        //beat = new BeatDetect(ap.bufferSize(), ap.sampleRate());
        //beat.setSensitivity(300);


        camera1 = new Camera(this, 
							 width/2, height/2, 0,
							 width/2, height/2, -width,
							 0, 1, 0);

        PImage img = loadImage("images/poison.png");
        tlps = new ParticleSystem(5, new PVector(0, 0), img, this);
        trps = new ParticleSystem(5, new PVector(0, width), img, this);
        blps = new ParticleSystem(5, new PVector(height, 0), img, this);
        brps = new ParticleSystem(5, new PVector(height, width), img, this);

        lerpedBuffer = new float[width];
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
                    text("<SELECT1>", width/2, (height)-50);
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
            float sum = 0;
            for(int i = 0 ; i < getAudioBuffer().size() ; i ++)
              {
                  sum += abs(getAudioBuffer().get(i));
                  lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.05f);
              }
            background(0);
                for(int i = 0 ; i < getAudioBuffer().size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * height/2 * 4.0f;
                    line(i, height/2 + f, i, height/2 - f);                    
                }
            float passed = getAudioPlayer().position();
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
                if (passed > 39500 && passed < 40000 ||
                    passed > 44000 && passed < 44500 ||
                    passed > 48500 && passed < 49000 ||
                    passed > 53000 && passed < 53500 ||
                    passed > 113000 && passed < 113500 ||
                    passed > 117500 && passed < 118000 ||
                    passed > 122000 && passed < 122500 ||
                    passed > 126500 && passed < 127000 ) {
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
        Shiftdown();
        changeAudio(Song);
        ShiftUp(); 
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