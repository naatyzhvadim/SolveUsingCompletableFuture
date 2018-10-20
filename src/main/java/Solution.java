public class Solution {
    private double aRe, aIm = 0, bRe, bIm = 0;
    Solution (double a, double b) {
        aRe = a;
        bRe = b;
    }
    Solution(double aRe, double aIm, double bRe, double bIm) {
        this.aRe = aRe;
        this.aIm = aIm;
        this.bRe = bRe;
        this.bIm = bIm;
    }
    public void printSolution(){
        if (aIm == 0) {
            System.out.println(aRe + "  " + bRe);
        } else {
            System.out.println(aRe + " + " + aIm + " i  " + bRe +
                    " + " + bIm + " i");
        }
    }

    public double getARe() {
        return aRe;
    }
    public double getBRe() {
        return bRe;
    }
    public double getaIm() {
        return aIm;
    }
    public double getbIm() {
        return bIm;
    }
    public void setaIm(double aIm) {
        this.aIm = aIm;
    }
    public void setaRe(double aRe) {
        this.aRe = aRe;
    }
    public void setbIm(double bIm) {
        this.bIm = bIm;
    }
    public void setbRe(double bRe) {
        this.bRe = bRe;
    }
}
