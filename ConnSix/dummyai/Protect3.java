import java.awt.Point;
import java.util.ArrayList;

public class Protect3 {
	
	static int put_count=0;
	static int num_enemy=0;
	static int num_empty=0;
	static int count_3 =0;
	static int fx=0,fy=0;  //시작 공백 
	
	public static void protectThree(int[][] result, ArrayList<Point> vcCom, ArrayList<Integer> coor) {
		count_3=0;
		int[][] ar = new int[19][19];
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++)
				ar[i][j] = result[i][j];
		}
		
		find3_1(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		find3_2(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		find3_3(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		find3_4(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		
	}
	
	public static void find3_1(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향.
		int i = 0;
		int j = 0;
		int k = 0;
		if (put_count == 2)
			return;
		if (count_3 == 2)
			return;
		Loop1: for (i = 0; i < 19; i++) {
			Loop2: for (j = 0; j < 14; j++) {
				if (ar[i][j] == 0 && ar[i][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i, j + k);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = i;
							fy = j + k;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, i, j + k, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
	}

	public static void find3_2(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향.
		int i = 0;
		int j = 0;
		int k = 0;
		if (put_count == 2)
			return;
		if (count_3 == 2)
			return;
		Loop1: for (i = 0; i < 19; i++) {
			Loop2: for (j = 0; j < 14; j++) {
				if (ar[j][i] == 0 && ar[j + 5][i] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, j + k, i);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = j + k;
							fy = i;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, j + k, i, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
	}

	public static void find3_3(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향.
		int i = 0;
		int j = 0;
		int k = 0;
		if (put_count == 2)
			return;
		if (count_3 == 2)
			return;
		Loop1: for (i = 5; i < 19; i++) {
			Loop2: for (j = 0; j < i - 4; j++) {
				if (ar[i - j][j] == 0 && ar[i - j - 5][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i - j - k, j + k);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = i - j - k;
							fy = j + k;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, i - j - k, j + k, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
		Loop1: for (i = 1; i < 14; i++) {
			Loop2: for (j = 0; j < 14 - i; j++) {
				if (ar[18 - j][i + j] == 0 && ar[18 - j - 5][i + j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, 18 - j - k, i + j + k);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = 18 - j - k;
							fy = i + j + k;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, 18 - j - k, i + j + k, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
	}
	
	public static void find3_4(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향.
		int i = 0;
		int j = 0;
		int k = 0;
		if (put_count == 2)
			return;
		if (count_3 == 2)
			return;
		Loop1: for (i = 1; i < 15; i++) {
			Loop2: for (j = 0; j < i; j++) {
				if (ar[j][14 - i + j] == 0 && ar[j + 5][14 - i + j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, j + k, 14 - i + j + k);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = j + k;
							fy = 14 - i + j + k;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, j + k, 14 - i + j + k, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
		Loop1: for (i = 1; i < 14; i++) {
			Loop2: for (j = 0; j < 14 - i; j++) {
				if (ar[i + j][j] == 0 && ar[i + j + 5][j + 5] == 0) { // 양쪽 끝이 공백인지 확인한다.
					reset();
					for (k = 1; k < 5; k++) { // 공백 사이의 4칸 확인.
						check_2x2(ar, i + j + k, j + k);
					}
					if ((num_enemy == 3) && (num_empty == 1)) { // 적공 3,빈칸 1 .
						count_3++;
						if (count_3 == 1) {
							fx = i + j + k ;
							fy = j + k ;
						} else if (count_3 == 2) {
							if (put_count == 0) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
								fill_2x2(ar, i + j + k, j + k, vcCom, result, coor);
							} else if (put_count == 1) {
								fill_2x2(ar, fx, fy, vcCom, result, coor);
							}
							break Loop1;
						}
						j += 4;
						if (j > 13) {
							break Loop2;
						}
					}
				}
			}
		}
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
	
	public static void reset() {
		num_enemy = 0;
		num_empty = 0;
	}
	
	public static void check_2x2(int[][] ar, int i, int j) {
		if (ar[i][j] == 1)
			num_enemy++;
		if (ar[i][j] == 0)
			num_empty++;
	}
}
