$("#registrochamado").submit(function(event){

    console.log("teste");
    $.ajax({
        
        url: "http://localhost:8080/auth/me",
        method: "GET",
        success: function(response){
           
            console.log(response);
            
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.error("Erro:", xhr.responseText);
        }
        
        
    });



});
