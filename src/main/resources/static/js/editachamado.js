$("#registrochamado").submit(function(event){
    
    event.preventDefault(); // impedir reload

    console.log("Submit acionado!");

    var nome = $("#nome").val();
    var email = $("#email").val();
    var categoria = $("#categoria").val();
    var titulo = $("#titulo").val();
    var prioridade = $("#select2 .selectValue").val();
    var ativo = $("#select1 .selectValue").val();
    var descricao = $("#descricao").val();

    // Pegar id da URL
    const params = new URLSearchParams(window.location.search);
    const idChamado = params.get("id");

    if (!idChamado) {
        alert("ID inválido!");
        return;
    }

    // TESTES
    console.log("PRIORIDADE:", prioridade);
    console.log("ATIVO:", ativo);
    console.log(">>> CATEGORIA REAL ENVIADA =", categoria, "| tipo:", typeof categoria);
    $.ajax({
        url: `/funcao/editar/${idChamado}`,
        method: "PUT",
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
            userId: localStorage.getItem("userId") // <-- CORRETO
        }),
        success: function(response){
            $("#mensagem").html("<h1 class='text-success'>Chamado editado com sucesso!</h1>");
        },
        error: function(xhr) {
            console.log("ERRO NO AJAX:", xhr);
            $("#mensagem").html("<h1 style='color: red;'>Erro ao editar chamado</h1>");
        }
    });
    
    
});
