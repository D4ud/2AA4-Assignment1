package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if ((args.length == 2 && args[0].equals("-i")) || (args.length == 4 && args[0].equals("-i") && args[2].equals("-method"))) {
            String mazeFilePath = args[1];
            String solverName = (args.length == 4) ? args[3] : "righthand";
            MazeMap mazeGrid = new MazeMap(mazeFilePath);
            MazeSolverAlgorithm solverAlgorithm = selectSolverAlgorithm(solverName);
            if (solverAlgorithm == null) {
                System.out.println("Invalid solver type: " + solverName);
                return;
            }
            MazeExplorer explorer = new MazeExplorer(mazeGrid, solverAlgorithm);
            String compressedPath = explorer.computeCompressedPath();
            System.out.println(compressedPath);
        } else if (args.length >= 4 && args[0].equals("-i") && args[2].equals("-p")) {
            String mazeFilePath = args[1];
            String providedPath = Arrays.stream(Arrays.copyOfRange(args, 3, args.length))
                    .collect(Collectors.joining(" "));
            MazeMap mazeGrid = new MazeMap(mazeFilePath);
            MazeSolverAlgorithm solverAlgorithm = new RightHandMazeSolver();
            MazeExplorer explorer = new MazeExplorer(mazeGrid, solverAlgorithm);
            boolean valid = explorer.validateProvidedPath(providedPath);
            System.out.println(valid ? "correct path" : "incorrect path");
        } else {
            System.out.println("Usage: '-i MAZE_FILE' OR '-i MAZE_FILE -p PATH_SEQUENCE' OR '-i MAZE_FILE -method {tremaux, righthand}'");
        }
    }

    private static MazeSolverAlgorithm selectSolverAlgorithm(String solverName) {
        switch (solverName.toLowerCase()) {
            case "righthand": return new RightHandMazeSolver();
            case "tremaux": return new TremauxMazeSolver();
            default: return null;
        }
    }
}
