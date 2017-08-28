var currentTime = 0;

$(document)
		.ready(
				function() {
					// initialize hilight
					$(".map").maphilight({
						strokeColor : "4CAF50",
						strokeWidth : 2,
						fillColor : '8BC34A',
						fillOpacity : 0.25,
						alwaysOn : true
					});

					var d = new Date();
					var m = d.getMinutes();
					var h = d.getHours();
					
					$("#slider").val(m + (h * 60));
					$("#hour h4").text((h > 12) ? h - 12 : h);
					$("#minute h4").text(m);
					$("#period").text((h >= 12) ? "PM" : "AM");
					
					$('area').click(
							function(e) {
								e.preventDefault();
								console.log("click");
								var data = $(this).data('maphilight') || {};
								data.fillColor = 'F44336';
								data.strokeColor = "FF5722";
								$(this).data('maphilight', data).trigger(
										'alwaysOn.maphilight');
							});

					$("area").each(function(index) {

					});

					/* time settings */
					$('#hourUp')
							.click(
									function() {
										if ($('#hourText').text() < 12) {
											$('#hourText').text(
													parseInt($('#hourText')
															.text()) + 1);
										} else {
											$('#hourText').text(1);
										}
										currentTime = (parseInt($('#hourText')
												.text()) * 60)
												+ parseInt($('#minute h4')
														.text());
										$('#slider').val(currentTime);

									});
					/*
					 * $('#hourDown').click( function() { if
					 * ($('#hourText').text() > 1) { $('#hourText') .text(
					 * parseInt($('#hourText') .text()) - 1); } else {
					 * $('#hourText').text(12); } currentTime =
					 * (parseInt($('#hourText').text()) * 60) +
					 * parseInt($('#minute h4').text());
					 * $('#slider').val(currentTime);
					 * 
					 * }); $('#minuteUp') .click( function() { if ($('#minute
					 * h4').text() < 59) { if (parseInt($('#minute h4').text()) -
					 * 1 < 8) { $('#minute h4') .text( "0" + (parseInt($(
					 * '#minute h4') .text()) + 1)); } else { $('#minute h4')
					 * .text( parseInt($( '#minute h4') .text()) + 1); } } else {
					 * $('#minute h4').text(1); } currentTime =
					 * (parseInt($('#hourText').text()) * 60) +
					 * parseInt($('#minute h4').text());
					 * $('#slider').val(currentTime);
					 * 
					 * }); $('#minuteDown') .click( function() { if ($('#minute
					 * h4').text() > 0) { if (parseInt($('#minute h4').text()) -
					 * 1 < 10) { $('#minute h4') .text( "0" + (parseInt($(
					 * '#minute h4') .text()) - 1)); } else { $('#minute h4')
					 * .text( parseInt($( '#minute h4') .text()) - 1); } } else {
					 * $('#minute h4').text(59); } currentTime =
					 * (parseInt($('#hourText').text()) * 60) +
					 * parseInt($('#minute h4').text());
					 * $('#slider').val(currentTime); });
					 * 
					 * $('#periodDown, #periodUp').click(function(){
					 * if($('#period h4').text().indexOf("AM") >= 0){ $('#period
					 * h4').text("PM"); console.log($('#slider').val());
					 * $('#slider').val($('#slider').val() + (12 * 15));
					 * console.log($('#slider').val()); } else{ $('#period
					 * h4').text("AM"); $('#slider').val($('#slider').val() -
					 * (12 * 15));
					 *  } });
					 */
					$('#slider')
							.on(
									"change mousemove",
									function() {
										if ($(this).val() < 60) {
											$('#hour h4').text(12);
											$('#minute h4').text(
													parseInt($(this).val()));
										} else if ($(this).val() < 780) {
											$('#hour h4').text(
													parseInt(Math.floor($(this)
															.val() / 60)));
											$('#minute h4')
													.text(
															parseInt($(this)
																	.val()
																	- parseInt(Math
																			.floor($(
																					this)
																					.val() / 60) * 60)));
											if ($(this).val() < 720) {
												$('#period h4').text('AM');
											} else {
												$('#period h4').text('PM');
											}

										} else {
											$('#hourText').text(
													parseInt(Math.floor($(this)
															.val() / 60) - 12));
											$('#minute h4')
													.text(
															parseInt($(this)
																	.val()
																	- parseInt(Math
																			.floor($(
																					this)
																					.val() / 60) * 60)));
											$('#period h4').text('PM');

										}
									});

				});