package emis.msf.cytaty;

import android.provider.BaseColumns;

/**
 * Created by Ireneusz on 2017-03-30.
 */
public class C implements BaseColumns
{
    public static final int WERSJA_BAZY_DANYCH = 5;

    public static final String PREF_INITDATE = "INITIAL_DATE";


    public static final String KOL_DAY_ID = "day_id";
    public static final String KOL_IN_CHAPTER_ID = "inch_id";
    public static final String KOL_CHAPTERID = "chapterid";
    public static final String KOL_CYTAT = "cytat";


    public static final int KAT_1_Cnoty = 1;
    public static final int KAT_2_Maryja = 2;
    public static final int KAT_3_MoPoZySaIDu = 3; //Modlitwa_pobożność_życie_sakramentalne_i_duchowe
    public static final int KAT_4_PoSzDlPrKaZa = 4; //Posłuszeństwo_szacunek_dla_przełożonych_karność_zakonna = 4;
    public static final int KAT_5_PyEgSkSiTyNaSoIWlZd = 5; //Pycha, egoizm, skupienie się tylko na sobie i własnym zdaniu
    public static final int KAT_6_SWIETA_RODZINA= 6;
    public static final int KAT_7_RozneWskazania = 7;
    public static final int KAT_8_IdealyDoskonaloscUswiecenie = 8; //Ideały, doskonałość uświęcenie
    public static final int KAT_9_PoDoZyZaUwDlZa = 9; //Powołanie do życia zakonnego, uwagi dla zakonników
    public static final int KAT_10_ZycieWspolnotyZakonnej = 10; //Życie wspólnoty zakonnej
    public static final int KAT_11_WoBoMiDoBoObDlWlWoISiSa = 11; //Wola Boża, miłość do Boga, obumarcie dla własnej woli i siebie samego
    public static final int KAT_12_MisjeIMisjonarze = 12;
    public static final int KAT_13_MiBlMiDoWy = 13; //Miłość bliźniego, miłosierdzie, dobre wychowanie
    public static final int KAT_14_NaukaIStudia = 14;
    public static final int KAT_15_KapKazMiLu = 15; //Kapłaństwo, kazania, misje ludowe
    public static final int KAT_16_Praca = 16;
    public static final int KAT_17_PoSkOp = 17; //Pokora, skromność, opanowanie
    public static final int KAT_18_ReKoRe = 18; //Reguła, Konstytucje, regulamin
    public static final int KAT_19_EMPTY = 19;
    public static final int KAT_20_UmPrNaISiSa = 20; //Umartwienie, przezwyciężenie natury i siebie samego
    public static final int KAT_21_WaIPrSiDoSt = 21; //Wady i przywiązanie się do stworzeń

}
