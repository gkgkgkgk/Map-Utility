$(document)
    .ready(
        function() {
            // initialize hilight
            $(".map").maphilight({
                strokeColor: "4CAF50",
                strokeWidth: 2,
                fillColor: '8BC34A',
                fillOpacity: 0.25,
                alwaysOn: true
            });

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
            $('#hourUp').click(
                function() {
                    if ($('#hourText').text() < 12) {
                        $('#hourText')
                            .text(
                                parseInt($('#hourText')
                                    .text()) + 1);
                    } else {
                        $('#hourText').text(1);
                    }
                });
            $('#hourDown').click(
                function() {
                    if ($('#hourText').text() > 1) {
                        $('#hourText')
                            .text(
                                parseInt($('#hourText')
                                    .text()) - 1);
                    } else {
                        $('#hourText').text(12);
                    }
                });
            $('#minuteUp')
                .click(
                    function() {
                        if ($('#minute h4').text() < 59) {
                            if (parseInt($('#minute h4').text()) - 1 < 8) {
                                $('#minute h4')
                                    .text(
                                        "0" +
                                        (parseInt($(
                                                '#minute h4')
                                            .text()) + 1));
                            } else {
                                $('#minute h4')
                                    .text(
                                        parseInt($(
                                                '#minute h4')
                                            .text()) + 1);
                            }
                        } else {
                            $('#minute h4').text(1);
                        }
                    });
            $('#minuteDown')
                .click(
                    function() {
                        if ($('#minute h4').text() > 0) {
                            if (parseInt($('#minute h4').text()) - 1 < 10) {
                                $('#minute h4')
                                    .text(
                                        "0" +
                                        (parseInt($(
                                                '#minute h4')
                                            .text()) - 1));
                            } else {
                                $('#minute h4')
                                    .text(
                                        parseInt($(
                                                '#minute h4')
                                            .text()) - 1);
                            }
                        } else {
                            $('#minute h4').text(59);
                        }
                    });
            $('#slider')
                .on(
                    "change mousemove",
                    function() {

                        if ($(this).val() < 60) {
                            $('#hourText').text(12);
                            $('#minute h4').text(
                                parseInt($(this).val()));
                        } else if ($(this).val() < 780) {
                            $('#hourText').text(
                                parseInt(Math.floor($(this)
                                    .val() / 60)));
                            $('#minute h4')
                                .text(
                                    parseInt($(this)
                                        .val() -
                                        parseInt(Math
                                            .floor($(
                                                    this)
                                                .val() / 60) * 60)));
                        } else {
                            $('#hourText').text(
                                parseInt(Math.floor($(this)
                                    .val() / 60) - 12));
                            $('#minute h4')
                                .text(
                                    parseInt($(this)
                                        .val() -
                                        parseInt(Math
                                            .floor($(
                                                    this)
                                                .val() / 60) * 60)));
                        }

                    });

        });