<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h2>Register as a new user</h2>
        <form action="loginservlet" method="POST">
            <label for="username">Username: </label> <input required type="text" placeholder="Username" name="username"> <br>
            <label for="password">Password: </label> <input required type="password" placeholder="Password" name="password"> <br>
            <label for="confirmpassword">Confirm password: </label> <input required type="password" placeholder="Confirm password" name="confirmpassword"> <br>
            <input type="submit" name="register" value="Register">
        </form>
    </body>
</html>