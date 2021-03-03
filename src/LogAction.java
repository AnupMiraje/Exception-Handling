import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogAction implements Action {

	public void takeAction(Map<String, String> nvPair) {
		// TODO Auto-generated method stub
		log(nvPair.get("logFilePath"), nvPair.get("logType"));
	}
	
	private static void log(String path, String logType)
	{
		Logger logger = Logger.getLogger(LogAction.class.getName());
		
		FileHandler handler = null;
		try {
			handler = new FileHandler(path, true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.addHandler(handler);
		logger.warning(logType);
	}
}
