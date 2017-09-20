(function($win) {
    $win.gxbapp = angular.module('gxbApp', ['gxb.controllers', 'gxb.directives', 'gxb.filters', "ui.router", 'ngSanitize', 'ngTouch','ngDialog','com.2fdevs.videogular',"com.2fdevs.videogular.plugins.controls","com.2fdevs.videogular.plugins.overlayplay","com.2fdevs.videogular.plugins.poster"]);
    $win.gxbapp.config(['$httpProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider', '$sceDelegateProvider',
        function($httpProvider, $locationProvider, $stateProvider, $urlRouterProvider, $sceDelegateProvider) {

            var viewRoot = "views/";

            $sceDelegateProvider.resourceUrlWhitelist([
                'self',
                window.location.protocol + "//gxb-file.s3.bj.xs3cnc.com/**",
                window.location.protocol + "//gxbvideo.gaoxiaobang.com/**"
            ]);
            var registerRouter = function(stateName, config) {
                $stateProvider.state(stateName, config);
            }

            var getTemplateUrl = function(templateUrl) {
                return viewRoot + templateUrl + ".html";
            }

            var getTemplateURL = function(ctrlName) {
                return getTemplateUrl(gxb.controllers.getView(ctrlName))
            }

            var getTemplateUrlAndController = function(ctrlName) {

                return {
                    templateUrl: getTemplateUrl(gxb.controllers.getView(ctrlName)),
                    controller: ctrlName
                };
            }

            registerRouter("demo", gxb.utils.objectUtils.extend({
                url: "/demo"
            }, getTemplateUrlAndController(gxb.controllers.demo.demoCtrlFullName)));

            // 微信登录页
            registerRouter("wechat", gxb.utils.objectUtils.extend({
                url: "/wechat",
                onEnter: function(){
                    // 进入页面
                },
                onExit: function(){
                    // 离开页面
                }
            }, getTemplateUrlAndController(gxb.controllers.users.wechatCtrlFullName)));

            registerRouter("intro", gxb.utils.objectUtils.extend({
                url: "/intro"
            }, getTemplateUrlAndController(gxb.controllers.intro.introCtrlFullName)));

            registerRouter("course_list", gxb.utils.objectUtils.extend({
                url: "/course/list"
            }, getTemplateUrlAndController(gxb.controllers.course.courseListCtrlFullName)));

            registerRouter("course_index", gxb.utils.objectUtils.extend({
                url: "/course/:courseId"
            }, getTemplateUrlAndController(gxb.controllers.course.courseIndexCtrlFullName)));

            registerRouter("chapter", gxb.utils.objectUtils.extend({
                url: "/chapter/:chapterId"
            }, getTemplateUrlAndController(gxb.controllers.chapter.chapterCtrlFullName)));

            registerRouter("chapterVideo", gxb.utils.objectUtils.extend({
                url: "/chapterVideo/:chapterId"
            }, getTemplateUrlAndController(gxb.controllers.chapter.chapterVideoCtrlFullName)));

            //测验
            registerRouter("quiz", gxb.utils.objectUtils.extend({
                url: "/quiz/:quizId"
            }, getTemplateUrlAndController(gxb.controllers.chapter.quizCtrlFullName)));

            //我的
            registerRouter("personal", gxb.utils.objectUtils.extend({
                url: "/personal"
            }, getTemplateUrlAndController(gxb.controllers.users.personalCtrlFullName)));

            //认知报告
            registerRouter("personal_report", gxb.utils.objectUtils.extend({
                url: "/personal/report/:name/:id"
            }, getTemplateUrlAndController(gxb.controllers.chapter.reportCtrlFullName)));

            //绑定高校邦账号
            registerRouter("bindAccount", gxb.utils.objectUtils.extend({
                url: "/bindAccount"
            }, getTemplateUrlAndController(gxb.controllers.users.bindAccountCtrlFullName)));

            //认证高校邦账号
            registerRouter("certification", gxb.utils.objectUtils.extend({
                url: "/certification"
            }, getTemplateUrlAndController(gxb.controllers.users.certificationCtrlFullName)));
            //注册高校邦
            registerRouter("register", gxb.utils.objectUtils.extend({
                url: "/register"
            }, getTemplateUrlAndController(gxb.controllers.users.registerCtrlFullName)));

            //九型人格详情
            registerRouter("character_type", gxb.utils.objectUtils.extend({
                url: "/character_type/:type"
            }, getTemplateUrlAndController(gxb.controllers.chapter.characterTypeCtrlFullName)));

            //高校邦序章
            registerRouter("prologue", gxb.utils.objectUtils.extend({
                url: "/prologue"
            }, getTemplateUrlAndController(gxb.controllers.chapter.prologueCtrlFullName)));

            //学校列表
            registerRouter("schoolList", gxb.utils.objectUtils.extend({
                url: "/schoolList"
            }, getTemplateUrlAndController(gxb.controllers.users.schoolListCtrlFullName)));


            //判断是否登录
            if (window.localStorage['openId']) {
                //判断是否有邀请课
                if (window.localStorage['invitationClass']) {
                    // $urlRouterProvider.otherwise("/addClass_certify/"+window.localStorage['invitationClass']);
                    window.localStorage.removeItem('invitationClass');
                } else {
                    // $urlRouterProvider.otherwise("/course");
                }
            } else {
                if (window.location.href.indexOf("/share") == -1) {
                    $urlRouterProvider.otherwise("/wechat");
                }
            }
        }
    ]);
    $win.gxbapp.run(['$state', '$rootScope', '$stateParams', function($state, $rootScope, $stateParams) {
        // if(window.location.hash != "" && !/wechat/.test(window.location.hash) && !window.sessionStorage.getItem('code')){
        //     alert('test');
        //     // $state.go('signin');
        //     // $location.hash('');
        // }
        $rootScope.$on("$stateChangeSuccess",function(event, toState, toParams, fromState, fromParams){
            var ua = window.navigator.userAgent.toLowerCase();
            var wechatUserInfo = window.sessionStorage.getItem('wechatUserInfo');
            console.log('$stateChangeSuccess');
            // if(toState && toState.name){
            //     if(toState.name != 'wechat'){
            //         window.sessionStorage.setItem('previousState', JSON.stringify({
            //             state: toState,
            //             params: toParams
            //         }));
            //     }else if(fromState && fromState.name){
            //         window.sessionStorage.setItem('previousState', JSON.stringify({
            //             state: fromState,
            //             params: fromParams
            //         }));
            //     }
            // }

            if(!wechatUserInfo){

                if(!/wechat/.test(window.location.hash)){
                // if(!/wechat/.test(window.location.hash) && ua.match(/MicroMessenger/i) != 'micromessenger'){
                    alert('用户信息有误，请重新访问！');
                    
                    window.sessionStorage.setItem('previousState', window.location.href);

                    // 测试环境
                    // window.location.href = window.location.protocol + '//dev.bigbang.gaoxiaobang.com/dist-wxlogin/index.html';
                    
                    // 生产环境
                    window.location.href = window.location.origin + window.location.pathname;
                }
            }else{
                if(window.sessionStorage.getItem('previousState')){
                    window.location.href = window.sessionStorage.getItem('previousState');
                    window.sessionStorage.removeItem('previousState');
                }
            }
        })

        //安全应用数据
        $rootScope.safeApply = function(fn) {
            var phase = this.$root.$$phase;
            if (phase == '$apply' || phase == '$digest') {
                if (fn && (typeof(fn) === 'function')) {
                    fn();
                }
            } else {
                this.$apply(fn);
            }
        };
        $rootScope.$on("$stateChangeStart",function(){
            $("#fakeLoader").hide();
            $rootScope.safeApply(function(){
                $rootScope.data_loading = false;
            })
        })
        $(document).on('ajaxStart', function(e, xhr, options){
            // console.log('ajaxStart');
            $rootScope.safeApply(function(){
                $rootScope.data_loading = true;
            })
        })
        $(document).on('ajaxBeforeSend', function(e, xhr, options){
            // console.log('ajaxBeforeSend');
        })
        $(document).on('ajaxSend', function(xhr, options){
            // console.log('ajaxSend');
        })
        $(document).on('ajaxSuccess', function(xhr, options, data){
            // console.log('ajaxSuccess');
            // console.log(data);
        })
        $(document).on('ajaxError', function(xhr, options, error){
            // console.log('ajaxError');
            // console.log(error);
        })
        $(document).on('ajaxComplete', function(xhr, options){
            // console.log('ajaxComplete');
        })
        $(document).on('ajaxStop', function(global){
            // console.log('ajaxStop');
            $("#fakeLoader").hide();
            $rootScope.safeApply(function(){
                $rootScope.data_loading = false;
            })
        });
    }])
    $win.gxbapp.config(['ngDialogProvider', function (ngDialogProvider) {
        ngDialogProvider.setDefaults({
            showClose: true,
            closeByDocument: true,
            closeByEscape: false
        });
    }]);
})(window);
