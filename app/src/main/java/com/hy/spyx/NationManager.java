package com.hy.spyx;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;

public class NationManager {
    private static class NationManagerHolder{
        /**
         * * 静态初始化器，由JVM来保证线程安全
         */
        private static NationManager instance = new NationManager();
    }

    public void init(Context context){
        mContext = context;
        loadNations();
    }
    public ArrayList<String> allNations(){
        Object[] nations = mNations.keySet().toArray();
        ArrayList<String> set = new ArrayList<String>();
        for(int i=0;i<nations.length;i++){
            set.add((String)nations[i]);
        }
        return set;
    }
    public GameNation getNation(String id){
        return mNations.get(id);
    }

    public HashSet<String> getandom(int count){
        Random ra =new Random();
        ArrayList<String> all = allNations();
        HashSet<String> set = new HashSet<String>();
        while (set.size()<count){
            String k = all.get(ra.nextInt(all.size()));
            String id = mNations.get(k).getId();
            if(!set.contains(id)){
                set.add(id);
            }
        }
        return set;
    }

    /**
     * 私有化构造方法
     * */
    private NationManager(){

    }

    public static  NationManager getInstance(){
        return NationManagerHolder.instance;
    }

    private void loadNations(){
        mNations = new HashMap<String,GameNation>();
        try {
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.data);
            InputStreamReader isr = null;
            isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bfr = new BufferedReader(isr);
            String ln;
            Gson gson = new Gson();
            while ((ln = bfr.readLine()) != null) {
                GameNation nation = new GameNation();
                nation = gson.fromJson(ln, GameNation.class);
                mNations.put(nation.getId(),nation);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNations.get("AFG").setResId(R.drawable.ic_flag_afg);
        mNations.get("ALB").setResId(R.drawable.ic_flag_alb);
        mNations.get("ALG").setResId(R.drawable.ic_flag_alg);
        mNations.get("AND").setResId(R.drawable.ic_flag_and);
        mNations.get("ANG").setResId(R.drawable.ic_flag_ang);
        mNations.get("ANT").setResId(R.drawable.ic_flag_ant);
        mNations.get("ARG").setResId(R.drawable.ic_flag_arg);
        mNations.get("ARM").setResId(R.drawable.ic_flag_arm);
        mNations.get("AUSL").setResId(R.drawable.ic_flag_ausl);
        mNations.get("AUSR").setResId(R.drawable.ic_flag_ausr);
        mNations.get("AZE").setResId(R.drawable.ic_flag_aze);
        mNations.get("BAHA").setResId(R.drawable.ic_flag_baha);
        mNations.get("BAHR").setResId(R.drawable.ic_flag_bahr);
        mNations.get("BAN").setResId(R.drawable.ic_flag_ban);
        mNations.get("BAR").setResId(R.drawable.ic_flag_bar);
        mNations.get("BELA").setResId(R.drawable.ic_flag_bela);
        mNations.get("BELG").setResId(R.drawable.ic_flag_belg);
        mNations.get("BELI").setResId(R.drawable.ic_flag_beli);
        mNations.get("BEN").setResId(R.drawable.ic_flag_ben);
        mNations.get("BHU").setResId(R.drawable.ic_flag_bhu);
        mNations.get("BOL").setResId(R.drawable.ic_flag_bol);
        mNations.get("BOS").setResId(R.drawable.ic_flag_bos);
        mNations.get("BOT").setResId(R.drawable.ic_flag_bot);
        mNations.get("BRA").setResId(R.drawable.ic_flag_bra);
        mNations.get("BRU").setResId(R.drawable.ic_flag_bru);
        mNations.get("BUL").setResId(R.drawable.ic_flag_bul);
        mNations.get("BURK").setResId(R.drawable.ic_flag_burk);
        mNations.get("BURU").setResId(R.drawable.ic_flag_buru);
        mNations.get("CAB").setResId(R.drawable.ic_flag_cab);
        mNations.get("CAMB").setResId(R.drawable.ic_flag_camb);
        mNations.get("CAME").setResId(R.drawable.ic_flag_came);
        mNations.get("CAN").setResId(R.drawable.ic_flag_can);
        mNations.get("CEN").setResId(R.drawable.ic_flag_cen);
        mNations.get("CHA").setResId(R.drawable.ic_flag_cha);
        mNations.get("CHI").setResId(R.drawable.ic_flag_chi);
        mNations.get("CHN").setResId(R.drawable.ic_flag_chn);
        mNations.get("COL").setResId(R.drawable.ic_flag_col);
        mNations.get("COM").setResId(R.drawable.ic_flag_com);
        mNations.get("COS").setResId(R.drawable.ic_flag_cos);
        mNations.get("COT").setResId(R.drawable.ic_flag_cot);
        mNations.get("CRO").setResId(R.drawable.ic_flag_cro);
        mNations.get("CUB").setResId(R.drawable.ic_flag_cub);
        mNations.get("CYP").setResId(R.drawable.ic_flag_cyp);
        mNations.get("CZE").setResId(R.drawable.ic_flag_cze);
        mNations.get("DEM").setResId(R.drawable.ic_flag_dem);
        mNations.get("DEN").setResId(R.drawable.ic_flag_den);
        mNations.get("DJI").setResId(R.drawable.ic_flag_dji);
        mNations.get("DOMA").setResId(R.drawable.ic_flag_doma);
        mNations.get("DOMN").setResId(R.drawable.ic_flag_domn);
        mNations.get("ECU").setResId(R.drawable.ic_flag_ecu);
        mNations.get("EGY").setResId(R.drawable.ic_flag_egy);
        mNations.get("ELS").setResId(R.drawable.ic_flag_els);
        mNations.get("EQU").setResId(R.drawable.ic_flag_equ);
        mNations.get("ERI").setResId(R.drawable.ic_flag_eri);
        mNations.get("EST").setResId(R.drawable.ic_flag_est);
        mNations.get("ETH").setResId(R.drawable.ic_flag_eth);
        mNations.get("FED").setResId(R.drawable.ic_flag_fed);
        mNations.get("FIJ").setResId(R.drawable.ic_flag_fij);
        mNations.get("FIN").setResId(R.drawable.ic_flag_fin);
        mNations.get("FRA").setResId(R.drawable.ic_flag_fra);
        mNations.get("GAB").setResId(R.drawable.ic_flag_gab);
        mNations.get("GAM").setResId(R.drawable.ic_flag_gam);
        mNations.get("GEO").setResId(R.drawable.ic_flag_geo);
        mNations.get("GER").setResId(R.drawable.ic_flag_ger);
        mNations.get("GHA").setResId(R.drawable.ic_flag_gha);
        mNations.get("GREE").setResId(R.drawable.ic_flag_gree);
        mNations.get("GREN").setResId(R.drawable.ic_flag_gren);
        mNations.get("GUA").setResId(R.drawable.ic_flag_gua);
        mNations.get("GUIN").setResId(R.drawable.ic_flag_guin);
        mNations.get("GUIB").setResId(R.drawable.ic_flag_guib);
        mNations.get("GUY").setResId(R.drawable.ic_flag_guy);
        mNations.get("HAI").setResId(R.drawable.ic_flag_hai);
        mNations.get("HON").setResId(R.drawable.ic_flag_hon);
        mNations.get("HUN").setResId(R.drawable.ic_flag_hun);
        mNations.get("ICE").setResId(R.drawable.ic_flag_ice);
        mNations.get("INDI").setResId(R.drawable.ic_flag_indi);
        mNations.get("INDO").setResId(R.drawable.ic_flag_indo);
        mNations.get("IRAN").setResId(R.drawable.ic_flag_iran);
        mNations.get("IRAQ").setResId(R.drawable.ic_flag_iraq);
        mNations.get("IRE").setResId(R.drawable.ic_flag_ire);
        mNations.get("ISR").setResId(R.drawable.ic_flag_isr);
        mNations.get("ITA").setResId(R.drawable.ic_flag_ita);
        mNations.get("JAM").setResId(R.drawable.ic_flag_jam);
        mNations.get("JAP").setResId(R.drawable.ic_flag_jap);
        mNations.get("JOR").setResId(R.drawable.ic_flag_jor);
        mNations.get("KAZ").setResId(R.drawable.ic_flag_kaz);
        mNations.get("KEN").setResId(R.drawable.ic_flag_ken);
        mNations.get("KIR").setResId(R.drawable.ic_flag_kir);
        mNations.get("KOS").setResId(R.drawable.ic_flag_kos);
        mNations.get("KUW").setResId(R.drawable.ic_flag_kuw);
        mNations.get("KYR").setResId(R.drawable.ic_flag_kyr);
        mNations.get("LAO").setResId(R.drawable.ic_flag_lao);
        mNations.get("LAT").setResId(R.drawable.ic_flag_lat);
        mNations.get("LEB").setResId(R.drawable.ic_flag_leb);
        mNations.get("LES").setResId(R.drawable.ic_flag_les);
        mNations.get("LIBE").setResId(R.drawable.ic_flag_libe);
        mNations.get("LIBY").setResId(R.drawable.ic_flag_liby);
        mNations.get("LIE").setResId(R.drawable.ic_flag_lie);
        mNations.get("LIT").setResId(R.drawable.ic_flag_lit);
        mNations.get("LUX").setResId(R.drawable.ic_flag_lux);
        mNations.get("MAC").setResId(R.drawable.ic_flag_mac);
        mNations.get("MAD").setResId(R.drawable.ic_flag_mad);
        mNations.get("MALW").setResId(R.drawable.ic_flag_malw);
        mNations.get("MALY").setResId(R.drawable.ic_flag_maly);
        mNations.get("MALD").setResId(R.drawable.ic_flag_mald);
        mNations.get("MALI").setResId(R.drawable.ic_flag_mali);
        mNations.get("MALT").setResId(R.drawable.ic_flag_malt);
        mNations.get("MAR").setResId(R.drawable.ic_flag_mar);
        mNations.get("MAUA").setResId(R.drawable.ic_flag_maua);
        mNations.get("MAUS").setResId(R.drawable.ic_flag_maus);
        mNations.get("MEX").setResId(R.drawable.ic_flag_mex);
        mNations.get("MOL").setResId(R.drawable.ic_flag_mol);
        mNations.get("MONA").setResId(R.drawable.ic_flag_mona);
        mNations.get("MONG").setResId(R.drawable.ic_flag_mong);
        mNations.get("MONT").setResId(R.drawable.ic_flag_mont);
        mNations.get("MOR").setResId(R.drawable.ic_flag_mor);
        mNations.get("MOZ").setResId(R.drawable.ic_flag_moz);
        mNations.get("MYA").setResId(R.drawable.ic_flag_mya);
        mNations.get("NAM").setResId(R.drawable.ic_flag_nam);
        mNations.get("NAU").setResId(R.drawable.ic_flag_nau);
        mNations.get("NEP").setResId(R.drawable.ic_flag_nep);
        mNations.get("NET").setResId(R.drawable.ic_flag_net);
        mNations.get("NEW").setResId(R.drawable.ic_flag_new);
        mNations.get("NIC").setResId(R.drawable.ic_flag_nic);
        mNations.get("NIGE").setResId(R.drawable.ic_flag_nige);
        mNations.get("NIGA").setResId(R.drawable.ic_flag_niga);
        mNations.get("NORT").setResId(R.drawable.ic_flag_nort);
        mNations.get("NORW").setResId(R.drawable.ic_flag_norw);
        mNations.get("OMA").setResId(R.drawable.ic_flag_oma);
        mNations.get("PAK").setResId(R.drawable.ic_flag_pak);
        mNations.get("PALA").setResId(R.drawable.ic_flag_pala);
        mNations.get("PALE").setResId(R.drawable.ic_flag_pale);
        mNations.get("PAN").setResId(R.drawable.ic_flag_pan);
        mNations.get("PAP").setResId(R.drawable.ic_flag_pap);
        mNations.get("PAR").setResId(R.drawable.ic_flag_par);
        mNations.get("PER").setResId(R.drawable.ic_flag_per);
        mNations.get("PHI").setResId(R.drawable.ic_flag_phi);
        mNations.get("POL").setResId(R.drawable.ic_flag_pol);
        mNations.get("POR").setResId(R.drawable.ic_flag_por);
        mNations.get("QAT").setResId(R.drawable.ic_flag_qat);
        mNations.get("REP").setResId(R.drawable.ic_flag_rep);
        mNations.get("ROM").setResId(R.drawable.ic_flag_rom);
        mNations.get("RUS").setResId(R.drawable.ic_flag_rus);
        mNations.get("RWA").setResId(R.drawable.ic_flag_rwa);
        mNations.get("SAM").setResId(R.drawable.ic_flag_sam);
        mNations.get("SAN").setResId(R.drawable.ic_flag_san);
        mNations.get("SAO").setResId(R.drawable.ic_flag_sao);
        mNations.get("SAU").setResId(R.drawable.ic_flag_sau);
        mNations.get("SEN").setResId(R.drawable.ic_flag_sen);
        mNations.get("SER").setResId(R.drawable.ic_flag_ser);
        mNations.get("SEY").setResId(R.drawable.ic_flag_sey);
        mNations.get("SIE").setResId(R.drawable.ic_flag_sie);
        mNations.get("SIN").setResId(R.drawable.ic_flag_sin);
        mNations.get("SLOK").setResId(R.drawable.ic_flag_slok);
        mNations.get("SLON").setResId(R.drawable.ic_flag_slon);
        mNations.get("SOL").setResId(R.drawable.ic_flag_sol);
        mNations.get("SOM").setResId(R.drawable.ic_flag_som);
        mNations.get("SOUA").setResId(R.drawable.ic_flag_soua);
        mNations.get("SOUK").setResId(R.drawable.ic_flag_souk);
        mNations.get("SOUS").setResId(R.drawable.ic_flag_sous);
        mNations.get("SPA").setResId(R.drawable.ic_flag_spa);
        mNations.get("SRI").setResId(R.drawable.ic_flag_sri);
        mNations.get("STK").setResId(R.drawable.ic_flag_stk);
        mNations.get("STL").setResId(R.drawable.ic_flag_stl);
        mNations.get("STV").setResId(R.drawable.ic_flag_stv);
        mNations.get("SUD").setResId(R.drawable.ic_flag_sud);
        mNations.get("SUR").setResId(R.drawable.ic_flag_sur);
        mNations.get("SWA").setResId(R.drawable.ic_flag_swa);
        mNations.get("SWE").setResId(R.drawable.ic_flag_swe);
        mNations.get("SWI").setResId(R.drawable.ic_flag_swi);
        mNations.get("SYR").setResId(R.drawable.ic_flag_syr);
        mNations.get("TAJ").setResId(R.drawable.ic_flag_taj);
        mNations.get("TAN").setResId(R.drawable.ic_flag_tan);
        mNations.get("THA").setResId(R.drawable.ic_flag_tha);
        mNations.get("TIM").setResId(R.drawable.ic_flag_tim);
        mNations.get("TOG").setResId(R.drawable.ic_flag_tog);
        mNations.get("TON").setResId(R.drawable.ic_flag_ton);
        mNations.get("TRI").setResId(R.drawable.ic_flag_tri);
        mNations.get("TUN").setResId(R.drawable.ic_flag_tun);
        mNations.get("TURK").setResId(R.drawable.ic_flag_turk);
        mNations.get("TURN").setResId(R.drawable.ic_flag_turn);
        mNations.get("TUV").setResId(R.drawable.ic_flag_tuv);
        mNations.get("UGA").setResId(R.drawable.ic_flag_uga);
        mNations.get("UKR").setResId(R.drawable.ic_flag_ukr);
        mNations.get("UNIA").setResId(R.drawable.ic_flag_unia);
        mNations.get("UNIK").setResId(R.drawable.ic_flag_unik);
        mNations.get("UNIS").setResId(R.drawable.ic_flag_unis);
        mNations.get("URU").setResId(R.drawable.ic_flag_uru);
        mNations.get("UZB").setResId(R.drawable.ic_flag_uzb);
        mNations.get("VAN").setResId(R.drawable.ic_flag_van);
        mNations.get("VAT").setResId(R.drawable.ic_flag_vat);
        mNations.get("VEN").setResId(R.drawable.ic_flag_ven);
        mNations.get("VIE").setResId(R.drawable.ic_flag_vie);
        mNations.get("YEM").setResId(R.drawable.ic_flag_yem);
        mNations.get("ZAM").setResId(R.drawable.ic_flag_zam);
        mNations.get("ZIM").setResId(R.drawable.ic_flag_zim);

    }
    private HashMap<String,GameNation> mNations;
    private Context mContext;
}
