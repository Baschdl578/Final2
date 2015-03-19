package finaltwo;

import edu.kit.informatik.FileInputHelper;
import finaltwo.messages.Output;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Setup {

    private static String[] file;

    public void setup(String filepath) {
        file = FileInputHelper.read(filepath);
        checkfile();
    }

    private void checkfile() {
        String regex = "[0-4a-zA-Z\\*]*";
        int length = -1;
        for (String line: file) {

            if (length != -1) {
                if (line.length() != length) {
                    Output.printMessage("Setup.1", true);
                    System.exit(1);
                }
            } else {
                length = line.length();
            }

            if (!line.matches(regex)) {
                Output.printMessage("Setup.1", true);
                System.exit(1);
            }
        }
    }
}
