package models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class UserSession extends BaseModel {
	
	
	public static final int DEFAULT_SESSION_DURATION = 1; //Days
	private static final String PRIVATE_KEY = UUID.randomUUID().toString();
	private static final String SEPARATOR = ":";
	private String sessionId;
	private Long userId;
	private Long startTimeStamp;
	private Long endTimeStamp;
	private Long lastActivityTimeStamp;
	private String ipAddress;
	public UserSession() {
		
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public Long getStartTimeStamp() {
		return startTimeStamp;
	}

	public void setStartTimeStamp(Long startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}

	public Long getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(Long endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public Long getLastActivityTimeStamp() {
		return lastActivityTimeStamp;
	}

	public void setLastActivityTimeStamp(Long lastActivityTimeStamp) {
		this.lastActivityTimeStamp = lastActivityTimeStamp;
	}

	public void create(Long userId, String ipAddress) {
		create(userId, ipAddress, DEFAULT_SESSION_DURATION);
	}
	public void create(Long userId, String ipAddress, int duration ) {
		//generate SessionID
		this.startTimeStamp = System.currentTimeMillis();
		this.lastActivityTimeStamp = System.currentTimeMillis();
		this.endTimeStamp = this.lastActivityTimeStamp+duration*24*60*60*1000;
		this.userId = userId;
		this.ipAddress = ipAddress;
	
		//useless computation cycles?
		this.sessionId = Crypto.sign(UUID.randomUUID()+SEPARATOR+this.startTimeStamp+PRIVATE_KEY) ;
		this.save();
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	
	
}
