package pl.com.tegess.RetrospectionSystem.parsers;

/**
 * Created by Szymek.
 */
public class DefaultMailsParserFactory implements MailsParserFactory {
    @Override
    public MailsParser createMailsParser(String fileContentType) {
        if("application/vnd.ms-excel".equals(fileContentType)) return new CSVMailsParser();
        return null;
    }
}
