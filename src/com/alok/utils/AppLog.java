package com.alok.utils;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public final class AppLog {
	static Integer intTraceLevel;
	String strLogDir;
	String strLogFile;
	static Boolean boolLogToConsole;
	
	static SimpleDateFormat ft;
	
	static FileOutputStream fstream = null;
	static DataOutputStream out = null;
	static BufferedWriter br = null;
	
	static AppLog instance = null;
	
	public AppLog() {

		ReadConfig();
		
		ft = new SimpleDateFormat("dd:MM:yyyy hhmmssS");
				
		try {
			fstream = new FileOutputStream(strLogDir + "\\" + strLogFile, true);
			
			out = new DataOutputStream(fstream);
			br = new BufferedWriter(new OutputStreamWriter(out));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ReadConfig() {
		ft = new SimpleDateFormat ("ddMMyyyy");
		intTraceLevel = new Integer(Config.GetInstance().GetSysConfig("TRACE", "TraceLevel"));
		strLogDir = Config.GetInstance().GetSysConfig("TRACE", "LogDir");
		strLogFile = Config.GetInstance().GetSysConfig("TRACE", "LogFilePrefix") + "." + ft.format(System.currentTimeMillis());
		boolLogToConsole = Config.GetInstance().GetSysConfig("TRACE", "LogToConsole").equalsIgnoreCase("true");
	}
	
	public static void PrintTrace(Integer traceLevel, String str) {
		if (instance == null) {
			instance = new AppLog();
		}
		 
		if (traceLevel <= intTraceLevel) {
			try {
				br.write(ft.format(System.currentTimeMillis()) + " " + str);
				br.newLine();
				br.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (boolLogToConsole == true) {
				System.out.println(ft.format(System.currentTimeMillis()) + " " + str);
			}
		}
	}
}
