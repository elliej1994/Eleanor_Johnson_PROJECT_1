package dev.johnson.utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {
    public static void log(String message, LogLevel level){

        String logMessage = level.name() + " " + message + " " + new Date() + "\n";


            System.out.println("logging example");
        /*    Files.write(Paths.get("C:\\Users\\14102\\Documents\\GitHub\\eleanor_johnson_p1\\applogs.log"), logMessage.getBytes(StandardCharsets.UTF_8
            ), StandardOpenOption.APPEND);*/

    }

}
