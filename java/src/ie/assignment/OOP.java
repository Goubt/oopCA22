package ie.assignment;

import C20402732.GOOBAvisual;
import C20402732.ParticleSystem;
import C20492576.FractalTree;
import damkjer.ocd.*;

import ddf.minim.analysis.BeatDetect;
import processing.core.PImage;
import processing.core.PVector;

public class OOP extends Visual {
    float rotation = 0;
    float direction = 0;
    Camera camera1;
    Camera camera2;

    public static ParticleSystem tlps;
    public static ParticleSystem trps;
    public static ParticleSystem blps;
    public static ParticleSystem brps;

    public BeatDetect beat;
    public BeatDetection fBeat = new BeatDetection();

    static final int FADE = 2500;

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;

    GOOBAvisual gooba;
    FractalTree fv;
    Visual v;

    public float branchCount = 0;
    public float rotationCycle = 0;

    int menu = 0;
    int choice = 0;

    public int screenBrightness = 0;
    public int spheresize = 0;

    String[] Songs = { "POISON.mp3", "GUMMY.mp3", "DARE.mp3" };

    public void keyPressed() {

        if (keyCode == LEFT && direction == 0) {
            direction -= 120;

            if (menu == 0)
                menu = 2;

            else
                menu--;

            loadMusic(Songs[menu]);
        }

        if (keyCode == RIGHT && direction == 0) {
            direction += 120;

            if (menu == 2)
                menu = 0;

            else
                menu++;

            loadMusic(Songs[menu]);
        }

        if (key == ENTER) {

            // if (choice == 1) {
            // af.pause();
            // ay.pause();
            // ag.pause();
            // // wait(50);
            // ag.play();
            // ag.rewind();
            // }
        }

    }

    public void settings() {
        size(1200, 1000, P3D);
        // fullScreen();
    }

    public void setup() {

        startMinim();

        loadAudio("GUMMY.mp3");
        getAudioPlayer().play();

        // BeatDetect(1024, 44100.0f);
        beat = new BeatDetect(getAudioPlayer().bufferSize(), getAudioPlayer().sampleRate());
        beat.setSensitivity(300);

        PImage img = loadImage("images/poison.png");

        tlps = new ParticleSystem(5, new PVector(-width / 2, -height / 2), img, this);
        trps = new ParticleSystem(5, new PVector(width / 2, -height / 2), img, this);
        blps = new ParticleSystem(5, new PVector(-width / 2, height / 2), img, this);
        brps = new ParticleSystem(5, new PVector(width / 2, height / 2), img, this);

        camera1 = new Camera(this,
                width / 2, height / 2, 0,
                width / 2, height / 2, -width,
                0, 1, 0);

        lerpedBuffer = new float[width];
        gooba = new GOOBAvisual(this);

    }

    float off = 0;

    public void draw() {

        calculateAverageAmplitude();

        changeBackground();
        textSize(100);
        textAlign(CENTER);
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

        fv = new FractalTree(this, OOP.map(smoothedAmplitude, 0, .5f, -height / 15f, -height / 4f), 0, 20);
        fv.render();
    }

    public void gooba() {
        stroke(0, 0, 255);
        noFill();
        box(100);
        rect(0, 0, width - 100, height - 100);
    }

    public void RotateRight() {
        camera1.look((float) (radians((float) (1))), 0);
    }

    public void RotateLeft() {
        camera1.look(-(float) (radians((float) (1))), 0);
    }

    public void changeBackground() {
        beat.detect(getAudioPlayer().mix);
        Boolean type = fBeat.readBeat((beat));

        background(screenBrightness);
        screenBrightness = backgroundBeat(type, screenBrightness);

        if (screenBrightness > 10)
            screenBrightness -= 2;
    }

    public int backgroundBeat(Boolean type, int i) {

        if (type == true)
            i += 20;

        return i;
    }

    public void loadMusic(String Song) {
        Shiftdown();
        changeAudio(Song);
        ShiftUp();
    }
}
