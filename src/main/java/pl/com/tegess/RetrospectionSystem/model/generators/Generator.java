package pl.com.tegess.RetrospectionSystem.model.generators;

import java.util.List;

/**
 * Created by Szymek.
 */
public interface Generator {

    public String getId();
    public List<String> getTokens(Integer amount);

}
