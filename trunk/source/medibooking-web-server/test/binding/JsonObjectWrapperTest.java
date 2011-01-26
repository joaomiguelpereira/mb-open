package binding;

import json.JsonObjectWrapper;
import models.Business;
import models.User;

import org.junit.Test;

import play.test.UnitTest;
import static org.junit.Assert.*;
import com.google.gson.Gson;

import data.binding.JsonObjectBinder;

public class JsonObjectWrapperTest extends UnitTest {

	@Test
	public void testGetPropertyAsString() {
		User user = new User();
		user.setName("userName");
		user.setEmail("email");
		user.setPhone("phone");
		String jsonStr = new Gson().toJson(user);
		JsonObjectBinder binder = new JsonObjectBinder();
		JsonObjectWrapper jsWrapped = null;
		try {
			jsWrapped = (JsonObjectWrapper)binder.bind("", null, jsonStr, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("no exception expected");
		}
		String name = jsWrapped.getStringProperty("name");
		assertEquals("userName", name);
		
		String inexistent = jsWrapped.getStringProperty("nameInexistend");
		assertNull(inexistent);
		

	}
	@Test
	public void testGetAs() {
		User user = new User();
		user.setName("userName");
		user.setEmail("email");
		user.setPhone("phone");
		String jsonStr = new Gson().toJson(user);
		JsonObjectBinder binder = new JsonObjectBinder();
		JsonObjectWrapper jsWrapped = null;
		try {
			jsWrapped = (JsonObjectWrapper)binder.bind("", null, jsonStr, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("no exception expected");
		}
		
		//try to convert back to a User
		User oUser = jsWrapped.getAs(User.class);
		assertNotNull(oUser);
		assertEquals(oUser.getName(), user.getName());
		
		assertEquals(oUser.getEmail(), user.getEmail());
		assertEquals(oUser.getPhone(), user.getPhone());
		
		
	}
}
