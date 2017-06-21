package emis.msf.cytaty;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ireneusz on 2017-05-12.
 */
public class FragmentUlubione extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_cytat_ulubione,
                container, false);
        ListView listView = (ListView) layoutView.findViewById(R.id.listView);

        DBHelperCytaty dbh = DBHelperCytaty.getInstance(getContext());


        listView.setAdapter(new ListViewAdapter(getContext(),
                dbh.getReadableDatabase().rawQuery("SELECT _id,cytat,day_id FROM cytaty WHERE fav=1",null)));

        return layoutView;
    }

    class ListViewAdapter extends CursorAdapter
    {

        public ListViewAdapter(Context context, Cursor c)
        {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View retView = inflater.inflate(R.layout.list_element, parent, false);

            return retView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor)
        {
            final long dayid = cursor.getLong(2);
            U.L(dayid +" is dayid");
            TextView tv = (TextView) view.findViewById(R.id.lblListItem);
            tv.setText('"'+cursor.getString(cursor.getColumnIndex(C.KOL_CYTAT))+'"');
            tv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ((ActivityMain)getActivity()).showBig(dayid,-1);
                }
            });


        }
    }
}
