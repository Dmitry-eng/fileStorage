$(document).ready(function () {

    $("#buttonLoad").click(function (event) {
        $.ajax({
            statusCode: {
                201: function (xhr) {
                    alert("Файл успешно загружен")
                }
            },
            url: "/file/load",
            type: 'POST',
            enctype: 'multipart/form-data',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
        })
            .done(function(data) {
                alert('done');
            });

    });
});