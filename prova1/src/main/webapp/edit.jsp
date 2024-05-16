<%@page import="model.AulaDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<title>Prova 1</title>
<link rel="stylesheet" href="style.css">
</head>

<body>
	<%
// Verifica se há dados na sessão e recupera o DTO da sessão
AulaDto aulaDto = (AulaDto) request.getSession().getAttribute("aulaDto");
String nomeDisciplina = aulaDto.getDisciplina(); // Corrigido para getDisciplina()
%>
	<header class="container-cabecalho">

		<h3>
			Editando: aula de <span id="nome-disciplina"><%=nomeDisciplina%></span>
		</h3>
	</header>
	<nav class="container-nav">
		<div class="btn-nav" onclick="enviarEdit()">ENVIAR</div>
		<div class="btn-nav" onclick="cancelarEdicao()">CANCELAR</div>
	</nav>

	<div class="container-geral">
		<div class="container-aula-edit" id="msg-id" hidden="hidden">
			<div class="texto">Erro ao tentar registrar dados</div>
		</div>
		<div class="container-aula-edit">
			<div id="id" hidden="hidden"><%=aulaDto.getId()%></div>
			<div class="container-linha1">
				<div class="info">
					Data: <input id="data-id" type="date" class="inp-data"
						value="<%=aulaDto.getData()%>">
				</div>
				<div class="info">
					Horário: <input id="hora-id" type="text" class="inp-hora"
						value="<%=aulaDto.getHorario()%>">
				</div>
				<div class="info">
					Duração: <input id="dur-id" type="number" class="inp-dur"
						value="<%=aulaDto.getDuracao()%>">
				</div>
			</div>
			<div class="container-linha2">
				<div class="info">
					Disciplina: <select name="" id="disc-id" class="inp-disc">
						<option value="1"
							<%=("1".equals(aulaDto.getCodDisciplina())) ? "selected" : ""%>>CÁLCULO</option>
						<option value="2"
							<%=("2".equals(aulaDto.getCodDisciplina())) ? "selected" : ""%>>LÓGICA</option>
						<option value="3"
							<%=("3".equals(aulaDto.getCodDisciplina())) ? "selected" : ""%>>GEOMETRIA</option>
						<option value="4"
							<%=("4".equals(aulaDto.getCodDisciplina())) ? "selected" : ""%>>FÍSICA</option>
						<option value="5"
							<%=("5".equals(aulaDto.getCodDisciplina())) ? "selected" : ""%>>COMPILADORES</option>
					</select>
				</div>
				<div class="info">
					Assunto: <input id="ass-id" type="text" class="inp-ass"
						value="<%=aulaDto.getAssunto()%>">
				</div>
			</div>
		</div>
	</div>

	<script src="script.js"></script>
	<script type="text/javascript">
		function cancelarEdicao() {
			window.location.href = "/prova1";
		}
	</script>

</body>

</html>
