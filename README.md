# Scroll Text View for Android

------
This project helps you build a scroll text vertically.Horizon mode is still under way.:)
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
