function buttonAdminInit() {
    if ($("meta[name='admin']").attr("content") === "true") {
        console.log("show")
        $('.admin-button').show();
    } else {
        console.log("hide")
        $('.admin-button').hide();
    }
}