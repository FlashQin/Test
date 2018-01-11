# GridView 平均分配布局
简介:------------------------------------------------------------------------------------------------------<br>.这是本人第一个<br>Github测试项目,旨在测试代码上传流程,可以正常使用,欢迎下载
<br>.本测试项目属于Demo项目,先上图片吧
<br>![曾艳勤](https://github.com/FlashQin/Imgs/blob/master/device-2018-01-11-154026.png)
<br>![曾艳勤](https://github.com/FlashQin/Imgs/blob/master/device-2018-01-11-154059.png)

<br>看到没有,GridView 的子元素不管大小都平均分配大小,整齐布局,就像来到一个女儿国,你懂的

<br>好了废话不多说,我显现的原理就是首先获取本手机的屏幕的大小PX,方法如下:

       <br> WindowManager windowManager = getWindowManager();
       <br> Display display = windowManager.getDefaultDisplay();
       <br> int whid = display.getWidth();//PX


<br>然后在我们的Adapter里面,指定子元素的宽度和高度,当然需要通过计算:

<br> @Override
    <br>public View getView(final int position, View convertView, ViewGroup parent) {
     <br>   ViewHolder holder = null;
     <br>   if (convertView == null) {
       <br>     convertView = LayoutInflater.from(context).inflate(R.layout.item_girdview_fragment_home, null);
        <br>    holder = new ViewHolder(convertView);
        <br>    //设置gridview item宽高自动适应各种屏幕
        <br>    int width = (int) ((whid - (5 * SomeInfoData.Dp2px(context, 5))) / 3);
	<br>    //5表示子元素之间的距离,3表示一排显示的个数,这样就平均排列了
         <br>   AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, width-10);
         <br>   convertView.setLayoutParams(params);
	 <br>   //设置宽度和高度一样

         <br>   convertView.setTag(holder);
      <br>  } else {
           <br> holder = (ViewHolder) convertView.getTag();
      <br>  }

/***
     * px 转dp
     * @param context
     * @param dp
     * @return
     */
    <br>public static int Dp2px(Context context, float dp) {
       <br> final float sc = context.getResources().getDisplayMetrics().density;
       <br> int dd=(int)(dp * sc + 0.5f);//浮点数取整数
	
       <br> return dd;

   <br> }
		
		
		

		
		
	
		
		
		.over-------------------------------------------------------------------------------------------------------The end

d




