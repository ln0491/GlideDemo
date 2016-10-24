## Glide入门教程一（基本使用）

### 添加依赖
```
compile 'com.github.bumptech.glide:glide:3.7.0'
```

### 简单介绍

* with(Context context)- Context是许多Android API需要调用的， Glide也不例外。这里Glide非常方便，你可以任意传递一个Activity或者Fragment对象，它都可以自动提取出上下文。
* load(String imageUrl) - 这里传入的是你要加载的图片的URL，大多数情况下这个String类型的变量会链接到一个网络图片。
* into(ImageView targetImageView) - 将你所希望解析的图片传递给所要显示的ImageView


### 加载url网络图片
* 权限
```
<uses-permission android:name="android.permission.INTERNET"/>
```
* 加载图片

```
 Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg").into(mIv1);
```
这几行！如果这个URL链接的图片的确存在，并且你的ImageView可见，你将会在1~2秒见到这张图片被加载。假如这张图片不存在，Glide会回调相应的出错接口下面说



### 加载本地图片

* File 文件
```
 //本地图片路径 /storage/emulated/0/1470634823290.jpg
        String path ="/storage/emulated/0/1470634823290.jpg";

        File file = new File(path);

        Glide.with(this).load(file).into(mIv3);
```
* 转换的URI
```
 //本地图片路径 /storage/emulated/0/1470634823290.jpg
        String path ="/storage/emulated/0/1470634823290.jpg";

        File file = new File(path);

       // Glide.with(this).load(file).into(mIv3);
        Uri uri = Uri.fromFile(file);


       Glide.with(this).load(uri).into(mIv3);
```
如果图片不显示建议设置要控件的宽与高，后面再说怎么解决



### 加载资源图片
这里是一个Int型的的资源id。
```
Glide.with(this).load(R.mipmap.test).into(mIv2);
```



-------



## Glide入门教程二ListView中使用


### 添加RecyclerView

* 准备数据

```

    /**
     * 准备数据
     */
    String [] mDatas = new String[]{

            "http://b337.photo.store.qq.com/psb?/V10FcMmY1Ttz2o/7.fo01qLQ*SI59*E2Wq.j82HuPfes*efgiyEi7mrJdk!/b/dLHI5cioAQAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
            "http://b118.photo.store.qq.com/psb?/V10FcMmY2gHuOI/8*6eK6PHCNTx1utXooId*KAWgwPTllj.b6uBg4McCwM!/b/dAt8W0YJJAAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
            "http://img1.imgtn.bdimg.com/it/u=488611129,2377736106&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3398443685,2594061265&fm=11&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2271902832,1324672617&fm=21&gp=0.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D200/sign=d20242020e24ab18ff16e63705fae69a/267f9e2f070828389f547b30bf99a9014c08f1bd.jpg",
            "http://img5.duitang.com/uploads/item/201406/28/20140628132554_UNE4n.thumb.700_0.jpeg",
            "http://cdn.duitang.com/uploads/item/201309/22/20130922202150_ntvAB.thumb.600_0.jpeg",
            "http://cdn.duitang.com/uploads/item/201208/04/20120804013554_yRGfe.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=2050390856,2980742959&fm=21&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201501/23/20150123204322_N8nw5.jpeg",
            "http://img4q.duitang.com/uploads/item/201505/09/20150509204813_nEwxF.jpeg",
            "http://img1.imgtn.bdimg.com/it/u=2432702027,3704029716&fm=21&gp=0.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg"



    };
```
* Activity

```
 private void initData() {


        mGlideListUseAdapter = new GlideListUseAdapter(this,mDatas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);


        mRecyclerView.setAdapter(mGlideListUseAdapter);


    }
```

* adapter

> 布局
```


    <ImageView
               android:layout_margin="2dp"
               android:id="@+id/iv"
               android:layout_width="200dp"
               android:layout_height="200dp">

    </ImageView>
```

> adapter
```android
public class GlideListUseAdapter extends RecyclerView.Adapter<GlideListUseAdapter.ViewHolder> {


    Context      mContext;
    String [] mDatas;

    LayoutInflater mInflater;

    public GlideListUseAdapter(Context context, String [] datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.item_glide_list_use,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = mDatas[position];


        Glide.with(mContext).load(url).into(holder.mIv);

    }

    @Override
    public int getItemCount() {
        if(mDatas!=null){

           return mDatas.length;
        }
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIv;
        public ViewHolder(View itemView) {

            super(itemView);

            mIv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}

```

这样图片就显示出来了

## Glide入门教程三 占位图-淡入淡出效果-添加动画

* 占位图
网络的环境不好，加载过程可能需要花费大量的时间。这时候就需要一个占位图先显示出来，直到实际的图片加载并处理完毕。

```
Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .into(holder.mIv); //显示在哪个控件中
```

* 出错的占位图 .error()
假设我们的app尝试从网页加载一张图片，但网页不可访问,Glide会给我们选项去进行出错的回调，并采取合适的行动

```
  Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                 .into(holder.mIv); //显示在哪个控件中
```

加载前显示placeholder中的图片资源，加载失败就显示error中的图片资源
error()可以接受的只能是已经被初始化的图片资源或者指向图片资源的id

### 渐变动画 .crossFade()

crossFade()
```

        Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .crossFade(5000)                 //淡入淡出 可以不写默认300
                 .into(holder.mIv); //显示在哪个控件中
```

淡入淡出 -不写默认也会有值是300毫秒
crossFade()方法有另外一个特征：.crossFade(int duration),如果你想要减慢（或加快）动画，随便传入一个毫秒级的时间进去感受一下。
![crossfade.gif](crossfade.gif)

#### dontAnimate()的使用
如果你只是直接显示图片，而不需要crossfade效果，那就在Glide的请求构造里调用.dontAnimate()



### animate 添加自定义的动画

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <alpha android:fromAlpha="0"
           android:duration="3000"
            android:toAlpha="1"/>

    <scale android:fromXScale="0"
           android:fromYScale="0"
           android:pivotX="50%"
           android:pivotY="50%"
           android:toXScale="100%"
           android:toYScale="100%"
           android:duration="3000"/>

    <rotate android:pivotY="50%"
            android:pivotX="50%"
            android:fromDegrees="30"
            android:toDegrees="360"
            android:duration="3000"
        />

</set>
```

* 添加
```

        Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图

                .animate(R.anim.my_alpha)
                 .into(holder.mIv); //显示在哪个控件中

```

![animate.gif](animate.gif)


这些参数都是独立的，并且设置不依赖彼此。例如，你可以只设置.error()，而不用调用.placeholder()。你可以设置crossFade()动画，而不用设置占位图。参数的任意结合都是可行的



## Glide入门教程四-调整图片大小

### 调整图片大小 override()

Picasso也有同样的能力,resize(x, y)但需要调用fit()方法

用Glide时，如果图片不需要自动适配ImageView，调用override(horizontalSize, verticalSize)，它会在将图片显示在ImageView之前调整图片的大小。

```
   int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height   = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
        Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width,height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.my_alpha)

                 .into(holder.mIv); //显示在哪个控件中

```

### 缩放图片-centerCrop

Glide提供了变换去处理图片显示，通过设置centerCrop 和 fitCenter，可以得到两个不同的效果。


CenterCrop()会缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分。ImageVIew会被完全填充满，但是图片可能不能完全显示出。
```
  String url = mDatas[position];


        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height   = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());
        Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width,height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.my_alpha)
                .centerCrop()
                 .into(holder.mIv); //显示在哪个控件中

```

![animate.gif](animate.gif)
### 缩放图片-fitCenter
为ImageView加个背景色
```xml

    <ImageView
        android:background="#ff0000"
               android:layout_margin="2dp"
               android:id="@+id/iv"
               android:layout_width="200dp"
               android:layout_height="200dp"/>
```

fitCenter()会缩放图片让两边都相等或小于ImageView的所需求的边框。图片会被完整显示，可能不能完全填充整个ImageView。

```
Glide.with(mContext) //上下文
                .load(url) //图片地址
                .placeholder(R.mipmap.pictures_no) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width,height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.my_alpha)
                .centerCrop()
                .fitCenter()
                 .into(holder.mIv); //显示在哪个控件中
```



![fitcenter.gif](fitcenter.gif)
![fitcenter1.png](fitcenter1.png)



centerCrop-fitCenter会相互覆盖，后面调用的后覆盖前面调用的效果


## Glide入门教程五-加载gif-视频快照-缩略图

### 播放 网络Gif动画

```
 //加载网络gif
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.mipmap.pictures_no).into(mIv4);
```

### 本地资源gif

```
 //加载资源gif
        Glide.with(this).load(R.mipmap.loading).asGif().placeholder(R.mipmap.pictures_no).into(mIv5);

```

### 加载本地gif文件


```

        //加载本地gif文件
        String gifPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Movies/test.gif";
        //test.gif  animate.gif Starry_Night.mp4 test.mp4

        File gifFile = new File(gifPath);

        Glide.with(this).load(gifFile).placeholder(R.mipmap.ic_launcher).into(mIv6);

```

### 加载本地视频快照

```
 //加载本地小视频 快照，只是快照 并不能播放
        String videoPath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/Movies/test.mp4";;

        File videoFile = new File(videoPath);

        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.mipmap.ic_launcher).into(mIv7);
```

### 加载缩略图一


如果你传递一个0.1f作为参数，Glide会加载原始图片大小的10%的图片。如果原始图片有1000x1000像素，缩略图的分辨率为100x100像素

```
String urlPath="/storage/emulated/0/MagazineUnlock/magazine-unlock-03-2.3.311-bigpicture_03_20.jpg";
        //第一种 设置缩略图比例
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.mipmap.pictures_no).into(mIv8);
```

### 加载缩略图二 高级缩略图请求：原图与缩略图完全不同

```
 //先建立一个缩略图对象 先加载缩略图，再加载原图
        DrawableRequestBuilder thumbnailRequest=  Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg");

        Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.pictures_no).into(mIv9);
