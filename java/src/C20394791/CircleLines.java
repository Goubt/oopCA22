package C20394791;

import ddf.minim.Minim;
import ie.assignment.OOP;
import ie.assignment.Visual;
import ie.assignment.VisualException;
import processing.core.PVector;


public class CircleLines extends OOP{

  OOP oop;

    public CircleLines(OOP oop) {
        this.oop = oop;
    }

  int canvasWidth = 1080;
  int canvasHeight = 1080;

  String audioFileName = "GUMMY.mp3";

  float fps = 30;
  float smoothingFactor = 0.25f;



  int bands = 256;
  float[] spectrum = new float[bands];
  float[] sum = new float[bands];

  float unit;
  int groundLineY;
  PVector center;
  


  public void strokeWeight(double d) {
  }

  public void frameRate(float fps2) {
  }

  int sphereRadius;

  float spherePrevX;
  float spherePrevY;

  int yOffset;

  boolean initialStatic = true;
  float[] extendingSphereLinesRadius;

  void drawStatic() {
    center = new PVector(oop.width/2, oop.height/2);
    if (initialStatic) {
      extendingSphereLinesRadius = new float[241];

      for (int angle = 0; angle <= 240; angle += 4) {
        int i = (int) random(1);
        extendingSphereLinesRadius[angle] = map(random(1), 0, 1, sphereRadius, sphereRadius * 7);
      }

      initialStatic = false;
    }

    for (int angle = 0; angle <= 240; angle += 4) {

      float x = round(cos(radians(angle + 150)) * sphereRadius + center.x);
      float y = round(sin(radians(angle + 150)) * sphereRadius + groundLineY - yOffset);

      float xDestination = x;
      float yDestination = y;

      for (int i = sphereRadius; i <= extendingSphereLinesRadius[angle]; i++) {
        float x2 = cos(radians(angle + 150)) * i + center.x;
        float y2 = sin(radians(angle + 150)) * i + groundLineY - yOffset;
        
        if (y2 <= getGroundY(x2)) {
          xDestination = x2;
          yDestination = y2;
        }
        
      }
      oop.stroke(255);
      
      if (y <= getGroundY(x)) {
        oop.line(x, y, xDestination, yDestination);
      }
      
    }
  }
  /*
  private Object random(int i) {
    return null;
  }*/

  private float getGroundY (float x2) {
    return 0; 
    }

  private int cos(Object radians) {
    return 0;
  }

  private int sin(Object radians) {
    return 0;
  }

  private Object radians(int i) {
    return null;
  }

  void drawAll(float[] sum) {

    sphereRadius = (int) (15 * round(unit));

    spherePrevX = 0;
    spherePrevY = 0;

    yOffset = (int) round(sin(radians(150)) * sphereRadius);

    drawStatic();

    // Lines surrounding
    float x = 0;
    float y = 0;
    int surrCount = 1;

    boolean direction = false;

    while (x < oop.width * 1.5 && x > 0 - oop.width / 2) {

      float surroundingRadius;

      float surrRadMin = sphereRadius + sphereRadius * 1 / 2 * surrCount;
      float surrRadMax = surrRadMin + surrRadMin * 1 / 8;

      float surrYOffset;

      float addon = (float) (oop.frameCount * 1.5);

      if (direction) {
        addon = (float) (addon * 1.5);
      }

      for (float angle = 0; angle <= 240; angle += 1.5) {

        surroundingRadius = map(sin(radians(angle * 7 + addon)), -1, 1, surrRadMin, surrRadMax);

        surrYOffset = sin(radians(150)) * surroundingRadius;

        x = round(cos(radians(angle + 150)) * surroundingRadius + center.x);
        y = round(sin(radians(angle + 150)) * surroundingRadius + getGroundY(x) - surrYOffset);

        oop.noStroke();
        oop.fill(map(surroundingRadius, surrRadMin, surrRadMax, 100, 255));
        oop.circle(x, y, (float) ((float) 3 * unit / 10.24));
        oop.noFill();
      }

      direction = !direction;

      surrCount += 1;
    }
  }

  // code removed here

  public void render() {
    oop.getFFT().forward(oop.getAudioPlayer().mix);

    spectrum = new float[bands];

    for (int i = 0; i < oop.getFFT().avgSize(); i++) {
      spectrum[i] = oop.getFFT().getAvg(i) / 2;

      sum[i] += (abs(spectrum[i]) - sum[i]) * smoothingFactor;
    }

    oop.fill(0);
    oop.noStroke();
    oop.rect(0, 0, oop.width, oop.height);
    oop.noFill();

    drawAll(sum);
  }
}
