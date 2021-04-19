import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 한쪽 방향으로 옮겼을 때
// 같은 값은 합치는 여부를 판단하기 위한 class
class Num {
	int su;
	boolean check;

	Num(int su, boolean check) {
		this.su = su;
		this.check = check;
	}
}

public class BOJ12100 {
	static int result = 0;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, map);
		System.out.println(result);
	}

	// DFS로 탐색
	static void solve(int depth, int[][] map) {
		if (depth == 5) {                        //5번 시행했을 때는 리턴하고 가장 큰값 저장
			int val = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > val) {
						val = map[i][j];
					}
				}
			}
			result = Math.max(result, val);
			return;
		}

		// 인덱스는 숫자 순서대로 위,오른쪽,아래,왼쪽 0123       //방향으로 탐색해서 들어감
 		for (int i = 0; i < 4; i++) {
			solve(depth + 1, rotate(i, map));
		}
	}

	static int[][] rotate(int idx, int[][] copyMap) {
		Stack<Num> stack = new Stack<>();  //스택을 통해서 값들을 쌓아두고
		int[][] result = new int[N][N];    // result 이차원배열에 복사후 리턴
		switch (idx) {    //idx값에 따라 스위치문으로 구분해줌
		case 0:              // 위
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[j][i] != 0) {
						if (!stack.isEmpty()) {
							if (stack.peek().su == copyMap[j][i] && !stack.peek().check) {
								stack.pop();
								stack.push(new Num(copyMap[j][i] * 2, true));
							} else {
								stack.push(new Num(copyMap[j][i], false));
							}
						} else {
							stack.push(new Num(copyMap[j][i], false));
						}
					}
				}
				if (!stack.isEmpty()) {    // 비어있는 지 판단
					int size = stack.size() - 1;
					for (int s = size; s >= 0; s--) {
						result[s][i] = stack.pop().su;
					}
				}
			}
			break;
		case 1:    //오른쪽
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (copyMap[i][j] != 0) {
						if (!stack.isEmpty()) {
							if (stack.peek().su == copyMap[i][j] && !stack.peek().check) {
								stack.pop();
								stack.push(new Num(copyMap[i][j] * 2, true));
							} else {
								stack.push(new Num(copyMap[i][j], false));
							}
						} else {
							stack.push(new Num(copyMap[i][j], false));
						}
					}
				}
				if (!stack.isEmpty()) {
					int size = stack.size() - 1;
					for (int s = size; s >= 0; s--) {
						result[i][N - 1 - s] = stack.pop().su;
					}
				}
			}
			break;
		case 2:   //아래
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (copyMap[j][i] != 0) {
						if (!stack.isEmpty()) {
							if (stack.peek().su == copyMap[j][i] && !stack.peek().check) {
								stack.pop();
								stack.push(new Num(copyMap[j][i] * 2, true));
							} else {
								stack.push(new Num(copyMap[j][i], false));
							}
						} else {
							stack.push(new Num(copyMap[j][i], false));
						}
					}
				}
				if (!stack.isEmpty()) {
					int size = stack.size() - 1;
					for (int s = size; s >= 0; s--) {
						result[N - 1 - s][i] = stack.pop().su;
					}
				}

			}
			break;
		case 3:  //왼쪽
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[i][j] != 0) {
						if (!stack.isEmpty()) {
							if (stack.peek().su == copyMap[i][j] && !stack.peek().check) {
								stack.pop();
								stack.push(new Num(copyMap[i][j] * 2, true));
							} else {
								stack.push(new Num(copyMap[i][j], false));
							}
						} else {
							stack.push(new Num(copyMap[i][j], false));
						}
					}
				}
				if (!stack.isEmpty()) {
					int size = stack.size() - 1;
					for (int s = size; s >= 0; s--) {
						result[i][s] = stack.pop().su;
					}
				}
			}
			break;
		}
		return result;        // 리턴
	}

	// 이차원배열 확인을 위한 함수
//	static void printArr(int[][] arr) {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
}