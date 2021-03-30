$(document).ready(function() { 
	var member_num;
	$.ajax({ 
		url: "MemberController?command=member_numdata",
		type: "post",
		dataType: "text",
		success: function(data){
			member_num = data;			
			$.ajax({ 
				url: "MemberController?command=member_statement",
				type: "post",
				data: { 
						"member_num" : member_num,				
						"member_statement" : "1"
					  },
				success: function(data){
				},
				error: function(){ 
				}		
			});
		},
		error: function(){ 
		}		
	});	
		
	$(window).bind("beforeunload", function (e){
		 $.ajax({ 
			url: "MemberController?command=member_statement",
			type: "post",
			data: { 
					"member_num" : member_num,
					"member_statement" : "2"
				  },
			async: true,
			success: function(data){
			},
			error: function(){ 
			}		
		});
	});
});