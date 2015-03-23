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
 * @version = 1.1
 */
public class Output {
    private static Output output = new Output();
    private ResourceBundle resourceBundle;

    /**
     * Constructor, Gets the appropriate ressource Bundle, defaults to English
     */
    public Output() {
        Locale local = Locale.getDefault();
        try {
            resourceBundle = ResourceBundle.getBundle("finaltwo.messages.messages", local);
        } catch (MissingResourceException e) {
            resourceBundle = ResourceBundle.getBundle("finaltwo.messages.messages", Locale.ENGLISH);
        }

    }

    /**
     * @param key message key
     * @return returns the message of the specified language
     */
    public String getString(String key) {
        if (key.equals("USAGE")) {
            return output.getString("Usage.1") + output.getString("Usage.2") + output.getString("Usage.3")
                    + output.getString("Usage.4") + output.getString("Usage.5") + output.getString("Usage.6")
                    + output.getString("Usage.7") + output.getString("Usage.8") + output.getString("Usage.9")
                    + output.getString("Usage.10") + output.getString("Usage.11");
        } else {
            if (key.equals("HELP")) {
                return output.getString("Help.1") + output.getString("Help.2") + output.getString("Help.3")
                        + output.getString("Help.4") + output.getString("Help.5");
            } else {
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
        }
    }

    /**
     * Directly prints a message
     * @param key message key
     * @param isError True if Message to print is an errormessage
     * @param printUsage should print usage?
     */
    public static void printMessage(String key, boolean isError, boolean printUsage) {
        String message = "";
        if (isError) message += "Error, ";
        message += output.getString(key);
        if (printUsage) {
            message += "\n\n" + output.getString("USAGE");
        }

        Terminal.printLine(message);
    }

}