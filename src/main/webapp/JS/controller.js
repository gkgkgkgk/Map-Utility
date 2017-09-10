var currentTime = 0;

var selectedOuter = "5B965B";
var selectedInner = "98FB98";
var freeOuter = "000080";
var freeInner = "7F7FBF";
var takenOuter = "CC8400";
var takenInner = "FFC04C";

var d = new Date();
var mo = d.getMonth() + 1;
var da = d.getDate();
var yr = d.getUTCFullYear();
var m = d.getMinutes();
var h = d.getHours();

var json = "";
var jsonObjects = [];

var bigString = "";



$(document).ready(function() {

	$(document).ajaxStart(function() {
		$("#fade").css("display", "block"); console.log("ajax started"); 
		}).ajaxStop(function() {
			$("#fade").css("display", "none"); console.log("ajax ended");
		});
	
	initTime();
	initRooms(mo + "-" + da + "-" + yr);
	initDateSelector();
	initMap();
	initControl();
	areaSelector();

});

function initMap() {
	$(".map").maphilight({
		strokeColor : freeOuter,
		strokeWidth : 2,
		fillColor : freeInner,
		fillOpacity : 0.25,
		alwaysOn : true
	});
}

function initControl() {
	$("#slider").val(m + (h * 60));
	$("#hour h4").text((h > 12) ? h - 12 : h);
	$("#minute h4").text(m);
	$("#period").text((h >= 12) ? "PM" : "AM");
}

function areaSelector() {
	$('area')
			.click(
					function(e) {
						e.preventDefault();
						var data = $(".selectedRoom").data('maphilight') || {};
						data.strokeColor = freeOuter;
						$(".selectedRoom").data('maphilight', data).trigger(
								'alwaysOn.maphilight');
						$(".selectedRoom").removeClass("selectedRoom");
						$(this).addClass("selectedRoom");
						data = $(this).data('maphilight') || {};
						data.strokeColor = selectedOuter;
						$(this).data('maphilight', data).trigger(
								'alwaysOn.maphilight');
					});
}

function initDateSelector() {
	$('#datePicker').datepicker({
		autoclose : true,
		todayHighlight : true,
		orientation : 'bottom',
		container : '#dateContainer'
	});
	$('#datePicker').datepicker('update', mo + "-" + da + "-" + yr);
	$('#datePicker').datepicker().on('changeDate', function(e) {
		console.log("changed");
		var selectDate = $('#datePicker').datepicker('getDate');
		var mon = selectDate.getMonth() + 1;
		var day = selectDate.getDate();
		var yer = selectDate.getUTCFullYear();
		console.log(mon + "-" + day + "-" + yer);
		initRooms(mon + "-" + day + "-" + yer);
	});

}

function initTime() {
	var date = new Date();

	$('#timepicker').timepicker();
	$('#timepicker').timepicker(
			'setTime',
			new Date(date.getFullYear(), date.getMonth(), date.getDate(), date
					.getHours(), date.getMinutes()));

	$('#slider').on(
			"change mousemove",
			function() {

				var currentTime = $('#slider').val();
				var hour = currentTime % 60;
				var minute = currentTime - (60 * (currentTime % 60));
				$('#timepicker').timepicker(
						'setTime',
						new Date(date.getFullYear(), date.getMonth(), date
								.getDate(), hour, minute));

				$("area").each(
						function(index) {
							var shortened = bigString.substring(bigString
									.indexOf("room" + $(this).attr('id'))
									+ ("room" + $(this).attr('id')).length,
									bigString.length);
							var roomString = shortened.substring(0, shortened
									.indexOf("r"));
							var date = $('#timepicker').timepicker('getTime');

							if (roomString
									.charAt(((date.getHours() * 60) + (date
											.getMinutes()))) == "0") {
								var data = $(this).data('maphilight') || {};
								data.strokeColor = freeOuter;

								data = $(this).data('maphilight') || {};
								data.fillColor = freeInner;
								$(this).data('maphilight', data).trigger(
										'alwaysOn.maphilight');
							} else {
								var data = $(this).data('maphilight') || {};
								data.strokeColor = freeOuter;
								data = $(this).data('maphilight') || {};
								data.fillColor = takenInner;
								$(this).data('maphilight', data).trigger(
										'alwaysOn.maphilight');
							}
						});
			});
}

function initRooms(dayP) {
	console.log(dayP);
	$.ajax({
		async : true,
		type : 'GET',
		url : 'http://localhost:8080/day=' + dayP,
		success : function(data) {
			// 525 = data.split(' ').map(Number);
			console.log(data);
			bigString = data;
		}
	});
}
