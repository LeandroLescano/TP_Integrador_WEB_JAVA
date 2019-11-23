
function mostrarPersona(legajo, tipo){
	var servlet;
	if(tipo == "P"){	
		servlet = "servletProfesor";
	}
	else{
		servlet = "servletAlumno";
	}
	var valueLocalidad;
	
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
		$("#txtLocalidad").val(responseJson.Domicilio.Localidad.ID);
		if(tipo == "P"){
			$("#TituloModal").html("Profesor/a: " + responseJson.Apellido + ", " + responseJson.Nombre);	
		}
		else{
			$("#TituloModal").html("Alumno/a: " + responseJson.Apellido + ", " + responseJson.Nombre);
		}

	});
	
	deshabilitarCampos();
 	$("#btnAgregar").prop('hidden', true);
	
	mostrarModal();
}

function editarPersona(legajo, tipo){
	mostrarPersona(legajo, tipo);
	
	habilitarCampos();
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
	listarLocalidades(1);
	habilitarCampos();
	limpiarCampos();
	$.post("servletProfesor",{"nuevoLegajo": "1"}, function(responseJson) {
		$("#txtLegajo").val(responseJson);
	});
	
    var objeto = [document.getElementById("txtNombre"),
        document.getElementById("txtApellido"),
        document.getElementById("txtMail"),
        document.getElementById("txtTelefono"),
        document.getElementById("dtpFechNac"),
        document.getElementById("txtCalle")]
        success = true;
        for (var i = 0; i < 6; i++) {
        	validarVacio(objeto[i].id);
        	sacarFoco(objeto[i].id);
        }
	
 	$("#btnAgregar").val("Agregar");
 	$("#btnAgregar").text("Agregar");
	$("#TituloModal").html("A&#241adir profesor");
	$("#ModalRegistro").modal('show');
}

function nuevoAlumno(){
	listarLocalidades(1);
	habilitarCampos();
	limpiarCampos();
	$.post("servletAlumno",{"nuevoLegajo": "1"}, function(responseJson) {
		$("#txtLegajo").val(responseJson);
	});
	
    var objeto = [document.getElementById("txtNombre"),
        document.getElementById("txtApellido"),
        document.getElementById("txtMail"),
        document.getElementById("txtTelefono"),
        document.getElementById("dtpFechNac"),
        document.getElementById("txtCalle")]
        success = true;
        for (var i = 0; i < 6; i++) {
        	validarVacio(objeto[i].id);
        	sacarFoco(objeto[i].id);
        }
	
 	$("#btnAgregar").val("Agregar");
 	$("#btnAgregar").text("Agregar");
	$("#TituloModal").html("A&#241adir alumno");
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
		$("#CantAlumnosVer").html("Cantidad: " + responseJson[0]);
		$("#grillaAlumnosCurso").html(responseJson[1]);
	});
	
	var table = $("#GridVerAlumnos").DataTable();
	$("#TituloModalVer").html("Alumnos inscriptos en el curso - " + IDCurso);
	$("#ModalAlumnosCurso").modal('show');

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

function listarLocalidadesUsadas(){
	$.post("servletProfesor",{"Localidades": 1}, function(responseJson) {
		$("#txtLocalidad").html(responseJson);
	});
}

function habilitarCampos(){
	$("#txtNombre").prop('disabled', false);
	$("#dtpFechNac").prop('disabled', false);
	$("#txtMail").prop('disabled', false);
	$("#txtTelefono").prop('disabled', false);
	$("#txtApellido").prop('disabled', false);
	$("#txtCalle").prop('disabled', false);
	$("#txtProvincia").prop('disabled', false);
	$("#txtLocalidad").prop('disabled', false);
}

function deshabilitarCampos(){
	$("#txtNombre").prop('disabled', true);
	$("#dtpFechNac").prop('disabled', true);
	$("#txtMail").prop('disabled', true);
	$("#txtTelefono").prop('disabled', true);
	$("#txtApellido").prop('disabled', true);
	$("#txtCalle").prop('disabled', true);
	$("#txtProvincia").prop('disabled', true);
	$("#txtLocalidad").prop('disabled', true);
}

function limpiarCampos(){
	$("#txtNombre").val('');
	$("#dtpFechNac").val('');
	$("#txtMail").val('');
	$("#txtTelefono").val('');
	$("#txtApellido").val('');
	$("#txtCalle").val('');
	$("#txtProvincia").val(1);
	$("#txtLocalidad").val('');
	
}

function sacarFoco(id) {
    objeto = document.getElementById(id);
    objeto.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,0)";
};

function enFoco(id) {
    objeto = document.getElementById(id);
    if (objeto.classList.contains("border-success")) {
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(79, 162, 51, 0.25)";
    }
    else if (objeto.classList.contains("border-danger")) {
        objeto.className = "form-control border border-danger";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(255, 0, 0, 0.23)";
    }
    else {
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,.25)";
    }
};

function validarVacio(id) {
    objeto = document.getElementById(id);
    valueForm = objeto.value;
    if (valueForm != "" && valueForm.length >= 2) {
        objeto.className = "form-control border border-success";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(79, 162, 51, 0.25)";
    }
    else if (valueForm == "") {
        objeto.className = "form-control";
        objeto.style.border = "1px solid #ced4da";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,.25)";
    }
    else {
        objeto.className = "form-control border border-danger";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(255, 0, 0, 0.23)";
    }
};

function validarEmail() {
    objeto = document.getElementById("txtMail");
    valueForm = objeto.value;
    var patron = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
    if (valueForm.search(patron) == 0) {
        objeto.className = "form-control border border-success";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(79, 162, 51, 0.25)";
    } else {
        objeto.className = "form-control border border-danger";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(255, 0, 0, 0.23)";
    }
};

function validarNumero() {
    objeto = document.getElementById("txtTelefono");
    valueForm = objeto.value;
    if (valueForm.length == 0) {
        objeto.className = "form-control";
        objeto.style.border = "1px solid #ced4da";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,.25)";
    }
    else if (valueForm.length < 8) {
        objeto.className = "form-control border border-danger";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(255, 0, 0, 0.23)";
    }
    else {
        objeto.className = "form-control border border-success";
        objeto.style.boxShadow = "0 0 0 0.2rem rgba(79, 162, 51, 0.25)";
    }
};

function habilitarAgregar() {
    var objeto = [document.getElementById("txtNombre"),
    document.getElementById("txtApellido"),
    document.getElementById("txtMail"),
    document.getElementById("txtTelefono"),
    document.getElementById("dtpFechNac"),
    document.getElementById("txtCalle")]
    success = true;
    for (var i = 0; i < 6; i++) {
        if (!objeto[i].classList.contains("border-success")) {
            success = false;
        }
    }
    if (success) {
        document.getElementById("btnAgregar").disabled = false;
    }
    else {
        document.getElementById("btnAgregar").disabled = true;
    }
};