<?php
 

class DB_CONNECT {
	
	 
 
    // constructor
    function __construct() {
        // connecting to database
        $this->connect();
    }
 
    // destructor
   // function __destruct() {
        // closing db connection
     //   $this->close($con);
   // }
 
    /**
     * Function to connect with database
     */
    function connect() {
        // import database connection variables
        require_once __DIR__ . '/db_config.php';
 
        // Connecting to mysql database
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysqli_error());
 
        // Selecing database
      //  $db = mysqli_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());
 
        // returing connection cursor
        return $con;
    }
 
    /**
     * Function to close db connection
     */
    function close($cone) {
        // closing db connection
        mysqli_close($cone);
    }
 
}
 
?>