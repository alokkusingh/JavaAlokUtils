import com.alok.utils.AppLog;
import com.alok.utils.AppReport;
import com.alok.utils.Config;


public class AlokUtilsMain {

	/**
	 * @param args
	 */
	public static void main (String str[]) {
		try {
		    Config cfg = Config.CreateInstance("C:\\Alok\\workspace\\VehicleSupplyChain\\conf\\SysConfig.file");

		    System.out.println(cfg.GetSysConfig("MAIN", "ConfDir"));
		    System.out.println(cfg.GetAppConfig("FACTORY", "CarProdPerHour"));
		    
		    for (int i = 0; i < 10; i++) {
		    	System.out.println("Hi");
		    	AppLog.PrintTrace(1, "Hello World");
		    	AppReport.WriteReport("Hello World");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			//StackTraceElement[] stes = e.getStackTrace();
			//for (int i = 0; i < stes.length; i++) {
			//	System.out.println("Exception: " + stes[i]);
			//}
			
		}
	}

}
