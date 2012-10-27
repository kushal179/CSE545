package com.asu.edu.base.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;




public abstract class BaseBaseDAO {

	protected Connection connection = null;
	protected DataSource dataSource;
	/*private static String url = null;
	private static String dbName = null;
	private static String driver = null;
	private static String userName = null;
	private static String password = null;

	static {
		url = "jdbc:mysql://localhost:3306/";
		dbName = "APPLICATION_PLANNER";
		driver = "com.mysql.jdbc.Driver";
		userName = "root";
		password = "";
	}*/
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}

	protected void getConnection() {

		if (connection != null) {
			return;
		}
		if (connection == null) {
			try {
				/*Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url + dbName,
						userName, password);*/
				connection = dataSource.getConnection();
			}  catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	

	

	protected void closeConnection() {

		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException de) {
			de.printStackTrace();
		}
	}

	protected static void closeStatement(Statement pStmt) {

		try {
			if (pStmt != null) {
				pStmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static void closeResultSet(ResultSet pRs) {

		try {
			if (pRs != null) {
				pRs.close();
				pRs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	abstract protected Object toDataObject(ResultSet rs) throws SQLException;

	protected static void doBinds(PreparedStatement pstmt, Object[] bindVars) {

		if (bindVars != null) {
			for (int i = 0; i < bindVars.length; i++) {
				Object o = bindVars[i];
				try {
					if (o == null) {
						pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
					} else if (o instanceof java.lang.String) {
						pstmt.setString(i + 1, (String) o);
					} else if (o instanceof java.lang.Integer) {
						pstmt.setInt(i + 1, ((Integer) o).intValue());
					} else if (o instanceof java.lang.Float) {
						pstmt.setFloat(i + 1, ((Float) o).floatValue());
					} else if (o instanceof Double) {
						pstmt.setDouble(i + 1, ((Double) o).doubleValue());
					} else if (o instanceof BigDecimal) {
						pstmt.setBigDecimal(i + 1, ((BigDecimal) o));
					} else if (o instanceof java.lang.Boolean) {
						pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
					} else if (o instanceof java.lang.Long) {
						pstmt.setLong(i + 1, ((Long) o).longValue());
					} else if (o instanceof java.util.Date) {
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp(
								((java.util.Date) o).getTime()));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected static void doBinds(PreparedStatement pstmt, Object[] bindVars,
			int[] types) {

		if (bindVars != null) {
			for (int i = 0; i < bindVars.length; i++) {
				Object o = bindVars[i];
				try {
					if (o == null) {
						pstmt.setNull(i + 1, types[i]);
					} else if (o instanceof java.lang.String) {
						pstmt.setString(i + 1, (String) o);
					} else if (o instanceof java.lang.Integer) {
						pstmt.setInt(i + 1, ((Integer) o).intValue());
					} else if (o instanceof java.lang.Float) {
						pstmt.setFloat(i + 1, ((Float) o).floatValue());
					} else if (o instanceof Double) {
						pstmt.setDouble(i + 1, ((Double) o).doubleValue());
					} else if (o instanceof BigDecimal) {
						pstmt.setBigDecimal(i + 1, ((BigDecimal) o));
					} else if (o instanceof java.lang.Boolean) {
						pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
					} else if (o instanceof java.lang.Long) {
						pstmt.setLong(i + 1, ((Long) o).longValue());
					} else if (o instanceof java.util.Date) {
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp(
								((java.util.Date) o).getTime()));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected Object getRowByCriteria(final String sql, List prepareParams) {

		Object params[] = null;
		if (prepareParams != null) {
			final int listSize = prepareParams.size();
			params = new Object[listSize];
			if (prepareParams.size() != 0) {
				for (int i = 0; i < listSize; i++) {
					params[i] = prepareParams.get(i);
				}
			}
		} else {
			params = new Object[0];
		}
		return getRowByCriteria(sql, params);
	}
	protected Object getListByCriteria(String sql, Object[] prepareParams, int[] types) {

		ArrayList<Object> aList = (ArrayList<Object>) getListByCriteria(sql, prepareParams,true);
		return aList;
	}
	protected Object getListByCriteria(String sql, Object[] prepareParams)  {

		ArrayList<Object> aList = (ArrayList<Object>) getListByCriteria(sql, prepareParams, true);
		return aList;
	}
	protected Object getListByCriteria(final String sql, List prepareParams)  {

		Object params[] = null;
		if (prepareParams != null) {
			final int listSize = prepareParams.size();
			params = new Object[listSize];
			if (prepareParams.size() != 0) {
				for (int i = 0; i < listSize; i++) {
					params[i] = prepareParams.get(i);
				}
			}
		} else {
			params = new Object[0];
		}
		return getListByCriteria(sql, params);
	}

	protected Object getRowByCriteria(String sql, Object[] prepareParams) {

		Object obj = getRowByCriteria(sql, prepareParams, true);
		return obj;
	}

	protected Object getRowByCriteria(String sql, Object[] prepareParams,
			boolean blCloseConn) {
		System.out.println("Inside GetRowsByCriteria");
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		Object obj = null;

		try {
			getConnection();

			int i = 0;

			if (connection != null) {
				lStmt = connection.prepareStatement(sql);
				doBinds(lStmt, prepareParams);
				lRs = lStmt.executeQuery();
				System.out.println("just before stmnt execution");
				while (lRs.next()) {
					i = i + 1;

					if (i == 1) {
						System.out.println("jst before todataObject");
						obj = this.toDataObject(lRs);
					}
				}
			}

			if (i > 1) {
				System.out.println("Unexpected row from database");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			closeResultSet(lRs);
			closeStatement(lStmt);

			if (blCloseConn) {
				closeConnection();
			}
		}
		return obj;
	}

	protected Object getListByCriteria(String sql, Object[] prepareParams,
			 boolean blCloseConn) {
		PreparedStatement lStmt = null;
		ResultSet lRs = null;
		ArrayList<Object> aList = null;

		try {
			getConnection();
			lStmt = connection.prepareStatement(sql);
			doBinds(lStmt, prepareParams);
			lRs = lStmt.executeQuery();

			if (lRs != null) {
				aList = new ArrayList<Object>();

				while (lRs.next()) {
					aList.add(this.toDataObject(lRs));
				}

				if (aList.isEmpty() == true) {
					aList = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(lRs);
			closeStatement(lStmt);
			if (blCloseConn) {
				closeConnection();
			}
		}
		return aList;
	}
	
	protected int preparedStatementUpdate(String query, Object[] bindVars, boolean blCloseConn) {

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

