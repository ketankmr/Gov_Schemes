<?php
require '../include/DB_CONNECT.php';
 
// connecting to db
$db = new DB_CONNECT();
$product = array();

if(isset($_POST['Problem']) && isset($_POST['Description'])&&isset($_POST['Location'])){

	$problem = $_POST['Problem'];
	$description = $_POST['Description'];
    $location = $_POST['Location'];

    $result = mysqli_query($db->connect(),"INSERT INTO COMPLAIN (Problem,DESCRIPTION,LOCATION) VALUES ('$problem','$description','$location')");

    if ($result) {
    	# code...
    	$product['error']= false;
    	$product['message']= "row inserted";

    	echo json_encode($product);
    }
    else{

    	$product['error']= true;
    	$product['message']= "Error occured";

    	echo json_encode($product);

    }
}
else{
      $product['error']= true;
    	$product['message']= "Required field missing";

    	echo json_encode($product);
}
?>