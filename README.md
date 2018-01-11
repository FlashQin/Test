# Test
简介:------------------------------------------------------------------------------------------------------
.这是本人第一个Github测试项目,旨在测试代码上传流程,
.本测试项目属于Demo项目
.请大家切勿下载
.因为里面有很多不该有的东西

测试模板-------------------------------------------------------------------------------------------------------

这是一个Tab





继续测试--------------------------------------------------------------------------------------------------------





    public   void login(Handler handler){

        sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        String phone = sp.getString("account", null);
        String utoken = sp.getString("utoken", null);
        String password = sp.getString("password", null);

        map.put("userName",phone);
        map.put("password", password);
        String json = ToJson.mapToJson(map);
        new HttpUtils().DoPostJson(myOKHttp, getString(R.string.url_root) + getString(R.string.login), handler, HTTP_SUCC, json, "");
    }
		
		
		
		.以上是部分测试代码--------------------------------------------------------------
		
		
		.over
		-------------------------------------------------------------------------------------------------------The end

d




