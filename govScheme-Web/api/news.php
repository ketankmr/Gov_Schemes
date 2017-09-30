<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';
require '../include/db.php';
$app = new \Slim\App;


//GetSchemes
$app->get('/GetAllSchemes', function (Request $request, Response $response) {

   $sql = "SELECT * FROM SCHEME";

    try{
        $db = new db();

       $db= $db->connect();

        $stmt = $db->query($sql);

        $scheme = $stmt->fetchAll(PDO::FETCH_OBJ);

        $db = null;

        echo json_encode($scheme);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});

//GetSchemeByCategory
$app->get('/GetSchemeByCategory/{category}', function (Request $request, Response $response) {

    $category=$request->getAttribute('category');

    $sql = "SELECT * FROM SCHEME WHERE  CATEGORY = $category";

    try{
        $db = new db();

        $db= $db->connect();

        $stmt = $db->query($sql);

        $scheme = $stmt->fetchAll(PDO::FETCH_OBJ);

        $db = null;

        echo json_encode($scheme);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});

//GetSchemeByLocation
$app->get('/GetSchemeByLocation/{location}', function (Request $request, Response $response) {

    $location=$request->getAttribute('location');

    $sql = "SELECT * FROM SCHEME WHERE  LOCATION = $location";

    try{
        $db = new db();

        $db= $db->connect();

        $stmt = $db->query($sql);

        $scheme = $stmt->fetchAll(PDO::FETCH_OBJ);

        $db = null;

        echo json_encode($scheme);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});


//AddScheme
$app->post('/AddSchemes', function (Request $request, Response $response) {

    $scheme = $request->getParam('SCHEME');
    $description = $request->getParam('DESCRIPTION');
    $category = $request->getParam('CATEGORY');
    $location = $request->getParam('LOCATION');
    $url = $request->getParam('URL');

    $sql = "INSERT INTO SCHEME (SCHEME,DESCRIPTION,CATEGORY,LOCATION,Url) VALUES (:scheme,:description,:category,:location,:url)";


    try{
        $db = new db();

        $db= $db->connect();

        $stmt = $db->prepare($sql);

        $stmt->bindParam('scheme',$scheme);
        $stmt->bindParam('description',$description);
        $stmt->bindParam('category',$category);
        $stmt->bindParam('location',$location);
        $stmt->bindParam('url',$url);

        $stmt->execute();

        echo '<script type="text/javascript">';
        echo 'alert("Scheme Added");' ;
        echo 'window.location.href = "../web pages/cardview.html";';
        echo '</script>';


    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});

//GetAllStates

$app->get('/GetAllStates', function (Request $request, Response $response) {

   $sql = "SELECT * FROM States";

    try{
        $db = new db();

       $db= $db->connect();

        $stmt = $db->query($sql);

        $states = $stmt->fetchAll(PDO::FETCH_OBJ);

        $db = null;

        echo json_encode($states);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});

//GetComplain
$app->get('/GetComplain', function (Request $request, Response $response) {

   $sql = "SELECT * FROM COMPLAIN WHERE STATUS=1";

    try{
        $db = new db();

       $db= $db->connect();

        $stmt = $db->query($sql);

        $complain = $stmt->fetchAll(PDO::FETCH_OBJ);

        $db = null;

        echo json_encode($complain);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});


$app->post('/AddUser', function (Request $request, Response $response) {

    $user = $request->getParam('User');

   $product= array();

    $sql = "INSERT IGNORE INTO UserTable (Account_ID) VALUES (:user)";


    try{
        $db = new db();

        $db= $db->connect();

        $stmt = $db->prepare($sql);

        $stmt->bindParam('user',$user);


        $stmt->execute();

       $product['error']=false;

        echo json_encode($product);

    }catch (PDOException $exception){

        echo '{"error": '.$exception->getMessage().'}';
    }
});


$app->run();
