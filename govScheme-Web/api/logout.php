<?php
session_start();
echo '<script type="text/javascript">';
echo 'alert("Succesfully Logged Out.");';
echo 'window.location.href = "../web pages/login.php";';
echo '</script>';
session_unset();
session_destroy();
?>
