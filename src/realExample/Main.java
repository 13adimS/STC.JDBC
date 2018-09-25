package realExample;

import realExample.dao.MobileDao;
import realExample.dao.MobileDaoJdbcImpl;
import realExample.pojo.Mobile;

public class Main {
    public static void main(String[] args) {
        Mobile mobile = new Mobile(null, "Iphone 2", 25000F, 5);
        MobileDao mobileDao = new MobileDaoJdbcImpl();
        //mobileDao.addMobile(mobile);
        //mobileDao.deleteMobileById(8);
        mobile = mobileDao.getMobileById(7);
        System.out.println(mobile);
        mobile.setPrice(70000F);
        mobileDao.updateMobileById(mobile);
        mobile = mobileDao.getMobileById(7);
        System.out.println(mobile);
    }
}
