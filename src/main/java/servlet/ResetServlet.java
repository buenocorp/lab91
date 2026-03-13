package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        // Gera labirinto novamente
        int size = 50;
        Integer[][] maze = new Integer[size][size];
        for(int i=0;i<size;i++) Arrays.fill(maze[i],1);

        generateMaze(maze,0,0,new Random());

        // Cria lista de células livres
        List<int[]> freeCells = new ArrayList<>();
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                if(maze[i][j]==0) freeCells.add(new int[]{i,j});

        Random rand = new Random();

        // Coloca itens
        List<int[]> items = new ArrayList<>();
        Collections.shuffle(freeCells, rand);
        for(int k=0;k<10 && k<freeCells.size();k++){
            int[] pos = freeCells.get(k);
            maze[pos[0]][pos[1]] = 2;
            items.add(pos);
        }

        // Coloca inimigos
        List<int[]> enemies = new ArrayList<>();
        Collections.shuffle(freeCells, rand);
        int placed = 0;
        for(int[] pos: freeCells){
            boolean used=false;
            for(int[] item: items) if(item[0]==pos[0] && item[1]==pos[1]) used=true;
            if(!used){
                enemies.add(new int[]{pos[0],pos[1]});
                placed++;
            }
            if(placed>=5) break;
        }

        // Salva na sessão
        session.setAttribute("maze", maze);
        session.setAttribute("enemies", enemies);
        session.setAttribute("items", items);

        // Redireciona para o labirinto
        response.sendRedirect("maze.jsp");
    }

    private void generateMaze(Integer[][] maze, int r, int c, Random rand){
        int size = maze.length;
        maze[r][c]=0;
        int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};
        List<int[]> list=Arrays.asList(dirs);
        Collections.shuffle(list,rand);
        for(int[] d:list){
            int nr=r+d[0]*2;
            int nc=c+d[1]*2;
            if(nr>=0 && nr<size && nc>=0 && nc<size && maze[nr][nc]==1){
                maze[r+d[0]][c+d[1]]=0;
                generateMaze(maze,nr,nc,rand);
            }
        }
    }
}