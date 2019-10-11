package module5;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Nahum Kouotou
 *
 */
// TODO: Change SimplePointMarker to CommonMarker as the very first thing you do 
// in module 5 (i.e. CityMarker extends CommonMarker).  It will cause an error.
// That's what's expected.
public class CityMarker extends CommonMarker 
{
	
	public static int TRI_SIZE = 5;  // The size of the triangle marker
	
	public CityMarker(Location location) 
	{
		super(location);
	}
	
	
	public CityMarker(Feature city) 
	{
		//super(((PointFeature)city).getLocation(), city.getProperties());
		super(((PointFeature)city).getLocation());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
		java.util.HashMap<String, Object> properties = city.getProperties();
		setProperties(properties);
	}

	
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void drawMarker(PGraphics pg, float x, float y) 
	{
		// Save previous drawing style
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		
		// Restore previous drawing style
		pg.popStyle();
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{
		// TODO: Implement this method
		String title = getCity() + ", " + getCountry() + "\nPop: " + getPopulation() + " Million";
		float delta = title.length();
		pg.fill(255, 250, 240);
		pg.rect(x - delta - 4, y - 36, 4*delta, 30);
		pg.fill(0);
		pg.stroke(2);
		pg.text(title, x - delta - 2, y - 24);
	}
	
	public String getTitle()
	{
		String title = getCity() + ", " + getCountry() + "\nPop: " + getPopulation() + " Million";
		return title;
	}
	
	/* Local getters for some city properties.  
	 */
	public String getCity()
	{
		return getStringProperty("name");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
	
}
