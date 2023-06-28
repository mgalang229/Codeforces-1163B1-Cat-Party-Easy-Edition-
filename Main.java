import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

goal: 
- find first x days wherein elements have the same frequency
- remove exactly one element from this chosen first x days
- 1 <= ans <= n

------------------------

13
1 1 1 2 2 2 3 3 3 4 4 4 5

num to be removed:
either freq[num] == 1 or freq[num] == freq[others] + 1

handle also sequence with all same numbers

ans: 13

------------------------

edge-cases:
- frequency[allNumbers] = 1
- all numbers are the same

------------------------

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
//		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] u = fs.readArray(n);
			int[] freq = new int[11];
			Arrays.fill(freq, 0);
			boolean same = true;
			for (int i = 0; i < n; i++) {
				freq[u[i]]++;
				if (u[i] != u[0]) {
					same = false;
				}
			}
			int single = 0;
			for (int x : u) {
				if (freq[x] == 1) {
					single++;
				}
			}
			if (single == n || same) {
				System.out.println(n);
				continue;
			}
			Arrays.fill(freq, 0);
			int ans = 0;
			for (int i = 0; i < n; i++) {
				freq[u[i]]++;
				int counter = 0, min = n + 1, max = 0;
				for (int num = 1; num <= 10; num++) {
					if (freq[num] > 0) {
						min = Math.min(min, freq[num]);
						max = Math.max(max, freq[num]);
						counter++;
					}
				}
				int minCount = 0, maxCount = 0;
				for (int num = 1; num <= 10; num++) {
					if (freq[num] == min) {
						minCount++;
					} else if (freq[num] == max) {
						maxCount++;
					}
				}
				//minCount == counter - 1 and maxCount == 1 (max == min + 1)
				//minCount == 1 and maxCount == counter - 1 (min == 1)
				if (minCount == counter - 1 && maxCount == 1 && max == min + 1) {
					ans = i + 1;
				}
				if (minCount == 1 && maxCount == counter - 1 && min == 1) {
					ans = i + 1;
				}
			}
			System.out.println(ans);
		}
		out.close();
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
