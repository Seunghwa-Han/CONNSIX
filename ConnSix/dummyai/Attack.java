import java.awt.Point;
import java.util.ArrayList;

public class Attack {
	static int num_mine = 0;
	static int num_empty = 0;
	static int put_count = 0;

//double attack 공격 	
	public static void putStone(int[][] result, ArrayList<Point> vcCom, ArrayList<Integer> coor) {
		int[][] ar = new int[19][19];
		System.out.println("result");
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				ar[i][j] = result[i][j];
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
		atk1(ar, vcCom, result, coor);
		if (put_count == 2) {
			put_count = 0;
			return;
		}
		atk2(ar, vcCom, result, coor);
		if (put_count == 2) {
			put_count = 0;
			return;
		}
		atk3(ar, vcCom, result, coor);
		if (put_count == 2) {
			put_count = 0;
			return;
		}
		atk4(ar, vcCom, result, coor);
		if (put_count == 2) {
			put_count = 0;
			return;
		}
//		System.out.println("attack");
//		for(int i=0; i<19; i++) {
//			for(int j=0; j<19; j++) {
//				System.out.print(ar[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	public static void atk1(int[][] ar, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) { // 가로 방향.

		int i = 0;
		int j = 0;
		int k = 0;
		System.out.println("atk1");
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				if (ar[i][j] == 0 && ar[i][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i, j + k);
					}
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i, j + k, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						}
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i, j + k, vcCom, resultt, coor);
						}
						put_count += 2;
//						put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, i, j + k, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}

			}
		}
	}

	public static void atk2(int[][] ar, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) { // 세로 방향.

		int i = 0;
		int j = 0;
		int k = 0;
		System.out.println("atk2");
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				if (ar[j][i] == 0 && ar[j + 5][i] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, j + k, i);
					}
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, j + k, i, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						}
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, j + k, i, vcCom, resultt, coor);
						}
						put_count += 2;
//						put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new2");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, j + k, i, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}

			}
		}

	}

	public static void atk3(int[][] ar, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) { // / 방향.

		int i = 0;
		int j = 0;
		int k = 0;
		System.out.println("atk3");
		for (i = 5; i < 19; i++) {
			for (j = 0; j < i - 4; j++) {
				if (ar[i - j][j] == 0 && ar[i - j - 5][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i - j - k, j + k);
					}
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i - j - k, j + k, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						}
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i - j - k, j + k, vcCom, resultt, coor);
						}
						put_count += 2;
//						put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, i - j - k, j + k, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}

			}
		}

		for (i = 1; i < 14; i++) {
			for (j = 0; j < 14 - i; j++) {
				if (ar[18 - j][i + j] == 0 && ar[18 - j - 5][i + j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, 18 - j - k, i + j + k);
					}
//					System.out.println("num_mine " + num_mine + "  num_empty " + num_empty);
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, 18 - j - k, i + j + k, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						} 
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, 18 - j - k, i + j + k, vcCom, resultt, coor);
						}
						put_count += 2;
//						put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, 18 - j - k, i + j + k, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}

			}
		}
	}

	public static void atk4(int[][] ar, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) { // \ 방향.

		int i = 0;
		int j = 0;
		int k = 0;
		System.out.println("atk4");
		for (i = 1; i < 15; i++) {
			for (j = 0; j < i; j++) {
				if (ar[j][14 - i + j] == 0 && ar[j + 5][14 - i + j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, j + k, 14 - i + j + k);
					}
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, j + k, 14 - i + j + k, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						}
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, j + k, 14 - i + j + k, vcCom, resultt, coor);
						}
						put_count += 2;
//							put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, j + k, 14 - i + j + k, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}

			}
		}

		for (i = 1; i < 14; i++) {
			for (j = 0; j < 14 - i; j++) {
				if (ar[i + j][j] == 0 && ar[i + j + 5][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i + j + k, j + k);
					}
					if ((num_mine == 3) && (num_empty == 1)) {
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i + j + k, j + k, vcCom, resultt, coor);
						}
						put_count++;
						if (put_count == 2) {
//							put_count = 0 ;
							return;
						}
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 0)) { // 2수 두기.
						for (k = 1; k < 5; k++) {
							fill_2x2(ar, i + j + k, j + k, vcCom, resultt, coor);
						}
						put_count += 2;
//						put_count = 0 ;
						return;
					} else if ((num_mine == 2) && (num_empty == 2) && (put_count == 1)) {
						System.out.println("new");
						int ct = 0;
						for (k = 1; k < 6; k++) {
							ct = fill_2x1(ar, i + j + k, j + k, vcCom, resultt, ct, coor);
							if (ct == 3)
								break;
						}
						put_count++;
						return;
					}
				}
			}
		}

	}

	public static void check_2x2(int[][] ar, int i, int j) {
		if (ar[i][j] == 2)
			num_mine++;
		if (ar[i][j] == 0)
			num_empty++;
	}

	public static void fill_2x2(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) {
		if (ar[i][j] <= 0) {
			ar[i][j] = 2;
			resultt[i][j] = 2;
			coor.add(i); coor.add(j);
			vcCom.add(new Point((int) (j * 41.7 + 26), (int) (i * 41.6 + 28)));
		}
	}

	public static int fill_2x1(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] resultt, int ct, ArrayList<Integer> coor) {

		if (ct == 2 && ar[i][j] <= 0) {
			ar[i][j] = 2;
			resultt[i][j] = 2;
			coor.add(i); coor.add(j);
			vcCom.add(new Point((int) (j * 41.7 + 26), (int) (i * 41.6 + 28)));
			return 3;
		}

		if (ar[i][j] == 2) {
			ct++;
		}
		return ct;
	}

	public static void reset() {
		num_mine = 0;
		num_empty = 0;
	}
}
