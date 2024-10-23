<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post" action="">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label><b>Login</b></label>
            <input type="text" placeholder="Login" name="login">

            <label><b>Password</b></label>
            <input type="text" placeholder="Password" name="password">

            <input class="button" type="submit" value="Enter">

            <hr>
        </div>
    </form>
    <form method="post" action="/showUsers">
        <input class="button" type="submit" value="Show users">
    </form>
    <a href="/register">Registration</a>

</body>
</html>