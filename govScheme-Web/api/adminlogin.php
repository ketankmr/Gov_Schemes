<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response

 session_start();

$response = array();

// include db connect class

require '../include/DB_CONNECT.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_POST["User"],$_POST["Password"])) {
    $user = $_POST['User'];
    $pass= $_POST['Password'];
	$product = array();


    // get a product from products table
    $result = mysqli_query($db->connect(),"SELECT * FROM Admin WHERE User = '$user' AND Password='$pass'");


    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {

               $product["error"] = "false";

               $_SESSION['user'] = $user;
           header("Location:http://hosting619.96.lt/web%20pages/cardview.php");

          //  array_push($response["product"], $product);

            // echoing JSON response
            // echo json_encode($product);

        } else {

                 echo '<script type="text/javascript">';
                 echo 'alert("Username and/or Password incorrect.\\nTry again.");';
                 echo 'window.location.href = "../web pages/login.php";';
                 echo '</script>';


             //array_push($response["product"], $product);
            // no product found

            // echo no users JSON

        }
    } else {
         $product["error"] = "result empty";




          //  array_push($response["product"], $product);
            // no product found

            // echo no users JSON
            echo json_encode($product);
    }
}
 else {
    // required field is missing
    $response["error"] = "true";
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
