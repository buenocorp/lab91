<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Labirinto Animado</title>
    <style>
        /* Reset simples */
        * { margin: 0; padding: 0; box-sizing: border-box; }

        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            background: linear-gradient(to bottom, #1e3c72, #2a5298);
            color: #fff;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 20px;
            text-shadow: 2px 2px 5px rgba(0,0,0,0.5);
        }

        p {
            font-size: 20px;
            margin-bottom: 40px;
            text-shadow: 1px 1px 3px rgba(0,0,0,0.5);
        }

        form button {
            background-color: #ffcc00;
            color: #000;
            border: none;
            border-radius: 8px;
            padding: 15px 30px;
            font-size: 18px;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 8px rgba(0,0,0,0.3);
        }

        form button:hover {
            background-color: #ffd633;
            transform: translateY(-2px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.4);
        }

        form button:active {
            transform: translateY(1px);
            box-shadow: 0 3px 6px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>
    <h1>Bem-vindo ao Labirinto!</h1>
    <p>Inicie o labirinto animado com inimigos e itens!</p>
    <form action="maze" method="post">
        <button type="submit">Iniciar Labirinto</button>
    </form>
</body>
</html>