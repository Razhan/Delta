package com.deltastudio.ran.deltalibrary.widget.recycleview;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ran on 4/14/16.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private static final int PENDING_REMOVAL_TIMEOUT = 3 * 1000;

    protected final List<T> mData;
    protected final Context mContext;
    protected LayoutInflater mInflater;

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;
    private RemoveListener mRemoveListener;

    boolean undoOn;
    private List<T> itemsPendingRemoval;
    private Handler handler;
    private HashMap<T, Runnable> pendingRunnables;

    public BaseRecyclerAdapter(Context ctx, List<T> list) {
        mData = (list != null) ? list : new ArrayList<>();
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);

        undoOn = false;
        itemsPendingRemoval = new ArrayList<>();
        handler = new Handler();
        pendingRunnables = new HashMap<>();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerViewHolder holder = new RecyclerViewHolder(mContext,
                mInflater.inflate(getItemLayoutId(viewType), parent, false));

        if (mClickListener != null) {
            holder.itemView.setOnClickListener((v) ->
                mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition(), mData.get(holder.getLayoutPosition()))
            );
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener((v) -> {
                mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition(), mData.get(holder.getLayoutPosition()));
                return true;
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        bindData(holder, position, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void set(@NonNull List<T> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void add(@NonNull List<T> more) {
        if (!more.isEmpty()) {
            int currentSize = mData.size();
            int amountInserted = more.size();

            mData.addAll(more);

            if (mData.size() > more.size()) {
                notifyItemRangeInserted(currentSize, amountInserted);
            } else {
                notifyDataSetChanged();
            }
        }
    }

    public T get(int pos) {
        return mData.get(pos);
    }

    public List<T> getItems() {
        return mData;
    }

    public void delete(int pos) {
        T item = mData.get(pos);

        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }

        if (mData.contains(item)) {
            mData.remove(pos);
        }

        notifyItemRemoved(pos);
    }

    public void clear() {
        mData.clear();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    public void setRemoveListener(RemoveListener listener) {
        this.mRemoveListener = listener;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(RecyclerViewHolder holder, int position, T item);

    public interface OnItemClickListener<T> {
        void onItemClick(View itemView, int pos, T item);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View itemView, int pos, T item);
    }

    public interface RemoveListener<T> {
        void onRemove(int pos, T item);
    }

    public void setUndoOn(boolean undoOn) {
        this.undoOn = undoOn;
    }

    private boolean isUndoOn() {
        return undoOn;
    }

    private void pendingRemoval(int position) {
        final T item = mData.get(position);
        if (!itemsPendingRemoval.contains(item)) {

            itemsPendingRemoval.add(item);
            notifyItemChanged(position);

            Runnable pendingRemovalRunnable = () -> remove(mData.indexOf(item));
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    private void remove(int position) {
        if (mRemoveListener != null) {
            mRemoveListener.onRemove(position, mData.get(position));
        }
    }

    private boolean isPendingRemoval(int position) {
        T item = mData.get(position);
        return itemsPendingRemoval.contains(item);
    }

}