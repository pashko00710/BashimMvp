package com.example.pashko00710.bashimmvp.ui;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.design.widget.Snackbar;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.view.View;import android.widget.RelativeLayout;import android.widget.TextView;import com.example.pashko00710.bashimmvp.R;import com.example.pashko00710.bashimmvp.core.storage.entities.QuoteEntity;import com.example.pashko00710.bashimmvp.ui.base.BaseActivity;import com.example.pashko00710.bashimmvp.ui.presenters.MainActivityPresenter;import com.example.pashko00710.bashimmvp.ui.recyclerview.DividerItemDecoration;import com.example.pashko00710.bashimmvp.ui.recyclerview.QuotesAdapter;import com.example.pashko00710.bashimmvp.ui.views.MainActivityView;import java.util.List;import javax.inject.Inject;import butterknife.BindString;import butterknife.BindView;import butterknife.ButterKnife;public class MainActivity extends BaseActivity implements MainActivityView {    @Inject    protected MainActivityPresenter presenter;    @Inject    protected QuotesAdapter quotesAdapter;    @BindView(R.id.main_activity_layout)    protected RelativeLayout rootLayout;    @BindView(R.id.list_of_quotes)    protected RecyclerView recyclerView;    @BindView(R.id.empty_list)    protected TextView emptyListView;    @BindString(R.string.no_internet_error)    protected String noInternetErrorMsg;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        ButterKnife.bind(this);        getActivityComponent().inject(this);        presenter.attach(this);        presenter.loadQuotes();        recyclerView.setLayoutManager(new LinearLayoutManager(this));        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));        recyclerView.setAdapter(quotesAdapter);    }    @Override    protected void onStart() {        super.onStart();        presenter.getQuotes();    }    @Override    protected void onDestroy() {        super.onDestroy();        presenter.detach();    }    @Override    public void showQuotes(@NonNull List<QuoteEntity> quoteEntities) {        emptyListView.setVisibility(View.GONE);        recyclerView.setVisibility(View.VISIBLE);        quotesAdapter.setQuoteEntities(quoteEntities);        quotesAdapter.notifyDataSetChanged();    }    @Override    public void showQuotesIsEmpty() {        recyclerView.setVisibility(View.GONE);        emptyListView.setVisibility(View.VISIBLE);    }    @Override    public void showInternetNotAvailable() {        Snackbar.make(rootLayout, noInternetErrorMsg, Snackbar.LENGTH_LONG).show();    }}