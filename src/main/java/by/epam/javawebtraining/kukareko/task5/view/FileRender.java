package by.epam.javawebtraining.kukareko.task5.view;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Yulya Kukareko
 * @version 1.0 31 Mar 2019
 */
public class FileRender implements Renderer {

    public static final Logger LOGGER;
    private static String fileName = "src/main/resources/logic/parking";

    static {
        LOGGER = Logger.getLogger("FileRender");
    }

    @Override
    public void render(String data) {

        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName, true))) {

            br.write(data + "\n");
            br.flush();

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

    }
}
