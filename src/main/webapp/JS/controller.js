var currentTime = 0;

var selectedOuter = "5B965B";
var selectedInner = "98FB98";
var freeOuter = "000080";
var freeInner = "7F7FBF";
var takenOuter = "CC8400";
var takenInner = "FFC04C";

var d = new Date();
var m = d.getMinutes();
var h = d.getHours();

$(document).ready(function() {
	initMap();
	initControl();
	slider();
	areaSelector();
	listManager();

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

function slider() {
	$('#slider')
			.on(
					"change mousemove",
					function() {
						if ($(this).val() < 60) {
							$('#hour h3').text(12);
							$('#minute h3').text(parseInt($(this).val()));
						} else if ($(this).val() < 780) {
							$('#hour h3').text(
									parseInt(Math.floor($(this).val() / 60)));
							$('#minute h3')
									.text(
											parseInt($(this).val()
													- parseInt(Math.floor($(
															this).val() / 60) * 60)));
							if ($(this).val() < 720) {
								$('#period h3').text('AM');
							} else {
								$('#period h3').text('PM');
							}

						} else {
							$('#hourText')
									.text(
											parseInt(Math
													.floor($(this).val() / 60) - 12));
							$('#minute h3')
									.text(
											parseInt($(this).val()
													- parseInt(Math.floor($(
															this).val() / 60) * 60)));
							$('#period h3').text('PM');

						}
					});
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

function listManager(){
	$("li").on("click", function() {
		$("li").removeClass("active");
		$(this).addClass("active");
	});
}
