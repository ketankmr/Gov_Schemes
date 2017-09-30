<?php
$con=mysqli_connect("mysql.hostinger.in","u227762889_learn","Juhi20301","u227762889_learn");
$id = $_GET['right'];

$sql = "Update COMPLAIN SET STATUS = 1 WHERE id = $id";
$query = mysqli_query($con, $sql);

if($query){
  echo '<script type="text/javascript">';
  echo 'alert("Complain Added");' ;
  echo 'window.location.href = "../web pages/complain.php";';
  echo '</script>';
}else{
  echo "not success";
}

?>
