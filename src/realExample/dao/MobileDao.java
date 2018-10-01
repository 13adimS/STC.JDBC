package realExample.dao;

import realExample.pojo.Manufacturer;
import realExample.pojo.Mobile;

public interface MobileDao {
    public boolean addMobile(Mobile mobile);

    public Mobile getMobileById(Integer id);

    public boolean updateMobileById(Mobile mobile);

    public boolean deleteMobileById(Integer id);

    public String getManufacturerById(Mobile mobile);
}
