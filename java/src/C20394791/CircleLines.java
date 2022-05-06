package C20394791;

import C20492576.Colours;
import C20492576.DynamicColour;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ie.assignment.OOP;


// ***************************************************
// *** SETTING UP VARIABLES FOR THE SOUND ANALYSIS ***
// ***************************************************


// *********************************************
// *** SETTING UP VARIABLES FOR THE GRAPHICS ***
// *********************************************


public class CircleLines extends OOP {
  
  OOP oop;
  DynamicColour ydc;

  public CircleLines(OOP oop, Colours ycl) {
      this.oop = oop;
      ydc = new DynamicColour(oop, ycl);
  }
  int bass;
  int snare;
  float noise;

  //int selector, weight;
  int topLx, topLy, topCx, topCy, topRx, topRy; // These are the values for kickbox beat top row
  int midLx, midLy, midCx, midCy, midRx, midRy; // These are the values for kickbox beat middle row
  int botLx, botLy, botCx, botCy, botRx, botRy; // These are the values for kickbox beat bottom row
  //int driftX;
  //float randomRotateAmt;
  //int circlepop;
  //int circleLoc;
  //float arcLength;
  float xoffset = (float) 0.0;
  float yrise = (float) 0.0;
  int col;
  int colorarray[];




  int selector = 2; 
  int weight = 1;
  int kickboxSens = 125;
 
  int snareSens = 5;
  
// ********************************************************
// *** SETTING UP FOR SQUARE, CIRCLE AND SPIRAL VISUALS ***
// ********************************************************
  int driftX = 0;
  float randomRotateAmt = random(1, 24);

  int circlepop = 0;
  int circleLoc = width/2;

