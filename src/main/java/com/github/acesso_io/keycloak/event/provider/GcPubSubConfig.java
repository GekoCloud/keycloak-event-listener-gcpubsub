package com.github.acesso_io.keycloak.event.provider;

import org.keycloak.Config.Scope;
import org.jboss.logging.Logger;
import java.util.Objects;

public class GcPubSubConfig {

	private static Logger logger = Logger.getLogger(GcPubSubConfig.class);

	private String projectId;
	private String eventTopicId;
	private String adminEventTopicId;
	private String appNameId;
	
	public static GcPubSubConfig createFromScope(Scope config) {
		GcPubSubConfig cfg = new GcPubSubConfig();
		
		cfg.projectId = resolveConfigVar(config, "projectId", "keycloak-test");
		cfg.eventTopicId = resolveConfigVar(config, "eventTopicId", "keycloak-events");
		cfg.adminEventTopicId = resolveConfigVar(config, "adminEventTopicId", "keycloak-events");
		cfg.appNameId = resolveConfigVar(config, "app_name", "keycloak-app");

		Objects.requireNonNull(cfg.projectId, "projectId must not be null.");
        Objects.requireNonNull(cfg.eventTopicId, "eventTopicId must not be null.");
        Objects.requireNonNull(cfg.adminEventTopicId, "adminEventTopicId must not be null");
        Objects.requireNonNull(cfg.appNameId, "appNameId must not be null");
		
		return cfg;
		
	}
	
	private static String resolveConfigVar(Scope config, String variableName, String defaultValue) {
		
		String value = defaultValue;
		if(config != null && config.get(variableName) != null) {
			value = config.get(variableName);
		} else {
			//try from env variables eg: KC_TO_GCP_PROJECTID:
			String envVariableName = "KC_TO_GCP_" + variableName.toUpperCase();
			if(System.getenv(envVariableName) != null) {
				value = System.getenv(envVariableName);
			}
		}
		logger.infof("keycloak-to-gcpubsub configuration: %s=%s", variableName, value);
		return value;
		
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getEventTopicId() {
		return eventTopicId;
	}
	public void setEventTopicId(String eventTopicId) {
		this.eventTopicId = eventTopicId;
	}
	public String getAdminEventTopicId() {
		return adminEventTopicId;
	}
	public void setAdminEventTopicId(String adminEventTopicId) {
		this.adminEventTopicId = adminEventTopicId;
	}
	public String getAppNameId() {
		return appNameId;
	}
	public void setAppNameId(String appNameId) {
		this.appNameId = appNameId;
	}

}
