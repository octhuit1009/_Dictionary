<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>home page</title>
    <script>
        function del() {
            return confirm("DELETE?")
        }
    </script>
</head>
<body>
<c:if test="${sessionScope.email eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>home page</h1>
${sessionScope.email}
<a href="/user?action=logout">LOG OUT</a>
<hr>
<h2>ADD WORDS</h2>
<form action="/dictionary" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="english" placeholder="ENGLISH" value="apple"><br>
    <input type="text" name="chinese" placeholder="CHINESE" value="苹果"><br>
    <input type="text" name="phonetic" placeholder="PHONETIC" value="/apple/"><br>
    <select name="partOfSpeech">
        <option value="n.">名词</option>
        <option value="v.">动词</option>
        <option value="adj.">形容词</option>
        <option value="adv.">副词</option>
    </select><br>
    <input type="submit" value="ADD">
</form>
<hr>
<h2>WORD LIST</h2>
<table border="1">
    <tr>
        <th>NO</th>
        <th>ENGLISH</th>
        <th>CHINESE</th>
        <th>PHONETIC</th>
        <th>PART OF SPEECH</th>
        <th colspan="2">OPERATIONS</th>
    </tr>
    <c:forEach var="dictionary" items="${sessionScope.dictionaries}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${dictionary.english}</td>
            <td>${dictionary.chinese}</td>
            <td>${dictionary.phonetic}</td>
            <td>${dictionary.partOfSpeech}</td>
            <td><a href="/dictionary?action=search&id=${dictionary.id}">MODIFY</a></td>
            <td><a href="/dictionary?action=remove&id=${dictionary.id}" onclick="return del()">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
