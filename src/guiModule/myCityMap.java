package guiModule;

import java.util.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;

public class myCityMap extends PApplet{
	
	private UnfoldingMap map;

	public void setup()
	{
		size(950, 600, OPENGL);
		background(10);
		
		AbstractMapProvider provider = new Microsoft.RoadProvider();
		map = new UnfoldingMap(this, 200, 50, 700, 500, provider);
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location beriLoc = new Location(4.0864f, 9.6505f);
		Location ivryLoc = new Location(48.805f, 2.37f);
		
		Feature beriProp = new PointFeature(beriLoc);
		Feature ivryProp = new PointFeature(ivryLoc);
		
		beriProp.addProperty("Quartier", "Ngwelle");
		beriProp.addProperty("Importance", "piole");
		
		ivryProp.addProperty("Quartier", "Ivry sur seine");
		ivryProp.addProperty("Importance", "Residence");
		
		List<PointFeature> theseCities = new ArrayList<PointFeature>();
		theseCities.add((PointFeature)beriProp);
		theseCities.add((PointFeature)ivryProp);
		
		List<Marker> markers = new ArrayList<Marker>();
		
		for(PointFeature city : theseCities)
			markers.add(new SimplePointMarker(city.getLocation(), city.getProperties()));
		
		for(Marker marker : markers)
		{
			if(marker.getProperty("Quartier").equals("Ngwelle"))
				marker.setColor(color(0, 255, 0));
			else
				marker.setColor(color(41, 117, 247));
		}
		
		map.addMarkers(markers);
		
	}

	public void draw()
	{
		map.draw();
	}
	
}
