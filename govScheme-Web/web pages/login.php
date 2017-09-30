
<html>
<head>
  <title> Dashboard </title>

  <link rel = "stylesheet" href = "css/style_login.css" >
</head>

<body>
  <div id = "top">
    <h1> Dashboard </h1>
    <img src="assets/democracy.png" id="logo">
    <hr id = "top_line">
  </div>
<!-- Login -->
<div id = "middle">
<div class="login">
	<h1>Login</h1>
    <form method="post" action="http://hosting619.96.lt/api/adminlogin.php">
    	<input type="text" name="User" placeholder="Username" required="required" />
        <input type="password" name="Password" placeholder="Password" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
    </form>
</div>
</div>

<div id = "bottom">
      <hr>
</div>
</body>
</html>
