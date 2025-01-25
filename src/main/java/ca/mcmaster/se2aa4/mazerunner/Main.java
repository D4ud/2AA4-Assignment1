package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "maze file path");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String mazeFilePath = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file: {}", mazeFilePath);

                try (BufferedReader reader = new BufferedReader(new FileReader(mazeFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                System.out.print("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                System.out.print("PASS ");
                            }
                        }
                        System.out.print(System.lineSeparator());
                    }
                } catch (Exception e) {
                    logger.error("/!\\ An error occurred while reading the maze file /!\\");
                }
            } else {
                logger.error("/!\\ No input file provided. Use -i to specify the file path /!\\");
            }
        } catch (ParseException e) {
            logger.error("/!\\ Error parsing command-line arguments /!\\");
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
