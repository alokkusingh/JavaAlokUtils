package com.alok.utils;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class AppReport {
	static boolean boolGenerateReport;
	String strReportDir;
	String strReportFile;
	
	static SimpleDateFormat ft;
	
	static FileOutputStream fstream = null;
	static DataOutputStream out = null;
	static BufferedWriter br = null;
	
	static AppReport instance = null;
	
	public AppReport() {
		ft = new SimpleDateFormat ("ddMMyyyy");
		boolGenerateReport = Config.GetInstance().GetSysConfig("REPORT", "GenerateReport").equalsIgnoreCase("true");
		strReportDir = Config.GetInstance().GetSysConfig("REPORT", "ReportDir");
		strReportFile = Config.GetInstance().GetSysConfig("REPORT", "ReportFilePrefix") + "." + ft.format(System.currentTimeMillis());
		
		ft = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss");
				
		try {
			fstream = new FileOutputStream(strReportDir + "\\" + strReportFile, true);
			
			out = new DataOutputStream(fstream);
			br = new BufferedWriter(new OutputStreamWriter(out));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteReport(String str) {
		if (instance == null) {
			instance = new AppReport();
		}
		if (boolGenerateReport == true) {
			try {
				br.write(ft.format(System.currentTimeMillis()) + " " + str);
				br.newLine();
				br.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(ft.format(System.currentTimeMillis()) + " " + str);
		}
	}
}
