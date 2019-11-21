
function mostrarPersona(legajo, tipo){
	var servlet;
	if(tipo == "P"){	
		servlet = "servletProfesor";
	}
	else{
		servlet = "servletAlumno";
	}
	$.post(servlet ,{"legajo": legajo}, function(responseJson) {
		$("#txtLegajo").val(legajo);
		$("#txtNombre").val(responseJson.Nombre);
		var dia = responseJson.FechNac.day;
		var mes = responseJson.FechNac.month;
		if(responseJson.FechNac.day < 10) dia = "0" + responseJson.FechNac.day;
		if(responseJson.FechNac.month < 10) mes = "0" + responseJson.FechNac.month;
		$("#dtpFechNac").val(dia + "/" + mes + "/" + responseJson.FechNac.year);
		$("#txtMail").val(responseJson.Mail);
		$("#txtTelefono").val(responseJson.Telefono);
		$("#txtApellido").val(responseJson.Apellido);
		$("#txtCalle").val(responseJson.Domicilio.Calle);
		$("#txtProvincia").val(responseJson.Domicilio.Provincia.ID);
		listarLocalidades($("#txtProvincia").val());
		//$("#txtLocalidad>option:eq("+responseJson.Domicilio.Localidad.ID+")").prop("selected", true);
		var valueLocalidad =  responseJson.Domicilio.Localidad.ID;
		$("#SelectLocalidad").val(responseJson.Domicilio.Localidad.ID)
		$("#txtLocalidad option[value =' "+ valueLocalidad +"']").prop("selected", true);
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
 	$("#btnAgregar").prop('hidden', true);
	
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
 	$("#btnAgregar").prop('hidden', false);
 	
 	$("#btnAgregar").val("Modificar");
 	$("#btnAgregar").text("Modificar");
 	
 	
 	
	$("#ModalRegistro").on('shown', function(){
		$("#txtLocalidad option[value =' "+ $("#SelectLocalidad").val() +"']").prop("selected", true);
		
	}); 
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
	$.post("servletProfesor",{"nuevoLegajo": "1"}, function(responseJson) {
		$("#txtLegajo").val(responseJson);
	});
 	$("#btnAgregar").val("Agregar");
 	$("#btnAgregar").text("Agregar");
	$("#ModalRegistro").modal('show');
}

function nuevoAlumno(){
	$.post("servletAlumno",{"nuevoLegajo": "1"}, function(responseJson) {
		$("#txtLegajo").val(responseJson);
	});
 	$("#btnAgregar").val("Agregar");
 	$("#btnAgregar").text("Agregar");
	$("#ModalRegistro").modal('show');
}

function nuevoCurso(){
	$.post("servletCurso",{"nuevoID": "1"}, function(responseJson) {
		$("#txtNumero").val(responseJson);
	});

	$("#ModalRegistro").modal('show');	
}

function mostrarAlumnos(IDCurso){
	$.post("servletCurso",{"listarAlumnosCurso": IDCurso}, function(responseJson) {
		$("#grillaAlumnosCurso").html(responseJson);
	});
	
	var table = $("#GridVerAlumnos").DataTable();
	$("#TituloModalVer").html("Alumnos inscriptos en el curso - " + IDCurso);
	$("#ModalAlumnosCurso").modal('show');
	$("#CantAlumnosVer").html("Cantidad: " + table.rows().data().length);
}

function mostrarMisAlumnos(IDCurso){
	$.post("servletMisCursos",{"setIDCurso": IDCurso}, function(responseJson) {
	});
}

function eliminarProfesor(legajo){
	$.post("servletProfesor",{"legajo": legajo}, function(responseJson) {
		$("#Legajo").val(legajo);
		$("#MensajeEliminar").html("&#191Desea eliminar al profesor: " + responseJson.Apellido + ", " + responseJson.Nombre + " - Legajo: "+ legajo +"?")
	});
	
	$("#ModalEliminar").modal('show');
}

function eliminarAlumno(legajo){
	$.post("servletAlumno",{"legajo": legajo}, function(responseJson) {
		$("#Legajo").val(legajo);
		$("#MensajeEliminar").html("&#191Desea eliminar al alumno: " + responseJson.Apellido + ", " + responseJson.Nombre + " - Legajo: "+ legajo +"?")
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
