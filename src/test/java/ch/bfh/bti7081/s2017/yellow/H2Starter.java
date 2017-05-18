package ch.bfh.bti7081.s2017.yellow;

import java.sql.SQLException;

/**
 * Run as separate application.
 * It starts a TCP server and connects to it via browser,
 * creating an in-memory database.
 */
public class H2Starter {
	public static void main(String... args){
		try {
			org.h2.tools.Console.main("-tcp", "-web", "-pg");
			org.h2.tools.Console.main(
					"-url", "jdbc:h2:tcp://localhost/mem:db1",
					"-driver", "org.h2.Driver",
					"-user", "sa",
					"-password", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
