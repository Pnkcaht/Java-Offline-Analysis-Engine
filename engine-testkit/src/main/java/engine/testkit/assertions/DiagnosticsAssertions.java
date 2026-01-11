package engine.testkit.assertions;

import engine.context.DiagnosticsCollector;

public final class DiagnosticsAssertions {

    public static void assertNoErrors(
            DiagnosticsCollector diagnostics
    ) {
        if (diagnostics == null) {
            throw new AssertionError(
                    "DiagnosticsCollector is null"
            );
        }

        if (diagnostics.hasErrors()) {
            throw new AssertionError(
                    "Diagnostics contain errors. Check diagnostic output for details."
            );
        }
    }
}
