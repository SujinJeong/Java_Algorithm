package BOJ.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 참고 : https://gyuyoungcho.github.io/posts/algorithm/study13/%EB%B0%B1%EC%A4%80_9177_%EB%8B%A8%EC%96%B4%ED%94%84%EC%A6%90/

public class Q9177 {
    static String a,b,c;
    static int N,as,bs,cs;
    static boolean visit[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            sb.append("Data set ").append(i).append(": ");
            st = new  StringTokenizer(br.readLine());
            a = st.nextToken();
            b = st.nextToken();
            c = st.nextToken();
            as = a.length();bs = b.length();cs=c.length();
            visit = new boolean[as+1][bs+1]; // 현재까지 a,b에서 찾은 문자열 idx저장
            sb.append(solve(0,0,0)?"yes":"no").append("\n");
        }
        System.out.print(sb);
    }
    private static boolean solve(int i, int j,int cnt) {

        // 끝까지 탐색완료
        if(cnt==cs) return true;

        if(visit[i][j]) return false;
        visit[i][j] = true;

        boolean flag = false;
        if(i<as && a.charAt(i)==c.charAt(cnt))
            // true 리턴해주는 순간부터 계속 true를 리턴할 수 있게끔 or 연산자
            flag|=solve(i+1,j,cnt+1);

        if(j<bs &&b.charAt(j)==c.charAt(cnt))
            flag|=solve(i,j+1,cnt+1);

        return flag;
    }
}