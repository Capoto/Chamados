$(document).ready(function(){
    
    // 🔹 1. Pegar ID da URL
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("ID inválido!");
        window.location.href = "index.html";
    }
    
    $.ajax({
       
        url: `http://localhost:8080/funcao/pesquisar/${id}`,
        method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
        success: function(response){
           
            console.log(response);
            var nome = $("#nome").val(response.nomeouempresa);
            var email = $("#email").val(response.email);
            var categoria = $("#categoria").val(response.categoria);
            var titulo = $("#titulo").val(response.titulo);
            var prioridade = $("#select2 .selectValue").val(response.prioridade);
            $("#select2 .selectText").text(response.prioridade);
            var ativo = $("#select1 .selectValue").val(response.ativo);
            $("#select1 .selectText").text(response.ativo);
            var descricao = $("#descricao").val(response.descricao);
            
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.log("Erro:", xhr.responseText);
        }
        
        
    
    
        
        
    });
    
    
    
});
