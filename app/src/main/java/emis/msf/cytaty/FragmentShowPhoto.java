package emis.msf.cytaty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Ireneusz on 2017-05-31.
 */
public class FragmentShowPhoto extends Fragment
{
    int providedFN;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        providedFN = getArguments() != null ? getArguments().getInt("FN") : 0;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View layoutView = inflater.inflate(R.layout.fragment_photo,
                container, false);

        PhotoView img = (PhotoView) layoutView.findViewById(R.id.pimage);

        switch (providedFN)
            {
            case 0:
                img.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.b1));
                break;

            case 1:
                img.setImageResource(R.drawable.b2);
                break;

            case 2:
                img.setImageResource(R.drawable.b3);
                break;
            }

        return layoutView;
    }
}
