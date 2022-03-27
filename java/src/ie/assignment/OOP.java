package ie.assignment;

import damkjer.ocd.*;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class OOP extends PApplet
{
    float rotation = 0;
	float direction = 0;
	Camera camera1;
    Camera camera2;
    
    Minim minim;
    AudioInput ai;
    AudioPlayer af;
    AudioBuffer abf;
    AudioPlayer ay;
    AudioBuffer aby;
    AudioPlayer ag;
    AudioBuffer abg;

    

    int mode = 0;

    static final int FADE = 2500;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    int Choice = 0;
    int Menu = 9;

    public void keyPressed() {
        if (keyCode == LEFT && direction == 0) {
			direction -= 120;
            Menu--;
            
            if (Menu%3 == 0) {
                ay.shiftGain(0,-50,FADE);
                
                ag.shiftGain(-50,0,FADE);
            }
            else if (Menu%3 == 1) {
                af.shiftGain(0,-50,FADE);
                
                ay.shiftGain(-50,0,FADE);
            }
            else {
                ag.shiftGain(0,-50,FADE);
                
                af.shiftGain(-50,0,FADE);
                
            }
            print(Menu);
		}
		if (keyCode == RIGHT && direction == 0) {
			direction += 120;
            Menu++;
            
            if (Menu%3 == 0) {
                af.shiftGain(0,-50,FADE);
                
                ag.shiftGain(-50,0,FADE);
            }
            else if (Menu%3 == 1) {
                ag.shiftGain(0,-50,FADE);
                
                ay.shiftGain(-50,0,FADE);
            }
            else {
                ay.shiftGain(0,-50,FADE);
                
                af.shiftGain(-50,0,FADE);
            }
            print(Menu);    
		}
        if (key == ENTER) {
            
            Choice = Menu%3;
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
        af = minim.loadFile("ONGP.mp3", 1024);
        af.play();
        abf = af.mix;
        ay = minim.loadFile("GUMMY.mp3", 1024);
        ay.play();
        aby = ay.mix;
        ag = minim.loadFile("POISON.mp3", 1024);
        ag.play();
        abg = ag.mix;
        
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

        camera1 = new Camera(this, 
							 width/2, height/2, 0,
							 width/2, height/2, -width,
							 0, 1, 0);

    }

    float off = 0;

    public void draw()
    {
        switch (Choice) {
            case 0:
                textSize(100);
                textAlign(CENTER);
                background(0);
                camera1.feed();
                rectMode(CENTER);
                
                translate(width/2, height/2, -width);
                gooba();
                double third = width * 0.866;
                float move = (float)third;
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
        
            case 1: // Gooba
            print("Gooba");
            break;

            case 2: // Yaris
            print("Yaris");
            break;

            case 3: // Finn
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

    public void RotateRight(){
		camera1.look((float) (radians((float) (1))), 0);
	}

	public void RotateLeft(){
		camera1.look(-(float) (radians((float) (1))), 0);
	}
}        
