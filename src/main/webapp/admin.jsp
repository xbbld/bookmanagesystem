<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统 - 管理员页面</title>
<link rel="stylesheet" type="text/css" href="css/everyuser.css">
</head>

<body>
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

	<header>
		<nav>
			<h3>图书管理系统-管理员</h3>
			<ul>
				<li class="quit"><a href="WEB-INF/login.jsp">退出登录</a></li>
			</ul>
		</nav>
	</header>

	<main>
		<div class="sidebar">
			<ul>
				<li><a href="book.jsp" target="main">图书管理</a></li>
				<li><a href="borrower.jsp" target="main">借阅人信息</a></li>
				<li><a href="borrowrec.jsp" target="main">借阅记录</a></li>
				<li><a href="personal.jsp" target="main">个人信息</a></li>
			</ul>
		</div>

	</main>
	<div class="content">
		<iframe src="book.jsp" name="main" scrolling="auto" target="_self"></iframe>
	</div>
</body>
</html>
