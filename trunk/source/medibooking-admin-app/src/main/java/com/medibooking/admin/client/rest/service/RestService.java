package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.medibooking.admin.client.event.JsonResultAvailableEvent;
import com.medibooking.admin.client.event.RequestEvent;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.SimpleURL;
import com.medibooking.admin.client.view.widget.ErrorWindow;

public abstract class RestService {

	protected EventBus eventBus;
	protected JsonResult jsonResult;

	private void startRequest() {

		this.eventBus.fireEvent(new RequestEvent(RequestEvent.State.START));
	}

	private void endRequest() {
		this.eventBus.fireEvent(new RequestEvent(RequestEvent.State.END));
	}

	private void sendRequest(RequestBuilder builder, String jsonData,
			final JsonResultAvailableCallback callback, final boolean silent) {
		startRequest();

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		Request request = null;

		try {
			request = builder.sendRequest(jsonData,

			new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					jsonResult = new JsonResult(response.getText()).parse();

					if (!silent) {
						handleJSONResult();
					}

					callback.onJsonResultAvaialble(jsonResult);
					endRequest();
				}

				@Override
				public void onError(Request request, Throwable exception) {
					handleError(exception);
					endRequest();
				}

			});
		} catch (RequestException e) {
			handleError(e);
			endRequest();
		}
	}

	protected void put(SimpleURL url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {
		put(url.build(), jsonData, callback, silent);
	}

	protected void put(String url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, url);
		sendRequest(builder, jsonData, callback, silent);
	}

	protected void delete(SimpleURL url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {
		delete(url.build(), jsonData, callback, silent);
	}

	protected void delete(String url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, url);
		sendRequest(builder, jsonData, callback, silent);
	}

	protected void post(String url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, url);
		sendRequest(builder, jsonData, callback, silent);

	}

	/**
	 * POST jsonData to the url
	 * 
	 * @param url
	 *            Url used to post
	 * @param jsonData
	 *            json to send to server
	 */
	protected void post(SimpleURL url, String jsonData,
			final JsonResultAvailableCallback callback, boolean silent) {
		post(url.build(), jsonData, callback, silent);
	}

	/**
	 * Handle errors related with the communication with server
	 * 
	 * @param e
	 */
	private void handleError(Throwable e) {
		ErrorWindow.show(e);
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
		eventBus.fireEvent(new JsonResultAvailableEvent(this.jsonResult));

	}
}
