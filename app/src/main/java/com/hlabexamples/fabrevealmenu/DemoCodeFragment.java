package com.hlabexamples.fabrevealmenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.hlab.fabrevealmenu.helper.RevealDirection;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.model.FABMenuItem;
import com.hlab.fabrevealmenu.view.FABRevealMenu;

import java.util.ArrayList;
import java.util.Objects;

public class DemoCodeFragment extends BaseFragment implements OnFABMenuSelectedListener {
    private ArrayList<FABMenuItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        builditems();

        final FABRevealMenu fabMenu = initFabMenu(view);

        fabMenu.setTitleVisible(true);
        fabMenu.setShowOverlay(false);
        fabMenu.setNormalMenu();

        RevealDirection currentRevealDirection = RevealDirection.LEFT;
        fabMenu.setMenuDirection(RevealDirection.LEFT);
        //currentRevealDirection = RevealDirection.UP;
        //fabMenu.setMenuDirection(RevealDirection.UP);
    }

    private FABRevealMenu initFabMenu(@NonNull View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        final FABRevealMenu fabMenu = view.findViewById(R.id.fabMenu);

        try {
            if (fab != null && fabMenu != null) {
                setFabMenu(fabMenu);
                fabMenu.setMenuItems(items);
                fabMenu.bindAnchorView(fab);
                fabMenu.setOnFABMenuSelectedListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fabMenu;
    }

    private void builditems() {
        items = new ArrayList<>();
        items.add(new FABMenuItem("Attachments", AppCompatResources.getDrawable(requireActivity(), R.drawable.ic_attachment)));
        items.add(new FABMenuItem("Images", AppCompatResources.getDrawable(getActivity(), R.drawable.ic_image)));
        items.add(new FABMenuItem("Places", AppCompatResources.getDrawable(getActivity(), R.drawable.ic_place)));
        items.add(new FABMenuItem("Emoticons", AppCompatResources.getDrawable(getActivity(), R.drawable.ic_emoticon)));
    }

    @Override
    public void onMenuItemSelected(View view, int id) {
        if (id >= 0 && items != null && items.size() > id) {
            Toast.makeText(getActivity(), items.get(id).getTitle() + "Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}