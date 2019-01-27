
一开始不知道如何控制

+ Swap Buffers
+ Sync & Upload
+ Issue Commands 过多的绘制命令  https://developer.android.com/topic/performance/rendering/profile-gpu
+ MiscTime/VSync Delay 代表在连续两帧间的时间间隔,可能是因为子线程执行时间过长抢占了UI线程被cpu执行的机会.  [ref](https://www.jianshu.com/p/0b90891771e9)

参考文档

https://developer.android.com/topic/performance/rendering/profile-gpu


https://developer.android.com/topic/performance/rendering/profile-gpu#psb

> It’s also worth noting that RecyclerView scrolling can appear in this phase. RecyclerView scrolls immediately when it consumes the touch event. As a result, it can inflate or populate new item views. For this reason, it’s important to make this operation as fast as possible. Profiling tools like Traceview or Systrace can help you investigate further.

> The Animations phase shows you just how long it took to evaluate all the animators that were running in that frame. The most common animators are ObjectAnimator, ViewPropertyAnimator, and Transitions.

> draw: The measured time applies to any code that you have added to the UI objects in your app. Examples of such code may be the onDraw(), dispatchDraw(), and the various draw ()methods belonging to the subclasses of the Drawable class.

>

