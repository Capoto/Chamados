$("#registrouser").submit(function(event){
    
    console.log("teste");
    event.preventDefault();
    
    
    var nome = $("#nome").val();
    var email = $("#email").val();
    var empresa = $("#empresa").val();
    var endereco = $("#endereco").val();
    var senha = $("#senha").val();
    var confima = $("confirma").val();
    
    
    $.ajax({
        
        url: "http://localhost:8080/auth/registrar",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            nome: nome,
            email: email,
            empresa: empresa,
            endereco: endereco,
            senha: senha,
            role: "CLIENTE"
        }),
        success: function(response) {
            
            $("#nome").val('');
            $("#email").val('');
            $("#empresa").val('');
            $("#endereco").val('');
            $("#senha").val('');
            $("#confirma").val('');
             
            $("#mensagem").html("<h1 class='text-success'>Usuário cadastrado com sucesso!</h1>");
            
            console.log("Resposta:", response);
        },
        error: function(xhr) {
            let json = JSON.parse(xhr.responseText);
            $("#mensagem").html("<h1 style='color: red;'>"+json.erro+"</h1>");
            console.error("Erro:", xhr.responseText);
        }
   
        
        
        
    });
    
    
    
    
});
