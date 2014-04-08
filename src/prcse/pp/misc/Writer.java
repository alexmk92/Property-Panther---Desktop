package prcse.pp.misc;

import prcse.pp.controller.ScreensFramework;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Creates a writer object to output to log files
 */
public class Writer {

    // Path to the file and append toggle
    private String path;
    private boolean append = false;


    public Writer(String file, Boolean append_value) {

        // Avoid any case mismatch
        file = file.toUpperCase();

        // Determine which file to write to
        switch(file) {
            case "CONFIG" :
                path = "logs/config.txt";
            break;
            case "ERROR" :
                path = "logs/error_log.txt";
            break;
            case "GENERAL" :
                path = "logs/general_log.txt";
            break;
            default :
                path = "logs/general_log.txt";
            break;
        }

        // True or false (append to top of file or not)
        append = append_value;
    }

    public void writeToFile(String message) {

        try {
            FileWriter write = new FileWriter(path, append);
            PrintWriter print_line = new PrintWriter(write);

            if(path != "logs/config.txt") {
                Calendar c = Calendar.getInstance();

                SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                String dateOut = date.format(c.getTime());

                print_line.printf(dateOut + ": %s" + "%n", message);
                print_line.close();
            } else {
                print_line.printf( "%s" + "%n", message);
                print_line.close();
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
