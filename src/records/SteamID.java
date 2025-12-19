package records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SteamID(@JsonAlias("steamAppID") String idSteam, @JsonAlias("external") String titulo) {
}
