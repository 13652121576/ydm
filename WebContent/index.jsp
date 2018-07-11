<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/26
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>hello baby</title>
  </head>
  <body>
  <input id="name" style="color:black;font-size: 100px;background-color: pink" value="乔科玲" />
  <input name="" type="submit" onclick="show()"/>

  </body>
<script type="text/javascript">

  function show() {
      var a = document.getElementById("name");
      alert(a);
      alert(a+"是小美女");
      window.location.href="http://www.baidu.com";
  }
</script>
</html>
