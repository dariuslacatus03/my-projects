<?php

require '../connection.php';

session_start();
try{

    $id = $_GET['id'];
    $sql ="DELETE FROM destinations where id=".$id;

    $result = mysqli_query($connection, $sql);
    $_SESSION['message'] = "Succesful operation!";
}
catch (Exception $e) {
     $_SESSION['message'] = $e->getMessage();
}
    return header("location:../");

?>
