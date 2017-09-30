<?php


class db
{
    private $dbhost = 'mysql.hostinger.in';
    private $dbuser = 'u227762889_learn';
    private $dbpass = 'Juhi20301';
    private $dbname = 'u227762889_learn';

    public function connect(){
        $mysql_connect_str = "mysql:host=$this->dbhost;dbname=$this->dbname";
        $dbConnection = new PDO($mysql_connect_str, $this->dbuser, $this->dbpass);

        $dbConnection->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

        return $dbConnection;
    }

}