import java.util.Map;

public class AbortAction implements Action {

	public void takeAction(Map<String, String> nvPair) {
		System.exit(1);
	}	
}
