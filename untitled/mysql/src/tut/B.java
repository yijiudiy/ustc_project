package tut;

import java.util.*;

public class Main {

    static class Node{
        int x;
        int y;
        int key;
        int step;
        public Node(int x,int y,int key,int step){
            this.x=x;
            this.y=y;
            this.key=key;
            this.step=step;
        }
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();
        int M=in.nextInt();
        in.nextLine();
        char[][] G=new char[N][M];
        for(int i=0;i<N;i++){
            G[i]=in.nextLine().toCharArray();
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(G[i][j]=='2'){
                    System.out.println(bfs(i,j,N,M,G));
                    return;
                }
            }
        }
    }
    private static int bfs(int si, int sj,int N,int M,char[][] G) {
        Queue<Node> queue=new LinkedList<>();
        int[][][] mp=new int[101][101][1025];
        int[][] next={{-1,0},{0,-1},{1,0},{0,1}};

        queue.offer(new Node(si,sj,0,0));
        while(!queue.isEmpty()){
            Node node=queue.poll();
            for(int i=0;i<4;i++){
                int x=node.x+next[i][0];
                int y=node.y+next[i][1];
                int key=node.key;
                if(x<0||x>=N||y<0||y>=M||G[x][y]=='0') continue;
                else if(G[x][y]=='3') return node.step+1;
                else if(G[x][y]<='z'&&G[x][y]>='a') key=key|(1<<(G[x][y]-'a'));
                else if(G[x][y]<='Z'&&G[x][y]>='A'&&(key&(1<<(G[x][y]-'A')))==0) continue;
                if(mp[x][y][key]==0){
                    mp[x][y][key]=1;
                    queue.offer(new Node(x,y,key,node.step+1));
                }

            }
        }
        return -1;
    }

}


编辑于 2017-08-07 01:17:32 回复(5)
        2

        chorifa头像chorifa
        1
        2
        3
        4
        5
        6
        7
        8
        9
        10
        11
        12
        13
        14
        15
        16
        17
        18
        19
        20
        21
        22
        23
        24
        25
        26
        27
        28
        29
        30
        31
        32
        33
        34
        35
        36
        37
        38
        39
        40
        41
        42
        43
        44
        45
        46
        47
        import java.util.*;
// 使用带有计数的层序遍历，求得最短根叶长度
// 带有附加钥匙限制的情况下，用更高维的数组记录是否访问过
// 该题实际字母字符不会超过j
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        char[][] maze = new char[m][n];
        sc.nextLine();
        for(int i = 0; i < m; i++) maze[i] = sc.nextLine().toCharArray();
        sc.close();
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(maze[i][j] == '2') {System.out.println(solution(maze,i,j)); return;}
    }

    private static int solution(char[][] maze, int startX, int startY){
        int res = 0;
        int m = maze.length, n = maze[0].length;
        boolean[][][] isVisted = new boolean[m][n][1024];
        isVisted[startX][startY][0] = true;
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startX); queue.offer(startY); queue.offer(0);
        while(!queue.isEmpty()){
            int num = queue.size()/3; // 带有计数的层序遍历
            res++; // 层数
            while(num > 0){
                startX = queue.poll(); startY = queue.poll(); int k = queue.poll();
                num--;
                for(int i = 0; i < 4; i++){
                    int x = startX + dir[i][0]; int y = startY + dir[i][1]; int key = k;
                    if(x<0 || x>=m || y<0 || y>=n || maze[x][y] == '0') continue;
                    else if(maze[x][y] == '3') return res;
                    else if(maze[x][y] <= 'j' && maze[x][y] >= 'a') key = key | 1 << maze[x][y]-'a';
                    else if(maze[x][y] <= 'J' && maze[x][y] >= 'A' && (key & 1 << maze[x][y]-'A') == 0) continue;
                    if(isVisted[x][y][key] == false){ // 注意不能加else 也不能加 == '1'，否则缺少小写字符的情况
                        isVisted[x][y][key] = true;
                        queue.offer(x); queue.offer(y); queue.offer(key);
                    }
                }
            }
        }
        return -1;
    }
}
