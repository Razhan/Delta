package com.deltastudio.ran.delta;

import android.content.Context;

import com.deltastudio.ran.delta.data.model.News;
import com.deltastudio.ran.deltalibrary.widget.recycleview.BaseRecyclerAdapter;
import com.deltastudio.ran.deltalibrary.widget.recycleview.RecyclerViewHolder;


import java.util.List;

/**
 * Created by ranzh on 7/1/2016.
 */
public class NewsAdapter extends BaseRecyclerAdapter<News.DatasBean> {

    public NewsAdapter(Context context, List<News.DatasBean> list) {
        super(context, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_news;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, News.DatasBean item) {
        holder.setText(R.id.news_title, item.getTitle())
                .setText(R.id.news_summary, item.getSummary())
                .setImage(R.id.news_image, item.getPic());
    }

}
