<%@ page import="java.awt.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-03-01
  Time: 오후 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%--<c:set var="context-path" value="${pageContext.request.contextPath}" />--%>
<html>
<head>
    <title>Hello~</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<table>
    <tr>
        <td>
            글번호
        </td>
        <td>
            작성일
        </td>
        <td>
            닉네임
        </td>
        <td>
            제목
        </td>
        <td>
            글내용
        </td>
    </tr>
    <tr>
        <c:forEach var="list" items="${board}">
            <td>
                ${list.num}
            </td>
            <td>
                    ${list.regdate}
            </td>
            <td>
                    ${list.nickname}
            </td>
            <td>
                    ${list.subject}
            </td>
            <td>
                    ${list.content}
            </td>

        </c:forEach>
    </tr>
</table>
<button onclick="location='write.jsp'">게시</button>
</body>
</html>
