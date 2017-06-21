package emis.msf.cytaty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ireneusz on 2017-05-18.
 */
public class FragmentBerthier extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_berthier,
                container, false);
        TextView tv = (TextView) layoutView.findViewById(R.id.tttx);
        tv.setText(Html.fromHtml(getString(R.string.wlt)));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        layoutView.findViewById(R.id.z_berthier).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getActivity(), ShowPhotoActivity.class);
                getActivity().startActivity(myIntent);
            }
        });


        return layoutView;
    }

}
