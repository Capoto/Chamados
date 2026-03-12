$(document).ready(function () {


// se não tiver token → vai pro login
if (!localStorage.getItem("token")) {
    window.location.href = "/login";
}

// exemplo de chamada à API protegida

});
