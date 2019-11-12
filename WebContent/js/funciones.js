
function mostrarPersona(legajo, tipo){
	$.post("servletProfesor",{"legajo": legajo}, function(responseJson) {
		$("#txtLegajo").val(legajo);
		$("#txtNombre").val(responseJson.Nombre);
		$("#dtpFechNac").val(responseJson.FechNac.day + "/" + responseJson.FechNac.month + "/" + responseJson.FechNac.year);
		$("#txtMail").val(responseJson.Mail);
		$("#txtTelefono").val(responseJson.Telefono);
		$("#txtApellido").val(responseJson.Apellido);
		$("#txtCalle").val(responseJson.Domicilio.Calle);
		$("#txtProvincia").val(responseJson.Domicilio.Provincia.ID);
		$("#txtLocalidad").val(responseJson.Domicilio.Localidad.Nombre);
		if(tipo == "P"){
			$("#TituloModal").html("Profesor/a: " + responseJson.Apellido + ", " + responseJson.Nombre);			
		}
		else{
			$("#TituloModal").html("Alumno/a: " + responseJson.Apellido + ", " + responseJson.Nombre);
		}
	});
	
	$("#txtNombre").prop('disabled', true);
	$("#dtpFechNac").prop('disabled', true);
	$("#txtMail").prop('disabled', true);
	$("#txtTelefono").prop('disabled', true);
	$("#txtApellido").prop('disabled', true);
	$("#txtCalle").prop('disabled', true);
	$("#txtProvincia").prop('disabled', true);
	$("#txtLocalidad").prop('disabled', true);
 	$("#btnAñadir").prop('hidden', true);
	
	mostrarModal();
}

function editarPersona(legajo, tipo){
	mostrarPersona(legajo, tipo);
	
	$("#txtNombre").prop('disabled', false);
	$("#dtpFechNac").prop('disabled', false);
	$("#txtMail").prop('disabled', false);
	$("#txtTelefono").prop('disabled', false);
	$("#txtApellido").prop('disabled', false);
	$("#txtCalle").prop('disabled', false);
	$("#txtProvincia").prop('disabled', false);
	$("#txtLocalidad").prop('disabled', false);
 	$("#btnAñadir").prop('hidden', false);
	
	mostrarModal();
}

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
}

function nuevoProfesor(){
	$.post("servletProfesor",{"nuevo": "1"}, function(responseJson) {
		$("#txtLegajo").val(responseJson);
	});
	$("#ModalRegistro").modal('show');
}

function mostrarAlumnos(){
	$("#ModalAlumnosCurso").modal('show');
}

function eliminarProfesor(legajo){
	$.post("servletProfesor",{"legajo": legajo}, function(responseJson) {
		$("#Legajo").val(legajo);
		$("#MensajeEliminar").html("¿Desea eliminar al profesor: " + responseJson.Apellido + ", " + responseJson.Nombre + " - Legajo: "+ legajo +"?")
	});
	
	$("#ModalEliminar").modal('show');
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

function listarLocalidades(idProv){
	$.post("servletProfesor",{"provincia": idProv}, function(responseJson) {
		$("#txtLocalidad").html(responseJson);
	});
}