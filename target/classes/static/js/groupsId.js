$( document ).ready(function() {
	
	var url = window.location;
	
	
	
	$('body').on('click','.editUser', function(event) {
		var id = $(this).val();
		location= "/edit/"+id;
	});
	
	$('body').on('click','.delete', function(event) {
		var th = $(this),
		deleteData = {
			id : th.val()
		};
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/deleteuser",
			data : JSON.stringify(deleteData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){					
					alert("студент успешно удальон");					
					th.parent().parent().remove();
				}else{
					alert("что то пошло не так");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
	});
	
	
	
	
	// SUBMIT FORM
    $("#userForm").submit(function(event) {
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	var userName = $("#userName").val(),
    		idGroup = parseInt($("#idGroup").val()),
    		userSurname = $("#userSurname").val(),
    		nameGroup = $("#nameGroup").val(),
    		nameCourses = $("#nameCourses").text(),
    		nameSpecialty = $("#nameSpecialty").text();
		
    	
		if((userName!="")&&(userSurname!="")){
			$.post("/addusergroups", {
	    		userName : userName,
	    		idGroup : idGroup,
	    		userSurname : userSurname
			},function(data) {
				$('.table > tbody:last-child').append('<tr>'
						+'<td><h1>'+userName+'</h1></td>'
						+'<td><h1>'+userSurname+'</h1></td>'
						+'<td><h1>'+nameGroup+'</h1></td>'
						+'<td><h1 id="nameCourses">'+nameCourses+'</h1></td>'
						+'<td><h1 id="nameSpecialty">'+nameSpecialty+'</h1></td>'
						+'<td><button type="button" class="btn-info editUser" value="'+data.data+'">Редактировать</button></td>'
						+'<td><button type="button" class="btn-danger delete" value="'+data.data+'">Удалить</button></td>'
						+'</tr>');	

			}).done(function() {
			}).fail(function(xhr, textStatus, errorThrown) {
			}).complete(function() {
			});
		}else{
    		alert("введите названия Имя и Фамилия ")
    	}
    	
    	// Reset FormData after Posting
    	resetData();
 
    }
    
    function resetData(){
    	$("#userName").val("");
    	$("#userSurname").val("");
    }
})