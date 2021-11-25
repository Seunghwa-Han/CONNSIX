import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Util {
	
	public static String mkCoord(int m1, int n1, int m2, int n2) {
		/*
		char M1 = (char) ('A' + m1) ;
		char M2 = (char) ('A' + m2) ;
		
		if(M1 >= 'I')
			M1++ ;
		if(M2 >= 'I')
			M2++ ;
		
		n1++ ;
		n2++ ;
		*/
		char M1 = (char) ('A' + n1) ;
		char M2 = (char) ('A' + n2) ;
		if(M1 >= 'I')
			M1++ ;
		if(M2 >= 'I')
			M2++ ;
		m1++; m2++;
		m1 = 20-m1; m2 = 20-m2;
		
		String result = String.format("%c%02d:%c%02d", M1, m1, M2, m2);
		
		return result ;
	}
	
	public static String mkCoordAL(ArrayList<Integer> list) {
		/*
		char M1 = (char) ('A' + list.get(0)) ;
		char M2 = (char) ('A' + list.get(2)) ;
		
		if(M1 >= 'I')
			M1++ ;
		if(M2 >= 'I')
			M2++ ; */
		char M1 = (char) ('A' + list.get(1)) ;
		char M2 = (char) ('A' + list.get(3)) ;
		if(M1 >= 'I')
			M1++ ;
		if(M2 >= 'I')
			M2++ ;
		int m1 = list.get(0);
		int m2 = list.get(2);
		m1++; m2++;
		m1 = 20-m1; m2 = 20-m2;
		
		String result = String.format("%c%02d:%c%02d", M1, m1, M2, m2);
		//String result = String.format("%c%02d:%c%02d", M1, list.get(1) + 1, M2, list.get(3) + 1);
		
		return result ;
	}
	
	public static ArrayList<Integer> mkCoor(String read) {
		String parse[] = read.split(":") ;
		ArrayList<Integer> list = new ArrayList<Integer>() ;
		
		for(int i = 0 ; i < parse.length ; i++) {
			
			list.add(18 - (Integer.parseInt(parse[i].substring(1, 3)) - 1));
			
			char M = parse[i].charAt(0) ;
			
			if( M > 'I')
				list.add(M - 'A' - 1) ;
			else if(M < 'I')
				list.add(M - 'A') ;
			
			//list.add(Integer.parseInt(parse[i].substring(1, 3)) - 1);
		}
		
		return list ;
	}
	
	public static ArrayList<Integer> setRedStone(String read) {
		String parse[] = read.split(":") ;
		ArrayList<Integer> list = new ArrayList<Integer>() ;
		
		for(int i = 0 ; i < parse.length ; i++) {
			
			list.add(18 - (Integer.parseInt(parse[i].substring(1, 3)) - 1));
			char M = parse[i].charAt(0) ;
			
			if( M > 'I')
				list.add(M - 'A' - 1) ;
			else if(M < 'I')
				list.add(M - 'A') ;
			
			//list.add(Integer.parseInt(parse[i].substring(1, 3)) - 1);
		}
		
		return list ;
	}

	public static void comWhiteFirst(ArrayList<Point> vcCom, ArrayList<Point> dont, int[][] result) {

		Random rnd = new Random();
		int firstX = 0, firstY = 0;
		int secondX = 0, secondY = 0;

		while (true) {
			firstX = rnd.nextInt(3) + 8;
			firstY = rnd.nextInt(3) + 8;
			int check = 0;
//			for (int i = 0; i < dont.size(); i++) {
//				System.out.println(((int) ((dont.get(i).getX() - 26) / 41.7))+" "+((int) ((dont.get(i).getY() - 28) / 41.6)));
//				if (dont.get(i).x == (int)((double) firstY * 41.7 + 26) && dont.get(i).y == (int)(firstX * 41.6 + 28)) {
//					System.out.println("dafsdfasdfsadf");
//					check = 1;
//				}
//			}
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[firstY][firstX] == 3) {
//						System.out.println("a");
						check = 1;
					}
				}
			}
			if (check != 1 && !(firstX == 9 && firstY == 9))
				break;
		}

		while (true) {
			int rand = rnd.nextInt(3);
			if (rand == 0) {
				secondX = firstX + 1;
			} else if (rand == 1) {
				secondX = firstX - 1;
			} else if (rand == 2) {
				secondX = firstX;
			}
			if (secondX >= 8 && secondX <= 10)
				break;
		}
		while (true) {
			int rand = rnd.nextInt(3);
			if (rand == 0) {
				secondY = firstY + 1;
			} else if (rand == 1) {
				secondY = firstY - 1;
			} else if (rand == 2) {
				secondY = firstY;
			}
			int check = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[secondY][secondX] == 3) {
//						System.out.println("b");
						check = 1;
					}
				}
			}
			if (secondY >= 8 && secondY <= 10 && check != 1 && !(secondX == 9 && secondY == 9)
					&& !(secondX == firstX && secondY == firstY))
				break;
		}
