package com.gr42.insta.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@XmlRootElement
public class Publication {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    private String comment;

    public String getComment() {
        return comment;
    }

    public String imageName;

    public void setComment(String comment) {
        this.comment = comment;
    }

    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    @FormParam("uploadedImage")
    @PartType("application/octet-stream")
    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}