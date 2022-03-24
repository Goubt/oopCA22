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
    
    Minim minim;
    AudioPlayer af;
    AudioInput ai;
    AudioBuffer ab;
    AudioPlayer ay;
    AudioBuffer aby;

    int mode = 0;

    static final int FADE = 2500;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
		if (keyCode == ' ') {
            if (af.isPlaying()) {
                af.pause();
            } else {
                af.rewind();
                af.play();
            }
        }
        if (keyCode == LEFT) {
			direction -= 90;
		}
		if (keyCode == RIGHT) {
			direction += 90;
		}
        if (keyCode == '1') {
            af.shiftGain(0,-30,FADE);
            ay.shiftGain(-30,0,FADE);
        }
        if (keyCode == '2') {
            af.shiftGain(-30,0,FADE);
            ay.shiftGain(0,-30,FADE);
        }
        if (keyCode == '3') {
            af.setGain(3);
        }
        if (keyCode == '4') {
            af.setGain(4);
        }

	}

    public void settings()
    {
        size(1024, 1000, P3D);  
    }

    public void setup()
    {
        minim = new Minim(this); 
        af = minim.loadFile("ONGP.mp3", 1024);
        af.play();
        ab = af.mix;
        ay = minim.loadFile("GUMMY.mp3", 1024);
        ay.play();
        aby = ay.mix;
        /*
        ay = minim.loadFile("GUMMY.mp3", 1024);
        ay.play();
        aby = ay.mix;
        */
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
        background(0);
        camera1.feed();
        translate(0, 0, -width);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
		background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, c, c);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, i, halfH - f);                    
                }
        //start();
        translate(width/2, height/2, 0);
        translate(width, 0, width);
		yaris();

		translate(-width, 0, width);
		finn();

		translate(-width, 0, -width);
		gooba();

        if (direction > 0) {
			RotateRight();
			direction--;
		}
		if (direction < 0) {
			RotateLeft();
			direction++;
            
		}
    }
    
    public void start(){
        
	}
    
    public void yaris(){
		stroke(255,0,0);
		noFill();
		box(200);
		
	}

	public void finn(){
		stroke(0,255,0);
        noFill();
        box(200);
	}

	public void gooba(){
		stroke(0,0,255);
		noFill();
		box(200);
	}

    public void RotateRight(){
		camera1.look((float) (radians((float) (1))), 0);
	}

	public void RotateLeft(){
		camera1.look(-(float) (radians((float) (1))), 0);
	}
}        
