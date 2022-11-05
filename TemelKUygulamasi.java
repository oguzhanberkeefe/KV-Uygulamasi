package com.example.kv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TemelKUygulamasi extends AppCompatActivity {

    private List<MainBean> list;
    private MainAdapter adapter;
    private RecyclerView recyclerView;

    private LinearLayoutManager manager;
    private TabLayout tabLayout;

    //tab的标签
    private String[] str = {"En Çok Satılanlar", "Sıcaklar", "Soğuklar", "Yiyecekler"};


    /**
     * 需要定位的地方，从小到大排列，需要和tab对应起来，长度一样
     */

    private int[] str1 = {0, 8, 27, 38, 59  };
    private boolean isScrolled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temel_kuygulamasi);
        initData();
        init();
        initTab();


    }

    private void initTab() {
        for (int i = 0; i < str.length; i++) {
            //插入tab标签
            tabLayout.addTab(tabLayout.newTab().setText(str[i]));
        }
        //标签页可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (!isScrolled) {
                    //滑动时不能点击,
                    //第一个参数是指定的位置，锚点
                    // 第二个参数表示 Item 移动到第一项后跟 RecyclerView 上边界或下边界之间的距离（默认是 0）
                    manager.scrollToPositionWithOffset(str1[pos], 0);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.mian_recy);
        tabLayout = findViewById(R.id.main_tab);

        manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {

                    //En çok Satılanlar Başlangıçı
                    case 0:
                        Intent havucdilim = new Intent(TemelKUygulamasi.this,HavucDilimKekDetay.class);
                        startActivity(havucdilim);
                        break;
                    case 1:
                        Intent colombiasupremo = new Intent(TemelKUygulamasi.this,ColombiaSupremoDetay.class);
                        startActivity(colombiasupremo);
                        break;
                    case 2:
                        Intent latte = new Intent(TemelKUygulamasi.this,LatteDetay.class);
                        startActivity(latte);
                        break;
                    case 3:
                        Intent filtrekahve = new Intent(TemelKUygulamasi.this,FiltreKahveDetay.class);
                        startActivity(filtrekahve);
                        break;
                    case 4:
                        Intent karisiksandvic2 = new Intent(TemelKUygulamasi.this,KarisikSandvicDetay.class);
                        startActivity(karisiksandvic2);
                        break;
                    case 5:
                        Intent peynirlisandvic2 = new Intent(TemelKUygulamasi.this,PeynirliSandvicDetay.class);
                        startActivity(peynirlisandvic2);
                        break;
                    case 6:
                        Intent snickers = new Intent(TemelKUygulamasi.this,SnickersCaramelFrappeDetay.class);
                        startActivity(snickers);
                        break;

                        //Sıcaklar Başlangıçı
                    case 7:
                        Intent espresso = new Intent(TemelKUygulamasi.this,EspressoDetay.class);
                        startActivity(espresso);
                        break;
                    case 8:
                        Intent americano = new Intent(TemelKUygulamasi.this,AmericanoDetay.class);
                        startActivity(americano);
                        break;
                    case 9:
                        Intent espressomacchiato = new Intent(TemelKUygulamasi.this,EspressoMacchiatoDetay.class);
                        startActivity(espressomacchiato);
                        break;
                    case 10:
                        Intent cortado = new Intent(TemelKUygulamasi.this,CortadoDetay.class);
                        startActivity(cortado);
                        break;
                    case 11:
                        Intent cappucino = new Intent(TemelKUygulamasi.this,CappucinoDetay.class);
                        startActivity(cappucino);
                        break;
                    case 12:
                        Intent latte2 = new Intent(TemelKUygulamasi.this,LatteDetay.class);
                        startActivity(latte2);
                        break;
                    case 13:
                        Intent filtrekahve2 = new Intent(TemelKUygulamasi.this,FiltreKahveDetay.class);
                        startActivity(filtrekahve2);
                        break;
                    case 14:
                        Intent flatwhite = new Intent(TemelKUygulamasi.this,FlatWhiteDetay.class);
                        startActivity(flatwhite);
                        break;
                    case 15:
                        Intent chaitealatte = new Intent(TemelKUygulamasi.this,ChaiTeaLatteDetay.class);
                        startActivity(chaitealatte);
                        break;
                    case 16:
                        Intent mochadetay = new Intent(TemelKUygulamasi.this,MochaDetay.class);
                        startActivity(mochadetay);
                        break;
                    case 17:
                        Intent chocolatecookielatte = new Intent(TemelKUygulamasi.this,ChocolateCookieLatteDetay.class);
                        startActivity(chocolatecookielatte);
                        break;
                    case 18:
                        Intent caramelmacchiato = new Intent(TemelKUygulamasi.this,CaramelMacchiatoDetay.class);
                        startActivity(caramelmacchiato);
                        break;
                    case 19:
                        Intent sicakcikolata = new Intent(TemelKUygulamasi.this,SicakCikolataDetay.class);
                        startActivity(sicakcikolata);
                        break;
                    case 20:
                        Intent saltedcaramellatte = new Intent(TemelKUygulamasi.this,SaltedCaramelLatteDetay.class);
                        startActivity(saltedcaramellatte);
                        break;
                    case 21:
                        Intent balkabaklilatte = new Intent(TemelKUygulamasi.this,BalKabakliLatteDetay.class);
                        startActivity(balkabaklilatte);
                        break;
                    case 22:
                        Intent whitemocha = new Intent(TemelKUygulamasi.this,WhiteMochaDetay.class);
                        startActivity(whitemocha);
                        break;
                    case 23:
                        Intent turkkahvesi = new Intent(TemelKUygulamasi.this,TurkKahvesiDetay.class);
                        startActivity(turkkahvesi);
                        break;
                    case 24:
                        Intent earl = new Intent(TemelKUygulamasi.this,EarlGreyCayDetay.class);
                        startActivity(earl);
                        break;
                    case 25:
                        Intent miel = new Intent(TemelKUygulamasi.this,MielDetay.class);
                        startActivity(miel);
                        break;

                        //Soğuklar
                    case 26:
                        Intent icedsaltedcaramellatte = new Intent(TemelKUygulamasi.this,IcedCaramelLatteDetay.class);
                        startActivity(icedsaltedcaramellatte);
                        break;
                    case 27:
                        Intent icedchaitealatte = new Intent(TemelKUygulamasi.this,IcedChaiTeaLatteDetay.class);
                        startActivity(icedchaitealatte);
                        break;
                    case 28:
                        Intent icedchocolatecookielatte = new Intent(TemelKUygulamasi.this,IcedChocolateCookieLatteDetay.class);
                        startActivity(icedchocolatecookielatte);
                        break;
                    case 29:
                        Intent caramelfrappe = new Intent(TemelKUygulamasi.this,CaramelFrappeDetay.class);
                        startActivity(caramelfrappe);
                        break;
                    case 30:
                        Intent mochafrappe = new Intent(TemelKUygulamasi.this,MochaFrappeDetay.class);
                        startActivity(mochafrappe);
                        break;
                    case 31:
                        Intent espressofrappe = new Intent(TemelKUygulamasi.this,EspressoFrappeDetay.class);
                        startActivity(espressofrappe);
                        break;
                    case 32:
                        Intent icedlatte = new Intent(TemelKUygulamasi.this,IcedLatteDetay  .class);
                        startActivity(icedlatte);
                        break;
                    case 33:
                        Intent icedamericano = new Intent(TemelKUygulamasi.this,IcedAmericanoDetay  .class);
                        startActivity(icedamericano);
                        break;
                    case 34:
                        Intent oreomochafrappe = new Intent(TemelKUygulamasi.this,OreoMochaFrappeDetay  .class);
                        startActivity(oreomochafrappe);
                        break;
                    case 35:
                        Intent coldbrew = new Intent(TemelKUygulamasi.this,ColdBrewDetay.class);
                        startActivity(coldbrew);
                        break;
                    case 36:
                        Intent snickers2 = new Intent(TemelKUygulamasi.this,SnickersCaramelFrappeDetay.class);
                        startActivity(snickers2);
                        break;

                    //      yemekler baslangici
                  case 37:
                      Intent havucdilim2 = new Intent(TemelKUygulamasi.this,HavucDilimKekDetay.class);
                      startActivity(havucdilim2);
                        break;
                    case 38:
                        Intent mozaikdilim3 = new Intent(TemelKUygulamasi.this,MozaikDilimKekDetay.class);
                        startActivity(mozaikdilim3);
                        break;
                    case 39:
                        Intent mozaikpasta = new Intent(TemelKUygulamasi.this,MozaikPastaDetay.class);
                        startActivity(mozaikpasta);
                        break;
                    case 40:
                        Intent sekertaneli = new Intent(TemelKUygulamasi.this,SekerTaneliDonutDetay.class);
                        startActivity(sekertaneli);
                        break;
                    case 41:
                        Intent velvetcupcake = new Intent(TemelKUygulamasi.this,VelvetCupcakeDetay.class);
                        startActivity(velvetcupcake);
                        break;
                    case 42:
                        Intent yabanmersinlimuffin = new Intent(TemelKUygulamasi.this,YabanMersinliMuffinDetay.class);
                        startActivity(yabanmersinlimuffin);
                        break;
                    case 43:
                        Intent limonlucheesecake = new Intent(TemelKUygulamasi.this,LimonluCheesecakeDetay.class);
                        startActivity(limonlucheesecake);
                        break;
                    case 44:
                        Intent hindistancevizliekler = new Intent(TemelKUygulamasi.this,HindistanCevizliEklerDetay.class);
                        startActivity(hindistancevizliekler);
                        break;
                    case 45:
                        Intent frambuazlicheesecake = new Intent(TemelKUygulamasi.this,FrambuazlıCheesecakeDetay.class);
                        startActivity(frambuazlicheesecake);
                        break;
                    case 46:
                        Intent findiklicookie = new Intent(TemelKUygulamasi.this,FındıklıCookieDetay.class);
                        startActivity(findiklicookie);
                        break;
                    case 47:
                        Intent cileklidonut = new Intent(TemelKUygulamasi.this,CilekliDonutDetay.class);
                        startActivity(cileklidonut);
                        break;
                    case 48:
                        Intent cikolatalimuffin = new Intent(TemelKUygulamasi.this,CikolataliMuffinDetay.class);
                        startActivity(cikolatalimuffin);
                        break;
                    case 49:
                        Intent cikolataliekler = new Intent(TemelKUygulamasi.this,CikolataliEklerDetay.class);
                        startActivity(cikolataliekler);
                        break;
                    case 50:
                        Intent cikolatalicookie = new Intent(TemelKUygulamasi.this,CikolataliCookieDetay.class);
                        startActivity(cikolatalicookie);
                        break;
                    case 51:
                        Intent cikolatalidonut = new Intent(TemelKUygulamasi.this,CikolataliDonutDetay.class);
                        startActivity(cikolatalidonut);
                        break;
                    case 52:
                        Intent karisiksandvic = new Intent(TemelKUygulamasi.this,KarisikSandvicDetay.class);
                        startActivity(karisiksandvic);
                        break;
                    case 53:
                        Intent peynirlisandvic = new Intent(TemelKUygulamasi.this,PeynirliSandvicDetay.class);
                        startActivity(peynirlisandvic);
                        break;
                    case 54:
                        Intent tereyaglikruvasan = new Intent(TemelKUygulamasi.this,TereyagliKruvasanDetay.class);
                        startActivity(tereyaglikruvasan);
                        break;
                    case 55:
                        Intent coktahillikruvasan = new Intent(TemelKUygulamasi.this,CokTahilliKruvasanDetay.class);
                        startActivity(coktahillikruvasan);
                        break;
                    case 56:
                        Intent findikkremalikruvasan = new Intent(TemelKUygulamasi.this,FindikKremaliKruvasanDetay.class);
                        startActivity(findikkremalikruvasan);
                        break;
                    case 57:
                        Intent balkancoregi = new Intent(TemelKUygulamasi.this,BalkanCoregiDetay.class);
                        startActivity(balkancoregi);
                        break;
                    default:
                        break;
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，12都是滑动
                if (newState == 0) {
                    isScrolled = false;
                } else {
                    isScrolled = true;
                }
                setMsg("isScrolled" + isScrolled + "--newState=" + newState);
            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法
                if (isScrolled) {
                    int top = manager.findFirstVisibleItemPosition();
                    int bottom = manager.findLastVisibleItemPosition();

                    int pos = 0;
                    if (bottom == list.size() - 1) {
                        //先判断滑到底部，tab定位到最后一个
                        pos = str1.length - 1;
                    } else if (top == str1[str1.length - 1]) {
                        //如果top等于指定的位置，对应到tab即可，
                        pos = str1[str1.length - 1];
                    } else {
                        //循环遍历，需要比较i+1的位置，所以循环长度要减1，
                        //  如果 i<top<i+1,  那么tab应该定位到i位置的字符，不管是向上还是向下滑动
                        for (int i = 0; i < str1.length - 1; i++) {
                            if (top == str1[i]) {
                                pos = i;
                                break;
                            } else if (top > str1[i] && top < str1[i + 1]) {
                                pos = i;
                                break;
                            }
                        }
                    }

                    //设置tab滑动到第pos个
                    tabLayout.setScrollPosition(pos, 0f, true);
                }

            }
        });

    }

    public static void setMsg(String str) {
        Log.i("tab", str);
    }

    private void initData() {
        list = new ArrayList<>();


        //En Çok Satılanlar
        list.add(new MainBean("Havuç Dilim Kek","2 dk","Tatlı  |","8.9 TL",R.drawable.havucludilimkek));
        list.add(new MainBean("Colombia Supremo","2 dk","Çekirdek Kahve  |","49.9 TL",R.drawable.colombiasupremo));
        list.add(new MainBean("Latte","2 dk","Standart  |","6.9 TL",R.drawable.latte));
        list.add(new MainBean("Filtre Kahve","1 dk","Standart  |","6.9 TL",R.drawable.filtrekahve));
        list.add(new MainBean("Karışık Sandviç","5 dk","Sandviç  |","8.9 TL",R.drawable.karisiksandvic));
        list.add(new MainBean("Peynirli Sandviç","5 dk","Sandviç  |","8.9 TL",R.drawable.peynirlisandvic));
        list.add(new MainBean("Snickers Caramel Frappe","3 dk","Büyük  |","10.9 TL",R.drawable.snickerscaramelfrappe));




        //Sıcaklar
        list.add(new MainBean("Espresso","3 dk","Single","6.9 TL",R.drawable.espressooo));
        list.add(new MainBean("Americano","3 dk","Büyük","8.9 TL",R.drawable.americanoo));
        list.add(new MainBean("Espresso Macchiato","3 dk","Standart","6.9 TL",R.drawable.espressomachiato));
        list.add(new MainBean("Cortado","3 dk","Standart","6.9 TL",R.drawable.cortado));
        list.add(new MainBean("Cappucino","3 dk","Standart","6.9 TL",R.drawable.cappucino));
        list.add(new MainBean("Latte","2 dk","Standart","6.9 TL",R.drawable.latte));
        list.add(new MainBean("Filtre Kahve","1 dk","Standart","6.9 TL",R.drawable.filtrekahve));
        list.add(new MainBean("Flat White","3 dk","Standart","6.9 TL",R.drawable.flatwhite));
        list.add(new MainBean("Chai Tea Latte","3 dk","Standart","6.9 TL",R.drawable.chaitealatte));
        list.add(new MainBean("Mocha","3 dk","Standart","6.9 TL",R.drawable.mocha));
        list.add(new MainBean("Chocolate Cookie Latte","3 dk","Büyük","8.9 TL",R.drawable.chocolatecookielatte));
        list.add(new MainBean("Caramel Macchiato","3 dk","Standart","6.9 TL",R.drawable.caramelmachiato));
        list.add(new MainBean("Sıcak Çikolata","3 dk","Standart","6.9 TL",R.drawable.sicakcikolata));
        list.add(new MainBean("Salted Caramel Latte","3 dk","Büyük","8.9 TL",R.drawable.saltedcaramellatte));
        list.add(new MainBean("Bal Kabaklı Latte","3 dk","Büyük","8.9 TL",R.drawable.balkabaklilatte));
        list.add(new MainBean("White Mocha","3 dk","Standart","6.9 TL",R.drawable.whitemocha));
        list.add(new MainBean("Türk Kahvesi","5 dk","Standart","8.9 TL",R.drawable.turkkahvesi));
        list.add(new MainBean("Earl Grey Çay","3 dk","Standart","6.9 TL",R.drawable.earlgreycay));
        list.add(new MainBean("Miel","3 dk","Standart","6.9 TL",R.drawable.miel));




        //Soğuklar
        list.add(new MainBean("Iced Salted Caramel Latte","3 dk","Büyük","8.9 TL",R.drawable.icedsaltedcaramellatte));
        list.add(new MainBean("Iced Chai Tea Latte","3 dk","Büyük","8.9 TL",R.drawable.icedchaitealatte));
        list.add(new MainBean("Iced Chocolate Cookie Latte","3 dk","Büyük","8.9 TL",R.drawable.icedchocolatecookielatte));
        list.add(new MainBean("Caramel Frappe","3 dk","Büyük","8.9 TL",R.drawable.caramelfrappe));
        list.add(new MainBean("Mocha Frappe","3 dk","Büyük","8.9 TL",R.drawable.mochafrappe));
        list.add(new MainBean("Espresso Frappe","3 dk","Standart","6.9 TL",R.drawable.espressofrappe));
        list.add(new MainBean("Iced Latte","3 dk","Büyük","8.9 TL",R.drawable.icedlatte));
        list.add(new MainBean("Iced Americano","3 dk","Büyük","8.9 TL",R.drawable.icedamericano));
        list.add(new MainBean("Oreo Mocha Frappe","3 dk","Büyük","10.9 TL",R.drawable.oreomochafrappe));
        list.add(new MainBean("Cold Brew","3 dk","Büyük","8.9 TL",R.drawable.coldbrew));
        list.add(new MainBean("Snickers Caramel Frappe","3 dk","Büyük","10.9 TL",R.drawable.snickerscaramelfrappe));




        //Yiyecekler
        list.add(new MainBean("Havuçlu Dilim Kek","2 dk","Tatlı","8.9 TL",R.drawable.havucludilimkek));
        list.add(new MainBean("Mozaik Dilim Kek","2 dk","Tatlı","8.9 TL",R.drawable.mozaikdilimkek));
        list.add(new MainBean("Mozaik Pasta","15 dk","Tatlı","6.9 TL",R.drawable.mozaikpasta));
        list.add(new MainBean("Şeker Taneli Donut","2 dk","Tatlı","6.9 TL",R.drawable.sekertanelidonut));
        list.add(new MainBean("Velvet Cupcake","2 dk","Tatlı","6.9 TL",R.drawable.velvetcupcake));
        list.add(new MainBean("Yaban Mersinli Muffin","2 dk","Tatlı","6.9 TL",R.drawable.yabanmersinlicupcake));
        list.add(new MainBean("Limonlu Cheesecake","2 dk","Tatlı","6.9 TL",R.drawable.limonlucheesecake));
        list.add(new MainBean("Hindistan Cevizli Ekler","2 dk","Tatlı","6.9 TL",R.drawable.hindistancevizliekler));
        list.add(new MainBean("Frambuazlı Cheesecake","2 dk","Tatlı","6.9 TL",R.drawable.frambuazlicheesecake));
        list.add(new MainBean("Fındıklı Cookie","2 dk","Tatlı","6.9 TL",R.drawable.findiklicookie));
        list.add(new MainBean("Çilekli Donut","2 dk","Tatlı","6.9 TL",R.drawable.cileklidonut));
        list.add(new MainBean("Çikolatalı Muffin","2 dk","Tatlı","6.9 TL",R.drawable.cikolatalimuffin));
        list.add(new MainBean("Çikolatalı Ekler","2 dk","Tatlı","6.9 TL",R.drawable.cikolataliekler));
        list.add(new MainBean("Çikolatalı Cookie","2 dk","Tatlı","6.9 TL",R.drawable.cikolatalicookie));
        list.add(new MainBean("Çikolatalı Donut","2 dk","Tatlı","6.9 TL",R.drawable.cikolatalidonut));
        list.add(new MainBean("Karışık Sandviç","5 dk","Sandviç","8.9 TL",R.drawable.karisiksandvic));
        list.add(new MainBean("Peynirli Sandviç","5 dk","Sandviç","8.9 TL",R.drawable.peynirlisandvic));
        list.add(new MainBean("Tereyağlı Kruvasan","2 dk","Kruvasan","6.9 TL",R.drawable.tereyaglikruvasan));
        list.add(new MainBean("Çok Tahıllı Kruvasan","2 dk","Kruvasan","6.9 TL",R.drawable.coktahillikruvasan));
        list.add(new MainBean("Fındık Kremalı Kruvasan","2 dk","Kruvasan","6.9 TL",R.drawable.findikkremalikruvasan));
        list.add(new MainBean("Balkan Çöreği","2 dk","Kruvasan","6.9 TL",R.drawable.balkancoregi));



    }
}