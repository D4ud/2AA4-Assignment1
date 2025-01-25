package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {
    private static final Logger logger = LogManager.getLogger();

    public void run(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "MAZE_FILE");
        options.addOption("p", "path", true, "PATH_SEQUENCE");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String mazeFilePath = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file: {}", mazeFilePath);

                MazeWalker mazeWalker = new MazeWalker(mazeFilePath);

                if (cmd.hasOption("p")) {
                    String pathSequence = cmd.getOptionValue("p").replaceAll(" ", "");
                    boolean isValid = mazeWalker.validatePath(pathSequence);
                    logger.info(isValid ? "correct path" : "incorrect path");
                } else {
                    printMaze(mazeWalker);
                }
            } else {
                logger.error("/!\\ No input file provided. Use -i to specify the file path /!\\");
            }
        } catch (ParseException e) {
            logger.error("/!\\ Error parsing command-line arguments /!\\", e);
        }
    }

    private void printMaze(MazeWalker mazeWalker) {
        char[][] maze = mazeWalker.getMaze();
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell == '#' ? "WALL " : "PASS ");
            }
            System.out.println();
        }
    }
}
