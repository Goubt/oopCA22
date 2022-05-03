package ie.assignment;

import C20402732.*;
import damkjer.ocd.*;

import ddf.minim.analysis.BeatDetect;

import processing.core.PImage;
import processing.core.PVector;

public class OOP extends Visual {
    float rotation = 0;
    float direction = 0;
    public Camera camera1;
    public Camera camera2;

    GOOBAvisual gooba;

    public BeatDetect beat;

    static final int FADE = 2500;

    int Choice = 4;
    int menu = 0;
    String[] Songs = { "POISON.mp3", "GUMMY.mp3", "DARE.mp3" };

    int start = 90;

    public int screenBrightness = 0;

    public int spheresize = 0;

    public static float[] lerpedBuffer;

    public static ParticleSystem tlps;
    public static ParticleSystem trps;
    public static ParticleSystem blps;
    public static ParticleSystem brps;

    public BeatDetection fBeat = new BeatDetection();

    public void keyPressed() {
        if (keyCode == LEFT && direction == 0) {
            direction -= 120;

            if (menu == 0)
                menu = 2;

            else
                menu--;

            print(menu);

            loadMusic(Songs[menu]);
        }

        if (keyCode == RIGHT && direction == 0) {
            direction += 120;

            if (menu == 2)
                menu = 0;

            else
                menu++;

            print(menu);

            loadMusic(Songs[menu]);
        }
        if (key == ENTER) {

            Choice = menu;
            getAudioPlayer().rewind();

        }
        if (key == BACKSPACE) {
            Choice = 4;

            getAudioPlayer().rewind();
        }

        if (keyCode == ' ') {
            getAudioPlayer().cue(110000);
        }
    }

    public void settings() {
        size(1200, 1000, P3D);
        // fullScreen();
    }

    public void setup() {
        startMinim();
        loadAudio("POISON.mp3");
        getAudioPlayer().play();

        colorMode(RGB);

        //BeatDetect(1024, 44100.0f);
        beat = new BeatDetect(getAudioPlayer().bufferSize(), getAudioPlayer().sampleRate());
        beat.setSensitivity(300);

        PImage img = loadImage("images/poison.png");

        tlps = new ParticleSystem(5, new PVector(-width/2, -height/2), img, this);
        trps = new ParticleSystem(5, new PVector(width/2, -height/2), img, this);
        blps = new ParticleSystem(5, new PVector(-width/2, height/2), img, this);
        brps = new ParticleSystem(5, new PVector(width/2, height/2), img, this);

        camera1 = new Camera(this,
                width / 2, height / 2, 0,
                width / 2, height / 2, -width,
                0, 1, 0);

        camera2 = new Camera(this,
                width / 2, height / 2, 0,
                width / 2, height / 2, -width,
                0, 1, 0);

        lerpedBuffer = new float[width];
        gooba = new GOOBAvisual(this);

    }

    float off = 0;

    public void draw() {
        switch (Choice) {
            case 4:

                textSize(100);
                textAlign(CENTER);
                background(0);
                camera1.feed();
                rectMode(CENTER);
                double third = width * 0.866;
                float move = (float) third;

                translate(width / 2, height / 2, -width);
                pushMatrix();
                gooba();
                popMatrix();
                translate(move, 0, width + (width / 2));
                rotateY(-2 * PI / 3);
                yaris();
                rotateY(2 * PI / 3);
                translate(-2 * move, 0, 0);
                rotateY(2 * PI / 3);
                finn();

                hint(DISABLE_DEPTH_TEST); // 2D code starts here
                camera();
                noLights();
                if (frameCount % 60 < 30 && direction == 0) {
                    text("<SELECT>", width / 2, (height) - 50);
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
                translate(width / 2, height / 2, -width);
                
                gooba.render();

                break;

            case 1: // Yaris
                print("Yaris");
                break;

            case 2: // Finn
                print("Finn");
                break;
        }

    }

    public void start() {

    }

    public void yaris() {

        stroke(255, 0, 0);
        noFill();
        box(100);
        rect(0, 0, width - 100, height - 100);
    }

    public void finn() {

        stroke(0, 255, 0);
        noFill();
        box(100);
        rect(0, 0, width - 100, height - 100);
    }

    public void gooba() {
                
        gooba.render();
    }

    public void loadMusic(String Song) {
        Shiftdown();
        changeAudio(Song);
        ShiftUp();
    }

    public int HatBeat(Boolean type, int i){
        if (type == true)
            i += 2;

        return i;
    }

    public int backgroundBeat(Boolean type, int i) {

        if (type == true)
            i += 20;

        return i;
    }

    public void RotateRight() {
        camera1.look((float) (radians((float) (1))), 0);
    }

    public void RotateLeft() {
        camera1.look(-(float) (radians((float) (1))), 0);
    }
}

