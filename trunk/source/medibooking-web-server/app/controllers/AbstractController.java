package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import play.Logger;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Scope.Params;
import utils.JSONUtils;

public abstract class AbstractController extends Controller {

	protected static void logValidationErrors() {

		Map<String, List<play.data.validation.Error>> errors = Validation
				.current().errorsMap();

		for (List<play.data.validation.Error> theErrors : errors.values()) {
			for (Error error : theErrors) {
				Logger.debug("Error on " + error.getKey() + ":"
						+ error.message());
			}
		}

	}

	/**
	 * Create a json representation of validation errors.
	 * 
	 * @param i18nKey The i18n key to user to generate the message
	 * @param varName the varName, i.e, name of the model being validated
	 * @param stripModelName if true, the model name will be removed from the result
	 */
	protected static void renderJsonValidationErrors(String i18nKey, String varName,
			boolean stripModelName) {
		Map<String, List<Error>> validations = Validation.current().errorsMap();

		Map<String, String[]> jsonErrors = new HashMap<String, String[]>();

		for (String field : validations.keySet()) {
			String errors[] = new String[validations.get(field).size()];
			int i = 0;
			for (Error error : validations.get(field)) {
				errors[i++] = error.message();
			}
			if (stripModelName) {
				field = field.replaceFirst(varName+"\\.", "");
			}
			jsonErrors.put(field, errors);
		}

		Map<String, Object> jsonOutMap = new HashMap<String, Object>();
		jsonOutMap.put(JSONUtils.MESSAGE_ERROR, Messages.get(i18nKey));
		jsonOutMap.put("errors", jsonErrors);
		String json = new Gson().toJson(jsonOutMap);
		Logger.debug(json);
		renderJSON(json);

	}

	/**
	 * Create two json Objects... TODO:Docs
	 * 
	 * @param i18nKey
	 * @param varName
	 */
	protected static void renderJsonValidationErrors(String i18nKey, String varName) {
		renderJsonValidationErrors(i18nKey, varName, false);

	}

	protected static void jsonError(String i18nKey) {
		renderJSON(JSONUtils.errorMessage(i18nKey));
	}

	protected static void warningSuccess(String i18nKey) {
		renderJSON(JSONUtils.warningMessage(i18nKey));
	}

	protected static void jsonSuccess(String i18nKey) {
		renderJSON(JSONUtils.successMessage(i18nKey));
	}

	protected static void inspectParams(Params params) {
		Map<String, String[]> mapParams = params.all();
		Set<String> keys = mapParams.keySet();

		for (String key : keys) {
			String[] vals = mapParams.get(key);
			for (String value : vals) {
				System.err.println("Key: " + key + " -> " + value);
			}
		}

	}
}
