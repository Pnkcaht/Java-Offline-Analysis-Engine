package engine.report;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Plain text report writer.
 */
public final class TextReportWriter implements ReportWriter {

    @Override
    public void write(Report report, OutputStream out) throws IOException {
        for (ReportSection section : report.sections()) {
            writeSection(section, out);
        }
    }

    private void writeSection(
            ReportSection section,
            OutputStream out
    ) throws IOException {

        out.write(
                ("== " + section.title() + " ==\n")
                        .getBytes(StandardCharsets.UTF_8)
        );

        for (Map.Entry<String, Object> e : section.data().entrySet()) {
            out.write(
                    (e.getKey() + ": " + e.getValue() + "\n")
                            .getBytes(StandardCharsets.UTF_8)
            );
        }

        out.write("\n".getBytes(StandardCharsets.UTF_8));
    }
}
