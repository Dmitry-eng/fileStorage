$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        event.preventDefault();
        $.ajax({
            type: "PUT",
            dataType: 'JSON',
            url: '/list/search/myFile',
        }).done(function (data) {
            Search(data);
        })
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
            "        <td>" + data[i].data + "</td>\n" +
            "        <td>" + toString(data[i].open) + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/download/" + data[i].id + "'\" class=\"btn btn-success\">Скачать</button>  </td>\n" +
            "        <td>  <button type=\"button\" onclick='Delete(" + data[i].id + ")' class=\"btn btn-success\">Удалить</button>  </td>\n" +
            "    </tr>");

    }

}

function toString(tmp) {
    if (tmp === true) return "Разрешен";
    return "Запрещен";
}

function Delete(number) {
    $.ajax({
        type: "DELETE",
        dataType: 'JSON',
        url: '/list/delete/' + number,
    }).done(function (data) {
        list()
    })

}

function list() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/list/search/myFile/' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}