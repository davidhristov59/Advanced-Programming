import java.math.BigDecimal;

public class BigComplex {

    private BigDecimal realenDel;
    private BigDecimal imaginarenDel;

    public BigDecimal getRealenDel() {
        return realenDel;
    }

    public BigDecimal getImaginarenDel() {
        return imaginarenDel;
    }

    public BigComplex(BigDecimal realenDel, BigDecimal imaginarenDel) {
        this.realenDel = realenDel;
        this.imaginarenDel = imaginarenDel;
    }

    //konkatanacija na imaginarniot i na realniot del
    public BigComplex add(BigComplex complex){

        BigDecimal real = this.realenDel.add(complex.realenDel);
        BigDecimal imag = this.imaginarenDel.add(complex.imaginarenDel);

        return new BigComplex(realenDel,imaginarenDel);
    }

    public BigComplex multiply(BigComplex complex){
        return new BigComplex(this.realenDel.multiply(this.realenDel),this.imaginarenDel.multiply(imaginarenDel));
    }

    @Override
    public String toString() {
        return String.format("%si + %sj", realenDel.toString(), imaginarenDel.toString()); //mora da povikame toString zoso ako ne povikame toString ke se pecati samo referenca
    }

    public static void main(String[] args) {

        BigDecimal real1 = new BigDecimal("2.0");
        BigDecimal imag1 = new BigDecimal("3.0");
        BigDecimal real2= new BigDecimal("1.0");
        BigDecimal imag2 = new BigDecimal("4.0");

        BigComplex complex = new BigComplex(real1,imag1);
        BigComplex complex2 = new BigComplex(real2, imag2);

        //Sobiranje
        BigComplex sum = complex.add(complex2);
        System.out.println("Sum: " + sum.getRealenDel() + " + " + sum.getImaginarenDel() + "i");

        BigComplex product = complex.add(complex2);
        System.out.println("Product: " + product.getRealenDel() + " + " + product.getImaginarenDel() + "i");

    }
}
