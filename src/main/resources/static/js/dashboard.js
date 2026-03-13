$(document).ready(function(){
    
 
    
    $.ajax({
        
        url: "http://localhost:8080/auth/me",
        method: "GET",
        headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
    },
        success: function(response){
           
        $.ajax({
        
            url: `http://localhost:8080/funcao/metricas/${response.id}`,
            method: "GET",
            headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
            },
            success: function(response){
                
                $("#quantidadechamados").text(response.quantidadeChamados);
                $("#quantidadecriticos").text(response.critico);
                $("#quantidadealtos").text(response.alta);
                $("#quantidadeabertos").text(response.aberto);
                
                
                

                const data = {
            labels: ["Hardware",
    "Software",
    "Switch",
    "Rede",
    "Licitação",
    "Financeiros",
    "Outros"],
            datasets: [{
                
                data: [response.hardware,response.software,response.switchCategoria
,response.rede,response.licitacao,response.financeiros,response.outros],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
                borderWidth: 1
            }]
        };

        const ctx = document.getElementById('myChart');
        new Chart(ctx, {
            type: 'bar',
            data: data,
            options: {
    plugins: {
      legend: {
      display: false // Oculta a legenda
    }
  }}});
    
    
        const data2 = {
  labels: [
    'Baixa',
    'Média',
    'Alta',
    'Crítica'
  ],
  datasets: [{
    data: [response.baixa,response.media,response.alta,response.critico],
    backgroundColor: [
      'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 205, 86)',
      'rgb(75, 192, 192)'
    ],
                borderColor: [
                    'rgb(255, 99, 132)',
      'rgb(54, 162, 235)',
      'rgb(255, 205, 86)',
      'rgb(75, 192, 192)'
                ],
                borderWidth: 1
  }]
};

const ctx2 = document.getElementById('myChart2');
new Chart(ctx2, {
  type: 'bar',
  data: data2, options: {
    plugins: {
      legend: {
      display: false // Oculta a legenda
    }
  }}
  
});
                
                console.log(response);
            },
            error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.log("Erro:", xhr.responseText);
            
            }
        });
            
            
        }, 
        error: function(xhr) {
            //$("#mensagem").html("<h1 style='color: red;'>Erro ao cadastrar!</h1>");
            console.log("Erro:", xhr.responseText);
        }
        
        
        
    });
    
    
    
});
