<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h2>Login</h2>
        <form action="loginservlet" method="GET">
            <label for="usernamelogin">Username: </label> <input required placeholder="Username" type="text" name="usernamelogin"> <br>
            <label for="passwordlogin">Password: </label> <input required placeholder="Password" type="password" name="passwordlogin"> <br>
            <input type="submit" name="login" value="Login">
        </form>
    </body>
</html>