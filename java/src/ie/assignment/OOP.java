package ie.assignment;

import damkjer.ocd.*;

import processing.core.PApplet;


public class OOP extends PApplet
{
	
	float rotation = 0;
	float direction = 0;
	Camera camera1;

	public void settings()
	{
		size(500, 500, P3D);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		camera1 = new Camera(this, 
							 width/2, height/2, 0,
							 width/2, height/2, -width,
							 0, 1, 0);
	}

	public void draw()
	{	
        background(0);
		camera1.feed();

		translate(width/2, height/2, -width);
		start();

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
	public void RotateRight(){
		camera1.look((float) (radians((float) (1))), 0);
	}

	public void RotateLeft(){
		camera1.look(-(float) (radians((float) (1))), 0);
	}

	public void keyPressed() {
		if (keyCode == LEFT) {
			direction -= 90;
		}
		if (keyCode == RIGHT) {
			direction += 90;
		}
		if (keyCode == ' ')
		{
			//zoom(5);
		}
		if (keyCode == UP) {
			camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
		}
	}

	public void start(){
		stroke(255,255,255);
		noFill();
		box(200);
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

}
