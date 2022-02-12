package controller.perpustakaan;

public class ModelBook {
    private Integer no;
    private String nama_buku;
    private String jenis_buku;
    private String status_buku;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNama_buku() {
        return nama_buku;
    }

    public void setNama_buku(String nama_buku) {
        this.nama_buku = nama_buku;
    }

    public String getJenis_buku() {
        return jenis_buku;
    }

    public void setJenis_buku(String jenis_buku) {
        this.jenis_buku = jenis_buku;
    }

    public String getStatus_buku() {
        return status_buku;
    }

    public void setStatus_buku(String status_buku) {
        this.status_buku = status_buku;
    }

    public ModelBook(int counters, String nama_buku, String jenis_buku, String status_buku){
        this.no = no;
        this.nama_buku = nama_buku;
        this.jenis_buku = jenis_buku;
        this.status_buku = status_buku;
    }
}
