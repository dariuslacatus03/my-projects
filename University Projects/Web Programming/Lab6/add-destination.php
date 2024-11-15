<?php

require 'connection.php';

?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
</head>
<body>
    <div class="container">
    <div class="row">
    <form method="POST" action="backend/add-destination-backend.php">
      <div class="form-group">
        <label for="location">Location</label>
        <input type="text" class="form-control" id="location" name="location" placeholder="Enter Location" required>
      </div>
      <div class="form-group">
        <label for="country">Country</label>
        <input type="text" class="form-control" id="country" name="country" placeholder="Enter Country" required>
      </div>
     <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description" name="description" placeholder="Enter Description" required>
      </div>
     <div class="form-group">
        <label for="targets">Tourist Targets</label>
        <input type="text" class="form-control" id="targets" name="targets" placeholder="Enter Targets" required>
      </div>
      <div class="form-group">
        <label for="costs">Costs</label>
        <input type="number" class="form-control" id="costs" name="costs" required>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
      <a href="./index.php" type="button" class="btn btn-danger">Back</a>
    </form>
    </div>
    </div>
</body>
</html>


