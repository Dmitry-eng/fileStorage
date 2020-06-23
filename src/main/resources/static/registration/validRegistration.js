$(document).ready(function () {

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

        $('#password').blur(function () {
            validPasswordForm();
        });

        $('#name').blur(function () {
            validNameForm();
        });
        $('#login').blur(function () {
            validLoginForm();
        });

        $('#email').blur(function () {
            validEmailForm();
        });


        $('#vEmail').click(function () {
            event.preventDefault();
            var a = validPasswordForm();
            var b = validNameForm();
            var c = validLoginForm();
            var d = validEmailForm();
            if (!a || !b || !c || !d) {
                $("#messageValidForm").text("Проверьте правильность заполнения формы");
                return;
            } else $("#messageValidForm").text("");
            $.ajax({
                type: "PUT",
                url: '/reg/sendEmail/' + $("#email").val(),
            });

            $(this).attr('disabled', true);
            $("#name").attr('disabled', true);
            $("#login").attr('disabled', true);
            $("#password").attr('disabled', true);
            $("#email").attr('disabled', true);
            $('div#verification').append("  <div class=\"form-group\">\n" +
                "            <label for=\"number\">Введите код подтверждения: </label>\n" +
                "            <input type=\"text\" class=\"form-control mb-2 mr-sm-2\" id=\"number\" placeholder=\"\">\n" +
                "        </div>\n" +
                "        <input type=\"submit\" id=\"send\" class=\"btn btn-primary\" value=\"Завершить регистрацию\">\n" +
                "        <span id=\"result\"></span>");
        });
        $(document).on('click', '#send', function () {
            event.preventDefault();
            $.ajax({
                type: "POST",
                url: '/reg/send/' + $("#name").val() + '/' + $("#login").val() + '/' + $("#password").val() + '/' + $("#email").val() + '/' + $("#number").val(),
            }).then(function (data) {
                if (data) window.location.href = '/';
                else
                    $('#result').text("Не верный код подтверждения");
                // проработать автоматическое перенаправление на сервелет в случае правильного ввода кода подтверждея
            })
        });


    }
);

function validPasswordForm() {
    if ($('#password').val().replace(" ", "").length == 0) {
        $('#messageValidPassword').text("Поле не может быть пустым");
        return false;
    } else $('#messageValidPassword').text("");
    return true;
}

function validNameForm() {
    if ($('#name').val().replace(" ", "").length == 0) {
        $('#messageValidName').text("Поле не может быть пустым");
        return false;
    } else $('#messageValidName').text("");
    return true;
}
function validLoginForm() {
    if ($('#login').val().replace(" ", "").length == 0) {
        $('#messageValidLogin').text("Поле не может быть пустым");
        return false;
    } else
    $.ajax({
        type: "PUT",
        url: '/reg/login/'+ $('#login').val(),
         }).done(function (data) {
        if (data) {
            $('#messageValidLogin').text("Логин свободный.");
            bool= true;
        } else $('#messageValidLogin').text("Логин занят.");
            bool= false;
    });
    return bool;
}


function validEmailForm() {

    if ($('#email').val().replace(" ", "").length == 0) {
        $('#messageValidEmail').text("Поле не может быть пустым");
        return false;
    }
    var pattern = /^[a-z0-9_-]+@[a-z0-9-]+\.[a-z]{2,6}$/i;
    var mail = $('#email');
    if (mail.val().search(pattern) != 0) {
        $('#messageValidEmail').text("Проверьте правильность заполнения адреса электронной почты");
        return false;
    }

    $.ajax({

        type: "PUT",
        url: '/reg/email/' + $('#email').val()
    }).done(function (data) {
        if (data) {
            $('#messageValidEmail').text("Почта свободна");
            bool= true;
        } else {
            $('#messageValidEmail').text("Почта занята.");
            bool= false;
        }
    })
    return bool;
}