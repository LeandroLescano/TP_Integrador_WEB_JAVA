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

function mostrarAlumnos(){
	$("#ModalAlumnosCurso").modal('show');
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