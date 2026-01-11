package engine.cli;

public final class Main {

    public static void main(String[] args) {
        try {
            CommandLineOptions options =
                    CommandLineOptions.parse(args);

            EngineLauncher launcher =
                    new EngineLauncher(options);

            launcher.run();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
