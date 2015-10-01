package com.alok.utils;

import java.io.*;
import java.util.Properties;
import java.util.regex.*;

public class Config {
	FileInputStream fstream = null;
	DataInputStream in = null;
	BufferedReader br = null;
	Properties proSys = new Properties();
	Properties proApp = new Properties();
	
	public static Config instance = null;
	
	public Config (String confFile) {
		try {
			
			fstream = new FileInputStream(confFile);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			Pattern pHead = Pattern.compile("\\[.*\\]");
			Pattern pComment = Pattern.compile("#.*");
			Pattern pEmpty = Pattern.compile("^ *$");
			Matcher mHead = null;
			Matcher mComment = null;
			Matcher mEmpty = null;
			String head = "";
			String tagVal[] = null;
			
			while ((strLine = br.readLine()) != null)   {
				mHead = pHead.matcher(strLine);
				mComment = pComment.matcher(strLine);
				mEmpty = pEmpty.matcher(strLine);
				//System.out.println (strLine);
				if (mComment.find() || mEmpty.find()) {
					//just ignore
				} else if (mHead.find()) {
					head = trimChar(strLine, '[').trim();
					//System.out.println("head: "+head);
				} else {
					tagVal = strLine.split("=");
					proSys.put(head+"-"+tagVal[0].trim(), tagVal[1].trim());
				}
			}
			//System.out.println(proSys.toString());
			in.close();
						
			fstream = new FileInputStream(GetSysConfig("MAIN", "ConfDir")+"\\"+GetSysConfig("MAIN", "AppConfigFile"));
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			while ((strLine = br.readLine()) != null)   {
				mHead = pHead.matcher(strLine);
				mComment = pComment.matcher(strLine);
				mEmpty = pEmpty.matcher(strLine);
				//System.out.println (strLine);
				if (mComment.find() || mEmpty.find()) {
					//just ignore
				} else if (mHead.find()) {
					head = trimChar(strLine, '[').trim();
				} else {
					tagVal = strLine.split("=");
					proApp.put(head+"-"+tagVal[0].trim(), tagVal[1].trim());
				}
			}
			
			//System.out.println(proSys.toString());
			//System.out.println(proApp.toString());
			in.close();
			
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
			}
			System.exit(1);
		}
		
	}
	
	public static Config CreateInstance(String confFile) {
		instance = new Config(confFile);
		return instance;
	}
	
	public static Config GetInstance() {
		return instance;
	}
	
	public String GetSysConfig(String head, String tag) {
		return proSys.getProperty(head+"-"+tag);
	}
	
	public String GetAppConfig(String head, String tag) {
		return proApp.getProperty(head+"-"+tag);
	}
	
	private static String trimChar(String inString, char toTrim ) { 
        int from = 0;
        int to = inString.length();

        for( int i = 0 ; i < inString.length() ; i++ ) {
            if( inString.charAt( i ) != toTrim) {
                from = i;
                break;
            }
        }
        for( int i = inString.length()-1 ; i >= 0 ; i-- ){ 
            if( inString.charAt( i ) != toTrim ){
                to = i;
                break;
            }
        }
        return inString.substring( from , to );
    }
}
