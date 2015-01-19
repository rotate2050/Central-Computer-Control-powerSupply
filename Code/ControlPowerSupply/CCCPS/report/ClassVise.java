package report;

import java.io.InputStream;
import java.util.HashMap;
import CCCPS.Dbcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ClassVise {

	public ClassVise(String path, String en) {
		String sem = en;
		InputStream is = getClass().getResourceAsStream("test.jasper");
		String outFileName = path;
		HashMap hm = new HashMap();
		hm.put("SEM", sem);
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