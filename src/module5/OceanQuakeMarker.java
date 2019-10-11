package module5;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Nahum Kouotou
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker 
{

	public OceanQuakeMarker(PointFeature quake) 
	{
		super(quake);

		// setting field in earthquake marker
		isOnLand = false;
	}

	/** Draw the earthquake as a square */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) 
	{
		pg.rect(x-radius, y-radius, 2*radius, 2*radius);
		
		List<Marker> threatenedCities = threatEntities();
		UnfoldingMap map = EarthquakeCityMap.getMap();
		
		for(Marker marker : threatenedCities)
		{
			if(getClicked())
				pg.fill(0);
			else
				pg.noStroke();
			
			ScreenPosition cityPosition = map.getScreenPosition(marker.getLocation());
			ScreenPosition myPosition = map.getScreenPosition(this.getLocation());
			pg.line(myPosition.x - 200, myPosition.y - 50, cityPosition.x - 200, cityPosition.y - 50);
		}
	}

	private List<Marker> threatEntities()
	{
		Location loc = this.getLocation();

		List<Marker> cityMarkers = EarthquakeCityMap.getCityMarkers();
		List<Marker> result = new ArrayList<Marker>();

		for(Marker marker : cityMarkers)
		{
			double threatRadius = this.threatCircle();
			double distance = marker.getDistanceTo(loc);
			
			if(Math.abs(distance) <= Math.abs(threatRadius))
				result.add(marker);
		}
		
		return result;
	}

}
