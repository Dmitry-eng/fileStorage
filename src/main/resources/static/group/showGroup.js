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
            "        <td>" + data[i].info + "</td>\n" +
            "        <td>"+data[i].data+"</td>\n" +
            "        <td>" +data[i].account.login + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/file/download/" + data[i].id + "'\" class=\"btn btn-success\">Скачать</button>  </td>\n" +
            getButtonRemoveFile( data[i].id) +
            "    </tr>");

    }
}
function list() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/show/'+$("meta[name='groupId']").attr("content")+'/'+ $("#list").val(),
    }).done(function (data) {
        Search(data);
    })

}
function addFileGroup() {
    var lefto = screen.availWidth/2-150;
    var righto = screen.availHeight/2-125;
    window.open("/addFileGroup/"+$("meta[name='groupId']").attr("content"), "INFO", "width=300, height=250, left=" + lefto + ", top="+righto+"");
}
function showAccountGroup() {
    var lefto = screen.availWidth/2-150;
    var righto = screen.availHeight/2-125;
    window.open("/showAccountGroup/"+$("meta[name='groupId']").attr("content"), "INFO", "width=300, height=250, left=" + lefto + ", top="+righto+"");
}
function getButtonRemoveFile(id) {
    if ($("meta[name='groupLead']").attr("content") === "true") {
        return " <td>  <button type=\"button\" onclick='removeFile(" + id + ")' class=\"btn btn-success\">Удалить файл</button>  </td>\n"
    } else
        return "<td> </td>"
}
function removeFile(id) {
    $.ajax({
        type: "DELETE",
        dataType: 'JSON',
        url: '/show/deleteFileGroup/' + $("meta[name='groupId']").attr("content") + '/' + id,
    }).done(function (data) {
        list()
    })
}