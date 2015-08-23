
public class EditDistance {
	public static int editDistance(String s, String t){
		int [][] table = new int [s.length()+1] [t.length()+1];
		table [0] [0] = 0;
		for (int i = 1; i<=s.length(); i++){
			table[i][0] = i;
		}
		for (int j = 1; j<=t.length(); j++){
			table[0][j] = j;
		}
		for (int i= 1; i<=s.length(); i++){
			for (int j= 1; j <= t.length(); j++){
				int a = Math.min(((table[i-1][j])+1), ((table[i][j-1])+1));
				int b = table[i-1][j-1];
				if(s.charAt(i-1) != t.charAt(j-1)){
					b++;
				}
				table [i][j] = Math.min(a, b);
			}
		}
		return table[s.length()][t.length()];
	}
	
}
