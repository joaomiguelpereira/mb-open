package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.medibooking.admin.client.rest.JsonResult;

public class RestService {

	private EventBus eventBus;
	private JsonResult jsonResult;

	/**
	 * POST jsonData to the url
	 * 
	 * @param url
	 *            Url used to post
	 * @param jsonData
	 *            json to send to server
	 */
	protected void post(String url, String jsonData) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		Request request = null;

		try {
			request = builder.sendRequest(jsonData,

			new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					jsonResult = new JsonResult(response.getText()).parse();

					handleJSONResult();
				}

				@Override
				public void onError(Request request, Throwable exception) {
					handleError(exception);
				}

			});
		} catch (RequestException e) {
			handleError(e);
		}
	}

	/**
	 * Handle errors related with the communication with server
	 * 
	 * @param e
	 */
	private void handleError(Throwable e) {
		Window.alert(e.getMessage());
	}

	/**
	 * Constructor with an event bus
	 * 
	 * @param eventBus
	 */
	public RestService(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * Check global status for JSON Result and propagate the status thorugh
	 * event bus
	 */
	protected void handleJSONResult() {
		Window.alert("OK: " + jsonResult.getJsonString());

		// get the jsonResult back to the presenter... use
		// the event bus for this
		// before sending the result back to the presenter,
		// send also to some activiy, or something, to show
		// the general statis (if error show in header
		// something like "ooops! error..." adn same as
		// succes and warnign
		// check if errors exist in the responseindex
		// Configure the event bus so that a waiting mole is
		// show in every event. See expenses project...

	}
}
