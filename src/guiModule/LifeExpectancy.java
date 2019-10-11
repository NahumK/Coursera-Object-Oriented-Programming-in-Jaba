package guiModule;

import java.util.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
	
	UnfoldingMap map;
	Map<String, Float> lifeExByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;

	public void setup()
	{
		size(800, 600, OPENGL);
		
		AbstractMapProvider provider = new Microsoft.RoadProvider();
		map = new UnfoldingMap(this, 50, 50, 700, 500, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		String countriesFile = "countries.geo.json";
		String fileName = "LifeExpectancyWorldBankModule3.csv"; 
		
		lifeExByCountry = loadLifeExpectancyFromCSV(fileName);
		countries = GeoJSONReader.loadData(this, countriesFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw()
	{
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName)
	{
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		
		String[] rows = loadStrings(fileName);
		
		for(String row : rows)
		{
			String[] columns = row.split(",");
			if(columns.length == 6 && !columns[5].equals(".."))
			{
				float value = Float.parseFloat(columns[5]);
				String country = columns[4];
				lifeExpMap.put(country, value);
			}
		}
		
		return lifeExpMap;
	}
	
	private void shadeCountries()
	{
		
		for(Marker marker : countryMarkers)
		{
			String countryID = marker.getId();
			
			if(lifeExByCountry.containsKey(countryID))
			{
				float lifeExp = lifeExByCountry.get(countryID);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255 - colorLevel, 100,  colorLevel));
			}
			else
			{
				marker.setColor(color(150, 150, 150));
			}
		}
		
	}
	
}
