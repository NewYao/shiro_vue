package cn.jnx.model;

public class Edition_detail {
    private Integer id;

    private Integer edition_id;


    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEdition_id() {
        return edition_id;
    }

    public void setEdition_id(Integer edition_id) {
        this.edition_id = edition_id;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}