//		System.out.println(firstX + " " + firstY + "        " + secondX + " " + secondY);
		vcCom.add(new Point((int) (firstX * 41.7 + 26), (int) (firstY * 41.6 + 28)));
		vcCom.add(new Point((int) (secondX * 41.7 + 26), (int) (secondY * 41.6 + 28)));
		result[firstY][firstX] = 2;
		result[secondY][secondX] = 2;
	}

	public static String comBlackSecond(ArrayList<Point> vcCom, int[][] result, ArrayList<Point> dont) {
		Random rnd = new Random();
		int firstX = 0, firstY = 0;
		int secondX = 0, secondY = 0;

		while (true) {
			firstX = rnd.nextInt(3) + 8;
			firstY = rnd.nextInt(3) + 8;
			int check = 0;
//			for (int i = 0; i < dont.size(); i++) {
//				System.out.println(((int) ((dont.get(i).getX() - 26) / 41.7))+" "+((int) ((dont.get(i).getY() - 28) / 41.6)));
//				if (dont.get(i).x == (int)((double) firstY * 41.7 + 26) && dont.get(i).y == (int)(firstX * 41.6 + 28)) {
//					System.out.println("dafsdfasdfsadf");
//					check = 1;
//				}
//			}
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[firstY][firstX] == 3 || result[firstY][firstX] == 1) {
						check = 1;
					}
				}
			}
			if (check != 1 && !(firstX == 9 && firstY == 9))
				break;
		}

//		while (true) {
		int rand2 = rnd.nextInt(3);
		if (rand2 == 0) {
			secondX = firstX + 1;
		} else if (rand2 == 1) {
			secondX = firstX - 1;
		} else if (rand2 == 2) {
			secondX = firstX;
		}
//			if (secondX >= 8 && secondX <= 10)
//				break;
//		}
		while (true) {
			int rand = rnd.nextInt(3);
			if (rand == 0) {
				secondY = firstY + 1;
			} else if (rand == 1) {
				secondY = firstY - 1;
			} else if (rand == 2) {
				secondY = firstY;
			}
			int check = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[secondY][secondX] == 3 || result[secondY][secondX] == 1) {
						check = 1;
					}
				}
			}
			if (check != 1 && !(secondX == 9 && secondY == 9) && !(secondX == firstX && secondY == firstY))
				break;
		} // secondY >= 8 && secondY <= 10 &&

//		System.out.println(firstX + " " + firstY + "        " + secondX + " " + secondY);
		vcCom.add(new Point((int) (firstX * 41.7 + 26), (int) (firstY * 41.6 + 28)));
		vcCom.add(new Point((int) (secondX * 41.7 + 26), (int) (secondY * 41.6 + 28)));
		result[firstY][firstX] = 2;
		result[secondY][secondX] = 2;
		
		String coord = Util.mkCoord(firstY, firstX, secondY, secondX);
		return coord;

