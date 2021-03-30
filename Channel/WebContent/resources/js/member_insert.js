	$(document).ready(function() {
		$('.input').focus(function() {
			$(this).parent().find(".label-txt").addClass('label-active');
		});
		$(".input").focusout(function() {
			if ($(this).val() == '') {
				$(this).parent().find(".label-txt").removeClass('label-active');
			}
		});
		
		$(function() { $('#postcodify_search_button').postcodifyPopUp(); });
		
		$("#id_check").click(function(){
			var value = document.getElementsByName("id")[0].value;			
			if(value == null || value.trim() == ""){
				alert("id를 입력해 주세요");
			} else {
				$.ajax({
					url : "MemberController?command=idcheck&id="+value,
					type : "get",
					success: function(data){
						alert(data);
						if(data !== "ID가 존재합니다."){
							document.getElementsByName("id")[0].title = "y";
							document.getElementsByName("pw")[0].focus();
						} else {
							document.getElementsByName("id")[0].focus();
						}
					},
					error: function(){
						alert("확인이 되지 않았습니다. 다시 확인해 주세요.");
					}					
				});								
			}
			
		});
		
		$('input[name=id]').change(function(){
			document.getElementsByName("id")[0].setAttribute('title','n');

		});
		
		$('#pw_check').click(function(){			
			var pw = document.getElementsByName("pw")[0].value;
			var re_pw = document.getElementsByName("re_pw")[0].value;
			if(pw !== null && pw.trim() !== ''){
				if(pw == re_pw){
					alert("비밀번호가 확인되었습니다.");
					document.getElementsByName("pw")[0].title = "y";
				} else {
					alert("비밀번호가 같지 않습니다.");
					document.getElementsByName("pw")[0].title = "n";
				}
			} else {
				alert("비밀번호를 입력해 주세요");
			}			
		});
		
		$('input[name=pw]').change(function(){
			document.getElementsByName("pw")[0].setAttribute('title','n');
		});
		$('input[name=re_pw]').change(function(){
			document.getElementsByName("pw")[0].setAttribute('title','n');
		});
		
		$("form").submit(function() {
			idchk = document.getElementsByName("id")[0].title;
			pwchk = document.getElementsByName("pw")[0].title;
			pschk = document.getElementsByName("pscode")[0].title;
			psvalue = document.getElementsByName("pscode")[0].value;
			
			if(idchk == 'n'){
				alert("id 중복체크를 해주세요.");
				document.getElementsByName("id")[0].focus();
				return false;
			}
			if(pwchk == 'n'){
				alert("비밀번호 재확인을 해주세요.");
				document.getElementsByName("pw")[0].focus();
				return false;
			}
			if(psvalue !== null && psvalue.trim() !== ""){
				document.getElementsByName("pscode")[0].setAttribute('title','y');
			} else {
				alert("우편번호를 검색해 주세요.");
				return false;
			}			
			
		})		
		
	});
		
