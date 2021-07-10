/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author CCK
 */
public class Connection {

    private final Path PATH;

    Validator valid = new Validator();

    /**
     * For the sake of Interface implementation...
     */
    public Connection() {
        this.PATH = Paths.get("test");
    }

    public Connection(String FILENAME) {
        this.PATH = Paths.get("db/" + FILENAME + ".csv");
    }

    /**
     *
     * Get all the line from the text file
     *
     * @return Raw data from the text file
     */
    public List<String> getFromFile() {
        List<String> data = null;
        try {
            data = Files.readAllLines(PATH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    /**
     *
     * https://rollbar.com/guides/java-throwing-exceptions/
     *
     * @param arraylist
     * @return
     */
    public String listToString(List<String> arraylist) {
        String line = new String();
        if (!arraylist.isEmpty()) {
            for (int i = 0; i < arraylist.size(); i++) {
                line += arraylist.get(i) + "\n";
            }
        }
        return line;
    }

    /**
     *
     * Rewrite the text file, make sure the line is properly formatted
     *
     * @param data
     * @return
     */
    public boolean reWrite(String data) {
        boolean success = false;
        try {
            Files.write(PATH, data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            success = true;
        } catch (IOException e) {
            System.out.println("The connection between text file cannot be initiate");
        }
        return success;
    }

    private String getLastUsedID() {
        return getFromFile().get(getFromFile().size() - 1).split("\\,")[0];
    }

    /**
     *
     * Get new ID for the model
     *
     * @return
     */
    public int getNewID() {
        System.out.println(this.getLastUsedID().equals("ID"));
        return this.getLastUsedID().equals("ID")
                ? 1
                : Integer.valueOf(this.getLastUsedID()) + 1;
    }

    /**
     *
     * Change comma to pipe
     *
     * @param input
     * @return
     */
    public String comma2Pipe(String input) {
        return input.replace(',', '|');
    }

    /**
     *
     * Change pipe to comma (Display purpose)
     *
     * @param input
     * @return
     */
    public String pipe2Comma(String input) {
        return input.replace('|', ',');
    }

}
