package com.aca.cultv8.dao;

import java.util.List;

import com.aca.cultv8.model.Crop;

public interface CropDao {
	public List<Crop> getCrops();
	public Crop createCrop(Crop newCrop);
	public Crop deleteCrop(Integer cropId);
	public List<Crop> getCrop(Integer cropId);
	public Crop updateCrop(Crop updateCrop);
}