  float arcLength = (float) 0.0005;
  

public void render() {
  
  oop.getFFT().forward(oop.getAudioPlayer().mix); 
  bass = (int) (oop.getFFT().getFreq(50)); 
  snare = (int) (oop.getFFT().getFreq(1760)); 
  noise = map(oop.getFFT().getFreq(19000), 0, 1, 0, 500); 
  if (bass>kickboxSens && frameCount>300) {
    selector = floor(random(1, (float) 5.57)); 
    weight = (int) (random(1, 50)); 
  } else {
    if (snare>snareSens) {
      weight = (int) (random(1, 65));
    }
  }
  
  if (selector==2) {
    oop.strokeWeight(weight + noise/15);
    square(20, 20, 1);
  }
  if (selector==3) {
    if (bass>kickboxSens/3 & frameCount>600) {
      oop.strokeWeight(weight + noise/15);
      square(20, 20, 0);
    } else {
      circlepop();
      fxRain();
    }
  }
  if (selector==4) {
    kickbox(50, bass, "warm", 2);
    if (bass>kickboxSens) {
      selector = floor(random(1, (float) 2.99)); 
    }
  }
  if (selector==5) {
    kickbox(50, bass, "cool", 1);
    if (bass>kickboxSens) {
      selector = floor(random(1, (float) 2.99)); 
    }
  }
  if (selector==2) {
    spiral();
  }
  
  if (mousePressed) {
    oop.fill(255);
    oop.textSize(12);
    oop.text(kickboxSens, width-40, 30);
    oop.text(bass, width-80, 30);
    oop.text(snareSens, width-120, 30);
    oop.text(snare, width-160, 30); 
  }
  
  if (noise < 1) {
    selector = 2;
  }
  
  
  //if (oop.getAudioPlayer().isPlaying() == false) {
  //    selector = 2;
  //    oop.getAudioPlayer() = minim.loadFile("GUMMY.mp3", 1024);
  //    oop.getAudioPlayer().rewind();
  //    oop.getAudioPlayer().play();
  //}
  
}

//void keyPressed() 
//{
//  if (key == ' ') 
//  {
//    if (oop.getAudioPlayer().isPlaying()) 
//    {
//      oop.getAudioPlayer().pause();  
//    } else 
//    {
//      oop.getAudioPlayer().play();
//    }
//  }
//  if (key == CODED) {
//    if (keyCode == UP) {
//      kickboxSens = kickboxSens + 10;
//      
//    }
//  }
//  if (key == CODED) {
//    if (keyCode == DOWN) {
//      kickboxSens = kickboxSens - 10;
//      
//    }
//  }
//  
//  if (key == CODED) {
//    if (keyCode == RIGHT) {
//      snareSens = snareSens + 5;
//      
//    }
//  }
//  if (key == CODED) {
//    if (keyCode == LEFT) {
//      snareSens = snareSens - 5;
//      
//    }
//  }
//}

// **********************************
// *** DRAWING THE ACTUAL VISUALS ***
// **********************************

void kickbox(int margin, int kickjerk, String colorMode, int thickness) {
  
 
  topLx = (int) (margin+(random(-kickjerk, kickjerk)));
  topLy = (int) (margin+(random(-kickjerk, kickjerk)));
  topCx = (int) (width/2+(random(-kickjerk, kickjerk)));
  topCy = (int) (margin+(random(-kickjerk, kickjerk)));
  topRx = (int) (width-margin+(random(-kickjerk, kickjerk)));
  topRy = (int) (margin+(random(-kickjerk, kickjerk)));
  
  midLx = (int) (margin+(random(-kickjerk, kickjerk)));
  midLy = (int) (height/2+(random(-kickjerk, kickjerk)));
  midCx = (int) (width/2+(random(-kickjerk, kickjerk)));
  midCy = (int) (height/2+(random(-kickjerk, kickjerk)));
  midRx = (int) (width-margin+(random(-kickjerk, kickjerk)));
  midRy = (int) (height/2+(random(-kickjerk, kickjerk)));
  
  botLx = (int) (margin+(random(-kickjerk, kickjerk)));
  botLy = (int) (height-margin+(random(-kickjerk, kickjerk)));
  botCx = (int) (width/2+(random(-kickjerk, kickjerk)));
  botCy = (int) (height-margin+(random(-kickjerk, kickjerk)));
  botRx = (int) (width-margin+(random(-kickjerk, kickjerk)));
  botRy = (int) (height-margin+(random(-kickjerk, kickjerk)));

  if (colorMode == "cool") {
    if (bass>kickboxSens) {
      oop.stroke(0, random(200, 255), random(150, 255), 230);
    } else { 
      oop.noStroke();
    }
  }
  if (colorMode == "warm") {
    if (bass>kickboxSens) {
      oop.stroke(random(180, 255), random(50, 75), random(30, 60), 230);
    } else { 
      oop.noStroke();
    }
  }
  oop.strokeWeight(random(thickness, thickness+10));
  
  oop.line(topLx, topLy, topCx, topCy);
  oop.line(topCx, topCy, topRx, topRy);
  oop.line(midLx, midLy, midCx, midCy);
  oop.line(midCx, midCy, midRx, midRy);
  oop.line(botLx, botLy, botCx, botCy);
  oop.line(botCx, botCy, botRx, botRy);
 
  oop.line(topLx, topLy, midLx, midLy);
  oop.line(topCx, topCy, midCx, midCy);
  oop.line(topRx, topRy, midRx, midRy);
  oop.line(midLx, midLy, botLx, botLy);
  oop.line(midCx, midCy, botCx, botCy);
  oop.line(midRx, midRy, botRx, botRy);
}


void square(int rectWidth, int rectHeight, int colorON) {
  
  oop.pushMatrix();
 
  driftX = driftX - 5;
  if (driftX<-width*3) {
    driftX = 0;
  }
  if (bass>kickboxSens) {
    randomRotateAmt = random(1, 24);
    if (colorON==1) {
      oop.stroke(random(125, 255), random(125, 255), random(125, 255));
    }
    if (colorON==0) {
      oop.stroke(255);
    }
  }
  oop.translate(driftX, 0);
  for (int iX = 20; iX<width*12; iX=iX+40) {
    
    for (int iY = 20; iY<height; iY=iY+40) {
      oop.noFill();
      oop.rotateY(randomRotateAmt);
      oop.rect(iX, iY, rectWidth, rectHeight);
    }
  }
  
  oop.popMatrix();
}

// *************************************
// *** CIRCLES WITH TRIANGLES VISUAL ***
// *************************************

void circlepop() {
  if(circleLoc==width){
    circleLoc = 0;
  } else { circleLoc = circleLoc + 1; }
  oop.pushMatrix();
  oop.translate(circleLoc, height/2);
  oop.fill(255);
  oop.noStroke();
  oop.ellipse(0, 0, noise, noise);
  oop.ellipse(-width/2, 0, noise, noise);
  oop.ellipse(width/2, 0, noise, noise);
  oop.fill(0);
  oop.ellipse(0, 0, ((float)(noise/1.1)), ((float)(noise/1.1)));
  oop.ellipse(-width/2, 0, ((float)(noise/1.1)), ((float)(noise/1.1)));
  oop.ellipse(width/2, 0, ((float)(noise/1.1)), ((float)(noise/1.1)));
  if (noise>500) {
    oop.stroke(255);
    oop.strokeWeight(1);
    oop.noFill();
    oop.triangle(random(-600, 600), random(-600, 600), random(-600, 600), random(-600, 600), random(-600, 600), random(-600, 600)); 
  }
  oop.popMatrix();
}

// ***************************************
// *** DRAWING THE CIRCLE LINES SPIRAL ***
// ***************************************
void spiral() {
  oop.noFill();
  arcLength = (float) (arcLength + 0.0001);
  if (arcLength == 10) {
    arcLength = (float) 0.0005;
  }
  oop.stroke(255);
  oop.translate(width/2, height/2);
  for (int r=50; r<650; r=r+5) {
    ydc.changeColour(0.1f);
    oop.rotate((float) (millis()/2000.0));
    oop.strokeWeight(3);
    oop.arc(0, 0, r*bass/10, r*bass/10, 0, arcLength);
  }
}


void squarebloom(int basesize) {
  xoffset = (float) (xoffset + .01);
  yrise = yrise - 1;
  if (yrise<-(height+15)) {
    yrise=0;
    oop.noStroke();
    oop.fill(0, 0, 0, 200);
    oop.rect(0, 0, width, height);
  }
  float n = noise(xoffset) * bass*2;
  //println(n);
  oop.strokeWeight(1);
  col = color(colorarray[(int)random(0, 5)]);
  oop.noFill();
  oop.stroke(col);
  oop.rect(width/2+5+n, height+15+yrise, basesize+bass/10, basesize+bass/10);
  oop.stroke(col);
  oop.rect(width/2-5-n, 0-15-yrise, basesize+bass/10, basesize+bass/10);
}

// ********************************
// *** GRAINY BACKGROUND EFFECT ***
// ********************************

void fxRain() {
  oop.fill(255);
  oop.textSize(random(noise));
  oop.text("l", random(width), random(height));
}

void fxGrain() {
  oop.fill(255);
  oop.textSize(random(noise));
  oop.text(".", random(width), random(height));
  oop.text(".", random(width), random(height));
}
}