function deletarChamado(id){
        
        
        if(!confirm("Você tem certeza que deseja deletar esse Chamado?")){
            
            return;
        }
        
       $.ajax({
          
          url: `/funcao/apagar/${id}`,
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

let paginaAtual = 0;

function carregarChamados() {

    let filtro = $("#selectFiltro").val();     // ex: 1, 2, 3...
    let busca  = $("#inputBusca").val();       // texto digitado


     $.ajax({
        
        url: "/auth/me",
        method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
        success: function(login){
            
            
    $.ajax({
        url: "/funcao/listar",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: {
            page: paginaAtual,
            size: 10,
            filtro: filtro,
            busca: busca,
            id: login.id
        },
        success: function(response) {

            // Limpando tabela
            $('#tabelaChamados tbody').empty();

            // response.content é a lista real
            let lista = response.content;

            for (let i = 0; i < lista.length; i++) {

                let chamado = lista[i];

                let tr = $('<tr>')
                    .attr('data-id', chamado.id)
                    .append($('<td>').text(chamado.id))
                    .append($('<td>').text(chamado.titulo))
                    .append($('<td>').text(chamado.nomeouempresa))
                    .append($('<td>').text(chamado.email))
                    .append($('<td>').text(chamado.categoria))
                    .append($('<td>').text(chamado.prioridade))
                    .append($('<td>').text(chamado.ativo))
                    .append($('<td>').append(
                        $('<button class="btn bg-transparent border-0">📷 Foto</button>')
                    ))
                    .append($('<td>').append(
                        $(`<button onclick="window.location.href='editachamado?id=${chamado.id}'" class="btn bg-transparent border-0">✏️ Editar</button>`)
                    ))
                    .append($('<td>').append(
                        $(`<button onclick="deletarChamado(${chamado.id})" class="btn bg-transparent border-0">🗑️ Apagar</button>`)
                    ));

                $('#tabelaChamados tbody').append(tr);
            }

            montarPaginacao(response.totalPages);
        },
        error: function(xhr) {
            console.error("Erro:", xhr.responseText);
        }
    });
    
        },erro: function(xhor){
            
            console.error("Erro:", xhr.responseText);
        }});
}

function montarPaginacao(totalPages) {

    $("#paginacao").empty();

    for (let i = 0; i < totalPages; i++) {
        let btn = $(`
            <li class="page-item ${i === paginaAtual ? 'active' : ''}">
                <button class="page-link">${i + 1}</button>
            </li>
        `);

        btn.click(() => {
            paginaAtual = i;
            carregarChamados();
        });

        $("#paginacao").append(btn);
    }
}

$("#inputBusca").on("keyup", function () {
    paginaAtual = 0;
    carregarChamados();
});

$("#selectFiltro").on("change", function () {
    paginaAtual = 0;
    carregarChamados();
});

$(document).ready(function(){
   
   carregarChamados(); 
    
});
