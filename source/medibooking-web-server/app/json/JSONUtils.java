package json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import play.Logger;
import play.data.binding.Binder;
import play.data.binding.Unbinder;
import play.data.validation.Error;
import play.db.jpa.Model;
import play.i18n.Messages;
import play.mvc.Router;
import play.mvc.Router.ActionDefinition;
import play.mvc.Scope.Params;

public class JSONUtils {

	public static final String ERROR_MESSAGE_PROP = "__errorMessage";
	public static final String WARNING_MESSAGE_PROP = "__warningMessage";
	public static final String SUCCESS_MESSAGE_PROP = "__successMessage";
	public static final String STATUS_PROP = "__status";
	public static final String ID_PROP = "__id";
	public static final String ERRORS_PROP = "__errors";
	
	public static class Status {
		
		//not related with http status codes
		public static final Integer SUCCESS = 200;
		public static final Integer FAIL = 500;
		
	}
	
	public static String successMessage(String i18nKey) {
		Map<String, String> jsonOut = new HashMap<String, String>();
		jsonOut.put(SUCCESS_MESSAGE_PROP, Messages.get(i18nKey));
		return new Gson().toJson(jsonOut);
	}
	
	
	public static String errorMessage(String i18nKey) {
		Map<String, String> jsonOut = new HashMap<String, String>();
		jsonOut.put(ERROR_MESSAGE_PROP, Messages.get(i18nKey));
		return new Gson().toJson(jsonOut);
	}
	
	public static String warningMessage(String i18nKey) {
		Map<String, String> jsonOut = new HashMap<String, String>();
		jsonOut.put(WARNING_MESSAGE_PROP, Messages.get(i18nKey));
		return new Gson().toJson(jsonOut);
	}
	
	public static <T extends Model> boolean mergeFromJson(T target,
			String varName, Params params) {
		T source = (T) new Gson().fromJson(params.get("body"),
				target.getClass());
		Logger.debug("Body: "+ params.get("body"));
		boolean result = false;
		if (source != null) {
			result = !result;
			Map<String, Object> tmpParams = new HashMap<String, Object>();
			Unbinder.unBind(tmpParams, source, varName);
			// Transform into params
			for (String key : tmpParams.keySet()) {
				Object value = tmpParams.get(key);
				if (value != null) {
					// is String, int, long?
					if (value instanceof String || value instanceof Integer
							|| value instanceof Long) {
						params.put(key, value.toString());
					}
					// TODO: Rest of the cases when needed
				}
			}
			// Now bind
			Binder.bind(target, varName, params.all());
		}
		return result;

	}


	public static String createRedirectionTo(String url, String i18nKey) {
		Map<String, String> jsonOut = new HashMap<String, String>();
		jsonOut.put(ERROR_MESSAGE_PROP, Messages.get(i18nKey));
		ActionDefinition ad = Router.reverse(url);
		ad.absolute();
		jsonOut.put("redirectTo",ad.url);
		
		return new Gson().toJson(jsonOut);
		
		
	}
	

}