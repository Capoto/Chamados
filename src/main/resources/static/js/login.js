$("#login").submit(function(event){
    
    var email = $("#email").val();
    var senha = $("#senha").val();
    
    event.preventDefault();
    
    $.ajax({
       
        url: "http://localhost:8080/auth/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(
                {
                    email: email,
                    senha: senha
                    
                }
                
                ),
        success: function(response){
           
            localStorage.setItem("token", response);
            window.location.href = "/chamado";
            
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.error("Erro:", xhr.responseText);
        }
    });
    
    
    });
