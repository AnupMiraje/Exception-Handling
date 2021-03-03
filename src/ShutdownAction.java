import java.util.Map;

public class ShutdownAction implements Action {

	@Override
	public void takeAction(Map<String, String> nvPair) {
		System.exit(1);
	}

}
