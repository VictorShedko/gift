package com.epam.esm.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;

public class GiftCertificate {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.'SZ")
    private Date creationTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.'SZ")
    private Date updateTime;
    private Integer duration;

    public GiftCertificate() {
    }

    public GiftCertificate(Integer id, String name, String description, Float price,
                           Date creationTime, Date updateTime, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftCertificate that = (GiftCertificate) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        return duration != null ? duration.equals(that.duration) : that.duration == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationTime='" + creationTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void update(GiftCertificate certificate) {
        if (certificate.creationTime != null) {
            this.creationTime = certificate.creationTime;
        }
        if (certificate.description != null) {
            this.description = certificate.description;
        }
        if (certificate.duration != null) {
            this.duration = certificate.duration;
        }
        if (certificate.price != null) {
            this.price = certificate.price;
        }
        if (certificate.name != null) {
            this.name = certificate.name;
        }


    }
}
