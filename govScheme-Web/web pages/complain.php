<link href="css/complain_style.css" rel="stylesheet">

<?php
   session_start();

  if(!isset($_SESSION['user']))
  {

       echo '<script type="text/javascript">';
       echo 'alert("Access Denied. Login to view this Page.");';
       echo 'window.location.href = "../web pages/login.php";';
       echo '</script>';
     }

?>

	<meta charset="utf-8" />
	<title>Table Style</title>
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
	        <style type="text/css">
	            tr.header
	            {
	                font-weight:bold;
	            }
	            tr.alt
	            {
	                background-color: #777777;
	            }
	        </style>
	        <script type="text/javascript">
	            $(document).ready(function(){
	               $('.striped tr:even').addClass('alt');
	            });
	        </script>


<?php

$con=mysqli_connect("mysql.hostinger.in","u227762889_learn","Juhi20301","u227762889_learn");
// Check connection
if (mysqli_connect_errno())
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$query = mysqli_query($con,"SELECT id,Problem,Location FROM COMPLAIN WHERE status=0");
        ?>

<div class="table-title">

</div>
<table class="table-fill">
<thead>
<tr>
<th class="text-left">S.No.</th>
<th class="text-left">Problem</th>
<th class="text-left">Location</th>
<th class="text-left">Action</th>
</tr>
</thead>

<tbody class="table-hover">
	<?php
	  $count=1;
							while ($row = mysqli_fetch_array($query)) {
									echo "<tr>";
									echo "<td>".$row[id]."</td>";
									echo "<td>".$row[Problem]."</td>";
									echo "<td>".$row[Location]."</td>";
									$count++;
									$id = $row[id];
									?>
							<td>
								<form action='http://hosting619.96.lt/api/Accept_Complain.php' method='GET' > <input type = 'image' src="assets/accept.png" alt="submit">
									<input type="hidden" value="<?php echo $id; ?>" name="right">
								</form>


								<form action='http://hosting619.96.lt/api/Decline_Complain.php'> <input type='image' src="assets/decline.png" alt="submit">
									<input type="hidden" value="<?php echo $id; ?>" name="wrong">

								</form> </td>
									</tr>
								<?php
																		}

					 ?>

</tbody>
</table>
