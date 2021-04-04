$(function() {
	
				var url = "covidController";
				$.ajax({
					type : "GET",
					url : url + "?command=covidInfo",
					
					dataType : "text",
					
					success : function(data) {
						// alert(data);
						var temp = $.trim(data);
						//alert(temp);
						var obj = JSON.parse(temp);
						//alert(obj);
						//console.log(obj);
						//console.log(obj[0].createDt);
						//console.log(createDt);
						//console.log(obj.createDt);
						
						$("#stateDt").val(obj.stateDt);
						$("#decideCnt").val(obj.decideCnt);
						$("#deathCnt").val(obj.deathCnt);
						$("#careCnt").val(obj.careCnt);
						$("#accExamCnt").val(obj.accExamCnt);
						$("#examCnt").val(obj.examCnt);
						$("#resultNegCnt").val(obj.resultNegCnt);
						$("#accDefRate").val(obj.accDefRate);
						
						if(data !== null){
							
					$(function() {
	
				var url = "covidController" ;
				$.ajax({
					type : "GET",
					url : url + "?command=covidInfo_2",
					dataType : "text",
					//contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
					success : function(data) {
						//var xmlDoc = data.responseXML;
    					//var x = xmlDoc.getElementsByTagName("gubun")[0];
						//alert(x);
						
						var temp = $.trim(data);
						//alert(temp);
						//var obj = JSON.parse(temp);
						//alert(obj);
						
						//var gubun = temp.getElementsByTagName("gubun"); 
						//var defCnt = temp.getElementsByTagName("defCnt"); 
						//var incDec = temp.getElementsByTagName("incDec"); 
						
						//alert(gubun);
						
						parser = new DOMParser();
						
						xmlDoc=parser.parseFromString(temp,"text/xml");

						var xml = xmlDoc.getElementsByTagName('rss');
						//alert(xml[0].getElementsByTagName('gubun')[0].childNodes[0].nodeValue);
						//alert(xml[0].getElementsByTagName('gubun')[1].childNodes[0].nodeValue);
						//alert("(+"+xml[0].getElementsByTagName('incDec')[0].childNodes[0].nodeValue+")");

						defCnt1 = xml[0].getElementsByTagName('defCnt')[1].childNodes[0].nodeValue;
						incDec1 = "(+"+xml[0].getElementsByTagName('incDec')[1].childNodes[0].nodeValue+")";						
						$("#defCnt1").val(defCnt1);
						$("#incDec1").val(incDec1);
						
						var defCnt2 = xml[0].getElementsByTagName('defCnt')[2].childNodes[0].nodeValue;
						var incDec2 = "(+"+xml[0].getElementsByTagName('incDec')[2].childNodes[0].nodeValue+")";
						$("#defCnt2").val(defCnt2);
						$("#incDec2").val(incDec2);
						
						var defCnt3 = xml[0].getElementsByTagName('defCnt')[3].childNodes[0].nodeValue;
						var incDec3 = "(+"+xml[0].getElementsByTagName('incDec')[3].childNodes[0].nodeValue+")";
						$("#defCnt3").val(defCnt3);
						$("#incDec3").val(incDec3);
						
						var defCnt4 = xml[0].getElementsByTagName('defCnt')[4].childNodes[0].nodeValue;
						var incDec4 = "(+"+xml[0].getElementsByTagName('incDec')[4].childNodes[0].nodeValue+")";
						$("#defCnt4").val(defCnt4);
						$("#incDec4").val(incDec4);
						
						var defCnt5 = xml[0].getElementsByTagName('defCnt')[5].childNodes[0].nodeValue;
						var incDec5 = "(+"+xml[0].getElementsByTagName('incDec')[5].childNodes[0].nodeValue+")";
						$("#defCnt5").val(defCnt5);
						$("#incDec5").val(incDec5);
						
						var defCnt6 = xml[0].getElementsByTagName('defCnt')[6].childNodes[0].nodeValue;
						var incDec6 = "(+"+xml[0].getElementsByTagName('incDec')[6].childNodes[0].nodeValue+")";
						$("#defCnt6").val(defCnt6);
						$("#incDec6").val(incDec6);
						
						var defCnt7 = xml[0].getElementsByTagName('defCnt')[7].childNodes[0].nodeValue;
						var incDec7 = "(+"+xml[0].getElementsByTagName('incDec')[7].childNodes[0].nodeValue+")";
						$("#defCnt7").val(defCnt7);
						$("#incDec7").val(incDec7);
						
						var defCnt8 = xml[0].getElementsByTagName('defCnt')[8].childNodes[0].nodeValue;
						var incDec8 = "(+"+xml[0].getElementsByTagName('incDec')[8].childNodes[0].nodeValue+")";
						$("#defCnt8").val(defCnt8);
						$("#incDec8").val(incDec8);
						
						var defCnt9 = xml[0].getElementsByTagName('defCnt')[9].childNodes[0].nodeValue;
						var incDec9 = "(+"+xml[0].getElementsByTagName('incDec')[9].childNodes[0].nodeValue+")";
						$("#defCnt9").val(defCnt9);
						$("#incDec9").val(incDec9);
						
						var defCnt10 = xml[0].getElementsByTagName('defCnt')[10].childNodes[0].nodeValue;
						var incDec10 = "(+"+xml[0].getElementsByTagName('incDec')[10].childNodes[0].nodeValue+")";
						$("#defCnt10").val(defCnt10);
						$("#incDec10").val(incDec10);
						
						var defCnt11 = xml[0].getElementsByTagName('defCnt')[11].childNodes[0].nodeValue;
						var incDec11 = "(+"+xml[0].getElementsByTagName('incDec')[11].childNodes[0].nodeValue+")";
						$("#defCnt11").val(defCnt11);
						$("#incDec11").val(incDec11);
						
						var defCnt12 = xml[0].getElementsByTagName('defCnt')[12].childNodes[0].nodeValue;
						var incDec12 = "(+"+xml[0].getElementsByTagName('incDec')[12].childNodes[0].nodeValue+")";
						$("#defCnt12").val(defCnt12);
						$("#incDec12").val(incDec12);
						
						var defCnt13 = xml[0].getElementsByTagName('defCnt')[13].childNodes[0].nodeValue;
						var incDec13 = "(+"+xml[0].getElementsByTagName('incDec')[13].childNodes[0].nodeValue+")";
						$("#defCnt13").val(defCnt13);
						$("#incDec13").val(incDec13);
						
						var defCnt14 = xml[0].getElementsByTagName('defCnt')[14].childNodes[0].nodeValue;
						var incDec14 = "(+"+xml[0].getElementsByTagName('incDec')[14].childNodes[0].nodeValue+")";
						$("#defCnt14").val(defCnt14);
						$("#incDec14").val(incDec14);
						
						var defCnt15 = xml[0].getElementsByTagName('defCnt')[15].childNodes[0].nodeValue;
						var incDec15 = "(+"+xml[0].getElementsByTagName('incDec')[15].childNodes[0].nodeValue+")";
						$("#defCnt15").val(defCnt15);
						$("#incDec15").val(incDec15);
						
						var defCnt16 = xml[0].getElementsByTagName('defCnt')[16].childNodes[0].nodeValue;
						var incDec16 = "(+"+xml[0].getElementsByTagName('incDec')[16].childNodes[0].nodeValue+")";
						$("#defCnt16").val(defCnt16);
						$("#incDec16").val(incDec16);
						
						var defCnt17 = xml[0].getElementsByTagName('defCnt')[17].childNodes[0].nodeValue;
						var incDec17 = "(+"+xml[0].getElementsByTagName('incDec')[17].childNodes[0].nodeValue+")";
						$("#defCnt17").val(defCnt17);
						$("#incDec17").val(incDec17);
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						//var gubun = xml[0].getElementsByTagName('item')[0].getElementsByTagName('gubun')[0].childNodes[0].nodeValue;
 						//var defCnt = xml[0].getElementsByTagName('item')[0].getElementsByTagName('defCnt')[0].childNodes[0].nodeValue;
 						//var incDec = xml[0].getElementsByTagName('item')[0].getElementsByTagName('incDec')[0].childNodes[0].nodeValue;
 
						//var gubun2 = xml[1].getElementsByTagName('item')[0].getElementsByTagName('gubun')[0].childNodes[0].nodeValue;
 						//var defCnt2 = xml[1].getElementsByTagName('item')[0].getElementsByTagName('defCnt')[0].childNodes[0].nodeValue;
 						//var incDec2 = xml[1].getElementsByTagName('item')[0].getElementsByTagName('incDec')[0].childNodes[0].nodeValue;
 
						//alert(gubun);
						//alert(defCnt);
						//alert(incDec);
						//alert(gubun2);
						//alert(defCnt2);
						//alert(incDec2);
						 
						


						
						/*
						$("#gubun").val(obj.gubun);
						//console.log(obj);
						$("#defCnt").val(obj.defCnt);
						$("#incDec").val(obj.incDec);
						*/

					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			
});
							
							
							
							
							
							
							
							
							
							
						}

					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			
});
			