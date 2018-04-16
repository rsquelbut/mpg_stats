package fr.sqlbt.mpg.stats;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class Application extends io.dropwizard.Application<Configuration> {
  @Override
  public void run(Configuration configuration, Environment environment) throws Exception {

    final Client client =
        new JerseyClientBuilder(environment)
            .using(configuration.getJerseyClientConfiguration())
            .build(getName());
//    environment.jersey().register(new ExternalServiceResource(client));
  }
}
