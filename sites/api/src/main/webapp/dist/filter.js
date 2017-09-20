/**
 * Created by Ryan on 16/6/2.
 */
(function($win) {

    $win.gxbapp.filter("dateSubStr",function(){

        return function (param){
            return param.substr(0,param.indexOf(" "))

        }
    })





})(window);
