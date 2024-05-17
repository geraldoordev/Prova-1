// Função de reset
function reset() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ControllerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            atualizaSessao();
            window.location.href = "/prova1";
        } else {
            // Lógica para lidar com falha
        }
    };
    xhr.send("op=RESET");
    console.log("foi");
}

// Função para criar uma nova aula
function novaAula() {
    console.log("teste");
    window.location.href = "nova";
}

// Função para cancelar a criação ou edição de uma aula
function cancelarNovaAula() {
    window.location.href = "/prova1";
}

// Função para editar uma aula existente
function editarAula(id) {
    window.location.href = "edit?id=" + id;
}

// Função para enviar os dados de uma nova aula
function enviarNovaAula() {
    console.log("Iniciando envio da nova aula...");

    var data = document.getElementById('data-id').value;
    var horario = document.getElementById('hora-id').value;
    var duracao = document.getElementById('dur-id').value;
    var codDisciplina = document.getElementById('disc-id').value;
    var assunto = document.getElementById('ass-id').value;

    console.log("Valores do formulário obtidos:", data, horario, duracao, codDisciplina, assunto);

    if (!validaAula(data, horario, duracao, codDisciplina, assunto)) {
        console.log("Validação da nova aula falhou. Exibindo mensagem de erro...");
        document.getElementById('msg-id').style.display = 'block';
        return;
    }

    console.log("Validação da nova aula bem-sucedida. Enviando dados via AJAX POST...");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ControllerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Dados enviados com sucesso. Atualizando sessão e redirecionando...");
            atualizaSessao();
            window.location.href = "/prova1";
        } else {
            console.log("Falha ao enviar dados via AJAX. Status:", xhr.status);
            // Lógica para lidar com falha
        }
    };
    xhr.send("op=CREATE&data=" + data + "&horario=" + horario + "&duracao=" + duracao + "&codDisciplina=" + codDisciplina + "&assunto=" + assunto);
}

// Função para enviar os dados de uma aula editada
function enviarEdit() {
    console.log("Iniciando envio da edição da aula...");

    var id = document.getElementById('id').innerHTML;
    var data = document.getElementById('data-id').value;
    var horario = document.getElementById('hora-id').value;
    var duracao = document.getElementById('dur-id').value;
    var codDisciplina = document.getElementById('disc-id').value;
    var assunto = document.getElementById('ass-id').value;

    console.log("Valores do formulário obtidos:", id, data, horario, duracao, codDisciplina, assunto);

    if (!validaAula(data, horario, duracao, codDisciplina, assunto)) {
        console.log("Validação da edição da aula falhou. Exibindo mensagem de erro...");
        document.getElementById('msg-id').style.display = 'block';
        return;
    }

    console.log("Validação da edição da aula bem-sucedida. Enviando dados via AJAX POST...");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ControllerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Dados enviados com sucesso. Atualizando sessão e redirecionando...");
            atualizaSessao();
            window.location.href = "/prova1";
        } else {
            console.log("Falha ao enviar dados via AJAX. Status:", xhr.status);
            // Lógica para lidar com falha
        }
    };
    xhr.send("op=UPDATE&id=" + id + "&data=" + data + "&horario=" + horario + "&duracao=" + duracao + "&codDisciplina=" + codDisciplina + "&assunto=" + assunto);
}

// Função para deletar uma aula
function deletarAula(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ControllerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            atualizaSessao();
            window.location.href = "/prova1";
        } else {
            // Lógica para lidar com falha
        }
    };
    xhr.send("op=DELETE&id=" + id);
}

// Função para atualizar a sessão
function atualizaSessao() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ControllerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Lógica para lidar com sucesso
        } else {
            // Lógica para lidar com falha
        }
    };
    xhr.send("op=START_SESSION");
}

// Função de validação para nova aula e edição
function validaAula(data, horario, duracao, codDisciplina, assunto) {
    if (data === "" || horario === "" || duracao === "" || codDisciplina === "0" || assunto === "") {
        console.log("Por favor, preencha todos os campos.");
        return false;
    }

    if (isNaN(duracao) || parseInt(duracao) <= 0) {
        console.log("A duração deve ser um número positivo.");
        return false;
    }

    return true;
}