```


## Glide入门教程六-缓存基础


Glide默认使用内存和磁盘缓存来避免不必要的网络请求,所有的图片请求都会被缓存在内存和磁盘上.

* 内存缓存
```
 Glide.with(this)
                .load(url10)
                .placeholder(R.mipmap.pictures_no)
                .skipMemoryCache(true)   //跳过内存缓存，不在内存中保存图片
                .into(mIv10);
```

调用了.skipMemoryCache( true )去特意告诉Glide跳过内存缓存。这意味着Glide不会把这个图片缓存到内存里。重要是，这个只影响内存缓存！Glide为了避免以后的网络请求，仍然会缓存到磁盘。
由于Glide默认会将所有的图片资源缓存到内存中，因此，没有必要手动调用.skipMemoryCache( false )了


* 跳过磁盘缓存

即使你关闭了内存缓存，所请求的图片仍然会被保存在设备的磁盘存储上。如果你有一张不段变化的图片，但是都是用的同一个URL，你可能需要禁止磁盘缓存了。
可以用.diskCacheStrategy()方法改变Glide的行为。不同于.skipMemoryCache()方法，它将需要从枚举型变量中选择一个，而不是一个简单的boolean。如果你想要禁止请求的磁盘缓存，使用枚举型变量DiskCacheStrategy.NONE作为参数

```
 Glide.with(this)
                .load(url10)
                .placeholder(R.mipmap.pictures_no)

                .skipMemoryCache(true)   //跳过内存缓存，不在内存中保存图片
                .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳这磁盘缓存
                .into(mIv10);
```
* DiskCacheStrategy

    * DiskCacheStrategy.NONE 不在磁盘下保存图片没有磁盘缓存
    * DiskCacheStrategy.SOURCE 只缓存全尺寸图，原图是多大就保存多大
    * DiskCacheStrategy.RESULT 只缓存最终降低分辨后用到的图片   例:  .override(300,300),300*300像素
    * DiskCacheStrategy.ALL 缓存所有类型的图片 (默认行为)

官方wiki<https://github.com/bumptech/glide/wiki/Caching-and-Cache-Invalidation>

## Glide入门教程七-图片请求优先级
.priority()方法参数的Priority的枚举变量
一共有四个不同的枚举变量。下面以递增方式列出：

	* Priority.LOW
	* Priority.NORMAL
	* Priority.HIGH
	* Priority.IMMEDIATE

```
 Glide.with(this)
                .load(url10)
                .placeholder(R.mipmap.pictures_no)
                .override(300,300)
                .skipMemoryCache(true)   //跳过内存缓存，不在内存中保存图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)   //添加高优先级
                .into(mIv10);

 Glide
.with( context )
.load( url10 )
.priority( Priority.LOW )
.into( imageViewLowPrioLeft );

Glide
    .with( context )
    .load( url10 )
    .priority( Priority.LOW )
    .into( imageViewLowPrioRight );
```


## Glide入门教程八-回调：定制view中使用SimpleTarget和ViewTarget


* Glide中的回调：Target

假设我们并没有ImageView作为图片加载的目标。我们只需要Bitmap本身。Glide提供了一个用Target获取Bitmap资源的方法。Target只是用来回调，它会在所有的加载和处理完毕时返回想要的结果。



```
   String url1 = "http://img4.imgtn.bdimg.com/it/u=21385237,1665284751&fm=21&gp=0.jpg";

        /**
         * 代码的第一部分，创建一个target字段对象，里面定义了个方法，
         * 这个方法一旦Glide加载和处理完图片将会被调用。
         * 回调方法传回Bitmap作为参数，你可以在你所需要用的地方随意使用这个Bitmap对象。
         */
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                //设置图片显示
                mIvt1.setImageBitmap(resource);
            }
        };

        Glide.with(this).load(url1)
                .asBitmap() //作为bitmap显示
                .into(target); //显示在这个对象
```



代码的第二部分，表明了Glide里如何使用Target，明显跟ImageView一样！
你可以传递一个Target或者ImageView作为参数到.into()方法里。
Glide会神奇地将结果返回。这里有个不同点，我们添加了.asBitmap()，
这会强制返回一个Bitmap对象。
记住，Glide也可以加载Gif或视频。为了防止在从网络URL（可能是GIF）获取Bitmap时，
出现未知格式图片冲突（期望是Bitmap），
我们设置.asBitmap()去告诉Glide只有在资源是一个图片是才算成功，其他的都算解析失败。

* 特定大小的Target

```
  /**
         *  通过构造方法设置图片大小
         */
        SimpleTarget<Bitmap> target2 = new SimpleTarget<Bitmap>(300,300) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //设置图片显示
                mIvt2.setImageBitmap(resource);
            }
        };

        Glide.with(this).load(url2)
                .asBitmap() //作为bitmap显示
                .into(target2); //显示在这个对象

```
和“普通”target唯一不同的是这个以像素为单位的图片大小声明
```
 new SimpleTarget<Bitmap>(300,300)
```

* ViewTarget

有很多原因导致我们不能直接使用ImageView,由于没有已知的方法在哪里设置图片，Glide并不支持加载图片到定制的View内。然而用ViewTarget会让这个更简单。
假设你有个自定义的View
```
public class FutureStudioView extends FrameLayout {
    ImageView iv;
    TextView tv;

    public void initialize(Context context) {
        inflate( context, R.layout.custom_view_futurestudio, this );

        iv = (ImageView) findViewById( R.id.custom_view_image );
        tv = (TextView) findViewById( R.id.custom_view_text );
    }

    public FutureStudioView(Context context, AttributeSet attrs) {
        super( context, attrs );
        initialize( context );
    }

    public FutureStudioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initialize( context );
    }

    public void setImage(Drawable drawable) {
        iv = (ImageView) findViewById( R.id.custom_view_image );

        iv.setImageDrawable( drawable );
    }
}
```
由于我们定制的view并不是继承自ImageView，这里不能使用常规的.into()方法。因此，我们只能创建一个ViewTarget，用来传递给.into()方法：


```
private void loadImageViewTarget() {
 String targetUrl = "http://photo.enterdesk.com/2009-4-21/200901241609531378.png";
    FutureStudioView customView = (FutureStudioView) findViewById( R.id.custom_view );

    viewTarget = new ViewTarget<FutureStudioView, GlideDrawable>( customView ) {
        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
            this.view.setImage( resource.getCurrent() );
        }
    };

    Glide.with(this)
                   .load(targetUrl)
                   .centerCrop()
                   .override(300,300)
                   //显示自定义控件中
                   .into(viewTarget);

}
```


在target的回调方法中，我们在定制view上使用我们创建的setImage(Drawable drawable)方法设置图片。同时，确保你注意到我们已经在ViewTarget的构造方法里传递了我们的定制view:new ViewTarget<FutureStudioView, GlideDrawable>( customView )。



## Glide入门教程九-通知栏图片加载
系统通知的图标为用户传递了重要的内容。用NotificationCompat.Builder为通知图片传递一个图片是最直接方式，但是这个图片必须是<b>Bitmap</b>格式的。如果这个图片已经在手机上，那没问题。但，如果这个图片还不在手机上，需要从网络下载，想要用这个标准的工具是不现实的。


* 自定义的通知布局


```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@android:color/white"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="2dp">

        <ImageView
            android:id="@+id/remoteview_notification_icon"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_marginRight="2dp"
            android:layout_weight="0"
            android:scaleType="centerCrop"/>

        <LinearLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:orientation="vertical">

            <TextView
                android:id="@+id/remoteview_notification_headline"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:ellipsize="end"
                android:singleLine="true"
                android:textSize="12sp"/>

            <TextView
                android:id="@+id/remoteview_notification_short_message"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:ellipsize="end"
                android:paddingBottom="2dp"
                android:singleLine="true"
                android:textSize="14sp"
                android:textStyle="bold"/>

        </LinearLayout>
    </LinearLayout>

</LinearLayout>
```

* 初始化

```
 //创建通知布局
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.remoteview_notification);


        remoteViews.setImageViewResource(R.id.remoteview_notification_icon,R.mipmap.ic_launcher);

        remoteViews.setTextViewText(R.id.remoteview_notification_headline,"头部分标题");
        remoteViews.setTextViewText(R.id.remoteview_notification_short_message,"二级标题内容");


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                                   .setSmallIcon(R.mipmap.ic_launcher)
                                    .setTicker("ticker")
                                    .setContentText("contenttext")
                                    .setContentTitle("contenttitle")
                                    .setContent(remoteViews)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = builder.build();

        if(Build.VERSION.SDK_INT>16){
            notification.bigContentView = remoteViews;
        }



        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID,notification);
        //这个类是Glide自带的
        NotificationTarget notificationTarget =
                new NotificationTarget(getApplicationContext(),remoteViews,R.id.remoteview_notification_icon,notification,NOTIFICATION_ID);


        String notUrl = "http://photo.enterdesk.com/2009-4-21/200901241609531378.png";
        Glide.with(getApplicationContext())
                .load(notUrl)
                .asBitmap()
                .into(notificationTarget);

