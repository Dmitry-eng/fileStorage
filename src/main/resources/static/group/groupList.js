$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        searchInit()
    }

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $('#search').click(function () {
        searchInit();
    });


})
function searchInit() {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '/group/search/' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}
function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {

        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + data[i].id + "</th>\n" +
            "        <td>" + data[i].name + "</td>\n" +
            "        <td>" + data[i].account.login + "</td>\n" +
            "        <td>" + show(data[i].id, data[i].status) + "</td>\n" +
            "    </tr>");

    }
}

function show(id, status) {
    if (status === 'IN_GROUP') {
        return " <td> <button type=\"button\" onclick=\"window.location.href='/show/" + id + "'\" class=\"btn btn-success\">Перейти</button>  </td>"
    }
    if (status === 'NOT_IN_GROUP') {
        return " <td> <button type=\"button\" onclick='invite(" + id + ")' class=\"btn btn-success\">Отправить запрос</button>  </td>"
    }
    if(status === 'REQUEST_SENT') {
        {
            return " <td> <button type=\"button\"  class=\"btn btn-success\">Запрос был отправлен</button>  </td>"
        }
    }
}

function invite(id) {
    event.preventDefault();
    $.ajax({        statusCode: {
            200: function (xhr) {
                searchInit()
            }
        },
        type: "POST",
        dataType: 'JSON',
        url: '/group/request/' + id,
    }).done(function (data) {
    })
} 