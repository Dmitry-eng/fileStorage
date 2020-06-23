$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        event.preventDefault();
        $.ajax({
            type: "PUT",
            dataType: 'JSON',
            url: '/list/search/',
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
            type: "PUT",
            dataType: 'JSON',
            url: '/list/search/'+ $("#list").val(),
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
            "        <td>" + data[i].info + "</td>\n" +
            "        <td>"+data[i].data+"</td>\n" +
            "        <td>" +data[i].user.login + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/download/" + data[i].id + "'\" class=\"btn btn-success\">Скачать</button>  </td>\n" +
            "    </tr>");

    }
}