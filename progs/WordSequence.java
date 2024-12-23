package progs;

public class WordSequence {

    static int dfs(char[][] matrix, int i, int j, char[] word, int wi, int cnt, boolean prevRight) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || wi > word.length || cnt < 0)
            return 0;
        if (wi == word.length)
            return 1;

        int res = 0;
        if (matrix[i][j] == word[wi]) {
            if (prevRight) {
                res += dfs(matrix, i, j + 1, word, wi + 1, cnt, prevRight);
                res += dfs(matrix, i + 1, j, word, wi + 1, cnt - 1, !prevRight);
            } else {
                res += dfs(matrix, i, j + 1, word, wi + 1, cnt - 1, !prevRight);
                res += dfs(matrix, i + 1, j, word, wi + 1, cnt, prevRight);
            }
            //res |= dfs( matrix, i, j+1, word, wi+1, cnt, prevRight)
            //res |= dfs( matrix, i, j+1, word, wi+1, cnt, prevRight)
        }
        return res;
    }

    static boolean dfs1(char[][] matrix, int i, int j, char[] word, int wi, int cnt, boolean prevRight) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || wi > word.length || cnt < 0)
            return false;
        if (wi == word.length)
            return true;

        boolean res = false;
        if (matrix[i][j] == word[wi]) {
            if (prevRight) {
                res |= dfs1(matrix, i, j + 1, word, wi + 1, cnt, prevRight);
                res |= dfs1(matrix, i + 1, j, word, wi + 1, cnt - 1, !prevRight);
            } else {
                res |= dfs1(matrix, i, j + 1, word, wi + 1, cnt - 1, !prevRight);
                res |= dfs1(matrix, i + 1, j, word, wi + 1, cnt, prevRight);
            }
            //res |= dfs( matrix, i, j+1, word, wi+1, cnt, prevRight)
            //res |= dfs( matrix, i, j+1, word, wi+1, cnt, prevRight)
            return res;
        } else {
            return false;
        }
    }

    static int solution(char[][] matrix, String[] words) {
        int res = 0;
        for (String w : words) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == w.charAt(0)) {
                        res += dfs(matrix, i, j, w.toCharArray(), 0, 1, true);
                        res += dfs(matrix, i, j, w.toCharArray(), 0, 1, false);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'c', 'o', 'd', 'e'},
                {'c', 'd', 'e', 's'},
                {'c', 'e', 'e', 'i'},
        };
        String[] words = {"code", "signal", "f"};
        int res = solution(matrix, words);
        System.out.println(res);
    }

}

