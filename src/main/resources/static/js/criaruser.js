$("#registrouser").submit(function(event){
    
    console.log("teste");
    event.preventDefault();
    
    
    var nome = $("#nome").val();
    var email = $("#email").val();
    var empresa = $("#empresa").val();
    var endereco = $("#endereco").val();
    var senha = $("#senha").val();
    var confirma = $("#confirma").val();
    
    var flag =0;
    if(!nome){
        
        document.getElementById("nome").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("nome").className = "form-control is-valid";
    }
    
    if(!email || !email.includes('@')){
        
        document.getElementById("email").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("email").className = "form-control is-valid";
    }
    
    
    if(!empresa){
        
        document.getElementById("empresa").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("empresa").className = "form-control is-valid";
    }
    
    if(!endereco){
        
        document.getElementById("endereco").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("endereco").className = "form-control is-valid";
    }
    
    
    if(!senha){
        
        document.getElementById("senha").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("senha").className = "form-control is-valid";
    }
    
    console.log(senha);
    if(!confirma || confirma!==senha){
        
        document.getElementById("confirma").className = "form-control is-invalid";
        flag=1;
    }else{
        
        
        document.getElementById("confirma").className = "form-control is-valid";
    }
    console.log(confirma);
    var myElements = document.querySelectorAll(".registro");
    console.log(myElements.length);
    for (var i= 0; i < myElements.length ; i++) { 
        myElements[i].style.height = "960px";
    }
    
    if(flag===1){
        
        return;
    }
    
    
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
