package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void preRequisite() throws IOException {
		
		//write a code here to generate a place_id only if AddPlace method is not run i.e. Only when place_id is null
		PlaceValidation pv = new PlaceValidation();
		
		if(PlaceValidation.place_id==null) {
			pv.add_place_payload("OmPatel", "French", "64, LakeView");
			pv.user_calls_with_http_request("addPlaceAPI", "POST");
			pv.verify_placeid_created_maps_to_something_using_something("OmPatel", "getPlaceAPI");
		}
				
	}

}
