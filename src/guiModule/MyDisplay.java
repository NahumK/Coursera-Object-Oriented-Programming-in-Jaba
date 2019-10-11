package guiModule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{

	public void setup()
	{
		size(400, 400);
		background(138, 180, 183);
	}
	
	public void draw()
	{
		fill(250, 209, 0);
		ellipse(200, 200, 390, 390);
		fill(0);
		ellipse(120, 130, 50, 70);
		ellipse(280, 130, 50, 70);
		
		noFill();
		arc(200, 280, 75, 75, 0, PI);
	}
	
}
