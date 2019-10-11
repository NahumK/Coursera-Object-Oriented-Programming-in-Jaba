package guiModule;

import processing.core.*;

public class EffectTestNaiv extends PApplet
{

	float rotateAmount;
	int boxColorR = 255;
	int boxColorG = 255;
	int boxColorB = 255;
	int boxW = 480;

	public void setup () 
	{
		size(640,480);
		rectMode(CENTER);
	}

	public void draw() 
	{
		//rect(width/2,height/2,640,480); //this solves the text overlapping but erases the cool effect
		drawBox();
		drawText();

	}


	public void drawText() 
	{
		//translate(width/2,height/2);
		textAlign(LEFT, CENTER);
		fill(255, 255, 255);
		textSize(32);
		text("RED: " + boxColorR,width/2,height/2);
		text("GREEN: " + boxColorG,width/2,height/2+30);
		text("BLUE: " + boxColorB,width/2,height/2+60);
		text("Box Width: " + boxW,width/2,height/2+90); 
	}

	public void drawBox() 
	{
		translate(width/2,height/2);
		rotateAmount += 12;
		
		if (boxColorR <= 0) 
			boxColorG--;
	
		if (boxColorG <= 0) 
			boxColorB--;
		
		boxColorR--;
		boxW--;
		rotateAmount += .05;
		
		rotate(rotateAmount);
		fill(boxColorR,boxColorG,boxColorB);
		rect(0,0,boxW,boxW);
		resetMatrix();

	}

}
