
package com.us.broadreach.stack.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("vtype")
    @Expose
    private String vtype;
    @SerializedName("nfid")
    @Expose
    private Integer nfid;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("avgrating")
    @Expose
    private Double avgrating;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("imdbid")
    @Expose
    private String imdbid;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("imdbrating")
    @Expose
    private Double imdbrating;
    @SerializedName("top250")
    @Expose
    private Integer top250;
    @SerializedName("top250tv")
    @Expose
    private Integer top250tv;
    @SerializedName("clist")
    @Expose
    private String clist;
    @SerializedName("titledate")
    @Expose
    private String titledate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public Integer getNfid() {
        return nfid;
    }

    public void setNfid(Integer nfid) {
        this.nfid = nfid;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Double getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(Double avgrating) {
        this.avgrating = avgrating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(Double imdbrating) {
        this.imdbrating = imdbrating;
    }

    public Integer getTop250() {
        return top250;
    }

    public void setTop250(Integer top250) {
        this.top250 = top250;
    }

    public Integer getTop250tv() {
        return top250tv;
    }

    public void setTop250tv(Integer top250tv) {
        this.top250tv = top250tv;
    }

    public String getClist() {
        return clist;
    }

    public void setClist(String clist) {
        this.clist = clist;
    }

    public String getTitledate() {
        return titledate;
    }

    public void setTitledate(String titledate) {
        this.titledate = titledate;
    }

}
