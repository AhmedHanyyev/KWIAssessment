package ui_automation.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigurationReader {
    private static Properties properties;

    public static String getUiProperty(String key){
        String path = "src/test/resources/ui-config.properties";
        try {
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);

    }



    public static String getProperty(String fileName, String key) {
        try {
            // based on the filename create a dynamic path
            String path = "src/test/resources/"+ fileName;
            // read the file as a stream
            FileInputStream stream = new FileInputStream(path);
            // properties object will be able to divide file into key and value
            properties = new Properties();
            // loading the file that we read into properties object
            properties.load(stream);
            // close the file stream
            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        // read the value based on the key from properties
        String value = properties.getProperty(key);
        return value;
    }
}