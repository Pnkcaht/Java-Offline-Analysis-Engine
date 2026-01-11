package engine.testkit.assertions;

import engine.context.DiagnosticsCollector;

public final class DiagnosticsAssertions {

    public static void assertNoErrors(
            DiagnosticsCollector diagnostics
    ) {
        if (diagnostics.hasErrors()) {
            throw new AssertionError(
                    "Diagnostics contain errors"
            );
        }
    }
}
