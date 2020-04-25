<?PHP
$hostname_localhost ="localhost";
$database_localhost ="ambiente";
$username_localhost ="root";
$password_localhost ="";
$conexion= mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost );
$query_search = "select * from estudiante order by idestudiante";
$query_exec = $conexion->query($query_search);
while($row = $query_exec-> fetch_array()){
	echo $row['nombre']." <br>".$row['idestudiante']." <br>".$row['programa']."/";
	}
?>