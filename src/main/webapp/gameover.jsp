<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Game Over</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            text-align: center; 
            background-color: #111; 
            color: #fff; 
            padding-top: 50px;
        }
        h1 { color: red; font-size: 48px; }
        button {
            padding: 10px 20px; 
            font-size: 18px; 
            margin-top: 20px; 
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Game Over!</h1>
    <p>Você foi pego por um inimigo!</p>
    
    <!-- Botão para reiniciar o jogo -->
    <form action="index.jsp" method="get">
        <button type="submit">Voltar para a tela inicial</button>
    </form>
</body>
</html>