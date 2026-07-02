$(document).ready(function () { 
    

console.log("teste");
    $.ajax({
        
        url: "/auth/me",
        method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
        success: function(response){
           
            console.log(response);
            
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.log("Erro:", xhr.responseText);
        }
        
        
    });



});
