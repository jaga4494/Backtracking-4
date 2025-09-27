
// TC: BFS takes HW time as can process every cell in grid. (HW P n) - permutation - placing 2 building in HW positions. for each of them we run bfs. therefore HW* (HW P n) total time
// SC: grid - HW, queue and visited - hw. So HW

public class Main {
    
    static class BuildingPlacement {
        int[][] grid;
        int h;
        int w;
        int n;
        int minDistance;
        
        BuildingPlacement(int h, int w, int n) {
            this.h = h;
            this.w = w;
            this.n = n;
            grid = new int[h][w];
            minDistance = Integer.MAX_VALUE;
        }
        
        public int findMinDistance() {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            
            backtrack(0, 0, n);
            return minDistance;
        }
        
        private void backtrack(int height, int width, int n) {
            
            if (n == 0) {
                minDistance = Math.min(minDistance, findDistance());
                return;
            }
            
            for (int i = height; i < h; ++i) {
                for (int j = width; j < w; ++j) {
                    if (grid[i][j] == 0) { // not necessary as we keep moving forward
                        grid[i][j] = 1;
                        backtrack(i, j, n - 1);
                        grid[i][j] = 0;
                    }
                    
                }
                // start from first column after completing the previous row.
                width = 0;
            }
        }
        
        private int findDistance() {
            boolean[][] visited = new boolean[h][w];
            
            Queue<int[]> q = new LinkedList<>();
            
            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    if (grid[i][j] == 1) {
                        visited[i][j] = true;
                        q.add(new int[] {i, j});
                    }
                }
            }
            
            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                
                for (int i = 0; i < size; ++i) {
                    int[] cur = q.poll();
                    for (int[] dir: dirs) {
                        int nr = cur[0] + dir[0];
                        int nc = cur[1] + dir[1];
                        
                        if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            q.add(new int[] {nr, nc});
                        }
                    }
                }
                level++;
            }
            return level - 1;
        }
        
    }
    public static void main(String[] args) {
        
        // BuildingPlacement bp = new BuildingPlacement(4, 4, 3);
        BuildingPlacement bp = new BuildingPlacement(5, 4, 2);
        
        System.out.println(bp.findMinDistance());
    }
}