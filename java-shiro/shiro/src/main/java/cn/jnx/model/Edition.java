package cn.jnx.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Edition {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String edition_code;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getEdition_code() {
        return edition_code;
    }

    public void setEdition_code(String edition_code) {
        this.edition_code = edition_code == null ? null : edition_code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}