<%@page import="java.util.ArrayList" %>
<%@page import="Dominio.Curso" %>
<%@page import="Dominio.Materia" %>
<%@page import="Dominio.Persona" %>
<%@page import="Negocio.PersonaNegocio" %>
<%@page import="Negocio.CursoNegocio" %>
<%@page import="Negocio.MateriaNegocio" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="js/funciones.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">

<link href="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
<script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/locales/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
 <!--    MODAL   -->
        <div id="ModalRegistro" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="TituloModal">Añadir curso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="FormCurso" method="post" action="servletCurso">
                    <div class="modal-body">
                    <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" href="#curso" role="tab" data-toggle="tab">Curso</a>
                            </li>
                            <li class="nav-item">
                                <a id="tabR" class="nav-link" href="#alumnos" role="tab" data-toggle="tab">Alumnos</a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">  
                        <!-- PESTAÑA CURSO --> 
                        <div role="tabpanel" class="tab-pane fade in active show" id="curso">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtNumero" class="col-form-label">Número:</label>
                                            <input type="text" readonly class="form-control" name="txtNumero" id="txtNumero">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtAño" class="col-form-label">Año:</label>
                                            <select id="slAño" name="slAño" class="custom-select">
									 		 	<option value="1" class="dropdown-item">2019</option>
									 		 	<option value="2" class="dropdown-item">2018</option>
									 		 	<option value="2" class="dropdown-item">2017</option> 
									 			 </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="slMateria" class="col-form-label">Materia:</label>
                                            <select name="slMateria" class="custom-select">
												<%
										    	MateriaNegocio negocioM = new MateriaNegocio();
											   	ArrayList<Materia> materias = negocioM.listarMaterias();
										  	 	for (Materia m : materias){
													%><option value="<%=m.getID()%>"> <%=m.getNombre()%> </option><%
											   	}
										 		%>
								 			 </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="slProfesor" class="col-form-label">Profesor:</label>
                                             <select name="slProfesor" class="custom-select">
							 		 		<%
									    	PersonaNegocio negocioP = new PersonaNegocio();
										   	ArrayList<Persona> profesores = negocioP.listarPersonas('P');
									  	 	for (Persona p : profesores){
												%><option value="<%=p.getLegajo()%>"><%=p.getLegajo()%> - <%=p.getApellido()%>, <%=p.getNombre()%> </option><%
										   	}
									 		%> 
								 			 </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="slSemestre" class="col-form-label">Semestre:</label>
                                            <select name="slSemestre" class="custom-select">
									 		 	<option value="1" class="dropdown-item">Primer</option> 
									 		 	<option value="2" class="dropdown-item">Segundo</option>
								 			 </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="txtAlumno" class="col-form-label">Alumnos:</label>
                                            <input type="text" class="form-control" id="txtAlumnos" disabled value="0" tabindex="3" >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- PESTAÑA ALUMNOS -->
                            <div role="tabpanel" class="tab-pane fade" id="alumnos" style="margin-top: 5px;">
							    <table id="GridAlumnos" class="table table-hover">
							        <thead class="thead-dark">
							            <tr>
							                <th scope="col">Legajo</th>
							                <th scope="col">Apellido y nombre</th>
							            </tr>
							        </thead>
							        <tbody id="grilla">
							 		 <%
									    	negocioP = new PersonaNegocio();
										   	ArrayList<Persona> alumnos = negocioP.listarPersonas('A');
									  	 	for (Persona a : alumnos){
									            %><tr>
											      <th scope="row"><%=a.getLegajo() %></th>
											      <td><%=a.getApellido()%>, <%=a.getNombre() %></td>
									   			 </tr>
												<%
										   	}
									 		%> 
							        </tbody>
							    </table>
							    <div>
							    	<label id="CantAlumnos">Cantidad: 0</label>
							    </div>
                            </div>
                           </div>
                       </div>
                    <div class="modal-footer">
                        <button type="button" id="btnCancelar" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" id="btnAgregar" name="btnAgregar" class="btn btn-primary" tabindex="9">Añadir</button>
                    </div>
                    <input type="hidden" id="LegajosAlumnos" name="LegajosAlumnos" value="">
                    </form>
                </div>
            </div>
        </div>
        <!--  FIN MODAL  -->
