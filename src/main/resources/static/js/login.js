$("#login").submit(function(event){
    
    var email = $("#email").val();
    var senha = $("#senha").val();
    
    event.preventDefault();
    var flag = 0;
    if (!email || !email.includes('@')) {
    document.getElementById("email").className = "form-control is-invalid";
     flag = 1; // Stop the API call
  }else{
      
      document.getElementById("email").className = "form-control is-valid";
  }
  
    if(!senha){
        document.getElementById("senha").className = "form-control is-invalid";
    flag=1;
        
    }else{
      
      document.getElementById("senha").className = "form-control is-valid";
  }
  
  if(flag===1){
      
      return;
  }
    
    $.ajax({
       
        url: "/auth/login",
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
            const erro = JSON.parse(xhr.responseText);
            $("#mensagem").html("<h1 style='color: red;'>"+erro.erro+"</h1>");
            console.error("Erro:", xhr.responseText);
        }
    });
    
    
    });
