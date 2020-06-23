$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        // event.preventDefault();
        // $.ajax({
        //     type: "PUT",
        //     dataType: 'JSON',
        //     url: '/request/list/',
        // }).done(function (data) {
        //     Search(data);
        // })
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
     list();
    });


})

function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {

        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + data[i].id + "</th>\n" +
            "        <td>" + data[i].group.name + "</td>\n" +
            "        <td>" + data[i].user.name + "</td>\n" +
            "        <td>  <button type=\"button\" onclick='accept(" + data[i].id + ")' class=\"btn btn-success\">Принять</button>  </td>\n" +
            "        <td>  <button type=\"button\" onclick='reject(" + data[i].id + ")' class=\"btn btn-success\">Отклонить</button>  </td>\n" +
            "    </tr>");
    }
}
function accept(id) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        dataType: 'JSON',
        url: '/request/accept/'+ id,
    }).done(function (data) {
       list()
    })
}

function reject(id) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        dataType: 'JSON',
        url: '/request/reject/'+ id,
    }).done(function (data) {
        list()
    })
}
function list() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/request/list/'+ $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}