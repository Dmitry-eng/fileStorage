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
            "        <td>" + data[i].login + "</td>\n" +
            "        <td>" + data[i].email + "</td>\n" +
            "        <td>  <button type=\"button\" onclick='deleteUser(" + data[i].id + ")' class=\"btn btn-success\">Удалить</button>  </td>\n" +
            "    </tr>");
    }
}

function list() {
    // event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/admin/accounts/' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}

function deleteUser(id) {
    $.ajax({
        type: "DELETE",
        dataType: 'JSON',
        url: '/admin/account/' + id,
    }).done(function (data) {
        list()
    })
}

