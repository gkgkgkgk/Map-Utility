$(document).ready(function() {
	
	// initialize hilight
	$(".map").maphilight({
		strokeColor : "4CAF50",
		strokeWidth : 2,
		fillColor : '8BC34A',
		fillOpacity : 0.25,
		alwaysOn : true
	});
	
	$('area').click(function(e) {
		e.preventDefault();
		console.log("click");
		var data = $(this).data('maphilight') || {};
		data.fillColor = 'F44336';
		data.strokeColor = "FF5722";
		$(this).data('maphilight', data).trigger('alwaysOn.maphilight');
	});
	
	$("area").each(function(index) {

	});
});