```
只要图片被加载了，我们定制的通知栏就会显示




## Glide入门教程十-异常: 调试和报错处理

* 本地调试

Glide的常规请求里提供了一个方法设置Log的层级。不幸地是，你没法轻易在产品使用中轻易获取。但，也有一个很简单的方法获得Glide的调试log。你只要通过adb shell，打开terminal，然后使用下面的命令行：

```
adb shell setprop log.tag.GenericRequest DEBUG
```
最后一部分DEBUG来自标准Android的log常量。因此，作为参数的递增优先级的选项如下：

    * VERBOSE
    * DEBUG
    * INFO
    * WARN
    * ERROR
当图片不存在时，会输出下面的日志：
```
io.futurestud.tutorials.glide D/GenericRequest: load failed
io.futurestud.tutorials.glide D/GenericRequest: java.io.IOException: Request failed 404: Not Found
...
```
* 基本的异常日志

Glide不提供直接获取常规请求的日志，但是你可以在请求出错时抓取异常的日志。例如，如果图片不存在，Glide会（静静地）抛出一个异常，并显示出你.erroer()里指定的图片。如果你明确想要知道异常，创建一个listener,然后传递给Glide的.listener()方法。


* 监听器
```
 String testUrl ="http://photo.enterdesk.com/2009-4-21/2009012416095313783333.png";

        RequestListener<String,GlideDrawable> listener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {


                e.printStackTrace();
                Log.d("vivi", "onException: "+e.getMessage());


                /**
                 * false 时error中的才会显示
                 * true 时error中的就不显示
                 */
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        };

```
* 加载
你可以在Glide中的构造方法里设置listener:
```
  Glide.with(this)
                .load(testUrl)
                .listener(listener)
                .error(R.mipmap.pictures_no)
                .into(mIvEc1);

```

在onException方法中，你可以抓取问题，并决定你需要做什么，比如记录日志。如果Glide应当处理这个后果，比如显示一个出错占位图，在onException方法中返回false是很重要的。


.error()是否设置不影响日志正常工作。但只有在listener的onException方法里返回false，R.mipmap.pictures_no才会显示出来。


## Glide入门教程十一变换
在图片显示出之前可以对图片进行变换处理。例如，如果你的app需要显示一张灰度图，但只能获取到一个原始全色彩的版本，你可以使用一个变换去将图片从有明艳色彩的版本转换成惨淡的黑白版


推荐一个库Glide变换
<https://github.com/wasabeef/glide-transformations>
里面有很实现的效果

* 引入

```
//glide 变换
    compile 'jp.wasabeef:glide-transformations:2.0.1'
    // If you want to use the GPU Filters   -glide 变换使用GPU
    compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.3.0'
```

* 使用

```
  String trUrl = "http://pic.58pic.com/58pic/16/83/91/40558PICYDj_1024.jpg";


        Glide.with(this)
                .load(trUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mIvEc2);



        Glide.with(this)
                .load(trUrl)
                .bitmapTransform(new BlurTransformation(this))
                .into(mIvEc3);


```


提示：当你使用变换的时候，你不能使用.centerCrop()或者.fitCenter()

## Glide — 用Modules定制Glide

### Glide Modules

Glide modules是一个全局改变Glide行为的抽象的方式。你需要创建Glide的实例，来访问GlideBuilder。可以通过创建一个公共的类，实现GlideModule的接口来定制Glide
```

public class SimpleGlideModule implements GlideModule {
    @Override public void applyOptions(Context context, GlideBuilder builder) {
        // todo
    }

    @Override public void registerComponents(Context context, Glide glide) {
        // todo
    }
}
```
接口提供了两个方法去调节Glide的不同部分，大部分内容只研究第一个方法：applyOptions(Context context, GlideBuilder builder)

需要创建一个额外的类去自定义Glide。下一步是要在全局中声明这个类，这样Glide知道它应该加载并使用它。Glide会扫描AndroidManifest.xml的Glide modules的meta定义。这样，你必须在AndroidManifest.xml里的<application>标签下声明刚创建的Glide module。
```
<manifest

    ...

    <application>

        <meta-data
            android:name="io.futurestud.tutorials.glide.glidemodule.SimpleGlideModule"
            android:value="GlideModule" />

        ...

    </application>
</manifest>
```

确保你设置android:name为你自己的包名+类名，这样才能正确引用。你不需要添加其他的代码到其中。如果你想要禁止Glide Module，只要从AndroidManifest.xml里移除它。Java类里的代码可以留着供以后使用。当在AndroidManifest.xml里没有引用的时候，它永远不会被加载。

Glide推荐实现自定义module的方式有一个好处：你可以一次同时声明多个Glide Module。Glide会（没有特殊的顺序）都遍历所有声明的module。由于你当前未定义顺序，确保你的定制不会造成冲突！

### GlideBuilder

接口的第一个方法：applyOptions(Context context, GlideBuilder builder)。这个方法将GlideBuilder的对象当作参数，并且是void返回类型，所以你在这个方法里能调用GlideBuilder可以用的方法。



* .setMemoryCache(MemoryCache memoryCache)
* .setBitmapPool(BitmapPool bitmapPool)
* .setDiskCache(DiskCache.Factory diskCacheFactory)
* .setDiskCacheService(ExecutorService service)
* .setResizeService(ExecutorService service)
* .setDecodeFormat(DecodeFormat decodeFormat)



Android里有两个方法去解析图片：ARGB8888和RGB565。第一个为每个像素采用4 byte表示，后面一个则用2 byte表示。ARG8888有更高的图片质量，并且能够存储一个alpha通道。当Picasso使用ARGB888时，Glide默认使用低质量的RGB565。Glide用户的好消息：你可以通过使用Glide module方法改变解析格式。

你只要简单地实现一个GlideModule，像之前提到的一样，然后调用builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)，并传入正确的枚举型参数

```
public class SimpleGlideModule implements GlideModule {
    @Override public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override public void registerComponents(Context context, Glide glide) {
        // nothing to do here
    }
}
```





源码
<https://github.com/ln0491/GlideDemo>


## Glide 源码浅析

### 用法

```
Glide.with(this).load(file).into(mIv3);
```

### 从with开始
```
   public static RequestManager with(Context context) {
        RequestManagerRetriever retriever = RequestManagerRetriever.get();
        return retriever.get(context);
    }
```

通过RequestManagerRetriever返回了一个RequestManager

### RequestManagerRetriever
```

/**
 * A collection of static methods for creating new {@link com.bumptech.glide.RequestManager}s or retrieving existing
 * ones from activities and fragment.
 */
```
一个静态方法集合，用于创建或者检索，从Activitys和fragement集合中取回一个

* 定义
```
public class RequestManagerRetriever implements Handler.Callback
```
实现了Handler.Callback接口
```
public interface Callback {
        public boolean handleMessage(Message msg);
    }
```
Hanlder的回调方法

* 类定义
```
 private static final RequestManagerRetriever INSTANCE = new RequestManagerRetriever();
 ....

 RequestManagerRetriever() {
         handler = new Handler(Looper.getMainLooper(), this /* Callback */);
     }


         public static RequestManagerRetriever get() {
             return INSTANCE;
         }

```

这个类是个单例的,在构造方法中，创建了一个在主线程的handler对象

通过get方法，得到这个类的实例对象

* 通过get得到RequestManager
```
 public RequestManager get(Context context) {
        if (context == null) {
        //判断上下文是否为空，
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
           //判断是不是在主线程中，同时content是不是全局的,这里不是全局的，才进入
           //下面进行各种匹配
            if (context instanceof FragmentActivity) {

                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }

        //以上都不是的话，就调全局的
        return getApplicationManager(context);
    }
```

* get((FragmentActivity) context)之类的各种匹配

```
  public RequestManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            FragmentManager fm = activity.getSupportFragmentManager();
            return supportFragmentGet(activity, fm);
        }
    }



```

判断是不是后台线程，是就回调上面的public RequestManager get(Context context)方法，再判断

不是就先断言Activity是不是销毁了，是抛出异常，不是就创建一个FragmentManager，通过supportFragmentGet方法返回RequestManager


*  assertNotDestroyed
```
   @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void assertNotDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }
```
* supportFragmentGet(activity, fm)

```
RequestManager supportFragmentGet(Context context, FragmentManager fm) {
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            requestManager = new RequestManager(context, current.getLifecycle(), current.getRequestManagerTreeNode());
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }
```
首先获取SupportRequestManagerFragment对象这个就是一个fragment继承fragment
```
public class SupportRequestManagerFragment extends Fragment {
```
* SupportRequestManagerFragment
```
   SupportRequestManagerFragment getSupportRequestManagerFragment(final FragmentManager fm) {
        SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                current = new SupportRequestManagerFragment();
                pendingSupportRequestManagerFragments.put(fm, current);
                //就添加到集合中，开启事务，
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                //发送消息到Handler
                handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }
```
首先获取SupportRequestManagerFragment，在集合中取出
```
if (current == null) {
            current = pendingSupportRequestManagerFragments.get(fm);
```
再判断是否为NULL,为空说明集合pendingSupportRequestManagerFragments中没有
```
   /** Pending adds for SupportRequestManagerFragments. */
    final Map<FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments =
            new HashMap<FragmentManager, SupportRequestManagerFragment>();
```



* 通过构造方法new一个出来
```
    /**
    * 通过构造方法new一个出来
    */
    RequestManager supportFragmentGet(Context context, FragmentManager fm) {
    //先得到一个SupportRequestManagerFragment
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            requestManager = new RequestManager(context, current.getLifecycle(), current.getRequestManagerTreeNode());
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }
```
* current.setRequestManager(requestManager);

```
private RequestManager requestManager;

public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

public RequestManager getRequestManager() {
        return requestManager;
    }
```
先得到一个SupportRequestManagerFragment,通过SupportRequestManagerFragment的getRequestManager，得到requestManager
起到把请求和Activity生命周期同步的作用



*  getApplicationManager
```
    private RequestManager getApplicationManager(Context context) {
        // Either an application context or we're on a background thread.
        if (applicationManager == null) {
            synchronized (this) {
                if (applicationManager == null) {
                    // Normally pause/resume is taken care of by the fragment we add to the fragment or activity.
                    // However, in this case since the manager attached to the application will not receive lifecycle
                    // events, we must force the manager to start resumed using ApplicationLifecycle.
                    applicationManager = new RequestManager(context.getApplicationContext(),
                            new ApplicationLifecycle(), new EmptyRequestManagerTreeNode());
                }
            }
        }

        return applicationManager;
    }

     /** The top application level RequestManager. */
        private volatile RequestManager applicationManager;

```
这里的applicationManager就是RequestManager

* handleMessage
```
 @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case ID_REMOVE_FRAGMENT_MANAGER:
                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
                key = fm;
                removed = pendingRequestManagerFragments.remove(fm);
                break;
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                FragmentManager supportFm = (FragmentManager) message.obj;
                key = supportFm;
                removed = pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                handled = false;
        }
        if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
        }
        return handled;
    }
