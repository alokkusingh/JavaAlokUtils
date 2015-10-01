import com.alok.utils.AppLog;
import com.alok.utils.Config;


public class ConfigReadMain {

	/**
	 * @param args
	 */
	public static void main (String str[]) {
		try {
		    Config.CreateInstance("C:\\Alok\\workspace\\VehicleSupplyChain\\conf\\SysConfig.file");
		    //Config cfg = new Config("C:\\Alok\\workspace\\VehicleSupplyChain\\conf\\SysConfig.file");
		    
		    System.out.println(Config.GetInstance().GetSysConfig("MAIN", "ConfDir"));
		    System.out.println(Config.GetInstance().GetAppConfig("FACTORY", "CarProdPerHour"));
		    
		    for (int i = 0; i < 10; i++) {
		    	AppLog.PrintTrace(3, "Hello World");
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
