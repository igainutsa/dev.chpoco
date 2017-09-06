$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#coursesForm").submit(function(event) {
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		coursesName :  $("#coursesName").val()
    	}
    	
    	if(formData.coursesName!=""){
    		// DO POST
	    	$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url + "/postcourses",
				data : JSON.stringify(formData),
				dataType : 'json',
				success : function(result) {
					if(result.status == "Done"){
						alert("курс добавле ->"+ result.data.coursesName);
						$("#sortable").append('<li class="ui-state-default"><h1>' + result.data.coursesName + '</h1></li>');
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
    	}else{
    		alert("введите названия курс")
    	}
    	
    	// Reset FormData after Posting
    	resetData();
 
    }
    
    function resetData(){
    	$("#coursesName").val("");
    }
})