```

 handle中就是移除发送过来的对象FragmentManager

### RequestManager
```
public class RequestManager implements LifecycleListener {

    public RequestManager(Context context, Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
        this(context, lifecycle, treeNode, new RequestTracker(), new ConnectivityMonitorFactory());
    }

    RequestManager(Context context, final Lifecycle lifecycle, RequestManagerTreeNode treeNode,
            RequestTracker requestTracker, ConnectivityMonitorFactory factory) {
        this.context = context.getApplicationContext();
        this.lifecycle = lifecycle;
        this.treeNode = treeNode;
        this.requestTracker = requestTracker;
        this.glide = Glide.get(context);
        this.optionsApplier = new OptionsApplier();

        ConnectivityMonitor connectivityMonitor = factory.build(context,
                new RequestManagerConnectivityListener(requestTracker));

        // If we're the application level request manager, we may be created on a background thread. In that case we
        // cannot risk synchronously pausing or resuming requests, so we hack around the issue by delaying adding
        // ourselves as a lifecycle listener by posting to the main thread. This should be entirely safe.
        if (Util.isOnBackgroundThread()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    lifecycle.addListener(RequestManager.this);
                }
            });
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(connectivityMonitor);
    }
```
这个类实现了，一个接口LifecycleListener有2个构造方法实际就一个，
```

/**
 * An interface for listener to {@link android.app.Fragment} and {@link android.app.Activity} lifecycle events.
 */
public interface LifecycleListener {

    /**
     * Callback for when {@link android.app.Fragment#onStart()}} or {@link android.app.Activity#onStart()} is called.
     */
    void onStart();

    /**
     * Callback for when {@link android.app.Fragment#onStop()}} or {@link android.app.Activity#onStop()}} is called.
     */
    void onStop();

    /**
     * Callback for when {@link android.app.Fragment#onDestroy()}} or {@link android.app.Activity#onDestroy()} is
     * called.
     */
    void onDestroy();
}

```
监听生命周期的接口

* private final Context context
上下文没什么说的

*  private final Lifecycle lifecycle一个接口用于添加生命监听器LifecycleListener
```

/**
 * An interface for listening to Activity/Fragment lifecycle events.
 */
public interface Lifecycle {
    /**
     * Adds the given listener to the set of listeners managed by this Lifecycle implementation.
     */
    void addListener(LifecycleListener listener);
}
```
* private final RequestManagerTreeNode treeNode 同样一个接口

```
/**
 * Provides access to the relatives of a RequestManager based on the current context. The context hierarchy
 * is provided by nesting in Activity and Fragments; the application context does not provide access to
 * any other RequestManagers hierarchically.
 */
public interface RequestManagerTreeNode {
    /**
     * Returns all descendant {@link RequestManager}s relative to the context of the current {@link RequestManager}.
     */
    Set<RequestManager> getDescendants();
}
```

用于获取层次接口的Set集合，只能访问本层次内的，不能访问其它分支的

*  private final RequestTracker requestTracker;

```
/**
 * A class for tracking, canceling, and restarting in progress, completed, and failed requests.
 */
public class RequestTracker {
```

一个请求类，用于跟踪取消，重启进度，完成与失败

```
public class RequestTracker {
    //一个集合，用于保存弱引用请求
    private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap<Request, Boolean>());
    // A set of requests that have not completed and are queued to be run again. We use this list to maintain hard
    // references to these requests to ensure that they are not garbage collected before they start running or
    // while they are paused. See #346.
    //一个集合，一组未完成的请求，并将要重新运行的请求 以确保它们不是在开始运行或运行之前收集的垃圾
       //                        当他们被暂停的时候
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Request> pendingRequests = new ArrayList<Request>();

//标识位是否暂停
    private boolean isPaused;

    /**
     * Starts tracking the given request.
     */
    public void runRequest(Request request) {
        requests.add(request);
        if (!isPaused) {
            request.begin();
        } else {
            pendingRequests.add(request);
        }
    }

    // Visible for testing.
    void addRequest(Request request) {
        requests.add(request);
    }

    /**
     * Stops tracking the given request.
     */
    public void removeRequest(Request request) {
        requests.remove(request);
        pendingRequests.remove(request);
    }

    /**
     * Returns {@code true} if requests are currently paused, and {@code false} otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Stops any in progress requests.
     */
    public void pauseRequests() {
        isPaused = true;
        for (Request request : Util.getSnapshot(requests)) {
            if (request.isRunning()) {
                request.pause();
                pendingRequests.add(request);
            }
        }
    }

    /**
     * Starts any not yet completed or failed requests.
     */
    public void resumeRequests() {
        isPaused = false;
        for (Request request : Util.getSnapshot(requests)) {
            if (!request.isComplete() && !request.isCancelled() && !request.isRunning()) {
                request.begin();
            }
        }
        pendingRequests.clear();
    }

    /**
     * Cancels all requests and clears their resources.
     */
    public void clearRequests() {
        for (Request request : Util.getSnapshot(requests)) {
            request.clear();
        }
        pendingRequests.clear();
    }

    /**
     * Restarts failed requests and cancels and restarts in progress requests.
     */
    public void restartRequests() {
        for (Request request : Util.getSnapshot(requests)) {
            if (!request.isComplete() && !request.isCancelled()) {
                // Ensure the request will be restarted in onResume.
                request.pause();
                if (!isPaused) {
                    request.begin();
                } else {
                    pendingRequests.add(request);
                }
            }
        }
    }
}
```

* Request

```
public interface Request {

    /**
     * Starts an asynchronous load.
     */
    void begin();

    /**
     * Identical to {@link #clear()} except that the request may later be restarted.
     */
    void pause();

    /**
     * Prevents any bitmaps being loaded from previous requests, releases any resources held by this request,
     * displays the current placeholder if one was provided, and marks the request as having been cancelled.
     */
    void clear();

    /**
     * Returns true if this request is paused and may be restarted.
     */
    boolean isPaused();

    /**
     * Returns true if this request is running and has not completed or failed.
     */
    boolean isRunning();

    /**
     * Returns true if the request has completed successfully.
     */
    boolean isComplete();

    /**
     * Returns true if a non-placeholder resource is set. For Requests that load more than one resource, isResourceSet
     * may return true even if {@link #isComplete()}} returns false.
     */
    boolean isResourceSet();

    /**
     * Returns true if the request has been cancelled.
     */
    boolean isCancelled();

    /**
     * Returns true if the request has failed.
     */
    boolean isFailed();

    /**
     * Recycles the request object and releases its resources.
     */
    void recycle();
}
```
方法一目了然都是状态需要子类实现

*  private final Glide glide;

```
  this.glide = Glide.get(context);
```
这里才创建Glide对象

*   private final OptionsApplier optionsApplier;

```
  class OptionsApplier {

        public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X builder) {
            if (options != null) {
            //option为DefaultOptions下面的内部接口
                options.apply(builder);
            }
            return builder;
        }
    }
```

一个内部类


*  private DefaultOptions options;

```
public interface DefaultOptions {
        /**
         * Allows the implementor to apply some options to the given request.
         *
         * @param requestBuilder The request builder being used to construct the load.
         * @param <T> The type of the model.
         */
        <T> void apply(GenericRequestBuilder<T, ?, ?, ?> requestBuilder);
    }
```
和optionsApplier配合使用

* public static Glide get(Context context)

```
   /**
     * Get the singleton.
     *
     * @return the singleton
     */
    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    Context applicationContext = context.getApplicationContext();
                    List<GlideModule> modules = new ManifestParser(applicationContext).parse();

                    GlideBuilder builder = new GlideBuilder(applicationContext);
                    for (GlideModule module : modules) {
                        module.applyOptions(applicationContext, builder);
                    }
                    glide = builder.createGlide();
                    for (GlideModule module : modules) {
                        module.registerComponents(applicationContext, glide);
                    }
                }
            }
        }

        return glide;
    }
```

获取Glide对象，同步代码块,首先获取上下文，

获取一个GlideModule集合,

获取GlideBuilder

对象其中的所有对象进行应用设置和注册

GildeModule与GlideBuilder见
<https://ln0491.github.io/2016/10/19/Glide-%E2%80%94-%E7%94%A8Modules%E5%AE%9A%E5%88%B6Glide/>

初始化一些设置
```
public interface GlideModule {

    /**
     * Lazily apply options to a {@link com.bumptech.glide.GlideBuilder} immediately before the Glide singleton is
     * created.
     *
     * <p>
     *     This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link android.content.Context}.
     * @param builder The {@link com.bumptech.glide.GlideBuilder} that will be used to create Glide.
     */
    void applyOptions(Context context, GlideBuilder builder);