<jsp:include page="ModalAlumnosCurso.html"></jsp:include>
<body>
<jsp:include page="NavBarAdmin.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<h2>Cursos</h2>
</div>
<div id="PanelFiltros" style="height: 62px">
  <div class="row">
    <div class="col colName">Busqueda:</div>
    <div class="col colName">Materia:</div>
    <div class="col colName">Semestre:</div>
    <div class="col colName">Año:</div>
    <div class="col colName">Profesor:</div>
    <div class="col colName"></div>
    <div class="w-100"></div>
    <div class="col"><input type="text" id="txtBusqueda" class="form-control"  style="width: 160px;padding: 2px 4px; margin: 0;"></div>
    <div class="col"><select id="slMateria" name="slMateria" class="custom-select">
    <option value="-1" class="dropdown-item">Todas</option>
	<%
    	negocioM = new MateriaNegocio();
	   	materias = negocioM.listarMaterias();
  	 	for (Materia m : materias){
			%><option value="<%=m.getID()%>"> <%=m.getNombre()%> </option><%
	   	}
 		%>
 		</select>></div>
    <div class="col"><select id="slSemestre" name="slSemestre" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todos</option>
	 		 	<option value="1" class="dropdown-item">Primer</option>
	 		 	<option value="2" class="dropdown-item">Segundo</option> 
	 			 </select></div>
    <div class="col"><select id="slAño" name="slAño" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todos</option>
	 		 	<option value="1" class="dropdown-item">2019</option>
	 		 	<option value="2" class="dropdown-item">2018</option>
	 		 	<option value="2" class="dropdown-item">2017</option> 
	 			 </select></div>
    <div class="col"><select id="slProfesor" name="slProfesor" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todos</option>
 		 		<%
		    	negocioP = new PersonaNegocio();
			   	profesores = negocioP.listarPersonas('P');
		  	 	for (Persona p : profesores){
					%><option value="<%=p.getLegajo()%>"><%=p.getLegajo()%> - <%=p.getApellido()%>, <%=p.getNombre()%> </option><%
			   	}
		 		%> 
	 			 </select></div>
	 			  <div class="col"><button class="btn btn-primary btn-add" onclick="nuevoCurso()" >Añadir curso</button></div>	
	</div>
</div> 
 <table id="Gridview" class="table table-hover">
       <thead class="thead-dark">
            <tr>
                <th scope="col">Nro</th>
                <th scope="col">Materia</th>
                <th scope="col">Semestre</th>
                <th scope="col">Año</th>
                <th scope="col">Profeosr</th>
                <th scope="col" id="colAcciones">Alumnos</th>
            </tr>
        </thead>
        <tbody id="grilla">
        		<%
			String listadoCursos = null;
			if(request.getAttribute("tabla")!=null)
			{
				listadoCursos = (String)request.getAttribute("tabla");
			}		
		%>
        <%=listadoCursos%>
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
		"dom": 'rtip',
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
	
    $('#GridAlumnos').DataTable( {
		"ordering":false,
		"bInfo": false,
		"lengthChange": false,
		"pageLength": 100,
		"dom": 'frti',
		"oLanguage": {
			   "sSearch": "Busqueda:",
			 },
	 	"language": {
			   "zeroRecords": "No se encontraron registros coincidentes",
				"paginate": {
				       "next": "Siguiente",
					   "previous": "Previo"
				}
	 	}
    } );
	
    var table = $('#GridAlumnos').DataTable();
    
    $('#GridAlumnos tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
        $("#CantAlumnos").html("Cantidad: " + table.rows('.selected').data().length);
       $("#txtAlumnos").val(table.rows('.selected').data().length);
       $("#LegajosAlumnos").val(table.rows(['.selected']).data().pluck(0).toArray());
    } );
    
	$("#slMateria").on( 'change', function () {
 		if($("#slMateria").val() > -1){
			$('#Gridview').DataTable().column(1).search( $("#slMateria option:selected").text() ).draw();	
		}
		else{
			$('#Gridview').DataTable().column(1).search("").draw();		
		}
	});
	
	$("#slSemestre").on( 'change', function () {
		if($("#slSemestre").val() > -1){
			$('#Gridview').DataTable().column(2).search( $("#slSemestre option:selected").text()).draw();	
		}
		else{
			$('#Gridview').DataTable().column(2).search("").draw();		
		}
	});
	
	$("#slAño").on( 'change', function () {
		if($("#slAño").val() > -1){
			$('#Gridview').DataTable().column(3).search( $("#slAño option:selected").text() ).draw();	
		}
		else{
			$('#Gridview').DataTable().column(3).search("").draw();		
		}
	});
	
	$("#slProfesor").on( 'change', function () {
		if($("#slProfesor").val() > -1){
			$('#Gridview').DataTable().column(4).search( $("#slProfesor option:selected").text() ).draw();	
		}
		else{
			$('#Gridview').DataTable().column(4).search("").draw();		
		}
	});
	
	$("#txtBusqueda").on( 'keyup', function () {
		$('#Gridview').DataTable().search( this.value ).draw();
	});
});
</script>
</html>