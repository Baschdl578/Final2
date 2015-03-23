package finaltwo;

import edu.kit.informatik.FileInputHelper;
import finaltwo.ants.LazyAnt;
import finaltwo.ants.SportyAnt;
import finaltwo.ants.StandardAnt;
import finaltwo.messages.Output;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Setup {
    private static String[] file;

    /**
     * Sets the game up from input file
     * @param filepath input file
     * @return Top left field
     */
    public static Field setup(String filepath) {
        file = FileInputHelper.read(filepath);
        checkFile();

        Field topLeft = null;
        Field current;
        for (int i = 0; i < file.length; i++) {
            int j = 0;
            current = topLeft;
            Field topRow = current;
            while (j < i - 1 && current != null) {
                topRow = topRow.getSouth();
                current = current.getSouth();
                j++;
            }
            if (current != null) {
                current = current.getSouth();
            }
            char[] chars = file[i].toCharArray();
            for (int k = 0; k < chars.length; k++) {
                Field newField = createField(chars[k], i, k);

                if (topRow != null) {
                    newField.setNorth(topRow);
                    topRow.setSouth(newField);
                    topRow = topRow.getEast();
                }

                if (k != 0) {
                    current.setEast(newField);
                    newField.setWest(current);
                }

                if (i == 0 && k == 0) {
                    topLeft = newField;
                }
                current = newField;
            }
        }
        //interlinkRows(topLeft);
        return topLeft;
    }

    /**
     * Checks the read file for errors
     */
    private static void checkFile() {
        String regex = "[0-4a-zA-Z\\*]*";
        String ants = "";
        int length = -1;

        for (String line: file) {
            if (length != -1) {
                if (line.length() != length) {
                    Output.printMessage("Setup.1", true, true);
                    System.exit(1);
                }
            } else {
                length = line.length();
            }

            if (!line.matches(regex)) {
                Output.printMessage("Setup.1", true, true);
                System.exit(1);
            }

            for (char c: line.toCharArray()) {  //Check for multiple Ants of the same name
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    String tmp = "";
                    tmp += Character.toLowerCase(c);
                    if (ants.contains(tmp)) {
                        Output.printMessage("Setup.1", true, true);
                        System.exit(1);
                    }
                    ants += tmp;
                }
            }
        }
    }

    /**
     * Setup() sets north/south links in the first column. this interlinks the rest
     * @param topLeft topLeft Field
     */
/*    private static void interlinkRows(Field topLeft) {
        Field currentRowLeft = topLeft;
        Field nextRowLeft;
        Field currentField;
        Field nextRowField;
        while (currentRowLeft.getSouth() != null) {
            nextRowLeft = currentRowLeft.getSouth();
            currentField = currentRowLeft;
            nextRowField = nextRowLeft;

            while (currentField != null) {
                currentField.setSouth(nextRowField);
                nextRowField.setNorth(currentField);

                currentField = currentField.getEast();
                nextRowField = nextRowField.getEast();
            }
            currentRowLeft = nextRowLeft;
        }
    }
*/
    /**
     * Creates and returns a new Field. Also creates an Ants and places it on it if neccessary
     * @param descr Field descriptor
     * @param line for positioning
     * @param pos for positioning
     * @return created Field
     */
    private static Field createField(char descr, int line, int pos) {
        Field newField = null;

        if (descr == '*') {
            newField = new Field(0, true, false, null, line, pos);
        }
        if ('0' <= descr && '5' > descr) {
            int color = Character.getNumericValue(descr);
            newField = new Field(color, false, false, null, line, pos);
        }
        if ('a' <= descr && 'h' >= descr) {
            StandardAnt newAnt = new StandardAnt(180, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }
        if ('i' <= descr && 'q' >= descr) {
            SportyAnt newAnt = new SportyAnt(180, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }
        if ('r' <= descr && 'z' >= descr) {
            LazyAnt newAnt = new LazyAnt(180, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }
        if ('A' <= descr && 'H' >= descr) {
            StandardAnt newAnt = new StandardAnt(0, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }
        if ('I' <= descr && 'Q' >= descr) {
            SportyAnt newAnt = new SportyAnt(0, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }
        if ('R' <= descr && 'Z' >= descr) {
            LazyAnt newAnt = new LazyAnt(0, descr);
            Main.addAnt(newAnt);
            newField = new Field(0, false, true, newAnt, line, pos);
            newAnt.setPosition(newField);
        }

        return newField;
    }

}
