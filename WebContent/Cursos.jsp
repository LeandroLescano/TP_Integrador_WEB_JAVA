<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
<body>
<jsp:include page="NavBarAdmin.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<h2>Cursos</h2>
</div>
<div id="PanelFiltros" style="height: 75px">
  <div class="row">
    <div class="col colName">Busqueda:</div>
    <div class="col colName">Materia:</div>
    <div class="col colName">Semestre:</div>
    <div class="col colName">Año:</div>
    <div class="col colName">Docente:</div>
    <div class="col colName"></div>
    <div class="w-100"></div>
    <div class="col"><input type="text" class="form-control border" placeholder="Progr..." style="width: 160px;padding: 2px 4px; margin: 0;"></div>
    <div class="col"><select name="slMateria" class="custom-select">
	 		 	<option value="0" class="dropdown-item">Todas</option> 
	 			 </select></div>
    <div class="col"><select name="slSemestre" class="custom-select">
	 		 	<option value="0" class="dropdown-item">Todos</option> 
	 			 </select></div>
    <div class="col"><select name="slAño" class="custom-select">
	 		 	<option value="0" class="dropdown-item">2019</option> 
	 			 </select></div>
    <div class="col"><select name="slDocente" class="custom-select">
	 		 	<option value="0" class="dropdown-item">Todos</option> 
	 			 </select></div>
      <div class="col"><button class="btn btn-primary" style="float: right">Añadir curso</button></div>	 			 
  </div>
</div>
 <table id="Gridview" class="table table-hover">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Nro</th>
                <th scope="col">Materia</th>
                <th scope="col">Semestre</th>
                <th scope="col">Año</th>
                <th scope="col">Docente</th>
                <th scope="col" id="colAcciones">Alumnos</th>
            </tr>
        </thead>
        <tbody id="grilla">
            <tr>
		      <th scope="row">1</th>
		      <td>Laboratorio IV</td>
		      <td>Segundo semestre</td>
		      <td>2019</td>
		      <td>Fernández Claudio</td><!-- Realizarlo con un link para poder ver detalles del profesor -->
		      <td><a href="#" onclick="mostrarAlumnos()" class="btn btn-primary">Ver alumnos</a></td>
   			 </tr>
             <tr>
		      <th scope="row">2</th>
		      <td>Laboratorio III</td>
		      <td>Primer semestre</td>
		      <td>2019</td>
		      <td>Fernández Claudio</td><!-- Realizarlo con un link para poder ver detalles del profesor -->
		      <td><a href="#" onclick="mostrarAlumnos()" class="btn btn-primary">Ver alumnos</a></td>
   			 </tr>
        </tbody>
    </table>
</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var screenH = window.innerHeight;
	var cantPags;
	if(screenH < 615){
		cantPags = 4;
	}
	else if(screenH < 680){
		cantPags = 5;
	}
	else if(screenH < 740){
		cantPags = 6;
	}
	else{
		cantPags = 7;
	}
	
	CurrentItem = document.getElementById("mnCursos");
	CurrentItem.className +=" active";
	$('#Gridview').DataTable({
		"ordering":false,
		"bInfo": false,
		"lengthChange": false,
		"pageLength": cantPags,
		"dom":'frtip',
		"oLanguage": {
			   "sSearch": "Busqueda:",
			 },
	 	"language": {
			   "zeroRecords": "No se encontraron registros coincidentes",
				"paginate": {
				       "next": "Siguiente",
					   "previous": "Previo"
				},
	 	}
	});
});

function cantidadPaginas(){
	var screenH = window.innerHeight;
	var cantPags;
	if(screenH < 615){
		$('#Gridview').DataTable().page.len(4).draw();
	}
	else if(screenH < 680){
		$('#Gridview').DataTable().page.len(5).draw();
	}
	else if(screenH < 740){
		$('#Gridview').DataTable().page.len(6).draw();
	}
	else{
		$('#Gridview').DataTable().page.len(7).draw();
	}
}

function filtrarTabla(){
	// Variables
	var input, filter, table, tr, td, i ;
	input = document.getElementById("txtBusqueda");
	filter = input.value.toUpperCase();
	table = document.getElementById("Gridview");
	tr = table.getElementsByTagName("tr"),
	th = table.getElementsByTagName("th");
	
	// Iteración entre las filas y columnas
	for (i = 1; i < tr.length; i++) {
	            tr[i].style.display = "none";
	            for(var j=0; j<th.length; j++){
	        td = tr[i].getElementsByTagName("td")[j];      
	        if (td) {
	            if (td.innerHTML.toUpperCase().indexOf(filter.toUpperCase()) > -1)                               {
	                tr[i].style.display = "";
	                break;
	            }
	        }
	    }
}
}
</script>
</html>