  $( function() {

	    $( "#files" ).selectmenu();
	    
	    $( "#number" )
	      .selectmenu()
	      .selectmenu( "menuWidget" )
	        .addClass( "overflow" );
	 
	    $( "#salutation" ).selectmenu();
	  
    $( "#sortable" ).sortable({
      placeholder: "ui-state-highlight"
    });
    $( "#sortable" ).disableSelection();
  });
  
