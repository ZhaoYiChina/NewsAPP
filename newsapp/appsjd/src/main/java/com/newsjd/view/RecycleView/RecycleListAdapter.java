package com.newsjd.view.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.network.bean.NewsData;
import com.newsjd.R;
import com.newsjd.view.webview.WebActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sjd on 2017/7/17.
 */

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<NewsData> mDatas;

    public RecycleListAdapter(Context context, List<NewsData> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override //创建
    public RecycleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_item_new, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_time.setText("更新于" + dateFormat(mDatas.get(position).getTime()));
        holder.tv_title.setText(mDatas.get(position).getTitle());
//        holder.tv_link.setTag("链接:"+mDatas.get(position).getLink());
        final Intent intent = new Intent(mContext, WebActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("link", mDatas.get(position).getLink());
        holder.tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });
//        holder.tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tv_description.setText(mDatas.get(position).getText());
//        holder.tv_img.setBackgroundResource(R.mipmap.null_pic);
//        holder.tv_img.setImageURI( Uri.parse("http://"+mDatas.get(position).getImg()));
        Glide.with(mContext)
                .load("http://" + mDatas.get(position).getImg()).placeholder(R.mipmap.null_pic).error(R.mipmap.null_pic)
                .into(holder.tv_img);

        initImage(mDatas.get(position).getImg(), holder.tv_img);
    }

    protected void setUpItemEvent(final ViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();//这里要注意 ， item 根据holder来获取，不是通过传输的数据
                    mOnItemClickListener.onItemClick(view, layoutPosition);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(view, layoutPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //添加onclick 接口
    //在 onBindViewHolder 进行回调
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time;
        public TextView tv_title;
        public Button tv_link;
        public TextView tv_description;
        public ImageView tv_img;

        public ViewHolder(View v) {
            super(v);
            tv_time = v.findViewById(R.id.tv_time);
            tv_title = v.findViewById(R.id.tv_title);
            tv_link = v.findViewById(R.id.tv_link);
            tv_description = v.findViewById(R.id.tv_description);
            tv_img = v.findViewById(R.id.tv_img);
        }
    }

    private static final String TAG = "RecycleListAdapter";

    public static Bitmap getImage(String urlString) {
        Log.e(TAG, "getImage: " + urlString);
        URL url = null;
        Bitmap bmp = null;
        if (TextUtils.isEmpty(urlString)) {
            return bmp;
        }
        try {
            url = new URL("http://" + urlString);
        } catch (MalformedURLException e) {
        }
        try {
            assert url != null;
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static String dateFormat(String dateString) {
        String inputText = dateString;
        SimpleDateFormat inputFormat = new SimpleDateFormat
                ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat outputFormat =
                new SimpleDateFormat("MM月dd日HH时mm分");
        // Adjust locale and zone appropriately
        Date date1 = null;
        try {
            date1 = inputFormat.parse(inputText);
//            System.out.println(date1.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String outputText = outputFormat.format(date1);
//        System.out.println(outputText);
        return outputText;
    }


    public static void initImage(String img, final ImageView tv_img) {
        Observable.just(img).flatMap(new Func1<String, Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call(String s) {
                return Observable.just(getImage(s));
            }
        }).subscribeOn(Schedulers.computation()) // 指定 subscribe() 发生在 运算 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        if (bitmap == null) {
                            tv_img.setVisibility(View.GONE);
                        } else {
                            tv_img.setImageBitmap(bitmap);
                        }
                    }
                });
    }
}
