$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    $("#buttonLoad").click(function (event) {
        $.ajax({
            statusCode: {
                201: function (xhr) {
                    alert("Файл успешно загружен")
                    $('#loadFile')[0].reset()
                }
            },
            url: "/file/load",
            type: 'POST',
            enctype: 'multipart/form-data',
            data: new FormData($('#loadFile')[0]),
            cache: false,
            contentType: false,
            processData: false,
        })
            .done(function () {
            });

    });
});