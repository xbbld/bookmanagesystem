<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2024/6/11
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>弹窗示例</title>
    <style>
        #myModal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
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
</head>
<body>
<form action="uploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="fileToUpload" id="fileToUpload">
    <input type="submit" value="Upload Image" name="submit">
</form>
<button onclick="showModal()">点击显示弹窗</button>

<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="hideModal()">&times;</span>
        <h2>介绍：</h2>
        <p id="introContent">这是介绍内容。</p>
    </div>
</div>

<script>
    // 显示弹窗的函数
    function showModal() {
        document.getElementById("myModal").style.display = "block";
    }

    // 隐藏弹窗的函数
    function hideModal() {
        document.getElementById("myModal").style.display = "none";
    }

    // 假设你有一个函数来设置介绍内容（这里只是示例）
    function setIntroContent(content) {
        document.getElementById("introContent").textContent = content;
    }

    // 在页面加载时或某个事件触发时设置介绍内容
    // setIntroContent("这是你的具体介绍内容...");
</script>

</body>
</html>
