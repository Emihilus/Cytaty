package emis.msf.cytaty;

import android.util.Log;

/**
 * Created by Ireneusz on 2017-04-14.
 */
public class U
{
    static String getKategoria(int kategoria)
    {
        switch (kategoria)
            {
            case C.KAT_1_Cnoty:
                return "Cnoty";


            case C.KAT_2_Maryja:
                return "Maryja";
            

            case C.KAT_3_MoPoZySaIDu:
                return "Modlitwa, pobożnożność, życie sakramentalne i duchowe";
            

            case C.KAT_4_PoSzDlPrKaZa:
                return "Posłuszeństwo, szacunek dla przełożonych, karność zakonna";
            

            case C.KAT_5_PyEgSkSiTyNaSoIWlZd:
                return "Pycha, egoizm, skupienie się tylko na sobie i własnym zdaniu";
            

            case C.KAT_6_SWIETA_RODZINA:
                return "Święta Rodzina";
            

            case C.KAT_7_RozneWskazania:
                return "Różne wskazania";
            

            case C.KAT_8_IdealyDoskonaloscUswiecenie:
                return "Ideały, doskonałość, uświęcenie";
            

            case C.KAT_9_PoDoZyZaUwDlZa:
                return "Powołanie do życia zakonnego, uwagi dla zakonników";
            

            case C.KAT_10_ZycieWspolnotyZakonnej:
                return "Życie wspólnoty zakonnej";
            

            case C.KAT_11_WoBoMiDoBoObDlWlWoISiSa:
                return "Wola Boża, miłość do Boga, obumarcie dla własnej woli i siebie samego";
            

            case C.KAT_12_MisjeIMisjonarze:
                return "Misje i misjonarze";
            

            case C.KAT_13_MiBlMiDoWy:
                return "Miłość bliźniego, miłosierdzie, dobre wychowanie";
            

            case C.KAT_14_NaukaIStudia:
                return "Nauka i studia";
            

            case C.KAT_15_KapKazMiLu:
                return "Kapłaństwo, kazania, misje ludowe";
            

            case C.KAT_16_Praca:
                return "Praca";
            

            case C.KAT_17_PoSkOp:
                return "Pokora, skromność, opanowanie";
            

            case C.KAT_18_ReKoRe:
                return "Reguła, Konstytucje, regulamin";
            

            case C.KAT_19_EMPTY:
                return "Cnoty";
            

            case C.KAT_20_UmPrNaISiSa:
                return "Umartwienie, przezwyciężenie natury i siebie samego";
            

            case C.KAT_21_WaIPrSiDoSt:
                return "Wady i przywiązanie się do stworzeń";
            

            default:
                return "Cnoty";
            }
    }

    static void L (String msg)
    {
        Log.i("Uni-Log", msg);
    }

    static String editWidgetPreference(String preference, int what, String newValue)
    {
        String[] table = preference.split(",");
        if(what >= table.length)
            return preference;

        table[what] = newValue;

        String returnS="";
        for(String pr : table)
            {
            returnS+=pr+",";
            }
        return returnS;
    }

    static String getWidgetPreferenceValue(String prefobj, int what)
    {
        String[] table = prefobj.split(",");
        if(what >= table.length)
            return "1";
        return table[what];
    }
}
