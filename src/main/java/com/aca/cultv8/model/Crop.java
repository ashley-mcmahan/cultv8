package com.aca.cultv8.model;

import java.time.LocalDateTime;

public class Crop {
	private Integer id;
	private String cropName;
	private String cropLink;
	private String cropImage;
	private Integer medianDaysToFirstHarvest;
	private Integer medianDaysToLastHarvest;
	private Integer medianLifespan;
	private boolean perennial;
	private String scientificName;
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getCropLink() {
		return cropLink;
	}
	public void setCropLink(String cropLink) {
		this.cropLink = cropLink;
	}
	public String getCropImage() {
		return cropImage;
	}
	public void setCropImage(String cropImage) {
		this.cropImage = cropImage;
	}
	public Integer getMedianDaysToFirstHarvest() {
		return medianDaysToFirstHarvest;
	}
	public void setMedianDaysToFirstHarvest(Integer medianDaysToFirstHarvest) {
		this.medianDaysToFirstHarvest = medianDaysToFirstHarvest;
	}
	public Integer getMedianDaysToLastHarvest() {
		return medianDaysToLastHarvest;
	}
	public void setMedianDaysToLastHarvest(Integer medianDaysToLastHarvest) {
		this.medianDaysToLastHarvest = medianDaysToLastHarvest;
	}
	public Integer getMedianLifespan() {
		return medianLifespan;
	}
	public void setMedianLifespan(Integer medianLifespan) {
		this.medianLifespan = medianLifespan;
	}
	public boolean isPerennial() {
		return perennial;
	}
	public void setPerennial(boolean perennial) {
		this.perennial = perennial;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
}
