package ie.assignment;

import C20402732.*;
import C20492576.*;
import damkjer.ocd.*;

import ddf.minim.analysis.BeatDetect;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

public class OOP extends Visual {
    float rotation = 0;
    float direction = 0;
    public Camera camera1;

    //CircleLines yaris;

    GOOBAvisual gooba;
    
    // fractal tree
    FractalTree fv;
    int treeCount = 8;
    public float branchCount = 0;
    public float rotationCycle = 0;
    public int changeVisual = 0;
    public int rotateDirection = 0;
    public int noBranches = 15;
    // colour
    public Colours clFinn;
    public Colours clGooba;

    // input tracking
    Boolean hideMenu = true;
    PVector mouseLocation = new PVector();
    public float rotationAngle = 0;
    public float mouseVal, mouseRotation, easing = 0.07f;
    public float scrollVal = 1.5f;

    static final int FADE = 2500;

    int menu = 0;
    String[] Songs = { "POISON.mp3", "GUMMY.mp3", "DARE.mp3" };

    int start = 90;

    public int screenBrightness = 0;

    public int spheresize = 0;

    public static ParticleSystem tlps;
    public static ParticleSystem trps;
    public static ParticleSystem blps;
    public static ParticleSystem brps;

    public BeatDetect beat;
    public BeatDetection fBeat = new BeatDetection();

    public boolean lock = false ;
    public boolean play = true;

    public void keyPressed() {
        if (keyCode == LEFT && direction == 0 && lock == false) {
            direction -= 120;

            if (menu == 0)
                menu = 2;

            else
                menu--;

            loadMusic(Songs[menu]);
        }

        if (keyCode == RIGHT && direction == 0 && lock == false) {
            direction += 120;

            if (menu == 2)
                menu = 0;

            else
                menu++;

            loadMusic(Songs[menu]);
        }

        if (keyCode == UP && direction == 0) {
            if (!(noBranches == 20))
                noBranches += 1;

        }

        if (keyCode == DOWN && direction == 0) {
            if (!(noBranches == 0))
                noBranches -= 1;

        }

        if (keyCode == ' ') {
            if (play == true){
                getAudioPlayer().pause();
                play = false;
            }else {
                getAudioPlayer().play();
                play = true;
            }
        }

        if (keyPressed) {
            if ((key == 'l' || key == 'L') && menu == 0) {
                if(lock == false)
                lock = true;
                else if(lock == true)
                lock = false;
                camera1.jump(width / 2, height / 2, 0);
            }
        }

        if (keyCode == 'X') {
            if (rotateDirection == 0)
                rotateDirection = 1;
            else
                rotateDirection = 0;
        }

        if (keyCode == 'C') {
            if (changeVisual == 0)
                changeVisual = 1;

            else
                changeVisual = 0;

        }

        if (keyCode == 'H') {
            if (hideMenu == true)
                hideMenu = false;

            else
                hideMenu = true;

        }

    }

    public void mouseWheel(MouseEvent mouse) {
        float mouseWheel = mouse.getCount();

        if (mouseWheel == 1.0f) {
            if (scrollVal < 1.5f) {
                scrollVal += 0.1f;
            }

        }

        else if (mouseWheel == -1.0f) {

            if (scrollVal > -0.3f) {
                scrollVal -= 0.1f;
            }

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
        getAudioPlayer().loop();

        colorMode(RGB);

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

        clGooba = new Colours();
        gooba = new GOOBAvisual(this, clGooba);

        //yaris = new CircleLines(this);

        clFinn = new Colours();
        

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

        fill(0, 50);
        rect(0, 0, width * 2, height * 2);
        textSize(20);
        fill(255);
        text(frameRate, 50, 50);
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

    public void start() {

    }

    public void yaris() {
        pushMatrix();
        //translate(0,0, width/2);
        //yaris.render();
        popMatrix();
    }

    public void finn() {

        stroke(255);
        fill(255);
        textSize(30);

        if(hideMenu == false) {
            text("UP/DOWN  |  Branch Count: " + noBranches, -(width / 2) - 15, -(height / 2) - 100);
            text("SCROLL   |  Amplitude Modifier: " + Math.round(scrollVal * 100.0f) / 100.0f, -(width / 2) + 20,
                    -(height / 2) - 50);
        }
        
        fv = new FractalTree(this, OOP.map(smoothedAmplitude, 0, .5f, -height / 15f, -height / 4f), 0, noBranches,
                clFinn, treeCount);
        fv.render();
    }


    public void gooba() {
        gooba.render();
    }

    public void loadMusic(String Song) {
        Shiftdown();
        changeAudio(Song);
        ShiftUp();
    }

    public int HatBeat(Boolean type, int i) {
        if (type == true)
            i += 2;

        return i;
    }

    public void changeBackground() {

        BeatDetection fBeat = new BeatDetection();
        beat.detect(getAudioPlayer().mix);
        Boolean type = fBeat.readBeat((beat));

        // background(screenBrightness);
        screenBrightness = backgroundBeat(type, screenBrightness);

        if (screenBrightness > 10)
            screenBrightness -= 2;
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

    public void getMouseAngle() {

        mouseLocation.set(mouseX, mouseY, 0);
        mouseLocation.sub(width / 2, height / 2, 0);
        mouseVal = mouseLocation.heading();
        mouseRotation = (mouseVal - rotationAngle) / PI;
        if (mouseRotation < -1)
            rotationAngle -= PI * 2;

        else if (mouseRotation > 1)
            rotationAngle += PI * 2;
        rotationAngle += (mouseVal - rotationAngle) * easing;

    }
}