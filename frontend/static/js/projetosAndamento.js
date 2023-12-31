function verificarLogin() {
    var token = localStorage.getItem('token');
    
    if (!token) {
        window.location.href = 'about.html';
    }
}

function carregarProjetos() {
    verificarLogin();
    var token = localStorage.getItem('token');

    axios.get('http://localhost:8080/projetos/', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })				
    
    .then(function (response) {
        console.log(response.data);
        exibirProjetos(response.data);
    })
    .catch(function (error) {
        console.error('Erro ao carregar projetos:', error);
    });
}

function exibirProjetos(projetos) {
var container = document.getElementById('projetosContainer');

var projetos = projetos.filter(function (projeto) {
    return projeto.removido === false;
});

projetos.forEach(function (projeto) {
    var cardHtml = `
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title mb-3">
                        <span class="theme-icon-holder card-icon-holder me-2">
                            <i class="fa solid fa-check"></i>
                        </span>
                        <span class="card-title-text">${projeto.nomeDoProjeto}</span>
                    </h5>
                    <div class="card-text">
                        Data de início: ${projeto.dataDeInicio}
                    </div>
                    <div class="card-text">
                        Valor do projeto: R$ ${projeto.custoDoProjeto}
                    </div>
                    <!-- Adicione mais campos conforme necessário -->
                    <div class="card-text">

                    </div>
                    <a class="card-link-mask" href="tarefa.html?id=${projeto.id}"></a>
                </div>
            </div>
        </div>
    `;

    container.innerHTML += cardHtml;
});
}

function editarProjeto(projetoId) {
// Redirecionar para a página de cadastro com os dados do projeto preenchidos
window.location.href = `cadProj.html?id=${projetoId}`;
}

window.onload = function () {
carregarProjetos();
};

