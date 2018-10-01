package realExample.dao;

import realExample.pojo.Manufacturer;

public interface ManufacturerDao {
    public boolean addManufacturer (Manufacturer manufacturer);
    public Manufacturer getManufacturerByID (Integer id);
    public boolean updateManufacturer (Manufacturer manufacturer);
    public boolean deleteManufacturerByID (Integer id);
}
