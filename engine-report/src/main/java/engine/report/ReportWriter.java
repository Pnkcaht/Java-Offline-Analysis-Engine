package engine.report;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Writes a report to an output stream.
 */
public interface ReportWriter {

    void write(Report report, OutputStream out) throws IOException;
}
