package sda.oscail.edu.actiontabs;
/**
 * Created by ckirwan on 18/06/2016.
 */
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPageAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public static final int HOME = 0;
    public static final int PRODUCTS = 1;
    public static final int ORDER = 2;
    public static final int COLLECTIONS = 3;
    public static final String UI_TAB_HOME = "Home";
    public static final String UI_TAB_PRODUCTS = "Products";
    public static final String UI_TAB_ORDERS = "Orders";
    public static final String UI_TAB_COLLECTIONS = "Collections";


    public MyPageAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {

        switch (position)
        {
            case HOME:
                HomeFragment homeTab = new HomeFragment();
                return homeTab;
            case PRODUCTS:
                ProductsFragment productsTab = new ProductsFragment();
                return productsTab;
            case ORDER:
                OrderFragment orderTab = new OrderFragment();
                return orderTab;
            case COLLECTIONS:
                CollectionsFragment collectionsTab = new CollectionsFragment();
                return collectionsTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case HOME:
                return UI_TAB_HOME;
            case PRODUCTS:
                return UI_TAB_PRODUCTS;
            case ORDER:
                return UI_TAB_ORDERS;
            case COLLECTIONS :
                return UI_TAB_COLLECTIONS;
            default:
                break;
        }
        return null;
    }



}