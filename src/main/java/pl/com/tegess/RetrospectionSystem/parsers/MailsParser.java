package pl.com.tegess.RetrospectionSystem.parsers;

import java.io.File;
import java.util.List;

/**
 * Created by Szymek.
 */
public interface MailsParser {

    public List<String> getMails(File file);
}
