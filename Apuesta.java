
import java.util.ArrayList;
import java.util.List;

/**
 * @author HMartin
 */
public class Apuesta {

	private Integer numsBetsPleno;
	private Double cuantityBetNum;
	private String groupsNum;
	private Double cuantityBetGroup;
	private String color;
	private Double colorCuantity;
	private double ganancias;
	private NumWin numWin;

	public Apuesta(Integer numsBetsPleno, String groupsNum, Double cuantityBetNum, Double cuantityBetGroup,
			String color, Double colorQuantity) {
		
		this.numsBetsPleno = numsBetsPleno;
		this.groupsNum = groupsNum;
		this.cuantityBetNum = cuantityBetNum;
		this.cuantityBetGroup = cuantityBetGroup;
		this.color = color;
		this.colorCuantity = colorQuantity;
		this.ganancias = 0;
		this.numWin = new NumWin();
	}

	public boolean checkBetN() {
		boolean check = false;

		if (this.numsBetsPleno == null && this.cuantityBetNum == null) {
			System.out.println("Numeros apostados: 0");
			System.out.println("Valor de la apuesta: 0");
			check = true;
		}

		return check;

	}

	public boolean checkBetG() {
		boolean check = false;

		if (this.groupsNum == null && this.cuantityBetGroup == null) {
			System.out.println("No has apostado a ningun grupo");
			System.out.println("Valor de la apuesta: 0");
			check = true;
		}

		return check;

	}

	public boolean compareNums(int gameNum) {
		boolean x = false;

		if (this.numsBetsPleno == gameNum) {
			System.out.println("Has acertado el pleno");
			x = true;

		}

		return x;
	}

	public List<String> compareList() {
		List<String> groupW = new ArrayList<String>();

		numWin.getNumRuleta();

		if (checkBetG() == true) {
			System.out.println("No ha apostado a ningun grupo");
		}

		if (numWin.getColorWList().equals("negro")) {
			groupW.add("negro");
		}
		if (numWin.getColorWList().equals("rojo")) {
			groupW.add("rojo");
		}
		if (numWin.getGroupNumWin().equals("vecinos")) {
			groupW.add("vecinos");
		}
		if (numWin.getGroupNumWin().equals("series")) {
			groupW.add("tercios");
		}
		if (numWin.getGroupNumWin().equals("huerfanos")) {
			groupW.add("huerfanos");
		}
		return groupW;
	}

	public double gains(int gameNum) {
		double finalGain = 0;
		if (compareNums(gameNum) == true) {
			finalGain = cuantityBetNum * 36;
			System.out.println(finalGain + "pleno");
		}

		if (compareList().contains("vecinos") && groupsNum.equalsIgnoreCase("vecinos")) {
			finalGain = +this.cuantityBetGroup * 2.4;

		}
		if (compareList().contains("tercios") && groupsNum.equalsIgnoreCase("series")) {
			finalGain = +this.cuantityBetGroup * 2.6;

		}
		if (compareList().contains("huerfanos") && groupsNum.equalsIgnoreCase("huerfanos")) {
			finalGain = +this.cuantityBetGroup * 2.8;

		}
		if (compareList().contains("rojo") && color.equalsIgnoreCase("rojo")) {
			finalGain = +this.colorCuantity * 2;

		}
		if (compareList().contains("negro") && color.equalsIgnoreCase("negro")) {
			finalGain = +this.colorCuantity * 2;

		}

		return finalGain;

	}
	
	

	
	public NumWin getValuesNum() {
		return numWin;
	}

	public void setValuesNum(NumWin valuesNum) {
		this.numWin = valuesNum;
	}

	public String getGroupsNum() {
		return groupsNum;
	}

	public void setGroupsNum(String groupsNum) {
		this.groupsNum = groupsNum;
	}

	public Double getCuantityBetGroup() {
		return cuantityBetGroup;
	}

	public void setCuantityBetGroup(Double cuantityBetGroup) {
		this.cuantityBetGroup = cuantityBetGroup;
	}

	public Integer getNumsBetsPleno() {
		return numsBetsPleno;
	}

	public void setNumsBetsPleno(Integer numsBetsPleno) {
		this.numsBetsPleno = numsBetsPleno;
	}

	public Double getCuantityBetNum() {
		return cuantityBetNum;
	}

	public void setCuantityBetNum(Double cuantityBetNum) {
		this.cuantityBetNum = cuantityBetNum;
	}

	public double getGanancias() {
		return ganancias;
	}

	public void setGanancias(double ganancias) {
		this.ganancias = ganancias;
	}

	@Override
	public String toString() {
		return "Apuesta{" + "numsBetsPleno=" + numsBetsPleno + ", groupsNum=" + groupsNum + ", cuantityBetNum="
				+ cuantityBetNum + ", cuantityBetGroup=" + cuantityBetGroup + ", ganancias=" + ganancias + '}';
	}

}
