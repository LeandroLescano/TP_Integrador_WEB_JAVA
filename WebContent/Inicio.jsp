<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
<body>
<div id="Menu">
	<!-- <jsp:include page="NavBarAdmin.html"></jsp:include>-->
	<jsp:include page="NavBar.html"></jsp:include>
</div>
<div class="h-100">
	<div class="row h-100 align-items-center justify-content-center text-center" style="margin-top: 10px">
		<div class="col-lg-10 align-self-end fadeInTitle zoomInUp">
			<h1 class="text-uppercase text-black font-weight-bol">Bienvenido al</h1>
			<h1 class="text-uppercase text-black font-weight-bol">Sistema de Gestión Educativa</h1>
		</div>
	</div>
</div>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <h3>Ingreso</h3>
    </div>

    <!-- Login Form -->
    <form>
      <input type="text" id="login" class="fadeIn second" name="login" placeholder="Usuario">
      <input type="text" id="password" class="fadeIn third" name="login" placeholder="Contraseña">
      <input type="submit" class="fadeIn fourth" value="Ingresar">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
    </div>

  </div>
</div>
</body>
<script type="text/javascript">
//If(Profesor)
//{
//  document.getElementById("Menu").innerMTML = '<jsp:include page="NavBarProfesor.html"></jsp:include>'
//}
// else{
//  document.getElementById("Menu").innerMTML = '<jsp:include page="NavBarAdmin.html"></jsp:include>'
//}

</script>
</html>

