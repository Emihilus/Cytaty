package emis.msf.cytaty;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ireneusz on 2017-04-06.
 */
public class FragmentWszystkie extends Fragment
{


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Cytat>> listDataChild;
    DBHelperCytaty dbh;
    int savedPos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        savedPos = getArguments() != null ? getArguments().getInt("pos") : 0;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_cytat_kategorie,
                container, false);

        expListView = (ExpandableListView) layoutView.findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        //expListView.ex
        expListView.setSelection(savedPos);
        return layoutView;
    }

    private void prepareListData() {

        dbh = new DBHelperCytaty(getActivity());
        Cursor cursor = dbh.getReadableDatabase().rawQuery("SELECT * FROM cytaty", null);


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Cytat>>();

        // Adding child data
        listDataHeader.add(U.getKategoria(1));
        listDataHeader.add(U.getKategoria(2));
        listDataHeader.add(U.getKategoria(3));
        listDataHeader.add(U.getKategoria(4));
        listDataHeader.add(U.getKategoria(5));
        listDataHeader.add(U.getKategoria(6));
        listDataHeader.add(U.getKategoria(7));
        listDataHeader.add(U.getKategoria(8));
        listDataHeader.add(U.getKategoria(9));
        listDataHeader.add(U.getKategoria(10));
        listDataHeader.add(U.getKategoria(11));
        listDataHeader.add(U.getKategoria(12));
        listDataHeader.add(U.getKategoria(13));
        listDataHeader.add(U.getKategoria(14));
        listDataHeader.add(U.getKategoria(15));
        listDataHeader.add(U.getKategoria(16));
        listDataHeader.add(U.getKategoria(17));
        listDataHeader.add(U.getKategoria(18));
        //listDataHeader.add(U.getKategoria(19));
        listDataHeader.add(U.getKategoria(20));
        listDataHeader.add(U.getKategoria(21));



        // Adding child data
        List<Cytat> k1 = new ArrayList<Cytat>();
        List<Cytat> k2 = new ArrayList<Cytat>();
        List<Cytat> k3 = new ArrayList<Cytat>();
        List<Cytat> k4 = new ArrayList<Cytat>();
        List<Cytat> k5 = new ArrayList<Cytat>();
        List<Cytat> k6 = new ArrayList<Cytat>();
        List<Cytat> k7 = new ArrayList<Cytat>();
        List<Cytat> k8 = new ArrayList<Cytat>();
        List<Cytat> k9 = new ArrayList<Cytat>();
        List<Cytat> k10 = new ArrayList<Cytat>();
        List<Cytat> k11 = new ArrayList<Cytat>();
        List<Cytat> k12 = new ArrayList<Cytat>();
        List<Cytat> k13 = new ArrayList<Cytat>();
        List<Cytat> k14 = new ArrayList<Cytat>();
        List<Cytat> k15 = new ArrayList<Cytat>();
        List<Cytat> k16 = new ArrayList<Cytat>();
        List<Cytat> k17 = new ArrayList<Cytat>();
        List<Cytat> k18 = new ArrayList<Cytat>();
        List<Cytat> k20 = new ArrayList<Cytat>();
        List<Cytat> k21 = new ArrayList<Cytat>();

        while (cursor.moveToNext())
            {
            switch (cursor.getInt(cursor.getColumnIndex(C.KOL_CHAPTERID)))
                {
                case C.KAT_1_Cnoty:
                    k1.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_2_Maryja:
                    k2.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_3_MoPoZySaIDu:
                    k3.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_4_PoSzDlPrKaZa:
                    k4.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_5_PyEgSkSiTyNaSoIWlZd:
                    k5.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_6_SWIETA_RODZINA:
                    k6.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_7_RozneWskazania:
                    k7.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_8_IdealyDoskonaloscUswiecenie:
                    k8.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_9_PoDoZyZaUwDlZa:
                    k9.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_10_ZycieWspolnotyZakonnej:
                    k10.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_11_WoBoMiDoBoObDlWlWoISiSa:
                    k11.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_12_MisjeIMisjonarze:
                    k12.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_13_MiBlMiDoWy:
                    k13.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_14_NaukaIStudia:
                    k14.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_15_KapKazMiLu:
                    k15.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_16_Praca:
                    k16.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_17_PoSkOp:
                    k17.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_18_ReKoRe:
                    k18.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_20_UmPrNaISiSa:
                    k20.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                case C.KAT_21_WaIPrSiDoSt:
                    k21.add(new Cytat(cursor.getInt(cursor.getColumnIndex(C.KOL_DAY_ID)),cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))));
                    break;

                }
            }

        // Header, Child data
        listDataChild.put(listDataHeader.get(0), k1);
        listDataChild.put(listDataHeader.get(1), k2);
        listDataChild.put(listDataHeader.get(2), k3);
        listDataChild.put(listDataHeader.get(3), k4);
        listDataChild.put(listDataHeader.get(4), k5);
        listDataChild.put(listDataHeader.get(5), k6);
        listDataChild.put(listDataHeader.get(6), k7);
        listDataChild.put(listDataHeader.get(7), k8);
        listDataChild.put(listDataHeader.get(8), k9);
        listDataChild.put(listDataHeader.get(9), k10);
        listDataChild.put(listDataHeader.get(10), k11);
        listDataChild.put(listDataHeader.get(11), k12);
        listDataChild.put(listDataHeader.get(12), k13);
        listDataChild.put(listDataHeader.get(13), k14);
        listDataChild.put(listDataHeader.get(14), k15);
        listDataChild.put(listDataHeader.get(15), k16);
        listDataChild.put(listDataHeader.get(16), k17);
        listDataChild.put(listDataHeader.get(17), k18);
        listDataChild.put(listDataHeader.get(18), k20);
        listDataChild.put(listDataHeader.get(19), k21);
    }

    class ExpandableListAdapter extends BaseExpandableListAdapter
    {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<Cytat>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<Cytat>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final long dayid = ((Cytat) getChild(groupPosition, childPosition)).dayid;
            final String childText = ((Cytat) getChild(groupPosition, childPosition)).cytat;

            if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_element, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.lblListItem);

            txtListChild.setText('"'+childText+'"');
            convertView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ((ActivityMain)getActivity()).showBig(dayid, expListView.getFirstVisiblePosition());
                }
            });
            convertView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    Cursor cursos = dbh.getReadableDatabase().rawQuery("SELECT fav FROM cytaty WHERE day_id="+dayid, null);
                    cursos.moveToFirst();
                    if (cursos.getInt(0) == 1)
                        {
                        Cursor untogle = dbh.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=0 WHERE day_id="+dayid,null);
                        untogle.moveToFirst();
                        untogle.close();
                        Toast.makeText(_context, "Usunięto z ulubionych", Toast.LENGTH_SHORT).show();
                        }
                    else
                        {
                        Cursor untogle = dbh.getWritableDatabase().rawQuery("UPDATE cytaty SET fav=1 WHERE day_id="+dayid,null);
                        untogle.moveToFirst();
                        untogle.close();
                        Toast.makeText(_context, "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
                        }
                    ((ActivityMain)getActivity()).queneNDSC = true;
                    cursos.close();
                    return true;
                }
            });
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
    
    class Cytat
    {
        String cytat;
        long dayid;
        
        Cytat (int dayid, String cytat)
        {
            this.dayid = dayid;
            this.cytat = cytat;
        }
    }

}

