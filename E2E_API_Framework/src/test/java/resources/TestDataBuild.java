package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String lang, String add) {
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(add);
		ap.setLanguage(lang);
		ap.setPhone_number("839832193");
		ap.setWebsite("https://www.google.com");
		ap.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.68178);
		l.setLng(33.783278);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
	}

}
