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

function salvarStatusDaTarefa(idTarefa, novoStatus, descricaoTarefa) {
const token = localStorage.getItem('token');
const idProjeto = obterParametroDaUrl('id'); 

if (novoStatus === 'remover') {
// Soft delete
axios.delete(`http://localhost:8080/tarefas/${idTarefa}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
.then(function (response) {
    console.log('Tarefa removida com sucesso:', response.data);
    window.location.reload();
})
.catch(function (error) {
    console.error('Erro ao remover a tarefa:', error.message);
});
} else {
// Atualização do status
axios.put(`http://localhost:8080/tarefas`, {
    id: idTarefa,
    descricaoDaTarefa: descricaoTarefa,
    status: novoStatus,
    projeto: {
        id: idProjeto
    }
}, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
.then(function (response) {
    console.log('Status da tarefa atualizado com sucesso:', response.data);
    window.location.reload();
})
.catch(function (error) {
    console.error('Erro ao atualizar o status da tarefa:', error.message);
});
}
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

            const taskName = document.createElement('span');
            taskName.textContent = tarefa.descricaoDaTarefa;
            
            if (tarefa.status === 'concluida') {
                taskName.style.textDecoration = 'line-through';
            }

            taskName.style.overflow = 'hidden';
            taskName.style.textOverflow = 'ellipsis';
            taskName.style.whiteSpace = 'nowrap';
            taskName.style.flex = '1';

            const taskStatus = document.createElement('select');
            taskStatus.classList.add('form-control', 'task-status');

            const optionAndamento = document.createElement('option');
            optionAndamento.value = 'andamento';
            optionAndamento.textContent = 'Andamento';
            optionAndamento.selected = tarefa.status === 'andamento';

            const optionFila = document.createElement('option');
            optionFila.value = 'fila';
            optionFila.textContent = 'Na fila';
            optionFila.selected = tarefa.status === 'fila';

            const optionConcluida = document.createElement('option');
            optionConcluida.value = 'concluida';
            optionConcluida.textContent = 'Concluída';
            optionConcluida.selected = tarefa.status === 'concluida';

            const optionRemover = document.createElement('option');
            optionRemover.value = 'remover';
            optionRemover.textContent = 'Remover';

            taskStatus.appendChild(optionAndamento);
            taskStatus.appendChild(optionFila);
            taskStatus.appendChild(optionConcluida);
            taskStatus.appendChild(optionRemover);

            const saveButton = document.createElement('button');
            saveButton.textContent = 'Atualizar';
            saveButton.classList.add('btn', 'btn-primary', 'btn-sm');
            saveButton.addEventListener('click', function () {
                // Chame a função para salvar o status no banco de dados, passando o ID da tarefa
                salvarStatusDaTarefa(tarefa.id, taskStatus.value, tarefa.descricaoDaTarefa);
            });

            taskItem.appendChild(taskName);
            taskItem.appendChild(taskStatus);
            taskItem.appendChild(saveButton);
            taskList.appendChild(taskItem);
        });
    })
    .catch(function (error) {
        console.error('Erro ao obter tarefas:', error.message);
    });
}

document.addEventListener('DOMContentLoaded', function () {
    verificarLogin();
    obterTarefasDoProjeto();
});






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
const idProjeto = obterIdProjetoDaUrl(); // Função a ser definida para extrair o id do projeto da URL

const tarefaData = {
    descricaoDaTarefa: descricaoTarefa,
    status: statusTarefa,
    projeto: {
        id: idProjeto
    }
};

function limparFormulario() {
    var formulario = document.getElementById("task-form");
    formulario.reset();
}

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
    //window.location.href = '';
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
