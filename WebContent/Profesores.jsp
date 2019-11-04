<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/funciones.js"></script>
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
<jsp:include page="ModalPersona.html"></jsp:include>
<body onresize="cantidadPaginas()">
<jsp:include page="NavBarAdmin.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<h2>Profesores</h2>
</div>
<button id="btnAgregar" class="btn btn-primary btn-add" onclick="mostrarModal()" data-toggle="modal" data-target=".bd-example-modal-lg">Añadir profesor</button>	 			 
 <table id="Gridview" class="table table-hover">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Legajo</th>
                <th scope="col">Apellido y nombre</th>
                <th scope="col">Dirección</th>
                <th scope="col">Email</th>
                <th scope="col">Teléfono</th>
                <th scope="col" id="colAcciones">Acciones</th>
            </tr>
        </thead>
        <tbody id="grilla">
            <tr>
		      <th scope="row">1000</th>
		      <td>Fernández, Claudio</td>
		      <td>Avenida 123</td>
		      <td>claudio@profesor.com</td>
		      <td>159999999</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon"  id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon"  id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1001</th>
		      <td>Sar, Maximiliano</td>
		      <td>Avenida 231</td>
		      <td>maxielprofe@profesor.com</td>
		      <td>159994567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1002</th>
		      <td>Natale, Damian</td>
		      <td>Calle 654</td>
		      <td>damielprofe@profesor.com</td>
		      <td>159894567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1003</th>
		      <td>Simon, Angel</td>
		      <td>calle 555</td>
		      <td>simonelprofe@profesor.com</td>
		      <td>154994567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1004</th>
		      <td>Lupani, Cecilia</td>
		      <td>Avenida 999</td>
		      <td>chechulaprofe@profesor.com</td>
		      <td>159594567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1005</th>
		      <td>Gerosa, Adrian</td>
		      <td>Avenida 231</td>
		      <td>adrianelprofe@profesor.com</td>
		      <td>153294567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1006</th>
		      <td>Brizzi, Maria</td>
		      <td>Avenida 231</td>
		      <td>marialaprofe@profesor.com</td>
		      <td>159544567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
   			 </tr>
			             <tr>
		      <th scope="row">1007</th>
		      <td>Arnedo, Brian</td>
		      <td>calle 444</td>
		      <td>brianelprofe@profesor.com</td>
		      <td>159994567</td>
		                  <td>
              <button type="button" class="btn btn-primary btn-icon" id="btnVer"><img src="https://i.ibb.co/yNgpRb4/eye.png" height="30" width="30" /></button>
              <button type="button" class="btn btn-success btn-icon" id="btnEditar"><img src="https://i.ibb.co/7Yj831F/edit.png" height="30" width="30"/></button>
            	<button type="button" class="btn btn-danger btn-icon" id="btnEliminar"><img src="https://i.ibb.co/JK4T4ZR/delete.png" height="30" width="30" /></button>
            </td>
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
	
	CurrentItem = document.getElementById("mnProfesores");
	CurrentItem.className +=" active";
	
	$("#TituloModal").html("Añadir profesor");
	
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

	$('#myModal').on('shown.bs.modal', function () {
	    $('#myInput').trigger('focus')
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

function mostrarModal(){
	$("#ModalRegistro").modal('show');
	document.getElementById("txtApellido").focus();
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