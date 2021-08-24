$(document).ready(function(){
    $('.datepicker').datepicker({
        format: "yyyy/mm/dd",
        clearBtn: "false",
        language: "es",
        autoclose: true,
        orientation: "top",
        defaultViewDate: {
            month: '04',
            day:'15',
            year: '1990'
        },
    });

    $('.datepickerdown').datepicker({
        format: "yyyy/mm/dd",
        todayBtn: "linked",
        language: "es",
        autoclose: true,
        orientation: "bottom",
    });
});