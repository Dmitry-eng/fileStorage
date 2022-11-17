$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    $("#buttonCreateGroup").click(function (event) {
        $.ajax({
            statusCode: {
                201: function (xhr) {
                    alert("Группа успешно создана")
                    $('#createGroup')[0].reset()
                },
                400: function (xhr) {
                    if ("NAME_EXISTS" === JSON.parse(xhr.responseText).code) {
                        alert("Группа с именем " + $('#name').val()  + " уже существует")
                    }
                    $('#createGroup')[0].reset()
                }
            },
            url: "/group/add",
            type: 'POST',
            // enctype: 'multipart/form-data',
            data: new FormData($('#createGroup')[0]),
            cache: false,
            contentType: false,
            processData: false,
        })
            .done(function () {
            });

    });
});