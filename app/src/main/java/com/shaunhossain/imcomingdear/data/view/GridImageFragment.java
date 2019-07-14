package com.shaunhossain.imcomingdear.data.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.view.adapter.GridImageAdapter;

/**
 * Created by adriaboschsaez on 16/02/2018.
 */

public class GridImageFragment extends Fragment {

    public static Fragment newInstance() {
        Fragment photosFragment = new GridImageFragment();
        return photosFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_image, container, false);

        initializeUI(view);
        return view;
    }

    private void initializeUI(View view) {

        GridView gridView = view.findViewById(R.id.gridview);

        String[] photos = {
                "http://www.apatita.com/rutas/Huesca/monte_perdido_goriz/fotos/28_monte_perdido.jpg",
                "http://guidevaldaran.com/wp-content/uploads/2017/10/monte-perdido.jpg",
                "http://www.nationalgeographic.com.es/medio/2016/02/25/monte-perdido_960x648_c588e7da.jpg",
                "http://zetaestaticos.com/aragon/img/noticias/0/791/791960_1.jpg",
                "http://www.komandokroketa.org/cilindro/74-5-Monte-Perdido-Escaleras-glaciar.jpg",
                "http://3.bp.blogspot.com/-UDUCPoasrxc/UvH2D2RfA2I/AAAAAAAAL4w/VVTkx8N0QDo/s1600/perdido+desde+el+cuello+del+cilindro.jpg"
        };

        GridImageAdapter gridImageAdapter = new GridImageAdapter(getActivity(), photos);
        gridView.setAdapter(gridImageAdapter);

    }
}
