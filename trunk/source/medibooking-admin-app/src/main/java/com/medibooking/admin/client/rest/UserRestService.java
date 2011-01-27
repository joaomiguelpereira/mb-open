package com.medibooking.admin.client.rest;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.medibooking.admin.shared.entity.User;

public class UserRestService {
	
	public void createUser(User user) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
				ServerRouteResolver.UserRoutes.getCreate());

		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");

		Request request = null;
		try {
			request = builder.sendRequest(User.WRITER.toJson(user),
					new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							JsonResult jsonResult = new JsonResult(response
									.getText()).parse();
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

							// want an object like
							/*
							 * String result.getErrorMessage(); String
							 * result.getSuccessMessage(); String
							 * result.getWarningMessage(); List<String>
							 * result.getFieldErrors("fieldName"); Long
							 * result.getId();
							 */

							Window.alert("OK: " + response.getText());

						}

						@Override
						public void onError(Request request, Throwable exception) {
							// Inform presneter though event bus
							Window.alert("FAIL:" + exception.getMessage());

						}
					});
		} catch (RequestException e) {

			Window.alert("EXC:" + e.getMessage());
			e.printStackTrace();
		}

	}
}
