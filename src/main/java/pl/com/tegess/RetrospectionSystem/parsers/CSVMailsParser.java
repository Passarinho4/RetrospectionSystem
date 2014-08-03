package pl.com.tegess.RetrospectionSystem.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymek.
 */
public class CSVMailsParser implements MailsParser {

    @Override
    public List<String> getMails(File file) {
        List<String> mails = new ArrayList<String>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = bufferedReader.readLine())!=null){
                mails.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mails;
    }
}
