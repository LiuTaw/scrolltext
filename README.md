# Scroll Text View for Android

------
This project helps you build a scroll text vertically.Horizon mode is still under way.:)
本项目的目的是能让字体上下滚动，这个可以做一些公示或者活动的效果（比如淘宝里面），我看了很多开源的项目，并没有实现，我就自己写了一个。这个支持并在用户点击的时候给予事件反馈，可以设定滚动时间间隔，现在支持上下滚动，其实这个项目可以扩展成更多自定义view的滚动效果，简单修改之后也可以支持水平滚动。
##Example:
![](https://github.com/LiuTaw/scrolltext/blob/master/example.gif)
##How to use:
###In your layout:
    <com.liutaw.mvctest.ScrollTextVertical
        android:id="@+id/scrolltextvertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </com.liutaw.mvctest.ScrollTextVertical>
    
###In your Activity:
####Bind the ScrollTextVertical view and set data:
####data: List<String> data = new ArrayList<>();
        data.add("This is from LiuTaw's GitHub!");
        data.add("Try to click me!");
        data.add("3333333333333333");
        data.add("4444444444444444");
        data.add("5555555555555555");
        data.add("6666666666666666");
####bind the view:
scrollTextVertical = (ScrollTextVertical) this.findViewById(R.id.scrolltextvertical);
scrollTextVertical.setData(data);
