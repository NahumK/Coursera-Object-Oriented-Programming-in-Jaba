package guiModule;

import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class EventTest extends PApplet
{

	private UnfoldingMap map;
	
	public void setup()
	{
		size(800, 600, OPENGL);
		AbstractMapProvider provider = new Microsoft.RoadProvider();
		map = new UnfoldingMap(this, 50, 50, 700, 500, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
	}
	
	public void draw()
	{
		background(0);
		map.draw();
		addButtons();
	}
	
	private void addButtons()
	{
		fill(255, 255, 255);
		rect(100, 100, 25, 25);
		
		fill(100, 100, 100);
		rect(100, 150, 25, 25);
	}
	
	public void mouseClicked()
	{
		if((mouseX > 100) && (mouseX < 125) & (mouseY > 100) && (mouseY < 125))
			background(255, 255, 255);
		if((mouseX > 100) && (mouseX < 125) & (mouseY > 150) && (mouseY < 175))
			background(100, 100, 100);
	}
	
	
}
