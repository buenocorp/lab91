<%@ page import="java.util.*" %>
<%
    Integer[][] maze = (Integer[][]) session.getAttribute("maze");
    Integer row = (Integer) session.getAttribute("playerRow");
    Integer col = (Integer) session.getAttribute("playerCol");
    List<int[]> enemies = (List<int[]>) session.getAttribute("enemies");
    String player = (String) session.getAttribute("player");
    Integer itemsCollected = (Integer) session.getAttribute("itemsCollected");
    Integer totalItems = (Integer) session.getAttribute("totalItems");

    if (maze == null) { out.println("<p>Erro: labirinto não inicializado!</p>"); return; }
%>

<html>
<head>
    <title>Labirinto Animado</title>
    <style>
        table { border-collapse: collapse; margin-top: 20px; }
        td { width: 30px; height: 30px; text-align:center; vertical-align:middle; border:1px solid #555; }
        .wall { background-color: black; }
        .player { background-color: red; }
        .enemy { background-color: blue; }
        .item { background-color: gold; }
    </style>
    <script>
        // Atualiza o labirinto a cada 1 segundo
        setInterval(function(){
            document.location.reload();
        }, 1000);

        // Movimentos com teclas
        document.addEventListener('keydown', function(e){
            let dir = null;
            switch(e.key){
                case "ArrowUp": dir = "up"; break;
                case "ArrowDown": dir = "down"; break;
                case "ArrowLeft": dir = "left"; break;
                case "ArrowRight": dir = "right"; break;
            }
            if(dir){
                const form = document.getElementById('moveForm');
                form.dir.value = dir;
                form.submit();
            }
        });
    </script>
</head>
<body>
    <h2>Jogador: <%= player %></h2>
    <p>Itens coletados: <%= itemsCollected %> / <%= totalItems %></p>

    <table>
        <%
            for (int i=0;i<maze.length;i++){
                out.println("<tr>");
                for (int j=0;j<maze[i].length;j++){
                    String cls="";
                    if (i==row && j==col) cls="player";
                    else if (maze[i][j]!=null) {
                        if (maze[i][j]==1) cls="wall";
                        else if (maze[i][j]==2) cls="item";
                    }
                    if (enemies!=null) {
                        for (int[] e: enemies) {
                            if (e[0]==i && e[1]==j) { cls="enemy"; break; }
                        }
                    }
                    out.println("<td class='"+cls+"'></td>");
                }
                out.println("</tr>");
            }
        %>
    </table>

    <form id="moveForm" action="move" method="post">
        <input type="hidden" name="dir" value="">
    </form>

    <form action="reset" method="post" style="margin-top:10px;">
        <button type="submit">Reiniciar</button>
    </form>
</body>
</html>