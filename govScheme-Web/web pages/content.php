<?php
$con=mysqli_connect("mysql.hostinger.in","u227762889_learn","Juhi20301","u227762889_learn");
// Check connection
if (mysqli_connect_errno())
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT id,Problem,Location FROM COMPLAIN WHERE status=0");

while($row = mysqli_fetch_array($result))
{
echo "<tr>";
echo "<td>" . $row['id'] . "</td>";
echo "<td>" . $row['Problem'] . "</td>";
echo "<td>" . $row['Location'] . "</td>";
echo "</tr>";
}
echo "</table>";

mysqli_close($con);
?>
