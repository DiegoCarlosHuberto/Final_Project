
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author HMartin
 */
public class NumWin {

	private int numRuleta;
	private String groupNumWin;
	private String colorWList;

	private static final List<Integer> vecinos = Arrays.asList(0, 2, 3, 4, 7, 12, 15, 18, 19, 21, 22, 25, 26, 28, 29,
			32, 35 // 17
	);
	private static final List<Integer> huerfanos = Arrays.asList(1, 6, 9, 14, 17, 20, 31, 34 // 8
	);
	private static final List<Integer> series = Arrays.asList(5, 8, 10, 11, 13, 16, 23, 24, 27, 30, 33, 36 // 12
	);
	private static final List<Integer> negro = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 19, 20, 22, 24, 26, 29, 31,
			33, 35);
	private static final List<Integer> rojo = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 21, 23, 25, 27, 28, 30, 32,
			34, 36);

	public NumWin() {
		Random ranNum = new Random();
		this.numRuleta = ranNum.nextInt(37);
		addValues();
	}

	public void addValues() {
		if (vecinos.contains(numRuleta)) {
			this.groupNumWin = ("vecinos");

		}
		if (series.contains(numRuleta)) {
			this.groupNumWin = ("series");

		}
		if (huerfanos.contains(numRuleta)) {
			this.groupNumWin = ("huerfanos");

		}
		if (rojo.contains(numRuleta)) {
			this.colorWList = ("rojo");

		}
		if (negro.contains(numRuleta)) {
			this.colorWList = ("negro");

		}
	}

	public String getColorWList() {
		return colorWList;
	}

	public void setColorWList(String colorWList) {
		this.colorWList = colorWList;
	}

	public String getGroupNumWin() {
		return groupNumWin;
	}

	public void setGroupNumWin(String groupNumWinString) {
		this.groupNumWin = groupNumWinString;
	}

	public int getNumRuleta() {
		return numRuleta;
	}

	public void setNumRuleta(int numRuleta) {
		this.numRuleta = numRuleta;
	}

}
