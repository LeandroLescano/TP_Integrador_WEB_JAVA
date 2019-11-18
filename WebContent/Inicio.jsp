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
    <!--<div class="wrapper fadeInDown">
  <div id="formContent">

    <div class="fadeIn first">
      <h3>Ingreso</h3>
    </div>

    <form>
      <input type="text" id="login" class="fadeIn second" name="login" placeholder="Usuario">
      <input type="text" id="password" class="fadeIn third" name="login" placeholder="Contraseña">
      <input type="submit" class="fadeIn fourth" value="Ingresar">
    </form>

    <div id="formFooter">
    </div>

  </div>
  </div>-->
  
      <div class="fadeInDown" id="login">
        <div class="container" style="width: 30%;background-color:white;" >
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" style="padding: 0px;" class="col-md-6">
                    <div id="login-box" style="padding: 0px;" class="col-md-12">
                        <form id="login-form" class="form" action="servletLogueo" method="post">
                            <h3 class="text-center fadeIn second">Iniciar sesión</h3>
                            <div class="form-group  fadeIn third">
                                <label for="txtEmail">E-mail:</label><br>
                                <input type="text" name="txtEmail" id="txtEmail" class="form-control">
                            </div>
                            <div class="form-group  fadeIn fourth">
                                <label for="txtContraseña">Contraseña:</label><br>
                                <input type="text" name="txtContraseña" id="txtContraseña" class="form-control">
                            </div>
                            <div class="form-group  fadeIn fourth">
                                <input type="submit" name="btnIngresar" class="btn btn-primary btn-md" value="Ingresar">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  
  
  
</body>
<script type="text/javascript">
$(document).ready(function(){


});



</script>
</html>

