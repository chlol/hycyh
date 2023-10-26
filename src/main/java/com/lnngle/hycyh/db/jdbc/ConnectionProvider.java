package com.lnngle.hycyh.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
	/**
	 * Obtains a connection from database.
	 *
	 * @return The obtained JDBC connection
	 *
	 * @throws SQLException Indicates a problem opening a connection
	 */
	Connection getConnection() throws SQLException;

	/**
	 * Release a connection from database.
	 *
	 * @param conn The JDBC connection to release
	 *
	 * @throws SQLException Indicates a problem closing the connection
	 */
	void closeConnection(Connection conn) throws SQLException;

	/**
	 * Does this connection provider support aggressive release of JDBC connections and later
	 * re-acquisition of those connections if needed?
	 * Typically, this is only true in managed environments where a container tracks connections
	 * by transaction or thread.
	 *
	 * Note that JTA semantic depends on the fact that the underlying connection provider does
	 * support aggressive release.
	 *
	 * @return {@code true} if aggressive releasing is supported; {@code false} otherwise.
	 */
	boolean supportsAggressiveRelease();
}
