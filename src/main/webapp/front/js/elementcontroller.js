jQuery(document).ready(function ($) {

    function move(e, obj) {
        var progress = e.pageX - obj.offset().left;
        var rating = progress * 5 / $('.stars').width();
        $('#mark_value').attr("value", rating.toFixed(1));
        obj.next().width(progress);
    }

    $('#rating .stars').click(function (e) {
        $(this).toggleClass('fixed');
        move(e, $(this));
    });


    $(document).ready(function () {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var hourse = today.getHours();
        var yyyy = today.getFullYear();
        var minutes = today.getMinutes();
        if (mm < 10) {
            mm = '0' + mm
        }
        if (hourse < 10) {
            hourse = 10;
        }
        if (hourse > 18) {
            dd += 1;
            hourse = 10;
            minutes = 0;
        }
        if (minutes < 10) {
            minutes = '0' + minutes;
        }
        if (dd < 10) {
            dd = '0' + dd
        }
        today = yyyy + '-' + mm + '-' + dd + 'T' + (hourse + 1) + ':' + minutes;
        $("#dateTime").attr("min", today);
        $("#dateTime").attr("value", today);
    });

    $(document).ready(function () {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 2;
        var hourse = today.getHours();
        var yyyy = today.getFullYear();
        var minutes = today.getMinutes();
        if (mm < 10) {
            mm = '0' + mm
        }
        if (hourse < 10) {
            hourse = 10;
        }
        if (hourse > 18) {
            dd += 1;
            hourse = 10;
            minutes = 0;
        }
        if (minutes < 10) {
            minutes = '0' + minutes;
        }
        if (dd < 10) {
            dd = '0' + dd
        }
        today = yyyy + '-' + mm + '-' + dd + 'T' + (hourse + 1) + ':' + minutes;
        $("#dateTime").attr("max", today);
    });

    $('input#dateTime').on('change', function (e) {
        var hourse = $('input#dateTime').val();
        var name_enReg = new RegExp('^[0-9]{4}-[0-9]{2}-[0-9]{2}T1[0-9]:[0-9]{2}$');
        if (name_enReg.test(hourse) == false) {
            document.payment.elements['payment_button'].disabled = true;

        } else {
            document.payment.elements['payment_button'].disabled = false;
        }
    });
});