    /**
     * Lazily register components immediately after the Glide singleton is created but before any requests can be
     * started.
     *
     * <p>
     *     This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link android.content.Context}.
     * @param glide The newly created Glide singleton.
     */
    void registerComponents(Context context, Glide glide);
}
```

*  createGlide

```
Glide createGlide() {
        if (sourceService == null) {
            final int cores = Math.max(1, Runtime.getRuntime().availableProcessors());
            sourceService = new FifoPriorityThreadPoolExecutor(cores);
        }
        if (diskCacheService == null) {
            diskCacheService = new FifoPriorityThreadPoolExecutor(1);
        }

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        if (bitmapPool == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                int size = calculator.getBitmapPoolSize();
                bitmapPool = new LruBitmapPool(size);
            } else {
                bitmapPool = new BitmapPoolAdapter();
            }
        }

        if (memoryCache == null) {
            memoryCache = new LruResourceCache(calculator.getMemoryCacheSize());
        }

        if (diskCacheFactory == null) {
            diskCacheFactory = new InternalCacheDiskCacheFactory(context);
        }

        if (engine == null) {
            engine = new Engine(memoryCache, diskCacheFactory, diskCacheService, sourceService);
        }

        if (decodeFormat == null) {
            decodeFormat = DecodeFormat.DEFAULT;
        }

        return new Glide(engine, memoryCache, bitmapPool, context, decodeFormat);
    }
```

### into方法

```
    @Override
    public Target<GlideDrawable> into(ImageView view) {
        return super.into(view); //调父类的方法
    }
```

* 父类 GenericRequestBuilder-目前只有2种变换
```
public Target<TranscodeType> into(ImageView view) {
        Util.assertMainThread();
        if (view == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }

        if (!isTransformationSet && view.getScaleType() != null) {
            switch (view.getScaleType()) {
                case CENTER_CROP:
                //变换
                    applyCenterCrop();
                    break;
                case FIT_CENTER:
                case FIT_START:
                case FIT_END:
                //变换
                    applyFitCenter();
                    break;
                //$CASES-OMITTED$
                default:
                    // Do nothing.
            }
        }

        return into(glide.buildImageViewTarget(view, transcodeClass));
    }

    /**
     * Returns a future that can be used to do a blocking get on a background thread.
     *
     * @param width The desired width in pixels, or {@link Target#SIZE_ORIGINAL}. This will be overridden by
     *             {@link #override * (int, int)} if previously called.
     * @param height The desired height in pixels, or {@link Target#SIZE_ORIGINAL}. This will be overridden by
     *              {@link #override * (int, int)}} if previously called).
     *
     * @see Glide#clear(com.bumptech.glide.request.FutureTarget)
     *
     * @return An {@link com.bumptech.glide.request.FutureTarget} that can be used to obtain the
     *         resource in a blocking manner.
     */
    public FutureTarget<TranscodeType> into(int width, int height) {
        final RequestFutureTarget<ModelType, TranscodeType> target =
                new RequestFutureTarget<ModelType, TranscodeType>(glide.getMainHandler(), width, height);

        // TODO: Currently all loads must be started on the main thread...
        glide.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (!target.isCancelled()) {
                    into(target);
                }
            }
        });

        return target;
    }



    /**
     * Set the target the resource will be loaded into.
     *
     * @see Glide#clear(com.bumptech.glide.request.target.Target)
     *
     * @param target The target to load the resource into.
     * @return The given target.
     */
    public <Y extends Target<TranscodeType>> Y into(Y target) {
        Util.assertMainThread();
        if (target == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        }
        if (!isModelSet) {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        }

        Request previous = target.getRequest();

        if (previous != null) {
            previous.clear();
            requestTracker.removeRequest(previous);
            previous.recycle();
        }

        Request request = buildRequest(target);
        target.setRequest(request);
        lifecycle.addListener(target);
        requestTracker.runRequest(request);

        return target;
    }
```
* RequestTracker
```

    /**
     * Starts tracking the given request.
     */
    public void runRequest(Request request) {
        requests.add(request);
        if (!isPaused) {
        //开始请求调父类的，佼
            request.begin();
        } else {
        //将请求添加到集合
            pendingRequests.add(request);
        }
    }
```
开始跟踪请求



* GenericRequest

```
 @Override
    public void begin() {
        startTime = LogTime.getLogTime();
        if (model == null) {
            onException(null);
            return;
        }

        status = Status.WAITING_FOR_SIZE;
        if (Util.isValidDimensions(overrideWidth, overrideHeight)) {
            onSizeReady(overrideWidth, overrideHeight);
        } else {
            target.getSize(this);
        }

        if (!isComplete() && !isFailed() && canNotifyStatusChanged()) {
            target.onLoadStarted(getPlaceholderDrawable());
        }
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logV("finished run method in " + LogTime.getElapsedMillis(startTime));
        }
    }
```

* onSizeReady

```
  /**
     * A callback method that should never be invoked directly.
     */
    @Override
    public void onSizeReady(int width, int height) {
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logV("Got onSizeReady in " + LogTime.getElapsedMillis(startTime));
        }
        if (status != Status.WAITING_FOR_SIZE) {
            return;
        }
        status = Status.RUNNING;

        width = Math.round(sizeMultiplier * width);
        height = Math.round(sizeMultiplier * height);

        ModelLoader<A, T> modelLoader = loadProvider.getModelLoader();
        final DataFetcher<T> dataFetcher = modelLoader.getResourceFetcher(model, width, height);

        if (dataFetcher == null) {
            onException(new Exception("Failed to load model: \'" + model + "\'"));
            return;
        }
        ResourceTranscoder<Z, R> transcoder = loadProvider.getTranscoder();
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logV("finished setup for calling load in " + LogTime.getElapsedMillis(startTime));
        }
        loadedFromMemoryCache = true;
        //调用load方法
        loadStatus = engine.load(signature, width, height, dataFetcher, loadProvider, transformation, transcoder,
                priority, isMemoryCacheable, diskCacheStrategy, this);
        loadedFromMemoryCache = resource != null;
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logV("finished onSizeReady in " + LogTime.getElapsedMillis(startTime));
        }
    }
```



里面调用了engine.load方法
```
  loadStatus = engine.load(signature, width, height, dataFetcher, loadProvider, transformation, transcoder,
                priority, isMemoryCacheable, diskCacheStrategy, this);
```

* load方法
```
 public <T, Z, R> LoadStatus load(Key signature, int width, int height, DataFetcher<T> fetcher,
            DataLoadProvider<T, Z> loadProvider, Transformation<Z> transformation, ResourceTranscoder<Z, R> transcoder,
            Priority priority, boolean isMemoryCacheable, DiskCacheStrategy diskCacheStrategy, ResourceCallback cb) {
        Util.assertMainThread();
        long startTime = LogTime.getLogTime();

        final String id = fetcher.getId();
        EngineKey key = keyFactory.buildKey(id, signature, width, height, loadProvider.getCacheDecoder(),
                loadProvider.getSourceDecoder(), transformation, loadProvider.getEncoder(),
                transcoder, loadProvider.getSourceEncoder());

        EngineResource<?> cached = loadFromCache(key, isMemoryCacheable);
        if (cached != null) {
            cb.onResourceReady(cached);
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                logWithTimeAndKey("Loaded resource from cache", startTime, key);
            }
            return null;
        }

        EngineResource<?> active = loadFromActiveResources(key, isMemoryCacheable);
        if (active != null) {
            cb.onResourceReady(active);
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                logWithTimeAndKey("Loaded resource from active resources", startTime, key);
            }
            return null;
        }

        EngineJob current = jobs.get(key);
        if (current != null) {
            current.addCallback(cb);
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                logWithTimeAndKey("Added to existing load", startTime, key);
            }
            return new LoadStatus(cb, current);
        }

        EngineJob engineJob = engineJobFactory.build(key, isMemoryCacheable);
        DecodeJob<T, Z, R> decodeJob = new DecodeJob<T, Z, R>(key, width, height, fetcher, loadProvider, transformation,
                transcoder, diskCacheProvider, diskCacheStrategy, priority);
        EngineRunnable runnable = new EngineRunnable(engineJob, decodeJob, priority);
        jobs.put(key, engineJob);
        engineJob.addCallback(cb);
        engineJob.start(runnable);

        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Started new load", startTime, key);
        }
        return new LoadStatus(cb, engineJob);
    }
```
加载资源方法

```
  private EngineResource<?> loadFromActiveResources(Key key, boolean isMemoryCacheable) {
        if (!isMemoryCacheable) {
            return null;
        }

        EngineResource<?> active = null;
        WeakReference<EngineResource<?>> activeRef = activeResources.get(key);
        if (activeRef != null) {
            active = activeRef.get();
            if (active != null) {
                active.acquire();
            } else {
                activeResources.remove(key);
            }
        }

        return active;
    }

    private EngineResource<?> loadFromCache(Key key, boolean isMemoryCacheable) {
        if (!isMemoryCacheable) {
            return null;
        }

        EngineResource<?> cached = getEngineResourceFromCache(key);
        if (cached != null) {
            cached.acquire();
            activeResources.put(key, new ResourceWeakReference(key, cached, getReferenceQueue()));
        }
        return cached;
    }

    @SuppressWarnings("unchecked")
    private EngineResource<?> getEngineResourceFromCache(Key key) {
        Resource<?> cached = cache.remove(key);

        final EngineResource result;
        if (cached == null) {
            result = null;
        } else if (cached instanceof EngineResource) {
            // Save an object allocation if we've cached an EngineResource (the typical case).
            result = (EngineResource) cached;
        } else {
            result = new EngineResource(cached, true /*isCacheable*/);
        }
        return result;
    }

//释放资源，索引++
  void acquire() {
        if (isRecycled) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
        ++acquired;
    }
```




先从cache中寻找资源，如果找到则将其从cache中移除并放入activeResources中，否则从activeResources中寻找。cache是LruResourceCache对象，作为资源的LRU缓存；activeResources是以弱引用为值的Map，用于缓存使用中的资源。比一般内存缓存额外多一级缓存的意义在于，当内存不足时清理cache中的资源时，不会对使用中的Bitmap造成影响。
若再次寻找失败，则创建EngineJob对象并调用其start方法。

* EngineJob

```
  public void start(EngineRunnable engineRunnable) {
        this.engineRunnable = engineRunnable;
        future = diskCacheService.submit(engineRunnable);
    }

        private final ExecutorService diskCacheService;
        private final ExecutorService sourceService;
