package com.javaweb.demo4.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.javaweb.demo4.util.DButil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *更改个人信息的操作
 */

@WebServlet("/savePersonalInfo")
public class SavePersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获取表单数据
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address=request.getParameter("address");
		String username = request.getParameter("username");
		String id =(String) request.getSession().getAttribute("id");
		// 更新数据库中的用户信息
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE users SET username=?, phone=?, email=?, address=? WHERE id=?";
		try {
			// 建立数据库连接
			conn = DButil.getConnection();
			// 准备 SQL 语句
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setString(1, username);
			ps.setString(2, phone);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, id);
			// 执行 SQL 更新操作
			ps.executeUpdate();
			// 返回成功信息
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			try {
				DButil.close(conn, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("personal.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
