$( document ).ready(function() {
	
	var url = window.location;
	
	// SUBMIT FORM
    $("#groupsForm").submit(function(event) {
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    			groupName :  $("#groupName").val(),
    			courses :  parseInt($( "#courses" ).val()),
    			specialty :  parseInt($( "#specialty" ).val())
    	}
    	
    	
    	if((formData.groupName!="")&&(formData.courses!=0)&&(formData.specialty!=0)){
    		// DO POST
	    	$.ajax({
				type : "POST",
				contentType : "application/json",
				url : url + "/postgroups",
				data : JSON.stringify(formData),
				dataType : 'json',
				success : function(result) {
					if(result.status == "Done"){
						alert("курс добавле ->"+ result.data.groupName);
						$("#sortable").append('<li class="ui-state-default"><a href="'+url+'/'+formData.groupName+'"><h1>' + formData.groupName + '</h1></a></li>');
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
    		alert("введите названия групы, курс и специальность")
    	}
    	
    	resetData();
 
    }
    
    function resetData(){
    	$("#groupName").val("");
    }
})