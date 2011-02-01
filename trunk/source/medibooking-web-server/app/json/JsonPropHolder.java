package json;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class JsonPropHolder {

	private Map<String, Object> props = new HashMap<String, Object>();
	
	public JsonPropHolder add(String key, Object value) {
		this.props.put(key, value);
		return this;
	}

	public String toJson() {
		
		return new Gson().toJson(this.props);
	}
}
