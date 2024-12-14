package nekonic.utils;

/*
public class Graph {
    public void drowGraph(){
        int[] data = {100, 112, 44, 56, 77, 98, 103};
	    int[] yData = new int[7];
		int dataSize = data.length;

        // 최소값과 최대값 계산
        int min = data[0];
        int max = data[0];
        for (int n : data) {
            if (n < min) min = n;
            if (n > max) max = n;
        }

        // 그리드 크기 설정
        int width = 14;
        int height = 10;

        // 그리드 초기화
        int[][] grid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = 0;
            }
        }

        // x축 위치 계산 (데이터를 2칸마다 배치)
        int[] xPositions = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            xPositions[i] = i * 2; // x 위치: 1, 3, 5, ..., 13
        }

        // 데이터 포인트를 그리드에 매핑
        for (int i = 0; i < dataSize; i++) {
            int n = data[i];
            // y 좌표 계산: (n - min) / (max - min) * 9 후 반올림
            int y = (int) Math.round(((double)(n - min) / (max - min)) * (height - 1));
            int x = xPositions[i];
            int yPos = height - 1 - y; // 그리드의 y 좌표는 위에서 아래로 증가하므로 반전
            yData[i] = yPos;

            grid[yPos][x] = 1;
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        for (int i=1; i<7; i++){
            int x = xPositions[i-1]+1;
            int y1 = yData[i-1];
            int y2 = yData[i];
            if(y1>y2){
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            if(y2-y1<2)grid[y1][x]=1;
            for (int j=y1+1; j<y2; j++){
                grid[j][x]=1;
            }
        }

        System.out.println("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }

        for (int i : yData)System.out.println(i);

        System.out.println();
        System.out.println(grid[0][0]);
        System.out.println(grid[0][1]);
        System.out.println(grid[1][0]);
        System.out.println(grid[1][1]);

        for (int i=0; i<5; i++) {
            for(int j=0; j<7; j++){
                int modleNum = 0;
                modleNum += grid[i*2][j*2]<<3;
                modleNum += grid[i*2][j*2+1]<<2;
                modleNum += grid[i*2+1][j*2]<<1;
                modleNum += grid[i*2+1][j*2+1];
                System.out.print(modleNum+" ");
            }System.out.println();
        }
    }
}
*/
