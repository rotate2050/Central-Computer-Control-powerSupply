package report;

import java.util.HashMap;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import CCCPS.Dbcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class Attendance_report_irregular {

	public Attendance_report_irregular(String path, String en) {
		InputStream is = (InputStream) getClass().getResourceAsStream(
				"attendance_report_irregular.jasper");
		String outFileName = path;
		HashMap hm = new HashMap();
		hm.put("parameter1", en);
		Dbcon h = new Dbcon();
		h.open();

		try {
			// Fill the report using an empty data source
			JasperPrint print = JasperFillManager.fillReport(is, hm, h.conn);

			// Create a PDF exporter
			JRExporter exporter = new JRPdfExporter();

			// Configure the exporter (set output file name and print object)
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					outFileName);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

			// Export the PDF file
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}