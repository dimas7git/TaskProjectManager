function verificarLogin() {
    var token = localStorage.getItem('token');

    if (!token) {
        window.location.href = 'about.html';
    }
}

function obterProjetoIdDaURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

async function salvarProjeto() {
    verificarLogin();
    event.preventDefault();

    const nomeDoProjeto = document.getElementById('nomeDoProjeto').value;
    const dataDeInicio = document.getElementById('dataDeInicio').value;
    const custoDoProjeto = document.getElementById('custoDoProjeto').value;
    const concluido = document.getElementById('concluido').checked;
    const idProjeto = obterProjetoIdDaURL();

    const projetoData = {
        id: idProjeto,
        nomeDoProjeto: nomeDoProjeto,
        dataDeInicio: dataDeInicio,
        custoDoProjeto: custoDoProjeto,
        removido: concluido
    };

    const token = localStorage.getItem('token');
    const salvarProjetoUrl = 'http://localhost:8080/projetos';

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + token);

    const raw = JSON.stringify(projetoData);

    const requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch(salvarProjetoUrl, requestOptions);

        if (!response.ok) {
            throw new Error(`Erro HTTP! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Projeto atualizado com sucesso:', data);
        window.location.href = 'andamento.html';
    } catch (error) {
        console.error('Erro ao salvar projeto:', error.message);
        window.location.href = 'andamento.html';
    }
}

async function carregarDadosDoProjetoParaEdicao() {
    const projetoId = obterProjetoIdDaURL();
    verificarLogin();

    const token = localStorage.getItem('token');
    const obterProjetoUrl = `http://localhost:8080/projetos/${projetoId}`;

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

        const projeto = await response.json();

        document.getElementById('nomeDoProjeto').value = projeto.nomeDoProjeto;
        document.getElementById('dataDeInicio').value = projeto.dataDeInicio;
        document.getElementById('custoDoProjeto').value = projeto.custoDoProjeto;
        document.getElementById('concluido').checked = projeto.removido;
    } catch (error) {
        console.error('Erro ao obter dados do projeto:', error.message);
    }
}

document.addEventListener('DOMContentLoaded', carregarDadosDoProjetoParaEdicao);
