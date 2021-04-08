function callWeather(city) {
	
	var res = city;
	
	var api_key = "6a0d9f98d475b0998a05cf0b47a8569c";
    
    var latitude = "null";
    var longitude = "null";

    if (res == "Seoul") {
        latitude = "37.583328";
        longitude = "127.0";                
    } else if (res == "Incheon") {
        longitude = "126.416107";
        latitude = "37.450001";
    } else if (res == "Chuncheon") {
        longitude = "127.734169";
        latitude = "37.874722";
    } else if (res == "Chungju") {
        longitude = "127.93222";
        latitude = "36.970558";
    } else if (res == "Gangneung") {
        longitude = "128.896103";
        latitude = "37.755562";
    } else if (res == "Tonghae") {
        longitude = "129.106934";
        latitude = "37.543892";
    } else if (res == "Andong") {
        longitude = "128.725006";
        latitude = "36.565559";
    } else if (res == "Suwon") {
        longitude = "127.008888";
        latitude = "37.291111";
    } else if (res == "Daejeon") {
        longitude = "127.416672";
        latitude = "36.333328";
    } else if (res == "Jeonju") {
        longitude = "127.148888";
        latitude = "35.821941";
    } else if (res == "Daegu") {
        longitude = "128.550003";
        latitude = "35.799999";
    } else if (res == "Ulsan") {
        longitude = "129.266663";
        latitude = "35.566669";
    } else if (res == "Gwangju") {
        longitude = "126.916672";
        latitude = "35.166672";
    } else if (res == "Mokpo") {
        longitude = "126.388611";
        latitude = "34.79361";
    } else if (res == "Reisui") {
        longitude = "127.737778";
        latitude = "34.744171";
    } else if (res == "Busan") {
        longitude = "129.050003";
        latitude = "35.133331";
    } else if (res == "Jeju-do") {
        longitude = "126.5";
        latitude = "33.416672";
    }
	
    $.ajax({
        type: "POST",
        url: "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" +
            longitude + "&appid=" + api_key + "&units=metric",
        dataType: "JSON",
        success: function (result) {

                var dayarr = new Array();
                var temparr = new Array();
                var feelarr = new Array();
                var humidarr = new Array();
                var pressarr = new Array();
                var cloudarr = new Array();
                var windarr = new Array();
                var count = 0;

            for (var i = 0; i < 48; i+=3) {
                var ctime = result.hourly[i].dt;
                var ctemp = result.hourly[i].temp;
                var cfeel = result.hourly[i].feels_like;
                var chumid = result.hourly[i].humidity;
                var cpress = result.hourly[i].pressure;
                var ccloud = result.hourly[i].clouds;
                var cwind = result.hourly[i].wind_speed;
                
                function Unix_timestamp(t) {
                    var date = new Date(t * 1000);
                    var day = "0" +
                        date.getDate();
                    var hour = "0" + date.getHours();
                    return day.substr(-2) + '일 ' + hour.substr(-2) + '시';
                }
                var currentTime = Unix_timestamp(ctime);

                dayarr[count] = currentTime;
                temparr[count] = ctemp;
                feelarr[count] = cfeel;
                humidarr[count] = chumid;
                pressarr[count] = cpress;
                cloudarr[count] = ccloud;
                windarr[count] = cwind;

                count++;

            }

                function current_timestamp(t) {
                    var date = new Date(t * 1000);
                    var year = date.getFullYear();
                    var month = "0" + (date.getUTCMonth() + 1);
                    var day = "0" + date.getDate();
                    var hour = "0" + date.getHours();
                    var minute = "0" + date.getMinutes();
                    var second = "0" + date.getSeconds();
                        return year + '/' + month.substr(-2) + '/' + day.substr(-2) + ' ' + hour.substr(-2) + ':' +
                         minute.substr(-2) + ':' + second.substr(-2);
                }

                var today = current_timestamp(result.hourly[0].dt);
                var todaytemp = result.hourly[0].temp;
                var todayfeel = result.hourly[0].feels_like;
                var todayhumid = result.hourly[0].humidity;
                var todaypress = result.hourly[0].pressure;
                var todaycloud = result.hourly[0].clouds;
                var todaywind = result.hourly[0].wind_speed;
                console.log(today);
                
                $("#current_temp").children().remove();
                $("#current_weather").find("td").remove();
                
                var query = document.getElementById('current_temp');
                var small = document.createElement('small');
                small.innerHTML = "현재시각 : " + today;
                query.appendChild(small);
                
                var query = document.getElementById('current_weather');
                var tr = document.createElement('tr');
                var td1 = document.createElement('td');
                td1.innerHTML = todaytemp + " ℃";
                tr.appendChild(td1);
                
                var td2 = document.createElement('td');
                td2.innerHTML = todayfeel + " ℃";
                tr.appendChild(td2);
                
                var td3 = document.createElement('td');
                td3.innerHTML = todayhumid + " %";
                tr.appendChild(td3);
                
                var td4 = document.createElement('td');
                td4.innerHTML = todaypress + " hPa";
                tr.appendChild(td4);
                
                var td5 = document.createElement('td');
                td5.innerHTML = todaycloud + " %";
                tr.appendChild(td5);
                
                var td6 = document.createElement('td');
                td6.innerHTML = todaywind + " m/s";
                tr.appendChild(td6);

                query.appendChild(tr);
                
            google.charts.load('current', {packages: ['corechart']});
            google.charts.setOnLoadCallback(drawTempChart);
            google.charts.setOnLoadCallback(drawHumidChart);
            google.charts.setOnLoadCallback(drawPressChart);
            google.charts.setOnLoadCallback(drawCloudChart);
            google.charts.setOnLoadCallback(drawWindChart);

            function drawTempChart() {
                var data = new google.visualization.DataTable();

                data.addColumn('string', '날짜/시간');
                data.addColumn('number', '온도');
                data.addColumn('number', '체감온도');

                var row = [];

                for (var i = 0; i < dayarr.length; i++) {
                    var date = dayarr[i];
                    var temp = temparr[i];
                    var feel = feelarr[i];
                    row = [date, temp, feel];
                    data.addRow(row);

                }

                var options = {
                curveType: 'function',
                is3D: true,
                hAxis: {
                 title: '온도/체감온도 정보',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#d97053', '#eed443'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                
                 };

                var chart = new google.visualization.LineChart(document.getElementById('tempchart'));
                chart.draw(data, options);

            }       
            function drawHumidChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', '날짜/시간');
                data.addColumn('number', '습도');
                var row = [];

                for (var i = 0; i < dayarr.length; i++) {
                    var date = dayarr[i];
                    var humid = humidarr[i];
                    row = [date, humid];
                    data.addRow(row);

                }

                var options = {
                curveType: 'function',
                hAxis: {
                 title: '습도정보',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#091994'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                 };

                 var chart = new google.visualization.LineChart(document.getElementById('humidchart'));
                chart.draw(data, options);
                
            }
            function drawPressChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', '날짜/시간');
                data.addColumn('number', '기압');
                var row = [];

                for (var i = 0; i < dayarr.length; i++) {
                    var date = dayarr[i];
                    var press = pressarr[i];
                    row = [date, press];
                    data.addRow(row);

                }

                var options = {
                curveType: 'function',
                hAxis: {
                 title: '기압정보',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#31a5d7'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                 };

                var chart = new google.visualization.LineChart(document.getElementById('presschart'));
                chart.draw(data, options);
            }
            function drawCloudChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', '날짜/시간');
                data.addColumn('number', '구름');
                var row = [];

                for (var i = 0; i < dayarr.length; i++) {
                    var date = dayarr[i];
                    var cloud = cloudarr[i];
                    row = [date, cloud];
                    data.addRow(row);

                }

                var options = {
                curveType: 'function',
                hAxis: {
                 title: '구름정보',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#acfbf8'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                 };

                var chart = new google.visualization.LineChart(document.getElementById('cloudchart'));
                chart.draw(data, options);
            }
            function drawWindChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', '날짜/시간');
                data.addColumn('number', '풍속');
                var row = [];

                for (var i = 0; i < dayarr.length; i++) {
                    var date = dayarr[i];
                    var wind = windarr[i];
                    row = [date,wind];
                    data.addRow(row);

                }

                var options = {
                curveType: 'function',
                hAxis: {
                 title: '풍속정보',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#dbe0e0'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                 };

                var chart = new google.visualization.LineChart(document.getElementById('windchart'));
                chart.draw(data, options);
            }

        },
        error: function () {
            alert("정보를 불러오는데 실패하였습니다.");
        }
    })
    
}
