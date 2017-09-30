<?php
require '../include/DB_CONNECT.php';
 
// connecting to db
$db = new DB_CONNECT();
$product = array();

if(isset($_POST['ID']) && isset($_POST['Confirmed'])){

	$id = $_POST['ID'];
	$confirm = $_POST['Confirmed'];
	
     $count;


    $result = mysqli_query($db->connect(),"UPDATE COMPLAIN SET Count = Count + 1, Confirmed = '$confirm' WHERE ID = '$id'");


   


     if ($result) {
    
 	$product['error']= false;
    

    echo json_encode($product);
     }
     else{

     	$product['error']= true;
   

    	echo json_encode($product);

    }
}
else{
      $product['error']= true;
    	$product['message']= "Required field missing";

    	echo json_encode($product);
}
?>