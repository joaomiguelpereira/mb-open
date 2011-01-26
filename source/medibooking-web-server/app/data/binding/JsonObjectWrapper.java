package data.binding;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.enums.UserType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonObjectWrapper {

	private JsonObject value;
	private Gson gsonInstance;

	public JsonObjectWrapper(JsonObject value) {
		this.value = value;
	}

	public void setValue(JsonObject value) {
		this.value = value;
	}

	public JsonObject getValue() {
		return value;
	}

	public String getStringProperty(String propName) {
		String stringPropValue = null;
		if (value != null ) {
			//Check if prop exists
			if (value.get(propName) != null && !value.get(propName).isJsonNull() && value.get(propName).isJsonPrimitive() ) {
				stringPropValue = value.get(propName).getAsString();
			}
		}
		return stringPropValue;
	}

	private Gson getGson() {
		if (this.gsonInstance == null) {
			this.gsonInstance = new Gson();
		}
		return this.gsonInstance;
	}

	public <T extends Object> T getAs(Class<T> clazz) {
		return getGson().fromJson(this.value, clazz);
		
	}

	public boolean getBooleanProperty(String propName) {
		boolean booleanPropValue = false;
		if (value != null ) {
			//Check if prop exists
			if (value.get(propName) != null && !value.get(propName).isJsonNull() && value.get(propName).isJsonPrimitive() ) {
				booleanPropValue =  value.get(propName).getAsBoolean();
			}
		}
		return booleanPropValue;
	}

	public JsonObjectWrapper getPropertyAsJsonObject(String propName) {
		JsonObjectWrapper jsObjectPropValue = null;
		if ( value!= null ) {
			if (value.get(propName)!= null && !value.get(propName).isJsonNull() && value.get(propName).isJsonObject()) {
				jsObjectPropValue = new JsonObjectWrapper(value.get(propName).getAsJsonObject());
			}
		}
		return jsObjectPropValue;
	}

	/**
	 * Return a list of string from a property that is a JsonArray containing JsonPrimitives
	 * @param propName
	 * @return
	 */
	public List<String> getStringArrayProperty(String propName) {
		List<String> arrayPropValue = null;
		
		if ( value!= null ) {
			if (value.get(propName)!= null && !value.get(propName).isJsonNull() && value.get(propName).isJsonArray()) {
				JsonArray jsArray = value.get(propName).getAsJsonArray();
				arrayPropValue  = new ArrayList<String>();
				for (JsonElement jsEl : jsArray ) {
					//check if the object contained is a primitive
					if ( jsEl.isJsonPrimitive() ) {
						arrayPropValue.add(jsEl.getAsString());
					}
				}
			}
		}
		
		return arrayPropValue;
	}

	public <T extends Enum<T>> T getEnumProperty(String propName, Class<T> enumClazz) {
		T enumVal = null;
		String value = getStringProperty(propName);
		if (  value  != null ) {
			enumVal = Enum.valueOf(enumClazz,value);
		}
		return enumVal;
	}

}
