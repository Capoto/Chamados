function deletarChamado(id){
        
        
        if(!confirm("Você tem certeza que deseja deletar esse Chamado?")){
            
            return;
        }
        
       $.ajax({
          
          url: `http://localhost:8080/funcao/apagar/${id}`,
          method: 'DELETE',
          headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
          success: function(){
              
              alert("Chamado Apagado com sucesso!");
              carregarChamados();
          },
          error: function(){
              
              alert("Não foi possível apagar o chamado!");
          }
          
           
           
       });
    }

function carregarChamados(){
        
        $.ajax({
            
           
            url: "http://localhost:8080/funcao/listar",
            method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    }, success: function(response){
           
            
        $('#tabelaChamados tbody').empty(); 
        
        for(let i =0; i < response.length; i++){
            
            let chamado = response[i];
            
            let id = $('<td>') 
                .text(chamado.id); 
            let titulo = $('<td>') 
                .text(chamado.titulo);
            let nome = $('<td>') 
                .text(chamado.nomeouempresa);
            let email = $('<td>') 
                .text(chamado.email);
            let categoria = $('<td>') 
                .text(chamado.categoria);
            let prioridade = $('<td>') 
                .text(chamado.prioridade);
            let ativo = $('<td>') 
                .text(chamado.ativo);
           
           
            let botaofoto = $('<button class="btn bg-transparent border-0">') 
                .text('📷 Foto');
            let foto = $('<td>') 
                .append(botaofoto);
        
            let botaoeditar = $(`<button onclick="window.location.href='editachamado?id=${response[i].id}'" class="btn bg-transparent border-0">`) 
                .text('✏️️ Editar'); 
                     
            let editar = $('<td>') 
                .append(botaoeditar);    
            
            
            let botaoDeletar = $(`<button onclick="deletarChamado(${response[i].id})" class="btn bg-transparent border-0">`) 
                .text('🗑️ Apagar'); 
                     
            let excluir = $('<td>') 
                .append(botaoDeletar); 
        
            let tr = $('<tr>')
                .attr('data-id', chamado.id) 
                .append(id) 
                .append(titulo)
                .append(nome)
                .append(email)
                .append(categoria)
                .append(prioridade)
                .append(ativo)
                .append(foto)
                .append(editar)
                .append(excluir)
                ;

$('#tabelaChamados tbody').append(tr);
            
        }
            
        console.log(response);
            
        }, 
        error: function(xhr) {
            const erro = JSON.parse(xhr.responseText);
            $("#mensagem").html("<h1 style='color: red;'>"+erro.erro+"</h1>");
            console.error("Erro:", xhr.responseText);
        }
            
            
        });}

$(document).ready(function(){
   
   carregarChamados(); 
    
});
