package com.aca.cultv8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.cultv8.model.Crop;
import com.aca.cultv8.dao.MariaDbUtil;

public class CropDaoImpl implements CropDao {
	
	private static String selectAllCrops = "SELECT id, cropName, cropLink, cropImage, medianDaysToFirstHarvest, medianDaysToLastHarvest, medianLifespan, "
			+ "perennial, scientificName, updateDateTime, createDateTime " +
			"FROM crops";

	private static String selectCropById = 
			"SELECT id, cropName, cropLink, cropImage, medianDaysToFirstHarvest, medianDaysToLastHarvest, medianLifespan, "
			+ "perennial, scientificName, updateDateTime, createDateTime " +
			"FROM crops " +
			"WHERE id = ? ";
	
	
	private static String insertCrop = 
			"INSERT INTO crops (cropName, cropLink, cropImage, medianDaysToFirstHarvest, medianDaysToLastHarvest, medianLifespan, perennial, scientificName ) " +
			"VALUES " +
			"(?,?,?,?,?,?,?,?)";
	
	private static String selectNewCropId =
			"SELECT LAST_INSERT_ID() AS cropId";
	
	private static String deleteCrop = 
			"DELETE FROM crops " +
			"WHERE id = ?";
	
	private static String updateCropById = 
			"UPDATE crops SET "
			+ "cropName = ?, "
			+ "cropLink = ?, "
			+ "cropImage = ?, "
			+ "medianDaysToFirstHarvest = ?, "
			+ "medianDaysToLastHarvest = ?, "
			+ "medianLifespan = ?, "
			+ "perennial = ?, "
			+ "scientificName = ? "
			+ "WHERE id = ? ";
	
	@Override
	public List<Crop> getCrops() {
		List<Crop> myCrops = new ArrayList<Crop>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		statement = conn.createStatement();
		result = statement.executeQuery(selectAllCrops);
		myCrops = makeCrop(result);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return myCrops;
	}
	
	private int getNewCropId(Connection conn) {
		ResultSet result = null;
		Statement statement = null;
		int newCropId = 0;
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectNewCropId);
			
			while(result.next()) {
				newCropId = result.getInt("cropId");
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		
		return newCropId;
	}

	
	private List<Crop> makeCrop(ResultSet result) throws SQLException {
		List<Crop> myCrops = new ArrayList<Crop>();
		
		while(result.next() ) {
			Crop crop = new Crop();
			
			crop.setId(result.getInt("id"));
			crop.setCropName(result.getString("cropName"));
			crop.setCropLink(result.getString("cropLink"));
			crop.setCropImage(result.getString("cropImage"));
			crop.setMedianDaysToFirstHarvest(result.getInt("medianDaysToFirstHarvest"));
			crop.setMedianDaysToLastHarvest(result.getInt("medianDaysToLastHarvest"));
			crop.setMedianLifespan(result.getInt("medianLifespan"));
			crop.setPerennial(result.getBoolean("perennial"));
			crop.setScientificName(result.getString("scientificName"));
			crop.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			crop.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
			
			myCrops.add(crop);
			
		}
		
		return myCrops;
	}
	
	@Override
	public Crop createCrop(Crop newCrop) {		
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(insertCrop);
				ps.setString(1, newCrop.getCropName());
				ps.setString(2, newCrop.getCropLink());
				ps.setString(3, newCrop.getCropImage());
				ps.setInt(4, newCrop.getMedianDaysToFirstHarvest());
				ps.setInt(5, newCrop.getMedianDaysToLastHarvest());
				ps.setInt(6, newCrop.getMedianLifespan());
				ps.setBoolean(7, newCrop.isPerennial());
				ps.setString(8, newCrop.getScientificName());
				updateRowCount = ps.executeUpdate();
				System.out.println("insert row count: " + updateRowCount);
				int cropId = getNewCropId(conn);
				
				newCrop.setId(cropId); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		return newCrop;	
	}
	
	@Override
	public Crop deleteCrop(Integer cropId) {
		List<Crop> crops = getCrop(cropId);
		Crop cropToDelete = null;
		
		if(crops.size() > 0) {
			cropToDelete = crops.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(deleteCrop);
				ps.setInt(1, cropId);
				updateRowCount = ps.executeUpdate();
				System.out.println("Rows Deleted: " + updateRowCount);
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cropToDelete;	
	
	}
	
	@Override
	public List<Crop> getCrop(Integer cropId) {
		List<Crop> myCrops = new ArrayList<Crop>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
		ps = conn.prepareStatement(selectCropById);
		ps.setInt(1, cropId);
		
		result = ps.executeQuery();
		myCrops = makeCrop(result);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return myCrops; 
		
	}
	
	@Override
	public Crop updateCrop(Crop updateCrop) {		
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(updateCropById);
				ps.setString(1, updateCrop.getCropName());
				ps.setString(2, updateCrop.getCropLink());
				ps.setString(3, updateCrop.getCropImage());
				ps.setInt(4, updateCrop.getMedianDaysToFirstHarvest());
				ps.setInt(5, updateCrop.getMedianDaysToLastHarvest());
				ps.setInt(6, updateCrop.getMedianLifespan());
				ps.setBoolean(7, updateCrop.isPerennial());
				ps.setString(8, updateCrop.getScientificName());
				ps.setInt(9, updateCrop.getId());
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		return updateCrop;	
	}
}
