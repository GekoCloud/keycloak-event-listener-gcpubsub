package com.github.acesso_io.keycloak.event.provider;

import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class GcPubSubEventListenerProviderFactory implements EventListenerProviderFactory {

	private static final String ID = "keycloak-to-gcpubsub";

	private static Logger logger = Logger.getLogger(GcPubSubEventListenerProviderFactory.class);

	private GcPubSubConfig cfg;

	private volatile GcPubSubEventListenerProvider instance;

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public EventListenerProvider create(KeycloakSession session) {
		return createLazily();
	}

	private GcPubSubEventListenerProvider createLazily() {

		GcPubSubEventListenerProvider provider = instance;
        if (provider != null) {
            return provider;
        }

		synchronized(GcPubSubEventListenerProviderFactory.class) {
			if (instance == null) {
				instance = new GcPubSubEventListenerProvider(cfg);
			}
			return instance;
		}
	}

	@Override
	public void init(Scope config) {

		logger.info("Init gcpubsub...");
		cfg = GcPubSubConfig.createFromScope(config);

	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
	}

	@Override
	public void close() {
		logger.info("Shutting down gcpubsub");
        try {
            //instance.shutdown(Duration.ofSeconds(shutdownTimeoutSeconds));
            //log.info("Shutdown KafkaProducer succeeded");
        } catch (Exception ex) {
            //log.errorf(ex, "Error during shutdown of gcpubsub");
        }
	}



}