//		int i1 = 0, j1 = 0;
//		int i2 = 0, j2 = 0;
//		int check = 0;
//		for (int i = 0; i < 19; i++) {
//			for (int j = 0; j < 19; j++) {
//				if (!(i == 9 && j == 9) && result[j][i] == 1) {
//					if (check == 0) {
//						i1 = i;
//						j1 = j;
//						check++;
//					} else {
//						i2 = i;
//						j2 = j;
//					}
//				}
//			}
//		}
//
////		System.out.println(j1 + " " + i1 + "        " + j2 + " " + i2);
//		Random rnd = new Random();
//		
//		int d = 0;
//		for (int i = 0; i < 19; i++) {
//			for (int j = 0; j < 19; j++) {
//				if (i >= 8 && i <= 10 && j >= 8 && j <= 10)
//					d = 1;
//			}
//		}
//		if (d != 1) {
//			if (i1 == i2) { // 세로줄
//				if ((j1 == 9 && j2 == 10) || (j1 == 10 && j2 == 9)) {
//					vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//					int rand = rnd.nextInt(2);
//					if (rand == 0) {
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//					} else {
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (11 * 41.6 + 28)));
//					}
//					return;
//				} else if ((j1 == 7 && j2 == 8) || (j1 == 8 && j2 == 7)) {
//					if (i1 != 9) {
//						vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//					} else {
//						int rand = rnd.nextInt(2);
//						if (rand == 0) {
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (7 * 41.6 + 28)));
//						} else {
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (7 * 41.6 + 28)));
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//						}
//					}
//					return;
//				} else if ((j1 == 10 && j2 == 11) || (j1 == 11 && j2 == 10)) {
//					if (i1 != 9) {
//						vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//					} else {
//						int rand = rnd.nextInt(2);
//						if (rand == 0) {
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (11 * 41.6 + 28)));
//						} else {
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (11 * 41.6 + 28)));
//						}
//					}
//					return;
//				} else if ((j1 == 9 && j2 == 8) || (j1 == 8 && j2 == 9)) {
//					vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//					int rand = rnd.nextInt(2);
//					if (rand == 0) {
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (7 * 41.6 + 28)));
//					} else {
//						vcCom.add(new Point((int) (i1 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//					}
//					return;
//				}
//			} else if (j1 == j2) { // 가로줄
//				if ((i1 == 9 && i2 == 10) || (i1 == 10 && i2 == 9)) {
//					vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//					int rand = rnd.nextInt(2);
//					if (rand == 0) {
//						vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					} else {
//						vcCom.add(new Point((int) (11 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					}
//					return;
//				} else if ((i1 == 11 && i2 == 10) || (i1 == 10 && i2 == 11)) {
//					if (j1 == 9) {
//						int rand = rnd.nextInt(2);
//						if (rand == 0) {
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//							vcCom.add(new Point((int) (11 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//						} else {
//							vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//							vcCom.add(new Point((int) (11 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//						}
//					} else {
//						vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//						vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					}
//					return;
//				} else if ((i1 == 8 && i2 == 7) || (i1 == 7 && i2 == 8)) {
//					if (j1 == 9) {
//						int rand = rnd.nextInt(2);
//						if (rand == 0) {
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//							vcCom.add(new Point((int) (7 * 41.7 + 26), (int) (8 * 41.6 + 28)));
//						} else {
//							vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//							vcCom.add(new Point((int) (7 * 41.7 + 26), (int) (10 * 41.6 + 28)));
//						}
//					} else {
//						vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//						vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					}
//					return;
//				} else if ((i1 == 9 && i2 == 8) || (i1 == 8 && i2 == 9)) {
//					vcCom.add(new Point((int) (8 * 41.7 + 26), (int) (9 * 41.6 + 28)));
//					int rand = rnd.nextInt(2);
//					if (rand == 0) {
//						vcCom.add(new Point((int) (7 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					} else {
//						vcCom.add(new Point((int) (10 * 41.7 + 26), (int) (j1 * 41.6 + 28)));
//					}
//					return;
//				}
//			}
//
//		}
//
//		int firstX = 0, firstY = 0;
//		while (true) {
//			firstX = rnd.nextInt(3) + 8;
//			firstY = rnd.nextInt(3) + 8;
//			int check2 = 0;
//			if ((i1 == firstX && j1 == firstY) || (i2 == firstX && j2 == firstY)) {
//				check2 = 1;
//			}
//			for (int i = 0; i < dont.size(); i++) {
//				if (dont.get(i).x == (double) firstX * 41.7 + 26 && dont.get(i).y == firstY * 41.6 + 28)
//					check2 = 1;
//			}
//			if (check2 != 1 && !(firstX == 9 && firstY == 9))
//				break;
//		}
//		vcCom.add(new Point((int) (firstY * 41.7 + 26), (int) (firstX * 41.6 + 28)));
//
//		int secondX = 0, secondY = 0;
//		while (true) {
//			int rand = rnd.nextInt(3);
//			if (rand == 0) {
//				secondX = firstX + 1;
//			} else if (rand == 1) {
//				secondX = firstX - 1;
//			} else if (rand == 2) {
//				secondX = firstX;
//			}
//			if (secondX >= 8 && secondX <= 10)
//				break;
//		}
//
//		while (true) {
//			int rand = rnd.nextInt(2);
//			if (rand == 0) {
//				secondY = firstY + 1;
//			} else if (rand == 1) {
//				secondY = firstY - 1;
//			}
//			int check2 = 0;
//			if ((i1 == firstX && j1 == firstY) || (i2 == firstX && j2 == firstY)) {
//				check2 = 1;
//			}
//			for (int i = 0; i < dont.size(); i++) {
//				if (dont.get(i).x == (double) firstX * 41.7 + 26 && dont.get(i).y == firstY * 41.6 + 28)
//					check2 = 1;
//			}
//			if (secondY >= 8 && secondY <= 10 && check2 != 1 && !(secondX == 9 && secondY == 9)
//					&& !(secondX == firstX && secondY == firstY))
//				break;
//		}
//		vcCom.add(new Point((int) (secondY * 41.7 + 26), (int) (secondX * 41.6 + 28)));
//
	}

	public static void putStoneRandomly1(ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) {
		Random rnd = new Random();
		int firstX = 0, firstY = 0;

		while (true) {
			firstX = rnd.nextInt(3) + 8;
			firstY = rnd.nextInt(3) + 8;
			int check = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[firstY][firstX] !=0 ) {
						check = 1;
					}
				}
			}
			if (check != 1)
				break;
		}
		vcCom.add(new Point((int) (firstX * 41.7 + 26), (int) (firstY * 41.6 + 28)));
		result[firstY][firstX] = 2;
		coor.add(firstY);
		coor.add(firstX);
	}

	public static void putStoneRandomly2(ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) {
		Random rnd = new Random();
		int firstX = 0, firstY = 0;
		int secondX =0, secondY= 0;

		while (true) {
			firstX = rnd.nextInt(3) + 8;
			firstY = rnd.nextInt(3) + 8;
			int check = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[firstY][firstX] !=0 ) {
						check = 1;
					}
				}
			}
			if (check != 1)
				break;
		}
		vcCom.add(new Point((int) (firstX * 41.7 + 26), (int) (firstY * 41.6 + 28)));
		result[firstY][firstX] = 2;
		coor.add(firstY); coor.add(firstX);
		while (true) {
			secondX = rnd.nextInt(3) + 8;
			secondY = rnd.nextInt(3) + 8;
			int check = 0;
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (result[secondY][secondX] !=0 ) {
						check = 1;
					}
				}
			}
			if (check != 1)
				break;
		}
		vcCom.add(new Point((int) (secondX * 41.7 + 26), (int) (secondY * 41.6 + 28)));
		result[secondY][secondX] = 2;
		coor.add(secondY); coor.add(secondX);
	}

}
