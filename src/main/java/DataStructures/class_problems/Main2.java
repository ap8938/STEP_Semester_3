public class Main2 {

    public static String warehouseSummary(int[][] grid){
        int total = 0;
        int max = -1;
        int maxRow = 0;
        int maxCol = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                total += grid[i][j];

                if (grid[i][j] > max){
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }
        return "total = " + total + ",maxCoordinate = (" + maxRow + "," + maxCol + ")";
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        System.out.println(warehouseSummary(grid));
    }
}