package com.aca.cultv8.service;

import java.util.List;

import com.aca.cultv8.dao.CropDao;
import com.aca.cultv8.dao.CropDaoImpl;
import com.aca.cultv8.model.Crop;


public class CropService {
	
	private CropDao cropDao = new CropDaoImpl();
	
	public List<Crop> listCrops() {
		return cropDao.getCrops();
	}
	
	public Crop createCrop(Crop newCrop) {
//		validateReleaseYear(newCrop.getReleaseYear());
		return cropDao.createCrop(newCrop);
	}
	
	public Crop deleteCrop(Integer cropId) {
//		validateMovieId(movieId);
		return cropDao.deleteCrop(cropId);
	}
	
	public Crop getCrop(Integer cropId) {
		return cropDao.getCrop(cropId).get(0);
	}
	
	public Crop updateCrop(Crop updateCrop) {
		return cropDao.updateCrop(updateCrop);
	}
}
