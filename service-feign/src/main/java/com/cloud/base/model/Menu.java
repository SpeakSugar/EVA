package com.cloud.base.model;

public class Menu {
    private Long id;
    private String name;
    private String enName;
    private Long parent;
    private String type;
    private String subType;
    private Long orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", parent=" + parent +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", orderNo=" + orderNo + '\'' +
                '}';
    }

    public Menu() {

    }

}
