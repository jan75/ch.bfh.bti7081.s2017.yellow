package ch.bfh.bti7081.s2017.yellow;

import java.sql.SQLException;

import org.h2.tools.Console;

/**
 * Run inner classes as a "Java Application".
 * This class provides quick access to H2 databases for developers.
 */
public class H2Starter {
	
	private static void createH2Server() throws SQLException {
		Console.main("-tcp");
	}
	
	private static void openConnectionInBrowser() throws SQLException {
		Console.main(
			"-url", "jdbc:h2:tcp://localhost/mem:db1",
			"-driver", "org.h2.Driver",
			"-user", "sa",
			"-password", "");
	}
	
	/**
	 * Creates a new TCP server and connects to it via browser.
	 * The login happens automatically.
	 * Can be used to play around with a H2 database.
	 */
	static class H2PlaygroundStarter {
		public static void main(String... args) throws SQLException {
			createH2Server();
			openConnectionInBrowser();
		}
	}
	
	/**
	 * Opens the h2 browser tool and connects automatically.
	 * This is handy to inspect an already running database.
	 * Note that it creates a web server that connects to the db.
	 * Close the program manually to stop the web server.
	 */
	static class H2BrowserToolStarter  {
		public static void main(String... args) throws SQLException {
			openConnectionInBrowser();
		}
	}
}
