package com.manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerJDBCDAO implements ManagerDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "25503605226";

	private static final String INSERT_STMT = 
		"INSERT INTO manager (managerName,managerAccount,managerPassword,managerStatus) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT managerNo,managerName,managerAccount,managerPassword,managerStatus FROM manager order by managerNo";
	private static final String GET_ONE_STMT = 
		"SELECT managerNo,managerName,managerAccount,managerPassword,managerStatus FROM manager where managerNo = ?";
	private static final String DELETE = 
		"DELETE FROM manager where managerNo = ?";
	private static final String UPDATE = 
		"UPDATE manager set managerName=?, managerAccount=?, managerPassword=?, managerStatus=? where managerNo = ?";
	private static final String LOGIN = 
			"SELECT * FROM manager where managerAccount=? AND managerPassword=?";
	@Override
	public void insert(ManagerVO managerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, managerVO.getManagerName());
			pstmt.setString(2, managerVO.getManagerAccount());
			pstmt.setString(3, managerVO.getManagerPassword());
			pstmt.setInt(4, managerVO.getManagerstatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ManagerVO managerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, managerVO.getManagerName());
			pstmt.setString(2, managerVO.getManagerAccount());
			pstmt.setString(3, managerVO.getManagerPassword());
			pstmt.setInt(4, managerVO.getManagerstatus());
			pstmt.setInt(5, managerVO.getManagerNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer managerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, managerNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ManagerVO findByPrimaryKey(Integer managerNo) {

		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				managerVO.setManagerstatus(rs.getInt("managerstatus"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return managerVO;
	}

	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("managerNo"));
				managerVO.setManagerName(rs.getString("managerName"));
				managerVO.setManagerAccount(rs.getString("ManagerAccount"));
				managerVO.setManagerPassword(rs.getString("ManagerPassword"));
				managerVO.setManagerstatus(rs.getInt("managerstatus"));
				list.add(managerVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public ManagerVO findAP(String managerAccount ,String managerPassword) {

		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, managerAccount);
			pstmt.setString(2,managerPassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setManagerAccount(rs.getString("managerAccount"));
				managerVO.setManagerPassword(rs.getString("managerPassword"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return managerVO;
	}
	public static void main(String[] args) {

		ManagerJDBCDAO dao = new ManagerJDBCDAO();

		// 新增
//		ManagerVO managerVO1 = new ManagerVO();
//		
//		managerVO1.setManagerName("緯育");
//		managerVO1.setManagerAccount("tibame");
//		managerVO1.setManagerPassword("cia103");
//		managerVO1.setManagerstatus(2);
//		dao.insert(managerVO1);

		// 修改
//		ManagerVO managerVO2 = new ManagerVO();
//		managerVO2.setManagerName("緯育2");
//		managerVO2.setManagerAccount("tibame2");
//		managerVO2.setManagerPassword("cia103G2");
//		managerVO2.setManagerstatus(1);
//		managerVO2.setManagerNo(3);
//		dao.update(managerVO2);

		// 刪除
//		dao.delete(4);

		// 查詢
//		ManagerVO managerVO3 = dao.findByPrimaryKey(1);
//		System.out.print(managerVO3.getManagerNo() + ",");
//		System.out.print(managerVO3.getManagerName() + ",");
//		System.out.print(managerVO3.getManagerAccount() + ",");
//		System.out.print(managerVO3.getManagerPassword() + ",");
//		System.out.print(managerVO3.getManagerstatus() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<ManagerVO> list = dao.getAll();
		for (ManagerVO Manager : list) {
			System.out.print(Manager.getManagerNo() + ",");
			System.out.print(Manager.getManagerName() + ",");
			System.out.print(Manager.getManagerAccount() + ",");
			System.out.print(Manager.getManagerPassword() + ",");
			System.out.print(Manager.getManagerstatus());
			System.out.println();
		}
	}
}
	