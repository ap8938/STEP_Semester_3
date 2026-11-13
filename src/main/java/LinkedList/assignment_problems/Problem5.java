
public class Problem5 {

    public static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];

        // Initially, all cells have distance -1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = -1;
            }
        }

        // Queue stores cell positions
        int[] queue = new int[rows * cols];
        int front = 0;
        int rear = 0;

        // Add all exits to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (plan[i][j] == 'E') {
                    distance[i][j] = 0;
                    queue[rear++] = i * cols + j;
                }
            }
        }

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // BFS
        while (front < rear) {
            int position = queue[front++];

            int r = position / cols;
            int c = position % cols;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // Check boundaries and walls
                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    plan[nr][nc] != '#' &&
                    distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;

                    queue[rear++] = nr * cols + nc;
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
