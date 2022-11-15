$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        event.preventDefault();
        $.ajax({
            type: "GET",
            dataType: 'JSON',
            url: '/group/search/',
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
        event.preventDefault();
        $.ajax({
            type: 'GET',
            dataType: 'JSON',
            url: '/group/search/'+ $("#list").val(),
        }).done(function (data) {
            Search(data);
        })
    });


})

function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {

        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + data[i].id + "</th>\n" +
            "        <td>" + data[i].name + "</td>\n" +
            "        <td>" +data[i].account.login + "</td>\n" +
            "        <td>" +show(data[i].id, data[i].access) + "</td>\n" +
            "    </tr>");

    }
}
function show(id, access){
    if(access==true){
        return " <td> <button type=\"button\" onclick=\"window.location.href='/show/" + id + "'\" class=\"btn btn-success\">Перейти</button>  </td>"
    }
    return " <td> <button type=\"button\" onclick='invite("+id+")' class=\"btn btn-success\">Отправить запрос</button>  </td>"
}

function invite(id) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            dataType: 'JSON',
            url: '/group/request/'+ id,
        }).done(function (data) {
        })
} 