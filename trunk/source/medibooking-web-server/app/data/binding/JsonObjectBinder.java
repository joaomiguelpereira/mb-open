package data.binding;

import java.io.IOException;
import java.lang.annotation.Annotation;

import json.JsonObjectWrapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import play.Logger;
import play.data.binding.Global;
import play.data.binding.TypeBinder;

@Global
public class JsonObjectBinder implements TypeBinder<JsonObjectWrapper> {

	@Override
	public Object bind(String name, Annotation[] annotations, String value,
			Class actualClass) throws Exception {
		JsonElement jsEl = new JsonParser().parse(value);
		
		if ( jsEl!= null && jsEl.isJsonObject() ) {
			return new JsonObjectWrapper(jsEl.getAsJsonObject());
		} else if (jsEl!=null){
			throw new Exception("The value ["+value+"] could not be binded to a JsonObject");
		} else {
			return null;
		}
		
	}

}