```

diskCacheService是线程池

传进来的参数engineRunnable，放在future = diskCacheService.submit(engineRunnable);执行



* EngineRunnable


```
   @Override
     public void run() {
         if (isCancelled) {
             return;
         }

         Exception exception = null;
         Resource<?> resource = null;
         try {
             resource = decode();
         } catch (Exception e) {
             if (Log.isLoggable(TAG, Log.VERBOSE)) {
                 Log.v(TAG, "Exception decoding", e);
             }
             exception = e;
         }

         if (isCancelled) {
             if (resource != null) {
                 resource.recycle();
             }
             return;
         }

         if (resource == null) {
             onLoadFailed(exception);
         } else {
             onLoadComplete(resource);
         }
     }


    private void onLoadComplete(Resource resource) {
        manager.onResourceReady(resource);
    }

    private void onLoadFailed(Exception e) {
        if (isDecodingFromCache()) {
            stage = Stage.SOURCE;
            manager.submitForSource(this);
        } else {
            manager.onException(e);
        }
    }

```
在EngineRunnable的run方法中进行编码，根据缓存策略调用decodeFromCache或者decodeFromSource。

* decode

```
   private Resource<?> decode() throws Exception {
        if (isDecodingFromCache()) {
            return decodeFromCache();
        } else {
            return decodeFromSource();
        }
    }
```

* decodeFromCache
```
 private Resource<?> decodeFromCache() throws Exception {
        Resource<?> result = null;
        try {
            result = decodeJob.decodeResultFromCache();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Exception decoding result from cache: " + e);
            }
        }

        if (result == null) {
            result = decodeJob.decodeSourceFromCache();
        }
        return result;
    }
```

* decodeFromSource

```
 private Resource<?> decodeFromSource() throws Exception {
        return decodeJob.decodeFromSource();
    }

```

* DecodeJob
```
 /**
     * Returns a transformed and transcoded resource decoded from source data in the disk cache, or null if no such
     * resource exists.
     *
     * @throws Exception
     */
    public Resource<Z> decodeSourceFromCache() throws Exception {
        if (!diskCacheStrategy.cacheSource()) {
            return null;
        }

        long startTime = LogTime.getLogTime();
        Resource<T> decoded = loadFromCache(resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Decoded source from cache", startTime);
        }
        return transformEncodeAndTranscode(decoded);
    }

    /**
     * Returns a transformed and transcoded resource decoded from source data, or null if no source data could be
     * obtained or no resource could be decoded.
     *
     * <p>
     *     Depending on the {@link com.bumptech.glide.load.engine.DiskCacheStrategy} used, source data is either decoded
     *     directly or first written to the disk cache and then decoded from the disk cache.
     * </p>
     *
     * @throws Exception
     */
    public Resource<Z> decodeFromSource() throws Exception {
        Resource<T> decoded = decodeSource();
        return transformEncodeAndTranscode(decoded);
    }

```

*  构建fetcher.loadData()加载数据
```
 private Resource<T> decodeSource() throws Exception {
        Resource<T> decoded = null;
        try {
            long startTime = LogTime.getLogTime();
            final A data = fetcher.loadData(priority);
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                logWithTimeAndKey("Fetched data", startTime);
            }
            if (isCancelled) {
                return null;
            }
            decoded = decodeFromSourceData(data);
        } finally {
            fetcher.cleanup();
        }
        return decoded;
    }
````
通过DataFetcher fetcher。
编码前需要先通过DataFetcher访问网络获得文件流。接口DataFetcher的实现类根据配置而不同，

之后根据需要将文件流写入磁盘缓存，再对文件流进行编码。

```
 public Resource<Z> decodeFromSource() throws Exception {
        Resource<T> decoded = decodeSource();
        return transformEncodeAndTranscode(decoded);
    }


    //转换

    private Resource<Z> transformEncodeAndTranscode(Resource<T> decoded) {
        long startTime = LogTime.getLogTime();
        Resource<T> transformed = transform(decoded);
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Transformed resource from source", startTime);
        }

//写入缓存
        writeTransformedToCache(transformed);

        startTime = LogTime.getLogTime();
        Resource<Z> result = transcode(transformed);
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Transcoded transformed from source", startTime);
        }
        return result;
    }

//写入缓存
    private void writeTransformedToCache(Resource<T> transformed) {
        if (transformed == null || !diskCacheStrategy.cacheResult()) {
            return;
        }
        long startTime = LogTime.getLogTime();
        SourceWriter<Resource<T>> writer = new SourceWriter<Resource<T>>(loadProvider.getEncoder(), transformed);
        diskCacheProvider.getDiskCache().put(resultKey, writer);
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Wrote transformed from source to cache", startTime);
        }
    }




     class SourceWriter<DataType> implements DiskCache.Writer {

            private final Encoder<DataType> encoder;
            private final DataType data;

            public SourceWriter(Encoder<DataType> encoder, DataType data) {
                this.encoder = encoder;
                this.data = data;
            }

            @Override
            public boolean write(File file) {
                boolean success = false;
                OutputStream os = null;
                try {
                    os = fileOpener.open(file);
                    success = encoder.encode(data, os);
                } catch (FileNotFoundException e) {
                    if (Log.isLoggable(TAG, Log.DEBUG)) {
                        Log.d(TAG, "Failed to find file to write to disk cache", e);
                    }
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            // Do nothing.
                        }
                    }
                }
                return success;
            }
        }


 ```
 * DiskLruCacheWrapper

 diskCacheProvider.getDiskCache().put(resultKey, writer);
 ```
         @Override
            public void put(Key key, Writer writer) {
                String safeKey = safeKeyGenerator.getSafeKey(key);
                writeLocker.acquire(key);
                try {
                    DiskLruCache.Editor editor = getDiskCache().edit(safeKey);
                    // Editor will be null if there are two concurrent puts. In the worst case we will just silently fail.
                    if (editor != null) {
                        try {
                            File file = editor.getFile(0);
                            if (writer.write(file)) {
                                editor.commit();
                            }
                        } finally {
                            editor.abortUnlessCommitted();
                        }
                    }
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, Log.WARN)) {
                        Log.w(TAG, "Unable to put to disk cache", e);
                    }
                } finally {
                    writeLocker.release(key);
                }
            }

```

* StreamBitmapDecoder --  decoded = loadProvider.getSourceDecoder().decode(data, width, height);

StreamBitmapDecoder

```
  @Override
    public Resource<Bitmap> decode(InputStream source, int width, int height) {
        Bitmap bitmap = downsampler.decode(source, bitmapPool, width, height, decodeFormat);
        return BitmapResource.obtain(bitmap, bitmapPool);
    }
```

* Downsampler

```
 @Override
    public Bitmap decode(InputStream is, BitmapPool pool, int outWidth, int outHeight, DecodeFormat decodeFormat) {
        final ByteArrayPool byteArrayPool = ByteArrayPool.get();
        final byte[] bytesForOptions = byteArrayPool.getBytes();
        final byte[] bytesForStream = byteArrayPool.getBytes();
        final BitmapFactory.Options options = getDefaultOptions();

        // Use to fix the mark limit to avoid allocating buffers that fit entire images.
        RecyclableBufferedInputStream bufferedStream = new RecyclableBufferedInputStream(
                is, bytesForStream);
        // Use to retrieve exceptions thrown while reading.
        // TODO(#126): when the framework no longer returns partially decoded Bitmaps or provides a way to determine
        // if a Bitmap is partially decoded, consider removing.
        ExceptionCatchingInputStream exceptionStream =
                ExceptionCatchingInputStream.obtain(bufferedStream);
        // Use to read data.
        // Ensures that we can always reset after reading an image header so that we can still attempt to decode the
        // full image even when the header decode fails and/or overflows our read buffer. See #283.
        MarkEnforcingInputStream invalidatingStream = new MarkEnforcingInputStream(exceptionStream);
        try {
            exceptionStream.mark(MARK_POSITION);
            int orientation = 0;
            try {
                orientation = new ImageHeaderParser(exceptionStream).getOrientation();
            } catch (IOException e) {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(TAG, "Cannot determine the image orientation from header", e);
                }
            } finally {
                try {
                    exceptionStream.reset();
                } catch (IOException e) {
                    if (Log.isLoggable(TAG, Log.WARN)) {
                        Log.w(TAG, "Cannot reset the input stream", e);
                    }
                }
            }

            options.inTempStorage = bytesForOptions;

            final int[] inDimens = getDimensions(invalidatingStream, bufferedStream, options);
            final int inWidth = inDimens[0];
            final int inHeight = inDimens[1];

            final int degreesToRotate = TransformationUtils.getExifOrientationDegrees(orientation);
            final int sampleSize = getRoundedSampleSize(degreesToRotate, inWidth, inHeight, outWidth, outHeight);

            final Bitmap downsampled =
                    downsampleWithSize(invalidatingStream, bufferedStream, options, pool, inWidth, inHeight, sampleSize,
                            decodeFormat);

            // BitmapFactory swallows exceptions during decodes and in some cases when inBitmap is non null, may catch
            // and log a stack trace but still return a non null bitmap. To avoid displaying partially decoded bitmaps,
            // we catch exceptions reading from the stream in our ExceptionCatchingInputStream and throw them here.
            final Exception streamException = exceptionStream.getException();
            if (streamException != null) {
                throw new RuntimeException(streamException);
            }

            Bitmap rotated = null;
            if (downsampled != null) {
                rotated = TransformationUtils.rotateImageExif(downsampled, pool, orientation);

                if (!downsampled.equals(rotated) && !pool.put(downsampled)) {
                    downsampled.recycle();
                }
            }

            return rotated;
        } finally {
            byteArrayPool.releaseBytes(bytesForOptions);
            byteArrayPool.releaseBytes(bytesForStream);
            exceptionStream.release();
            releaseOptions(options);
        }
    }

    private int getRoundedSampleSize(int degreesToRotate, int inWidth, int inHeight, int outWidth, int outHeight) {
        int targetHeight = outHeight == Target.SIZE_ORIGINAL ? inHeight : outHeight;
        int targetWidth = outWidth == Target.SIZE_ORIGINAL ? inWidth : outWidth;

        final int exactSampleSize;
        if (degreesToRotate == 90 || degreesToRotate == 270) {
            // If we're rotating the image +-90 degrees, we need to downsample accordingly so the image width is
            // decreased to near our target's height and the image height is decreased to near our target width.
            //noinspection SuspiciousNameCombination
            exactSampleSize = getSampleSize(inHeight, inWidth, targetWidth, targetHeight);
        } else {
            exactSampleSize = getSampleSize(inWidth, inHeight, targetWidth, targetHeight);
        }

        // BitmapFactory only accepts powers of 2, so it will round down to the nearest power of two that is less than
        // or equal to the sample size we provide. Because we need to estimate the final image width and height to
        // re-use Bitmaps, we mirror BitmapFactory's calculation here. For bug, see issue #224. For algorithm see
        // http://stackoverflow.com/a/17379704/800716.
        final int powerOfTwoSampleSize = exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize);

        // Although functionally equivalent to 0 for BitmapFactory, 1 is a safer default for our code than 0.
        return Math.max(1, powerOfTwoSampleSize);
    }

    private Bitmap downsampleWithSize(MarkEnforcingInputStream is, RecyclableBufferedInputStream  bufferedStream,
            BitmapFactory.Options options, BitmapPool pool, int inWidth, int inHeight, int sampleSize,
            DecodeFormat decodeFormat) {
        // Prior to KitKat, the inBitmap size must exactly match the size of the bitmap we're decoding.
        Bitmap.Config config = getConfig(is, decodeFormat);
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = config;
        if ((options.inSampleSize == 1 || Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) && shouldUsePool(is)) {
            int targetWidth = (int) Math.ceil(inWidth / (double) sampleSize);
            int targetHeight = (int) Math.ceil(inHeight / (double) sampleSize);
            // BitmapFactory will clear out the Bitmap before writing to it, so getDirty is safe.
            setInBitmap(options, pool.getDirty(targetWidth, targetHeight, config));
        }
        return decodeStream(is, bufferedStream, options);
    }

```
通过设置采样率缩放图片，降低内存占用，提高加载性能。

