package controller.perpustakaan;

public class ModelTable {
    private String nama;
    private String kelas;
    private Integer no;
    private String jurusan;
    private String status;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ModelTable(Integer no, String namal, String kelas, String jurusan, String status) {
        this.nama = namal;
        this.kelas = kelas;
        this.no = no;
        this.jurusan = jurusan;
        this.status = status;
    }
}
