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
            getButtonRemoveAccountGroup(data[i].id) +
            "    </tr>");
    }

}

function getButtonRemoveAccountGroup(id) {
    if ($("meta[name='groupLead']").attr("content") === "true") {
        return " <td>  <button type=\"button\" onclick='remove(" + id + ")' class=\"btn btn-success\">Удалить учатника</button>  </td>\n"
    } else
        return "<td> </td>"
}


function remove(id) {
    $.ajax({
        type: "DELETE",
        dataType: 'JSON',
        url: '/show/deleteAccountGroup/' + $("meta[name='groupId']").attr("content") + '/' + id,
    }).done(function (data) {
        list()
    })
}

function list() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/show/AccountGroup/' + $("meta[name='groupId']").attr("content") + '/' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}