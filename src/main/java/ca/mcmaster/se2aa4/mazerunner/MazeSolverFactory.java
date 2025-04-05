package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MazeSolverFactory {
    private static final Map<String, Supplier<MazeSolverAlgorithm>> registry = new HashMap<>();

    static {
        registerSolver("righthand", RightHandMazeSolver::new);
        registerSolver("tremaux", TremauxMazeSolver::new);
    }

    public static void registerSolver(String name, Supplier<MazeSolverAlgorithm> constructor) {
        registry.put(name.toLowerCase(), constructor);
    }

    public static MazeSolverAlgorithm createSolver(String name) {
        Supplier<MazeSolverAlgorithm> supplier = registry.get(name.toLowerCase());
        if (supplier == null) {
            throw new IllegalArgumentException("No solver registered for: " + name);
        }
        return supplier.get();
    }
}
