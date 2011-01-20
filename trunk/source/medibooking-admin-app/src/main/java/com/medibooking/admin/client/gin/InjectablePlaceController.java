package com.medibooking.admin.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

/**
 * This class is only to be injactable and the App Place controller can be
 * managed by GIN
 * 
 * @author jpereira
 * 
 */
public class InjectablePlaceController extends PlaceController {

	@Inject
	public InjectablePlaceController(EventBus eventBus) {
		super(eventBus);
	}

}
