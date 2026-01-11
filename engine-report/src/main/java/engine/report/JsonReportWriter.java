package engine.report;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * Minimal JSON report writer.
 */
public final class JsonReportWriter implements ReportWriter {

    @Override
    public void write(Report report, OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"sections\":[");

        Iterator<ReportSection> it = report.sections().iterator();
        while (it.hasNext()) {
            writeSection(it.next(), sb);
            if (it.hasNext()) sb.append(",");
        }

        sb.append("]}");
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    private void writeSection(
            ReportSection section,
            StringBuilder sb
    ) {
        sb.append("{");
        sb.append("\"title\":\"").append(escape(section.title())).append("\",");
        sb.append("\"data\":{");

        Iterator<Map.Entry<String, Object>> it =
                section.data().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Object> e = it.next();
            sb.append("\"")
                    .append(escape(e.getKey()))
                    .append("\":\"")
                    .append(escape(String.valueOf(e.getValue())))
                    .append("\"");

            if (it.hasNext()) sb.append(",");
        }

        sb.append("}}");
    }

    private static String escape(String v) {
        return v.replace("\"", "\\\"");
    }
}
