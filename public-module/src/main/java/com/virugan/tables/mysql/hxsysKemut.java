package com.virugan.tables.mysql;

/**
 * 科目表对应实体
 */
public class hxsysKemut {

    //法人代码
    private String faredma;
    //科目号
    private Integer kemuhoo;
    //科目级别
    private Integer kemujib;
    //科目类型
    private String kemulex;
    //科目中文名
    private String kemunme;
    //科目描述
    private String kemumas;

    public String getFaredma() {
        return faredma;
    }

    public void setFaredma(String faredma) {
        this.faredma = faredma;
    }

    public Integer getKemuhoo() {
        return kemuhoo;
    }

    public void setKemuhoo(Integer kemuhoo) {
        this.kemuhoo = kemuhoo;
    }

    public Integer getKemujib() {
        return kemujib;
    }

    public void setKemujib(Integer kemujib) {
        this.kemujib = kemujib;
    }

    public String getKemulex() {
        return kemulex;
    }

    public void setKemulex(String kemulex) {
        this.kemulex = kemulex;
    }

    public String getKemunme() {
        return kemunme;
    }

    public void setKemunme(String kemunme) {
        this.kemunme = kemunme;
    }

    public String getKemumas() {
        return kemumas;
    }

    public void setKemumas(String kemumas) {
        this.kemumas = kemumas;
    }
}
