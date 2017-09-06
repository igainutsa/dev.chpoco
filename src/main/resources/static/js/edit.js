$( document ).ready(function() {
	
	var url = window.location;
	
////////////////groups
	
	$('#groups').change(function(){
		var idGroups = parseInt($("#groups").val()),
			nameGroups = $( "#groups option:selected" ).text();
		
		$.post("/resetgroups", {
			idGroups : idGroups
		},function(data) {
			
			$('#courses option[value='+data.data[1]+']').prop('selected',true);
			$('#specialty option[value='+data.data[2]+']').prop('selected',true);

		}).done(function() {
		}).fail(function(xhr, textStatus, errorThrown) {
		}).complete(function() {
		});	
	});
	
	//////////////// courses
	$('body').on('change','#courses', function(){
		var idCourses = parseInt($("#courses").val()),
			idGroups = parseInt($("#groups").val()),
			idSpecialty = parseInt($("#specialty").val());
		
			$('#groups option[value='+idGroups+']').prop('selected',true);
			
		$.post("/resetcourses", {
			idCourses : idCourses,
			idSpecialty : idSpecialty
		},function(data) {

			$('#groups option[value="'+data.data[0][0]+'"]').prop('selected',true);

		}).done(function() {
		}).fail(function(xhr, textStatus, errorThrown) {
		}).complete(function() {
		});	
	});
	
	////////////////specialty
	$('body').on('change','#specialty', function(){
		var idCourses = parseInt($("#courses").val()),
			idGroups = parseInt($("#groups").val()),
			idSpecialty = parseInt($("#specialty").val());
		
			$('#groups option[value='+idGroups+']').prop('selected',true);
			
		$.post("/resetcourses", {
			idCourses : idCourses,
			idSpecialty : idSpecialty
		},function(data) {

			$('#groups option[value="'+data.data[0][0]+'"]').prop('selected',true);

		}).done(function() {
		}).fail(function(xhr, textStatus, errorThrown) {
		}).complete(function() {
		});	
	});
		
	// SUBMIT FORM
    $("#userForm").submit(function(event) {
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	var userName = $("#userName").val(),
    		idGroup = parseInt($("#groups").val()),
    		idUser = parseInt($("#idUser").val()),
    		userSurname = $("#userSurname").val();
		
    	
		if((userName!="")&&(userSurname!="")){
			$.post("/edituser", {
	    		userName : userName,
	    		idGroup : idGroup,
	    		idUser : idUser,
	    		userSurname : userSurname
			},function(data) {
				
				alert("Редактирование студента прошло успешно");
				

			}).done(function() {
			}).fail(function(xhr, textStatus, errorThrown) {
			}).complete(function() {
			});
		}else{
    		alert("введите названия Имя и Фамилия ")
    	}
 
    }
})