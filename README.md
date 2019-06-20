# cmdemo
一些杂七杂八的例子

# RecyclerView 嵌套滚动

![](https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650828127&idx=1&sn=a41f70febd47b9813fd124073028a5d1&chksm=80b7b9c1b7c030d707dbe8393bedec01e074c672014139715a36e273dcf232f71088eab4475c&mpshare=1&scene=1&srcid=0617OUaeDN16bzvzNGilbQfi&rd2werd=1#wechat_redirect)

# onTouchEvent

见 `CmView` 和 `CmViewGroup`。

要点：`ViewGroup.onInterceptTouchEvent()` 返回 `true` 之后对 touch event 事件处理流程的影响。

# fake_dlopen 示例

+ 了解 [android-7.0-changes](https://developer.android.com/about/versions/nougat/android-7.0-changes#ndk)
+ 了解 [Improving Stability with Private C/C++ Symbol Restrictions in Android N](https://android-developers.googleblog.com/2016/06/improving-stability-with-private-cc.html)
+ 使用 [avs333/Nougat_dlfunctions](https://github.com/avs333/Nougat_dlfunctions)

示例见 `LoadSoActivity.kt`

# Bitmap 解码性能测试

测试代码见 [BitmapDecodePerfActivity](https://github.com/410063005/cmdemo/blob/master/app/src/main/java/com/sunmoonblog/cmdemo/bitmap/BitmapDecodePerfActivity.kt)。

测试结论：

+ 解码性能跟图片体积有一定关系, 基本上体积越大解码时间越长
+ 解码性能跟图片尺寸有一定关系, 基本上尺寸越大解码时间越长
+ WebP 解码性能比 PNG 解码性能差
+ 较大图片的解码时间常常超过 16ms，所以不应该在主线程解码较大图片
+ 小图片的解码时间很短，几乎不会影响到主线程
+ 同一个图片放在不同的资源目录, 会影响解码性能

## 图片体积和图片尺寸

图片体积和图片尺寸会影响解码性能。

|图片名      |体积(KB)|尺寸     |内存(MB)|解码时间(ms)|
|-----------|-------|--------|-------|-----------|
|kb_1.png   |1      |116x22  |0      |0.5        |
|kb_50.png  |53     |750x520 |1.5    |7.1        |
|kb_80.png  |88     |1125x711|3.1    |11.1       |
|kb_100.png |103    |750x416 |1.2    |7.0        |
|kb_190.png |190    |750x460 |1.3    |10.0       |
|kb_320.png |326    |752x942 |2.7    |17.8       |

(数据来自 BitmapDecodePerfActivity.button12.setOnClickListener {} )

从上表可以看出，

+ 随着图片体积增加，解码时间也越来越长
+ `kb_100.png` 体积比 `kb_80.png` 大，但解码时间反而短，看起来是个例外。原因可能是因为图片尺寸增加也会影响到解码时间，`kb_80.png` 的尺寸是 `kb_100.png` 的两倍以上
+ 小图片的解码时间很短，几乎不会影响到主线程。实际开发中可以直接在布局文件中引用小图片资源，不用担心影响主线程
+ 较大图片的解码时间可能超过 16ms，比如这里的 `kb_320.png`，所以不应该在主线程解码较大图片

## WebP 与 PNG

|图片名      |体积(KB)|尺寸     |内存(MB)|解码时间(ms)|
|-----------|-------|--------|-------|-----------|
|kb_80.webp |78     |1500x742|4.2    |29.7       |
|kb_90.webp |91     |750x1206|3.5    |24.9       |
|kb_100.webp|96     |750x1334|3.8    |27.6       |

(数据来自 BitmapDecodePerfActivity.button11.setOnClickListener {} )

直观上 WebP 图片解码耗时更长。以下是对比：

|图片名                |体积(KB)|尺寸      |内存(MB)|解码时间(ms)|
|---------------------|-------|---------|-------|-----------|
|jialuo.png           |326    |752x942  |2.7    |21.5       |
|jialuo_lossless.webp |222    |752x942  |2.7    |30.0       |
|jialuo_lossy_75.webp |55     |752x942  |2.7    |24.6       |

(数据来自 BitmapDecodePerfActivity.button18.setOnClickListener {} )

上表对三张相同尺寸的图片进行测试：

+ `jialuo.png` - 原始 PNG 图片
+ `jialuo_lossless.webp` - 原始图片无损压缩得到的 WebP
+ `jialuo_lossy_75.webp` - 原始图片有损压缩压缩后得到的 WebP，质量为75%

从上表可以看出，

+ 相同尺寸下，WebP 图片体积明显小很多
+ 相同质量下，WebP 图片解码性能明显比 PNG 要低
+ 降低质量后 WebP 图片体积减小很多，可能在一定程度上弥补解码性能差的问题
+ 较大图片的解码时间常常超过 16ms，比方说这里的三张图片均超过 16ms，所以不应该在主线程解码较大图片

# 分辨率与图片资源目录
图片资源该放在哪个目录， `hdpi` 还是 `xhdpi` 还是 `xxhdpi`？官方对此的说明见[这里](https://developer.android.com/training/multiscreen/screendensities#TaskProvideAltBmp)

|图片名                  |目录    |Bitmap尺寸|内存(MB)|解码时间(ms)|
|-----------------------|-------|---------|-------|-----------|
|splash_as_xhdpi.webp   |xhdpi  |1125x2001|8.6    |82.4      |
|splash_as_xxhdpi.webp  |xxhdpi |750x1334 |3.8    |27.1      |
|splash_as_xxxhdpi.webp |xxxhdpi|563x1001 |2.1    |40.0      |

(数据来自 BitmapDecodePerfActivity.button19.setOnClickListener {} )

上表中三张图片的体积均为 96KB，尺寸均为 750x1334。

测试手机是华为 Nova 2，屏幕大小 1080x2150，densityDpi 是 480。根据 Android 源码的定义，480 的手机分类是 DENSITY_XXHIGH。

```java
public class DisplayMetrics {
    /**
     * Standard quantized DPI for extra-extra-high-density screens.
     */
    public static final int DENSITY_XXHIGH = 480;
}
```

从上表可以看出，

+ 同一张图片放在不同的资源目录，最终解码出来的图片大小不一样。理由很简单，系统会对根据屏幕分辨率图片资源进行缩放处理
+ 图片放在错误的资源目录会产生不良影响。以 `splash.webp` 为例，由于我的测试机屏幕分辨率是 DENSITY_XXHIGH，所以预期是将它放在 `xxhdpi` 目录
  + 如果错误地放在 `xhdpi` 目录，得到一个放大的 Bitmap，白白消耗内存
  + 如果放在不正确的目录，解码时间变长

# 总结
精确测试解码性能比较困难，从但上述测试数据中不难归纳出以下结论：

+ 大图片的解码时间很可能超过 16ms。所以为了更流畅的用户体验，**不应在主线程中解码大图片**
+ 图片放在错误的资源目录，不仅浪费内存，还会增加解码时间。所以为了更好的性能，**应当提供为不同屏幕密度提供合适的图片资源**

(测试过程中，为了让数据更稳定，做法是取解码20次取平均值。这样做是否有问题？之所以有这个疑问，因为测试中发现一个现象：解码20次得到的平均值倾向于比单独测试一次得到的值要小。)