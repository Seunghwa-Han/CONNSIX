import java.awt.Point;
import java.util.ArrayList;

public class PrimaryProtect {
	
	static int num_mine = 0;
	static int num_empty = 0;
	static int put_count = 0;
	
	public static void detectPrimaryProtect(ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) {
		int[][] ar = new int[19][19];
		for(int i=0;i<19; i++) {
			for(int j=0;j<19;j++)
				ar[i][j] = result[i][j];
		}
		avoid_protect1(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		avoid_protect2(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		avoid_protect3(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		avoid_protect4(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
	}
	
	public static void avoid_protect1(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향 위험 예지.
		int i = 0;
		int j = 0;
		int k = 0;
		if(put_count == 2) return ;
		System.out.println("a_th1") ;
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				check2_3(ar, i, j);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(j + k > 18) break ;
						if (ar[i][j + k] <= 0) {
							if (depend_th2(ar, i, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th3(ar, i, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th4(ar, i, j + k, vcCom, result, coor) > 1) {
								return;
							}
						}

					}

				}

			}

		}

	}

	public static void avoid_protect2(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향 위험 예지.
		int i = 0;
		int j = 0;
		int k = 0;
		if(put_count == 2) return ;

		System.out.println("a_th2") ;
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				check2_4(ar, j, i);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(j + k > 18) break ;
						if (ar[j + k][i] <= 0) {
							if (depend_th1(ar, j + k, i, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th3(ar, j + k, i, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th4(ar, j + k, i, vcCom, result, coor) > 1) {
								return;
							} if(put_count == 2) return ;
						}

					}

				}

			}

		}

	}

	public static void avoid_protect3(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // / 방향 위험 예지.
		int i = 0;
		int j = 0;
		int k = 0;
		if(put_count == 2) return ;
		System.out.println("a_th3") ;
		for (i = 5; i < 19; i++) {
			for (j = 0; j < i - 4; j++) {
				check2_1(ar, i - j, j);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(i - j - k < 0 || j + k > 18) break ;
						if (ar[i - j - k][j + k] <= 0) {
							if (depend_th1(ar, i - j - k, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th2(ar, i - j - k, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th4(ar, i - j - k, j + k, vcCom, result, coor) > 1) {
								return;
							} if(put_count == 2) return ;
						}
					}
				}
			}
		}
		for (i = 1; i < 14; i++) {
			for (j = 0; j < 14 - i; j++) {
				check2_1(ar, 18 - j, i + j);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(18 - j - k < 0 || i + j + k > 18) break ;
						if (ar[18 - j - k][i + j + k] <= 0) {
							if (depend_th1(ar, 18 - j - k, i + j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th2(ar, 18 - j - k, i + j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th4(ar, 18 - j - k, i + j + k, vcCom, result, coor) > 1) {
								return;
							} if(put_count == 2) return ;
						}
					}
				}
			}
		}

	}

