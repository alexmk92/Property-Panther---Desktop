package prcse.pp.misc;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import prcse.pp.controller.ScreensFramework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 20/04/14
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public class UploadImage {

    private FTPClient        client;
    private FileInputStream  fis;
    private FileOutputStream fos;

    /**
     * Constructs an upload Object
     */
    public UploadImage() {
        this.client = new FTPClient();
    }

    /**
     * connects to our FTP server, requires no parameters
     * as its a one time configuration setup
     * @param thisFile - the file we are uploading
     * @param destination - the destination folder, properties OR thumbnails OR rooms
     * @param extension - the type of file we are uploading
     */
    public Boolean upload (String thisFile, String extension, String destination) {

        Boolean uploaded = false;
        try {
            // Connect to our client
            connect();

            // Get the file to upload and set its destination
            fis = new FileInputStream(thisFile);

            // Write file to the server, close connection and input stream
            client.storeFile(destination + "test" + extension, fis);
            fis.close();

            // Disconnects from the server
            client.logout();
            client.disconnect();

            // Set flag to true
            uploaded = true;
        } catch (IOException e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
            }
        }
        return uploaded;
    }

    /**
     * Pulls a file down from the server to populate an image view
     * @param subject_id - either Room, Properties or Users
     * @param folder - the Room, Property or User folder we wish to pull from
     * @param format - the format of the file we are downloading
     * @return true if downloaded else false
     */
    public Boolean download(String subject_id, String format, String folder) {
        Boolean downloaded = false;

        try {
            // Connect to our client
            connect();

            // Set the image path to save to - either rooms, properties or users
            String localPath = System.getProperty("user.dir") + "\\src\\prcse\\pp\\view\\img\\" + folder + "\\";

            // Get the file to upload and set its destination
            fos = new FileOutputStream(localPath + subject_id + format);

            // Read file from the server, close connection and input stream
            client.retrieveFile(folder + "/" + subject_id + format, fos);
            fos.close();

            // Disconnects from the server
            client.logout();
            client.disconnect();

            // Set flag to true
            downloaded = true;
        } catch (IOException e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
            }
        }

        return downloaded;
    }

    /**
     * Connects
     */
    private void connect() throws IOException {
        client.connect("propertypanther.info");
        client.login("prcse", "bl@ckpotato");

        // Set passive mode to ensure upload
        client.enterLocalPassiveMode();

        // Set the transfer type
        client.setFileType(FTP.BINARY_FILE_TYPE);
        client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
    }

}
