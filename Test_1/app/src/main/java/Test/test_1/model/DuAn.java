package Test.test_1.model;

public class DuAn {
    private String tenDA, ngayBD, ngayKT;
    private boolean hoanThanh;

    public DuAn() {
    }

    public DuAn(String tenDA, String ngayBD, String ngayKT) {
        this.tenDA = tenDA;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.hoanThanh = false;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public boolean isHoanThanh() {
        return hoanThanh;
    }

    public void setHoanThanh(boolean hoanThanh) {
        this.hoanThanh = hoanThanh;
    }
}
