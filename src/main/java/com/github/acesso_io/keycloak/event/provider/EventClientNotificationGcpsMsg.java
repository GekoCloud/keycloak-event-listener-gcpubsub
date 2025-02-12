package com.github.acesso_io.keycloak.event.provider;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.keycloak.events.Event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(use = Id.CLASS)
public class EventClientNotificationGcpsMsg extends CustomEventAttributes implements Serializable {

	private static final long serialVersionUID = -2192461924304841222L;

	private String appName;

	public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

	public static EventClientNotificationGcpsMsg create(Event event, String appName) {
		EventClientNotificationGcpsMsg msg = new EventClientNotificationGcpsMsg();
		msg.setClientId(event.getClientId());
		msg.setDetails(event.getDetails());
		msg.setError(event.getError());
		msg.setIpAddress(event.getIpAddress());
		msg.setRealmId(event.getRealmId());
		msg.setSessionId(event.getSessionId());
		msg.setTime(event.getTime());
		msg.setType(event.getType());
		msg.setUserId(event.getUserId());
		msg.setAppName(appName);

		return msg;
	}	
}
