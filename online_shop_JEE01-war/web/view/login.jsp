<%-- 
    Document   : login
    Created on : 01.10.2014, 17:54:10
    Author     : Mick_02
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online Shop Login</title>
  </head>
  <body>
    <h1>Benutzer anmelden</h1>
    
    <form method="post" action="/online_shop_JEE01-war/front/user/login">

      <label for="username">Benutzer Name:</label>
      <input type="text" name="username" id="username"/>
    </p>
    <input type="submit" value="senden"/>
  </form>

</body>
</html>
