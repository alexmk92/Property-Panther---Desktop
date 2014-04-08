package prcse.pp.misc;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 05/04/14
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class Reader {

    private String pathToRead = "";

    /**
     * Creates a new reader object
     * @param path the path to read the file from
     */
    public Reader(String path) {
        pathToRead = path;
    }

    public String readFile() {
        String contents = "";

        try(BufferedReader br = new BufferedReader(new FileReader(pathToRead))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            contents = sb.toString();
        } catch(IOException io) {
            System.out.println(io.getMessage());
        }

        return contents;
    }
}