	public static void avoid_protect4(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // \ 방향 위험 예지.
		int i = 0;
		int j = 0;
		int k = 0;
		if(put_count == 2) return ;
		System.out.println("a_th4") ;
		for (i = 1; i < 15; i++) {
			for (j = 0; j < i; j++) {
				check2_2(ar, j, 14 - i + j);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(j - k < 0 || 14 - i + j - k < 0 ) break ;
						if (ar[j - k][14 - i + j - k] <= 0) {
							if (depend_th1(ar, j + k, 14 - i + j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th2(ar, j + k, 14 - i + j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th3(ar, j + k, 14 - i + j + k, vcCom, result, coor) > 1) {
								return;
							} if(put_count == 2) return ;
						}
					}
				}
			}
		}
		for (i = 1; i < 14; i++) {
			for (j = 0; j < 14 - i; j++) {
				check2_2(ar, i + j, j);
				if (num_mine == 3 && num_empty == 3) {
					for (k = 0; k < 6; k++) {
						if(i + j + k > 18 || j + k > 18) break ;
						if (ar[i + j + k][j + k] <= 0) {
							if (depend_th1(ar, i + j + k, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th2(ar, i + j + k, j + k, vcCom, result, coor) > 1) {
								return;
							} else if (depend_th3(ar, i + j + k, j + k, vcCom, result, coor) > 1) {
								return;
							} if(put_count == 2) return ;
						}
					}
				}
			}
		}

	}

	
	public static int depend_th1(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향 찾기.
		int count = 0;
		int k = 0;
		// 좌측 검색.
		if (put_count == 2)
			return 2;

		for (k = 1; k < 4; k++) {
			if (j - k < 0) {
				count = 0;
				break;
			}
			if (ar[i][j - k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}

		// 우측 검색.
		for (k = 1; k < 4; k++) {
			if (j + k > 18) {
				count = 0;
				break;
			}
			if (ar[i][j + k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j + k, vcCom, result, coor);
			return 2;
		}

		// 전체 범위 검색.
		for (k = -3; k < 4; k++) {
			if (j + k > 18 || j + k < 0) {
				count = 0;
				break;
			}
			if (ar[i][j + k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}
		return count;
	}

	public static int depend_th2(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향 찾기.
		int count = 0;
		int k = 0;
		// 상단 검색.
		if (put_count == 2)
			return 2;
		for (k = 1; k < 4; k++) {
			if (i - k < 0) {
				count = 0;
				break;
			}
			if (ar[i - k][j] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}
		// 하단 검색.
		for (k = 1; k < 4; k++) {
			if (i + k > 18) {
				count = 0;
				break;
			}
			if (ar[i + k][j] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}

		// 전체 검색.
		for (k = -3; k < 4; k++) {
			if (i + k > 18 || i + k < 0) {
				count = 0;
				break;
			}
			if (ar[i + k][j] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}

		return count;
	}

	public static int depend_th3(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // / 방향 찾기.
		int count = 0;
		int k = 0;
		// 우측 상단 검색.
		if (put_count == 2)
			return 2;
		for (k = 1; k < 4; k++) {
			if (i - k < 0 || j + k > 18) {
				count = 0;
				break;
			}
			if (ar[i - k][j + k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}

		// 좌측 하단 검색.
		for (k = 1; k < 4; k++) {
			if (i + k > 18 || j - k < 0) {
				count = 0;
				break;
			}
			if (ar[i + k][j - k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}
		
		for (k = -3; k < 4; k++) {
			if (i + k > 18 || i + k < 0 || j - k > 18 || j - k < 0) {
				count = 0;
				break;
			}
			if (ar[i + k][j - k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor) ;
			return 2;
		}

		return count;
	}

	public static int depend_th4(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // \ 방향 찾기.
		int count = 0;
		int k = 0;
		if (put_count == 2)
			return 2 ;
		// 좌측 상단 검색.
		for (k = 1; k < 4; k++) { // 세로 방향 찾기.
			if (i - k < 0 || j - k < 0) {
				count = 0;
				break;
			}
			if (ar[i - k][j - k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}
		// 우측 하단 검색.
		for (k = 1; k < 4; k++) {
			if (i + k > 18 || j + k > 18) {
				count = 0;
				break;
			}
			if (ar[i + k][j + k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}
		
		// 전체 범위 검색.
		for (k = -3; k < 4; k++) {
			if (i + k > 18 || i + k < 0 || j + k > 18 || j + k < 0) {
				count = 0;
				break;
			}
			if (ar[i + k][j + k] == 1) {
				count++;
			}
		}
		if (count > 1) {
			fill_2x2(ar, i, j, vcCom, result, coor);
			return 2;
		}

		return count;
	}
	
	public static void fill_2x2(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) {
		if (ar[i][j] <= 0) {
			put_count++ ;
			ar[i][j] = 2;
			resultt[i][j] = 2;
			coor.add(i); coor.add(j);
			vcCom.add(new Point((int) (j * 41.7 + 26), (int) (i * 41.6 + 28)));
		}
	}

	public static void check2_1(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i - 1, j + 1);
		check(find_state, i - 2, j + 2);
		check(find_state, i - 3, j + 3);
		check(find_state, i - 4, j + 4);
		check(find_state, i - 5, j + 5);

	}

	public static void check2_2(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i + 1, j + 1);
		check(find_state, i + 2, j + 2);
		check(find_state, i + 3, j + 3);
		check(find_state, i + 4, j + 4);
		check(find_state, i + 5, j + 5);
	}

	public static void check2_3(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i, j + 1);
		check(find_state, i, j + 2);
		check(find_state, i, j + 3);
		check(find_state, i, j + 4);
		check(find_state, i, j + 5);

	}

	public static void check2_4(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i + 1, j);
		check(find_state, i + 2, j);
		check(find_state, i + 3, j);
		check(find_state, i + 4, j);
		check(find_state, i + 5, j);

	}

	public static void check(int[][] find_state, int i, int j) {

		if (find_state[i][j] == 2) {
			num_mine++;
		} else if (find_state[i][j] <= 0) {
			num_empty++;
		}
	}

	public static void reset() {
		num_mine = 0;
		num_empty = 0;
	}
}
