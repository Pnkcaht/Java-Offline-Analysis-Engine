package engine.cli;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Parsed CLI options.
 */
public final class CommandLineOptions {

    public enum ReportFormat {
        TEXT,
        JSON
    }

    private final Path inputPath;
    private final ReportFormat reportFormat;
    private final boolean failOnDiagnostics;

    private CommandLineOptions(
            Path inputPath,
            ReportFormat reportFormat,
            boolean failOnDiagnostics
    ) {
        this.inputPath = inputPath;
        this.reportFormat = reportFormat;
        this.failOnDiagnostics = failOnDiagnostics;
    }

    public Path inputPath() {
        return inputPath;
    }

    public ReportFormat reportFormat() {
        return reportFormat;
    }

    public boolean failOnDiagnostics() {
        return failOnDiagnostics;
    }

    public static CommandLineOptions parse(String[] args) {
        Objects.requireNonNull(args);

        Path input = null;
        ReportFormat format = ReportFormat.TEXT;
        boolean fail = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--input" -> {
                    if (++i >= args.length)
                        throw new IllegalArgumentException(
                                "--input requires a path"
                        );
                    input = Path.of(args[i]);
                }
                case "--report" -> {
                    if (++i >= args.length)
                        throw new IllegalArgumentException(
                                "--report requires a value"
                        );
                    format = switch (args[i].toLowerCase()) {
                        case "text" -> ReportFormat.TEXT;
                        case "json" -> ReportFormat.JSON;
                        default -> throw new IllegalArgumentException(
                                "Unknown report format: " + args[i]
                        );
                    };
                }
                case "--fail-on-diagnostics" -> fail = true;
                default -> throw new IllegalArgumentException(
                        "Unknown argument: " + args[i]
                );
            }
        }

        if (input == null)
            throw new IllegalArgumentException(
                    "--input is required"
            );

        return new CommandLineOptions(input, format, fail);
    }
}
