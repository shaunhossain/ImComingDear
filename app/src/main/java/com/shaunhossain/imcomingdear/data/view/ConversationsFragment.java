package com.shaunhossain.imcomingdear.data.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.view.adapter.ConversationAdapter;
import com.shaunhossain.imcomingdear.data.view.adapter.MatchAdapter;
import com.shaunhossain.imcomingdear.data.view.viewmodel.MatchViewModel;

/**
 * Created by adriaboschsaez on 17/11/2017.
 */

public class ConversationsFragment extends Fragment implements SearchView.OnQueryTextListener {

    public static Fragment newInstance() {

        Fragment fragment = new ConversationsFragment();
        return fragment;
    }

    private MatchViewModel matchViewModel;

    private ConversationAdapter conversationAdapter;
    private MatchAdapter matchAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_chat);

        matchViewModel = ViewModelProviders.of(getActivity()).get(MatchViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_conversations, container, false);

        initUI(view);

        return view;
    }

    private void initUI(View view) {

        RecyclerView matchRecycle = view.findViewById(R.id.fragment_conversations_recycler_match);

        matchAdapter = new MatchAdapter(getContext());
        matchAdapter.setListener((v) -> {

            int position = matchRecycle.getChildAdapterPosition(v);
            User user = matchAdapter.getItemAt(position);

            Intent intent = new Intent(getActivity(), ChatActivity.class);

            intent.putExtra(getString(R.string.intent_extra_user), user);

            startActivity(intent);
        });

        matchRecycle.setAdapter(matchAdapter);
        matchRecycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        matchViewModel.getUserList().observe(this, (matchList) -> {

            matchAdapter.setContent(matchList);
            matchAdapter.notifyDataSetChanged();
        });



        RecyclerView conversationRecycle = view.findViewById(R.id.fragment_conversations_recycler_conversation);

        conversationAdapter = new ConversationAdapter(getContext());
        conversationAdapter.setListener((v) -> {

            int position = conversationRecycle.getChildAdapterPosition(v);
            User user = conversationAdapter.getItemAt(position);

            Intent intent = new Intent(getActivity(), ChatActivity.class);

            intent.putExtra(getString(R.string.intent_extra_user), user);

            startActivity(intent);
        });

        conversationRecycle.setAdapter(conversationAdapter);
        conversationRecycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        matchViewModel.getUserList().observe(this, (matchList) -> {

            conversationAdapter.setContent(matchList);
            conversationAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menu_search_search);
        SearchView searchView = (SearchView) item.getActionView();

        //Fix the bug of search view clear button
        ImageView searchClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.ic_clear_white_24px);

        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        matchAdapter.filter(query);
        conversationAdapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        matchAdapter.filter(query);
        conversationAdapter.filter(query);
        return true;
    }
}
