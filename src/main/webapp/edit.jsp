<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit page</title>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script>
        function del() {
            return confirm("DELETE?")
        }
        $(function () {
            var partOfSpeech = $('select').attr('title');
            console.log(partOfSpeech);
            if (partOfSpeech == 'n.') {
                $('#n').attr('selected', 'selected');
            }
            if (partOfSpeech == 'v.') {
                $('#v').attr('selected', 'selected');
            }
            if (partOfSpeech == 'adj.') {
                $('#adj').attr('selected', 'selected');
            }
            if (partOfSpeech == 'adv.') {
                $('#adv').attr('selected', 'selected');
            }
        });
    </script>
</head>
<body>
<c:if test="${sessionScope.email eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>edit page</h1>
${sessionScope.email}
<a href="/user?action=logout">LOG OUT</a>
<hr>
<h2>ADD WORDS</h2>
<form action="/dictionary" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${sessionScope.dictionary.id}">
    <input type="text" name="english" placeholder="ENGLISH" value="${sessionScope.dictionary.english}"><br>
    <input type="text" name="chinese" placeholder="CHINESE" value="${sessionScope.dictionary.chinese}"><br>
    <input type="text" name="phonetic" placeholder="PHONETIC" value="${sessionScope.dictionary.phonetic}"><br>
    <select name="partOfSpeech" title="${sessionScope.dictionary.partOfSpeech}">
        <option id="n" value="n.">名词</option>
        <option id="v" value="v.">动词</option>
        <option id="adj" value="adj.">形容词</option>
        <option id="adv" value="adv.">副词</option>
    </select><br>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>
