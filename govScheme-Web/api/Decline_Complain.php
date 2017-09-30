<?php
$con=mysqli_connect("mysql.hostinger.in","u227762889_learn","Juhi20301","u227762889_learn");
$id = $_GET['wrong'];

$sql = "Delete From COMPLAIN WHERE id = $id";
$query = mysqli_query($con, $sql);

if($query){
  echo '<script type="text/javascript">';
  echo 'alert("Complain Deleted");' ;
  echo 'window.location.href = "../web pages/complain.php";';
  echo '</script>';
}else{
  echo "not success";
}

?>
