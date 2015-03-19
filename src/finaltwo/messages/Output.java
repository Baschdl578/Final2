package finaltwo.messages;

import edu.kit.informatik.Terminal;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This handles the resource packs.
 * Chooses the machines locale, if no translation is available, defaults to english
 *
 * Created by Sebastian on 22.02.2015.
 *
 * @author = Sebastian Schindler
 * @version = 1.0
 */
public class Output {
    private static Output output = new Output();
    private ResourceBundle resourceBundle;

    /**
     * Constructor
     */
    public Output() {
        Locale local = Locale.getDefault();
        try {
            resourceBundle = ResourceBundle.getBundle("recommendations.errors.messages", local);
        } catch (MissingResourceException e) {
            resourceBundle = ResourceBundle.getBundle("recommendations.errors.messages", Locale.ENGLISH);
        }

    }
    /**
     * @param key message key
     * @return returns the message of the specified language
     */
    public String getString(String key)
    {
        try
        {
            return resourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            Terminal.printLine("Error, missing String ressource: " + key);
            System.exit(10);
            return null; //Dont know why this is required after exit statement
        }
    }

    /**
     * Directly prints a message
     * @param key message key
     * @param isError True if Message to print is an errormessage
     */
    public static void printMessage(String key, boolean isError) {
        String message = "";
        if (isError) message += "Error, ";
        message += output.getString(key);
        Terminal.printLine(message);
    }
}