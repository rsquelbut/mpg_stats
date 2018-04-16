package fr.sqlbt.mpg.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.JerseyClientConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Configuration extends io.dropwizard.Configuration {

  @NotEmpty private String leagueId;


  @Valid
  @NotNull
  private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

  @JsonProperty("jerseyClient")
  public JerseyClientConfiguration getJerseyClientConfiguration() {
    return jerseyClient;
  }

  public String getLeagueId() {
    return leagueId;
  }
}
