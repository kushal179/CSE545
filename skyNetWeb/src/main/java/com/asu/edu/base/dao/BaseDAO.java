package com.asu.edu.base.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDAO extends BaseBaseDAO {

	protected int preparedStatementUpdate(String query, Object[] bindVars,
			boolean blCloseConn) {

		PreparedStatement pstmt = null;
		int i = 0;
		try {
			if (connection == null) {
				getConnection();
			}

			pstmt = connection.prepareStatement(query);
			doBinds(pstmt, bindVars);
			i = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(pstmt);

			if (blCloseConn) {
				closeConnection();
			}
		}

		return i;
	}
}
