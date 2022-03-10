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
		//camera1 = new Camera(this, 200, -250, 300);
	}



	public void draw()
	{	
        background(0);
		//float orbitRadius= 100;
		camera1.feed();
		translate(width/2, height/2, -width);
		stroke(255,255,255);
		noFill();
		box(200);
		translate(width, 0, width);
		stroke(255,0,0);
		noFill();
		box(200);
		translate(-width, 0, width);
		stroke(0,255,0);
		noFill();
		box(200);
		translate(-width, 0, -width);
		stroke(0,0,255);
		noFill();
		box(200);
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

	public void DrawSphere(){
		strokeWeight(2);
		stroke(200, 255, 255);
		
		translate(width/2, height/2, -100);
        sphere(200);
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

}
