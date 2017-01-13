/**
 * Created by winds on 16/4/5.
 */
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE
            presto: u.indexOf('Presto') > -1, //opera
            webKit: u.indexOf('AppleWebKit') > -1, //webkit
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //firefox
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //mobile
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android or uc
            android43: function () {
                var s = navigator.userAgent.substr(navigator.userAgent.indexOf('Android') + 8, 3);
                console.log("version android " + s, parseFloat(s) < 4.4);
                return parseFloat(s) < 4.4 ? true : false
            }(),
            iPhone: u.indexOf('iPhone') > -1, //iPhone QQHD
            iPad: u.indexOf('iPad') > -1, //iPad
            webApp: u.indexOf('Safari') == -1,
            weixin:u.indexOf('MicroMessenger')>-1
        };
    }()
};


$(document).ready(function(){
    //var isQa=false; //修改isQa的值可更换是测试版的还是发布版的
    //var ios=document.getElementsByClassName('button_ios')[0];
    //if(isQa){
    //    ios.innerHTML='<a href="itms-services://?action=download-manifest&url=https://dn-cndr.qbox.me/zjjy.plist" class="text-muted btn">下载安装</a>';
    //}else{
    //    ios.innerHTML='<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.dfss.dfssclub" class="text-muted btn">下载安装</a>';
    //}
    if(browser.versions.weixin){
        //$(".content_android").show();
        $('body').addClass("overflow");
        $('.logo_apple').hide();
        $('.logo_android').hide();
        $('.button_ios').hide();
        $('.button_android').hide();
        $('.button_text').show();
        $('.model').show();
    }else if(browser.versions.ios){
        //$(".content_ios").show();
        $('.logo_apple').show();
        $('.logo_android').hide();
        $('.button_ios').show();
        $('.button_android').hide();
        $('.button_text').hide();
        $('.model').hide();
    }else if(browser.versions.android || !browser.versions.mobile) {
        //}$(".content_pc").show();
        $('.logo_apple').hide();
        $('.logo_android').show();
        $('.button_ios').hide();
        $('.button_android').show();
        $('.button_text').hide();
        $('.model').hide();
    }
});