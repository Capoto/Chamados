$("#registrochamado").submit(function(event){

    event.preventDefault();
    var nome = $("#nome").val();
    var email = $("#email").val();
    var categoria = $("#categoria").val();
    var titulo = $("#titulo").val();
    var prioridade = $("#select2 .selectValue").val();
    var ativo = $("#select1 .selectValue").val();
    var descricao = $("#descricao").val();
    
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
    
    if(!titulo){
        document.getElementById("titulo").className = "form-control is-invalid";
        flag=1;
        
    }else{
        
        document.getElementById("titulo").className = "form-control is-valid";
    }
    
    if(categoria==="Selecione a Categoria do Problema"){
        document.getElementById("categoria").className = "form-control is-invalid";
        flag=1;
        
    }else{
        
        document.getElementById("categoria").className = "form-control is-valid";
    }
 
    
    
    if(!ativo){
        $("#select1 .bs-select-display").css("border-color", "red");
        $("#select1 .bs-select-display").css("color", "red");
        flag=1;
        
    }else{
        
        $("#select1 .bs-select-display").css("border-color", "green");
        $("#select1 .bs-select-display").css("color", "green");
    }
    
    if(!prioridade){
        $("#select2 .bs-select-display").css("border-color", "red");
        $("#select2 .bs-select-display").css("color", "red");
        flag=1;
        
    }else{
        
        $("#select2 .bs-select-display").css("border-color", "green");
        $("#select2 .bs-select-display").css("color", "green");
    }
    
    
    if(flag===1){
        
        return;
    }
    
    var id = 0;
    
    $.ajax({
        
        url: "/auth/me",
        method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
        success: function(response){
           
            
            id = response.id;
           $.ajax({
              
               url: "http://localhost:8080/funcao/criar",
               method: "POST",
               headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
               contentType: "application/json",
        data: JSON.stringify({
            nomeouempresa: nome,
            email: email,
            titulo: titulo,
            descricao: descricao,
            categoria: categoria,
            prioridade: prioridade,
            ativo: ativo,
            status: "Aberto",
            userId: id
        }),success: function(response) {
            
            var nome = $("#nome").val('');
            var email = $("#email").val('');
            var categoria = $("#categoria").val('');
            var titulo = $("#titulo").val('');
            var prioridade = $("#select2 .selectValue").val('');
            var ativo = $("#select1 .selectValue").val('');
            var descricao = $("#descricao").val('');
             
            $("#mensagem").html("<h1 class='text-success'>Chamado cadastrado com sucesso!</h1>");
            
            
        },
        error: function(xhr) {
            let json = JSON.parse(xhr.responseText);
            $("#mensagem").html("<h1 style='color: red;'>Erro no cadastro do chamado</h1>");
            
        }
               
           });
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.error("Erro:", xhr.responseText);
        }
        
        
    });

    
        
   


});
