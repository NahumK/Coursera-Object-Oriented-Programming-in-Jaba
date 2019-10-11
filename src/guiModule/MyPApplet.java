package guiModule;

import processing.core.*;

public class MyPApplet extends PApplet{
	
	private String URL = "https://images.pexels.com/photos/844452/pexels-photo-844452.jpeg?cs=srgb&dl=beach-beautiful-view-buildings-844452.jpg&fm=jpg";
	private PImage backgroundImg;
	
	public void setup()
	{
		size(600, 600);
		backgroundImg = loadImage(URL, "jpg");
		backgroundImg.resize(0, height);
		image(backgroundImg, 0, 0);
	}
	
	public void draw()
	{
		int[] color = sunColorSec(second());
		int red = color[0];
		int green = color[1];
		int blue = color[2];
		
		fill(red, green, blue);
		ellipse(width / 4, height / 5, width /5, height / 5);
	}
	
	public int[] sunColorSec(float seconds)
	{
		int[] rgb = new int[3];
		
		//Scale the brightness of the yellow based on the seconds. 30 seconds. 
		//30 seconds is black. 0 seconds is bright yellow.
		float diffFrom30 = Math.abs(30 - seconds);
		float ratio = diffFrom30 / 30;
		
		rgb[0] = (int)(255 * ratio);
		rgb[1] = (int)(255 * ratio);
		
		return rgb;
	}
	
}
