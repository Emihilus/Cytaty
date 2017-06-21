package emis.msf.cytaty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ireneusz on 2017-04-14.
 */
public class ActivityCardFragment extends Fragment
{
   String text;
    static ActivityCardFragment init(String val)
    {
        ActivityCardFragment truitonFrag = new ActivityCardFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putString("text", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        text = getArguments() != null ? getArguments().getString("text") : "text";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.activity_card_fragment,
                container, false);
        TextView textView = (TextView) layoutView.findViewById(R.id.textViewa);
        textView.setText(text);
        Log.d("fragment", "Loaded "+ text.substring(0,4));
        return layoutView;
    }
}
