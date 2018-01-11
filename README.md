# GridView 平均分配布局
简介:------------------------------------------------------------------------------------------------------<br>.这是本人第一个Github测试项目,旨在测试代码上传流程,可以正常使用,欢迎下载
.本测试项目属于Demo项目,先上图片吧
![曾艳勤](https://github.com/FlashQin/Imgs/blob/master/device-2018-01-11-154026.png)
![曾艳勤](https://github.com/FlashQin/Imgs/blob/master/device-2018-01-11-154059.png)

看到没有,GridView 的子元素不管大小都平均分配大小,整齐布局,就像来到一个女儿国,你懂的

好了废话不多说,我显现的原理就是首先获取本手机的屏幕的大小PX,方法如下:

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int whid = display.getWidth();//PX


然后在我们的Adapter里面,指定子元素的宽度和高度,当然需要通过计算:

 @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girdview_fragment_home, null);
            holder = new ViewHolder(convertView);
            //设置gridview item宽高自动适应各种屏幕
            int width = (int) ((whid - (5 * SomeInfoData.Dp2px(context, 5))) / 3);
	    //5表示子元素之间的距离,3表示一排显示的个数,这样就平均排列了
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, width-10);
            convertView.setLayoutParams(params);
	    //设置宽度和高度一样

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

/***
     * px 转dp
     * @param context
     * @param dp
     * @return
     */
    public static int Dp2px(Context context, float dp) {
        final float sc = context.getResources().getDisplayMetrics().density;
        int dd=(int)(dp * sc + 0.5f);//浮点数取整数
	
        return dd;

    }
		
		
		

		
		
	
		
		
		.over-------------------------------------------------------------------------------------------------------The end

d




