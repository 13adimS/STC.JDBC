package realExample;

import realExample.dao.MobileDao;
import realExample.dao.MobileDaoJdbcImpl;
import realExample.pojo.Mobile;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        Mobile mobile = new Mobile(7, "Iphone 4", 25000F, 1);
        MobileDao mobileDao = new MobileDaoJdbcImpl();
        String resultSet = mobileDao.getManufacturerById(mobile);
        System.out.println(resultSet);
        /*mobileDao.addMobile(mobile);


        mobile.setPrice(69000F);
        mobileDao.updateMobileById(mobile);
        mobile = mobileDao.getMobileById(7);
        System.out.println(mobile);*/

    }
}
