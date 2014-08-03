package pl.com.tegess.RetrospectionSystem.parsers;

/**
 * Created by Szymek.
 */
public interface MailsParserFactory {

    public MailsParser createMailsParser(String fileContentType);
}
