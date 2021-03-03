
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class Test {
	//private static Context con = null;
	private ExceptionHandle obj = new ExceptionHandle();
	
	public void m1() {
		/*
		try {
			throw new SQLException();
		}
		catch(SQLException ex) {			
			obj.handleException("Perennial Bank", "Module2", ex);
		}
		*/
		try {
			throw new IOException();
		}
		catch(IOException ex) {
			obj.handleException("Perennial Bank", "Module1", ex);
		}
		/*
		try {
			throw new FileNotFoundException();
		}
		catch(FileNotFoundException ex) {
			obj.handleException("Perennial Bank", "Module1", ex);
		}
		*/
	}

	public static void main(String[] args) {
		new Test().m1();
	}
}
