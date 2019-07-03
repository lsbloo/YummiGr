package com.com.yummigr.dtos;

public class GraphicsDTO {


    private String description;

    private String path_save_png;

    private boolean status_save;

    private Integer type_graphic;

    private String option;

    public boolean isStatus_save() {
        return status_save;
    }

    public Integer getType_graphic() {
        return type_graphic;
    }

    public void setType_graphic(Integer type_graphic) {
        this.type_graphic = type_graphic;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSave() {
        return status_save;
    }

    public String getPath_save_png() {
        return path_save_png;
    }

    public void setPath_save_png(String path_save_png) {
        this.path_save_png = path_save_png;
    }

    public void setSave(boolean save) {
        this.status_save = save;
    }

    public GraphicsDTO(String description, String path_save_png, boolean status, String option, Integer type) {


        setDescription(description);
        setPath_save_png(path_save_png);
        setSave(status);
        setOption(option);
        setType_graphic(type);
    }
}
