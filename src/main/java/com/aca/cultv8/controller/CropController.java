
package com.aca.cultv8.controller;

import java.util.List;

import com.aca.cultv8.service.CropService;
import com.aca.cultv8.model.Crop;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/crop")
@Produces(MediaType.APPLICATION_JSON)
public class CropController {
	
	private CropService service = new CropService();
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
//    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }
    
    @GET
    @Path("/getCrops")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Crop> getCrops() {
    	return service.listCrops();
    }
    
    @GET
    @Path("/{cropValue}")
	public Crop getCrop(@PathParam("cropValue") Integer cropId) {
		return service.getCrop(cropId);
	}
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Crop createCrop(Crop newCrop) {
		return service.createCrop(newCrop);
	}
	
	@DELETE
	@Path("/{cropValue}")
	public Crop deleteCrop(@PathParam("cropValue") Integer cropId) {
		return service.deleteCrop(cropId);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Crop updateCrop(Crop updateCrop) {
		return service.updateCrop(updateCrop);

	}
}

