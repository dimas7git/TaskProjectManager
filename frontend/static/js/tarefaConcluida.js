function verificarLogin() {
    var token = localStorage.getItem('token');

    if (!token) {
        window.location.href = 'about.html';
    }
}

function obterIdProjetoDaUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

function obterTarefasDoProjeto() {
    const idProjeto = obterIdProjetoDaUrl();
    const token = localStorage.getItem('token');

    axios.get(`http://localhost:8080/tarefas`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(function (response) {
            const tarefasDoProjeto = response.data.filter(tarefa => tarefa.projeto && tarefa.projeto.id === parseInt(idProjeto));

            const taskList = document.getElementById('task-list');
            taskList.innerHTML = '';

            tarefasDoProjeto.forEach(function (tarefa) {
                const taskItem = document.createElement('li');
                taskItem.classList.add('task-row');
                taskItem.style.marginTop = '20px';

                const taskName = document.createElement('span');
                taskName.textContent = tarefa.descricaoDaTarefa;
                taskName.style.overflow = 'hidden';
                taskName.style.textOverflow = 'ellipsis';
                taskName.style.whiteSpace = 'nowrap';
                taskName.style.flex = '1';

                const taskStatus = document.createElement('span');
                taskStatus.classList.add('task-status');

                taskItem.appendChild(taskName);
                taskItem.appendChild(taskStatus);
                taskList.appendChild(taskItem);
            });
        })
        .catch(function (error) {
            console.error('Erro ao obter tarefas:', error.message);
        });
}

document.addEventListener('DOMContentLoaded', function () {
    verificarLogin();
    pegaId();
    obterTarefasDoProjeto();

});

function pegaId() {
    const urlParams = new URLSearchParams(window.location.search);
    const projectId = urlParams.get('id');
    document.getElementById('projetoNome').innerText = `${projectId}`;
}


function obterParametroDaUrl(parametro) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(parametro);
}

async function carregarNomeDoProjetoPorId() {
    const idUrl = obterParametroDaUrl('id');

    if (idUrl) {
        const token = localStorage.getItem('token');
        const obterProjetoUrl = `http://localhost:8080/projetos/${idUrl}`;

        const myHeaders = new Headers();
        myHeaders.append("Authorization", "Bearer " + token);

        const requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
        };

        try {
            const response = await fetch(obterProjetoUrl, requestOptions);

            if (!response.ok) {
                throw new Error(`Erro HTTP! Status: ${response.status}`);
            }

            const data = await response.json();
            const nomeDoProjeto = data.nomeDoProjeto;

            document.getElementById('projetoNome').innerText = `${nomeDoProjeto}`;

        } catch (error) {
            console.error('Erro ao obter projeto por ID:', error.message);
        }
    } else {
        console.error('ID não encontrado na URL.');
    }
}

carregarNomeDoProjetoPorId();

function verificarLogin() {
    var token = localStorage.getItem('token');

    if (!token) {
        window.location.href = 'about.html';
    }
}

verificarLogin();

async function salvarTarefa() {
    verificarLogin();
    event.preventDefault();

    const descricaoTarefa = document.getElementById('descricaoTarefa').value;
    const statusTarefa = document.getElementById('statusTarefa').value;
    const idProjeto = obterIdProjetoDaUrl(); 
    const tarefaData = {
        descricaoDaTarefa: descricaoTarefa,
        status: statusTarefa,
        projeto: {
            id: idProjeto
        }
    };

    const token = localStorage.getItem('token');
    const salvarTarefaUrl = 'http://localhost:8080/tarefas';

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + token);

    const raw = JSON.stringify(tarefaData);

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch(salvarTarefaUrl, requestOptions);

        if (!response.ok) {
            throw new Error(`Erro HTTP! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Tarefa salva com sucesso:', data);
        //window.location.href = 'andamento.html'; 
        location.reload();
    } catch (error) {
        console.error('Erro ao salvar tarefa:', error.message);
    }
}

function obterIdProjetoDaUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}


function obterParametroId() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

function redirecionarParaCadUpdate() {
    const id = obterParametroId();

    const novoURL = `http://127.0.0.1:5500/frontend/templates/cadUpdate.html?id=${id}`;

    window.location.href = novoURL;
}
