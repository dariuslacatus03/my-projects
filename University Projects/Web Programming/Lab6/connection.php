<?php
$connection = mysqli_connect("localhost", "root", "", "my_prac_db");

if(!$connection)
{
    echo "EROARE!".'</br>';
    die(mysqli_connect_error());
}



?>