编码之后通过transformEncodeAndTranscode方法进行转换处理。--DecodeJob类
```
   private Resource<T> transform(Resource<T> decoded) {
        if (decoded == null) {
            return null;
        }

        Resource<T> transformed = transformation.transform(decoded, width, height);
        if (!decoded.equals(transformed)) {
            decoded.recycle();
        }
        return transformed;
    }

```

transform方法调用了Transformation的transform方法。


Transformation是接口；
BitmapTransformation实现了该接口但留下了另一个抽象方法transform；
CenterCrop和FitCenter两个类继承了BitmapTransformation并实现了抽象方法transform。


*  BitmapTransformation

```
   @Override
    public final Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        if (!Util.isValidDimensions(outWidth, outHeight)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + outWidth + " or height: "
                    + outHeight + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        Bitmap toTransform = resource.get();
        int targetWidth = outWidth == Target.SIZE_ORIGINAL ? toTransform.getWidth() : outWidth;
        int targetHeight = outHeight == Target.SIZE_ORIGINAL ? toTransform.getHeight() : outHeight;
        Bitmap transformed = transform(bitmapPool, toTransform, targetWidth, targetHeight);

        final Resource<Bitmap> result;
        if (toTransform.equals(transformed)) {
            result = resource;
        } else {
            result = BitmapResource.obtain(transformed, bitmapPool);
        }

        return result;
    }

        protected abstract Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight)
```

* CenterCrop


```
  protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        final Bitmap toReuse = pool.get(outWidth, outHeight, toTransform.getConfig() != null
                ? toTransform.getConfig() : Bitmap.Config.ARGB_8888);
        Bitmap transformed = TransformationUtils.centerCrop(toReuse, toTransform, outWidth, outHeight);
        if (toReuse != null && toReuse != transformed && !pool.put(toReuse)) {
            toReuse.recycle();
        }
        return transformed;
    }

```
* FitCenter
```
 @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.fitCenter(toTransform, pool, outWidth, outHeight);
    }
```

由BitmapPool提供一个Bitmap作为下一步的Canvas载体。BitmapPool的实现类是LruBitmapPool，顾名思义是一个基于LRU方式的Bitmap缓存池，用于Bitmap的复用。


*  TransformationUtils

```
public static Bitmap centerCrop(Bitmap recycled, Bitmap toCrop, int width, int height) {
        if (toCrop == null) {
            return null;
        } else if (toCrop.getWidth() == width && toCrop.getHeight() == height) {
            return toCrop;
        }
        // From ImageView/Bitmap.createScaledBitmap.
        final float scale;
        float dx = 0, dy = 0;
        Matrix m = new Matrix();
        if (toCrop.getWidth() * height > width * toCrop.getHeight()) {
            scale = (float) height / (float) toCrop.getHeight();
            dx = (width - toCrop.getWidth() * scale) * 0.5f;
        } else {
            scale = (float) width / (float) toCrop.getWidth();
            dy = (height - toCrop.getHeight() * scale) * 0.5f;
        }

        m.setScale(scale, scale);
        m.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        final Bitmap result;
        if (recycled != null) {
            result = recycled;
        } else {
            result = Bitmap.createBitmap(width, height, getSafeConfig(toCrop));
        }

        // We don't add or remove alpha, so keep the alpha setting of the Bitmap we were given.
        TransformationUtils.setAlpha(toCrop, result);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(PAINT_FLAGS);
        canvas.drawBitmap(toCrop, m, paint);
        return result;
    }
```
在TransformationUtils的centerCrop方法中，根据目标尺寸调整矩阵并绘制结果。
与上面的流程类似，FitCenter类的transform方法调用TransformationUtils的fitCenter方法调整Bitmap。

继续看DecodeJob的transformEncodeAndTranscode方法。接下来调用writeTransformedToCache将转换结果写入磁盘缓存，再调用transcode方法进行转码。transcode方法中的transcoder的实际类型是GlideBitmapDrawableTranscoder。


* GlideBitmapDrawableTranscoder
```
  @Override
    public Resource<GlideBitmapDrawable> transcode(Resource<Bitmap> toTranscode) {
        GlideBitmapDrawable drawable = new GlideBitmapDrawable(resources, toTranscode.get());
        return new GlideBitmapDrawableResource(drawable, bitmapPool);
    }
```
GlideBitmapDrawableTranscoder的transcode方法将Bitmap资源进行封装。
到这里就结束了decodeFromSource的流程。
然后看decodeFromCache。

* EngineRunnable-decodeFromCache

```
  private Resource<?> decodeFromCache() throws Exception {
        Resource<?> result = null;
        try {
            result = decodeJob.decodeResultFromCache();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Exception decoding result from cache: " + e);
            }
        }

        if (result == null) {
            result = decodeJob.decodeSourceFromCache();
        }
        return result;
    }
```
先调用DecodeJob的decodeResultFromCache方法获取，获取失败则调用DecodeJob的decodeSourceFromCache方法。


* decodeResultFromCache
```
 public Resource<Z> decodeResultFromCache() throws Exception {
        if (!diskCacheStrategy.cacheResult()) {
            return null;
        }

        long startTime = LogTime.getLogTime();
        Resource<T> transformed = loadFromCache(resultKey);
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Decoded transformed from cache", startTime);
        }
        startTime = LogTime.getLogTime();
        Resource<Z> result = transcode(transformed);
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Transcoded transformed from cache", startTime);
        }
        return result;
    }
```
先loadFromCache 再

* loadFromCache
```
 private Resource<T> loadFromCache(Key key) throws IOException {
        File cacheFile = diskCacheProvider.getDiskCache().get(key);
        if (cacheFile == null) {
            return null;
        }

        Resource<T> result = null;
        try {
            result = loadProvider.getCacheDecoder().decode(cacheFile, width, height);
        } finally {
            if (result == null) {
                diskCacheProvider.getDiskCache().delete(key);
            }
        }
        return result;
    }
```
*  transcode
```

    private Resource<Z> transcode(Resource<T> transformed) {
        if (transformed == null) {
            return null;
        }
        return transcoder.transcode(transformed);
    }
```

* decodeSourceFromCache 就是上面 的decodeSource
```
   public Resource<Z> decodeSourceFromCache() throws Exception {
        if (!diskCacheStrategy.cacheSource()) {
            return null;
        }

        long startTime = LogTime.getLogTime();
        Resource<T> decoded = loadFromCache(resultKey.getOriginalKey());
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            logWithTimeAndKey("Decoded source from cache", startTime);
        }
        return transformEncodeAndTranscode(decoded);
    }
```

decodeResultFromCache方法从磁盘缓存中获取对应Bitmap并将其转码。
decodeSourceFromCache方法从磁盘缓存中获取对应Bitmap并将其转换（因为是原尺寸，需要调整大小）。
到这里结束了EngineRunnable的decode方法的流程。

* EngineRunnable的decode是decodeFromCache和decodeFromSource二选一，decodeFromCache也获取失败的话就没有然后了
EngineRunnable

