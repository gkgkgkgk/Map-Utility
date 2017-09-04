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

$(document).ready(function() {
	configureJson();
	initSelectors();
	initMap();
	initControl();
	// slider();
	areaSelector();
	listManager();
	
	for(var i = 0; i < jsonObjects.length; i++){
		if(jsonObjects[i].className == "525"){
			console.log("ok");
			for(var i = 0; i < jsonObjects.periods.length; i++){
				if(jsonObjects.periods[i].date == "Sep 5, 2017 12:00:00 AM"){
					if(){
						
					}
				}
			}
		}
	}
	
	
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

/*
 * function slider() { $('#slider') .on( "change mousemove", function() { if
 * ($(this).val() < 60) { $('#hour h3').text(12); $('#minute
 * h3').text(parseInt($(this).val())); } else if ($(this).val() < 780) {
 * $('#hour h3').text( parseInt(Math.floor($(this).val() / 60))); $('#minute
 * h3') .text( parseInt($(this).val() - parseInt(Math.floor($( this).val() / 60) *
 * 60))); if ($(this).val() < 720) { $('#period h3').text('AM'); } else {
 * $('#period h3').text('PM'); }
 *  } else { $('#hourText') .text( parseInt(Math .floor($(this).val() / 60) -
 * 12)); $('#minute h3') .text( parseInt($(this).val() - parseInt(Math.floor($(
 * this).val() / 60) * 60))); $('#period h3').text('PM');
 *  } }); }
 */

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

function listManager(){
	$("li").on("click", function() {
		$("li").removeClass("active");
		$(this).addClass("active");
	});
}

function initSelectors(){
	$('#datePicker').datepicker({
		autoclose : true,
		todayHighlight : true,
		orientation : 'bottom',
		container:'#dateContainer'
	});
	$('#datePicker').datepicker('update', mo + "-" + da + "-" + yr);
	$('.clockpicker').clockpicker({
	default : 'now',
	autoclose : true,
	align : 'top'
	});
	$(".clockpicker input").css("value", h+":"+m);

}

function configureJson(){
	$.ajax({
	     async: false,
	     type: 'GET',
	     url: 'http://localhost:8080/Libraries/json.txt',
	     success: function(data) {
	    	 console.log(data);
	 	    json = data;
	 	   console.log("loaded json");
	 		jsonObjects.push((JSON.parse(json)));
	 		jsonObjects = jsonObjects[0];
	 		console.log(json);
	 		console.log(jsonObjects);
	 	    }
	});
	
	
}
