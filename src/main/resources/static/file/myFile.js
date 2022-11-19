$(document).ready(function () {
    event.preventDefault();

    window.onload = function () {
        event.preventDefault();
        list()
    }

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });


    $('#search').click(function () {
        list()
    });


})

function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {

        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + data[i].id + "</th>\n" +
            "        <td>" + data[i].name + "</td>\n" +
            "        <td>" + data[i].info + "</td>\n" +
            "        <td>" + data[i].date + "</td>\n" +
            "        <td>" + toString(data[i].open) + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='file/download/" + data[i].id + "'\" class=\"btn btn-success\">Скачать</button>  </td>\n" +
            "        <td>  <button type=\"button\" onclick='Delete(" + data[i].id + ")'  class=\"btn btn-success\">Удалить</button>  </td>\n" +
            "    </tr>");

    }

}

function toString(tmp) {
    if (tmp === true) return "Разрешен";
    return "Запрещен";
}

function Delete(number) {
    $.ajax({
        statusCode: {
            204: function (xhr) {
               list()
            }
        },
        type: "DELETE",
        dataType: 'JSON',
        url: '/file/delete/' + number,
    }).done(function () {
        list()
    })

}

function list() {
    event.preventDefault();
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/file/search/account/' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}