```
 @Override
    public void run() {
        if (isCancelled) {
            return;
        }

        Exception exception = null;
        Resource<?> resource = null;
        try {
            resource = decode();
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "Exception decoding", e);
            }
            exception = e;
        }

        if (isCancelled) {
            if (resource != null) {
                resource.recycle();
            }
            return;
        }

        if (resource == null) {
            onLoadFailed(exception);
        } else {
            onLoadComplete(resource);
        }
    }
```

失败会走onLoadFailed，成功会走onLoadComplete
缓存中没有结果的情况下会调用onLoadFailed方法，变更缓存策略重新放入线程池中执行，也就是从网络获取。这里的线程池是sourceService而不是上面的diskCacheService
得到了处理结果，接下来调用EngineRunnable的onLoadComplete方法将结果传入

```
  private void onLoadComplete(Resource resource) {
        manager.onResourceReady(resource);
    }

    private void onLoadFailed(Exception e) {
        if (isDecodingFromCache()) {
            stage = Stage.SOURCE;
            manager.submitForSource(this);
        } else {
            manager.onException(e);
        }
    }
```
onLoadComplete方法调用了EngineJob的onResourceReady方法。

* EngineJob-onResourceReady

```
  @Override
    public void onResourceReady(final Resource<?> resource) {
        this.resource = resource;
        MAIN_THREAD_HANDLER.obtainMessage(MSG_COMPLETE, this).sendToTarget();
    }
```
只是赋值加发送消息

```
  private static class MainThreadCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (MSG_COMPLETE == message.what || MSG_EXCEPTION == message.what) {
                EngineJob job = (EngineJob) message.obj;
                if (MSG_COMPLETE == message.what) {
                    job.handleResultOnMainThread();
                } else {
                    job.handleExceptionOnMainThread();
                }
                return true;
            }

            return false;
        }
    }
```
接收消息判断，并处理
```
    //完成处理
    private void handleResultOnMainThread() {
        if (isCancelled) {
            resource.recycle();
            return;
        } else if (cbs.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        }
        engineResource = engineResourceFactory.build(resource, isCacheable);
        hasResource = true;

        // Hold on to resource for duration of request so we don't recycle it in the middle of notifying if it
        // synchronously released by one of the callbacks.
        engineResource.acquire();
        listener.onEngineJobComplete(key, engineResource);

        for (ResourceCallback cb : cbs) {
            if (!isInIgnoredCallbacks(cb)) {
                engineResource.acquire();
                cb.onResourceReady(engineResource);
            }
        }
        // Our request is complete, so we can release the resource.
        engineResource.release();
    }

    //异常处理
    private void handleExceptionOnMainThread() {
        if (isCancelled) {
            return;
        } else if (cbs.isEmpty()) {
            throw new IllegalStateException("Received an exception without any callbacks to notify");
        }
        hasException = true;

        listener.onEngineJobComplete(key, null);

        for (ResourceCallback cb : cbs) {
            if (!isInIgnoredCallbacks(cb)) {
                cb.onException(exception);
            }
        }
    }
```
onResourceReady方法中向Handler传递消息并由MainThreadCallback处理消息 ，也就切换到了主线程。
handleResultOnMainThread方法会调用GenericRequest的onResourceReady方法。

* GenericRequest
```
public void onResourceReady(Resource<?> resource) {
        if (resource == null) {
            onException(new Exception("Expected to receive a Resource<R> with an object of " + transcodeClass
                    + " inside, but instead got null."));
            return;
        }

        Object received = resource.get();
        if (received == null || !transcodeClass.isAssignableFrom(received.getClass())) {
            releaseResource(resource);
            onException(new Exception("Expected to receive an object of " + transcodeClass
                    + " but instead got " + (received != null ? received.getClass() : "") + "{" + received + "}"
                    + " inside Resource{" + resource + "}."
                    + (received != null ? "" : " "
                        + "To indicate failure return a null Resource object, "
                        + "rather than a Resource object containing null data.")
            ));
            return;
        }

        if (!canSetResource()) {
            releaseResource(resource);
            // We can't set the status to complete before asking canSetResource().
            status = Status.COMPLETE;
            return;
        }

        onResourceReady(resource, (R) received);
    }

       private void onResourceReady(Resource<?> resource, R result) {
            // We must call isFirstReadyResource before setting status.
            boolean isFirstResource = isFirstReadyResource();
            status = Status.COMPLETE;
            this.resource = resource;

            if (requestListener == null || !requestListener.onResourceReady(result, model, target, loadedFromMemoryCache,
                    isFirstResource)) {
                GlideAnimation<R> animation = animationFactory.build(loadedFromMemoryCache, isFirstResource);
                target.onResourceReady(result, animation);
            }

            notifyLoadSuccess();

            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                logV("Resource ready in " + LogTime.getElapsedMillis(startTime) + " size: "
                        + (resource.getSize() * TO_MEGABYTE) + " fromCache: " + loadedFromMemoryCache);
            }
        }
```
onResourceReady方法调用GlideDrawableImageViewTarget的onResourceReady方法。


* GlideDrawableImageViewTarget


```
    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
        if (!resource.isAnimated()) {
            //TODO: Try to generalize this to other sizes/shapes.
            // This is a dirty hack that tries to make loading square thumbnails and then square full images less costly
            // by forcing both the smaller thumb and the larger version to have exactly the same intrinsic dimensions.
            // If a drawable is replaced in an ImageView by another drawable with different intrinsic dimensions,
            // the ImageView requests a layout. Scrolling rapidly while replacing thumbs with larger images triggers
            // lots of these calls and causes significant amounts of jank.
            float viewRatio = view.getWidth() / (float) view.getHeight();
            float drawableRatio = resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight();
            if (Math.abs(viewRatio - 1f) <= SQUARE_RATIO_MARGIN
                    && Math.abs(drawableRatio - 1f) <= SQUARE_RATIO_MARGIN) {
                resource = new SquaringDrawable(resource, view.getWidth());
            }
        }
        super.onResourceReady(resource, animation);
        this.resource = resource;
        resource.setLoopCount(maxLoopCount);
        resource.start();
    }

     @Override
        protected void setResource(GlideDrawable resource) {
            view.setImageDrawable(resource);
        }
```
调用了父类的ImageViewTarget 中的方法

* ImageViewTarget
```
 @Override
    public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
        if (glideAnimation == null || !glideAnimation.animate(resource, this)) {
            setResource(resource);
        }
    }
    protected abstract void setResource(Z resource);
```

GlideDrawableImageViewTarget的onResourceReady方法调用其父类ImageViewTarget的onResourceReady方法，
而ImageViewTarget的onResourceReady方法中调用的抽象方法setResource在子类GlideDrawableImageViewTarget中实现，
该方法中调用了ImageView的setImageDrawable方法设置图像。至此，整个加载流程就完成了。



![glide.png](glide.png)


### load方法 在RequestManager中

有很多的load方法 以url为例
```
@Deprecated
    public DrawableTypeRequest<URL> load(URL url) {
        return (DrawableTypeRequest<URL>) fromUrl().load(url);
    }

    @Deprecated
    public DrawableTypeRequest<URL> fromUrl() {
        return loadGeneric(URL.class);
    }


```
最后调的都是loadGeneric

```
private <T> DrawableTypeRequest<T> loadGeneric(Class<T> modelClass) {
        ModelLoader<T, InputStream> streamModelLoader = Glide.buildStreamModelLoader(modelClass, context);
        ModelLoader<T, ParcelFileDescriptor> fileDescriptorModelLoader =
                Glide.buildFileDescriptorModelLoader(modelClass, context);
        if (modelClass != null && streamModelLoader == null && fileDescriptorModelLoader == null) {
            throw new IllegalArgumentException("Unknown type " + modelClass + ". You must provide a Model of a type for"
                    + " which there is a registered ModelLoader, if you are using a custom model, you must first call"
                    + " Glide#register with a ModelLoaderFactory for your custom model class");
        }

        return optionsApplier.apply(
                new DrawableTypeRequest<T>(modelClass, streamModelLoader, fileDescriptorModelLoader, context,
                        glide, requestTracker, lifecycle, optionsApplier));
    }
```

* DrawableTypeRequest

```
 DrawableTypeRequest(Class<ModelType> modelClass, ModelLoader<ModelType, InputStream> streamModelLoader,
            ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader, Context context, Glide glide,
            RequestTracker requestTracker, Lifecycle lifecycle, RequestManager.OptionsApplier optionsApplier) {
        super(context, modelClass,
                buildProvider(glide, streamModelLoader, fileDescriptorModelLoader, GifBitmapWrapper.class,
                        GlideDrawable.class, null),
                glide, requestTracker, lifecycle);
        this.streamModelLoader = streamModelLoader;
        this.fileDescriptorModelLoader = fileDescriptorModelLoader;
        this.optionsApplier = optionsApplier;
    }
```
调用了super

* DrawableRequestBuilder
```
public class DrawableRequestBuilder<ModelType>
        extends GenericRequestBuilder<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable>
        implements BitmapOptions, DrawableOptions {

    DrawableRequestBuilder(Context context, Class<ModelType> modelClass,
            LoadProvider<ModelType, ImageVideoWrapper, GifBitmapWrapper, GlideDrawable> loadProvider, Glide glide,
            RequestTracker requestTracker, Lifecycle lifecycle) {
        super(context, modelClass, loadProvider, GlideDrawable.class, glide, requestTracker, lifecycle);
        // Default to animating.
        crossFade();
    }
```

调用了crossFade设置淡入淡出效果

```
  public final DrawableRequestBuilder<ModelType> crossFade() {
        super.animate(new DrawableCrossFadeFactory<GlideDrawable>());
        return this;
    }
```

*DrawableCrossFadeFactory  默认淡入淡出效果时间

```
public class DrawableCrossFadeFactory<T extends Drawable> implements GlideAnimationFactory<T> {
    private static final int DEFAULT_DURATION_MS = 300;
```