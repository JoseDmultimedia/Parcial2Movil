<?PHP
$hostname_localhost ="localhost";  //nuestro servidor
$database_localhost ="ambiente";//Nombre de nuestra base de datos
$username_localhost ="root";//Nombre de usuario de nuestra base de datos (yo utilizo el valor por defecto)
$password_localhost ="";//Contraseña de nuestra base de datos (yo utilizo por defecto)
$conexion= mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost );//Conexión a nuestro servidor mysql
 //mensaaje de 
//variables que almacenan los valores que enviamos por nuestra app, (observar que se llaman igual en nuestra app y aqui)
$nombre=$_POST['nombre'];
$idestudiante=$_POST['idestudiante'];
$programa=$_POST['programa'];

$query_search = "insert into estudiante(nombre,idestudiante,programa) values ('".$nombre."','".$idestudiante."','".$programa."')";//Sentencia sql a realizar
$resultado=$conexion->query($query_search);
echo"ejecucion ok";
?>