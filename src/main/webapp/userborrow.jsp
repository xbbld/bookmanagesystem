<%@ page language="java" import="java.util.*,com.javaweb.demo4.util.DButil,java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>借阅图书</title>
	<style>
		/* 弹窗样式 */
		#myModal {
			display: none;
			position: fixed;
			z-index: 1;
			left: 0;
			top: 0;
			width: 50%;
			height: 80%;
			overflow: auto;
			background-color: rgb(213, 208, 208);
		}
		.intro-content {
			font-size: 14px;
			margin-top: 10px;
		}
		/* 弹窗内容样式 */
		.modal-content {
			background-color: #fefefe;
			margin: 15% auto;
			padding: 20px;
			border: 1px solid #888;
			width: 85%;
		}
		.modal-content img {
			width: 50%;
			max-width: 500px;
			max-height:700px;
			margin-bottom: 10px;
		}
		.close {
			color: #aaa;
			float: right;
			font-size: 28px;
			font-weight: bold;
			cursor: pointer;
		}

		.close:hover,
		.close:focus {
			color: black;
			text-decoration: none;
			cursor: pointer;
		}
	</style>
<link rel="stylesheet" href="css/book.css">
<link rel="stylesheet" href="css/userborrow.css">
<script>
    function showReturnDate() {
        var currentDate = new Date();
        var returnDate = new Date(currentDate.getTime() + 14 * 24 * 60 * 60 * 1000);
        var year = returnDate.getFullYear();
        var month = returnDate.getMonth() + 1;
        month = month < 10 ? '0' + month : month;
        var day = returnDate.getDate();
        day = day < 10 ? '0' + day : day;
        alert('图书最大借阅期限14天，请于' + year + '-' + month + '-' + day + '之前归还');
    }

</script>
</head>
<body>
<script>
	function showModal(content,path,bookname) {
		document.getElementById("introContent").textContent = content;
		document.getElementById("myimage").src=	path;
		document.getElementById("myimage").alt=bookname;
		document.getElementById("myModal").style.display = "block";
	}

	function hideModal() {
		document.getElementById("myModal").style.display = "none";
	}

	function setIntroContent(content) {
		document.getElementById("introContent").textContent = content;
		document.getElementById("myModal").style.display = "block";
	}

	</script>
		<%
			String username = (String) session.getAttribute("username");
			if (username == null || "".equals(username)) {
				response.setContentType("text/html;charset=UTF-8");
		%>
		<script>
			alert("未登录，请登录后使用！");
			window.location.href = "login.jsp";
</script>

	<%
	return;
	}
	%>


	<p>图书列表</p>

	<!--查找图书 -->
	<div>
		<form method="get" action="" class="findbook">
			<label for="search-type">按照</label> <select name="searchType"
				id="search-type">
				<option value="id">编号</option>
				<option value="bookname">图书名</option>
				<option value="author">作者</option>
				<option value="publisher">出版社</option>
			    <option value="category">类别</option>
			</select> <label for="search-keyword">查找</label> <input type="text"
				name="searchKeyword" id="search-keyword">
			<button type="submit">搜索</button>
		</form>
	</div>

	<c:if test="${not empty borrowmsg}">
		<div class="msg-box">${borrowmsg}</div>
	</c:if>
	<div class="table-container">
		<table border="1">
			<tr>
				<th>编号</th>
				<th>书名</th>
				<th>作者</th>
				<th>出版社</th>
				<th>出版日期</th>
				<th>类别</th>
				<th>库存</th>
				<th>操作</th>
			</tr>

			<%
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DButil.getConnection();

				String sql = "select * from book";
				if (searchKeyword != null && !"".equals(searchKeyword)) {
					sql += " where " + searchType + " like '%" + searchKeyword + "%'";
				}
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
			%>
			<tr>
				<td><%=rs.getInt("id")%></td>
				<td><%=rs.getString("bookname")%></td>
				<td><%=rs.getString("author")%></td>
				<td><%=rs.getString("publisher")%></td>
				<td><%=rs.getString("publish_date")%></td>
				<td><%=rs.getString("category")%></td>
				<td><%=rs.getInt("quantity")%></td>
				<td>
					<form method="post" action="borrow" onsubmit="showReturnDate()">
						<input type="hidden" name="bookId" value="<%=rs.getInt("id")%>">
						<input type="submit" value="借阅" >
					</form>
					<button onclick="showModal('<%=rs.getString("introduction")%>','<%=rs.getString("image_path")%>','<%=rs.getString("bookname")%>')">介绍</button>
				</td>
			</tr>
			<%
			}
			} catch (SQLException e) {
			e.printStackTrace();
			} finally {
			// 关闭数据库连接
			try {
			DButil.close(conn, ps, rs);
			} catch (SQLException e) {
			e.printStackTrace();
			}
			}
			%>
		</table>
	</div>
<div id="myModal" class="modal">
	<div class="modal-content">
		<span class="close" onclick="hideModal()">&times;</span>
		<img  alt="" id="myimage">
		<h2>介绍：</h2>
		<p id="introContent" class="intro-content"></p>
	</div>
</div>
</body>
</html>
