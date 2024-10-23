<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post" action="/add_user">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label><b>Login</b></label>
            <input type="text" placeholder="Login" name="login">

            <label><b>Password</b></label>
            <input type="text" placeholder="Password" name="password">
            <label>Role</label>
            <select id="roles" name="role">
                <option value="ADMIN">Administrator</option>
                <option value="NORMAL_USER">User</option>
            </select>
            <input class="button" type="submit" value="Add">

            <hr>
        </div>
    </form>
</body>
</html>