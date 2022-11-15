$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
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
            "        <td>" +data[i].account.login + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/show/" + data[i].id + "'\" class=\"btn btn-success\">Перейти</button>  </td>\n" +
          //  "        <td>  <button type=\"button\" onclick='leave("+data[i].id+")' class=\"btn btn-danger\">Покинуть группу</button>  </td>\n" +
            buttonLeave(data[i].user.id, data[i].id ) +
            "    </tr>");

    }
}
function leave(id) {
    event.preventDefault();
    $.ajax({
        type: "Delete",
        dataType: 'JSON',
        url: '/show/leave/'+ id,
    }).done(function (data) {
        list()
    })
}
function list() {
    event.preventDefault();
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/group/search/account/'+ $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}
function buttonLeave(user, group) {
    if($("meta[name='idUser']").attr("content")==user) {
        return " <td>  <button type=\"button\" onclick='deleteGroup("+group+")' class=\"btn btn-danger\">Удалить группу</button>  </td>\n"
    }
    else {
            return " <td>  <button type=\"button\" onclick='leave("+group+")' class=\"btn btn-danger\">Покинуть группу</button>  </td>\n"
    }
}
function deleteGroup(group) {
    event.preventDefault();
    $.ajax({
        type: "DELETE",
        dataType: 'JSON',
        url: '/group/delete/'+ group,
    }).done(function (data) {
        list()
    })
}