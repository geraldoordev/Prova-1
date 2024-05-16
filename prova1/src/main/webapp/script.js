// RESET
function reset() {
    // Redefinir a sessão via AJAX POST
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
    console.log("foi")
}

// NOVA AULA
function novaAula() {
	console.log("teste");
    window.location.href = "nova";
}

// CANCELA NOVA AULA (OU EDIÇÃO)
function cancelarNovaAula() {
    window.location.href = "/prova1";
}


// EDITA UMA AULA COM ID ESPECIFICADO
function editarAula(id) {
    window.location.href = "edit?id=" + id;
}

// ENVIA CONTEÚDO DA NOVA AULA
function enviarNovaAula() {
    console.log("Iniciando envio da nova aula...");

    // Obter valores do formulário
    var data = document.getElementById('data-id').value;
    var horario = document.getElementById('hora-id').value;
    var duracao = document.getElementById('dur-id').value;
    var codDisciplina = document.getElementById('disc-id').value;
    var assunto = document.getElementById('ass-id').value;

    console.log("Valores do formulário obtidos:", data, horario, duracao, codDisciplina, assunto);

    // Verificar validação
    if (!validaNovaAula(data, horario, duracao, codDisciplina, assunto)) {
        console.log("Validação da nova aula falhou. Exibindo mensagem de erro...");
        document.getElementById('msg-id').style.display = 'block';
        return;
    }

    console.log("Validação da nova aula bem-sucedida. Enviando dados via AJAX POST...");

    // Enviar dados via AJAX POST
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
    
    if (data && horario && duracao && codDisciplina && assunto) {
        console.log("Aula válida!");
        return true;
    } else {
        console.log("Aula inválida!");
        return false;
    }
}


// ENVIA CONTEÚDO EM EDIÇÃO
function enviarEdit() {
    // Obter valores do formulário
    var id = document.getElementById('id').innerHTML;
    var data = document.getElementById('data-id').value;
    var horario = document.getElementById('hora-id').value;
    var duracao = document.getElementById('dur-id').value;
    var codDisciplina = document.getElementById('disc-id').value;
    var assunto = document.getElementById('ass-id').value;
    // Enviar dados via AJAX POST
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
    xhr.send("op=UPDATE&id=" + id + "&data=" + data + "&horario=" + horario + "&duracao=" + duracao + "&codDisciplina=" + codDisciplina + "&assunto=" + assunto);
}

// DELETA UMA AULA
function deletarAula(id) {
    // Enviar dados via AJAX POST
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

// Função de validação para nova aula
function validaNovaAula(data, horario, duracao, codDisciplina, assunto) {
    // Verifica se algum dos campos está vazio
    if (data === "" || horario === "" || duracao === "" || codDisciplina === "0" || assunto === "") {
        // Se algum campo estiver vazio, retorna false e exibe uma mensagem de erro
        console.log("Por favor, preencha todos os campos.");
        return false;
    }

    // Verifica se a duração é um número positivo
    if (isNaN(duracao) || parseInt(duracao) <= 0) {
        console.log("A duração deve ser um número positivo.");
        return false;
    }

    // Se todos os campos passaram na validação, retorna true
    return true;
}

