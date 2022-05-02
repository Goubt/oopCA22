package ie.assignment;

import java.util.ArrayList;
import java.util.Random;

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
    int Menu = 999;
    int song = 1;
    
    int start = 90;

    public void keyPressed() {
        if (keyCode == LEFT && direction == 0) {
			direction -= 120;
            
            if (Menu%3 == 1) {
                ay.shiftGain(0,-50,FADE);
                ag.shiftGain(-50,0,FADE);
                song = 1;
            }
            else if (Menu%3 == 2) {
                af.shiftGain(0,-50,FADE);
                ay.shiftGain(-50,0,FADE);
                song = 2;
            }
            else {
                ag.shiftGain(0,-50,FADE);
                af.shiftGain(-50,0,FADE);
                song = 3;
            }
            Menu--;
            print(Menu);
		}
		if (keyCode == RIGHT && direction == 0) {
			direction += 120;
            
            if (Menu%3 == 2) {
                af.shiftGain(0,-50,FADE);
                ag.shiftGain(-50,0,FADE);
                song = 1;
            }
            else if (Menu%3 == 0) {
                ag.shiftGain(0,-50,FADE);
                ay.shiftGain(-50,0,FADE);
                song = 2;
            }
            else {
                ay.shiftGain(0,-50,FADE);
                af.shiftGain(-50,0,FADE);
                song = 3;
            }
            Menu++;
            print(Menu);    
		}
        if (key == ENTER) {
            
            Choice = song;
            if (Choice == 1) {
                ag.shiftGain(0,-50,FADE);
            }
            
            
        }

        if (keyCode == ' ') {
            if (ag.isPlaying()) {
                ag.pause();
            } else {
                ag.rewind();
                ag.play();
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
        af = minim.loadFile("ONGP.mp3", 1024);
        af.play();
        af.setGain(-50);
        abf = af.mix;
        ay = minim.loadFile("GUMMY.mp3", 1024);
        ay.play();
        ay.setGain(-50);
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
        
            case 1: // Gooba
            
                if (start > 0) {
                    print("Gooba");
                    clear();
                    start--;
                    print("Gooba");
                } else if(start == 0){
                    af.pause();
                    ay.pause();
                    
                    
                    ag.play();
                    ag.shiftGain(-50,0,500);
                    ag.rewind();
                    start--;
                } else {
                //background(0);
                

                float halfH = height / 2;
                float average = 0;
                float sum = 0;
                off += 1;
                // Calculate sum and average of the samples
                // Also lerp each element of buffer;
                for(int i = 0 ; i < abg.size() ; i ++)
                {
                    sum += abs(abg.get(i));
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], abg.get(i), 0.05f);
                }
                average= sum / (float) abg.size();

                smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
                background(0);
                for(int i = 0 ; i < abg.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, abg.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                }
            }
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