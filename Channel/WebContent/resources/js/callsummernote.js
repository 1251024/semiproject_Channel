$(function(){
	var sendButton = function () {
		  var ui = $.summernote.ui;

		  // create button
		  var button = ui.button({
		    contents: '<span id="sendButton" >SEND</span>',
		    tooltip: 'send',
		    click: function () {
		    	var inputmsg = $('#summernote').val();
				var outmsg = inputmsg.replace(/<(\/?)p>/gi, '');
				inputmsg.replace('<br>', '');
				$('#summernote').summernote('reset');
					send(outmsg);
		    }
		  });

		  return button.render();   // return button as jquery object
		}
	
	//썸머노트 실행
	$(document).ready(function() {
		

		
		
		$('#summernote').summernote(
										{
											height : 100,
											minHeight : 30,
											maxHeight : 100, 
											focus : true, 
											dialogsInBody : true,
											placeholder : '메세지를 입력해주세요.',
											lang : 'ko-KR',
											toolbar : [
													// [groupName, [list of button]]
													[ 'style', [ 'bold', 'underline' ] ],
													[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
													[ 'insert', [ 'link', 'picture', 'video' ] ] ,
													[ 'mybutton', ['send']] ],
													buttons:{
														send: sendButton
													},
											callbacks : { // 여기 부분이 이미지를 첨부하는 부분
												onImageUpload : function(files) {
													sendFile(files[0], this);
												},
												onPaste : function(e) {
													var clipboardData = e.originalEvent.clipboardData;
													if (clipboardData
															&& clipboardData.items
															&& clipboardData.items.length) {
														var item = clipboardData.items[0];
														if (item.kind === 'file'
																&& item.type
																		.indexOf('image/') !== -1) {
															e.preventDefault();
														}
													}
												}
											}
										});
						$('.note-statusbar').hide();	
						// 엔터 입력시 에어리어 초기화 및 값 전송
						$('#summernote').on('summernote.enter', function() {
							$(function() {
							var inputmsg = $('#summernote').val();
							var outmsg = inputmsg.replace(/<(\/?)p>/gi, '');
							inputmsg.replace('<br>', '');
							$('#summernote').summernote('reset');
								send(outmsg);
							})
						});
		});
	
})
// 이미지 파일 업로드
	function sendFile(file, editor) {
		// 파일 전송을 위한 폼생성
		var data = new FormData();
		data.append("uploadFile", file);
		$.ajax({ // ajax를 통해 파일 업로드 처리
			data : data,
			type : "POST",
			url : "FileController",
			cache : false,
			async : false,
			contentType : false,
			processData : false,
			success : function(data) { // 처리가 성공할 경우
				// 에디터에 이미지 출력
				$(editor).summernote('editor.insertImage', data.url);
			}
		});
	}

$(function(){

	$("#sendButton").closest("button").css('float','right');
	
})

