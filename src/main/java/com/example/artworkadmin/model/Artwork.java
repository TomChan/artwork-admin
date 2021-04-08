package com.example.artworkadmin.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artwork {

    public static final String ART_NAME_KEY = "artname";
    public static final String ARTIST_NAME_KEY = "artistname";
    public static final String ART_CAT_KEY = "artcat";
    public static final String COLOR_KEY = "color";
    public static final String MEDIUM_KEY = "medium";
    public static final String CULTURE_KEY = "culture";
    public static final String BRG_KEY = "brg";
    public static final String CATEGORY_KEY = "category";
    public static final String CREATION_YEAR_KEY = "creationyear";
    public static final String ACQUISTION_YEAR_KEY = "acquistionyear";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String artName;
    private String artCat;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> artCatList;
    private String artistName;
    private String artistInfo;
    private String creationDate;
    private String creationMonth;
    private String creationYear;
    private String acquistionDate;
    private String acquistionMonth;
    private String acquistionYear;
    private String color;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> colorList;
    private String medium;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> mediumList;
    private String culture;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> cultureList;
    private String brg;
    private String category;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> categorylist;
    private String displayImage;
    private String imgLoc;
    private String soundLoc;
    private String description;

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public String getArtCat() {
        return artCat;
    }

    public void setArtCat(String artCat) {
        this.artCat = artCat;
    }

    public List<String> getArtCatList() {
        return artCatList;
    }

    public void setArtCatList(List<String> artCatList) {
        this.artCatList = artCatList;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationMonth() {
        return creationMonth;
    }

    public void setCreationMonth(String creationMonth) {
        this.creationMonth = creationMonth;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(String creationYear) {
        this.creationYear = creationYear;
    }

    public String getAcquistionDate() {
        return acquistionDate;
    }

    public void setAcquistionDate(String acquistionDate) {
        this.acquistionDate = acquistionDate;
    }

    public String getAcquistionMonth() {
        return acquistionMonth;
    }

    public void setAcquistionMonth(String acquistionMonth) {
        this.acquistionMonth = acquistionMonth;
    }

    public String getAcquistionYear() {
        return acquistionYear;
    }

    public void setAcquistionYear(String acquistionYear) {
        this.acquistionYear = acquistionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public List<String> getMediumList() {
        return mediumList;
    }

    public void setMediumList(List<String> mediumList) {
        this.mediumList = mediumList;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public List<String> getCultureList() {
        return cultureList;
    }

    public void setCultureList(List<String> cultureList) {
        this.cultureList = cultureList;
    }

    public String getBrg() {
        return brg;
    }

    public void setBrg(String brg) {
        this.brg = brg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCategorylist() {
        return categorylist;
    }

    public void setCategorylist(List<String> categorylist) {
        this.categorylist = categorylist;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }

    public String getSoundLoc() {
        return soundLoc;
    }

    public void setSoundLoc(String soundLoc) {
        this.soundLoc = soundLoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
