package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer[][] maze = (Integer[][]) session.getAttribute("maze");
        Integer row = (Integer) session.getAttribute("playerRow");
        Integer col = (Integer) session.getAttribute("playerCol");
        List<int[]> enemies = (List<int[]>) session.getAttribute("enemies");
        Integer itemsCollected = (Integer) session.getAttribute("itemsCollected");
        Integer totalItems = (Integer) session.getAttribute("totalItems");

        if (maze == null || row == null || col == null || enemies == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String dir = request.getParameter("dir");
        int newRow = row, newCol = col;
        switch(dir){
            case "up": newRow--; break;
            case "down": newRow++; break;
            case "left": newCol--; break;
            case "right": newCol++; break;
        }

        // Movimento jogador
        if(newRow>=0 && newRow<maze.length && newCol>=0 && newCol<maze[0].length && maze[newRow][newCol]!=1){
            row=newRow; col=newCol;

            // coleta item
            if(maze[row][col]==2){ itemsCollected++; maze[row][col]=0; }
            // vitória
            if(row==maze.length-1 && col==maze[0].length-1){
                session.invalidate();
                response.sendRedirect("win.jsp");
                return;
            }
        }

        // Movimento aleatório inimigos
        Random rand = new Random();
        for(int[] e: enemies){
            int er=e[0], ec=e[1];
            List<int[]> moves = Arrays.asList(new int[]{-1,0}, new int[]{1,0}, new int[]{0,-1}, new int[]{0,1});
            Collections.shuffle(moves, rand);
            for(int[] m: moves){
                int nr=er+m[0], nc=ec+m[1];
                if(nr>=0 && nr<maze.length && nc>=0 && nc<maze[0].length && maze[nr][nc]!=1){
                    e[0]=nr; e[1]=nc; break;
                }
            }
        }

        // Colisão
        for(int[] e: enemies){
            if(e[0]==row && e[1]==col){
                session.invalidate();
                response.sendRedirect("gameover.jsp");
                return;
            }
        }

        session.setAttribute("playerRow", row);
        session.setAttribute("playerCol", col);
        session.setAttribute("enemies", enemies);
        session.setAttribute("itemsCollected", itemsCollected);

        response.sendRedirect("maze.jsp");
    }
}